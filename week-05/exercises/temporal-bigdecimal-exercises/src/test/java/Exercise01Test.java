import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Exercise01Test {

    Exercise01 instance = new Exercise01();

    @Test
    void getToday() {
        assertEquals(LocalDate.now(), instance.getToday());
    }

    @Test
    void getFirstFlightDate() {
        assertEquals(LocalDate.of(1903, Month.DECEMBER, 17), instance.getFirstFlightDate());
    }

    @Test
    void makeFutureNullShiftThePast() {

        // future
        assertNull(instance.makeFutureNullShiftThePast(LocalDate.now().plusDays(14)));
        assertNull(instance.makeFutureNullShiftThePast(LocalDate.now().plusYears(11)));

        // today
        assertEquals(LocalDate.now().plusDays(5), instance.makeFutureNullShiftThePast(LocalDate.now()));

        // past
        LocalDate date = LocalDate.now().minusDays(200);
        assertEquals(date.plusDays(5), instance.makeFutureNullShiftThePast(date));

        date = LocalDate.now().minusWeeks(200);
        assertEquals(date.plusDays(5), instance.makeFutureNullShiftThePast(date));

        date = LocalDate.now().minusMonths(200);
        assertEquals(date.plusDays(5), instance.makeFutureNullShiftThePast(date));
    }

    @Test
    void fiveFridaysFromToday() {
        LocalDate date = LocalDate.now();
        assertEquals(fiveFridaysRefImpl(date), instance.fiveFridaysFromDate(date));

        date = LocalDate.now().minusDays(200);
        assertEquals(fiveFridaysRefImpl(date), instance.fiveFridaysFromDate(date));

        date = LocalDate.now().plusWeeks(73);
        assertEquals(fiveFridaysRefImpl(date), instance.fiveFridaysFromDate(date));
    }

    @Test
    void getNextFridays() {
        LocalDate date = LocalDate.now();
        assertEquals(getNextFridaysRefImpl(date, 4), instance.getNextFridays(date, 4));

        date = LocalDate.now().minusDays(200);
        assertEquals(getNextFridaysRefImpl(date, 3), instance.getNextFridays(date, 3));

        date = LocalDate.now().plusWeeks(73);
        assertEquals(getNextFridaysRefImpl(date, 5), instance.getNextFridays(date, 5));
    }

    @Test
    void getDaysBetween() {
        LocalDate one = LocalDate.of(1985, 3, 15);
        LocalDate two = LocalDate.of(2025, 12, 15);
        assertEquals(14885, instance.getDaysBetween(one, two));

        one = LocalDate.of(2025, 12, 15);
        two = LocalDate.of(1985, 3, 15);
        assertEquals(14885, instance.getDaysBetween(one, two));

        one = LocalDate.of(2000, 12, 15);
        two = LocalDate.of(2000, 12, 16);
        assertEquals(1, instance.getDaysBetween(one, two));

        one = LocalDate.of(2000, 5, 22);
        two = LocalDate.of(2000, 5, 15);
        assertEquals(7, instance.getDaysBetween(one, two));
    }


    LocalDate fiveFridaysRefImpl(LocalDate date) {
        LocalDate dt = date.plusDays(1);
        while (dt.getDayOfWeek() != DayOfWeek.FRIDAY) {
            dt = dt.plusDays(1);
        }
        return dt.plusWeeks(4);
    }

    List<LocalDate> getNextFridaysRefImpl(LocalDate date, int fridayCount) {
        ArrayList<LocalDate> fridays = new ArrayList<>();
        LocalDate dt = date.plusDays(1);
        while (dt.getDayOfWeek() != DayOfWeek.FRIDAY) {
            dt = dt.plusDays(1);
        }

        for (int i = 0; i < fridayCount; i++) {
            fridays.add(dt);
            dt = dt.plusWeeks(1);
        }

        return fridays;
    }
}
