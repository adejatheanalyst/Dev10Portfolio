package learn.SulSulOverFlow.data;

import learn.SulSulOverFlow.TestHelper;
import learn.SulSulOverFlow.models.Questions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class QuestionJdbcClientRepositoryTest {

    @Autowired
    JdbcClient client;
    @Autowired
    QuestionJdbcClientRepository repository;

    @BeforeEach
    void setup() {
        client.sql("call set_known_good_state();").update();
    }


    @Nested
    class CreateTests {
        @Test
        void create() {
            Questions question = TestHelper.makeQuestion();
            question.setTitle("testTitle1");
            Questions actual = repository.create(question);
            List<Questions> questions = repository.findAll();
            assertEquals(4, actual.getQuestionId());
            assertEquals(4, questions.size());
        }
    }

    @Nested
    class findByTests {
        @Test
        void findById() {
            Questions actual = repository.findById(2);
            assertNotNull(actual);
            assertEquals(2, actual.getQuestionId());
            assertEquals(2, actual.getUserId());
        }
        @Test
        void findByIdFail() {
            Questions actual = repository.findById(10);
            assertNull(actual);
        }
    }


    @Nested
    class updateTests {
        @Test
        void update() {
            Questions question = TestHelper.makeQuestion();
            question.setQuestionId(1);
            question.setTitle("testTitle1");
            assertTrue(repository.update(question));
            Questions actual = repository.findById(1);
            assertEquals("testTitle1", actual.getTitle());
        }
        @Test
        void updateFailNoId() {
            Questions question = TestHelper.makeQuestion();
            question.setQuestionId(0);
            question.setTitle("testTitle1");
            assertFalse(repository.update(question));
        }
    }



    @Nested
    class deleteByIdTests {
        @Test
        void deleteById() {
            assertTrue(repository.deleteById(1));

        }
        @Test
        void deleteByIdFail() {
            assertFalse(repository.deleteById(10));
        }
    }


    @Nested
    class findAllTests {
        @Test
        void findAll() {
            List<Questions> questions = repository.findAll();
            assertEquals(3, questions.size());
        }
    }


    @Nested
    class findByUserTests {
        @Test
        void findByUserId() {
            List<Questions> questions = repository.findByUserId(1);
            assertEquals(1, questions.size());
        }
        @Test
        void findByUserIdFail() {
            List<Questions> questions = repository.findByUserId(5);
            assertEquals(0, questions.size());
        }
        }

}