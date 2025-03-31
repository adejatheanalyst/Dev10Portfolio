package learn.SulSulOverFlow.domain;

import learn.SulSulOverFlow.TestHelper;
import learn.SulSulOverFlow.data.AnswerJdbcClientRepository;
import learn.SulSulOverFlow.data.QuestionJdbcClientRepository;
import learn.SulSulOverFlow.data.UserJdbcClientRepository;
import learn.SulSulOverFlow.models.Answers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AnswerServiceTest {
    @Autowired
    AnswerService service;

    @MockBean
    AnswerJdbcClientRepository repository;
    @MockBean
    UserJdbcClientRepository userRepository;
    @MockBean
    QuestionJdbcClientRepository questionRepository;


    @Test
    void findAll() {
        List<Answers> answers = List.of(TestHelper.makeAnswer());
        when(repository.findAll()).thenReturn(answers);
        assertEquals(answers, service.findAll());
    }
    @Nested
    class findByIDTests {
        @Test
        void findById() {
            AnswerResult answer = new AnswerResult(TestHelper.makeAnswer());
            when(repository.findById(1)).thenReturn(TestHelper.makeAnswer());
            AnswerResult expected = new AnswerResult();
            expected.setAnswers(TestHelper.makeAnswer());
            AnswerResult actual = service.findById(1);
            assertEquals(expected, actual);
        }
        @Test
        void findByIdFail() {
            when(repository.findById(10)).thenReturn(null);
            AnswerResult expected = service.findById(10);
            assertEquals(expected.getErrorMessages(), service.findById(10).getErrorMessages());
        }
    }

    @Nested
    class findByQuestionIDTests {
        @Test
        void findByQuestionId() {
            List<Answers> answers = List.of(TestHelper.makeAnswer());
            when(repository.findByQuestionId(1)).thenReturn(answers);
            assertEquals(answers, service.findByQuestionId(1));
        }
        @Test
        void findByQuestionIdFail() {
            when(repository.findByQuestionId(1)).thenReturn(null);
            assertNull(service.findByQuestionId(1));
        }
    }
    @Nested
    class findByUserIDTests {

        @Test
        void findByUserId() {
            List<Answers> answers = List.of(TestHelper.makeAnswer());
            when(repository.findByUserId(1)).thenReturn(answers);
            assertEquals(answers, service.findByUserId(1));
        }
        @Test
        void findByUserIdFail() {
            when(repository.findByUserId(1)).thenReturn(null);
            assertNull(service.findByUserId(1));
        }
    }
    @Nested
    class createTests {

        @Test
        void createPass() {
            Answers answer = TestHelper.makeAnswer();
            answer.setAnswerId(0);
            when(userRepository.findById(answer.getUserId())).thenReturn(TestHelper.makeUser());
            when(questionRepository.findById(answer.getQuestionId())).thenReturn(TestHelper.makeQuestion());
            when(repository.create(answer)).thenReturn(answer);
            AnswerResult expected = new AnswerResult();
            expected.setAnswers(answer);
            expected.setQuestionId(answer.getQuestionId());
            expected.setUserId(answer.getUserId());
            AnswerResult actual = service.create(answer);
            assertEquals(expected, actual);
        }
        @Test
        void createFailNullBody() {
            Answers answer = TestHelper.makeAnswer();
            answer.setAnswerId(0);
            answer.setBody("");
            when(userRepository.findById(answer.getUserId())).thenReturn(TestHelper.makeUser());
            when(questionRepository.findById(answer.getQuestionId())).thenReturn(TestHelper.makeQuestion());
            when(repository.create(answer)).thenReturn(answer);
            AnswerResult expected = new AnswerResult();
            expected.setQuestionId(answer.getQuestionId());
            expected.setUserId(answer.getUserId());
            expected.addErrorMessage("Answer body is required.", ResultType.INVALID);
            AnswerResult actual = service.create(answer);
            assertEquals(expected.getErrorMessages(), actual.getErrorMessages());
        }
        @Test
        void createFaiLNullUser() {
            Answers answer = TestHelper.makeAnswer();
            answer.setAnswerId(0);
            answer.setUserId(0);
            when(userRepository.findById(answer.getUserId())).thenReturn(null);
            when(questionRepository.findById(answer.getQuestionId())).thenReturn(TestHelper.makeQuestion());
            when(repository.create(answer)).thenReturn(answer);
            AnswerResult expected = new AnswerResult();
            expected.addErrorMessage("Answer must be associated with a user.", ResultType.NOT_FOUND);
            AnswerResult actual = service.create(answer);
            assertEquals(expected.getErrorMessages(), actual.getErrorMessages());
        }
        @Test
        void createFailNullQuestion() {
            Answers answer = TestHelper.makeAnswer();
            answer.setAnswerId(0);
            answer.setQuestionId(0);
            when(userRepository.findById(answer.getUserId())).thenReturn(TestHelper.makeUser());
            when(questionRepository.findById(answer.getQuestionId())).thenReturn(null);
            when(repository.create(answer)).thenReturn(answer);
            AnswerResult expected = new AnswerResult();
            expected.addErrorMessage("Answer must be associated with a question.", ResultType.NOT_FOUND);
            AnswerResult actual = service.create(answer);
            assertEquals(expected.getErrorMessages(), actual.getErrorMessages());
        }
    }

    @Nested
    class updateTests {
        @Test
        void updatePass() {
            Answers answers = TestHelper.makeAnswer();
            answers.setBody("New Body");
            when(userRepository.findById(answers.getUserId())).thenReturn(TestHelper.makeUser());
            when(questionRepository.findById(answers.getQuestionId())).thenReturn(TestHelper.makeQuestion());
            when(repository.update(answers)).thenReturn(true);
            AnswerResult expected = new AnswerResult();
            expected.setAnswers(answers);
            AnswerResult actual = service.update(answers);
            assertEquals(expected.getResultType(), actual.getResultType());

        }
        @Test
        void updateFailNullBody() {
            Answers answers = TestHelper.makeAnswer();
            answers.setBody("");
            when(userRepository.findById(answers.getUserId())).thenReturn(TestHelper.makeUser());
            when(questionRepository.findById(answers.getQuestionId())).thenReturn(TestHelper.makeQuestion());
            when(repository.update(answers)).thenReturn(false);
            AnswerResult expected = new AnswerResult();
            expected.addErrorMessage("Answer body is required.", ResultType.INVALID);
            expected.addErrorMessage("Answer not found.", ResultType.NOT_FOUND);
            AnswerResult actual = service.update(answers);
            assertEquals(expected.getErrorMessages(), actual.getErrorMessages());
        }
        @Test
        void updateFailDuplicate() {
            Answers answers = TestHelper.makeAnswer();
            answers.setBody("New Body");
            when(userRepository.findById(answers.getUserId())).thenReturn(TestHelper.makeUser());
            when(questionRepository.findById(answers.getQuestionId())).thenReturn(TestHelper.makeQuestion());
            when(repository.update(answers)).thenReturn(false);
            AnswerResult expected = new AnswerResult();
            expected.addErrorMessage("Your answer is a duplicate.", ResultType.INVALID);
            AnswerResult actual = service.update(answers);
            assertEquals(expected.getResultType(), actual.getResultType());
        }
        @Test
        void updateFailNullUser() {
            Answers answers = TestHelper.makeAnswer();
            answers.setBody("New Body");
            answers.setUserId(0);
            when(userRepository.findById(answers.getUserId())).thenReturn(null);
            when(questionRepository.findById(answers.getQuestionId())).thenReturn(TestHelper.makeQuestion());
            when(repository.update(answers)).thenReturn(false);
            AnswerResult expected = new AnswerResult();
            expected.addErrorMessage("Answer must be associated with a user.", ResultType.INVALID);
            expected.addErrorMessage("Answer not found.", ResultType.NOT_FOUND);
            AnswerResult actual = service.update(answers);
            assertEquals(expected, actual);
        }
    }


    @Nested
    class deleteTests {
        @Test
        void deleteById() {
            when(repository.deleteById(1)).thenReturn(true);
           AnswerResult expected = new AnswerResult();
           AnswerResult actual = service.deleteById(1);
            assertEquals(expected, actual);
        }
        @Test
        void deleteByIdFail() {
            when(repository.deleteById(10)).thenReturn(false);
           AnswerResult expected = new AnswerResult();
            expected.addErrorMessage("Answer not found.", ResultType.NOT_FOUND);
           AnswerResult actual = service.deleteById(10);
            assertEquals(expected, actual);
        }
    }
}