import java.util.HashMap;
import java.util.Map.Entry;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
//
//        System.out.println("cspacey0@myspace.com".hashCode());
//        System.out.println("sbretherick1@va.gov".hashCode());
//        System.out.println("zcripwell2@dot.gov".hashCode());
//        System.out.println("spyatt3@marketwatch.com".hashCode());
//        System.out.println("oranahan4@bing.com".hashCode());

// All keys should map to unique values.
// The following are from our examples.
//        HashMap<Integer, Character> numberToLetter = new HashMap<>();
//        HashMap<Integer, Character> numberToAB = new HashMap<>();
//        HashMap<Character, String> letterToFruit = new HashMap<>();
//        HashMap<String, Login> emailToLogin = new HashMap<>();
// put method
//        HashMap<Character, String> letterToFruit = new HashMap<>();
//        letterToFruit.put('A', "Apple");
//        letterToFruit.put('B', "Banana");
//        letterToFruit.put('C', "Cherry");
//        letterToFruit.put('D', "Durian");
////
////        HashMap<String, Login> emailToLogin = new HashMap<>();
////        Login one = new Login("cspacey0@myspace.com", "Carita", "Spacey");
////        Login two = new Login("sbretherick1@va.gov", "Sammy", "Bretherick");
////        emailToLogin.put(one.getEmailAddress(), one);
////        emailToLogin.put(two.getEmailAddress(), two);
//
//        HashMap<String, Login> emailToLogin = new HashMap<>();
//        emailToLogin.put("cspacey0@myspace.com", new Login("cspacey0@myspace.com", "Carita", "Spacey"));
//        emailToLogin.put("sbretherick1@va.gov", new Login("sbretherick1@va.gov", "Sammy", "Bretherick"));
//        emailToLogin.put("zcripwell2@dot.gov", new Login("zcripwell2@dot.gov", "Zia", "Cripwell"));
//        emailToLogin.put("spyatt3@marketwatch.com", new Login("spyatt3@marketwatch.com", "Sianna", "Pyatt"));
//        emailToLogin.put("oranahan4@bing.com", new Login("oranahan4@bing.com", "Odelinda", "Ranahan"));
//
//        System.out.println("Loop over values:");
//        System.out.println("=====================");
//        for (Login l : emailToLogin.values()) {
//            System.out.printf("%s %s%n", l.getFirstName(), l.getLastName());
//        }
//
//        System.out.println("Loop over keySet:");
//        System.out.println("=====================");
//        for (String key : emailToLogin.keySet()) {
//            Login l = emailToLogin.get(key);
//            System.out.printf("%s %s%n", l.getFirstName(), l.getLastName());
//        }
//
//        System.out.println("Loop over entrySet:");
//        System.out.println("=====================");
//        for (Entry<String, Login> entry : emailToLogin.entrySet()) {
//            System.out.printf("Key: %s, Value: %s %s%n",
//                    entry.getKey(),
//                    entry.getValue().getFirstName(),
//                    entry.getValue().getLastName());
//        }
//        letterToFruit.remove('B');
//        letterToFruit.remove('C');
//        letterToFruit.remove('X');
//        for (char key : letterToFruit.keySet()) {
//            System.out.printf("Key: %s, Value: %s%n", key, letterToFruit.get(key));
//        }

        HashSet<Integer> uniqueNumbers = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            uniqueNumbers.add(1);
            uniqueNumbers.add(2);
            uniqueNumbers.add(3);
        }
        System.out.println("size: " + uniqueNumbers.size());

        for (int n : uniqueNumbers) {
            System.out.println(n);
        }

        HashSet<String> uniqueColors = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            uniqueColors.add("red");
            uniqueColors.add("blue");
            uniqueColors.add("yellow");
        }
        System.out.println("size: " + uniqueColors.size());

        for (String color : uniqueColors) {
            System.out.println(color);
        }

        HashSet<Login> uniqueLogins = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            uniqueLogins.add(new Login("cspacey0@myspace.com", "Carita", "Spacey"));
            uniqueLogins.add(new Login("sbretherick1@va.gov", "Sammy", "Bretherick"));
            uniqueLogins.add(new Login("zcripwell2@dot.gov", "Zia", "Cripwell"));
        }
        System.out.println("size: " + uniqueLogins.size());

        for (Login login : uniqueLogins) {
            System.out.println(login.getEmailAddress());
        }






    }
}
