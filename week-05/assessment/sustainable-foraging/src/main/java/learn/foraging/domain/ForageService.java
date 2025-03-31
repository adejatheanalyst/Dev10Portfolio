package learn.foraging.domain;

import learn.foraging.data.ForageRepository;
import learn.foraging.data.ForagerRepository;
import learn.foraging.data.ItemRepository;
import learn.foraging.models.Forage;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Service
public class ForageService {

    private final ForageRepository forageRepository;
    private final ForagerRepository foragerRepository;
    private final ItemRepository itemRepository;

    public ForageService(ForageRepository forageRepository, ForagerRepository foragerRepository, ItemRepository itemRepository) {
        this.forageRepository = forageRepository;
        this.foragerRepository = foragerRepository;
        this.itemRepository = itemRepository;
    }

    public List<Forage> findByDate(LocalDate date) {
        return forageRepository.findByDate(date);
    }

    public Result<Forage> add(Forage forage) {
        Result<Forage> result = validate(forage);
        if (!result.isSuccess()) {
            return result;
        }

        result.setPayload(forageRepository.add(forage));

        return result;
    }

    private Result<Forage> validate(Forage forage) {

        Result<Forage> result = validateNulls(forage);
        if (!result.isSuccess()) {
            return result;
        }

        validateFields(forage, result);
        if (!result.isSuccess()) {
            return result;
        }

        validateChildrenExist(forage, result);

        return result;
    }

    private Result<Forage> validateNulls(Forage forage) {
        Result<Forage> result = new Result<>();

        if (forage == null) {
            result.addErrorMessage("Nothing to save.");
            return result;
        }

        if (forage.getDate() == null) {
            result.addErrorMessage("Forage date is required.");
            result.setResultType(ResultType.INVALID);
        }

        if (forage.getForager() == null) {
            result.addErrorMessage("Forager is required.");
            result.setResultType(ResultType.INVALID);
        }

        if (forage.getItem() == null) {
            result.addErrorMessage("Item is required.");
            result.setResultType(ResultType.INVALID);
        }

        if(forage.getKilograms() == null) {
            result.addErrorMessage("Kilograms are required.");
            result.setResultType(ResultType.INVALID);
        }

        return result;
    }

    private void validateFields(Forage forage, Result<Forage> result) {
        // No future dates.
        if (forage.getDate().isAfter(LocalDate.now())) {
            result.addErrorMessage("Forage date cannot be in the future.");
            result.setResultType(ResultType.INVALID);
        }

        if (forage.getKilograms().compareTo(BigDecimal.ZERO) <= 0
                || forage.getKilograms().compareTo(new BigDecimal("250")) > 0) {
            result.addErrorMessage("Kilograms must be a positive number less than 250.0");
            result.setResultType(ResultType.INVALID);
        }
    }

    private void validateChildrenExist(Forage forage, Result<Forage> result) {

//        if (forage.getForager().getId() <= 0
//                || foragerRepository.findById(forage.getForager().getId()) == null) {
//            result.addErrorMessage("Forager does not exist.");
//        }

        if (forage.getForager().getId() <= 0) {
            result.addErrorMessage("Forager does not exist.");
            result.setResultType(ResultType.INVALID);
        }

        if (itemRepository.findById(forage.getItem().getId()) == null) {
            result.addErrorMessage("Item does not exist.");
            result.setResultType(ResultType.INVALID);
        }
    }
}
