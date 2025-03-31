package learn.data;

import learn.data.mappers.MediaMapper;
import learn.data.mappers.MediaRepository;
import learn.models.Media;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class MediaClientRepository implements MediaRepository {
private final JdbcClient client;
private final String selectAll = """
            select mediaId, moodId, mediaType, contentUrl, description
            from media
            """;
    public MediaClientRepository(JdbcClient client) {
        this.client = client;
    }

    @Override
    public List<Media> findByMoodId(int moodId) {
        return client.sql(selectAll + "where moodId = ?")
                .param(moodId)
                .query(new MediaMapper())
                .list();
    }

    @Override
    public Media findById(int mediaId) {
        return client.sql(selectAll + "where mediaId = ?")
                .param(mediaId)
                .query(new MediaMapper())
                .optional()
                .orElse(null);
    }

}
