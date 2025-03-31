package learn.SulSulOverFlow.data;

import learn.SulSulOverFlow.data.Mappers.AnswerMapper;
import learn.SulSulOverFlow.domain.Result;
import learn.SulSulOverFlow.models.Answers;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class AnswerJdbcClientRepository implements AnswerRepository {
    private final JdbcClient client;

    public AnswerJdbcClientRepository(JdbcClient client) {
        this.client = client;
    }
    private final String selectAll = """
            select answer_id, user_id, question_id, body
            from answers
            """;

    @Override
    public Answers create(Answers answers) {
        final String sql = "insert into answers (user_id, question_id, body) values (:user_id, :question_id, :body)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowAffected = client.sql(sql)
                .param("user_id", answers.getUserId())
                .param("question_id", answers.getQuestionId())
                .param("body", answers.getBody())
                .update(keyHolder, "answer_id");
        if (rowAffected <= 0) {
            return null;
        }
        answers.setAnswerId(keyHolder.getKey().intValue());
        return answers;
    }

    @Override
    public Answers findById(int answerId) {
        final String sql = selectAll + "where answer_id = ?";
        return client.sql(sql)
                .param(answerId)
                .query(new AnswerMapper())
                .optional().orElse(null);
    }

    @Override
    public boolean update(Answers answers) {
        final String sql = "update answers set body = :body where answer_id = :answer_id";
        return client.sql(sql)
                .param("answer_id", answers.getAnswerId())
                .param("body", answers.getBody())
                .update() > 0;
    }

    @Override
    public boolean deleteById(int answerId) {
        final String sql = "delete from answers where answer_id = ?";
        return client.sql(sql)
                .param(answerId)
                .update() > 0;
    }

    @Override
    public List<Answers> findAll() {
        return client.sql(selectAll + ";")
                .query(new AnswerMapper())
                .list();
    }

    @Override
    public List<Answers> findByUserId(int userId) {
        final String sql = selectAll + "where user_id = ?";
        return client.sql(sql)
                .param(userId)
                .query(new AnswerMapper())
                .list();
    }

    @Override
    public List<Answers> findByQuestionId(int questionId) {
        final String sql = selectAll + "where question_id = ?";
        return client.sql(sql)
                .param(questionId)
                .query(new AnswerMapper())
                .list();
    }
}
