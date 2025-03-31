package learn.solarfarm.data;

import learn.solarfarm.models.Material;
import learn.solarfarm.models.SolarPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SolarPanelFileRepositoryTest {
    static final String SEED_FILE_PATH = "./data/solarfarm-seed.txt";
    static final String TEST_FILE_PATH = "./data/solarfarm-test.txt";

    SolarPanelFileRepository repository = new SolarPanelFileRepository(TEST_FILE_PATH);

    /**
     * Runs before each test.
     * This is an (A)rrange step.
     * Takes an existing seed data file and copies its contents
     * into a test file. The repository then operates on the
     * test file.
     *
     * @throws IOException
     */
    @BeforeEach
    void setupTest() throws IOException {
        Path seedPath = Paths.get(SEED_FILE_PATH);
        Path testPath = Paths.get(TEST_FILE_PATH);

        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void findBySection() throws DataAccessException {
        // Arrange happens in setupTest().

        // Act: test that the "The Ridge" section returns two solar panels
        List<SolarPanel> actual = repository.findBySection("The Ridge");
        // Assert
        assertEquals(2, actual.size());

        // Act: test that capitalization doesn't matter
        actual = repository.findBySection("THE RIDGE");
        // Assert
        assertEquals(2, actual.size());

        // Act: test that an empty list returned if a section doesn't have any solar panels
        actual = repository.findBySection("East Hill");
        // Assert
        assertEquals(0, actual.size());
    }

    @Test
    void findByKey() throws DataAccessException {
        // Arrange also happens in setupTest().
        // Arrange
        SolarPanel expected = new SolarPanel(1, "The Ridge", 1, 1, 2020, Material.POLY_SI, true);

        // Act: test that an existing solar panel can be retrieved
        SolarPanel actual = repository.findByKey("The Ridge", 1, 1);
        // Assert
        assertEquals(expected, actual);

        // Act: test that capitalization doesn't matter
        actual = repository.findByKey("THE RIDGE", 1, 1);
        // Assert
        assertEquals(expected, actual);

        // Act: test that attempting to retrieve a non-existent solar panel returns null
        actual = repository.findByKey("East Hill", 5, 6);
        // Assert
        assertNull(actual);
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

        SolarPanel expected = new SolarPanel(7, "The Ridge", 1, 10, 2020, Material.A_SI, true);

        // Act
        SolarPanel actual = repository.create(solarPanel);
        // Assert
        assertEquals(7, actual.getId());

        // Act
        List<SolarPanel> all = repository.findBySection("The Ridge");
        // Assert
        assertEquals(3, all.size());

        // the newly-added solar panel
        actual = all.get(2);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void update() throws DataAccessException {// TODO: complete
        SolarPanel solarPanelToUpdate = new SolarPanel(1, "Flats", 2, 6, 1997, Material.MONO_SI, true);
        boolean result = repository.update(solarPanelToUpdate);

        assertTrue(result);
        SolarPanel afterUpdate = null;
        for (SolarPanel solarPanel : repository.findAll()) {
           if (solarPanel.getKey().equals(solarPanelToUpdate.getKey()))
            {
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
        SolarPanel ToUpdate = new SolarPanel(7,"test",2, 5, 2007, Material.POLY_SI,true);

        boolean result = repository.update(ToUpdate);

        assertFalse(result);
    }





    @Test
    void delete() throws DataAccessException {  // TODO: complete
        SolarPanel solarPanel = repository.findByKey("Hill", 1,1);
        boolean result = repository.delete(solarPanel);
        assertTrue(result);
        assertEquals(5, repository.findAll().size());

    }

    @Test
    void deleteFail() throws DataAccessException {  // TODO: complete
        SolarPanel solarPanel = repository.findByKey("Hill", 10,1);
        boolean result = repository.delete(solarPanel);
        assertFalse(result);
        assertEquals(6, repository.findAll().size());

    }
}
