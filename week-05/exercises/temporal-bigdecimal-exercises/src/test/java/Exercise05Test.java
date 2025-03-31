import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class Exercise05Test {

    Exercise05 instance = new Exercise05();

    @Test
    void calculateGiftsTilEndOfYear() {
        BigDecimal actual = instance.calculateGiftsTilEndOfYear(LocalDate.of(2020, 8, 29));
        assertEquals(new BigDecimal("80"), actual);

        actual = instance.calculateGiftsTilEndOfYear(LocalDate.of(2021, 2, 15));
        assertEquals(new BigDecimal("230"), actual);

        actual = instance.calculateGiftsTilEndOfYear(LocalDate.of(1995, 6, 22));
        assertEquals(new BigDecimal("140"), actual);

        actual = instance.calculateGiftsTilEndOfYear(LocalDate.of(2010, 12, 27));
        assertEquals(new BigDecimal("10"), actual);

        actual = instance.calculateGiftsTilEndOfYear(LocalDate.of(2011, 12, 27));
        assertEquals(BigDecimal.ZERO, actual);
    }

    @Test
    void calculateQuirkyGiftsTilEndOfYear() {
        BigDecimal actual = instance.calculateQuirkyGiftsTilEndOfYear(LocalDate.of(2020, 8, 29));
        assertEquals(new BigDecimal("116"), actual);

        actual = instance.calculateQuirkyGiftsTilEndOfYear(LocalDate.of(2021, 2, 15));
        assertEquals(new BigDecimal("373"), actual);

        actual = instance.calculateQuirkyGiftsTilEndOfYear(LocalDate.of(1995, 6, 22));
        assertEquals(new BigDecimal("222"), actual);

        actual = instance.calculateQuirkyGiftsTilEndOfYear(LocalDate.of(2010, 12, 27));
        assertEquals(new BigDecimal("31"), actual);

        actual = instance.calculateQuirkyGiftsTilEndOfYear(LocalDate.of(2011, 12, 27));
        assertEquals(BigDecimal.ZERO, actual);

    }
}