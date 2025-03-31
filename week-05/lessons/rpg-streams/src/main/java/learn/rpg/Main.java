package learn.rpg;

import learn.rpg.data.NameRepository;
import learn.rpg.data.PlayerRepository;
import learn.rpg.domain.PlayerService;
import learn.rpg.models.Hero;
import learn.rpg.models.Player;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        // getPlayers()
//        Stream<Player> playerStream = getPlayers().stream();
//        Stream<Player> playersFromThailand = playerStream.filter(
//                player -> player.getCountry().equalsIgnoreCase("Thailand"));
//        playersFromThailand.forEach(System.out::println);
//        getPlayers().stream()
//                .filter(player -> player.getCountry().equalsIgnoreCase("Thailand"))
//                .forEach(System.out::println);
//        getPlayers().stream()
//                .filter(player -> player.getLastName().startsWith("B")) // <-- this changed
//                .forEach(System.out::println);
//        getPlayers().stream()
//                .filter(player -> player.getHeroes().isEmpty())
//                .forEach(System.out::println);

// Skip over 100 of the 106 players who have a last name that starts with "B"
// and print the last 6.
//        getPlayers().stream()
//                .filter(player -> player.getLastName().startsWith("B"))
//                .skip(100)
//                .forEach(System.out::println);

// Only print the first 5 players who have a last name that starts with "B".
//        getPlayers().stream()
//                .filter(player -> player.getLastName().startsWith("B"))
//                .limit(5)
//                .forEach(System.out::println);
////
//// Skip the first 500 players, then print the next 10 players.
//        getPlayers().stream()
//                .skip(500)
//                .limit(10)
//                .forEach(System.out::println);
        // findFirst()
//        Optional<Player> firstThaiPlayer = getPlayers().stream()
//                .filter(player -> player.getCountry().equalsIgnoreCase("Thailand"))
//                .findFirst();
//
//        Optional<Player> firstMarsPlayer = getPlayers().stream()
//                .filter(player -> player.getCountry().equalsIgnoreCase("Mars")) // result is an empty stream
//                .findFirst();
//
//        if (firstThaiPlayer.isPresent()) {
//            Player p = firstThaiPlayer.get();
//            System.out.println("Found a player from Thailand: " + p.getLastName());
//        } else {
//            System.out.println("There are no players from Thailand.");
//        }
//
//        if (firstMarsPlayer.isPresent()) {
//            Player p = firstMarsPlayer.get();
//            System.out.println("Found a player from Mars: " + p.getLastName());
//        } else {
//            System.out.println("There are no players from Mars.");
//        }
        //COLLECT
        // toList produces a List interface
//        List<Player> playersWithNoHero = getPlayers().stream()
//                .filter(player -> player.getHeroes().isEmpty())
//                .collect(Collectors.toList());
//
//// for a concrete collection, use the toCollection method
//        ArrayList<Player> playersFromNigeria = getPlayers().stream()
//                .filter(player -> player.getCountry().equals("Nigeria"))
//                .collect(Collectors.toCollection(ArrayList::new));
//SORTING
        // Lambda that returns an int:
// sorts by the length of the country name.
//        getPlayers().stream()
//                .sorted((a, b) -> a.getCountry().length() - b.getCountry().length())
//                .forEach(System.out::println);

// lambda for sorting by Country descending
//        getPlayers().stream()
//                .sorted((a, b) -> a.getCountry().compareTo(b.getCountry()))
//                .forEach(System.out::println);
//
//// Comparator for sorting by Country descending
//        getPlayers().stream()
//                .sorted(Comparator.comparing(Player::getCountry))
//                .forEach(System.out::println);
//
//// Comparator for sorting by Country ascending
//        getPlayers().stream()
//                .sorted(Comparator.comparing(Player::getCountry).reversed())
//                .forEach(System.out::println);
//
//// sort by last name, then first name
//        getPlayers().stream()
//                .sorted(Comparator.comparing(Player::getLastName)
//                        .thenComparing(Player::getFirstName))
//                .forEach(System.out::println);
////TRANSFORM
//        Stream<Player> players = getPlayers().stream();
//// Mapping lambda: a Player is the input, String is the output
//        Stream<String> fullNames = players.map(p -> p.getFirstName() + " " + p.getLastName());
//        fullNames.forEach(System.out::println);
//
//// Or
//        getPlayers().stream()
//                .map(p -> p.getFirstName() + " " + p.getLastName())
//                .forEach(System.out::println);
//        getPlayers().stream()
//                .map(p -> p.getHeroes().size() == 0 ? null : p.getHeroes().get(0))
//                .forEach(System.out::println);
//
//
//        int[] heroCounts = getPlayers().stream()
//                .mapToInt(p -> p.getHeroes().size())
//                .toArray();


