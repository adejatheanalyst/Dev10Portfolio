import java.util.Scanner;

public class Exercise10 {

    public static void main(String[] args) {
        // BALLOON GAME
        Scanner console = new Scanner(System.in);

        // 1. Instantiate three balloons of different colors.
        Balloon a = new Balloon("Red");
        Balloon b = new Balloon("Blue");
        Balloon c = new Balloon("Yellow");
//        for (int i = 0; i < 10; i++) {
//            b.inflate();
//            System.out.printf("psi:%s, exploded?:%s%n", b.getPsi(), b.isExploded() ? "yes" : "no");
//        }
        do {

            System.out.print("Inflate? [y/n]: ");
            if (console.nextLine().equalsIgnoreCase("y")) {
                // 2. If the user confirms an inflate, inflate each balloon.
                b.inflate(); c.inflate(); a.inflate();

                System.out.printf("Balloon Color:%s, exploded?:%s%n", a.getColor(), b.isExploded() ? "yes" : "no");
                System.out.printf("Balloon Color:%s, exploded?:%s%n", b.getColor(), b.isExploded() ? "yes" : "no");
                System.out.printf("Balloon Color:%s, exploded?:%s%n", c.getColor(), b.isExploded() ? "yes" : "no");

            }
            if (a.isExploded() || b.isExploded() || c.isExploded()) {
                break;
            }
            // 3. When one or more balloons explode, stop the loop.
        } while (true);

        // 4. Print the color of the winners (balloons that exploded).
        System.out.println("Game over! The following balloons exploded:");
        if (a.isExploded()) {
            System.out.println("Red");
        }
        if (b.isExploded()) {
            System.out.println("Blue");
        }
        if (c.isExploded()) {
            System.out.println("Yellow");
        }
    }
}
