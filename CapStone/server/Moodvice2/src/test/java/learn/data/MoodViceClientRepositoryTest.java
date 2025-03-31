package learn.data;

import learn.TestHelper;
import learn.models.MoodVice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MoodViceClientRepositoryTest {

    @Autowired
    JdbcClient client;
    @Autowired
    MoodViceClientRepository repository;

    @BeforeEach
    void setup() {
        client.sql("call set_known_good_state();").update();
    }

    @Test
    void create() {
        MoodVice moodVice = TestHelper.makeMoodVice();
        MoodVice actual = repository.create(moodVice);
        assertNotNull(actual);
        assertEquals(5, actual.getMoodViceId());
    }

    @Test
    void findByMoodViceId() {
        MoodVice actual = repository.findByMoodViceId(1);
        assertNotNull(actual);
        assertEquals(1, actual.getMoodViceId());

    }

    @Test
    void findByUserId() {
        List<MoodVice> actual = repository.findByUserId(1);
        assertNotNull(actual);
        assertEquals(1, actual.size());
    }

    @Nested class UpdateTests {
    @Test
    void update() {
        MoodVice moodVice = TestHelper.makeMoodVice();
        moodVice.setMoodViceId(1);
        assertTrue(repository.update(moodVice));
    }
    @Test
    void updateFail() {
        MoodVice moodVice = TestHelper.makeMoodVice();
        moodVice.setMoodViceId(0);
        assertFalse(repository.update(moodVice));
    }
}
@Nested class DeleteTests {

    @Test
    void deleteById() {
        assertTrue(repository.deleteById(1));
    }
    @Test
    void deleteByIdFail() {
        assertFalse(repository.deleteById(10));
    }
}

    @Nested class findAllTests {
    @Test
    void findAll() {
        List<MoodVice> moodVices = repository.findAll();
        assertEquals(4, moodVices.size());
    }
}
@Nested class findByIdTests {
//    @Test
//    void findByMoodType() {
//        List<MoodVice> moodVice = repository.findByMoodType(2);
//        assertNotNull(moodVice);
//        assertEquals(2, moodVice.size());
//    }
    @Test
    void findByMoodTypeFail() {
        List<MoodVice> moodVice = repository.findByMoodType(5);
        assertNotNull(moodVice);
        assertEquals(0, moodVice.size());
    }
}
}