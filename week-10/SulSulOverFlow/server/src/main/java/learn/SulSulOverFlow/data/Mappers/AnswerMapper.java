package learn.SulSulOverFlow.data.Mappers;

import learn.SulSulOverFlow.models.Answers;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class AnswerMapper implements RowMapper<Answers> {
    @Override
    public Answers mapRow(ResultSet rs, int rowNum) throws SQLException {

        Answers answer = new Answers();
        answer.setAnswerId(rs.getInt("answer_id"));
        answer.setBody(rs.getString("body"));
        answer.setUserId(rs.getInt("user_id"));
        answer.setQuestionId(rs.getInt("question_id"));
        return answer;
    }
}
