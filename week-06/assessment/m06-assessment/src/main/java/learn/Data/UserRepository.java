package learn.Data;

import learn.Model.User;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserRepository {
    User findById(Integer user_id) throws DataAccessException;
    List<User> findAllUsers() throws DataAccessException;
    User findByEmail (String email) throws DataAccessException;

}
