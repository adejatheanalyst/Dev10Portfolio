package learn.unexplained.data;

import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;

import java.util.List;

public interface EncounterRepository {
    List<Encounter> findAll() throws DataAccessException;

    Encounter add(Encounter encounter) throws DataAccessException;

    boolean deleteById(int encounterId) throws DataAccessException;

     List<Encounter> findByType(EncounterType encounter) throws DataAccessException;

    boolean update(Encounter encounter) throws DataAccessException;

}
