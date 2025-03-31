package learn.data;

import learn.models.MoodType;
import learn.models.MoodVice;

import java.util.List;

public interface MoodViceRepository {
    List<MoodVice> findAll();
    List<MoodVice> findByMoodType(int moodTypeId);
    MoodVice findByMoodViceId(int moodViceId);
    List<MoodVice> findByUserId(int userId);
    MoodVice create(MoodVice moodVice);
    boolean update(MoodVice moodVice);
    boolean deleteById(int moodViceId);
}
