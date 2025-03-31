package learn.safari.domain;

import learn.safari.data.BugOrderRepository;
import learn.safari.data.BugSightingRepository;
import learn.safari.models.BugSighting;
import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class BugSightingService {

    private final BugSightingRepository bugSightingRepository;
    private final BugOrderRepository bugOrderRepository;
    private final Validator validator;

    public BugSightingService(
            BugSightingRepository bugSightingRepository,
            BugOrderRepository bugOrderRepository, Validator validator) {
        this.bugSightingRepository = bugSightingRepository;
        this.bugOrderRepository = bugOrderRepository;
        this.validator = validator;
    }

    public List<BugSighting> findAll() {
        return bugSightingRepository.findAll();
    }

    public BugSighting findById(int sightingId) {
        return bugSightingRepository.findById(sightingId);
    }

    public Result<BugSighting> add(BugSighting sighting) {

        Result<BugSighting> result = validate(sighting);
        if (result.getStatus() != ActionStatus.SUCCESS) {
            return result;
        }

        BugSighting inserted = bugSightingRepository.add(sighting);
        if (inserted == null) {
            result.addMessage(ActionStatus.INVALID, "insert failed");
        } else {
            result.setPayload(inserted);
        }

        return result;
    }

    public Result<BugSighting> update(BugSighting sighting) {

        Result<BugSighting> result = validate(sighting);
        if (result.getStatus() != ActionStatus.SUCCESS) {
            return result;
        }

        if (!bugSightingRepository.update(sighting)) {
            result.addMessage(ActionStatus.NOT_FOUND, "sighting id `" + sighting.getSightingId() + "` not found.");
        }

        return result;
    }

    public Result<BugSighting> deleteById(int sightingId) {
        Result<BugSighting> result = new Result<>();
        boolean deleted = bugSightingRepository.deleteById(sightingId);
        if (!deleted) {
            result.addMessage(ActionStatus.NOT_FOUND, "sighting id `" + sightingId + "` not found.");
        }
        return result;
    }

    private Result<BugSighting> validate(BugSighting sighting) {

        Result<BugSighting> result = new Result<>();

        if (sighting == null) {
            result.addMessage(ActionStatus.INVALID, "bug sighting cannot be null.");
            return result;
        }

        Set<ConstraintViolation<BugSighting>> violations = validator.validate(sighting);

        for (ConstraintViolation<BugSighting> violation : violations) {
            result.addMessage(ActionStatus.INVALID, violation.getMessage());
        }

        if (!result.isSuccess()) {
            return result;
        }

        if (bugOrderRepository.findById(sighting.getOrder().getBugOrderId()) == null) {
            result.addMessage(ActionStatus.INVALID, "bug order id " + sighting.getOrder().getBugOrderId() + " not found.");
        }

        return result;
    }
}
