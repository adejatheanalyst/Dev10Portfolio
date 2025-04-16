package learn.data;

import learn.models.Media;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MediaClientRepositoryTest {
    @Autowired
    JdbcClient client;
    @Autowired
    MediaClientRepository repository;

    @BeforeEach
    void setup() {
        client.sql("call set_known_good_state();").update();
    }
@Nested class findByTests {
    @Test
    void findByMoodId() {
        List<Media> actual = repository.findByMoodId(2);
        assertNotNull(actual);
        assertEquals(2, actual.size());
    }
    @Test
    void findByMoodIdNone() {
        List<Media> actual = repository.findByMoodId(5);
        assertNotNull(actual);
        assertEquals(0, actual.size());
    }

    @Test
    void findById() {
        Media actual = repository.findById(2);
        assertNotNull(actual);
        assertEquals(2, actual.getMediaId());
        assertEquals(1, actual.getMoodId());
    }
}
}