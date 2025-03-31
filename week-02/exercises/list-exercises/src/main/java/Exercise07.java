import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise07 {

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();

        // 1. Instantiate a new ArrayList<BoardGame> and call it `economicGames`.
        ArrayList<BoardGame> economicGames = new ArrayList<>();
//        games.addAll(moreGames);  add all
        //moreGames.add(hungry);
        // 2. Loop over `games`. Add each game with the "Economic" category to `economicGames`.
        for (BoardGame boardGame : games) {
            if ("Economic".equalsIgnoreCase(boardGame.getCategory())) {
                economicGames.add(boardGame);
            }
        }

        // 3. Print `economicGames`.
        for (BoardGame game: economicGames) {
            System.out.printf("%s: %s %s, %s%n",
                    game.getName(),
                    game.getMinPlayers(),
                    game.getMaxPlayers(),
                    game.getCategory());
        }
    }
}




