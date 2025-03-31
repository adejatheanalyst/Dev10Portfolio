package learn.SulSulOverFlow.domain;

import learn.SulSulOverFlow.data.UserRepository;
import learn.SulSulOverFlow.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Result<User> create(User user){
        Result<User> result = validate(user);

        if(repository.findByUsername(user.getUsername()) != null){
            result.addErrorMessage("Username already exists.", ResultType.INVALID);
        }
        if(result.isSuccess()) {
            User newUser = repository.create(user);
            result.setPayload(newUser);
        }
        return result;
    }
    public Result<User> findByUsername(String username){
        Result<User> result = new Result<>();
        if(username == null || username.isBlank()){
            result.addErrorMessage("Username is required.", ResultType.INVALID);
        }
        if(result.isSuccess()) {
            User user = repository.findByUsername(username);
            if(user == null){
                result.addErrorMessage("User not found.", ResultType.NOT_FOUND);
            }
            result.setPayload(user);
        }
        return result;
    }

    public Result<User> validate(User user) {
        Result<User> result = new Result<>();
        if (user == null) {
            result.addErrorMessage("User cannot be null.", ResultType.INVALID);
            return result;
        }
        if (user.getUsername() == null || user.getUsername().isBlank()) {
            result.addErrorMessage("Username is required.", ResultType.INVALID);
        }
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            result.addErrorMessage("Password is required.", ResultType.INVALID);
        }
        return result;
    }
}
