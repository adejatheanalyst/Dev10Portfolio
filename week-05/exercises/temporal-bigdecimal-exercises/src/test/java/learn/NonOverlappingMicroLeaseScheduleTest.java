package learn;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class NonOverlappingMicroLeaseScheduleTest {

    // 2. The tests below are fairly simple. Most test interactions with only two leases.
    // Create one positive and one negative test that requires a more complex
    // Arrange step.

    NonOverlappingMicroLeaseSchedule schedule = new NonOverlappingMicroLeaseSchedule();

    @Test
    void shouldNotAllowNulls() {
        assertFalse(schedule.add(null));
        assertFalse(schedule.add(new MicroLease(null, LocalDateTime.now())));
        assertFalse(schedule.add(new MicroLease(LocalDateTime.now(), null)));
        assertFalse(schedule.add(new MicroLease(null, null)));
    }

    @Test
    void startShouldNotComeAfterEnd() {
        assertFalse(schedule.add(new MicroLease(
                LocalDateTime.of(2021, 1, 1, 0, 0),
                LocalDateTime.of(2012, 12, 12, 12, 12))));
    }

    @Test
    void shouldNotAddLeaseThatSurroundsExistingLease() {
        assertTrue(schedule.add(new MicroLease(
                LocalDateTime.of(2021, 1, 1, 0, 0),
                LocalDateTime.of(2021, 2, 1, 0, 0))));

        assertFalse(schedule.add(new MicroLease(
                LocalDateTime.of(2020, 1, 1, 0, 0),
                LocalDateTime.of(2022, 2, 1, 0, 0))));
    }

    @Test
    void shouldNotAddLeaseInsideExistingLease() {
        assertTrue(schedule.add(new MicroLease(
                LocalDateTime.of(2021, 1, 1, 0, 0),
                LocalDateTime.of(2021, 2, 1, 0, 0))));

        assertFalse(schedule.add(new MicroLease(
                LocalDateTime.of(2021, 1, 7, 0, 0),
                LocalDateTime.of(2021, 1, 14, 0, 0))));
    }

    @Test
    void shouldNotAddLeaseThatOverlapsStartOfExistingLease() {
        assertTrue(schedule.add(new MicroLease(
                LocalDateTime.of(2021, 1, 1, 0, 0),
                LocalDateTime.of(2021, 2, 1, 0, 0))));

        assertFalse(schedule.add(new MicroLease(
                LocalDateTime.of(2020, 12, 1, 0, 0),
                LocalDateTime.of(2021, 1, 14, 0, 0))));
    }

    @Test
    void shouldNotAddLeaseThatOverlapsEndOfExistingLease() {
        assertTrue(schedule.add(new MicroLease(
                LocalDateTime.of(2021, 1, 1, 0, 0),
                LocalDateTime.of(2021, 2, 1, 0, 0))));

        assertFalse(schedule.add(new MicroLease(
                LocalDateTime.of(2021, 1, 14, 0, 0),
                LocalDateTime.of(2021, 4, 1, 0, 0))));
    }

    @Test
    void shouldAddOneValidLease() {
        assertTrue(schedule.add(new MicroLease(
                LocalDateTime.now(),
                LocalDateTime.now().plusMonths(6))));
    }

    @Test
    void shouldAddLeaseThatStartsExactlyWhenAnotherEnds() {
        assertTrue(schedule.add(new MicroLease(
                LocalDateTime.of(2021, 1, 1, 0, 0),
                LocalDateTime.of(2021, 2, 1, 0, 0))));

        assertTrue(schedule.add(new MicroLease(
                LocalDateTime.of(2021, 2, 1, 0, 0),
                LocalDateTime.of(2021, 3, 1, 0, 0))));
    }

    @Test
    void shouldSqueezeLeaseInAtTheHourLevel() {
        assertTrue(schedule.add(new MicroLease(
                LocalDateTime.of(2021, 1, 1, 0, 0),
                LocalDateTime.of(2021, 1, 1, 12, 0))));

        assertTrue(schedule.add(new MicroLease(
                LocalDateTime.of(2021, 1, 1, 20, 0),
                LocalDateTime.of(2021, 1, 1, 22, 0))));

        assertTrue(schedule.add(new MicroLease(
                LocalDateTime.of(2021, 1, 1, 14, 0),
                LocalDateTime.of(2021, 1, 1, 16, 0))));
    }

    @Test
    void shouldAddManyLeases() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusDays(1);
        for (int i = 0; i < 31; i++) {
            assertTrue(schedule.add(new MicroLease(start, end)));
            start = end;
            end = start.plusDays(1);
        }
    }
}