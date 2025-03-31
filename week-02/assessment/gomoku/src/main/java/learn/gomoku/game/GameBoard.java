package learn.gomoku.game;

public class GameBoard{
    public String printGameBoard(Gomoku game) {
        StringBuilder board = new StringBuilder();
        for (int row = 0; row < Gomoku.WIDTH; row++) {
            for (int col = 0; col < Gomoku.WIDTH; col++) {
                Stone hasStone = null;
//                boolean hasStone = false
                for (Stone stone : game.getStones()) {
                    if (stone.getRow() == row && stone.getColumn() == col) {
                        hasStone = stone;
                        break;
                    }
                }
                if (hasStone != null) { // if stones are black or white add a B or W based off placement
                    board.append(hasStone.isBlack() ? "B  " : "W  ");
                } else {

                    board.append("⬛ ");
=======

                } //
            }
            board.append("\n");
        }
        return board.toString();
    }

}


