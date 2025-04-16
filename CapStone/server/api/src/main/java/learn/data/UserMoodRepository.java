package learn.data;

import learn.models.User;
import learn.models.UserMoods;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface UserMoodRepository {
    UserMoods create(UserMoods userMood);
    List<UserMoods> findByMoodId(int userMoodId);
    List<UserMoods> findUserMoodsByMonth(int moodId, int month);
    List<UserMoods> findByUserId(int userId);
    List<UserMoods> findAll();
    List<UserMoods> findByDay(LocalDate date, int userId);

}







