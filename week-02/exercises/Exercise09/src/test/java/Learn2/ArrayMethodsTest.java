package Learn2;

import static Learn2.ArrayMethods.removeZero;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ArrayMethodsTest {

    @org.junit.jupiter.api.Test
    void testRemoveZero() {
        assertArrayEquals(new int[]{1, 2, 3, 4}, removeZero(new int[]{0, 1, 2, 0, 3, 0, 4}));
        assertArrayEquals(new int[]{}, removeZero(new int[]{0, 0, 0, 0}));
        assertArrayEquals(new int[]{1, 2, 3}, removeZero(new int[]{1, 2, 3}));
        assertArrayEquals(new int[]{5}, removeZero(new int[]{0, 5, 0}));
        assertArrayEquals(new int[]{}, removeZero(new int[]{})); 
    }
}