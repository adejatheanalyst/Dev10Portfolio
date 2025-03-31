package learn.foraging.data;

import learn.foraging.DataHelper;
import learn.foraging.models.Forager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

import static learn.foraging.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ForagerJdbcClientRepositoryTest {
    JdbcClient jdbcClient = DataHelper.getJdbcClient();
    ForagerJdbcClientRepository repository = new ForagerJdbcClientRepository(jdbcClient);

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
}