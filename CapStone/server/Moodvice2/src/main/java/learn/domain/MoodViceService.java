package learn.domain;

import learn.data.MoodViceRepository;
import learn.data.UserRepository;
import learn.domain.Results.MoodViceResult;
import learn.domain.Results.ResultType;
import learn.models.MoodVice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MoodViceService {
    private final MoodViceRepository repository;
    private final UserRepository userRepository;

    public MoodViceService(MoodViceRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }
    public List<MoodVice> findAll() {
        return repository.findAll();
    }
    public MoodVice findByMoodViceId(int moodViceId){
        return repository.findByMoodViceId(moodViceId);
    }
    public List<MoodVice> findByUserId(int userId){
        return repository.findByUserId(userId);
    }

    public MoodViceResult findByMoodType(int moodType) {
        MoodViceResult result = new MoodViceResult();
        List<MoodVice> moodVices = repository.findByMoodType(moodType);
        if (moodVices == null) {
            result.addErrorMessage("No MoodVices found for that MoodType.");
            result.setResultType(ResultType.NOT_FOUND);
        } else {
            result.setMoodVices(moodVices);
        }
        return result;
    }
    public MoodViceResult create(MoodVice moodVice) {
        MoodViceResult result = validate(moodVice);

        if (moodVice != null && moodVice.getMoodViceId() > 0) {
            result.addErrorMessage("MoodVice `id` should not be set.");
        }
        if (result.isSuccess()) {
            moodVice = repository.create(moodVice);
            result.setMoodVice(moodVice);
        }
        return result;
    }
    public MoodViceResult update(MoodVice moodVice){
        MoodViceResult result = validate(moodVice);
        if(moodVice == null || moodVice.getMoodViceId() <0){
            result.addErrorMessage("MoodVice does not exist");
            result.setResultType(ResultType.NOT_FOUND);
        }
        if(userRepository.findByUserId(moodVice.getUserId()) == null || moodVice.getUserId() <= 0){
            result.addErrorMessage("User does not exist");
            result.setResultType(ResultType.NOT_FOUND);
        }
        if(result.isSuccess()){
            boolean success = repository.update(moodVice);
            if(success){
                result.setMoodVice(moodVice);
            }else {
                result.addErrorMessage("Could not update");
                result.setResultType(ResultType.INVALID);
                return result;
            }
        }
        return result;
    }
    public MoodViceResult deleteById(int moodViceId){
        MoodViceResult result = new MoodViceResult();
        boolean success = repository.deleteById(moodViceId);
        if(!success){
            result.addErrorMessage("Not found for Deletion");
            result.setResultType(ResultType.NOT_FOUND);
            return result;
        }
        return result;
    }

    public MoodViceResult validate(MoodVice moodVice) {
        MoodViceResult result = new MoodViceResult();
        if (moodVice == null) {
            result.addErrorMessage("MoodVice cannot be null.");
            result.setResultType(ResultType.INVALID);
            return result;
        }
        if (moodVice.getMoodId() <= 0) {
            result.addErrorMessage("MoodType is required.");
            result.setResultType(ResultType.INVALID);
        }
        if (moodVice.getBody().isEmpty()) {
            result.addErrorMessage("Body is required");
            result.setResultType(ResultType.INVALID);
        }
        if(moodVice.getTitle().isEmpty()) {
            result.addErrorMessage("Title is Required");
            result.setResultType(ResultType.INVALID);
        }
        return result;
    }
}
