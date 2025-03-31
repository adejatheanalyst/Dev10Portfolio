package learn.rpg.domain;

import learn.rpg.Nation;
import learn.rpg.Profession;
import learn.rpg.data.NameRepository;
import learn.rpg.data.PlayerRepository;
import learn.rpg.models.Hero;
import learn.rpg.models.Name;
import learn.rpg.models.Player;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;

public class PlayerService {

    private final PlayerRepository playerRepository;
    private final NameRepository nameRepository;
    private final Random random = new Random(572479448);
    private List<Name> names;

    public PlayerService(PlayerRepository playerRepository, NameRepository nameRepository) {
        this.playerRepository = playerRepository;
        this.nameRepository = nameRepository;
    }

    public List<Player> generate() {
        List<Player> players = playerRepository.findAll();
        names = nameRepository.findAll();
        populate(players);
        return players;
    }

    private void populate(List<Player> players) {
        for (Player p : players) {
            populate(p);
        }
    }

    private void populate(Player p) {
        int chance = random.nextInt(100);
        int heroCount;
        if (chance < 5) {
            heroCount = 0;
        } else if (chance < 55) {
            heroCount = 1;
        } else if (chance < 75) {
            heroCount = 2;
        } else if (chance < 95) {
            heroCount = 3;
        } else {
            heroCount = 5;
        }

        for (int i = 0; i < heroCount; i++) {
            p.getHeroes().add(makeHero());
        }
    }

    private Hero makeHero() {
        Hero hero = new Hero();
        hero.setName(makeName());
        hero.setProfession(makeProfession());
        hero.setNation(makeNation());
        hero.setLevel(random.nextInt(10) + 1);
        hero.setPower(random.nextInt(50) + 50);
        hero.setWit(random.nextInt(50) + 50);
        hero.setSlipperiness(random.nextInt(50) + 50);
        hero.setCoin(new BigDecimal(random.nextDouble() * 100000.0).setScale(2, RoundingMode.HALF_UP));
        return hero;
    }

    private String makeName() {
        int chance = random.nextInt(100);
        if (chance < 35) {
            return names.get(random.nextInt(names.size())).getFirstName();
        }
        String first = names.get(random.nextInt(names.size())).getFirstName();
        String last = names.get(random.nextInt(names.size())).getLastName();
        return first + " " + last;
    }

    private Profession makeProfession() {
        Profession[] values = Profession.values();
        return values[random.nextInt(values.length)];
    }

    private Nation makeNation() {
        Nation[] values = Nation.values();
        return values[random.nextInt(values.length)];
    }

}
