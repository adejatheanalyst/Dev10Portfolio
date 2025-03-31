package learn.gomoku;
import learn.gomoku.game.Gomoku;
import learn.gomoku.game.Result;
import learn.gomoku.game.Stone;
import learn.gomoku.players.HumanPlayer;
import learn.gomoku.players.Player;
import learn.gomoku.players.RandomPlayer;


import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

    public class App {
        private Gomoku game;
        private Player player1;
        private Player player2;
        private JFrame frame;
        private JPanel boardPanel;
        private JTextArea logArea;

        public App() {
            setupGame();
        }


        private void setupGame() {
            // Create the main frame
            frame = new JFrame("Gomoku Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // Log area for displaying messages
            logArea = new JTextArea(10, 30);
            logArea.setEditable(false);
            JScrollPane logScrollPane = new JScrollPane(logArea);

            // Board panel
            boardPanel = new JPanel();
            boardPanel.setLayout(new GridLayout(15, 15));

            // Setup players
            configurePlayers();

            // Initialize game instance
            game = new Gomoku(player1, player2);

            // Show the board
            renderBoard();

            // Add components to frame
            frame.add(boardPanel, BorderLayout.CENTER);
            frame.add(logScrollPane, BorderLayout.SOUTH);
            frame.pack();
            frame.setVisible(true);

            // Start game
            startGame();
        }

        private void configurePlayers() {
            Random random = new Random();
            String[] playerTypes = {"Human", "Random"};

            // Configure Player 1
            String p1Type = (String) JOptionPane.showInputDialog(
                    null, "Select Player 1 type:", "Player 1",
                    JOptionPane.QUESTION_MESSAGE, null, playerTypes, playerTypes[0]);
            if (p1Type.equals("Human")) {
                String name = JOptionPane.showInputDialog(null, "Enter Player 1's name:", "Player 1", JOptionPane.PLAIN_MESSAGE);
                player1 = new HumanPlayer(name);
            } else {
                String name = "Bot-" + (random.nextInt(9000) + 1000);
                player1 = new RandomPlayer();
            }

            // Configure Player 2
            String p2Type = (String) JOptionPane.showInputDialog(
                    null, "Select Player 2 type:", "Player 2",
                    JOptionPane.QUESTION_MESSAGE, null, playerTypes, playerTypes[0]);
            if (p2Type.equals("Human")) {
                String name = JOptionPane.showInputDialog(null, "Enter Player 2's name:", "Player 2", JOptionPane.PLAIN_MESSAGE);
                player2 = new HumanPlayer(name);
            } else {
                String name = "Bot-" + (random.nextInt(9000) + 1000);
                player2 = new RandomPlayer();
            }
        }

        private void renderBoard() {
            boardPanel.removeAll();

            for (int row = 0; row < 15; row++) {
                for (int col = 0; col < 15; col++) {
                    JButton cellButton = new JButton(" ");
                    int finalRow = row;
                    int finalCol = col;
                    cellButton.addActionListener(e -> handleMove(finalRow, finalCol));
                    boardPanel.add(cellButton);
                }
            }

            boardPanel.revalidate();
            boardPanel.repaint();
        }

        private Stone handleMove(int row, int col) {
            Stone currentStone = null;
            boolean validMove = false;
            List<Stone> previousMoves = game.getStones();
            while (!validMove) {
                // if move is not null/ its a random player
                if (player1 != null) {
                    currentStone = player1.generateMove(previousMoves);
                    if (currentStone == null) {// if human player prompt for move
                        currentStone = handleMove(row, col);
                    }
                    Result result = game.place(currentStone);
                    if (result.isSuccess()) {
                        validMove = true;
                    } else {
                        System.out.println(result.getMessage());
                    }
                }
                //switch players
                player1 = game.getCurrent();
            }
            return currentStone;
        }
        

            private void startGame () {
                logArea.append("Game started!\n");
                renderBoard();

                while (!game.isOver()) {
                    Player currentPlayer = game.getCurrent();

                    if (currentPlayer instanceof RandomPlayer) {
                        boolean success = false;
                        List<Stone> previousMoves = game.getStones();
                        while (!success) {
                            Stone move = ((RandomPlayer) currentPlayer).generateMove(previousMoves); // Simplified
                            success = game.place(move).isSuccess();
                        }
                        logArea.append(currentPlayer.getName() + " (AI) made a move.\n");
                        checkGameState();
                        renderBoard();
                    }
                }

                // Game over
                JOptionPane.showMessageDialog(frame, "Game over! Winner: " + game.getWinner().getName(), "Game Over", JOptionPane.INFORMATION_MESSAGE);

                int playAgain = JOptionPane.showConfirmDialog(frame, "Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                if (playAgain == JOptionPane.YES_OPTION) {
                    setupGame();
                } else {
                    frame.dispose();
                }
            }

            private void checkGameState () {
                if (game.isOver()) {
                    JOptionPane.showMessageDialog(frame, "Game over! Winner: " + game.getWinner().getName(), "Game Over", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            public static void main (String[]args){
                SwingUtilities.invokeLater(App::new);
            }
        }
=======
import learn.gomoku.game.GamePlay;

public class App {

    public static void main(String[] args) {
         new GamePlay().run();

















        }
}

