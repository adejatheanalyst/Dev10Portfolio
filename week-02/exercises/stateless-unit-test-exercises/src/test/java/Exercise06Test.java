import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class Exercise06Test {

    Exercise06 instance = new Exercise06();

    // Suggested test additions:
    // shouldBeNullForNulLArg
    // shouldCapitalizeMultipleElements (several scenarios)
    // shouldIgnoreNullElements
    // shouldIgnoreEmptyElements

    @Test
    void shouldCapitalizeOneElement() {
        String[] values = {"lower"};
        String[] expected = {"Lower"};
        String[] actual = instance.capitalizeAll(values);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldBeEmptyForEmptyArg() {
        String[] values = {""};
        String[] expected = {""};
        String[] actual = instance.capitalizeAll(values);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldBeNullForNullArg() {
        String[] values = {null};
        String[] expected = {null};
        String[] actual = instance.capitalizeAll(values);
        assertArrayEquals(expected, actual);
    }
    @Test
    void shouldCapitalizeMultipleElements1() {
        String[] values = {"null", "hey", "crazy"};
        String[] expected = {"Null", "Hey", "Crazy"};
        String[] actual = instance.capitalizeAll(values);
        assertArrayEquals(expected, actual);
    }
    @Test
    void shouldIgnoreNullElements() {
        String[] values = {null, "hey", "crazy"};
        String[] expected = {null, "Hey", "Crazy"};
        String[] actual = instance.capitalizeAll(values);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldIgnoreEmptyElements() {
        String[] values = {"", "hey", "crazy"};
        String[] expected = {"", "Hey", "Crazy"};
        String[] actual = instance.capitalizeAll(values);
        assertArrayEquals(expected,actual);
    }


}