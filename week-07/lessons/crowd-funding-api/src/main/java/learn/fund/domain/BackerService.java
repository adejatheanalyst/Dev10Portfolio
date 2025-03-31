package learn.fund.domain;

import learn.fund.data.BackerRepository;
import learn.fund.models.Backer;
import org.springframework.stereotype.Service;

import java.util.List;

import static learn.fund.domain.Validation.isNullOrBlank;
import static learn.fund.domain.Validation.isValidEmail;

@Service
public class BackerService {
    private final BackerRepository repository;

    public BackerService(BackerRepository repository) {
        this.repository = repository;
    }

    public List<Backer> findNameContains(String snippet) {
        return repository.findNameContains(snippet);
    }

    public Result<Backer> add(Backer backer) {
        Result<Backer> result = validate(backer);
        if (!result.isSuccess()) {
            return result;
        }

        Backer b = repository.add(backer);
        result.setPayload(b);
        return result;
    }

    public Result<?> update(Backer backer) {
        Result<Backer> result = validate(backer);
        if (!result.isSuccess()) {
            return result;
        }

        boolean success = repository.update(backer);
        if (!success) {
            result.setNotFound();
        }

        return result;
    }

    public Result<?> deleteById(int backerId) {
        Result<?> result = new Result<>();
        boolean success = repository.deleteById(backerId);
        if (!success) {
            result.setNotFound();
        }
        return result;
    }

    private Result<Backer> validate(Backer backer) {
        Result<Backer> result = new Result<>();

        if (backer == null) {
            result.addErrorMessage("backer may not be null");
            // return early to avoid null reference
            return result;
        }

        if (isNullOrBlank(backer.getName())) {
            result.addErrorMessage("name is required");
        }

        if (isNullOrBlank(backer.getEmailAddress())) {
            result.addErrorMessage("email is required");
        }

        if (!result.isSuccess()) {
            // return early to avoid null email reference
            return result;
        }

        if (!isValidEmail(backer.getEmailAddress())) {
            result.addErrorMessage("not a valid email address");
            return result;
        }

        Backer existing = repository.findByEmailAddress(backer.getEmailAddress());
        if (existing != null && backer.getBackerId() != existing.getBackerId()) {
            result.addErrorMessage("duplicate email address");
        }

        return result;
    }
}
