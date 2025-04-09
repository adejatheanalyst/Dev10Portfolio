package learn.data;

import learn.models.User;

public interface UserRepository {
    public User create(User user);
    public User findByUsername(String username);
    public boolean update(User user);
    public boolean deleteById(int userId);
    public User findByUserId(int userId);
    public String getUserProfileImg(int userId);
    public boolean updateProfileImg(int userId, String profileImgUrl);

}
