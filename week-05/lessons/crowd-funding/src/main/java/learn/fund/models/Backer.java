package learn.fund.models;

import java.util.Objects;

public class Backer {
    private int backerId;
    private String name;
    private String emailAddress;

    public Backer() {
    }

    public Backer(int backerId, String name, String emailAddress) {
        this.backerId = backerId;
        this.name = name;
        this.emailAddress = emailAddress;
    }

    public int getBackerId() {
        return backerId;
    }

    public void setBackerId(int backerId) {
        this.backerId = backerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Backer backer = (Backer) o;

        if (backerId != backer.backerId) return false;
        if (!Objects.equals(name, backer.name)) return false;
        return Objects.equals(emailAddress, backer.emailAddress);
    }

    @Override
    public int hashCode() {
        int result = backerId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
        return result;
    }
}
