public class Exercise20 {

    public static void main(String[] args) {

        // From Alice Roosevelt Longworth:
        String aliceQuote = "If you can't say something good about someone, sit right here by me.";

        char tenthChar = aliceQuote.charAt(9);
        System.out.println(tenthChar);

        // 1. Store the first character from aliceQuote in a char variable.
        char firstChar = aliceQuote.charAt(0);
        System.out.println(firstChar);
        // 2. Print it.
        // 3. Print the 20th character.
        char twentyChar = aliceQuote.charAt(19);
        System.out.println(twentyChar);
        // 4. Print the 68th character.
        char sixtyeightChar = aliceQuote.charAt(67);
        System.out.println(sixtyeightChar);
    }
}