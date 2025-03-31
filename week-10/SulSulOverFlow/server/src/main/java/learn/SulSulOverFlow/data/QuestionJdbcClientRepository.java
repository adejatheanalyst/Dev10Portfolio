package learn.SulSulOverFlow.data;

import learn.SulSulOverFlow.data.Mappers.QuestionMapper;
import learn.SulSulOverFlow.models.Questions;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class QuestionJdbcClientRepository implements QuestionRepository {
    private final JdbcClient client;

    public QuestionJdbcClientRepository(JdbcClient client) {
        this.client = client;
    }
    private final String selectAll = """
            select question_id, user_id, title, body
            from questions
            """;

    @Override
    public Questions create(Questions question) {
        final String sql = "insert into questions (user_id, title, body) values (:user_id, :title, :body)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowAffected = client.sql(sql)
                .param("user_id", question.getUserId())
                .param("title", question.getTitle())
                .param("body", question.getBody())
                .update(keyHolder, "question_id");
        if (rowAffected <= 0) {
            return null;
        }
        question.setQuestionId(keyHolder.getKey().intValue());
        return question;
    }

    @Override
    public Questions findById(int questionId) {
       final String sql = selectAll + "where question_id = ?";
        return client.sql(sql)
                .param(questionId)
                .query(new QuestionMapper())
                .optional().orElse(null);
    }

    @Override
    public boolean update(Questions question) {
        final String sql = "update questions set title = :title, body = :body where question_id = :question_id";
        return client.sql(sql)
                .param("question_id", question.getQuestionId())
                .param("title", question.getTitle())
                .param("body", question.getBody())
                .update() > 0;
    }

    @Override
    public boolean deleteById(int questionId) {
        final String sql = "delete from questions where question_id = ?";
        return client.sql(sql)
                .param(questionId)
                .update() > 0;
    }

    @Override
    public List<Questions> findAll() {
        return client.sql(selectAll)
                .query(new QuestionMapper())
                .list();
    }

    @Override
    public List<Questions> findByUserId(int userId) {
        final String sql = selectAll + "where user_id = ?";
        return client.sql(sql)
                .param(userId)
                .query(new QuestionMapper())
                .list();
    }
}
