import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exercise07Test {
    Exercise07 instance = new Exercise07();
    @Test
    void reverse() {
        String[] values = {"A","B","C","D"};
        String[] expected = {"D","C","B","A"};
        String[] actual = instance.reverse(values);
        assertArrayEquals(expected, actual, "The array was not reversed correctly");
        }
    @Test
    void reverseWithEmptyArray() {
        String[] values = {};
        String[] expected = {};
        String[] actual = instance.reverse(values);
        assertArrayEquals(expected, actual, "Reversing an empty array should return an empty array.");
    }
    @Test
    void reverseWithSingleElement() {
        String[] values = {"A"};
        String[] expected = {"A"};
        String[] actual = instance.reverse(values);
        assertArrayEquals(expected, actual, "Reversing a single-element array should return the same array.");
    }
    @Test
    void reverseWithNullValues() {
        String[] values = {"A", null, "B", null};
        String[] expected = {null, "B", null, "A"};
        String[] actual = instance.reverse(values);
        assertArrayEquals(expected, actual, "The array with null values was not reversed correctly.");
    }
    @Test
    void reverseWithDuplicates() {
        String[] values = {"A", "B", "B", "A"};
        String[] expected = {"A", "B", "B", "A"};
        String[] actual = instance.reverse(values);
        assertArrayEquals(expected, actual, "The array with duplicate values was not reversed correctly.");
    }


    }