//        Stream<Hero> heroes = getPlayers().stream()
//                .flatMap(p -> p.getHeroes().stream());
//
//        heroes.forEach(System.out::println);
//
//        List<Player> players = getPlayers();
//        List<Hero> allHeroes = players.stream()
//                .flatMap(p -> p.getHeroes().stream())
//                .collect(Collectors.toList());
//
//        long playerCount = players.stream()
//                .count();
//
//        String sevenLastNames = players.stream()
//                .map(p -> p.getLastName())
//                .limit(7)
//                .collect(Collectors.joining(","));
//
//// Collectors.summarizingInt returns an IntSummaryStatistics object.
//// From that, we can determine various aggregate values:
//// getCount, getMin, getMax, getSum, getAverage.
//        long sumLevels = allHeroes.stream()
//                .collect(Collectors.summarizingInt(Hero::getLevel))
//                .getSum();
//
//        double averageLevel = allHeroes.stream()
//                .collect(Collectors.summarizingInt(Hero::getLevel))
//                .getAverage();
//
//        System.out.println("Player Count: " + playerCount);
//        System.out.println("7 Last Names: " + sevenLastNames);
//        System.out.println("Sum of Levels: " + sumLevels);
//        System.out.println("Average Level: " + averageLevel);
//ANY MATCH ALL MATCH
//        List<Player> players = getPlayers();
//
//        boolean anyPlayerIsFromChile = players.stream()
//                .anyMatch(p -> p.getCountry().equals("Chile"));
//
//        boolean allPlayersAreFromChile = players.stream()
//                .allMatch(p -> p.getCountry().equals("Chile"));
//
//        boolean allPlayersHaveFirstNames = players.stream()
//                .allMatch(p -> !p.getFirstName().isBlank());
//
//        System.out.println("At least one player from Chile: " + anyPlayerIsFromChile);
//        System.out.println("All players are from Chile: " + allPlayersAreFromChile);
//        System.out.println("All players have first names: " + allPlayersHaveFirstNames);
//        getPlayers().stream()
//                .filter(p -> p.getHeroes().stream()
//                        .anyMatch(h -> h.getProfession() == Profession.TINKERER)) // check for TINKERER
//                .forEach(System.out::println);
//
//        long playerWithTinkererCount = getPlayers().stream()
//                .filter(p -> p.getHeroes().stream()
//                        .anyMatch(h -> h.getProfession() == Profession.TINKERER))
//                .count();
//
//        System.out.println(playerWithTinkererCount);
//// GROUPING
//        Map<String, Long> playersByCountry = getPlayers().stream()
//                .collect(Collectors.groupingBy(Player::getCountry,
//                        Collectors.counting()));
//
//        for (String country : playersByCountry.keySet()) {
//            System.out.println(country + ": " + playersByCountry.get(country));
//        }
//
//        Map<Profession, Long> professionCount = getPlayers().stream()
//                .flatMap(p -> p.getHeroes().stream())
//                .collect(Collectors.groupingBy(Hero::getProfession,
//                        Collectors.counting()));
//
//        for (Profession profession : professionCount.keySet()) {
//            System.out.println(profession + ": " + professionCount.get(profession));
//        }
//
//        Map<Profession, Double> avgCoinsPerProfession = getPlayers().stream()
//                .flatMap(p -> p.getHeroes().stream())
//                .collect(Collectors.groupingBy(Hero::getProfession,
//                        Collectors.averagingDouble(h -> h.getCoin().doubleValue()))); // <- this changed
//
//        for (Profession profession : avgCoinsPerProfession.keySet()) {
//            System.out.printf("%s: %.2f%n", profession, avgCoinsPerProfession.get(profession));
//        }

// streams with maps
        //myMap.entrySet().stream()









    }

    static List<Player> getPlayers() {
        PlayerRepository playerRepo = new PlayerRepository("players.csv");
        NameRepository nameRepo = new NameRepository("characters.csv");
        PlayerService service = new PlayerService(playerRepo, nameRepo);
        return service.generate();
    }
}
