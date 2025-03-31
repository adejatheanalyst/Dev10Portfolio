package learn.unexplained.domain;

import learn.unexplained.data.DataAccessException;
import learn.unexplained.data.EncounterRepositoryDouble;
import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EncounterServiceTest {

//    EncounterService service = new EncounterService(new EncounterRepositoryDouble());

    EncounterService service;

    @BeforeEach
    void setup() {
        EncounterRepositoryDouble repository = new EncounterRepositoryDouble();
        service = new EncounterService(repository);
    }

    @Test
    void shouldNotAddNull() throws DataAccessException {
        EncounterResult expected = makeResult("Encounter cannot be null");
        EncounterResult actual = service.add(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddEmptyWhen() throws DataAccessException {
        Encounter encounter = new Encounter(0, EncounterType.CREATURE, " ", "test desc", 1);
        EncounterResult expected = makeResult("when is required");
        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }


    @Test
    void shouldNotAddEmptyDescription() throws DataAccessException {
        Encounter encounter = new Encounter(0, EncounterType.CREATURE, "2/2/2019", "  ", 1);
        EncounterResult expected = makeResult("description is required");
        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullDescription() throws DataAccessException {
        Encounter encounter = new Encounter(0, EncounterType.CREATURE, "2/2/2019", null, 1);
        EncounterResult expected = makeResult("description is required");
        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddZeroOccurrences() throws DataAccessException {
        Encounter encounter = new Encounter(0, EncounterType.CREATURE, "2/2/2019", "test description", 0);
        EncounterResult expected = makeResult("occurrences must be greater than 0");
        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddDuplicate() throws DataAccessException {
        Encounter encounter = new Encounter(0, EncounterType.CREATURE, "1/1/2015", "test description", 1);
        EncounterResult expected = makeResult("duplicate encounter is not allowed");
        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd() throws DataAccessException {
        Encounter encounter = new Encounter(0, EncounterType.CREATURE, "2/2/2019", "test description", 1);
        EncounterResult expected = new EncounterResult();


        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }

    private EncounterResult makeResult(String message) {
        EncounterResult result = new EncounterResult();
        result.addErrorMessage(message);
        return result;
    }

    @Test
    void shouldUpdate() throws DataAccessException {
        Encounter encounter = service.findAll().get(0);
        encounter.setDescription("updated content");

        EncounterResult result = service.update(encounter);

        assertTrue(result.isSuccess());
    }


    @Test
    void shouldNotUpdateEmptyContent() throws DataAccessException {
        Encounter encounter = service.findAll().get(0);
        encounter.setDescription("\t\n ");

        EncounterResult result = service.update(encounter);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertFalse(result.getMessages().get(0).contains("`content`"));
    }

    //
    @Test
    void findByType() throws DataAccessException{
        EncounterResult result = service.findByType(EncounterType.UFO);

        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());
        assertEquals(1 , result.getPayload());


    }
}
