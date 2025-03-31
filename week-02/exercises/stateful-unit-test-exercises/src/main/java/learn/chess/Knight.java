package learn.chess;

public class Knight {
    private int row = 0;
    private int column = 0;

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Knight(int row, int column) {
        this.row = row;
        this.column = column;
    }
    public boolean move(int row, int column) {
        if (row < 0 || row > 7 || column < 0 || column > 7) {
            return false;
        }

        int rowDiff = Math.abs(this.row - row);
        int colDiff = Math.abs(this.column - column);

        if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
            this.row = row;
            this.column = column;
            return true;  // Valid move
        }


        return false;
    }
    }
