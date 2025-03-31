import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise06 {

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();

        // 1. Use a loop to find the game in `games` that can be played by the most players.
        BoardGame mostPlayerGames = games.get(0);

        for (BoardGame boardGame : games) {
            if (mostPlayerGames == null || boardGame.getMaxPlayers() > mostPlayerGames.getMaxPlayers()) {
                mostPlayerGames = boardGame;
            }
        }
        System.out.println(mostPlayerGames);
    }
}
//if (mostPlayerGames != null){
//    System.out.printf("%s: %s %s, %s%n",
//            mostPlayerGames.getName(),
//            mostPlayerGames.getMinPlayers(),
//            mostPlayerGames.getMaxPlayers(),
//            mostPlayerGames.getCategory());
//
//}
//}


//        for (BoardGame boardGame : games) {
//            if (boardGame.getMaxPlayers()> 4){
//                System.out.printf("%s: %s %s, %s%n",
//                        boardGame.getName(),
//                        boardGame.getMinPlayers(),
//                        boardGame.getMaxPlayers(),
//                        boardGame.getCategory());
//
//            }
//        }
        // 2. Print the game. (Expected: "Ultimate Werewolf...")



