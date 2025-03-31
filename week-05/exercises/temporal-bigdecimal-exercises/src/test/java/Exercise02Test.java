import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Exercise02Test {

    Exercise02 instance = new Exercise02();

    @Test
    void getNow() {
        LocalDateTime expected = LocalDateTime.now()
                .withNano(0)
                .withSecond(0);
        LocalDateTime actual = instance.getNow()
                .withNano(0)
                .withSecond(0);

        // So, testing a current time is fraught with issues.
        // It's unlikely, but if we run the test near an hour/minute/second change-over,
        // the test can fail.
        // Take failure with a grain of salt.
        assertEquals(expected, actual);
    }

    @Test
    void getTeaTime() {
        LocalDateTime expected = LocalDateTime.now()
                .withHour(16)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
        LocalDateTime actual = instance.getTeaTime()
                .withSecond(0)
                .withNano(0);
        assertEquals(expected, instance.getTeaTime());
    }

    @Test
    void add12Hours() {
        LocalDateTime dateTime = LocalDateTime.now();
        assertEquals(dateTime.plusHours(12), instance.add12Hours(dateTime));

        dateTime = dateTime.withHour(16).withMinute(15);
        assertEquals(dateTime.plusHours(12), instance.add12Hours(dateTime));

        dateTime = dateTime.withHour(4).withMinute(47);
        assertEquals(dateTime.plusHours(12), instance.add12Hours(dateTime));
    }

    @Test
    void getQuarterHourRefImpl() {
        LocalDateTime dateTime = LocalDateTime.of(2020, 5, 5, 16, 6, 32);
        List<LocalDateTime> expected = getQuarterHourRefImpl(dateTime);
        List<LocalDateTime> actual = instance.getQuarterHourAppointments(dateTime);
        assertEquals(expected, actual);

        dateTime = LocalDateTime.of(2024, 6, 22, 3, 0, 1);
        expected = getQuarterHourRefImpl(dateTime);
        actual = instance.getQuarterHourAppointments(dateTime);
        assertEquals(expected, actual);

        dateTime = LocalDateTime.of(1980, 2, 9, 4, 30);
        expected = getQuarterHourRefImpl(dateTime);
        actual = instance.getQuarterHourAppointments(dateTime);
        assertEquals(expected, actual);

        dateTime = LocalDateTime.of(2005, 12, 3, 5, 55);
        expected = getQuarterHourRefImpl(dateTime);
        actual = instance.getQuarterHourAppointments(dateTime);
        assertEquals(expected, actual);
    }

    List<LocalDateTime> getQuarterHourRefImpl(LocalDateTime dt) {

        ArrayList<LocalDateTime> result = new ArrayList<>();
        int delta = dt.getMinute() % 15;
        if (delta > 0) {
            dt = dt.plusMinutes(15 - delta);
        }

        dt = dt.withSecond(0).withNano(0);

        for (int i = 0; i < 4; i++) {
            result.add(dt);
            dt = dt.plusMinutes(15);
        }

        return result;
    }
}