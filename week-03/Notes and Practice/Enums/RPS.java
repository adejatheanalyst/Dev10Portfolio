import java.util.Scanner;

public class RPS {
    public static void main(String[] args) {
        Move p1Move = chooseMove("Player 1");
        switch (p1Move) {
            case ROCK:
                System.out.println("You chose rock.");
                System.out.println("Rock beats scissors.");
                break;
            case PAPER:
                System.out.println("You chose paper.");
                System.out.println("Paper beats rock.");
                break;
            case SCISSORS:
                System.out.println("You chose scissors.");
                System.out.println("Scissors beats paper.");
                break;
        }
        Move p2Move = chooseMove("Player 2");
        switch (p2Move) {
            case ROCK:
                System.out.println("You chose rock.");
                System.out.println("Rock beats scissors.");
                break;
            case PAPER:
                System.out.println("You chose paper.");
                System.out.println("Paper beats rock.");
                break;
            case SCISSORS:
                System.out.println("You chose scissors.");
                System.out.println("Scissors beats paper.");
                break;
        }
        System.out.printf("Player 1 chose: %s. Player 2 chose: %s.%n", p1Move, p2Move);
    }

    static Move chooseMove(String playerName) {

        Scanner console = new Scanner(System.in);
        Move result = Move.ROCK;
        boolean isValid = false;

        do {
            System.out.printf("%s, choose your move [Rock/Paper/Scissors]: ", playerName);
            try {
                result = Move.valueOf(console.nextLine().toUpperCase());
                isValid = true;
            } catch (IllegalArgumentException ex) {
                System.out.println("Invalid move.");
            }
        } while (!isValid);

        return result;
    }

}
