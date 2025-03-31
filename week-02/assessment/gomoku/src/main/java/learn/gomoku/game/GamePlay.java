package learn.gomoku.game;
import learn.gomoku.players.Player;
import learn.gomoku.players.RandomPlayer;
import learn.gomoku.players.HumanPlayer;

import java.util.List;
import java.util.Random;

import java.util.Scanner;


public class GamePlay extends GameBoard {


    public void run() {
        Scanner console = new Scanner(System.in);
        Random random = new Random();

        boolean playAgain;

        do {
            System.out.println("Lets Play Gomoku!");
            System.out.println("=".repeat(50));
            //player one set up
            System.out.println("Player 1 is: ");
            System.out.println("1. Human");
            System.out.println("2. Random Player");
            System.out.print("Select [1-2]: ");
            String player1type = (console.nextLine());
            Player player1 = null;
            switch (player1type) {
                case "1":
                    System.out.print("Enter Player 1's name: ");
                    String player1Name = console.nextLine();
                    player1 = new HumanPlayer(player1Name);
                    break;
                case "2":
                    player1 = new RandomPlayer();
                    break;
            }// player 2 set up
            System.out.println("Player 2 is: ");
            System.out.println("1. Human");
            System.out.println("2. Random Player");
            System.out.print("Select [1-2]: ");
            String player2type = (console.nextLine());
            Player player2 = null;
            switch (player2type) {
                case "1":
                    System.out.print("Enter Player 2's name: ");
                    String player2Name = console.nextLine();
                    player2 = new HumanPlayer(player2Name);
                    break;
                case "2":
                    player2 = new RandomPlayer();
                    break;
            }
            // figure out who the first player will be
            //black stone always goes first
            Gomoku game;
            if (random.nextBoolean()) {
                game = new Gomoku(player1, player2);
            } else {
                game = new Gomoku(player2, player1);
            }

//            Gomoku game = new Gomoku(player1, player2);
//            if(game.isBlacksTurn()){
//                game = new Gomoku(player1, player2);
//            }else {
//                game = new Gomoku(player2, player1);
//            }
            Player currentPlayer = game.getCurrent();
            if (currentPlayer != null) {
                System.out.println("Randomizing.......");
                System.out.println("=".repeat(50));
                System.out.println(currentPlayer.getName() + " will go first! Your Color is Black.");
            }
            // Generate the board
            GameBoard gameBoard = new GameBoard();

            //Main Game Loop

            while (!game.isOver()) {
                System.out.println(gameBoard.printGameBoard(game));

                //generate move
                Stone currentStone;
                boolean validMove = false;
                List<Stone> previousMoves = game.getStones();
                while (!validMove) {
                    // if move is not null/ its a random player
                    if (currentPlayer != null) {
                        currentStone = currentPlayer.generateMove(previousMoves);
                        if (currentStone == null) {// if human player prompt for move
                            currentStone = promptForMove(console, currentPlayer, game.isBlacksTurn());
                        }
                        Result result = game.place(currentStone);
                        if (result.isSuccess()) {
                            validMove = true;
                        } else {
                            System.out.println(result.getMessage());
                        }
                    }
                    //switch players
                    currentPlayer = game.getCurrent();
                }
            }// Final results
                System.out.println(gameBoard.printGameBoard(game));
                if (game.getWinner() != null) { // if winner is NOT null, print game over
                    System.out.println("Game over! The winner is: " + game.getWinner().getName());
                }else {
                    System.out.println("Game Over! Its a tie.");
            }
                System.out.print("Would you like to play again? (y/n): ");
                playAgain = console.nextLine().equalsIgnoreCase("y");
            } while (playAgain) ;
        }




        private Stone promptForMove (Scanner console, Player player,boolean isBlacksTurn){
            System.out.print(player.getName() + ", enter row: ");
            int row = console.nextInt();
            row -= 1;
            System.out.print(player.getName() + ", enter a column: ");
            int col = console.nextInt();
            col -= 1;
            console.nextLine();
            return new Stone(row, col, isBlacksTurn);
        }

}


























