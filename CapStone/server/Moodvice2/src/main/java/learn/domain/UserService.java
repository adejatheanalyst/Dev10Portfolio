package learn.domain;

import learn.data.UserRepository;
import learn.domain.Results.ResultType;
import learn.domain.Results.UserResult;
import learn.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    public UserResult findByUserId(int userId){
        UserResult result = new UserResult();
        if(userId <= 0){
            result.addErrorMessage("User ID is required.");
            result.setResultType(ResultType.INVALID);
        }
        if(result.isSuccess()) {
            User user = repository.findByUserId(userId);
            if(user == null){
                result.addErrorMessage("User not found.");
                result.setResultType(ResultType.NOT_FOUND);
            }
            result.setUser(user);
        }
        return result;
    }
    public UserResult create(User user){
        UserResult result = validate(user);
        if(repository.findByUsername(user.getUsername()) != null){
            result.addErrorMessage("Username already exists.");
            result.setResultType(ResultType.INVALID);
        }
        if(result.isSuccess()) {
            User newUser = repository.create(user);
            result.setUser(newUser);
        }
        return result;
    }
    public UserResult findByUsername(String username){
        UserResult result = new UserResult();
        if(username == null || username.isBlank()){
            result.addErrorMessage("Username is required.");
            result.setResultType(ResultType.INVALID);
        }
        if(result.isSuccess()) {
            User user = repository.findByUsername(username);
            if(user == null){
                result.addErrorMessage("The username does not exist. Please check login information and try again.");
                result.setResultType(ResultType.NOT_FOUND);
            }
            result.setUser(user);
        }
        return result;
    }
    public UserResult update(User user){
        UserResult result = validate(user);
        if(user.getUserId() <= 0){
            result.addErrorMessage("User ID is required.");
            result.setResultType(ResultType.INVALID);
            return result;
        }
        if(result.isSuccess()) {
            boolean success = repository.update(user);
            if (!success) {
                result.addErrorMessage("User not found.");
                result.setResultType(ResultType.NOT_FOUND);
            }else {
                result.setUser(user);
            }
        }
        return result;
    }
    public UserResult deleteById(int userId){
        UserResult result = new UserResult();
        if(userId <= 0){
            result.addErrorMessage("User ID is required.");
            result.setResultType(ResultType.INVALID);
        }
        if(result.isSuccess()) {
            boolean success = repository.deleteById(userId);
            if (!success) {
                result.addErrorMessage("User not found.");
                result.setResultType(ResultType.NOT_FOUND);
            }
        }
        return result;
    }
    private UserResult validate(User user) {
        UserResult result = new UserResult();

        if (user == null) {
            result.addErrorMessage("User cannot be null.");
            result.setResultType(ResultType.INVALID);
            return result;
        }
        if(user.getUsername() == null || user.getUsername().isBlank()){
            result.addErrorMessage("Username is required.");
            result.setResultType(ResultType.INVALID);
        }
        if(user.getPassword() == null || user.getPassword().isBlank()){
            result.addErrorMessage("Password is required.");
            result.setResultType(ResultType.INVALID);
        }

        return result;
    }
}
