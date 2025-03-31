package learn.Domain;

import learn.Data.UserRepository;
import learn.Model.User;
import org.springframework.dao.DataAccessException;
import learn.Domain.UserResult;

import java.util.List;

public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAllUsers() throws DataAccessException {
        return repository.findAllUsers();
    }

    public UserResult<User> findById(Integer user_id) throws DataAccessException {
        User user = repository.findById(user_id);
        UserResult<User> result = new UserResult<>();
        if (user == null) {
            result.addErrorMessage("User not found.");
        }else {
            result.setUser(user);
        }
        return result;
    }
    public UserResult<User> findByEmail(String email) throws DataAccessException {
        User user = repository.findByEmail(email);
        UserResult<User> result = new UserResult<>();
        if (email == null || email.isBlank()) {
            result.addErrorMessage("Email is required.");
        } if (user == null) {
            result.addErrorMessage("User not found.");
        }else {
            result.setUser(user);
        }
        return result;
    }
}
