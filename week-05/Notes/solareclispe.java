import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class solareclispe {

    public static void main(String[] args) {
        // set up actual eclipse dates
        List<LocalDate> eclipses = new ArrayList<>();
        eclipses.add(LocalDate.of(2023, 4, 20));  // hybrid eclipse
        eclipses.add(LocalDate.of(2023, 10, 14)); // annular eclipse
        eclipses.add(LocalDate.of(2024, 4, 8));   // total eclipse
        eclipses.add(LocalDate.of(2024, 10, 2));  // annular eclipse
        eclipses.add(LocalDate.of(2025, 3, 29));  // partial eclipse
        eclipses.add(LocalDate.of(2025, 9, 21));  // partial eclipse

        DateTimeFormatter usDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        long daysTotal = 0;
        for (int i = 1; i < eclipses.size(); i++) {
            // determine days between eclipses
            long daysBetween = ChronoUnit.DAYS.between(eclipses.get(i - 1), eclipses.get(i));
            // total to ultimately calculate an average
            daysTotal += daysBetween;
            System.out.printf("Eclipse 1: %s, Eclipse 2: %s, Days Between: %s%n",
                    eclipses.get(i - 1).format(usDate),
                    eclipses.get(i).format(usDate),
                    daysBetween);
        }

// calculate the average days between eclipses
        long averageDaysBetween = daysTotal / (eclipses.size() - 1); // 177

// "estimate" four new eclipses
        for (int i = 0; i < 4; i++) {
            LocalDate lastEclipse = eclipses.get(eclipses.size() - 1);
            eclipses.add(lastEclipse.plusDays(averageDaysBetween));
        }

// print out all ten eclipse dates
        for (LocalDate dt : eclipses) {
            System.out.println(dt.format(usDate));
        }

    }
}
