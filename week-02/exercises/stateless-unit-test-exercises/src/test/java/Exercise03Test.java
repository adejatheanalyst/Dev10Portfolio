import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exercise03Test {

    @Test
    void hasAllVowels() {
        assertEquals(true, Exercise03.hasAllVowels("aeiou"));
        assertEquals(true, Exercise03.hasAllVowels("James and the Giant Peach really makes up for boredom"));

    }


    @Test
    void doesNotHaveAllVowels() {
        assertEquals(false, Exercise03.hasAllVowels("James"));
        assertEquals(false, Exercise03.hasAllVowels("Pizza pie"));
    }

    @Test
    void doesNotHaveAllVowelsNull() {
        assertEquals(false, Exercise03.hasAllVowels(null));
    }



}