package learn.solarfarm.domain;

import learn.solarfarm.data.DataAccessException;
import learn.solarfarm.data.SolarPanelRepository;
import learn.solarfarm.data.SolarPanelRepositoryDouble;
import learn.solarfarm.models.Material;
import learn.solarfarm.models.SolarPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SolarPanelServiceTest {
    @Autowired
    SolarPanelService service;
    @MockBean
    SolarPanelRepository repository;

    List<SolarPanel> MockSolarPanel = List.of(new SolarPanel(1,"The Hills", 2, 3,2006, Material.CD_TE, true),
            new SolarPanel(2, "The Hills", 5, 3, 2025, Material.MONO_SI, false));

//    @BeforeEach
//    void setup() {
//        SolarPanelRepositoryDouble repository = new SolarPanelRepositoryDouble();
//        service = new SolarPanelService(repository);
//    }

    @Test
    void shouldFindTwoSolarPanelsForSectionOne() throws DataAccessException {
        when(repository.findBySection("The Hills")).thenReturn(List.of(new SolarPanel(1, "The Hills", 2, 3,2006, Material.CD_TE, true ),
                new SolarPanel(2, "The Hills", 5, 3, 2025, Material.MONO_SI, false)));

        List<SolarPanel> solarPanels = service.findBySection("The Hills");
        assertEquals(2, solarPanels.size());
    }

    @Test
    void shouldFindSolarPanelInSectionTwoRow10Column11() throws DataAccessException {
        when(repository.findByKey("Section Two", 10, 11)).thenReturn(new SolarPanel(6, "Section Two", 10, 11, 1997, Material.MONO_SI, true));
        SolarPanel solarPanel = service.findByKey("Section Two", 10, 11);
        assertNotNull(solarPanel);
    }

    @Test
    void shouldNotCreateNull() throws DataAccessException {
        // Arrange
        SolarPanel solarPanel = null;

        // Act
        SolarPanelResult result = service.create(solarPanel);

        // Assert
        assertFalse(result.isSuccess());
        assertEquals(1, result.getErrorMessages().size());
        assertTrue(result.getErrorMessages().get(0).contains("cannot be null"));
    }

    @Test
    void shouldNotCreateNullSection() throws DataAccessException {
        // Arrange
        SolarPanel solarPanel = new SolarPanel();
        solarPanel.setSection(null);
        solarPanel.setRow(1);
        solarPanel.setColumn(1);
        solarPanel.setMaterial(Material.POLY_SI);

        // Act
        SolarPanelResult result = service.create(solarPanel);

        // Assert
        assertFalse(result.isSuccess());
        assertEquals(1, result.getErrorMessages().size());
        assertTrue(result.getErrorMessages().get(0).contains("`section`"));
    }

    @Test
    void shouldNotCreateEmptySection() throws DataAccessException {
        // Arrange
        SolarPanel solarPanel = new SolarPanel();
        solarPanel.setSection("");
        solarPanel.setRow(1);
        solarPanel.setColumn(1);
        solarPanel.setMaterial(Material.POLY_SI);

        // Act
        SolarPanelResult result = service.create(solarPanel);

        // Assert
        assertFalse(result.isSuccess());
        assertEquals(1, result.getErrorMessages().size());
        assertTrue(result.getErrorMessages().get(0).contains("`section`"));
    }

    @Test
    void shouldNotCreateNullMaterial() throws DataAccessException {
        // Arrange
        SolarPanel solarPanel = new SolarPanel();
        solarPanel.setSection("Section One");
        solarPanel.setRow(1);
        solarPanel.setColumn(1);
        solarPanel.setYearInstalled(2000);

        // Act
        SolarPanelResult result = service.create(solarPanel);

        // Assert
        assertFalse(result.isSuccess());
        assertEquals(1, result.getErrorMessages().size());
        assertTrue(result.getErrorMessages().get(0).contains("`material`"));
    }

    @Test
    void shouldNotCreateNonPositiveRow() throws DataAccessException {
        // Arrange
        SolarPanel solarPanel = new SolarPanel();
        solarPanel.setSection("Section One");
        solarPanel.setRow(0);
        solarPanel.setColumn(1);
        solarPanel.setMaterial(Material.POLY_SI);

        // Act
        SolarPanelResult result = service.create(solarPanel);

        // Assert
        assertFalse(result.isSuccess());
        assertEquals(1, result.getErrorMessages().size());
        assertTrue(result.getErrorMessages().get(0).contains("`row`"));
    }

    @Test
    void shouldNotCreateGreaterThanMaxRow() throws DataAccessException {
        // Arrange
        SolarPanel solarPanel = new SolarPanel();
        solarPanel.setSection("Section One");
        solarPanel.setRow(SolarPanelService.MAX_ROW_COLUMN + 1);
        solarPanel.setColumn(1);
        solarPanel.setMaterial(Material.POLY_SI);

        // Act
        SolarPanelResult result = service.create(solarPanel);

        // Assert
        assertFalse(result.isSuccess());
        assertEquals(1, result.getErrorMessages().size());
        assertTrue(result.getErrorMessages().get(0).contains("`row`"));
    }

    @Test
    void shouldNotCreateNonPositiveColumn() throws DataAccessException {
        // Arrange
        SolarPanel solarPanel = new SolarPanel();
        solarPanel.setSection("Section One");
        solarPanel.setRow(1);
        solarPanel.setColumn(0);
        solarPanel.setMaterial(Material.POLY_SI);

        // Act
        SolarPanelResult result = service.create(solarPanel);

        // Assert
        assertFalse(result.isSuccess());
        assertEquals(1, result.getErrorMessages().size());
        assertTrue(result.getErrorMessages().get(0).contains("`column`"));
    }

    @Test
    void shouldNotCreateGreaterThanMaxColumn() throws DataAccessException {
        // Arrange
        SolarPanel solarPanel = new SolarPanel();
        solarPanel.setSection("Section One");
        solarPanel.setRow(1);
        solarPanel.setColumn(SolarPanelService.MAX_ROW_COLUMN + 1);
        solarPanel.setMaterial(Material.POLY_SI);

        // Act
        SolarPanelResult result = service.create(solarPanel);

        // Assert
        assertFalse(result.isSuccess());
        assertEquals(1, result.getErrorMessages().size());
        assertTrue(result.getErrorMessages().get(0).contains("`column`"));
    }

    @Test
    void shouldNotCreateYearInstalledInTheFuture() throws DataAccessException {
        // Arrange
        SolarPanel solarPanel = new SolarPanel();
        solarPanel.setSection("Section One");
        solarPanel.setRow(1);
        solarPanel.setColumn(1);
        solarPanel.setYearInstalled(Year.now().plusYears(1).getValue());
        solarPanel.setMaterial(Material.POLY_SI);

        // Act
        SolarPanelResult result = service.create(solarPanel);

        // Assert
        assertFalse(result.isSuccess());
        assertEquals(1, result.getErrorMessages().size());
        assertTrue(result.getErrorMessages().get(0).contains("`yearInstalled`"));
    }

    @Test
    void shouldNotCreateNonUniqueSectionRowColumn() throws DataAccessException {
        SolarPanel solarPanel = new SolarPanel();
        solarPanel.setId(1);
        solarPanel.setSection("Section One");
        solarPanel.setRow(1);
        solarPanel.setColumn(1);
        solarPanel.setYearInstalled(2000);
        solarPanel.setMaterial(Material.POLY_SI);
        solarPanel.setTracking(false);
        when(repository.findAll()).thenReturn(List.of(new SolarPanel(1,"Section One",1,1,2000, Material.POLY_SI,false)));

        SolarPanelResult result = service.create(solarPanel);

        assertFalse(result.isSuccess());
//        assertEquals(1, result.getErrorMessages().size());
//        assertTrue(result.getErrorMessages().get(0).contains("must be unique"));
    }

    @Test
    void shouldNotCreatePositiveId() throws DataAccessException {
        SolarPanel solarPanel = new SolarPanel(1, "Section One", 1, 1, 2020,
                Material.POLY_SI, true);

        SolarPanelResult result = service.create(solarPanel);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getErrorMessages().size());
        assertTrue(result.getErrorMessages().get(0).contains("`id`"));
    }

    @Test
    void shouldCreate() throws DataAccessException {
        SolarPanel solarPanel = new SolarPanel();
        solarPanel.setSection("Section One");
        solarPanel.setRow(1);
        solarPanel.setColumn(3);
        solarPanel.setYearInstalled(2000);
        solarPanel.setMaterial(Material.POLY_SI);

        SolarPanelResult result = service.create(solarPanel);

        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotUpdateEmptySection() throws DataAccessException {// TODO: complete

        SolarPanel updateP = new SolarPanel(2, "", 1, 2, 2003, Material.MONO_SI, true);
        SolarPanelResult result = service.update(updateP);

        assertFalse(result.isSuccess());
        assertEquals("SolarPanel `section` is required.", result.getErrorMessages().get(0));
    }

    @Test
    void shouldNotUpdateNonPositiveId() throws DataAccessException {// TODO: complete

        SolarPanel updateP = new SolarPanel(-4, "Section Three", 6, 1, 2003, Material.MONO_SI, true);
        SolarPanelResult result = service.update(updateP);

        assertFalse(result.isSuccess());
        assertEquals("Solar Panel Not Found", result.getErrorMessages().get(0));
    }

    @Test
    void shouldNotUpdateNonExistentSolarPanel() throws DataAccessException {// TODO: complete

        SolarPanel updateP = new SolarPanel(4, "Section four", 8, 7, 2003, Material.MONO_SI, true);
        SolarPanelResult result = service.update(updateP);

        assertFalse(result.isSuccess());
        assertEquals("Solar Panel Not Found", result.getErrorMessages().get(0));

    }

    @Test
    void shouldUpdate() throws DataAccessException { // TODO: complete
        SolarPanel updateP = new SolarPanel(2, "Section One", 1, 2, 2003, Material.MONO_SI, true);
        when(repository.update(updateP)).thenReturn(true);
        SolarPanelResult result = service.update(updateP);

        assertTrue(result.isSuccess());
        assertEquals(updateP, result.getSolarPanel());

    }

    @Test
    void shouldNotDeleteNonExistentSolarPanel() throws DataAccessException { // TODO: complete
        SolarPanel solarPanel = service.findByKey("Hill", 1,1);
        when(repository.findByKey("Hill", 1,1)).thenReturn(new SolarPanel());
        SolarPanelResult result = service.delete(solarPanel);
        assertFalse(result.isSuccess());
//        assertThrows(NullPointerException.class, () -> {
//            SolarPanelResult result = service.delete(solarPanel);
//        });
    }


    @Test
    void shouldDelete() throws DataAccessException { // TODO: complete
        SolarPanel solarPanel = service.findByKey("Hill", 1,1);
        when(repository.findByKey("Hill", 1,1)).thenReturn(new SolarPanel());
        SolarPanelResult result = service.delete(solarPanel);
        assertFalse(result.isSuccess());
    }
}
