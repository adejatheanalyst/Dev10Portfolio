public class Exercise04 {

    public static void main(String[] args) {
        System.out.println(getFirstVowel("magnificent")); // Expected: a
        System.out.println(getFirstVowel("winsome")); // Expected: i
        System.out.println(getFirstVowel("xxx")); // Expected:

        // 2. Call getFirstVowel at least one more time.
        System.out.println(getFirstVowel("moesha"));
        System.out.println(getFirstVowel("Adrian"));
    }

    // getFirstVowel returns the first vowel in a string as a char.
    // 1. Complete getFirstVowel.
    // If no vowel is found, return 0. (As a char, 0 represents the NULL value.)
    public static char getFirstVowel(String value) {
        for (int i = 0; i <value.length(); i++){
            char ch = value.charAt(i);
            if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'){
                return ch;
            }
        }
        return 0;
    }
//    public static boolean isVowel(char c) {
//        // Convert to lowercase for case insensitivity
//        c = Character.toLowerCase(c);
//        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

