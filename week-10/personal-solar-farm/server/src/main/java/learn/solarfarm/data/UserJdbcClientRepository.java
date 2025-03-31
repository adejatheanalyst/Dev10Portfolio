package learn.solarfarm.data;

import learn.solarfarm.models.User;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UserJdbcClientRepository implements UserRepository{

    private final JdbcClient jdbcClient;

    public UserJdbcClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public User create(User user) {
        final String sql = "insert into `user`(username, password) values(:username, :password)";

        KeyHolder keyHolder =  new GeneratedKeyHolder();
        int rowsAffected = jdbcClient.sql(sql)
                .param("username", user.getUsername())
                .param("password", user.getPassword())
                .update(keyHolder, "userId");

        if (rowsAffected <= 0) {
            return null;
        }
        user.setUserId(keyHolder.getKey().intValue());
        return user;
    }

    @Override
    public User findByUsername(String username) {
       final String sql = "select * from `user` where username = ?";
         return jdbcClient.sql(sql)
                 .param(username)
                 .query(new UserMapper())
//                 .stream()
//                 .findFirst()
                 .optional().orElse(null);
    }

    @Override
    public User findById(int userId) {
        final String sql = "select * from `user` where user_Id = ?";
        return jdbcClient.sql(sql)
                .param(userId)
                .query(new UserMapper())
                .optional().orElse(null);
    }
}
