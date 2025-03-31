package learn.Domain;

import learn.Model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserResult<U> {
    private static ArrayList<String> messages = new ArrayList<String>();
    private User user;
    private int user_id;
    private String message;

    public List<String> getErrorMessages() {
        return new ArrayList<>(messages);
    }

    public static void addErrorMessage(String message) {
        messages.add(message);
    }
    public boolean isSuccess() {
        return messages.size() == 0;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserResult<?> that = (UserResult<?>) o;
        return user_id == that.user_id && Objects.equals(user, that.user) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, user_id, message);
    }
}

