package learn.safari.data;

import learn.safari.models.BugSighting;

import java.util.List;

public interface BugSightingRepository {
    List<BugSighting> findAll();

    BugSighting findById(int sightingId);

    BugSighting add(BugSighting sighting);

    boolean update(BugSighting sighting);

    boolean deleteById(int sightingId);
}
