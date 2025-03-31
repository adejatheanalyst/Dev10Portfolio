import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exercise05Test {

    @Test
    void isWithinFiveOfAHundred() {
        assertEquals(true, Exercise05.isWithinFiveOfAHundred(-105));
        assertEquals(true, Exercise05.isWithinFiveOfAHundred(5));
        assertEquals(true, Exercise05.isWithinFiveOfAHundred(100));
    }

    @Test
    void isNotWithinFiveOfAHundred() {
        assertEquals(false, Exercise05.isWithinFiveOfAHundred(-94));
        assertEquals(false, Exercise05.isWithinFiveOfAHundred(6));
        assertEquals(false, Exercise05.isWithinFiveOfAHundred(106));
    }
}