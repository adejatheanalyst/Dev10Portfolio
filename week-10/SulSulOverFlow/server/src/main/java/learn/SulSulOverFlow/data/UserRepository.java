package learn.SulSulOverFlow.data;

import learn.SulSulOverFlow.models.User;

import java.util.List;

public interface UserRepository {
     User create(User user);
     User findByUsername(String username);
     User findById(int userId);
     List<User> findAll();
}
