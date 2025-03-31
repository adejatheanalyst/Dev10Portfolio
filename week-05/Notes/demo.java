import java.time.LocalDate;

public class demo {
    public static void main(String[] args) {
        LocalDate date1 = LocalDate.of(2023, 10, 1);
        LocalDate date2 = LocalDate.of(2023, 10, 31);


        System.out.println(date1.compareTo(date2));
        System.out.println(date2.compareTo(date1));


        System.out.println(date1.isAfter(date1));
        System.out.println(date1.isBefore(date2));
    }
}
