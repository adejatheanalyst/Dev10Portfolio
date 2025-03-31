package learn.capsule_hotel.domain;

import learn.capsule_hotel.models.Capsule;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CapsuleService {

    private final static int NUMBER_OF_CAPSULES = 100;

    private final Map<Integer, Capsule> capsules = new HashMap<>(Map.of(
            10, new Capsule(10, "John Smith"),
            25, new Capsule(25, "Sally Jones"),
            75, new Capsule(75, "Fred Johnson")
    ));

    public List<Capsule> findAll() {
        List<Capsule> capsules = new ArrayList<>();

        for (int capsuleNumber = 1; capsuleNumber <= NUMBER_OF_CAPSULES; capsuleNumber++) {
            if (this.capsules.containsKey(capsuleNumber)) {
                capsules.add(this.capsules.get(capsuleNumber));
            } else {
                capsules.add(new Capsule(capsuleNumber, null));
            }
        }

        return capsules;
    }

    public Result<Capsule> checkIn(Capsule capsule) {
        Result<Capsule> result = new Result<>();

        if (capsule == null) {
            result.addMessage("Capsule cannot be null.", ResultType.INVALID);
            return result;
        }

        int capsuleNumber = capsule.getCapsuleNumber();
        String guestName = capsule.getGuestName();

        if (capsuleNumber <= 0 || capsuleNumber > NUMBER_OF_CAPSULES) {
            result.addMessage("Capsule number must be between 1 and " + NUMBER_OF_CAPSULES + ".", ResultType.INVALID);
        }

        if (capsules.containsKey(capsuleNumber)) {
            result.addMessage("Capsule number " + capsuleNumber + " is occupied.", ResultType.INVALID);
        }

        if (guestName == null || guestName.trim().length() == 0) {
            result.addMessage("Guest name is required.", ResultType.INVALID);
        }

        if (!result.isSuccess()) {
            return result;
        }

        capsules.put(capsuleNumber, capsule);
        result.setPayload(capsule);
        return result;
    }

    public Result<Capsule> checkOut(int capsuleNumber) {
        Result<Capsule> result = new Result<>();

        if (capsuleNumber <= 0 || capsuleNumber > NUMBER_OF_CAPSULES) {
            result.addMessage("Capsule number must be between 1 and " + NUMBER_OF_CAPSULES + ".", ResultType.INVALID);
            return result;
        }

        if (!capsules.containsKey(capsuleNumber)) {
            result.addMessage("Capsule number " + capsuleNumber + " is unoccupied.", ResultType.INVALID);
            return result;
        }

        Capsule capsule = capsules.get(capsuleNumber);
        capsules.remove(capsuleNumber);
        result.setPayload(capsule);
        return result;
    }
}
