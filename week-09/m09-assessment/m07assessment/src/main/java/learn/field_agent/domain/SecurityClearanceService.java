package learn.field_agent.domain;

import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.SecurityClearance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityClearanceService {

    private SecurityClearanceRepository repository;

    public SecurityClearanceService(SecurityClearanceRepository repository) {
        this.repository = repository;
    }

    public SecurityClearance findById(int id) {
        return repository.findById(id);
    }

    public List<SecurityClearance> findAll() {
        return repository.findAll();
    }

    public Result<SecurityClearance> add(SecurityClearance securityClearance) {
        Result<SecurityClearance> result = validate(securityClearance);

        if (result.isSuccess()) {
            SecurityClearance afterAdd = repository.add(securityClearance);
            result.setPayload(afterAdd);
        }

        return result;
    }

    public Result<SecurityClearance> update(SecurityClearance securityClearance) {
        Result<SecurityClearance> result = validate(securityClearance);

        if (result.isSuccess()) {
            boolean didUpdate = repository.update(securityClearance);
            if (didUpdate) {
                result.setPayload(securityClearance);
            } else {
                result.addMessage("Could not find security clearance for updating", ResultType.NOT_FOUND);
            }
        }
        return result;
    }

    public Result<Void> deleteById(int securityClearanceId) {
        Result<Void> result = new Result<>();

        if (repository.findActive().stream().anyMatch(sc -> sc.getSecurityClearanceId() == securityClearanceId)) {
            result.addMessage("Cannot delete because it is in use", ResultType.INVALID);
        }

        if (result.isSuccess()) {
            boolean didDelete = repository.deleteById(securityClearanceId);
            if (!didDelete) {
                result.addMessage("Could not find for deletion", ResultType.NOT_FOUND);
            }
        }
        return result;
    }

    private Result<SecurityClearance> validate(SecurityClearance securityClearance) {
        Result<SecurityClearance> result = new Result<>();

        if (Validations.isNullOrBlank(securityClearance.getName())) {
            result.addMessage("Name cannot be blank", ResultType.INVALID);
        }

        for (SecurityClearance existingSecurityClearance : findAll()) {
            if (existingSecurityClearance.getName().equals(securityClearance.getName())) {
                result.addMessage("Name must be unique", ResultType.INVALID);
            }
        }

        return result;
    }
}
