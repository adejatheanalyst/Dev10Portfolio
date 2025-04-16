package learn.domain;

import learn.data.MoodViceRepository;
import learn.data.ReplyRepository;
import learn.data.UserRepository;
import learn.domain.Results.ReplyResult;
import learn.domain.Results.ResultType;
import learn.models.Reply;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {
    private final ReplyRepository repository;
    private final MoodViceRepository moodViceRepository;
    private final UserRepository userRepository;

    public ReplyService(ReplyRepository repository, MoodViceRepository moodViceRepository, UserRepository userRepository) {
        this.repository = repository;
        this.moodViceRepository = moodViceRepository;
        this.userRepository = userRepository;
    }
public Reply findByKey(int replyId, int userId, int moodViceId){
        return repository.findByKey(replyId, userId, moodViceId);
}
public ReplyResult findById(int replyId) {
    ReplyResult result = new ReplyResult();
    Reply reply = repository.findByReplyId(replyId);
    if (reply == null) {
        result.addErrorMessage("No reply found");
        result.setResultType(ResultType.NOT_FOUND);
    } else {
        result.setReply(reply);
    }
    return result;
}

    public ReplyResult findByMoodViceId(int moodViceId){
        ReplyResult result = new ReplyResult();
        List<Reply> replyList = repository.findByReplyMoodViceId(moodViceId);
        if(replyList == null){
            result.addErrorMessage("No replies found");
            result.setResultType(ResultType.NOT_FOUND);
        }else {
            result.setReplyList(replyList);
        }
        return result;
    }
    public ReplyResult create(Reply reply){
        ReplyResult result = validate(reply);

        if(reply != null && reply.getReplyId() > 0){
            result.addErrorMessage("Id should not be set");
            result.setResultType(ResultType.INVALID);
        }
        if(result.isSuccess()){
            reply = repository.addReply(reply);
            result.setReply(reply);
        }
        return result;
    }
    public ReplyResult update(Reply reply){
        ReplyResult result = validate(reply);
        if(result.isSuccess()){
            boolean success = repository.updateReply(reply);
            if(success){
                result.setReply(reply);
            }else {
                result.addErrorMessage("Could not update");
                result.setResultType(ResultType.INVALID);
                return result;
            }
        }
        return result;


    }
    public ReplyResult deleteById(int replyId){
        ReplyResult result = new ReplyResult();
        boolean success = repository.deleteById(replyId);
        if(!success){
            result.addErrorMessage("Could not delete");
            result.setResultType(ResultType.INVALID);
        }
        return result;
    }

    public ReplyResult validate(Reply reply){
        ReplyResult result = new ReplyResult();
        if(reply == null){
            result.addErrorMessage("Reply cannot be null.");
            result.setResultType(ResultType.INVALID);
            return result;
        }
        if (reply.getMoodViceId() <= 0) {
            result.addErrorMessage("No MoodVice to reply to");
            result.setResultType(ResultType.INVALID);
        }
        if (reply.getMoodId() <= 0) {
            result.addErrorMessage("MoodType is required..");
            result.setResultType(ResultType.INVALID);
        }
        if (reply.getBody().isEmpty()) {
            result.addErrorMessage("Body is required");
            result.setResultType(ResultType.INVALID);
        }
        if(reply.getTitle().isEmpty()) {
            result.addErrorMessage("Title is Required");
            result.setResultType(ResultType.INVALID);
        }
        if(userRepository.findByUserId(reply.getUserId()) == null || reply.getUserId() <= 0){
            result.addErrorMessage("User does not exist");
            result.setResultType(ResultType.NOT_FOUND);
            return result;
        }
        return result;
    }
}
