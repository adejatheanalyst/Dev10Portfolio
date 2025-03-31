package learn.chess;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    Queen queen = new Queen(8, 8);

    @Test
    void shouldMoveToFourCorners() {
        assertTrue(queen.move(7, 0)); // top left;
        assertEquals(7, queen.getRow());
        assertEquals(0, queen.getColumn());

        assertTrue(queen.move(7, 7)); // top right;
        assertEquals(7, queen.getRow());
        assertEquals(7, queen.getColumn());

        assertTrue(queen.move(0, 7)); // bottom right;
        assertEquals(0, queen.getRow());
        assertEquals(7, queen.getColumn());

        assertTrue(queen.move(0, 0)); // bottom left;
        assertEquals(0, queen.getRow());
        assertEquals(0, queen.getColumn());
    }

    // 1. Add tests to validate Queen movement.
    // Required tests:
    // - anything off the board is invalid, should return false and leave field values alone.
    @Test
    void shouldMoveOffTheBoardAndCauseError(){
        Queen queen = new Queen(4, 4);
        assertFalse(queen.move(8, 8));
        assertEquals(4, queen.getRow());
        assertEquals(4, queen.getColumn());
    }
    // - moving to the current location should return false and leave field values alone.
    @Test
    void shouldMoveToCurrentLocation(){
        Queen queen = new Queen(4, 4);
        assertFalse(queen.move(4, 4));
        assertEquals(4, queen.getRow());
        assertEquals(4, queen.getColumn());
    }
    // - should still be able to move after an invalid move.
    // - can move diagonally in various ways

    @Test
    void shouldMoveDiagonally(){
        Queen queen = new Queen(4, 4);
        assertTrue(queen.move(7, 4));
        assertEquals(7, queen.getRow());
        assertEquals(4, queen.getColumn());
    }
    // Always confirm that fields have been properly updated using getters.
}