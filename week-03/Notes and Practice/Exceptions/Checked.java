import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Scanner;

public class Checked {
    public static Number readNumber(String prompt) throws ParseException {

            Scanner console = new Scanner(System.in);
            NumberFormat formatter = NumberFormat.getInstance();
            System.out.print(prompt);
            // BAD CODE: Compile-time error
            // "Unhandled exception type ParseException"
//            try {
                return formatter.parse(console.nextLine());
//            } catch (ParseException ex) {
//                return null;
//
//            }
        }
        public static void getMinAndMax() throws ParseException {
            Number min = readNumber("Enter a Minimum: ");
            Number max = readNumber("Enter a Maximum: ");
        }

    public static void main(String[] args) throws ParseException {
        getMinAndMax();
    }
    }


