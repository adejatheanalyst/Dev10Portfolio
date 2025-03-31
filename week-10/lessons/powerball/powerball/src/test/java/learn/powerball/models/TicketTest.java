package learn.powerball.models;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TicketTest {

    @Test
    void emptyTicketShouldFailValidation() {
        Ticket ticket = new Ticket();

        // Grab a Validator instance and validate the ticket.
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Ticket>> violations = validator.validate(ticket);
        for (ConstraintViolation<Ticket> violation : violations) {
            System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
        }

        assertEquals(9, violations.size());
    }

    @Test
    void invalidEmailShouldFailValidation() {
        Ticket ticket = makeValidTicket();
        ticket.setCustomerEmail("not-an-email");

        // Grab a Validator instance and validate the ticket.
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Ticket>> violations = validator.validate(ticket);

        assertEquals(1, violations.size());

        ConstraintViolation<Ticket> first = violations.stream().findFirst().orElse(null);
        for (ConstraintViolation<Ticket> violation : violations) {
            System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
        }
        assertEquals("Customer email must be a valid email address.", first.getMessage());

    }

    @Test
    void validTicketShouldPassValidation() {
        Ticket ticket = makeValidTicket();

        // Grab a Validator instance and validate the ticket.
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Ticket>> violations = validator.validate(ticket);
        for (ConstraintViolation<Ticket> violation : violations) {
            System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
        }

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
