package learn.models;

import java.util.Random;

public class Game {
    private int secretNumber;
    private int attempts;
    private boolean isGameOver;

    public Game(int secretNumber, int attempts, boolean isGameOver) {
        this.secretNumber = secretNumber;
        this.attempts = attempts;
        this.isGameOver = isGameOver;
    }

    public Game() {
        startNewGame();
    }
public void startNewGame(){
        this.secretNumber = new Random().nextInt(100)+1;
        this.attempts = 10;
        this.isGameOver = false;

}
public String makeGuess(int guess){
    if(isGameOver){
        System.out.println("Game over! The secret number was: " + secretNumber);
    } attempts --;
    if(guess == secretNumber) {
        isGameOver = true;
        return ("You win! That's the secret number:  " + secretNumber);
    } if (attempts == 0){
        isGameOver = true;
                return("You ran out of attempts. Game over! The secret number was: " + secretNumber);
    }if (guess < secretNumber){
            return "That number is too low, you have  " + attempts + " attempts left!";
    }else {
            return "That number is too high, you have  " + attempts + " attempts left!";
        }
    }



    public int getSecretNumber() {
        return secretNumber;
    }

    public void setSecretNumber(int secretNumber) {
        this.secretNumber = secretNumber;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }
}
