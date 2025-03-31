package learn.data;

import learn.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserClientRepositoryTest {
    @Autowired
    JdbcClient jdbcClient;

    @Autowired
    UserClientRepository repository;

    @BeforeEach
    void setup() {
        jdbcClient.sql("call set_known_good_state();").update();
    }

    @Test
    void findByUserId() {
        User actual = repository.findByUserId(1);
        assertNotNull(actual);
        assertEquals(1, actual.getUserId());
    }

    @Test
    void deleteById() {
        assertTrue(repository.deleteById(1));
        User actual = repository.findByUserId(1);
        assertNull(actual);
    }

    @Nested class updateTests {
    @Test
    void update() {
        User user = new User(1, "testUsername", "testEmail", "testPassword", LocalDate.now());
        user.setUsername("testUsernameTest");
        assertTrue(repository.update(user));
        User actual = repository.findByUsername("testUsernameTest");
        assertEquals(user.getUsername(), actual.getUsername());
    }
    @Test
    void updateFailNoId() {
        User user = new User(1, "testUsername", "testEmail", "testPassword", LocalDate.now());
        user.setUserId(0);
        user.setUsername("testUsernameTest");
        assertFalse(repository.update(user));
    }

}
    @Nested class findByIdTests {
    @Test
    void findByUsernamePass() {
        User actual = repository.findByUsername("testUsername");
        assertNotNull(actual);
        assertEquals(1, actual.getUserId());
    }

    @Test
    void findByUsernameFail() {
        User actual = repository.findByUsername("test");
        assertNull(actual);
    }
}

    @Nested
    class createTests {
        @Test
        void create() {
            User user = new User(1, "testUsername100", "testEmail400@email.com", "testPassword4", LocalDate.now());
            User actual = repository.create(user);
            assertEquals(5, actual.getUserId());
        }
        @Test
        void createFailUsernameDup() {
            User user = new User(0, "testUsername2", "testEmail4@email.com", "testPassword4", LocalDate.now());
            assertThrows(DuplicateKeyException.class, () -> {
                repository.create(user);
            });
        }
        @Test
        void createFailEmailDup() {
            User user = new User(0, "testUsername4", "testemail2@test.com", "testPassword4", LocalDate.now());
            assertThrows(DuplicateKeyException.class, () -> {
                repository.create(user);
            });
        }
    }
}