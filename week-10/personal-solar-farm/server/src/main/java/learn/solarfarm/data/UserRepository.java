package learn.solarfarm.data;

import learn.solarfarm.models.User;

public interface UserRepository {
    public User create(User user);
    public User findByUsername(String username);
    public User findById(int userId);
}
