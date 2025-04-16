package learn.domain;

import learn.TestHelper;
import learn.data.UserClientRepository;
import learn.data.UserMoodClientRepository;
import learn.data.UserMoodRepository;
import learn.domain.Results.ResultType;
import learn.domain.Results.UserMoodResult;
import learn.models.User;
import learn.models.UserMoods;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserMoodServiceTest {
@Autowired
UserMoodService service;
@Autowired
UserService userService;
@MockBean
UserMoodClientRepository repository;
@MockBean
UserClientRepository userRepository;


    @Test
    void getUserMoodsByUserId() {
        User user = TestHelper.makeUser();
        List<UserMoods> userMood = List.of(TestHelper.makeUserMood());
        when(userRepository.findByUserId(user.getUserId())).thenReturn(user);
        when(repository.findByUserId(user.getUserId())).thenReturn(userMood);
        UserMoodResult result = new UserMoodResult();
        result.setUserMoodsList(userMood);
        UserMoodResult actual = service.getUserMoodsByUserId(user.getUserId());
        assertEquals(result, actual);
    }

    @Test
    void getUserMoodsByDay() {
        when(repository.findByDay(LocalDate.now(), 1)).thenReturn(List.of(TestHelper.makeUserMoodExisting()));
        UserMoodResult result = new UserMoodResult();
        result.setUserMoodsList(List.of(TestHelper.makeUserMoodExisting()));
        UserMoodResult actual = service.getUserMoodsByDay(LocalDate.now(), 1);
        assertEquals(result, actual);
    }

    @Nested class createTests {
    @Test
    void create() {
        User user = TestHelper.makeUser();
        UserMoods userMood = TestHelper.makeUserMood();
        userMood.setUserId(user.getUserId());
        when(userRepository.findByUserId(user.getUserId())).thenReturn(user);
        when(repository.create(userMood)).thenReturn(userMood);
        UserMoodResult result = new UserMoodResult();
        result.setUserMoods(userMood);
        UserMoodResult actual = service.create(userMood);
        assertEquals(result, actual);
    }

    @Test
    void createFailNonexistentUser() {
        User user = TestHelper.makeUser();
        user.setUserId(0);
        UserMoods userMood = TestHelper.makeUserMood();
        userMood.setUserId(user.getUserId());
        when(userRepository.findByUserId(user.getUserId())).thenReturn(null);
        UserMoodResult result = new UserMoodResult();
        result.addErrorMessage("User does not exist.");
        result.setResultType(ResultType.NOT_FOUND);
        UserMoodResult actual = service.create(userMood);
        assertEquals(result, actual);
    }
    @Test
    void createFailNullMood() {
        User user = TestHelper.makeUser();
        UserMoods userMood = TestHelper.makeUserMood();
        userMood.setUserId(user.getUserId());
        userMood.setMoodId(0);
        when(userRepository.findByUserId(user.getUserId())).thenReturn(user);
        when(repository.create(userMood)).thenReturn(null);
        UserMoodResult result = new UserMoodResult();
        result.addErrorMessage("Mood is required.");
        result.setResultType(ResultType.INVALID);
        UserMoodResult actual = service.create(userMood);
        assertEquals(result, actual);
    }
}
}