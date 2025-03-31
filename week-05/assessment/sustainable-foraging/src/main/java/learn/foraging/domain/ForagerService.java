package learn.foraging.domain;

import learn.foraging.data.ForagerRepository;
import learn.foraging.models.Forager;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ForagerService {

    private final ForagerRepository repository;

    public ForagerService(ForagerRepository repository) {
        this.repository = repository;
    }
    public Result<Forager> findById(int id) {
        Forager forager = repository.findById(id);
        Result<Forager> result = new Result<>();
        if (forager == null) {
            result.addErrorMessage("Forager `id` not found.");
            result.setResultType(ResultType.NOT_FOUND);
            return result;
        }
        result.setPayload(forager);
        return result;
    }

    public List<Forager> findByState(String stateAbbr) {
        return repository.findByState(stateAbbr);
    }

    public List<Forager> findByLastName(String prefix) {
        return repository.findByLastName(prefix);
    }

    public Result<Forager> add(Forager forager) {
        Result<Forager> result = validate(forager);
        if (!result.isSuccess()) {
            return result;
        }

        if (forager.getId() != 0) {
            result.addErrorMessage("Forager `id` cannot be set for `add` operation.");
            result.setResultType(ResultType.INVALID);
            return result;
        }

        forager = repository.create(forager);
        result.setPayload(forager);
        return result;
    }


    public Result<Forager> deleteById(int id) {
        Result<Forager> result = new Result<>();
        if (!repository.deleteById(id)) {
            result.addErrorMessage("Forager `id` not found.");
            result.setResultType(ResultType.NOT_FOUND);
        }
        return result;
    }
    public Result<Forager> validate(Forager forager) {
        Result<Forager> result = new Result<>();
        if (forager == null) {
            result.addErrorMessage("Forager cannot be null.");
            result.setResultType(ResultType.INVALID);
            return result;
        }

        if (forager.getFirstName() == null || forager.getFirstName().isBlank()) {
            result.addErrorMessage("Forager `firstName` is required.");
            result.setResultType(ResultType.INVALID);
        }

        if (forager.getLastName() == null || forager.getLastName().isBlank()) {
            result.addErrorMessage("Forager `lastName` is required.");
            result.setResultType(ResultType.INVALID);
        }

        if (forager.getState() == null || forager.getState().isBlank()) {
            result.addErrorMessage("Forager `state` is required.");
            result.setResultType(ResultType.INVALID);
        }

        return result;
    }
}
