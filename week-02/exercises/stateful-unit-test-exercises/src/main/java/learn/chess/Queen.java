package learn.chess;

/**
 * The most powerful chess piece.
 * Moves horizontally, vertically, or diagonally as many spaces as she wants.
 * She tracks her current position with two integer fields: row and column.
 * Rows and columns are zero-based.
 * The queen starts at row 0 and column 0, though there is no board.
 * Row 7 - - - - - - - -
 * Row 6 - - - - - - - -
 * Row 5 - - - - - - - -
 * Row 4 - - - - - - - -
 * Row 3 - - - - - - - -
 * Row 2 - - - - - - - -
 * Row 1 - - - - - - - -
 * Row 0 Q - - - - - - -
 * Cols: 0 1 2 3 4 5 6 7
 * <p>
 * See: https://en.wikipedia.org/wiki/Queen_(chess)
 */
public class Queen {

    private int row = 0;
    private int column = 0;

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Queen(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Requests a move to a new row and column.
     *
     * @param row    the requested row
     * @param column the requested column
     * @return true if the move is valid, false if it's not
     */
    public boolean move(int row, int column) {
        if (row < 0 || row > 7 || column < 0 || column > 7) {
            return false;
        }

        // 1. Implement the move method.
        // If the move is valid, return true and update `row` and `column` fields.
        // If the move is invalid, return false and do not update fields.
        // Rules for valid moves:
        // - row and column can never be less than 0 or greater than 7 (8 rows and columns on a chess board).
        // - can't equal the existing row and column (same location)
        if (this.row == row && this.column == column ) {return false;}
        // - If the row parameter and field are the same but the column is not, the queen is moving horizontally. That's valid.
        if (this.row == row){
            this.column = column;
            return true;
        }
        // - If the column parameter and field are the same but the row is not, the queen is moving vertically. That's valid.
        if (this.column == column){
            this.row = row;
            return true;
        }
        // - Otherwise, the absolute difference between row parameter and field
        //   and the absolute difference between the column parameter and field must be the same.
        //   That represents a diagonal move.
        if (Math.abs(this.row - row) == Math.abs(this.column - column)) {
            this.row = row;
            this.column = column;
            return true;
        }

        return false;
    }
}