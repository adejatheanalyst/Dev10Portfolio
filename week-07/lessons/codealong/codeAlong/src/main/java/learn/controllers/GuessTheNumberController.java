package learn.controllers;

import learn.models.Game;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/game")
public class GuessTheNumberController {
    private final Game game = new Game();

    static Map<Integer, Integer> numbers;
    /*
    Guess the number:
    Generate a random number between 1 and 100. The user makes a series of guess.
    For each guess, the game gives feedback: too high, too low, or got it!
     */
@PostMapping("/start")
    public ResponseEntity<String> startGame(){
    game.startNewGame();
    return new ResponseEntity<>("New Game Started! Guess a number between 1 and 100.", HttpStatus.OK);
}
    @PostMapping("/start")
    public ResponseEntity<Integer> startGame2(){
 Integer newSession = numbers.size() + 1;
 Integer numberToGuess = (new Random()).nextInt(100);
 numbers.put(newSession, numberToGuess);
 return new ResponseEntity<>(newSession, HttpStatus.OK);
    }
    @PutMapping("/{sessionNumber}")
    public  ResponseEntity<String> playGame(@PathVariable Integer sessionNumber, @RequestBody Map<String, Integer> guessMap){
    Integer guess = guessMap.get("guess");
    Integer numberToGuess = numbers.get(sessionNumber);

    if(guess > numberToGuess){
        return new ResponseEntity<>("Too high!", HttpStatus.BAD_REQUEST);
    }
    }



@PostMapping("/guess")
    public ResponseEntity<String> makeGuess(@RequestBody Map<String, Integer> request){
    Integer guess = request.get("number");
  String response = game.makeGuess(guess);
    return new ResponseEntity<>(response, HttpStatus.OK);
}


}
