package learn.SulSulOverFlow.data;

import learn.SulSulOverFlow.models.Questions;

import java.util.List;

public interface QuestionRepository {
    Questions create(Questions question);
    Questions findById(int questionId);
    boolean update(Questions question);
    boolean deleteById(int questionId);
    List<Questions> findAll();
    List<Questions> findByUserId(int userId);
}
