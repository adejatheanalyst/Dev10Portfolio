package learn.powerball.models;

import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TicketTest {

    @Test
    void emptyTicketShouldFailValidation() {
        Ticket ticket = new Ticket();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Ticket>> violations = validator.validate(ticket);

        assertEquals(9, violations.size());

        for (ConstraintViolation<Ticket> violation : violations) {
            System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
        }
    }

    @Test
    void invalidEmailShouldFailValidation() {
        Ticket ticket = makeValidTicket();
        ticket.setCustomerEmail("not-an-email");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Ticket>> violations = validator.validate(ticket);

        assertEquals(1, violations.size());

        ConstraintViolation<Ticket> first = violations.stream().findFirst().orElse(null);
        assertEquals("Customer email must be a valid email address.", first.getMessage());
    }

    @Test
    void validTicketShouldPassValidation() {
        Ticket ticket = makeValidTicket();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Ticket>> violations = validator.validate(ticket);

        assertEquals(0, violations.size());
    }

    Ticket makeValidTicket() {
        Ticket ticket = new Ticket();
        ticket.setCustomerName("Test Customer");
        ticket.setCustomerEmail("test@example.com");
        ticket.setPurchaseDate(LocalDate.now());
        ticket.setPick1(1);
        ticket.setPick2(2);
        ticket.setPick3(3);
        ticket.setPick4(4);
        ticket.setPick5(5);
        ticket.setPowerball(1);
        return ticket;
    }

}