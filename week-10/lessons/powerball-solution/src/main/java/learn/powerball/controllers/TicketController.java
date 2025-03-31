package learn.powerball.controllers;

import learn.powerball.models.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class TicketController {

    @PostMapping("/ticket")
    public ResponseEntity<Object> create(
            @RequestBody @Valid Ticket ticket, // 1. Add @Valid.
            BindingResult result) { // 2. A BindResult must immediately follow.

        // 3. BindingResult.hasErrors() checks if violations exists.
        if (result.hasErrors()) {
            // 4. BindResult.getAllErrors() returns violations wrapped in Spring
            // ObjectError instances.
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }
}
