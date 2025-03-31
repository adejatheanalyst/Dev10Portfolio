import java.util.Scanner;

public class MazeGame {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        boolean hasKey = false;

        boolean isLoop = true;

        boolean inputIsValid = true;

        boolean reachedDeadend = false;

        int input = 0;

        System.out.println("Welcome to the Maze game!");

        while(isLoop) {

            if (!reachedDeadend) {

                System.out.println("You're stranded.\nYou have two directions to start.\nWhat will your choice be? ");

                System.out.println("1. Left\n2. Right");

            } else {

                System.out.println("You hit a dead end.\nBack to the start for you.\nWill you try again or give up?");

                if(hasKey){

                    System.out.println("You now have the key... find the door and leave.");

                }

                System.out.println("1. Left\n2. Right\n3. Quit");

            }

            input = Integer.parseInt(console.nextLine());

            do {

                switch (input) {

                    case 1:// left

                        System.out.println("You went left?");

                        do {

                            System.out.println("You walk into the the left direction passage, and reach another choice.");

                            System.out.println("1. Go left\n2. Go right");

                            input = Integer.parseInt(console.nextLine());

                            if (input == 1) { // left left

                                System.out.println("You start walking to your left, and down the hall see an opening on the right.");

                                System.out.println("1. Go straight\n2. Go right");

                                input = Integer.parseInt(console.nextLine());

                                if (input == 1) { // left left straight

                                    System.out.println("You continue forward, and see another opening on the right.");

                                    System.out.println("1. Go straight\n2. Go right");

                                    input = Integer.parseInt(console.nextLine());

                                    if (input == 1) { // left left straight straight

                                        System.out.println("You keep walking forward, and reach a dead end...");

                                        reachedDeadend = true;

                                    } else if (input == 2) { // left left straight right

                                        System.out.println("You turn right and soon after, reach a dead end...");

                                        reachedDeadend = true;

                                    }

                                } else if (input == 2) { // left left right

                                    System.out.println("You take the right, and it's a dead end...");

                                    reachedDeadend = true;

                                }

                            } else if (input == 2) { // left right

                                System.out.println("Taking the path on the right, you continue as it turns left.");

                                System.out.println("Soon after you reach another decision.");

                                System.out.println("1. Go left\n2. Go right");

                                input = Integer.parseInt(console.nextLine());

                                if (input == 1) { // left right left

                                    System.out.println("You swerve left, and reach a dead end...");

                                    reachedDeadend = true;

                                } else if (input == 2) { //left right right

                                    System.out.println("Following the path on the right.");

                                    System.out.println("It curves left and before you is the key...");

                                    System.out.println("Added ***Key*** to inventory.");

                                    hasKey = true;

                                    reachedDeadend = true;

                                }

                            } else {

                                System.out.println("Please input a valid option!");

                                inputIsValid = false;

                            }

                        } while (!inputIsValid);

                        break;

                    case 2:// right

                        System.out.println("You went right?");

                        System.out.println("You walk into the the right direction passage and see a passage open on your right");

                        System.out.println("You continue down the right passage and reach the end with an opening on your left");

                        System.out.println("You walk down that left passage and see an opening on your left and an opening on your right. You also see an opening in front of you.");

                        System.out.println("1. Go left\n2. Go right \n3. Go Forward");

                        input = Integer.parseInt(console.nextLine());

                        if (input == 1) {// left

                            System.out.println("You turn left and soon after, reach a dead end...");

                            reachedDeadend = true;

                        } else if (input == 2) {

                            System.out.println("You turn right and soon after, reach a dead end...");

                            reachedDeadend = true;

                        } else if (input == 3) {

                            System.out.println("You continue forward and reach another 3 openings. ");

                            System.out.println("1. Go left\n2. Go right \n3. Go Forward");

                            input = Integer.parseInt(console.nextLine());

                            if (input == 1) {

                                System.out.println("You turn left and soon after you see a passage on your left");

                                System.out.println("1. Go left\n2. Go Forward ");

                                input = Integer.parseInt(console.nextLine());

                                if (input == 1) {

                                    System.out.println("You turn left and soon after, reach a dead end...");

                                    reachedDeadend = true;

                                } else if (input == 2) {

                                    System.out.println("You continue forward and see an opening on your right and in front of you. ");

                                    System.out.println("1. Go right\n2. Go Forward ");

                                    input = Integer.parseInt(console.nextLine());

                                    if (input == 1) {

                                        System.out.println("You turn right and take another right and see a door.");

                                        if(hasKey){

                                            System.out.println("You use the key and make your escape!\nYou win.");

                                            isLoop = false;

                                        }

                                        else{

                                            System.out.println("You walk up to the door and open it...");

                                            System.out.println("It's locked, you need to find a ***key***.");

                                            reachedDeadend = true;

                                        }

                                    } else if (input == 2) {

                                        System.out.println("You continue forward and reach a dead end....");

                                        reachedDeadend = true;

                                    }

                                }

                            } else if (input == 2) {

                                System.out.println("You head right and, it's a dead end...");

                                reachedDeadend = true;

                            } else if (input == 3) {

                                System.out.println("You journey straight ahead, taking a slight turn to the right.");

                                System.out.println("Another split do you go straight or the left?");

                                System.out.println("1. Go left\n2. Go straight");

                                input = Integer.parseInt(console.nextLine());

                                if (input == 1){

                                    System.out.println("You venture left and it's a dead end...");

                                    reachedDeadend = true;

                                } else if (input == 2) {

                                    System.out.println("You head straight into a dead end...");

                                    reachedDeadend = true;

                                }

                            }

                        }

                        break;

                    case 3:

                        if (reachedDeadend){

                            System.out.println("You are forever trapped in the maze.");

                            isLoop = false;

                        } else{

                            System.out.println("Please input a valid option!");
                            inputIsValid = false;

                        }

                        break;

                    default:
                        System.out.println("Please input a valid option!");
                        inputIsValid = false;

                }

            } while(!inputIsValid);

        }

    }

}
