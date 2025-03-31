import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise03 extends Exercise02{

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();

        // 1. Add three new games to `games` with the `add` method.
        BoardGame trouble = new BoardGame("Trouble",2, 4, " Racing");
        // 2. Print `games` after each add.
        games.add(trouble);
//        allBoardGames(games);
        BoardGame sorry = new BoardGame("Sorry",2, 4, " Racing");
        games.add(sorry);
        allBoardGames(games);
        BoardGame clue = new BoardGame("CLUE",3, 6, " Mystery");
        games.add(clue);
        allBoardGames(games);


    }
}
