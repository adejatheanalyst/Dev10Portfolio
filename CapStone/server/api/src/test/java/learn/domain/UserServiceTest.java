package learn.domain;

import learn.data.UserClientRepository;
import learn.domain.Results.ResultType;
import learn.domain.Results.UserResult;
import learn.models.User;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserServiceTest {
    @Autowired
    UserService service;

    @MockBean
    UserClientRepository repository;

    @Test
    void deleteById() {
        User user = new User();
        when(repository.deleteById(1)).thenReturn(true);
        UserResult ExpectedResult = new UserResult();
        ExpectedResult.setUser(user);
        ExpectedResult.setResultType(ResultType.SUCCESS);
        UserResult actual = service.deleteById(1);
        assertEquals(ExpectedResult.getResultType(), actual.getResultType());

    }

    @Nested class shouldFindAllTest {
    @Test
    void findByUserId() {
        User user = makeUser();
        when(repository.findByUserId(user.getUserId())).thenReturn(user);
        UserResult ExpectedResult = new UserResult();
        ExpectedResult.setUser(user);
        UserResult actual = service.findByUserId(user.getUserId());
        assertEquals(ExpectedResult, actual);
    }

    @Test
    void findByUserIdFailNotFound() {
        User user = makeUser();
        when(repository.findByUserId(user.getUserId())).thenReturn(null);
        UserResult ExpectedResult = new UserResult();
        ExpectedResult.addErrorMessage("User not found.");
        ExpectedResult.setResultType(ResultType.NOT_FOUND);
        UserResult actual = service.findByUserId(user.getUserId());
        assertEquals(ExpectedResult, actual);
    }
}
    @Nested class shouldUpdateTest {
    @Test
    void update() {
        User user = makeUser();
        user.setUsername("NewUsername253");
        when(repository.update(user)).thenReturn(true);
        UserResult ExpectedResult = new UserResult();
        ExpectedResult.setUser(user);
        UserResult actual = service.update(user);
        assertEquals(ExpectedResult, actual);

    }
    @Test
    void updateFailNoId() {
        User user = makeUser();
        user.setUserId(0);
        user.setUsername("NewUsername253");
        when(repository.update(user)).thenReturn(false);
        UserResult ExpectedResult = new UserResult();
        ExpectedResult.addErrorMessage("User ID is required.");
        ExpectedResult.setResultType(ResultType.INVALID);
        UserResult actual = service.update(user);
        assertEquals(ExpectedResult, actual);

    }
    @Test
    void updateFailNotFound() {
        User user = makeUser();
        user.setUserId(6);
        user.setUsername("NewUsername253");
        when(repository.update(user)).thenReturn(false);
        UserResult ExpectedResult = new UserResult();
        ExpectedResult.addErrorMessage("User not found.");
        ExpectedResult.setResultType(ResultType.NOT_FOUND);
        UserResult actual = service.update(user);
        assertEquals(ExpectedResult, actual);
    }
}

    @Nested class shouldCreateTest {
        @Test
        void shouldCreate() {
            User userNew = makeUser();
            User afterAdd = makeUser();
            when(repository.create(userNew)).thenReturn(afterAdd);
            UserResult ExpectedResult = new UserResult();
            ExpectedResult.setUser(afterAdd);
            UserResult actual = service.create(userNew);
            assertEquals(ExpectedResult, actual);

        }

        @Test
        void shouldFailCreateUserNameBlank() {
            User userNew = makeUser();
            userNew.setUsername("");
            UserResult ExpectedResult = new UserResult();
            ExpectedResult.addErrorMessage("Username is required.");
            UserResult actual = service.create(userNew);
            assertEquals(ExpectedResult, actual);

        }
        @Test
        void shouldFailCreatePasswordBlank() {
            User userNew = makeUser();
            userNew.setPassword("");
            UserResult ExpectedResult = new UserResult();
            ExpectedResult.addErrorMessage("Password is required.");
            UserResult actual = service.create(userNew);
            assertEquals(ExpectedResult, actual);

        }
        @Test
        void shouldFailCreateDupUsername() {
            User userNew = makeUser();
            userNew.setUserId(0);
            userNew.setUsername("testUsername");
            when(repository.findByUsername(userNew.getUsername())).thenReturn(userNew);
            UserResult ExpectedResult = new UserResult();
            ExpectedResult.addErrorMessage("Username already exists.");
            UserResult actual = service.create(userNew);
            assertEquals(ExpectedResult, actual);

        }
    }
@Nested class shouldFindByUsernameTest {
    @Test
    void findByUsername() {
        User user = makeUser();
        when(repository.findByUsername(user.getUsername())).thenReturn(user);
        UserResult ExpectedResult = new UserResult();
        ExpectedResult.setUser(user);
        UserResult actual = service.findByUsername(user.getUsername());
        assertEquals(ExpectedResult, actual);

    }
    @Test
    void findByUsernameFail() {
        User user = makeUser();
        when(repository.findByUsername(user.getUsername())).thenReturn(null);
        UserResult ExpectedResult = new UserResult();
        ExpectedResult.addErrorMessage("User not found.");
        ExpectedResult.setResultType(ResultType.NOT_FOUND);
        UserResult actual = service.findByUsername(user.getUsername());
        assertEquals(ExpectedResult, actual);

    }
}
    public static User makeUser() {
        return new User(1,"testUrl","testFirstName", "testLastName", "test", "password", "testemail@test.com", LocalDate.now());
    }
}