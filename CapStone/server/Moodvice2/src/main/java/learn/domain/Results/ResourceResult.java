package learn.domain.Results;

import learn.models.MoodVice;
import learn.models.Resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResourceResult {
    private final ArrayList<String> messages = new ArrayList<>();
    private ResultType resultType = ResultType.SUCCESS;
    private int moodId;
    List<Resources> resourcesList;
    Resources resources;

    public ResourceResult() {
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

    public int getMoodId() {
        return moodId;
    }

    public void setMoodId(int moodId) {
        this.moodId = moodId;
    }

    public List<Resources> getResourcesList() {
        return resourcesList;
    }

    public void setResourcesList(List<Resources> resourcesList) {
        this.resourcesList = resourcesList;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public void addErrorMessage(String message) {
        this.resultType = ResultType.INVALID;
        messages.add(message);
    }
    public void addErrorMessage(String format, Object... args) {
        this.resultType = resultType;
        messages.add(String.format(format, args));
    }
    public List<String> getErrorMessages() {
        return new ArrayList<>(messages);
    }
    public boolean isSuccess() {
        return this.resultType == ResultType.SUCCESS;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ResourceResult that = (ResourceResult) o;
        return moodId == that.moodId && Objects.equals(messages, that.messages) && resultType == that.resultType && Objects.equals(resourcesList, that.resourcesList) && Objects.equals(resources, that.resources);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messages, resultType, moodId, resourcesList, resources);
    }

    @Override
    public String toString() {
        return "ResourceResult{" +
                "messages=" + messages +
                ", resultType=" + resultType +
                ", moodId=" + moodId +
                ", resourcesList=" + resourcesList +
                ", resources=" + resources +
                '}';
    }
}
