import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;
import java.util.Collections;

public class Exercise12 extends Exercise02 {

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();

        // 1. Shift all games two positions to the left. A game at index 0 "shifts" to the end of the list.
        // Example: A,B,C,D,E -> shifted two positions is -> C,D,E,A,B
        // 2. Print `games` and confirm the new order.
//        int shift = 2;
//        int size = games.size();
//        ArrayList<BoardGame> reordered = new ArrayList<>(Collections.nCopies(size,null));
//
//        for(int i = 0; i < size; i++){
//            int newIndex = (i + size - shift) % size;
//            reordered.set(newIndex, games.get(i));
//        }
                for(int i = 0; i < games.size() - 2 ; i++){
                    BoardGame temp = games.remove(0);
                    games.add(temp);
        }

//        games = reordered;
           allBoardGames(games);
    }
}
