package learn.foraging.data;

import learn.foraging.models.Forager;

import java.util.List;

public interface ForagerRepository {
    Forager findById(int id);

    List<Forager> findByLastName(String lastNamePrefix);

    List<Forager> findByState(String stateAbbr);
}
