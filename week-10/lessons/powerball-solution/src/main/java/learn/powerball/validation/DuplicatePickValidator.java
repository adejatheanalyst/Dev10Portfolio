package learn.powerball.validation;

import learn.powerball.models.Ticket;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.HashSet;

public class DuplicatePickValidator
        implements ConstraintValidator<NoDuplicatePicks, Ticket> {

    @Override
    public void initialize(NoDuplicatePicks constraintAnnotation) {
        // nothing to do
    }

    @Override
    public boolean isValid(Ticket ticket, ConstraintValidatorContext constraintValidatorContext) {
        if (ticket == null) {
            return true;
        }
        // A set can't contain duplicates.
        HashSet<Integer> numbers = new HashSet<>();
        numbers.add(ticket.getPick1());
        numbers.add(ticket.getPick2());
        numbers.add(ticket.getPick3());
        numbers.add(ticket.getPick4());
        numbers.add(ticket.getPick5());
        // So if there are 5 numbers, all are unique.
        return numbers.size() == 5;
    }
}
