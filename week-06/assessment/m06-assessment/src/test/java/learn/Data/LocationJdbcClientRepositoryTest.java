package learn.Data;

import learn.DataHelper;
import learn.Model.Location;
import learn.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocationJdbcClientRepositoryTest {
    JdbcClient jdbcClient = DataHelper.getJdbcClient();
    LocationJdbcClientRepository repository = new LocationJdbcClientRepository(jdbcClient);
    UserJdbcClientRepository userRepo = new UserJdbcClientRepository(jdbcClient);
private final String existingHostEmail = "alardeux4@nhs.uk";

    @BeforeEach
    void setup() {
        jdbcClient.sql("call set_known_good_state();").update();
    }
    @Test
    void findById() throws DataAccessException {
        Location actual = repository.findById(3);
        assertEquals("Atlanta", actual.getCity());
        assertEquals(3, actual.getLocation_id());
    }
    @Test
    void doesNotFindId() throws DataAccessException {
        Location actual = repository.findById(6);
        assertNull(actual);
    }

    @Test
    void findByState() throws DataAccessException {
        List<Location> actual = repository.findByState(9);
        assertEquals(2, actual.size());
    }
    @Test
    void doesNotFindByState() throws DataAccessException {
        List<Location> actual = repository.findByState(3);
        assertEquals(0, actual.size());
    }

    @Test
    void findAll() {
        List<Location> actual = repository.findAll();
        assertEquals(5, actual.size());
    }

    @Test
    void findByCity() {
        List<Location> actual = repository.findByCity("Atlanta");
        assertEquals(1, actual.size());
        List<Location> actual2 = repository.findByCity("Santa Cruz");
        assertEquals(1, actual2.size());
    }
    @Test
    void doesNotFindByCity() {
        List<Location> actual = repository.findByCity("Stafford");
        assertEquals(0, actual.size());
        List<Location> actual2 = repository.findByCity("New York");
        assertEquals(0, actual2.size());
    }
    @Test
    void findByUser() {
        User user = userRepo.findByEmail(existingHostEmail);
        assertNotNull(user);

//        user.setUser_id(1);
        List<Location> actual = repository.findByUserId(user);
        assertNotNull(actual);
        assertEquals(1, actual.size());
    }

    @Test
    void findByUserFail() {
        User user = userRepo.findByEmail("hgfhgk@jhgffg.com");
        assertNull(user);

//        user.setUser_id(1);
        List<Location> actual = repository.findByUserId(user);
        assertNull(actual);
        assertEquals(0, actual.size());
    }
    @Test
    void findStandardRate(){
        Location actual = repository.findStandardRate(1);
        assertEquals(new BigDecimal("110.00"), actual.getStandard_rate());

    }
    @Test
    void findWeekendRate(){
        Location actual = repository.findWeekendRate(1);
        assertEquals(new BigDecimal("127.00"), actual.getWeekend_rate());

    }

}