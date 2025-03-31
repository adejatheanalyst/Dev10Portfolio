package learn.field_agent.data;

import learn.field_agent.domain.Result;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SecurityClearanceJdbcClientRepositoryTest {

    @Autowired
    SecurityClearanceJdbcClientRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindById() {
        SecurityClearance secret = new SecurityClearance(1, "Secret");
        SecurityClearance topSecret = new SecurityClearance(2, "Top Secret");

        SecurityClearance actual = repository.findById(1);
        assertEquals(secret, actual);

        actual = repository.findById(2);
        assertEquals(topSecret, actual);

        actual = repository.findById(3);
        assertEquals(null, actual);
    }

    @Test
    void shouldFindAll() {
        List<SecurityClearance> expected = List.of(
                new SecurityClearance(1, "Secret"),
                new SecurityClearance(2, "Top Secret")
        );

        List<SecurityClearance> actual = repository.findAll();

        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd() {
        SecurityClearance expected = new SecurityClearance(3, "For British Eyes Only");
        SecurityClearance toAdd = new SecurityClearance(0, "For British Eyes Only");
        int countBeforeAdd = repository.findAll().size();

        SecurityClearance actual = repository.add(toAdd);

        assertEquals(expected, actual);
        assertEquals(countBeforeAdd + 1, repository.findAll().size());
    }

    @Test
    void shouldUpdate() {
        SecurityClearance toUpdate = new SecurityClearance(1, "Super Secret");

        boolean actual = repository.update(toUpdate);

        assertTrue(actual);
        assertEquals(toUpdate, repository.findById(toUpdate.getSecurityClearanceId()));
    }

    @Test
    void updateFailsIfNotFound() {
        SecurityClearance toUpdate = new SecurityClearance(999, "Super Secret");

        boolean actual = repository.update(toUpdate);

        assertFalse(actual);
    }

    @Test
    void shouldDelete() {
        int countBeforeDelete = repository.findAll().size();

        boolean actual = repository.deleteById(2);

        assertTrue(actual);
        assertEquals(countBeforeDelete - 1, repository.findAll().size());
    }

    @Test
    void shouldNotDeleteIfNotFound() {
        int countBeforeDelete = repository.findAll().size();

        boolean actual = repository.deleteById(999);

        assertFalse(actual);
        assertEquals(countBeforeDelete, repository.findAll().size());
    }

    @Test
    void findActive() {
        List<SecurityClearance> actual = repository.findActive();

        assertTrue(actual.stream().anyMatch(sc -> sc.getSecurityClearanceId() == 1));
        assertFalse(actual.stream().anyMatch(sc -> sc.getSecurityClearanceId() == 2));
    }
}