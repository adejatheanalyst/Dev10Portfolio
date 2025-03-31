package learn.Domain;

import learn.Data.UserRepositoryDouble;
import learn.Model.User;
import learn.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    UserService service;
    final String existingUserEmail = "testemail3@email.com";
    final String nonexistingUserEmail = "466@hruii.com";
@BeforeEach
    void setup() {service = new UserService(new UserRepositoryDouble());
}

    @Test
    void findAllUsers() {
    List<User> users = service.findAllUsers();
    assertEquals(3, users.size());
    }
@Nested class FindById {
    @Test
    void findById() {
        UserResult<User> result = service.findById(1);
        assertTrue(result.isSuccess());
    }
    @Test
    void doesNotFindById() {
        User user = new User();
        user.setUser_id(4);
        UserResult<User> result = service.findById(4);
        assertFalse(result.isSuccess());
    }
}
@Nested class FindByEmail {
    @Test
    void findByEmail() {
        UserResult<User> result = service.findByEmail(existingUserEmail);
        assertTrue(result.isSuccess());
    }
    @Test
    void FailToFindEmail() {
        UserResult<User> result = service.findByEmail(nonexistingUserEmail);
        assertFalse(result.isSuccess());
    }
    @Test
    void FailToFindNullEmail() {
        UserResult<User> result = service.findByEmail(null);
        assertFalse(result.isSuccess());
    }
    @Test
    void FailToFindEmptyEmail() {
        UserResult<User> result = service.findByEmail(" ");
        assertFalse(result.isSuccess());
    }
}
}