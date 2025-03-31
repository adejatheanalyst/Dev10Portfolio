package learn.SulSulOverFlow.data;

import learn.SulSulOverFlow.data.Mappers.UserMapper;
import learn.SulSulOverFlow.models.User;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserJdbcClientRepository implements UserRepository {
    private final JdbcClient client;

    public UserJdbcClientRepository(JdbcClient client) {
        this.client = client;
    }


    @Override
    public User create(User user) {
       final String sql = "insert into users (username, password) values (:username, :password)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowAffected = client.sql(sql)
                .param("username", user.getUsername())
                .param("password", user.getPassword())
                .update(keyHolder, "user_id");
        if (rowAffected <= 0 ){
            return null;
        }
        user.setUserId(keyHolder.getKey().intValue());
        return user;
    }

    @Override
    public User findByUsername(String username) {
        final String sql = "select * from users where username = ?";
        return client.sql(sql)
                .param(username)
                .query(new UserMapper())
//                 .stream()
//                 .findFirst()
                .optional().orElse(null);
    }


    @Override
    public User findById(int userId) {
        final String sql = "select * from users where user_Id = ?";
        return client.sql(sql)
                .param(userId)
                .query(new UserMapper())
                .optional().orElse(null);
    }

    @Override
    public List<User> findAll() {
        final String sql = "select * from users";
        return client.sql(sql)
                .query(new UserMapper())
                .list();
    }
}
