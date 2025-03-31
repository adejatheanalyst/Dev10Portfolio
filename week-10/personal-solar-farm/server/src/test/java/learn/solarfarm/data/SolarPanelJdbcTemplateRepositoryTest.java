package learn.solarfarm.data;

import learn.solarfarm.models.SolarPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static learn.solarfarm.TestHelper.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SolarPanelJdbcTemplateRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SolarPanelJdbcTemplateRepository repository;

    @BeforeEach
    void setup() {
        jdbcTemplate.update("call set_known_good_state();");
    }

    @Test
    void shouldFindBySection_red() {
        var expected = List.of(makePanel(1), makePanel(2), makePanel(3));
        var actual = repository.findBySection("red");
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindBySection_blue() {
        var expected = List.of(makePanel(4), makePanel(5));
        var actual = repository.findBySection("blue");
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindBySection_yellow() {
        var expected = List.of();
        var actual = repository.findBySection("yellow");
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByKey_red_1_1() {
        SolarPanel expected = makePanel(1);
        SolarPanel actual = repository.findByKey("red", 1, 1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindByKey_yellow_1_1() {
        SolarPanel actual = repository.findByKey("yellow", 1, 1);
        assertNull(actual);
    }

    @Test
    void shouldFindById_2() {
        SolarPanel expected = makePanel(2);
        SolarPanel actual = repository.findById(2);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindById_25() {
        SolarPanel actual = repository.findById(MISSING_ID);
        assertNull(actual);
    }
    @Test
    void shouldFindByUserId1() {
        List<SolarPanel> actual = repository.findByUserId(1);
        assertEquals(5, actual.size());
    }
    @Test
    void shouldNotFindByUserId999() {
        List<SolarPanel> actual = repository.findByUserId(999);
        assertEquals(0, actual.size());
    }

    @Test
    void shouldAdd() {
        SolarPanel arg = makePanel(NEXT_ID);
        arg.setId(0);
        SolarPanel expected = makePanel(NEXT_ID);
        SolarPanel actual = repository.create(arg);
        assertEquals(expected, actual);

        actual = repository.findById(NEXT_ID);
        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowDuplicateAdd() {
        SolarPanel arg = makePanel(4);
        arg.setId(0);
        assertThrows(DuplicateKeyException.class, () -> repository.create(arg));
    }

    @Test
    void shouldUpdate_4() {
        SolarPanel arg = makePanel(4);
        arg.setSection("yellow");
        boolean success = repository.update(arg);
        assertTrue(success);

        SolarPanel actual = repository.findById(4);
        assertEquals(arg, actual);
    }

    @Test
    void shouldNotUpdate_25() {
        SolarPanel arg = makePanel(MISSING_ID);
        boolean success = repository.update(arg);
        assertFalse(success);
    }

    @Test
    void shouldDeleteById_1() {
        boolean success = repository.deleteById(1);
        assertTrue(success);
        SolarPanel actual = repository.findById(1);
        assertNull(actual);
    }

    @Test
    void shouldNotDeleteById_25() {
        boolean success = repository.deleteById(MISSING_ID);
        assertFalse(success);
    }

}