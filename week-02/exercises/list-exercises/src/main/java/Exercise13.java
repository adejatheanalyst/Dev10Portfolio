import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class Exercise13 {

    public ArrayList<BoardGame> games = GameRepository.getAll();
    public Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        Exercise13 instance = new Exercise13();
        instance.run();
    }

    private void printHeader(String message) {
        System.out.println();
        System.out.println(message);
        System.out.println("=".repeat(message.length()));
    }

    private void run() {

        printHeader("Welcome to the Board Game Manager.");

        String input;
        do {
            printHeader("Menu");
            System.out.println("1. Add a board game.");
            System.out.println("2. Display all board games.");
            System.out.println("3. Remove a board game.");
            System.out.println("4. Exit");
            System.out.print("Select [1-4]: ");
            input = console.nextLine();

            switch (input) {
                case "1":
                    // 1. Create a method that gathers user input via `console` to instantiate a BoardGame
                    // and then adds it to the `games` list.

                    // 2. Replace the line of code below with a call to the method.
                    printHeader("Add a board game.");
                    addBoardGame();
                    break;
                case "2":
                    // 3. Create a method to display all board games in the `games` list.
                    // 4. Replace the line of code below with a call to the method.
                    printHeader("Display all board games.");
                    displayAllBoardGames();
                    break;
                case "3":
                    removeBoardGames();
                    // 5. Stretch Goal: Create a method that allows the user to remove a board game from the
                    // `games` list with an index.
                    // 6. Replace the line of code below with a call to the method.
                    printHeader("Remove a board game.");
                    break;
                case "4":
                    printHeader("Goodbye.");
                    break;
                default:
                    System.out.printf("%nI don't understand '%s'.%n", input);
                    break;
            }
        } while (!input.equals("4"));
    }
    public void addBoardGame(){
        System.out.print("Name of a Board Game: ");
        String name = console.nextLine();
        System.out.print("Minimum Players: ");
        int min = Integer.parseInt(console.nextLine());
        System.out.print("Maximum Players:  ");
        int max = Integer.parseInt(console.nextLine());
        System.out.print("Game Category:  ");
        String category = console.nextLine();
        BoardGame newGame = new BoardGame(name, min, max, category);
        games.add(newGame);
        System.out.println("Board Game was added Successfully.");
    }
    public void displayAllBoardGames(){
        for (BoardGame boardGame : games) {
            System.out.printf("%s: %s %s, %s%n",
                    boardGame.getName(),
                    boardGame.getMinPlayers(),
                    boardGame.getMaxPlayers(),
                    boardGame.getCategory());

        }
    }
//
    public void removeBoardGames(){
        System.out.print("Index of Game you want to remove: ");
        int index = Integer.parseInt(console.nextLine());
        games.remove(index);
        System.out.println("The game has successfully been removed!");

    }
}
