package learn.unexplained.data;

import learn.unexplained.domain.EncounterResult;
import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EncounterFileRepositoryTest {

    static final String TEST_PATH = "./data/encounters-test.csv";
    static final String SEED_PATH = "./data/encounters-test.csv";
    final Encounter[] testEncounters = new Encounter[]{
            new Encounter(1, EncounterType.UFO, "2020-01-01", "short test #1", 1),
            new Encounter(2, EncounterType.CREATURE, "2020-02-01", "short test #2", 1),
            new Encounter(3, EncounterType.SOUND, "2020-03-01", "short test #3", 1)
    };

    EncounterRepository repository = new EncounterFileRepository(TEST_PATH);

    @BeforeEach
    void setup() throws DataAccessException, IOException {
        Path seedPath = Paths.get(SEED_PATH);
        Path testPath = Paths.get(TEST_PATH);
        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
        for (Encounter e : repository.findAll()) {
            repository.deleteById(e.getEncounterId());
        }
        for (Encounter e : testEncounters) {
            repository.add(e);
        }
    }

    @Test
    void shouldFindAll() throws DataAccessException {
        List<Encounter> encounters = repository.findAll();
        Encounter[] actual = encounters.toArray(new Encounter[encounters.size()]);
        assertArrayEquals(testEncounters, actual);
    }

    @Test
    void shouldAdd() throws DataAccessException {
        Encounter encounter = new Encounter();
        encounter.setType(EncounterType.UFO);
        encounter.setWhen("Jan 15, 2005");
        encounter.setDescription("moving pinpoint of light." +
                "seemed to move with me along the highway. " +
                "then suddenly reversed direction without slowing down. it just reversed.");
        encounter.setOccurrences(1);

        Encounter actual = repository.add(encounter);

        assertNotNull(actual);
        assertEquals(4, actual.getEncounterId());
    }

    @Test
    void findByType() throws DataAccessException {
        List<Encounter> result = repository.findByType(EncounterType.UFO);
        assertEquals(1, result.size());
    }
    @Test
    void update() throws DataAccessException {
        Encounter encounterToUpdate = new Encounter(1,EncounterType.UFO,"07/02/1942","Lights in a line, may be planets may be sumn else",6);

        boolean result = repository.update(encounterToUpdate);

        assertTrue(result);
        Encounter encounterAfterUpdate = null;
        for (Encounter encounter : repository.findAll()) {
            if (encounter.getEncounterId() == 1) {
                encounterAfterUpdate = encounter;
            }
        }
        assertEquals("07/02/1942", encounterAfterUpdate.getWhen());
        assertEquals("Lights in a line, may be planets may be sumn else", encounterAfterUpdate.getDescription());
        assertEquals(6, encounterAfterUpdate.getOccurrences());
    }
    @Test
    void updateFailsToFind() throws DataAccessException {
        Encounter encounterToUpdate = new Encounter(4,EncounterType.UFO,"600","Bobby",6);

        boolean result = repository.update(encounterToUpdate);

        assertFalse(result);
    }





    @Test
    void deleteById() throws DataAccessException {
        int count = repository.findAll().size();
        assertTrue(repository.deleteById(3));
        assertFalse(repository.deleteById(5));
        assertEquals(count - 1, repository.findAll().size());
    }

}