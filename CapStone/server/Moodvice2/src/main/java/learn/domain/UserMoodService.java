package learn.domain;

import learn.data.UserMoodRepository;
import learn.data.UserRepository;
import learn.domain.Results.ResultType;
import learn.domain.Results.UserMoodResult;
import learn.models.UserMoods;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserMoodService {
    private final UserMoodRepository repository;
    private final UserRepository userRepository;

    public UserMoodService(UserMoodRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public UserMoodResult create(UserMoods userMoods) {
        UserMoodResult result = validate(userMoods);
        if (userMoods == null) {
            result.addErrorMessage("UserMood cannot be null.");
            result.setResultType(ResultType.INVALID);
            return result;
        }
        if (result.isSuccess()) {
            UserMoods created = repository.create(userMoods);
            result.setUserMoods(created);
        }
        return result;
    }
    public UserMoodResult getUserMoodsByDay(LocalDate date, int userId) {
        UserMoodResult result = new UserMoodResult();
        List<UserMoods> userMoods = repository.findByDay(date, userId);
        if (userMoods == null) {
            result.addErrorMessage("No UserMoods found.");
            result.setResultType(ResultType.NOT_FOUND);
        } else {
            result.setUserMoodsList(userMoods);
        }
        return result;
    }
    public UserMoodResult getUserMoodsByMoodId(int moodId) {
        UserMoodResult result = new UserMoodResult();
        List<UserMoods> userMoods = repository.findByMoodId(moodId);
        if (userMoods == null) {
            result.addErrorMessage("No UserMoods found.");
            result.setResultType(ResultType.NOT_FOUND);
        } else {
            result.setUserMoodsList(userMoods);
        }
        return result;
    }
    public UserMoodResult getUserMoodsByUserId(int userId) {
        UserMoodResult result = new UserMoodResult();
        List<UserMoods> userMoods = repository.findByUserId(userId);
        if (userMoods == null) {
            result.addErrorMessage("No UserMoods found.");
            result.setResultType(ResultType.NOT_FOUND);
        } else {
            result.setUserMoodsList(userMoods);
        }
        return result;
    }

   public List<UserMoods> findAll(){return repository.findAll();}

    private UserMoodResult validate(UserMoods userMoods){
        UserMoodResult result = new UserMoodResult();
        if(userMoods == null){
            result.addErrorMessage("UserMood cannot be null.");
            result.setResultType(ResultType.INVALID);
            return result;
        }
        if (userRepository.findByUserId(userMoods.getUserId()) == null) {
            result.addErrorMessage("User does not exist.");
            result.setResultType(ResultType.NOT_FOUND);
            return result;
        }
        if(userMoods.getMoodId() == 0){
            result.addErrorMessage("Mood is required.");
            result.setResultType(ResultType.INVALID);
        }
        return result;
    }
}
