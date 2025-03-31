import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Exercise11 extends Exercise02{

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();

        // 1. Swap the 2nd game with the 4th and the 3rd with the 7th.
        Collections.swap(games, 1,3);
        Collections.swap(games, 2,6);

//games.set(1, games.get(3));
//games.set(2, games.get(6));
        // 2. Print `games` and confirm their order.
        allBoardGames(games);
    }
}
