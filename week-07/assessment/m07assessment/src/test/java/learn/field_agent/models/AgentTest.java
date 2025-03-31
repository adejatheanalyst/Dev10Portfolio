package learn.field_agent.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

class AgentTest {

    @Test
    void emptyAgentShouldFailValidation() {
        Agent agent = new Agent();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Agent>> violations = validator.validate(agent);
        for (ConstraintViolation<Agent> violation : violations) {
            System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
        }
        assertEquals(4, violations.size());


    }

    @Test
    void emptyFirstNameShouldFailValidation() {
        Agent agent = makeValidAgent();
        agent.setFirstName("");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Agent>> violations = validator.validate(agent);

        assertEquals(1, violations.size());

        ConstraintViolation<Agent> first = violations.stream().findFirst().orElse(null);
        assertEquals("First Name is required", first.getMessage());
    }
    @Test
    void emptyLastNameShouldFailValidation() {
        Agent agent = makeValidAgent();
        agent.setLastName("");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Agent>> violations = validator.validate(agent);

        assertEquals(1, violations.size());

        ConstraintViolation<Agent> first = violations.stream().findFirst().orElse(null);
        assertEquals("Last name is required", first.getMessage());
    }

    @Test
    void emptyHeightShouldFailValidation() {
        Agent agent = makeValidAgent();
        agent.setHeightInInches(0);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Agent>> violations = validator.validate(agent);

        assertEquals(1, violations.size());

        ConstraintViolation<Agent> first = violations.stream().findFirst().orElse(null);
        assertEquals("Height must be greater than 36 inches", first.getMessage());
    }
    @Test
    void HeightNotInRangeShouldFailValidation() {
        Agent agent = makeValidAgent();
        agent.setHeightInInches(100);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Agent>> violations = validator.validate(agent);

        assertEquals(1, violations.size());

        ConstraintViolation<Agent> first = violations.stream().findFirst().orElse(null);
        assertEquals("Height must be less than 96 inches", first.getMessage());
    }
    @Test
    void futureDobFailsValidation() {
        Agent agent = makeValidAgent();
        agent.setDob(LocalDate.now().plusDays(1));
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Agent>> violations = validator.validate(agent);

        assertEquals(2, violations.size());

        ConstraintViolation<Agent> first = violations.stream().findFirst().orElse(null);
        assertEquals("Date of birth cannot be younger than 12", first.getMessage());
    }
    @Test
    void nullDobFailsValidation() {
        Agent agent = makeValidAgent();
        agent.setDob(null);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Agent>> violations = validator.validate(agent);

        assertEquals(1, violations.size());

        ConstraintViolation<Agent> first = violations.stream().findFirst().orElse(null);
        assertEquals("Date of Birth is required", first.getMessage());
    }


    @Test
    void validAgentShouldPassValidation() {
        Agent agent = makeValidAgent();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Agent>> violations = validator.validate(agent);

        assertEquals(0, violations.size());
    }

    Agent makeValidAgent() {
        Agent agent = new Agent();
        agent.setFirstName("Test Customer");
        agent.setMiddleName("Test Middle Name");
        agent.setLastName("Test Last Name");
        agent.setDob(LocalDate.of(1990, 1, 1));
        agent.setHeightInInches(60);
        return agent;
    }

}