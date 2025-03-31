package learn.safari.data;

import learn.safari.models.BugOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BugOrderJdbcTemplateRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    BugOrderJdbcTemplateRepository repository;

    static boolean hasRun = false;

    @BeforeEach
    void setup() {
        if (!hasRun) {
            jdbcTemplate.update("call set_known_good_state();");
            hasRun = true;
        }
    }

    @Test
    void shouldFindAll() {
        List<BugOrder> bugOrders = repository.findAll();

        assertTrue(bugOrders.size() >= 2);
        assertTrue(bugOrders.stream()
                .anyMatch(o -> o.getBugOrderId() == 1 && o.getName().equals("Coleptera")));
    }

    @Test
    void shouldFindLepidopteraById() {
        BugOrder expected = new BugOrder(2, "Lepidoptera", "butterflies, moths");
        BugOrder actual = repository.findById(2);

        assertEquals(expected, actual);
    }
}