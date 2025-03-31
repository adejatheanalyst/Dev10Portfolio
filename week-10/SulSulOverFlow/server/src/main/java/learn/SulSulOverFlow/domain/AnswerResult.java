package learn.SulSulOverFlow.domain;

import learn.SulSulOverFlow.models.Answers;
import learn.SulSulOverFlow.models.Questions;
import learn.SulSulOverFlow.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AnswerResult {
    private final ArrayList<String> messages = new ArrayList<>();
    private ResultType resultType = ResultType.SUCCESS;
    private Answers answers;;
    private int questionId;
    private int userId;

    public AnswerResult(Answers answers) {
    }
    public AnswerResult() {
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public List<String> getErrorMessages() {
        return new ArrayList<>(messages);
    }
    public void addErrorMessage(String message) {
        this.resultType = ResultType.INVALID;
        messages.add(message);
    }

    public void addErrorMessage(String format, Object... args) {
        this.resultType = resultType;
        messages.add(String.format(format, args));

    }
    public boolean isSuccess() {
        // If result type is success, the operation was successful;
        return this.resultType == ResultType.SUCCESS;
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

    public Answers getAnswers() {
        return answers;
    }

    public void setAnswers(Answers answers) {
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AnswerResult that = (AnswerResult) o;
        return questionId == that.questionId && userId == that.userId && Objects.equals(messages, that.messages) && resultType == that.resultType && Objects.equals(answers, that.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messages, resultType, answers, questionId, userId);
    }

    @Override
    public String toString() {
        return "AnswerResult{" +
                "messages=" + messages +
                ", resultType=" + resultType +
                ", answers=" + answers +
                ", questionId=" + questionId +
                ", userId=" + userId +
                '}';
    }
}
