package learn.fund.data;

import learn.fund.DataHelper;
import learn.fund.models.Backer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

import static learn.fund.TestHelper.makeBacker;
import static org.junit.jupiter.api.Assertions.*;

class BackerJdbcClientRepositoryTest {

    static final int MISSING_ID = 100;
    JdbcClient jdbcClient = DataHelper.getJdbcClient();
    BackerJdbcClientRepository repository = new BackerJdbcClientRepository(jdbcClient);

    @BeforeEach
    void setup() {
        jdbcClient.sql("call set_known_good_state();").update();
    }

    @Test
    void shouldFindById() {
        Backer expected = makeBacker(2);
        Backer actual = repository.findById(2);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindById() {
        Backer actual = repository.findById(MISSING_ID);
        assertNull(actual);
    }

    @Test
    void shouldFindNameContains_ack() {
        List<Backer> expected = List.of(
                makeBacker(1),
                makeBacker(2)
        );
        List<Backer> actual = repository.findNameContains("ack");
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindNameContains_2() {
        List<Backer> expected = List.of(
                makeBacker(2)
        );
        List<Backer> actual = repository.findNameContains("2");
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByEmailAddress() {
        Backer expected = makeBacker(1);
        Backer actual = repository.findByEmailAddress("backer1@example.com");
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindByEmailAddress() {
        assertNull(repository.findByEmailAddress("nope@example.com"));
    }

    @Test
    void shouldAdd() {
        Backer arg = makeBacker(3);
        arg.setBackerId(0);
        Backer expected = makeBacker(3);
        Backer actual = repository.add(arg);
        assertEquals(expected, actual);

        actual = repository.findById(3);
        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdate() {
        Backer expected = makeBacker(1);
        expected.setName("updated");
        expected.setEmailAddress("updated@example.com");
        assertTrue(repository.update(expected));

        Backer actual = repository.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdate() {
        Backer expected = makeBacker(MISSING_ID);
        assertFalse(repository.update(expected));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById(2));
    }

    @Test
    void shouldNotDelete() {
        assertFalse(repository.deleteById(MISSING_ID));
    }
}