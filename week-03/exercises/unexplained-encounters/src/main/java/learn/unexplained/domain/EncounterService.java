package learn.unexplained.domain;

import learn.unexplained.data.DataAccessException;
import learn.unexplained.data.EncounterRepository;
import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;

import java.util.List;
import java.util.Objects;

public class EncounterService {

    private final EncounterRepository repository;

    public EncounterService(EncounterRepository repository) {
        this.repository = repository;
    }

    public List<Encounter> findAll() throws DataAccessException {
        return repository.findAll();
    }

    public EncounterResult add(Encounter encounter) throws DataAccessException {
        EncounterResult result = validate(encounter);
        if (!result.isSuccess()) {
            return result;
        }

        // check for duplicate
        List<Encounter> encounters = repository.findAll();
        for (Encounter e : encounters) {
            if (Objects.equals(encounter.getWhen(), e.getWhen())
                    && Objects.equals(encounter.getType(), e.getType())
                    && Objects.equals(encounter.getDescription(), e.getDescription())) {
                result.addErrorMessage("duplicate encounter is not allowed");
                return result;
            }
        }

        encounter = repository.add(encounter);
        result.setPayload(encounters.size());
        return result;
    }

    private EncounterResult validate(Encounter encounter) {

        EncounterResult result = new EncounterResult();
        if (encounter == null) {
            result.addErrorMessage("Encounter cannot be null");
            return result;
        }

        if (encounter.getWhen() == null || encounter.getWhen().trim().isEmpty()) {
            result.addErrorMessage("when is required");
        }

        if (encounter.getDescription() == null || encounter.getDescription().trim().isEmpty()) {
            result.addErrorMessage("description is required, content cannot be empty.");
        }

        if (encounter.getOccurrences() <= 0) {
            result.addErrorMessage("occurrences must be greater than 0");
        }

        return result;
    }

    public EncounterResult update(Encounter encounter) throws DataAccessException {
        EncounterResult result = validate(encounter);

        if (encounter.getEncounterId() <= 0) {
            result.addErrorMessage("Encounter `id` is required.");
        }

        if (result.isSuccess()) {
            if (repository.update(encounter)) {
                result.setPayload(encounter.getEncounterId());
            } else {
                String message = String.format("Encounter id %s was not found.", encounter.getEncounterId());
                result.addErrorMessage(message);
            }
        }
        return result;
    }

    public EncounterResult deleteById(int encounterId) throws DataAccessException {
        EncounterResult result = new EncounterResult();
        if (!repository.deleteById(encounterId)) {
            String message = String.format("Encounter id %s was not found.", encounterId);
            result.addErrorMessage(message);
        }
        return result;
    }

    public EncounterResult findByType(EncounterType encounterType) throws DataAccessException {
        EncounterResult result = new EncounterResult();
        if (encounterType == null) {
            result.addErrorMessage("No Encounters found" + encounterType);
            return result;
        }

        List<Encounter> encounter = repository.findByType(encounterType);
        if (encounterType == null) {
            result.setPayload(encounter.size());
        } else {
            result.addErrorMessage("No Encounters found" + encounterType);
//            result.setPayload(encounterType);
        }
        return result;
    }
}







