package learn.SulSulOverFlow.domain;

import learn.SulSulOverFlow.models.Questions;
import learn.SulSulOverFlow.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuestionResult {
    private final ArrayList<String> messages = new ArrayList<>();
    private ResultType resultType = ResultType.SUCCESS;
    private Questions questions;
    private User user;
    private int UserId;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public ResultType getResultType() {
        return resultType;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getErrorMessages() {
        return new ArrayList<>(messages);
    }
//    public void addErrorMessage(String message) {
//        this.resultType = ResultType.INVALID;
//        messages.add(message);
//    }
    public void addErrorMessage(String format, Object... args) {
        this.resultType = resultType;
        messages.add(String.format(format, args));

    }
    public boolean isSuccess() {
        return this.resultType == ResultType.SUCCESS;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    public Questions getQuestions() {
        return questions;
    }

    public void setQuestions(Questions questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        QuestionResult that = (QuestionResult) o;
        return UserId == that.UserId && Objects.equals(messages, that.messages) && resultType == that.resultType && Objects.equals(questions, that.questions) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messages, resultType, questions, user, UserId);
    }

    @Override
    public String toString() {
        return "QuestionResult{" +
                "messages=" + messages +
                ", resultType=" + resultType +
                ", questions=" + questions +
                ", user=" + user +
                ", UserId=" + UserId +
                '}';
    }
}
