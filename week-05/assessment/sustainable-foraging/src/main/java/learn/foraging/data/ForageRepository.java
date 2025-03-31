package learn.foraging.data;

import learn.foraging.models.Forage;

import java.time.LocalDate;
import java.util.List;

public interface ForageRepository {

    Forage findById(int forageId);

    List<Forage> findByDate(LocalDate date);

    Forage add(Forage forage);

    boolean update(Forage forage);
}
