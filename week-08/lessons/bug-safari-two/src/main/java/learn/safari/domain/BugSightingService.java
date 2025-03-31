package learn.safari.domain;

import learn.safari.models.BugSighting;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BugSightingService {

    private final ArrayList<BugSighting> sightings = new ArrayList<>();

    public BugSightingService() {
        sightings.add(new BugSighting(1,
                "Ladybug",
                "Coleptera",
                "mature ladybug in the grass",
                LocalDate.now().minusDays(3),
                5.5,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f2/Coccinella_magnifica01.jpg/640px-Coccinella_magnifica01.jpg"));
        sightings.add(new BugSighting(2,
                "Cicadas",
                "Hemiptera",
                "the cicadas are singing in the trees",
                LocalDate.now().minusDays(2),
                7.0,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/Neotibicen_linnei.jpg/536px-Neotibicen_linnei.jpg"));
        sightings.add(new BugSighting(3,
                "Darkling Beetle",
                "Coleptera",
                "found an interesting beetle crawling in the compost",
                LocalDate.now().minusDays(1),
                9.75,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Darkling_beetle.jpg/640px-Darkling_beetle.jpg"));
    }

    public List<BugSighting> findAll() {
        return new ArrayList<>(sightings);
    }

    public BugSighting findById(int sightingId) {
        return sightings.stream()
                .filter(i -> i.getSightingId() == sightingId)
                .findAny()
                .orElse(null);
    }

    public BugSightingResult add(BugSighting sighting) {

        BugSightingResult result = validate(sighting);
        if (result.getStatus() != ActionStatus.SUCCESS) {
            return result;
        }

        sighting.normalize();

        if (isDuplicate(sighting)) {
            result.addMessage(ActionStatus.DUPLICATE, "duplicate sighting is not allowed");
            return result;
        }

        int nextId = sightings.stream()
                .mapToInt(i -> i.getSightingId())
                .max()
                .orElse(0) + 1;

        sighting.setSightingId(nextId);
        sightings.add(sighting);
        result.setSighting(sighting);

        return result;
    }

    public BugSightingResult update(BugSighting sighting) {

        BugSightingResult result = validate(sighting);
        if (result.getStatus() != ActionStatus.SUCCESS) {
            return result;
        }

        sighting.normalize();

        if (isDuplicate(sighting)) {
            result.addMessage(ActionStatus.DUPLICATE, "duplicate sighting is not allowed");
            return result;
        }

        for (int i = 0; i < sightings.size(); i++) {
            if (sighting.getSightingId() == sightings.get(i).getSightingId()) {
                sightings.set(i, sighting);
                return result;
            }
        }

        result.addMessage(ActionStatus.NOT_FOUND, "sighting id `" + sighting.getSightingId() + "` not found.");
        return result;

    }

    public BugSightingResult deleteById(int sightingId) {
        BugSightingResult result = new BugSightingResult();
        if (sightings.removeIf(i -> i.getSightingId() == sightingId)) {
            return result;
        }
        result.addMessage(ActionStatus.NOT_FOUND, "sighting id `" + sightingId + "` not found.");
        return result;
    }

    private BugSightingResult validate(BugSighting sighting) {

        BugSightingResult result = new BugSightingResult();

        if (sighting == null) {
            result.addMessage(ActionStatus.INVALID, "sighting may not be null");
            return result;
        }

        if (sighting.getBugType() == null || sighting.getBugType().isBlank()) {
            result.addMessage(ActionStatus.INVALID, "bugType is required");
        }

        if (sighting.getOrder() == null || sighting.getOrder().isBlank()) {
            result.addMessage(ActionStatus.INVALID, "order is required");
        }

        if (sighting.getDate() == null) {
            result.addMessage(ActionStatus.INVALID, "date is required");
        } else if (sighting.getDate().isAfter(LocalDate.now())) {
            result.addMessage(ActionStatus.INVALID, "date cannot be in the future");
        }

        if (sighting.getInterest() < 0.0) {
            result.addMessage(ActionStatus.INVALID, "interest cannot be negative");
        }

        return result;
    }

    private boolean isDuplicate(BugSighting sighting) {
        for (BugSighting s : sightings) {
            if (sighting.getBugType().equalsIgnoreCase(s.getBugType())
                    && sighting.getOrder().equals(s.getOrder())
                    && sighting.getDescription().equals(s.getDescription())
                    && sighting.getDate().equals(s.getDate())
                    && sighting.getSightingId() != s.getSightingId()) {
                return true;
            }
        }
        return false;
    }
}
