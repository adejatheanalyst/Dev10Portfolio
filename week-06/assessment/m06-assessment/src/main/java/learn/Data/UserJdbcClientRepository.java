package learn.Data;

import learn.Data.Mappers.UserMapper;
import learn.Model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

public class UserJdbcClientRepository implements UserRepository {
    private final JdbcClient jdbcClient;
    final String selectAll = "select user_id, first_name, last_name, email, phone from user";

    public UserJdbcClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public User findById(Integer user_id) throws DataAccessException {
        final String sql = selectAll + " where user_id = ?;";
        return jdbcClient.sql(sql)
                .param(user_id)
                .query(new UserMapper())
                .optional().orElse(null);
    }

    @Override
    public List<User> findAllUsers() throws DataAccessException {
        final String sql = selectAll ;
        return jdbcClient.sql(sql)
                .query(new UserMapper())
                .list();
    }
    @Override
    public User findByEmail(String email) throws DataAccessException {
        final String sql = selectAll + " where email = ?";
        return jdbcClient.sql(sql)
                .param(email)
                .query(new UserMapper())
                .optional().orElse(null);
    }
}
