package learn.SulSulOverFlow.data;

import learn.SulSulOverFlow.domain.Result;
import learn.SulSulOverFlow.models.Answers;

import java.util.List;

public interface AnswerRepository {
    Answers create(Answers answers);
    Answers findById(int answerId);
    boolean update(Answers answers);
    boolean deleteById(int answerId);
    List<Answers> findAll();
    List<Answers> findByUserId(int userId);
    List<Answers> findByQuestionId(int questionId);
}
