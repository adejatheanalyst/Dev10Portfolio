package learn.chess;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {



    @Test
    void move() {
        Knight knight = new Knight(4, 4);  // Start at position (4, 4)

        // Try to move out of bounds (invalid move)
        assertFalse(knight.move(8, 8));  // Invalid move
        // Position should remain the same
        assertEquals(4, knight.getRow());
        assertEquals(4, knight.getColumn());

        // Try a valid knight move (L-shaped move)
        assertTrue(knight.move(6, 5));  // Valid move
        // Position should be updated to (6, 5)
        assertEquals(6, knight.getRow());
        assertEquals(5, knight.getColumn());

        // Try another valid knight move
        assertTrue(knight.move(7, 3));  // Valid move
        // Position should be updated to (7, 3)
        assertEquals(7, knight.getRow());
        assertEquals(3, knight.getColumn());

        // Try an invalid move (not L-shaped)
        assertFalse(knight.move(5, 5));  // Invalid knight move (not L-shaped)
        // Position should remain the same
        assertEquals(7, knight.getRow());
        assertEquals(3, knight.getColumn());
    }
}