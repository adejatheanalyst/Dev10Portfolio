package learn.safari.data;

import learn.safari.models.BugOrder;
import learn.safari.models.BugSighting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BugSightingJdbcTemplateRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    BugSightingJdbcTemplateRepository repository;

    static boolean hasRun = false;

    private static BugOrder BEETLE = new BugOrder(1, "Coleptera", "beetles");

    @BeforeEach
    void setup() {
        if (!hasRun) {
            jdbcTemplate.update("call set_known_good_state();");
            hasRun = true;
        }
    }

    @Test
    void shouldFindAll() {
        List<BugSighting> sightings = repository.findAll();

        assertTrue(sightings.size() >= 2);
        assertTrue(sightings.stream()
                .anyMatch(s -> s.getSightingId() == 1 && s.getDate().equals(LocalDate.parse("2020-08-12"))));
    }

    @Test
    void shouldFindLadybugSightingById() {
        BugSighting sighting = repository.findById(1);

        assertEquals(1, sighting.getSightingId());
        assertEquals("Ladybug", sighting.getBugType());
        assertEquals(LocalDate.parse("2020-08-12"), sighting.getDate());
        assertEquals("mature ladybug in the grass", sighting.getDescription());
        assertEquals(5.5, sighting.getInterest());
        assertEquals(BEETLE, sighting.getOrder());
        assertEquals("https://upload.wikimedia.org/wikipedia/commons/thumb/f/f2/Coccinella_magnifica01.jpg/640px-Coccinella_magnifica01.jpg", sighting.getImageUrl());
    }

    @Test
    void shouldAddBugSighting() {
        BugSighting sighting = new BugSighting(
                0,
                "Stick Bug",
                BEETLE,
                "hanging on the underside of a branch",
                LocalDate.parse("2022-09-23"),
                4.3,
                "https://fake.com/img.jpg");

        BugSighting expected = new BugSighting(
                4,
                "Stick Bug",
                BEETLE,
                "hanging on the underside of a branch",
                LocalDate.parse("2022-09-23"),
                4.3,
                "https://fake.com/img.jpg");

        BugSighting actual = repository.add(sighting);

        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdateCicadas() {
        BugSighting cicadas = repository.findById(2);

        assertEquals("the cicadas are singing in the trees", cicadas.getDescription());

        cicadas.setDescription("silly new test description");

        assertTrue(repository.update(cicadas));

        BugSighting updated = repository.findById(2);
        assertEquals("silly new test description", updated.getDescription());
    }

    @Test
    void shouldNotUpdateMissing() {
        BugSighting sighting = new BugSighting(
                99,
                "Fairy",
                BEETLE,
                "riding a praying mantis in a field of tall grass",
                LocalDate.parse("2022-09-23"),
                4.3,
                "https://fake.com/img.jpg");

        assertFalse(repository.update(sighting));
    }

    @Test
    void shouldDeleteDarklingBeetleById() {
        assertTrue(repository.deleteById(3));
        assertFalse(repository.deleteById(3));
    }
}