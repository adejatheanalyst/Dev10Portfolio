public class SoccerPlayer {

    private final int number;
    private final String firstName;
    private final String lastName;
    private final String position;
//constructor
    public SoccerPlayer(int number, String firstName, String lastName, String position) {
        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
    }
//getters
    public int getNumber() {
        return number;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }



}
