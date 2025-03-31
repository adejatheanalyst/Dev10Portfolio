public class Enums {
    public static void main(String[] args) {
        Move p1Move = Move.ROCK;
        Move p2Move = Move.PAPER;
       p1Move = Move.SCISSORS;
if (p1Move == p2Move){
    System.out.println("Its a tie.");
} else{
    System.out.println("There's going to be a winner and a loser. ");
}
        System.out.println(p1Move);// scissors
        System.out.println(p2Move); // paper
    }
}


