package learn.domain;

import learn.TestHelper;
import learn.data.MoodViceRepository;
import learn.data.UserRepository;
import learn.domain.Results.MoodViceResult;
import learn.domain.Results.ResultType;
import learn.models.MoodVice;
import learn.models.User;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MoodViceServiceTest {
    @Autowired
    MoodViceService service;
    @MockBean
    MoodViceRepository repository;
    @MockBean
    UserRepository userRepository;
User existingUser = TestHelper.makeUserExisting();

@Nested class findTests {
    @Test
    void findAll() {
        List<MoodVice> expected = List.of(new MoodVice(1, "testTitle", "testbody", 1, 4,"testUrl", LocalDate.now()));
        when(repository.findAll()).thenReturn(expected);
        List<MoodVice> actual = service.findAll();
        assertEquals(expected, actual);
    }

    @Test
    void findByMoodType() {
        List<MoodVice> expected = List.of(new MoodVice(1, "testTitle", "testbody", 1, 4, "testUrl", LocalDate.now()));
        when(repository.findByMoodType(1)).thenReturn(expected);
        MoodViceResult expectedResult = new MoodViceResult();
        expectedResult.setMoodVices(expected);
        MoodViceResult actual = service.findByMoodType(1);
        assertEquals(expectedResult.getResultType(), actual.getResultType());
    }

    @Test
    void findByMoodViceId() {
        MoodVice expected = TestHelper.makeMoodVice();
        when(repository.findByMoodViceId(1)).thenReturn(TestHelper.makeMoodVice());
        MoodVice actual = service.findByMoodViceId(1);
        assertEquals(expected, actual);
    }

    @Test
    void findByUserId() {
        List<MoodVice> expected = List.of(new MoodVice(4,"testtitle4","testbody4", 1, 1, "testUrl",LocalDate.now()));
        when(repository.findByUserId(1)).thenReturn(expected);
        List<MoodVice> actual = service.findByUserId(1);
        assertEquals(actual,expected);
    }
}

    @Nested class createTests {
    @Test
    void createFailNullMood() {
        MoodVice newMoodVice = TestHelper.makeMoodVice();
        newMoodVice.setMoodId(0);
        when(repository.create(newMoodVice)).thenReturn(null);
        MoodViceResult expectedResult = new MoodViceResult();
        expectedResult.setMoodVice(newMoodVice);
        expectedResult.setResultType(ResultType.INVALID);
        MoodViceResult actual = service.create(newMoodVice);
        assertEquals(expectedResult.getResultType(), actual.getResultType());

    }

    @Test
    void createFailSetMoodViceId() {
        MoodVice newMoodVice = TestHelper.makeMoodVice();
        newMoodVice.setMoodViceId(2);
        when(repository.create(newMoodVice)).thenReturn(null);
        MoodViceResult expectedResult = new MoodViceResult();
        expectedResult.setMoodVice(newMoodVice);
        expectedResult.setResultType(ResultType.INVALID);
        MoodViceResult actual = service.create(newMoodVice);
        assertEquals(expectedResult.getResultType(), actual.getResultType());

    }

    @Test
    void create() {
        MoodVice newMoodVice = TestHelper.makeMoodVice();
        when(repository.create(newMoodVice)).thenReturn(newMoodVice);
        MoodViceResult expectedResult = new MoodViceResult();
        expectedResult.setMoodVice(newMoodVice);
        MoodViceResult actual = service.create(newMoodVice);
        assertEquals(expectedResult.getResultType(), actual.getResultType());

    }
}
@Nested class updateTests {

    @Test
    void update() {
        MoodVice moodVice = TestHelper.makeMoodViceExisting();
        User user = existingUser;
        moodVice.setUserId(user.getUserId());
        moodVice.setTitle("NewTitle");
        moodVice.setBody("newBody");
        when(userRepository.findByUserId(moodVice.getUserId())).thenReturn(TestHelper.makeUserExisting());
        when(repository.update(moodVice)).thenReturn(true);
        MoodViceResult result = new MoodViceResult();
        result.setMoodVice(moodVice);
        MoodViceResult actual = service.update(moodVice);
        assertEquals(result, actual);
    }
    @Test
    void updateFailMoodTypeRequired() {
        MoodVice moodVice = TestHelper.makeMoodViceExisting();
        User user = existingUser;
        moodVice.setUserId(user.getUserId());
        moodVice.setTitle("NewTitle");
        moodVice.setBody("newBody");
        moodVice.setMoodId(0);
        when(userRepository.findByUserId(moodVice.getUserId())).thenReturn(TestHelper.makeUserExisting());
        when(repository.update(moodVice)).thenReturn(false);
        MoodViceResult result = new MoodViceResult();
        result.addErrorMessage("MoodType is required.");
        result.setResultType(ResultType.INVALID);
        MoodViceResult actual = service.update(moodVice);
        assertEquals(result, actual);
    }
    @Test
    void updateFailMoodViceNonexistent() {
        MoodVice moodVice = TestHelper.makeMoodViceExisting();
        User user = existingUser;
        moodVice.setUserId(user.getUserId());
        moodVice.setTitle("NewTitle");
        moodVice.setBody("newBody");
        moodVice.setMoodViceId(0);
        when(userRepository.findByUserId(moodVice.getUserId())).thenReturn(TestHelper.makeUserExisting());
        when(repository.update(moodVice)).thenReturn(false);
        MoodViceResult result = new MoodViceResult();
        result.addErrorMessage("Could not update");
        result.setResultType(ResultType.INVALID);
        MoodViceResult actual = service.update(moodVice);
        assertEquals(result, actual);
    }
    @Test
    void updateFailUserNonexistent() {
        MoodVice moodVice = TestHelper.makeMoodViceExisting();
        moodVice.setTitle("NewTitle");
        moodVice.setBody("newBody");
        moodVice.setUserId(0);
        when(userRepository.findByUserId(moodVice.getUserId())).thenReturn(TestHelper.makeUser());
        when(repository.update(moodVice)).thenReturn(false);
        MoodViceResult result = new MoodViceResult();
        result.addErrorMessage("User does not exist");
        result.setResultType(ResultType.NOT_FOUND);
        MoodViceResult actual = service.update(moodVice);
        assertEquals(result, actual);
    }
    @Test
    void updateFailNullTitle() {
        MoodVice moodVice = TestHelper.makeMoodViceExisting();
        User user = existingUser;
        moodVice.setUserId(user.getUserId());
        moodVice.setTitle("");
        moodVice.setBody("newBody");
        when(userRepository.findByUserId(moodVice.getUserId())).thenReturn(TestHelper.makeUserExisting());
        when(repository.update(moodVice)).thenReturn(false);
        MoodViceResult result = new MoodViceResult();
        result.addErrorMessage("Title is Required");
        result.setResultType(ResultType.INVALID);
        MoodViceResult actual = service.update(moodVice);
        assertEquals(result, actual);
    }
    @Test
    void updateFailNullBody() {
        MoodVice moodVice = TestHelper.makeMoodViceExisting();
        User user = existingUser;
        moodVice.setUserId(user.getUserId());
        moodVice.setTitle("SOMTHING");
        moodVice.setBody("");
        when(userRepository.findByUserId(moodVice.getUserId())).thenReturn(TestHelper.makeUserExisting());
        when(repository.update(moodVice)).thenReturn(false);
        MoodViceResult result = new MoodViceResult();
        result.addErrorMessage("Body is required");
        result.setResultType(ResultType.INVALID);
        MoodViceResult actual = service.update(moodVice);
        assertEquals(result, actual);
    }
}
@Nested class deleteTests {
    @Test
    void deleteById() {
        when(repository.deleteById(1)).thenReturn(true);
        MoodViceResult result = new MoodViceResult();
        MoodViceResult actual = service.deleteById(1);
        assertEquals(result, actual);
    }
    @Test
    void deleteByIdFailNotFound() {
        when(repository.deleteById(999)).thenReturn(false);
        MoodViceResult result = new MoodViceResult();
        result.addErrorMessage("Not found for Deletion");
        result.setResultType(ResultType.NOT_FOUND);
        MoodViceResult actual = service.deleteById(999);
        assertEquals(result, actual);
    }
}
}