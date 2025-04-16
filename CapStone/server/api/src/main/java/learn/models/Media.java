package learn.models;

import java.util.Objects;

public class Media {
    private int mediaId;
    private int moodId;
    private String mediaType;
    private String contentUrl;
    private String description;

    public Media(int mediaId, int moodId, String mediaType, String contentUrl, String description) {
        this.mediaId = mediaId;
        this.moodId = moodId;
        this.mediaType = mediaType;
        this.contentUrl = contentUrl;
        this.description = description;
    }

    public Media() {
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

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Media media = (Media) o;
        return mediaId == media.mediaId && moodId == media.moodId && Objects.equals(mediaType, media.mediaType) && Objects.equals(contentUrl, media.contentUrl) && Objects.equals(description, media.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mediaId, moodId, mediaType, contentUrl, description);
    }

    @Override
    public String toString() {
        return "Media{" +
                "mediaId=" + mediaId +
                ", moodId=" + moodId +
                ", mediaType='" + mediaType + '\'' +
                ", contentUrl='" + contentUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
