package learn;

import java.util.Objects;

public class BoardGame {

    private final String name;
    private final int minPlayers;
    private final int maxPlayers;
    private final String category;

    public BoardGame(String name, int minPlayers, int maxPlayers, String category) {
        this.name = name;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardGame boardGame = (BoardGame) o;
        return minPlayers == boardGame.minPlayers &&
                maxPlayers == boardGame.maxPlayers &&
                Objects.equals(name, boardGame.name) &&
                Objects.equals(category, boardGame.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, minPlayers, maxPlayers, category);
    }

    @Override
    public String toString() {
        return "BoardGame{" +
                "Name= '" + name + '\'' +
                ", MinPlayers= " + minPlayers +
                ", MaxPlayers= " + maxPlayers +
                ", Category= '" + category + '\'' +
                '}';
    }
}
