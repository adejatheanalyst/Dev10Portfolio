import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise04 extends Exercise02{

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();

        // 1. Instantiate a new ArrayList<BoardGame>.
        ArrayList<BoardGame> moreGames = new ArrayList<>();
        // 2. Add three BoardGames to the new list.
        BoardGame wakeDaddy = new BoardGame("Don't Wake Daddy",2, 4, " Jumpscare");
        moreGames.add(wakeDaddy);
        BoardGame hungry = new BoardGame("Hungry Hungry Hippos",2, 4, " Eat em up");
        moreGames.add(hungry);
        BoardGame perfect = new BoardGame("Perfection",1, 4, " Timed");
        moreGames.add(perfect);

        // 3. Print the new list.
//for (BoardGame boardGame : moreGames){
//    System.out.printf("%s: %s %s, %s%n",
//            boardGame.getName(),
//            boardGame.getMinPlayers(),
//            boardGame.getMaxPlayers(),
//            boardGame.getCategory())
        games.addAll(moreGames);
        allBoardGames(games);
}
}
        // 4. Add items in the new list to `games` with the `addAll` method.

        // 5. Print `games`.
