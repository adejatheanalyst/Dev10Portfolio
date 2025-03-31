package learn.Data;

import learn.DataHelper;
import learn.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserJdbcClientRepositoryTest {
    JdbcClient jdbcClient = DataHelper.getJdbcClient();
    UserJdbcClientRepository repository = new UserJdbcClientRepository(jdbcClient);

    @BeforeEach
    void setup() {
        jdbcClient.sql("call set_known_good_state();").update();
    }
    @Test
    void findById() throws DataAccessException {
        User expected = new User();
        User actual = repository.findById(1);
        assertEquals("Llywellyn", actual.getFirst_name());
        assertEquals(1, actual.getUser_id());
    }

    @Test
    void findByIdFail() throws DataAccessException {
        User actual = repository.findById(6);
        assertEquals(null, actual);
    }

    @Test
    void findAllUsers() throws DataAccessException {
        List<User> result = repository.findAllUsers();
        assertEquals(5, result.size());
    }

    @Test
    void findByEmail() throws DataAccessException {
        User actual = repository.findByEmail("thonnan2@berkeley.edu");
        assertEquals(3, actual.getUser_id());
    }
    @Test
    void findByEmailFail() throws DataAccessException {
        User actual = repository.findByEmail("thonnan3@berkeley.edu");
        assertEquals(null, actual);
    }
}