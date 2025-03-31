package learn.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class User {
    private int userId;
    private String username;
    private String email;
    private String password;
    private LocalDate created_at;

    public User(int userId, String username, String email, String password, LocalDate created_at) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
    }

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User users = (User) o;
        return userId == users.userId && Objects.equals(username, users.username) && Objects.equals(email, users.email) && Objects.equals(password, users.password) && Objects.equals(created_at, users.created_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, email, password, created_at);
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}
