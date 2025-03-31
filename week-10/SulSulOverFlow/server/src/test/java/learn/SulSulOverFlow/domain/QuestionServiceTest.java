package learn.SulSulOverFlow.domain;

import learn.SulSulOverFlow.TestHelper;
import learn.SulSulOverFlow.data.QuestionJdbcClientRepository;
import learn.SulSulOverFlow.data.UserJdbcClientRepository;
import learn.SulSulOverFlow.models.Questions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class QuestionServiceTest {
    @Autowired
    QuestionService service;

    @MockBean
    QuestionJdbcClientRepository repository;
    @MockBean
    UserJdbcClientRepository userRepository;

    @Test
    void findAll() {
        List<Questions> expected = List.of(TestHelper.makeQuestion());
        when(repository.findAll()).thenReturn(expected);
        List<Questions> actual = service.findAll();
        assertEquals(expected, actual);
    }
    @Nested
    class findByIDTests {
        @Test
        void findById() {
            Questions expected = TestHelper.makeQuestion();
            when(repository.findById(1)).thenReturn(expected);
            QuestionResult expectedResult = new QuestionResult();
            expectedResult.setQuestions(expected);
            QuestionResult actual = service.findById(1);
            assertEquals(expected, actual.getQuestions());
        }
        @Test
        void findByIdFailNotFound() {
            Questions expected = TestHelper.makeQuestion();
            when(repository.findById(10)).thenReturn(null);
            QuestionResult expectedResult = new QuestionResult();
            expectedResult.addErrorMessage("Question not found", ResultType.NOT_FOUND);
            QuestionResult actual = service.findById(1);
            assertEquals(null, actual.getQuestions());
        }
    }

    @Nested
    class findByUserIDTests {
        @Test
        void findByUserId() {
            List<Questions> expected = List.of(TestHelper.makeQuestion());
            when(repository.findByUserId(1)).thenReturn(expected);
//            Result<Questions> expectedResult = new Result<>();
//            expectedResult.setPayload(expected);
            List<Questions> actual = service.findByUserId(1);
            assertEquals(expected, actual);
        }
        @Test
        void findByUserIdFailNotFound() {
            when(repository.findByUserId(10)).thenReturn(null);
            QuestionResult expectedResult = new QuestionResult();
            expectedResult.addErrorMessage("Question not found", ResultType.NOT_FOUND);
            List<Questions> actual = service.findByUserId(10);
            assertEquals(null, actual);
        }
    }
    @Nested class createTests {
        @Test
        void create() {
            Questions question = TestHelper.makeQuestion();
            question.setQuestionId(0);
            when(userRepository.findById(question.getUserId())).thenReturn(TestHelper.makeUser());
            when(repository.create(question)).thenReturn(question);
            QuestionResult expectedResult = new QuestionResult();
            expectedResult.setQuestions(question);
            QuestionResult actual = service.create(question);
            assertEquals(expectedResult, actual);
        }
        @Test
        void createFailNoUser() {
            Questions question = TestHelper.makeQuestion();
            question.setQuestionId(0);
            question.setUserId(10);
            when(userRepository.findById(question.getUserId())).thenReturn(null);
            QuestionResult expectedResult = new QuestionResult();
            expectedResult.addErrorMessage("User does not exist.", ResultType.INVALID);
            QuestionResult actual = service.create(question);
            assertEquals(expectedResult, actual);
        }
        @Test
        void createFailNullTitle() {
            Questions question = TestHelper.makeQuestion();
            question.setQuestionId(0);
            question.setTitle("");
            when(userRepository.findById(question.getUserId())).thenReturn(TestHelper.makeUser());
            QuestionResult expectedResult = new QuestionResult();
            expectedResult.addErrorMessage("Question `title` is required.", ResultType.INVALID);
            QuestionResult actual = service.create(question);
            assertEquals(expectedResult, actual);
        }
        @Test
        void createFailNullBody() {
            Questions question = TestHelper.makeQuestion();
            question.setQuestionId(0);
            question.setBody("");
            when(userRepository.findById(question.getUserId())).thenReturn(TestHelper.makeUser());
            QuestionResult expectedResult = new QuestionResult();
            expectedResult.addErrorMessage("Question `body` is required.", ResultType.INVALID);
            QuestionResult actual = service.create(question);
            assertEquals(expectedResult, actual);
        }
    }

    @Nested
    class updateTests {
        @Test
        void update() {
            Questions question = TestHelper.makeQuestion();
            question.setTitle("New Title");
            when(repository.update(question)).thenReturn(true);
            when(userRepository.findById(question.getUserId())).thenReturn(TestHelper.makeUser());
            QuestionResult expectedResult = new QuestionResult();
            expectedResult.setQuestions(question);
            QuestionResult actual = service.update(question);
            assertEquals(expectedResult, actual);
        }
        @Test
        void updateFailNoId() {
            Questions question = TestHelper.makeQuestion();
            question.setQuestionId(0);
            when(userRepository.findById(question.getUserId())).thenReturn(TestHelper.makeUser());
            QuestionResult expectedResult = new QuestionResult();
            expectedResult.addErrorMessage("Question `id` is required.", ResultType.INVALID);
            expectedResult.addErrorMessage("Question not found.", ResultType.NOT_FOUND);
            QuestionResult actual = service.update(question);
            assertEquals(expectedResult, actual);
        }
        @Test
        void updateFailNotFound() {
            Questions question = TestHelper.makeQuestion();
            question.setQuestionId(10);
            when(userRepository.findById(question.getUserId())).thenReturn(TestHelper.makeUser());
            when(repository.update(question)).thenReturn(false);
            QuestionResult expectedResult = new QuestionResult();
            expectedResult.addErrorMessage("Question not found.", ResultType.NOT_FOUND);
            QuestionResult actual = service.update(question);
            assertEquals(expectedResult, actual);
        }
    }

    @Nested
    class deleteByIdTests {
        @Test
        void deleteById() {
            when(repository.deleteById(1)).thenReturn(true);
            QuestionResult expectedResult = new QuestionResult();
            QuestionResult actual = service.deleteById(1);
            assertEquals(expectedResult, actual);
        }
        @Test
        void deleteByIdFail() {
            when(repository.deleteById(10)).thenReturn(false);
            QuestionResult expectedResult = new QuestionResult();
            expectedResult.addErrorMessage("Question not found.", ResultType.NOT_FOUND);
            QuestionResult actual = service.deleteById(10);
            assertEquals(expectedResult, actual);
        }
    }
}