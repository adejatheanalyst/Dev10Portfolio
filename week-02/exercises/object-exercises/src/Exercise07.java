public class Exercise07 {

    public static void main(String[] args) {

        // 1. Uncomment the code below.
        // 2. Fix any errors by editing the Balloon class, not this code.
        // 3. Run it and confirm its output.

        Balloon one = new Balloon("red");
        Balloon two = new Balloon("yellow");
        Balloon three = new Balloon("blue");

        System.out.printf("%s: %s%n", one.getColor(), one.getPsi());
        System.out.printf("%s: %s%n", two.getColor(), two.getPsi());
        System.out.printf("%s: %s%n", three.getColor(), three.getPsi());

        // Expected Output
        // red: 0.0
        // yellow: 0.0
        // blue: 0.0

    }
}
