import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {

    // constants
    private static int WIDTH = 15;
    private static String WALL_CHARACTER = "||";
    private static String EMPTY_CHARACTER = "  ";

    private final Scanner console = new Scanner(System.in);
    private Hero hero;
    private Treasure treasure;
    private boolean isOver;
    private Monster monster;
    private Traps traps;

    public void run() {
        setUp();
        while (!isOver) {
            printWorld();
            move();
            monster.moveMonster(hero);
        }
        printWorld();
    }
private List<Traps> trapList = new ArrayList<>();

    private void setUp() {
        System.out.print("What is the name of your hero?: ");
        String name = console.nextLine();
        System.out.print("Choose your Character: ");
        String symbol = console.nextLine();
        System.out.print("Choose your Dungeon: ");
        WIDTH = Integer.parseInt(console.nextLine());


        Random rand = new Random();
        int x = rand.nextInt(WIDTH);
        int y = rand.nextInt(WIDTH);

        hero = new Hero(name, x, y, symbol);

        do {
            x = rand.nextInt(WIDTH);
            y = rand.nextInt(WIDTH);
        } while (x == hero.getX() && y == hero.getY());

        treasure = new Treasure(x, y);
        monster = new Monster(x, y);
        traps = new Traps(x,y);
        do {
            x = rand.nextInt(WIDTH);
            y = rand.nextInt(WIDTH);
        } while ((x == hero.getX() && y == hero.getY()) || (x == treasure.getX() && y == treasure.getY()));

       // adding multiple traps
        int numberOfTraps = 10;
        for (int i = 0 ; i < numberOfTraps; i++) {
            do{
                x = rand.nextInt(WIDTH);
                y = rand.nextInt(WIDTH);
            } while ((x == hero.getX() && y == hero.getY()) ||
                    (x == treasure.getX() && y == treasure.getY()) ||
                    (x == monster.getX() && y == monster.getY()) ||
            isTrapAtPosition(x,y));
            trapList.add(new Traps(x,y));

            }
        }


    private void printWorld() {
        // top wall border
        System.out.println(WALL_CHARACTER.repeat(WIDTH + 2));

        for (int row = 0; row < WIDTH; row++) {
            // left wall border
            System.out.print(WALL_CHARACTER);

            for (int col = 0; col < WIDTH; col++) {
                if (row == hero.getY() && col == hero.getX()) {
                    System.out.print(hero.getSymbol());
                } else if (row == treasure.getY() && col == treasure.getX()) {
                    System.out.print("T ");
                } else if (col == monster.getY() && row == monster.getX()) {
                    System.out.print("M ");
                } else if (col == traps.getX() && row == traps.getY()) {
                    System.out.print("X ");
                } else {
                    System.out.print(EMPTY_CHARACTER);
                }
            }


        // right wall border
        System.out.println(WALL_CHARACTER);
              }

        // bottom wall border
        System.out.println(WALL_CHARACTER.repeat(WIDTH + 2));
    }

    private void move() {

        System.out.print(hero.getName() + ", move [WASD]: ");
        String move = console.nextLine().trim().toUpperCase();

        if (move.length() != 1) {
            return;
        }

        switch (move.charAt(0)) {
            case 'W':
                hero.moveUp();
                break;
            case 'A':
                hero.moveLeft();
                break;
            case 'S':
                hero.moveDown();
                break;
            case 'D':
                hero.moveRight();
                break;
        }

        if (hero.getX() < 0 || hero.getX() >= WIDTH
                || hero.getY() < 0 || hero.getY() >= WIDTH) {
            System.out.println(hero.getName() + " touched lava! You lose.");
            isOver = true;
        } else if (hero.getX() == treasure.getX() && hero.getY() == treasure.getY()) {
            System.out.println(hero.getName() + " found the treasure! You win.");
            isOver = true;
        } else if (hero.getX() == monster.getY() && hero.getY() == monster.getX()) {
            System.out.println(hero.getName() + " you were killed by a Monster! You lose! ");
            isOver = true;
        } else if (hero.getX() == traps.getY() && hero.getY() == traps.getX()){
            System.out.println(hero.getName() + " is trapped in the Dungeon forever! Game over!");
            isOver = true;
        }

    }

    // is trap at the position
    boolean isTrapAtPosition(int x, int y){
        for (Traps trap : trapList) {
            if (trap.getX() == x && trap.getY() == y){
                return true;
            }
        }
        return false;
    }

}













