package learn.SulSulOverFlow.models;

import java.time.LocalDate;
import java.util.Objects;

public class Answers {
    private int answerId;
    private String body;
    private int userId;
    private int questionId;
//    private LocalDate datePosted;


//    public LocalDate getDatePosted() {
//        return datePosted;
//    }
//
//    public void setDatePosted(LocalDate datePosted) {
//        this.datePosted = datePosted;
//    }

    public Answers() {
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Answers answers = (Answers) o;
        return answerId == answers.answerId && userId == answers.userId && questionId == answers.questionId && Objects.equals(body, answers.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerId, body, userId, questionId);
    }

    public Answers(int answerId, String body, int userId, int questionId) {
        this.answerId = answerId;
        this.body = body;
        this.userId = userId;
        this.questionId = questionId;
    }


    @Override
    public String toString() {
        return "Answers{" +
                "answerId=" + answerId +
                ", body='" + body + '\'' +
                ", userId=" + userId +
                ", questionId=" + questionId +
                '}';
    }
}
