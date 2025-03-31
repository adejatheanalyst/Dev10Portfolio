package learn.foraging.data;

import learn.foraging.DataHelper;
import learn.foraging.models.Forager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

import static learn.foraging.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ForagerJdbcClientRepositoryTest {
    @Autowired
            JdbcClient jdbcClient;
//    JdbcClient jdbcClient = DataHelper.getJdbcClient();
    @Autowired
        ForagerJdbcClientRepository repository;
//    ForagerJdbcClientRepository repository = new ForagerJdbcClientRepository(jdbcClient);

    @BeforeEach
    void setup() {
        jdbcClient.sql("call set_known_good_state();").update();
    }

    @Test
    void shouldFindByLastNameLa() {
        List<Forager> expected = List.of(
                FORAGER_ONE,
                FORAGER_TWO
        );
        List<Forager> actual = repository.findByLastName("La");
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindByLastNameX() {
        List<Forager> expected = List.of();
        List<Forager> actual = repository.findByLastName("X");
        assertEquals(expected, actual);
    }

    @Test
    void create() {
        Forager forager = new Forager(0, "Test", "Testerson", "TT");
        Forager actual = repository.create(forager);
        assertEquals(3, actual.getId());
    }

    @Test
    void deleteById() {
        Forager forager = new Forager(1, "Test", "Testerson", "TT");
        boolean actual = repository.deleteById(1);
        assertEquals(true, actual);
    }

    @Test
    void findById() {
        Forager expected = FORAGER_ONE;
        Forager actual = repository.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void findByLastName() {
        List<Forager> expected = List.of(
                FORAGER_ONE,
                FORAGER_TWO
        );
        List<Forager> actual = repository.findByLastName("La");
        assertEquals(expected, actual);
    }

    @Test
    void findByState() {
        List<Forager> expected = List.of(
                FORAGER_ONE,
                FORAGER_TWO
        );
        List<Forager> actual = repository.findByState("OR");
        assertEquals(List.of(FORAGER_ONE), actual);
    }
}