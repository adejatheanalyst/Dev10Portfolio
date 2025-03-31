import java.util.Scanner;

public class Exercise05 {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        Musician[] musicians = new Musician[5];
        musicians[0] = new Musician("Frank Ocean", 10);

        // 1. Use a loop to populate the `musicians` array with your top 5 favorite musicians.
        // (Replace Frank Ocean.)
        // Create musicians from user input. (See Exercise04.)
int index = 0;
        while(index < musicians.length) {
            Musician m = new Musician();
            System.out.print("Musician name: ");
            m.setName(console.nextLine());
//            if (m.getName().equalsIgnoreCase("end")) {
//                break;
//            }
            System.out.print("Musician rating:");
            int rating = Integer.parseInt(console.nextLine());
            m.setRating(rating);
            musicians[index] = m;
            index++;
//            System.out.printf("%s: %s%n", m.getName(), m.getRating());
        }
        // 2. Use a second loop to print details about each musician.
        System.out.println("Top 5 Favorite Musicians");
        for (Musician m : musicians){
            System.out.printf("%s: %d%n", m.getName(), m.getRating());
        }


    }
}
