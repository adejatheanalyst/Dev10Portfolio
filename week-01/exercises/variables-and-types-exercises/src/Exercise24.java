public class Exercise24 {

    public static void main(String[] args) {
        String knockKnock = "Knack, knack. \n"
                + "Wha's there?\n"
                + "Interrupting caw.\n"
                + "Interrupti...\n"
                + "Maa!";

        // 1. Use the replace method to replace all occurrences of "a" with "o" from knockKnock.
        // 2. Assign the result to a new variable.
        String aBecomesO = knockKnock.replace('a', 'o');
        // 3. Print it.
        System.out.println(aBecomesO);

        // Expected Output:
        // Knock, knock.
        // Who's there?
        // Interrupting cow.
        // Interrupti...
        // Moo!
    }
}
