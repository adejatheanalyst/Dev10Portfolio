package learn.data.mappers;

import learn.models.Media;

import java.util.List;

public interface MediaRepository {
    List<Media> findByMoodId(int moodId);
    Media findById(int mediaId);
}
