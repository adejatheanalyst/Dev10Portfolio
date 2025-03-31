package learn.solarfarm.domain;

import at.favre.lib.crypto.bcrypt.BCrypt;
import learn.solarfarm.data.UserRepository;
import learn.solarfarm.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;
    private final int BCRYPT_COST = 12;


    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Result<User> create(User user){
        Result<User> result = new Result<>();
        if(user.getUsername() == null || user.getUsername().isBlank()){
            result.addErrorMessage("Username is required.", ResultType.INVALID);
        }
        if(user.getPassword() == null || user.getPassword().isBlank()){
            result.addErrorMessage("Password is required.", ResultType.INVALID);
        }
        if(repository.findByUsername(user.getUsername()) != null){
            result.addErrorMessage("Username already exists.", ResultType.INVALID);
        }

        if(result.isSuccess()) {
            String hashedPassword = BCrypt.withDefaults().hashToString(BCRYPT_COST, user.getPassword().toCharArray());
            user.setPassword(hashedPassword);
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

}
