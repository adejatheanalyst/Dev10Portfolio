import java.time.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Temporal {
    public static void main(String[] args) {
        // Use the class name, LocalDate, to access builder methods.
//        LocalDate juneFirst = LocalDate.of(2020, 6, 1);             // numeric year, month, day
//        LocalDate julyTwelfth = LocalDate.of(2020, Month.JULY, 12); // month enum
//        //LocalDate today = LocalDate.now();// current year, month, day
//
//        System.out.println(juneFirst);   // 2020-06-01
//        System.out.println(julyTwelfth); // 2020-07-12
//        System.out.println(today);       // 2020-05-26
//
//        System.out.println(julyTwelfth.getYear());       // 2020
//        System.out.println(julyTwelfth.getMonth());      // JULY (Month enum)
//        System.out.println(julyTwelfth.getMonthValue()); // 7
//        System.out.println(julyTwelfth.getDayOfMonth()); // 12
//        System.out.println(julyTwelfth.getDayOfYear());  // 194
//        System.out.println(julyTwelfth.getDayOfWeek());  // SUNDAY (DayOfWeek enum)

//        LocalDate today = LocalDate.now();
//        System.out.println(today);                    // 2020-05-26
//
//        LocalDate weekFromToday = today.plusWeeks(1); // new instance
//        System.out.println(today);                    // 2020-05-26 <-- `today` didn't change
//        System.out.println(weekFromToday);            // 2020-06-02

//        LocalDate today = LocalDate.now();
//        System.out.println(today);                  // 2020-05-26
//        System.out.println(today.plusYears(12));    // 2032-05-26
//        System.out.println(today.plusMonths(100));  // 2028-09-26
//        System.out.println(today.plusWeeks(27));    // 2020-12-01
//        System.out.println(today.plusDays(10000));  // 2047-10-12
//
//        System.out.println(today.minusYears(12));   // 2008-05-26
//        System.out.println(today.minusMonths(100)); // 2012-01-26
//        System.out.println(today.minusWeeks(27));   // 2019-11-19
//        System.out.println(today.minusDays(10000)); // 1993-01-08
//
//// Negative arguments
//        System.out.println(today.plusDays(-100));   // 2020-02-16
//        System.out.println(today.minusDays(-100));  // 2020-09-03


//        LocalDateTime elevenFifteenAM = LocalDateTime.of(2025, 1, 1, 11, 15); // AM
//        LocalDateTime elevenFifteenPM = LocalDateTime.of(2025, 1, 1, 23, 15); // PM
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(elevenFifteenAM); // 2025-01-01T11:15
//        System.out.println(elevenFifteenPM); // 2025-01-01T23:15
//        System.out.println(now);             // 2023-06-20T09:08:14.135824800
//
//        System.out.println(elevenFifteenPM.getHour());   // 23
//        System.out.println(elevenFifteenPM.getMinute()); // 15
//        System.out.println(elevenFifteenPM.getSecond()); // 0
//        System.out.println(elevenFifteenPM.getNano());   // 0
//
//        System.out.println(now.getHour());   // 9
//        System.out.println(now.getMinute()); // 8
//        System.out.println(now.getSecond()); // 14
//        System.out.println(now.getNano());   // 135824800

//        LocalDateTime now = LocalDateTime.now();
//
//        System.out.println(now);                   // 2023-06-20T09:22:30.906073600
//        System.out.println(now.plusHours(2));      // 2023-06-20T11:22:30.906073600
//        System.out.println(now.plusMinutes(64));   // 2023-06-20T10:26:30.906073600
//        System.out.println(now.plusSeconds(35));   // 2023-06-20T09:23:05.906073600
//        System.out.println(now.plusNanos(100123)); // 2023-06-20T09:22:30.906173723
//        System.out.println(now);                   // 2023-06-20T09:22:30.906073600

//        LocalDateTime now = LocalDateTime.of(2020, 7, 8, 17, 15);
//
//        DateTimeFormatter fourDigitYear = DateTimeFormatter.ofPattern("yyyy");
//        DateTimeFormatter twoDigitYear = DateTimeFormatter.ofPattern("yy");
//
//        DateTimeFormatter oneDigitMonth = DateTimeFormatter.ofPattern("M");
//        DateTimeFormatter twoDigitMonth = DateTimeFormatter.ofPattern("MM");
//        DateTimeFormatter monthAbbr = DateTimeFormatter.ofPattern("MMM");
//        DateTimeFormatter monthName = DateTimeFormatter.ofPattern("MMMM");
//
//        System.out.println(now.format(fourDigitYear)); // 2020
//        System.out.println(now.format(twoDigitYear));  // 20
//        System.out.println(now.format(oneDigitMonth)); // 7
//        System.out.println(now.format(twoDigitMonth)); // 07
//        System.out.println(now.format(monthAbbr));     // Jul
//        System.out.println(now.format(monthName));     // July
//
//        LocalDateTime now2 = LocalDateTime.of(2020, 7, 8, 17, 15);
//
//        DateTimeFormatter oneDigitDayOfMonth = DateTimeFormatter.ofPattern("d");
//        DateTimeFormatter twoDigitDayOfMonth = DateTimeFormatter.ofPattern("dd");
//
//        DateTimeFormatter dayOfYear = DateTimeFormatter.ofPattern("D");
//        DateTimeFormatter dayOfWeekAbbr = DateTimeFormatter.ofPattern("eee");
//        DateTimeFormatter dayOfWeekName = DateTimeFormatter.ofPattern("eeee");
//
//        System.out.println(now2.format(oneDigitDayOfMonth)); // 8
//        System.out.println(now2.format(twoDigitDayOfMonth)); // 08
//        System.out.println(now2.format(dayOfYear));          // 190
//        System.out.println(now2.format(dayOfWeekAbbr));      // Wed
//        System.out.println(now2.format(dayOfWeekName));      // Wednesday
//
//        LocalDateTime now3 = LocalDateTime.of(2020, 7, 8, 17, 15);
//
//        DateTimeFormatter twelveHourOneDigit = DateTimeFormatter.ofPattern("h");
//        DateTimeFormatter twelveHourTwoDigit = DateTimeFormatter.ofPattern("hh");
//        DateTimeFormatter twentyFourHour = DateTimeFormatter.ofPattern("kk");
//        DateTimeFormatter twelveHourTwoDigitAMPM = DateTimeFormatter.ofPattern("hha");
//
//        DateTimeFormatter minutesTwoDigits = DateTimeFormatter.ofPattern("mm");
//        DateTimeFormatter secondsTwoDigits = DateTimeFormatter.ofPattern("ss");
//
//        System.out.println(now3.format(twelveHourOneDigit));     // 5
//        System.out.println(now3.format(twelveHourTwoDigit));     // 05
//        System.out.println(now3.format(twentyFourHour));         // 17
//        System.out.println(now3.format(twelveHourTwoDigitAMPM)); // 05PM
//        System.out.println(now3.format(minutesTwoDigits));       // 15
//        System.out.println(now3.format(secondsTwoDigits));       // 00

//        LocalDateTime now = LocalDateTime.of(2020, 7, 8, 17, 15);
//
//        DateTimeFormatter usDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//        DateTimeFormatter usTime = DateTimeFormatter.ofPattern("h:mma");
//        DateTimeFormatter usDateTime = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mma");
//        DateTimeFormatter monthAbbrDate = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
//
//        DateTimeFormatter dotDate = DateTimeFormatter.ofPattern("yyyy.MM.dd");
//        DateTimeFormatter formalDate = DateTimeFormatter.ofPattern("d MMMM, yyyy");
//// The first two M's have meaning. The third M, surrounded
//// by single quotes is embedded directly in the output.
//        DateTimeFormatter withLiteral = DateTimeFormatter.ofPattern("yy.MM'M'.dd");
//
//        System.out.println(now.format(usDate));        // 07/08/2020
//        System.out.println(now.format(usTime));        // 5:15PM
//        System.out.println(now.format(usDateTime));    // 07/08/2020 5:15PM
//        System.out.println(now.format(monthAbbrDate)); // 08-Jul-2020
//        System.out.println(now.format(dotDate));       // 2020.07.08
//        System.out.println(now.format(formalDate));    // 8 July, 2020
//        System.out.println(now.format(withLiteral));   // 20.07M.08


//        Scanner console = new Scanner(System.in);
//
//        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
//        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h:mma");
//
//        System.out.print("Enter a date in month/day/year format:");
//        LocalDate date = LocalDate.parse(console.nextLine(), dateFormat);
//        System.out.println(date); // default format
//
//        System.out.print("Enter a time in twelve hour:minute format:");
//        LocalTime time = LocalTime.parse(console.nextLine(), timeFormat);
//        System.out.println(time); // default format

//        Scanner console2 = new Scanner(System.in);
//        DateTimeFormatter dateFormat2 = DateTimeFormatter.ofPattern("M/d/yyyy");
//        String value = console2.nextLine();
//        try {
//            LocalDate date2 = LocalDate.parse(value, dateFormat2);
//            System.out.println(date2);
//        } catch (DateTimeParseException ex) {
//            System.out.printf("'%s' is not a valid date.%n", value);
//        }
        LocalDateTime past = LocalDateTime.of(1972, 7, 25, 20, 13, 33);
        LocalDateTime future = LocalDateTime.of(2051, 4, 9, 3, 47, 5);
        System.out.println(ChronoUnit.YEARS.between(past, future));     // 78
        System.out.println(ChronoUnit.MONTHS.between(past, future));    // 944
        System.out.println(ChronoUnit.DAYS.between(past, future));      // 28746
        System.out.println(ChronoUnit.HOURS.between(past, future));     // 689911
        System.out.println(ChronoUnit.MINUTES.between(past, future));   // 41394693
        System.out.println(ChronoUnit.SECONDS.between(past, future));   // 2483681612
        System.out.println(ChronoUnit.CENTURIES.between(past, future)); // 0
        System.out.println(ChronoUnit.DECADES.between(past, future));   // 7
        System.out.println(ChronoUnit.HALF_DAYS.between(past, future)); // 57492








    }

}
