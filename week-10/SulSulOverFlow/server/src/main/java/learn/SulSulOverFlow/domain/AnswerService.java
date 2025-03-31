package learn.SulSulOverFlow.domain;

import learn.SulSulOverFlow.data.AnswerRepository;
import learn.SulSulOverFlow.data.QuestionRepository;
import learn.SulSulOverFlow.data.UserRepository;
import learn.SulSulOverFlow.models.Answers;
import learn.SulSulOverFlow.models.Questions;
import learn.SulSulOverFlow.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    private final QuestionRepository QuestionRepository;
    private final UserRepository userRepository;
    private final AnswerRepository repository;

    public AnswerService(learn.SulSulOverFlow.data.QuestionRepository questionRepository, UserRepository userRepository, AnswerRepository repository) {
        QuestionRepository = questionRepository;
        this.userRepository = userRepository;
        this.repository = repository;
    }
    public List<Answers> findAll() {
        return repository.findAll();
    }

    public AnswerResult findById(int id) {
        AnswerResult result = new AnswerResult();
        if(id <= 0) {
            result.addErrorMessage("AnswerId must be greater than 0.", ResultType.INVALID);
            result.setResultType(ResultType.INVALID);
        }
        Answers found = repository.findById(id);
        if(found == null) {
            result.addErrorMessage("Answer not found.", ResultType.NOT_FOUND);
            result.setResultType(ResultType.NOT_FOUND);

        }else {
            result.setAnswers(found);
        }
        return result;
    }
    public List<Answers> findByQuestionId(int questionId) {
        return repository.findByQuestionId(questionId);
    }
    public List<Answers> findByUserId(int userId) {
        return repository.findByUserId(userId);
    }
    public AnswerResult create(Answers answer) {
        AnswerResult result = validate(answer);
        if (!result.isSuccess()) {
            return result;
        }
        if (answer.getAnswerId() != 0) {
            result.addErrorMessage("AnswerId cannot be set for `add` operation.", ResultType.INVALID);
            result.setResultType(ResultType.INVALID);
            return result;
        }
        answer = repository.create(answer);
        result.setAnswers(answer);
        return result;
    }
    public AnswerResult update(Answers answer) {
        AnswerResult result = validate(answer);
        if (!result.isSuccess()) {
            return result;
        }
        if (answer.getAnswerId() <= 0) {
            result.addErrorMessage("AnswerId must be set", ResultType.INVALID);
            result.setResultType(ResultType.INVALID);
        }
        if (!repository.update(answer)) {
            result.addErrorMessage("Answer not found.", ResultType.NOT_FOUND);
            result.setResultType(ResultType.NOT_FOUND);
        }
        result.setAnswers(answer);
        return result;
    }
    public AnswerResult deleteById(int answerId) {
        AnswerResult result = new AnswerResult();
        if (!repository.deleteById(answerId)) {
            result.addErrorMessage("Answer not found.", ResultType.NOT_FOUND);
            result.setResultType(ResultType.NOT_FOUND);
            return result;
        }
        return result;
    }
    private AnswerResult validate(Answers answer) {
        AnswerResult result = new AnswerResult();
        if (answer == null) {
            result.addErrorMessage("Answer cannot be null.", ResultType.INVALID);
            result.setResultType(ResultType.INVALID);

        }
        if(answer.getBody() == null || answer.getBody().isBlank()) {
            result.addErrorMessage("Answer body is required.", ResultType.INVALID);
            result.setResultType(ResultType.INVALID);

        }
        User user = userRepository.findById(answer.getUserId());
        if (user == null) {
            result.addErrorMessage("Answer must be associated with a user.", ResultType.NOT_FOUND);
            result.setResultType(ResultType.NOT_FOUND);

        }
        Questions question = QuestionRepository.findById(answer.getQuestionId());
        if (question == null) {
            result.addErrorMessage("Answer must be associated with a question.",ResultType.NOT_FOUND);
            result.setResultType(ResultType.NOT_FOUND);

        }
        if(result.isSuccess()) {
            Answers existing = repository.findById(answer.getAnswerId());
            if (existing != null && existing.getBody().equals(answer.getBody())) {
                result.addErrorMessage("Your answer is a duplicate.", ResultType.INVALID);
                result.setResultType(ResultType.INVALID);
            }
        }
        result.setUserId(user.getUserId());
        result.setQuestionId(question.getQuestionId());
        return result;
    }
}
