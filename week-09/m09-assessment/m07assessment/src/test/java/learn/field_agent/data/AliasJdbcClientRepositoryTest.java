package learn.field_agent.data;
import learn.field_agent.models.Agent;
import learn.field_agent.models.Alias;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AliasJdbcClientRepositoryTest {
    Agent existingAgent = new Agent(1, "'Hazel'", "C", "Sauven", LocalDate.of(1954,9, 16), 76);
    Alias NonExisitingAlias = new Alias(2, "test", "test-persona", existingAgent);
    Alias NonExisitingAliasTooAdd = new Alias(0, "test", "test-persona", existingAgent);

    @Autowired
    AliasJdbcClientRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }


    @Test
    void findAll() {
        List<Alias> actual = repository.findAll();
        assertEquals(1, actual.size());

    }
    @Test
    void findById() {
        Alias actual = repository.findById(1);
        assertEquals(1, actual.getAliasId());
    }
    @Test
    void findByIdFail() {
        Alias actual = repository.findById(5);
        assertNull(actual);
    }

    @Test
    void add() {
       Alias newAlias = NonExisitingAlias;
       Alias toAdd = NonExisitingAliasTooAdd;
       int countBeforeAdd = repository.findAll().size();
       Alias actual = repository.add(toAdd);
       assertEquals(newAlias, actual);
       assertEquals(countBeforeAdd + 1, repository.findAll().size());
    }

    @Test
    void update() {
        Alias newUpdate = new Alias(1, "TEST", "TEST-PERSONA", existingAgent);
        assertTrue(repository.update(newUpdate));
        assertEquals(1, newUpdate.getAliasId());
    }
    @Test
    void shouldNotUpdateIfNotFound() {
        Alias newUpdate = new Alias(9, "TEST", "TEST-PERSONA", existingAgent);
        assertFalse(repository.update(newUpdate));
    }

    @Test
    void deleteById() {
        int countBeforeDelete = repository.findAll().size();
        boolean actual = repository.deleteById(1);
        assertTrue(actual);
        assertEquals(countBeforeDelete - 1, repository.findAll().size());

    }
    @Test
    void ShouldNotDeleteByIdIfNotFound() {
        int countBeforeDelete = repository.findAll().size();
        boolean actual = repository.deleteById(8);
        assertFalse(actual);
        assertEquals(countBeforeDelete, repository.findAll().size());

    }
}