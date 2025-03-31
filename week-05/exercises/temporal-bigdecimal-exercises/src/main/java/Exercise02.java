import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Exercise02 {

    // LocalDateTime
    // ========================
    // Complete the numbered tasks below.
    // Open and execute the accompanying tests to confirm your solution is correct.

    // 1. return the current date and time as a LocalDateTime
    LocalDateTime getNow() {
        return LocalDateTime.now();
    }

    // 2. return the current date and 4PM (tea time!) as a LocalDateTime.
    LocalDateTime getTeaTime() {
        return LocalDateTime.now().with(LocalTime.of(16, 0));
    }

    // 3. add 12 hours to the LocalDateTime parameter and return the value
    LocalDateTime add12Hours(LocalDateTime dateTime) {


        return dateTime.plusHours(12);
    }

    // 4. given a LocalDateTime parameter, return a list of the next 4
    // quarter-hour appointments available after the datetime.
    // appointment times should not include seconds even if the time parameter does.
    // ignore seconds and nanoseconds.
    // Examples:
    // time == 16:07:32
    // appointments == 16:15, 16:30, 16:45, 17:00
    //
    // time == 03:00:01
    // appointments == 03:00, 03:15, 03:30, 03:45
    //
    // time == 04:30:00
    // appointments == 04:30, 04:45, 05:00, 05:15
    List<LocalDateTime> getQuarterHourAppointments(LocalDateTime dateTime) {
        List<LocalDateTime> appointments = new ArrayList<>();
        dateTime = dateTime.withSecond(0).withNano(0);
        int minute = dateTime.getMinute();
        if (minute % 15 != 0) {
            dateTime = dateTime.plusMinutes(15 - minute % 15);
        }
        for (int i = 0; i < 4; i++) {
            appointments.add(dateTime);
            dateTime = dateTime.plusMinutes(15);
        }
        return appointments;
    }
    }

