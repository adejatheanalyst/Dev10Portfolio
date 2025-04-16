package learn.domain;

import learn.data.mappers.MediaRepository;
import learn.domain.Results.MediaResult;
import learn.domain.Results.ResultType;
import learn.models.Media;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaService {
    private final MediaRepository repository;

    public MediaService(MediaRepository repository) {
        this.repository = repository;
    }

    public MediaResult findByMoodId(int moodId) {
        MediaResult result = new MediaResult();
        List<Media> media = repository.findByMoodId(moodId);
        if (media == null) {
            result.addErrorMessage("No media found for that mood.");
            result.setResultType(ResultType.NOT_FOUND);
        } else {
            result.setMediaList(media);
        }
        return result;

    }

    public MediaResult getRandomMediaByMoodId(int moodId) {
        MediaResult result = new MediaResult();
        List<Media> media = repository.findByMoodId(moodId);
        if (media.isEmpty()) {
            result.addErrorMessage("No media found for that mood.");
            result.setResultType(ResultType.NOT_FOUND);
        } else {
            int randomIndex = (int) (Math.random() * media.size());
            result.setMediaList(List.of(media.get(randomIndex)));
        }
        return result;
    }


    public MediaResult findByMediaId(int mediaId) {
        MediaResult result = new MediaResult();
        Media media = repository.findById(mediaId);
        if (media == null) {
            result.addErrorMessage("Media not found.");
            result.setResultType(ResultType.NOT_FOUND);
        } else {
            result.setMedia(media);
        }
        return result;
    }
}
