package learn.SulSulOverFlow.domain;

import learn.SulSulOverFlow.TestHelper;
import learn.SulSulOverFlow.data.UserJdbcClientRepository;
import learn.SulSulOverFlow.models.User;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserServiceTest {
    @Autowired
    UserService service;

    @MockBean
    UserJdbcClientRepository repository;


    @Nested
    class CreateTests {
        @Test
        void create() {
            User newUser = TestHelper.makeUser();
            newUser.setUsername("testUsername1");
            User afterAdd = newUser;
            when(repository.create(newUser)).thenReturn(afterAdd);
            Result<User> expectedResult = new Result<>();
            expectedResult.setPayload(afterAdd);
            Result<User> actual = service.create(newUser);
            assertEquals(expectedResult, actual);
        }

        @Test
        void createFailDupUsername() {
            User newUser = TestHelper.makeUser();
            newUser.setUserId(0);
            when(repository.findByUsername(newUser.getUsername())).thenReturn(newUser);
            Result<User> expectedResult = new Result<>();
            expectedResult.addErrorMessage("Username already exists.", ResultType.INVALID);
            Result<User> actual = service.create(newUser);
            assertEquals(expectedResult, actual);
        }

        @Test
        void createFailUsernameNull() {
            User newUser = TestHelper.makeUser();
            newUser.setUsername("");
            Result<User> expectedResult = new Result<>();
            expectedResult.addErrorMessage("Username is required.", ResultType.INVALID);
            Result<User> actual = service.create(newUser);
            assertEquals(expectedResult, actual);
        }

        @Test
        void createFailPasswordNull() {
            User newUser = TestHelper.makeUser();
            newUser.setPassword("");
            Result<User> expectedResult = new Result<>();
            expectedResult.addErrorMessage("Password is required.", ResultType.INVALID);
            Result<User> actual = service.create(newUser);
            assertEquals(expectedResult, actual);
        }
    }

    @Nested
    class findByUsernameTests {
        @Test
        void findByUsername() {
            User actual = TestHelper.makeUser();
            when(repository.findByUsername("alice")).thenReturn(actual);
            Result<User> expectedResult = new Result<>();
            expectedResult.setPayload(actual);
            Result<User> result = service.findByUsername("alice");
            assertEquals(expectedResult, result);
        }

        @Test
        void findByUsernameFailNullUsername() {
            User user = TestHelper.makeUser();
            user.setUsername("");
            when(repository.findByUsername(user.getUsername())).thenReturn(null);
            Result<User> expectedResult = new Result<>();
            expectedResult.addErrorMessage("Username is required.", ResultType.INVALID);
            Result<User> result = service.findByUsername(user.getUsername());
            assertEquals(expectedResult, result);
        }

        @Test
        void findByUsernameFailNotFound() {
            User user = TestHelper.makeUser();
            user.setUsername("test3");
            when(repository.findByUsername(user.getUsername())).thenReturn(null);
            Result<User> expectedResult = new Result<>();
            expectedResult.addErrorMessage("User not found.", ResultType.INVALID);
            Result<User> result = service.findByUsername(user.getUsername());
            assertEquals(expectedResult, result);
        }

    }
}
