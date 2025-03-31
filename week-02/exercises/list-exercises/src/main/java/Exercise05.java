import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise05 extends Exercise02 {

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();

        // 1. Loop over each BoardGame in `games` and print games with the "Adventure" category.
        for (BoardGame boardGame : games) {
            if ("Adventure".equalsIgnoreCase(boardGame.getCategory())){
                System.out.printf("%s: %s %s, %s%n",
                        boardGame.getName(),
                        boardGame.getMinPlayers(),
                        boardGame.getMaxPlayers(),
                        boardGame.getCategory());

            }
            }

        }


    }

