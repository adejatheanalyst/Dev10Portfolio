import java.util.ArrayList;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {

//    // create an ArrayList with a default capacity
//    ArrayList<SoccerPlayer> one = new ArrayList<SoccerPlayer>();
//    // create an ArrayList with an explicit capacity
//    ArrayList<SoccerPlayer> two = new ArrayList<SoccerPlayer>(23);
//    // create an ArrayList based on another collection
//    ArrayList<SoccerPlayer> three = new ArrayList<SoccerPlayer>(two);
//// generic types can only track reference types
//ArrayList<Integer> integers = new ArrayList<Integer>();
//    ArrayList<Double> doubles = new ArrayList<Double>();
//    ArrayList<Boolean> booleans = new ArrayList<Boolean>();
//
//    // another way to code them
//    ArrayList<SoccerPlayer> players = new ArrayList<>();
//    ArrayList<Integer> numbers = new ArrayList<>();

 //add methods appends elements at end of a list
//        ArrayList<SoccerPlayer> goalKeepers = new ArrayList<>();
//        goalKeepers.add(new SoccerPlayer(1, "Alyssa", "Naeher", "GK"));
//        goalKeepers.add(new SoccerPlayer(18, "Ashlyn", "Harris", "GK"));
//        goalKeepers.add(new SoccerPlayer(21, "Adrianna", "Franch", "GK"));
//
//        ArrayList<Integer> evenNumbers = new ArrayList<>();
//// Primitive types can be used, but they're automatically "boxed"
//// into their wrapper type.
//        evenNumbers.add(2);
//        evenNumbers.add(4);
//        evenNumbers.add(6);

        //. add method
//        ArrayList<SoccerPlayer> goalKeepers = new ArrayList<>();
//        goalKeepers.add(new SoccerPlayer(1, "Alyssa", "Naeher", "GK"));
//        goalKeepers.add(new SoccerPlayer(18, "Ashlyn", "Harris", "GK"));
//
//        SoccerPlayer franch = new SoccerPlayer(21, "Adrianna", "Franch", "GK");
//// Add Adrianna Franch to index 0.
//// She's now the first element.
//        goalKeepers.add(0, franch);
//
//        ArrayList<Integer> evenNumbers = new ArrayList<>();
//        evenNumbers.add(2);
//        evenNumbers.add(6);
//
//// Add 4 in between 2 and 6, at index 1.
//        evenNumbers.add(1, 4);
//
//        System.out.println(goalKeepers);

        // addALL method
//        ArrayList<SoccerPlayer> goalKeepers = new ArrayList<>();
//        goalKeepers.add(new SoccerPlayer(1, "Alyssa", "Naeher", "GK"));
//        goalKeepers.add(new SoccerPlayer(18, "Ashlyn", "Harris", "GK"));
//        goalKeepers.add(new SoccerPlayer(21, "Adrianna", "Franch", "GK"));
//
//        ArrayList<SoccerPlayer> defense = new ArrayList<>();
//        defense.add(new SoccerPlayer(14, "Emily", "Sonnett", "DF"));
//        defense.add(new SoccerPlayer(19, "Crystal", "Dunn", "DF"));
//        defense.add(new SoccerPlayer(20, "Casey", "Short", "DF"));
//
//        ArrayList<SoccerPlayer> players = new ArrayList<>();
//        players.addAll(goalKeepers);
//        players.addAll(defense);



        ArrayList<SoccerPlayer> defense = new ArrayList<>();
        defense.add(new SoccerPlayer(4, "Becky", "Sauerbrunn", "DF"));
        defense.add(new SoccerPlayer(5, "Kelley", "O'Hara", "DF"));
        defense.add(new SoccerPlayer(7, "Abby", "Dahlkemper", "DF"));
        defense.add(new SoccerPlayer(11, "Ali", "Krieger", "DF"));
        defense.add(new SoccerPlayer(12, "Tierna", "Davidson", "DF"));
        defense.add(new SoccerPlayer(14, "Emily", "Sonnett", "DF"));
        defense.add(new SoccerPlayer(19, "Crystal", "Dunn", "DF"));
        defense.add(new SoccerPlayer(20, "Casey", "Short", "DF"));

        SoccerPlayer fifth = defense.get(4);              // the fifth player is index 4
        System.out.println(fifth.getLastName());          // Davidson

        System.out.println(defense.get(6).getLastName()); // Dunn

        ArrayList<Integer> evens = new ArrayList<>(Arrays.asList(2, 4, 6, 8, 10));
        System.out.println(evens.get(0)); // 2
        System.out.println(evens.get(1)); // 4
        System.out.println(evens.get(2)); // 6

        ArrayList<String> cityAnimals = new ArrayList<>();
        System.out.println(cityAnimals.size()); // 0

        cityAnimals.add("Raccoon");
        cityAnimals.add("Possum");
        System.out.println(cityAnimals.size()); // 2

        cityAnimals.add("Turkey");
        System.out.println(cityAnimals.size()); // 3

        cityAnimals.add("Red-tailed Hawk");
        System.out.println(cityAnimals.size()); // 4
// normal for loop
        for (int i = 0; i < defense.size(); i++) {
            SoccerPlayer sp = defense.get(i);
            System.out.printf("%s: %s %s, %s%n", sp.getNumber(), sp.getFirstName(), sp.getLastName(), sp.getPosition());
        }

/* Syntax
for ([data type] [name] : [collection]) {
}

[data type] is the data type for an element in the [collection].
[name] is a variable name that is valid only inside the code block.
[collection] is an iterable collection like lists and arrays.
*/

        for (SoccerPlayer sp : defense) {
            System.out.printf("%s: %s %s, %s%n", sp.getNumber(), sp.getFirstName(), sp.getLastName(), sp.getPosition());
        }

        ArrayList<SoccerPlayer> forwards = new ArrayList<>();
        forwards.add(new SoccerPlayer(2, "Mallory", "Pugh", "FW"));
        forwards.add(new SoccerPlayer(10, "Carli", "Lloyd", "FW"));
        forwards.add(new SoccerPlayer(13, "Lynn", "Williams", "FW"));
        forwards.add(new SoccerPlayer(15, "Megan", "Rapinoe", "FW"));
        forwards.add(new SoccerPlayer(17, "Tobin", "Heath", "FW"));
        forwards.add(new SoccerPlayer(22, "Jessica", "McDonald", "FW"));
        forwards.add(new SoccerPlayer(23, "Christen", "Press", "FW"));

        System.out.println(forwards.size());

        SoccerPlayer removed = forwards.remove(3);
        System.out.printf("%s: %s, %s%n", removed.getNumber(), removed.getLastName(), removed.getPosition());
        removed = forwards.remove(3);
        System.out.printf("%s: %s, %s%n", removed.getNumber(), removed.getLastName(), removed.getPosition());













    }
}
