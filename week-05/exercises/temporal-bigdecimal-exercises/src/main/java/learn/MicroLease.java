package learn;

import java.time.LocalDateTime;

/**
 * A lease that covers any time span from years to seconds.
 */
public class MicroLease {

    private final LocalDateTime start;
    private final LocalDateTime end;

    public MicroLease(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public boolean doesNotCollide(MicroLease other) {
        return start.isBefore(other.end) && end.isAfter(other.start);
    }
}
