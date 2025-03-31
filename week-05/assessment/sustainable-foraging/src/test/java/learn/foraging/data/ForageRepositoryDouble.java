package learn.foraging.data;

import learn.foraging.models.Forage;

import java.time.LocalDate;
import java.util.List;

import static learn.foraging.TestData.*;

public class ForageRepositoryDouble implements ForageRepository {

    @Override
    public Forage findById(int forageId) {
        if (forageId == 1) {
            return new Forage(1, JAN_01_2023, FORAGER_ONE, EDIBLE, ONE_KILO);
        }
        return null;
    }

    @Override
    public List<Forage> findByDate(LocalDate date) {
        if (date.equals(JAN_01_2023)) {
            return List.of(
                    new Forage(1, JAN_01_2023, FORAGER_ONE, EDIBLE, ONE_KILO),
                    new Forage(3, JAN_01_2023, FORAGER_ONE, INEDIBLE, ONE_KILO),
                    new Forage(2, JAN_01_2023, FORAGER_TWO, MEDICINAL, ONE_KILO)
            );
        }
        return List.of();
    }

    @Override
    public Forage add(Forage forage) {
        return new Forage(5, JAN_01_2023, FORAGER_ONE, EDIBLE, ONE_KILO);
    }

    @Override
    public boolean update(Forage forage) {
        return forage.getId() == 1;
    }
}
