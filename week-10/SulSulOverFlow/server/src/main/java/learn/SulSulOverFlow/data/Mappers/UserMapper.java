package learn.SulSulOverFlow.data.Mappers;

import learn.SulSulOverFlow.models.User;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {


  @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException{
      User user = new User();
      user.setUserId(rs.getInt("user_Id"));
      user.setUsername(rs.getString("username"));
      user.setPassword(rs.getString("password"));
      return user;
  }
}
