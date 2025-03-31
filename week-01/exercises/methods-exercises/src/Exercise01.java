public class Exercise01 {

    public static void main(String[] args) {

        String firstCompliment = getRandomCompliment();
        System.out.println(firstCompliment);

        // Call the getRandomCompliment method two more times.
        // 1. Store the result in a new string variable.
        String result = getRandomCompliment();
        // 2. Print the result.
        System.out.println(result);
        // 3. Print the value returned directly without an intermediate variable.
        System.out.println(getRandomCompliment());
    }

    public static String getRandomCompliment() {

        switch ((int) (Math.random() * 10)) {
            case 0:
                return "Your hard work is inspiring.";
            case 1:
                return "Your outfit is cute.";
            case 2:
                return "You're a very nice person.";
            case 3:
                return "Great attitude. Keep going!";
            case 4:
                return "You always know how to put a smile on my face.";
            case 5:
                return "Well done!";
            case 6:
                return "Excellent job.";
            case 7:
                return "Thank you for your kindness.";
            case 8:
                return "Bravo.";
            case 9:
                return "Really, really great.";
        }

        return ""; // Should never happen.
    }
}
