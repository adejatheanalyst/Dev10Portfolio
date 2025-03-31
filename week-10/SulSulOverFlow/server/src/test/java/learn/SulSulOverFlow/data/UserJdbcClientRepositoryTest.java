package learn.SulSulOverFlow.data;

import learn.SulSulOverFlow.TestHelper;
import learn.SulSulOverFlow.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserJdbcClientRepositoryTest {
    @Autowired
    JdbcClient client;
    @Autowired
    UserJdbcClientRepository repository;

    @BeforeEach
        void setup() {
            client.sql("call set_known_good_state();").update();
        }

    @Nested
    class CreateTests {
        @Test
        void create() {
            User user = TestHelper.makeUser();
            user.setUsername("testUsername1");
            User actual = repository.create(user);
            List<User> users = repository.findAll();

            assertEquals(4, actual.getUserId());
            assertEquals(4, users.size());

        }
        @Test
        void createFailDupUser() {
            User user = TestHelper.makeUser();
            user.setUsername("alice");
            user.setUserId(0);
            assertThrows(DuplicateKeyException.class, () -> {
                repository.create(user);
            });
        }
    }


    @Nested
    class findByUsernameTests {
        @Test
        void findByUsername() {
            User actual = repository.findByUsername("alice");
            assertNotNull(actual);
            assertEquals("alice", actual.getUsername());
            assertEquals(1, actual.getUserId());
        }
        @Test
        void findByUsernameFail() {
            User actual = repository.findByUsername("notAUser");
            assertNull(actual);
        }
    }


    @Nested
    class findByIdTests {
        @Test
        void findById() {
            User actual = repository.findById(1);
            assertNotNull(actual);
            assertEquals("alice", actual.getUsername());
        }
        @Test
        void findByIdFail() {
            User actual = repository.findById(1000);
            assertNull(actual);
        }
    }
}