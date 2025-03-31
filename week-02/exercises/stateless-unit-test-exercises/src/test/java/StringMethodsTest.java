import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
class StringMethodsTest {

    @Test
    void startsWithDayOfWeekMon() {
            assertTrue(StringMethods.startsWithDayOfWeek("Monster"));
    }
    @Test
    void startsWithDayOfWeekTue() {
        assertTrue(StringMethods.startsWithDayOfWeek("Tuesday Night"));
    }

    @Test
    void startsWithDayOfWeek() {
        String[] testStrings = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (String testString : testStrings) {
            assertTrue(StringMethods.startsWithDayOfWeek(testString),
                    "Expected true for: " + testString);
        }
    }

    @Test
    void doesNotStartsWithDayOfWeek() {
        assertFalse(StringMethods.startsWithDayOfWeek("Child"));
    }
    @Test
    void containsDayOfWeek() {
        String[] testStrings = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (String testString : testStrings) {
            assertTrue(StringMethods.containsDayOfWeek(testString),
                    "Expected true for: " + testString);}

    }

    @Test
    void removeVowelFromBetweenX() {
        assertEquals("xx",StringMethods.removeVowelFromBetweenX("xox"));
        assertEquals("onexxx",StringMethods.removeVowelFromBetweenX("onexexx"));
        assertEquals("xerrex",StringMethods.removeVowelFromBetweenX("xerrex"));
        assertEquals("xxxxxx",StringMethods.removeVowelFromBetweenX("xuxxuxxux"));

    }
    }
