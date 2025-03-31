package learn.Data;

import learn.Model.User;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static learn.TestData.*;

public class UserRepositoryDouble implements UserRepository{

    ArrayList<User> users = new ArrayList<User>();
    public UserRepositoryDouble(){
        users.add(user);
        users.add(user2);
        users.add(user3);
    }

    @Override
    public User findById(Integer user_id) throws DataAccessException {
        return users.stream()
                .filter(i -> i.getUser_id() == user_id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> findAllUsers() throws DataAccessException {
        return users;
    }

    @Override
    public User findByEmail(String email) throws DataAccessException {
        return users.stream()
                .filter(i -> i.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

}
