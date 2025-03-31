package learn.SulSulOverFlow.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Questions {
    private int questionId;
    private String title;
    private String body;
    private int userId;
//    private LocalDate datePosted;


    public Questions(int questionId, String title, String body, int userId) {
        this.questionId = questionId;
        this.title = title;
        this.body = body;
        this.userId = userId;
    }

    public Questions() {
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

//    public LocalDate getDatePosted() {
//        return datePosted;
//    }
//
//    public void setDatePosted(LocalDate datePosted) {
//        this.datePosted = datePosted;
//    }


    @Override
    public String toString() {
        return "Questions{" +
                "questionId=" + questionId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Questions questions = (Questions) o;
        return questionId == questions.questionId && userId == questions.userId && Objects.equals(title, questions.title) && Objects.equals(body, questions.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, title, body, userId);
    }
}
