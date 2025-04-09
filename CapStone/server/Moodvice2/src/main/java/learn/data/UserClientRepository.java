package learn.data;
import learn.data.mappers.UserMapper;
import learn.models.User;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
@Repository
public class UserClientRepository implements UserRepository {
    private final JdbcClient jdbcClient;

    public UserClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
    @Override
    public User create(User user) {
        final String sql = "insert into users (profile_image_url, first_name, last_name, username, email, password) values(:profile_image_url, :first_name, :last_name, :username, :email, :password)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcClient.sql(sql)
                .param("profile_image_url", user.getProfile_picture())
                .param("first_name", user.getFirst_name())
                .param("last_name", user.getLast_name())
                .param("username", user.getUsername())
                .param("email", user.getEmail())
                .param("password", user.getPassword())
                .update(keyHolder, "userId");
        if(rowsAffected <= 0) {
            return null;
        }
        user.setUserId(keyHolder.getKey().intValue());
        return user;
    }

    @Override
    public User findByUsername(String username) {
        final String sql = "select * from users where username = ?";
        return jdbcClient.sql(sql)
                .param(username)
                .query(new UserMapper())
                .optional()
                .orElse(null);
    }
    @Override
    public boolean update(User user) {
        final String sql = "update users set username = :username, first_name = :first_name, last_name = :last_name, email = :email, password = :password, profile_image_url = :profile_image_url, where userId = :userId";
        return jdbcClient.sql(sql)
                .param("username", user.getUsername())
                .param("email", user.getEmail())
                .param("password", user.getPassword())
                .param("userId", user.getUserId())
                .update() > 0;
    }

    @Override
    public boolean deleteById(int userId) {
        final String sql = "delete from users where userId = ?";
        return jdbcClient.sql(sql)
                .param(userId)
                .update() > 0;
    }

    @Override
    public User findByUserId(int userId) {
        final String sql = "select * from users where userId = ?";
        return jdbcClient.sql(sql)
                .param(userId)
                .query(new UserMapper())
                .optional()
                .orElse(null);
    }
    public boolean updateProfileImg(int userId, String profileImgUrl) {
        final String sql = "update users set profile_image_url = ? where userId = ?";
        return jdbcClient.sql(sql)
                .param(profileImgUrl)
                .param(userId)
                .update() > 0;
    }
    public String getUserProfileImg(int userId) {
        final String sql = "select profile_image_url from users where userId = ?";
        return jdbcClient.sql(sql)
                .param(userId)
                .query((resultSet) -> {
                    if(resultSet.next()) {
                        return resultSet.getString("profile_image_url");
                    }
                    return null;
                });
    }
}
