package learn.data;

import learn.models.User;
import learn.TestHelper;
import learn.models.UserMoods;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserMoodClientRepositoryTest {
    @Autowired
    JdbcClient jdbcClient;
    @Autowired
    UserMoodClientRepository repository;

    @BeforeEach
    void setup() {
        jdbcClient.sql("call set_known_good_state();").update();
    }
@Nested class findByTests {
    @Test
    void findByMoodId() {
        List<UserMoods> actual = repository.findByMoodId(1);
        assertNotNull(actual);
        assertEquals(1, actual.size());
    }

    @Test
    void findUserMoodsByMonth() {
        List<UserMoods> actual = repository.findUserMoodsByMonth(1, 3);
        assertNotNull(actual);
        assertEquals(0, actual.size());
    }

    @Test
    void findByUserId() {
        List<UserMoods> actual = repository.findByUserId(1);
        assertNotNull(actual);
        assertEquals(7, actual.size());
    }

    @Test
    void findAll() {
        List<UserMoods> actual = repository.findAll();
        assertNotNull(actual);
        assertEquals(7, actual.size());
    }

    @Test
    void findByDay() {
        List<UserMoods> actual = repository.findByDay(LocalDate.of(2025, 3, 10),1);
        assertNotNull(actual);
        assertEquals(7, actual.size());
    }
}
    @Nested class createTests {
      @Test
      void create() {
          User user = TestHelper.makeUserExisting();
          UserMoods userMoods = TestHelper.makeUserMood();
          userMoods.setMood_notes("new notesss");
          userMoods.setUserId(user.getUserId());
          UserMoods actual = repository.create(userMoods);
          assertEquals(8, actual.getUserMoodId());
      }
      @Test
      void createFailNullUserId() {
          UserMoods userMoods = TestHelper.makeUserMood();
          userMoods.setUserId(0);
          assertThrows(DataIntegrityViolationException.class, () -> {
              repository.create(userMoods);
          });
      }
      @Test
      void createFailNullMoodId() {
          UserMoods userMoods = TestHelper.makeUserMood();
          userMoods.setMoodId(0);
          assertThrows(DataIntegrityViolationException.class, () -> {
              repository.create(userMoods);
          });

      }
  }
}