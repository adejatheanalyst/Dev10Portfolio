import java.util.Scanner;

public class CatSimulator {
    public static void main(String[] args) {

        // you are a cat
        // you start inside
        // game asks what do you want to do
        // one option: go outside
        // then you are outside
        // game asks what you want to do
        // one option: go inside
        //while inside you have a new option to take a nap
        // taking a nap exits the game


        // variable to track cat (inside or outside)
        // provide user with instructions and explain game
        // tell user where they are
        // ask user what they want ( provide options)
        // move user based off of option they choose
        // keep game going as long as they want to play.
        Scanner console = new Scanner(System.in);
        String location = "inside";
        System.out.println("Welcome to the Cat Simulator! You're are a cat");
        boolean done = false;

        while(!done) {
        System.out.println("You are currently " + location);

        String otherLocation = "";
        if (location.equals("inside")){
            otherLocation = "outside";
        } else{
            otherLocation = "inside";
        }

        System.out.println("What do you want to do next? { Go Outside; Take a nap}");

        String userCommand = console.nextLine();
        userCommand = userCommand.toLowerCase();

        switch (userCommand){
            case "go outside":
                if (location.equals("outside")){
                    System.out.println("You are already outside");
                }else{
            location = "outside";}
            break;
            case "go inside":
                location = "inside";
                break;
            case "take a nap":
                System.out.println("Goodnight cat!");
                done = true;
                break;
            default:
                System.out.println("I don't understand the command!");
                break;

        }


//        if (userCommand.equalsIgnoreCase("go outside")){
//            location = "outside";
//        } else if (userCommand.equalsIgnoreCase("take a nap")){
//            System.out.println("Goodnight cat!");
//        } else {
//            System.out.println("I don't understand the command!");
        }


    }
}
