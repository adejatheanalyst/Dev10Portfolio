package learn.solarfarm.data;

import learn.solarfarm.TestHelper;
import learn.solarfarm.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserJdbcClientRepositoryTest {
    @Autowired
    JdbcClient jdbcClient;

    @Autowired
    UserJdbcClientRepository repository;

    @BeforeEach
    void setup() {
        jdbcClient.sql("call set_known_good_state();").update();
    }

    @Nested
    class createTests {
        @Test
        void create() {
            User user = TestHelper.makeUser();
            user.setUsername("testUsername1");
            User actual = repository.create(user);
            assertEquals(3, actual.getUserId());

        }

        @Test
        void createFailUsernameDup() {
            User user = TestHelper.makeUser();
            user.setUserId(0);
            assertThrows(DuplicateKeyException.class, () -> {
                repository.create(user);
            });
//            User actual = repository.create(user);
//
//            assertEquals(2, actual.getUserId());
        }
    }

    @Nested
    class findByUsernameTests {
        @Test
        void pass() {
            User actual = repository.findByUsername("testUsername");
            assertNotNull(actual);
            assertEquals(TestHelper.makeUser(), actual);


        }

        @Test
        void Fail() {
            User actual = repository.findByUsername("badUsername");
            assertNull(actual);

        }
    }
}

