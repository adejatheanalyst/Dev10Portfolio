package learn.models;

public class Resources {
    private int resourceId;
    private String title;
    private String resourceUrl;
    private int moodId;

    public Resources(int resourceId, String title, String resourceUrl, int moodId) {
        this.resourceId = resourceId;
        this.title = title;
        this.resourceUrl = resourceUrl;
        this.moodId = moodId;
    }

    public Resources() {
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public int getMoodId() {
        return moodId;
    }

    public void setMoodId(int moodId) {
        this.moodId = moodId;
    }
}
