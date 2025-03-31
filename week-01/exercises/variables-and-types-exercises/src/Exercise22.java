public class Exercise22 {

    public static void main(String[] args) {
        String name = "Asma";
        int milesToWork = 3;
        String transport = "bike";

        // 1. Use the variables above to construct a sentence in the form:
        // "Asma commutes with a bike. The commute is 3 miles each way."
        String message = name + " commutes with a " + transport + ". The Commute is " + milesToWork + " miles each way.";
        // Changing variable values should change the sentence.
        System.out.println(message);
    }
}
