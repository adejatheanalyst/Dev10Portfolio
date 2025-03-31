public class Main {
    public static void main(String[] args) {
        SoccerPlayer captain = new SoccerPlayer(10, "Carli", "Lloyd", "FW");
        SoccerPlayer goalKeeper = new SoccerPlayer(1, "Alyssa", "Naeher", "GK");
        System.out.printf("%-2s: %s, %s%n", captain.getNumber(), captain.getLastName(), captain.getPosition());
        System.out.printf("%-2s: %s, %s%n", goalKeeper.getNumber(), goalKeeper.getLastName(), goalKeeper.getPosition());

    }
}
