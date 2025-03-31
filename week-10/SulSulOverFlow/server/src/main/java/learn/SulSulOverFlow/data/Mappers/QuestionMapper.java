package learn.SulSulOverFlow.data.Mappers;

import learn.SulSulOverFlow.models.Questions;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionMapper implements RowMapper<Questions> {
    @Override
    public Questions mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Questions question = new Questions();
        question.setQuestionId(resultSet.getInt("question_id"));
        question.setTitle(resultSet.getString("title"));
        question.setBody(resultSet.getString("body"));
        question.setUserId(resultSet.getInt("user_id"));
        return question;
    }
}
