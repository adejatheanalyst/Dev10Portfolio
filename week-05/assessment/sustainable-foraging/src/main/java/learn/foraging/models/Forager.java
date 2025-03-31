package learn.foraging.models;

import java.util.Objects;

public class Forager {

    private int id;
    private String firstName;
    private String lastName;
    private String state;

    public Forager() {
    }

    public Forager(int id, String firstName, String lastName, String state) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Forager forager = (Forager) o;

        if (id != forager.id) return false;
        if (!Objects.equals(firstName, forager.firstName)) return false;
        if (!Objects.equals(lastName, forager.lastName)) return false;
        return Objects.equals(state, forager.state);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}
