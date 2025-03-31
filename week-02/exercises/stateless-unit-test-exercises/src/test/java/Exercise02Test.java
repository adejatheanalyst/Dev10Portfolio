import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exercise02Test {

    @Test
    void surroundWithTags() {
        assertEquals("<b>a</b>", Exercise02.surroundWithTag("a", "b"));
        assertEquals("splendid", Exercise02.surroundWithTag("splendid", null));
    }
    @Test
    void surroundWithTags2() {
        assertEquals("<boom>abc</boom>", Exercise02.surroundWithTag("abc", "boom"));
        assertEquals("<span></span>", Exercise02.surroundWithTag(null, "span"));
    }

    @Test
    void surroundWithTags3() {
        assertEquals("James", Exercise02.surroundWithTag("James", ""));
        assertEquals("<James> </James>", Exercise02.surroundWithTag(" ", "James"));
    }


}