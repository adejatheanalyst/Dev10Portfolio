package learn.SulSulOverFlow.domain;

import learn.SulSulOverFlow.data.QuestionRepository;
import learn.SulSulOverFlow.data.UserRepository;
import learn.SulSulOverFlow.models.Questions;
import learn.SulSulOverFlow.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository repository;
    private final UserRepository userRepository;

    public QuestionService(QuestionRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public List<Questions> findAll() {
        return repository.findAll();
    }

    public QuestionResult findById(int id) {
        QuestionResult result = new QuestionResult();
        Questions question = repository.findById(id);
        if (question == null) {
            result.addErrorMessage("Question not found.", ResultType.NOT_FOUND);
            result.setResultType(ResultType.NOT_FOUND);
        } else {
            result.setQuestions(question);
        }
        return result;
    }

    public List<Questions> findByUserId(int userId) {
        return repository.findByUserId(userId);
    }

    public QuestionResult create(Questions question) {
        QuestionResult result = validate(question);

        if (question != null && question.getQuestionId() > 0) {
            result.addErrorMessage("Question `id` should not be set.", ResultType.INVALID);
        }
        if(userRepository.findById(question.getUserId()) == null) {
            result.addErrorMessage("User does not exist.", ResultType.INVALID);
            result.setResultType(ResultType.INVALID);
        }
        if (result.isSuccess()) {
            question = repository.create(question);
            result.setQuestions(question);

        }
        return result;
    }

    public QuestionResult update(Questions question) {
        QuestionResult  result = validate(question);

        if (question == null || question.getQuestionId() <= 0) {
            result.addErrorMessage("Question `id` is required.", ResultType.INVALID);
            result.setResultType(ResultType.INVALID);
            return result;
        }
        if (result.isSuccess()) {
            boolean success = repository.update(question);
            if (success) {
                result.setQuestions(question);
            } else  {
                result.addErrorMessage("Question not found.", ResultType.NOT_FOUND);
                result.setResultType(ResultType.NOT_FOUND);
                return result;
            }
        }
        return result;
    }

    public QuestionResult deleteById(int id) {
        QuestionResult result = new QuestionResult();
        boolean success = repository.deleteById(id);
        if(!success) {
            result.addErrorMessage("Question not found.", ResultType.NOT_FOUND);
            result.setResultType(ResultType.NOT_FOUND);
            return result;
        }
        return result;
    }

    private QuestionResult  validate(Questions question) {
        QuestionResult  result = new QuestionResult();

        if (question == null) {
            result.addErrorMessage("Question cannot be null.", ResultType.INVALID);
            result.setResultType(ResultType.INVALID);
        }

        if (question.getTitle() == null || question.getTitle().isBlank()) {
            result.addErrorMessage("Question `title` is required.", ResultType.INVALID);
            result.setResultType(ResultType.INVALID);

        }

        if (question.getBody() == null || question.getBody().isBlank()) {
            result.addErrorMessage("Question `body` is required.", ResultType.INVALID);
            result.setResultType(ResultType.INVALID);
        }
//        if(user == null) {
//            result.addErrorMessage("User does not exist.", ResultType.INVALID);
//            result.setResultType(ResultType.INVALID);
//        }
        return result;
    }

}
