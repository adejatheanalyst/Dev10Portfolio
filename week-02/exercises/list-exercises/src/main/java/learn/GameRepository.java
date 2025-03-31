package learn;

import java.util.ArrayList;
import java.util.Arrays;

public class GameRepository {

    public static ArrayList<BoardGame> getAll() {

        return new ArrayList<>(Arrays.asList(
                new BoardGame("Catan", 3, 4, "Economic"),
                new BoardGame("Monopoly", 2, 8, "Economic"),
                new BoardGame("Dominion", 2, 4, "Card Game"),
                new BoardGame("Splendor", 2, 4, "Card Game"),
                new BoardGame("The Resistance", 5, 10, "Bluffing"),
                new BoardGame("7 Wonders", 2, 7, "City Building"),
                new BoardGame("Gloomhaven", 1, 4, "Adventure"),
                new BoardGame("Betrayal at House on the Hill", 3, 6, "Adventure"),
                new BoardGame("Terraforming Mars", 1, 5, "Economic"),
                new BoardGame("Ravine", 3, 6, "Party Game"),
                new BoardGame("Ultimate Werewolf: Deluxe Edition", 5, 75, "Party Game"),
                new BoardGame("Coup", 2, 6, "Bluffing"),
                new BoardGame("Machi Koro", 2, 4, "City Building")
        ));
    }
}
