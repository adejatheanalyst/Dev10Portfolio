package learn.pets.domain;

import learn.pets.data.PetRepository;
import learn.pets.models.Pet;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PetService {
    private final PetRepository repository;

    public PetService(PetRepository repository) {
        this.repository = repository;
    }

    public List<Pet> findAll() {
        return repository.findAll();
    }

    public Pet findById(int id) {
        return repository.findById(id);
    }

    public Result<Pet> add(Pet pet) {
        Result<Pet> result = validate(pet);
        if (!result.isSuccess()) {
            return result;
        }

        if (pet.getPetId() != 0) {
            result.addMessage("Pet id cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        pet = repository.add(pet);
        result.setPayload(pet);
        return result;
    }

    public Result<Pet> update(Pet pet) {
        Result<Pet> result = validate(pet);
        if (!result.isSuccess()) {
            return result;
        }

        if (pet.getPetId() <= 0) {
            result.addMessage("Pet id must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(pet)) {
            String msg = String.format("Pet id: %s, not found", pet.getPetId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }

    public Result<Pet> deleteById(int id) {
        Result<Pet> result = new Result<>();
        if (!repository.deleteById(id)) {
            String msg = String.format("Pet id: %s, not found", id);
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }

    private Result<Pet> validate(Pet pet) {
        Result<Pet> result = new Result<>();
        if (pet == null) {
            result.addMessage("Pet cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(pet.getName())) {
            result.addMessage("Name is required", ResultType.INVALID);
        }

        if (pet.getType() == null) {
            result.addMessage("Type is required", ResultType.INVALID);
        }

        if (pet.getVaccinationStatus() == null) {
            result.addMessage("Vaccination status is required", ResultType.INVALID);
        }

        if (pet.getDob() != null && pet.getDob().isAfter(LocalDate.now())) {
            result.addMessage("DOB cannot be in the future", ResultType.INVALID);
        }

        if (isDuplicate(pet)) {
            result.addMessage("Cannot create a pet with the same name, breed, type, and DOB", ResultType.INVALID);
        }

        return result;
    }

    private boolean isDuplicate(Pet pet) {
        for (Pet p : findAll()) {
            if (p.getPetId() != pet.getPetId()
                    && p.getName().equalsIgnoreCase(pet.getName())
                    && p.getBreed().equalsIgnoreCase(pet.getBreed())
                    && p.getType().equals(pet.getType())
                    && p.getDob() != null
                    && p.getDob().equals(pet.getDob())
            ) {
                return true;
            }
        }
        return false;
    }


}
