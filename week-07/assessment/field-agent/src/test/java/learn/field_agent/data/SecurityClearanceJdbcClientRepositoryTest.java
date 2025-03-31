package learn.field_agent.data;

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

        actual = repository.findById(66);
        assertEquals(null, actual);
    }
    @Test
    void findAll(){
        List<SecurityClearance> expected = List.of(new SecurityClearance(1, "Secret"),
                new SecurityClearance(2, "Top Secret"));

        List<SecurityClearance> actual = repository.findAll();
        assertEquals(expected, actual);
    }
    @Test
    void shouldAdd() {
        SecurityClearance newClearance = new SecurityClearance(3, "Top level - High Security");
//        SecurityClearance toADD = new SecurityClearance(0, "Top level - High Security");

        SecurityClearance actual = repository.add(newClearance);
        assertEquals(newClearance, actual);
        assertEquals(3, actual.getSecurityClearanceId());
    }

    @Test
    void ShouldUpdate(){
        SecurityClearance securityClearance = new SecurityClearance(1, "Super Secret");

        boolean actual = repository.update(securityClearance);

        assertTrue(actual);
        assertEquals(securityClearance, repository.findById(securityClearance.getSecurityClearanceId()));
    }
    @Test
    void ShouldNotUpdateNotFound() {
        SecurityClearance securityClearance = new SecurityClearance(999, "Super Secret");

        boolean actual = repository.update(securityClearance);

        assertFalse(actual);
    }

    @Test
    void ShouldDelete(){
        int countBeforeDelete = repository.findAll().size();
        boolean actual = repository.deleteById(2);
        assertTrue(actual);
        assertEquals(countBeforeDelete - 1, repository.findAll().size());

    }
    @Test
    void ShouldNotDeleteNull(){
        int countBeforeDelete = repository.findAll().size();
        boolean actual = repository.deleteById(9999);
        assertFalse(actual);
        assertEquals(countBeforeDelete - 1, repository.findAll().size());

    }


    @Test
    void findActive(){
        List<SecurityClearance> actual = repository.findActive();
        assertTrue(actual.stream().anyMatch(securityClearance -> securityClearance.getSecurityClearanceId() == 1));
        assertFalse(actual.stream().anyMatch(securityClearance -> securityClearance.getSecurityClearanceId() == 2));
    }
}