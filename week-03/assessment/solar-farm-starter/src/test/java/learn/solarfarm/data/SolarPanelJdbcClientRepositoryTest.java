package learn.solarfarm.data;

import learn.solarfarm.DataHelper;
import learn.solarfarm.models.Material;
import learn.solarfarm.models.SolarPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SolarPanelJdbcClientRepositoryTest {

//    JdbcClient jdbcClient = DataHelper.getJdbcClient();
//    SolarPanelJdbcClientRepository repository = new SolarPanelJdbcClientRepository(jdbcClient);
    @Autowired
    SolarPanelRepository repository;
    @Autowired
    JdbcClient jdbcClient;

    private final String existingSection = "Hill";
    private final String existingSection2 = "The Ridge";

    private final int existingRow = 1;
    private final int existingColumn = 3;

class SolarPanelJdbcClientRepositoryTest {

    JdbcClient jdbcClient = DataHelper.getJdbcClient();
    SolarPanelJdbcClientRepository repository = new SolarPanelJdbcClientRepository(jdbcClient);



    @BeforeEach
    void setUp() {
        jdbcClient.sql("call set_known_good_state()").update();
    }

    @Test
    void findBySection() throws DataAccessException {
      List<SolarPanel> actual = repository.findBySection(existingSection);
        assertEquals(2, actual.size());

        List<SolarPanel> actual = repository.findBySection("test-section1");
        assertEquals(1, actual.size());
        actual = repository.findBySection("test-section2");
        assertEquals(1, actual.size());
        actual = repository.findBySection("Hill");
        assertEquals(0, actual.size());


    @Test
    void findByKey() throws DataAccessException {

        SolarPanel expected = new SolarPanel(3, existingSection, existingRow, existingColumn, 2010, Material.A_SI, false);
        SolarPanel actual = repository.findByKey(existingSection, existingRow, existingColumn);

        SolarPanel expected = new SolarPanel(4, "test-section4", 1, 4, 2000, Material.CD_TE, true);
        SolarPanel actual = repository.findByKey("test-section4", 1, 4);

        assertEquals(expected, actual);
//        actual = repository.findByKey("test-section", 1, 1);
//        assertEquals(expected, actual);
//        actual = repository.findByKey("Hill", 1, 4);
//        assertNull(actual);
    }

    @Test
    void create() throws DataAccessException {
        // Arrange
        SolarPanel solarPanel = new SolarPanel();
        solarPanel.setSection("The Ridge");
        solarPanel.setRow(1);
        solarPanel.setColumn(10);
        solarPanel.setYearInstalled(2020);
        solarPanel.setMaterial(Material.A_SI);
        solarPanel.setTracking(true);

        SolarPanel expected = new SolarPanel(6, "The Ridge", 1, 10, 2020, Material.A_SI, true);

        SolarPanel result = repository.create(solarPanel);
        assertEquals(expected, result);

        SolarPanel actual = repository.create(solarPanel);
        assertEquals(6, actual.getId());
        List<SolarPanel> all = repository.findBySection("The Ridge");
        assertEquals(6, all.size());
        actual = all.get(2);
        assertEquals(expected, actual);

    }

    @Test
    void update() throws DataAccessException {// TODO: complete
        SolarPanel solarPanelToUpdate = new SolarPanel(1, "Flats", 2, 6, 1997, Material.A_SI, true);
        boolean result = repository.update(solarPanelToUpdate);

  assertTrue(result);


        assertTrue(result);
        SolarPanel afterUpdate = null;
        for (SolarPanel solarPanel : repository.findAll()) {
            if (solarPanel.getKey().equals(solarPanelToUpdate.getKey())) {
                afterUpdate = solarPanel;
                break;
            }
        }
        assertEquals(1997, afterUpdate.getYearInstalled());
        assertEquals(1, afterUpdate.getId());
        assertEquals(Material.MONO_SI, afterUpdate.getMaterial());


    }

    @Test
    void updateFailsToFind() throws DataAccessException {
        SolarPanel ToUpdate = new SolarPanel(7, "test", 2, 5, 2007, Material.POLY_SI, true);

        boolean result = repository.update(ToUpdate);

        assertFalse(result);
    }

    @Test

    void delete() throws DataAccessException {  // TODO: complete
        SolarPanel solarPanel = repository.findByKey("Hill", 1, 3);

    void deleteById() throws DataAccessException {  // TODO: complete
        boolean result = repository.deleteById(1);

        assertTrue(result);
        assertEquals(4, repository.findAll().size());

    }

    @Test

    void deleteFail() throws DataAccessException {// TODO: complete
        SolarPanel solarPanel = repository.findByKey("Hill", 10, 1);
        assertThrows(NullPointerException.class, () -> {
            boolean result = repository.delete(solarPanel);
            assertFalse(result);
        });

    void deleteByIdFail() throws DataAccessException {  // TODO: complete
        boolean result = repository.deleteById(7);
        assertFalse(result);
        assertEquals(5, repository.findAll().size());

    }
}