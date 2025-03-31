package learn.solarfarm.domain;

import learn.solarfarm.TestHelper;
import learn.solarfarm.data.UserJdbcClientRepository;
import learn.solarfarm.data.UserRepository;
import learn.solarfarm.models.User;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserServiceTest {
    @Autowired
    UserService service;

    @MockBean
    UserJdbcClientRepository repository;


    @Nested
    class shouldCreateTest {
        @Test
        void shouldCreate() {
            User userNew = TestHelper.makeUser();
            User afterAdd = TestHelper.makeUser();
            when(repository.create(userNew)).thenReturn(afterAdd);
            Result<User> ExpectedResult = new Result<>();
            ExpectedResult.setPayload(afterAdd);
            Result<User> actual = service.create(userNew);
            assertEquals(ExpectedResult, actual);

        }

        @Test
        void shouldFailCreateUserNameBlank() {
            User userNew = TestHelper.makeUser();
            userNew.setUsername("");
            Result<User> ExpectedResult = new Result<>();
            ExpectedResult.addErrorMessage("Username is required.");
            Result<User> actual = service.create(userNew);
            assertEquals(ExpectedResult, actual);

        }


        @Test
        void shouldFailCreatePasswordBlack() {
            User userNew = TestHelper.makeUser();
            userNew.setPassword("");
            Result<User> ExpectedResult = new Result<>();
            ExpectedResult.addErrorMessage("Password is required.");
            Result<User> actual = service.create(userNew);
            assertEquals(ExpectedResult, actual);

        }
        @Test
        void shouldFailCreateUserNotUnique() {
            User userNew = TestHelper.makeUser();
            userNew.setUserId(0);
            when(repository.findByUsername(userNew.getUsername())).thenReturn(userNew);
            Result<User> ExpectedResult = new Result<>();
            ExpectedResult.addErrorMessage("Username already exists.", ResultType.INVALID);
            Result<User> actual = service.create(userNew);
            assertEquals(ExpectedResult, actual);

        }
    }

    @Nested
    class findByUserTests {

        @Test
        void shouldFindUser() {
            User user = TestHelper.makeUser();
            when(repository.findByUsername(user.getUsername())).thenReturn(user);
            Result<User> ExpectedResult = new Result<>();
            ExpectedResult.setPayload(user);
            Result<User> actual = service.findByUsername(user.getUsername());
            assertEquals(ExpectedResult, actual);
        }
        @Test
        void shouldNotFindUser() {
            User user = TestHelper.makeUser();
            when(repository.findByUsername(user.getUsername())).thenReturn(null);
            Result<User> ExpectedResult = new Result<>();
            ExpectedResult.addErrorMessage("User not found.");
            Result<User> actual = service.findByUsername(user.getUsername());
            assertEquals(ExpectedResult, actual);
        }
    }
}

