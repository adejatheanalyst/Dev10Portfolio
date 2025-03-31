package learn.foraging.data;

import learn.foraging.models.Forager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ForagerRepositoryDouble implements ForagerRepository {

    public final static Forager FORAGER = makeForager();

    private final ArrayList<Forager> foragers = new ArrayList<>();

    public ForagerRepositoryDouble() {
        foragers.add(FORAGER);
    }

    @Override
    public Forager findById(int id) {
        return foragers.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Forager> findByLastName(String lastNamePrefix) {
        return foragers;
    }

    @Override
    public List<Forager> findByState(String stateAbbr) {
        return foragers.stream()
                .filter(i -> i.getState().equalsIgnoreCase(stateAbbr))
                .collect(Collectors.toList());
    }

    private static Forager makeForager() {
        Forager forager = new Forager();
        forager.setId(1);
        forager.setFirstName("Jilly");
        forager.setLastName("Sisse");
        forager.setState("GA");
        return forager;
    }

}
