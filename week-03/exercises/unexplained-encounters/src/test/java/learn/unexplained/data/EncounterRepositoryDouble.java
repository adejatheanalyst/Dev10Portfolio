package learn.unexplained.data;

import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;

import java.util.List;
import java.util.ArrayList;

public class EncounterRepositoryDouble implements EncounterRepository {
    public static int EXISTING_ID = 2;
    public static EncounterType EXISTING_ENCOUNTER_TYPE = EncounterType.CREATURE;

    private ArrayList<Encounter> encounters = new ArrayList<>();



  public EncounterRepositoryDouble(){
      encounters.add(new Encounter(1, EncounterType.UFO,"1/02/2022", "test description1", 5));
      encounters.add(new Encounter(2, EncounterType.SOUND,"02/06/2024", "test description1", 3));
      encounters.add(new Encounter(3, EncounterType.VISION,"07/08/1947", "test description1", 8));
      encounters.add(new Encounter(4, EncounterType.CREATURE,"01/22/2025", "test description1", 5));

  }


    @Override
    public List<Encounter> findAll() throws DataAccessException {
        return encounters;
    }

    @Override
    public Encounter add(Encounter encounter) throws DataAccessException {
        return encounter;
    }


    public boolean deleteById(int encounterId) throws DataAccessException {
        return encounterId == 12;
    }


    @Override
    public List<Encounter> findByType(EncounterType Encounter) throws DataAccessException {
        return List.of();
    }

    @Override
    public boolean update(Encounter encounter) throws DataAccessException {
        return true;
    }
}
