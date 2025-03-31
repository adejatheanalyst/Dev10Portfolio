package learn.rpg.data;

import learn.rpg.models.Player;

public class PlayerRepository extends BaseRepository<Player> {

    public PlayerRepository(String filePath) {
        super(filePath, 6, true);
    }

    @Override
    protected Player deserialize(String[] fields) {
        Player player = new Player();
        player.setFirstName(fields[0]);
        player.setLastName(fields[1]);
        player.setCity(fields[2]);
        player.setCountry(fields[3]);
        player.setRegion(fields[4]);
        player.setPostalCode(fields[5]);
        return player;
    }
}

