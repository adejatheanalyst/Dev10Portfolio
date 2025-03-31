package learn.SulSulOverFlow.data;

import learn.SulSulOverFlow.TestHelper;
import learn.SulSulOverFlow.models.Answers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AnswerJdbcClientRepositoryTest {
    @Autowired
    JdbcClient client;
    @Autowired
    AnswerJdbcClientRepository repository;

    @BeforeEach
    void setup() {
        client.sql("call set_known_good_state();").update();
    }

    @Nested
    class CreateTests {
        @Test
        void create() {
            Answers answer = TestHelper.makeAnswer();
            answer.setAnswerId(0);
            answer.setBody("testBody");
            Answers actual = repository.create(answer);
            List<Answers> answersList = repository.findAll();

            assertEquals(5, actual.getAnswerId());
            assertEquals(5, answersList.size());
        }
        @Test
        void createFailDuplicateAnswer() {
            Answers answers = TestHelper.makeAnswer();
            answers.setAnswerId(0);
//            Answers actual = repository.create(answers);
            assertThrows(DuplicateKeyException.class, () -> {
                repository.create(answers);
            });

        }
    }
    @Nested
    class findByTests {
        @Test
        void findById() {
            Answers actual = repository.findById(1);
            assertNotNull(actual);
            assertEquals("42", actual.getBody());
        }
        @Test
        void findByIdFail() {
            Answers actual = repository.findById(10);
            assertNull(actual);
        }
    }


    @Nested
    class updateTests {
        @Test
        void update() {
            Answers answers = TestHelper.makeAnswer();
            answers.setAnswerId(1);
            answers.setBody("testbody1");
            assertTrue(repository.update(answers));
            Answers actual = repository.findById(1);
            assertEquals("testbody1", actual.getBody());
            assertEquals(1, actual.getAnswerId());

        }
        @Test
        void updateFailNoId() {
            Answers answer = TestHelper.makeAnswer();
            answer.setAnswerId(0);
            answer.setBody("testTitle1");
            assertFalse(repository.update(answer));
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
            List<Answers> answers = repository.findAll();
            assertEquals(4, answers.size());
        }
    }
    @Nested
    class findByUserTests {
        @Test
        void findByUserId() {
            List<Answers> answers = repository.findByUserId(1);
            assertEquals(2, answers.size());
        }
        @Test
        void findByUserIdFail() {
            List<Answers> answers = repository.findByUserId(10);
            assertEquals(0, answers.size());
        }
    }

    @Nested
    class findByQuestionTests {
        @Test
        void findByQuestionId() {
            List<Answers> answers = repository.findByQuestionId(1);
            assertEquals(2, answers.size());
        }
        @Test
        void findByQuestionIdFail() {
            List<Answers> answers = repository.findByUserId(10);
            assertEquals(0, answers.size());
        }
    }
}