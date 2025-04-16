package learn.domain.Results;

import learn.models.Media;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MediaResult {
    private final ArrayList<String> messages = new ArrayList<>();
    private ResultType resultType = ResultType.SUCCESS;
    private Media media;
    List<Media> mediaList;
    private int mediaId;
    private int moodId;

    public List<String> getErrorMessages() {
        return new ArrayList<>(messages);
    }
    public boolean isSuccess() {
        return this.resultType == ResultType.SUCCESS;
    }
    public void addErrorMessage(String format, Object... args) {
        this.resultType = resultType;
        messages.add(String.format(format, args));
    }
    public void addErrorMessage(String message) {
        this.resultType = ResultType.INVALID;
        messages.add(message);
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public ResultType getResultType() {
        return resultType;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public List<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public int getMoodId() {
        return moodId;
    }

    public void setMoodId(int moodId) {
        this.moodId = moodId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MediaResult that = (MediaResult) o;
        return mediaId == that.mediaId && moodId == that.moodId && Objects.equals(messages, that.messages) && resultType == that.resultType && Objects.equals(media, that.media) && Objects.equals(mediaList, that.mediaList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messages, resultType, media, mediaList, mediaId, moodId);
    }

    @Override
    public String toString() {
        return "MediaResult{" +
                "messages=" + messages +
                ", resultType=" + resultType +
                ", media=" + media +
                ", mediaList=" + mediaList +
                ", mediaId=" + mediaId +
                ", moodId=" + moodId +
                '}';
    }
}
