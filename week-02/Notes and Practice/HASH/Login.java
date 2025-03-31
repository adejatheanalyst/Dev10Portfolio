import java.util.Objects;

public class Login {
    private final String emailAddress;
    private final String firstName;
    private final String lastName;

    public Login(String emailAddress, String firstName, String lastName) {
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Login login = (Login) o;
        return Objects.equals(emailAddress, login.emailAddress) && Objects.equals(firstName, login.firstName) && Objects.equals(lastName, login.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress, firstName, lastName);
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
