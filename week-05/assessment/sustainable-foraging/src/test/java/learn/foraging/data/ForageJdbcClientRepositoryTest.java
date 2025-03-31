package learn.foraging.data;

import learn.foraging.DataHelper;
import learn.foraging.models.Forage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.time.LocalDate;
import java.util.List;

import static learn.foraging.TestData.*;
import static org.junit.jupiter.api.Assertions.*;

class ForageJdbcClientRepositoryTest {

    static final int NEXT_ID = 4;
    JdbcClient jdbcClient = DataHelper.getJdbcClient();
    ForageJdbcClientRepository repository = new ForageJdbcClientRepository(jdbcClient);


    @BeforeEach
    void setup() {
        jdbcClient.sql("call set_known_good_state();").update();
    }

    @Test
    void shouldFindById1() {
        Forage expected = new Forage(1, JAN_01_2023, FORAGER_ONE, EDIBLE, ONE_KILO);
        Forage actual = repository.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindById15() {
        Forage actual = repository.findById(15);
        assertNull(actual);
    }

    @Test
    void shouldFindByDateJan1_2023() {
        // Arrange
        List<Forage> expected = List.of(
                new Forage(1, JAN_01_2023, FORAGER_ONE, EDIBLE, ONE_KILO),
                new Forage(3, JAN_01_2023, FORAGER_ONE, INEDIBLE, ONE_KILO),
                new Forage(2, JAN_01_2023, FORAGER_TWO, MEDICINAL, ONE_KILO)
        );

        List<Forage> actual = repository.findByDate(JAN_01_2023);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindByDateJul2_2023() {
        List<Forage> expected = List.of();
        List<Forage> actual = repository.findByDate(LocalDate.of(2023, 7, 2));
        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd() {
        // Arrange
        Forage arg = new Forage(0, JAN_01_2023, FORAGER_TWO, EDIBLE, ONE_KILO);
        Forage expected = new Forage(NEXT_ID, JAN_01_2023, FORAGER_TWO, EDIBLE, ONE_KILO);

        Forage actual = repository.add(arg);
        assertEquals(expected, actual);

        actual = repository.findById(NEXT_ID);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddUnique() {
        Forage arg = new Forage(0, JAN_01_2023, FORAGER_ONE, EDIBLE, ONE_KILO);
        assertThrows(DuplicateKeyException.class, () -> {
            repository.add(arg);
        });
    }
}