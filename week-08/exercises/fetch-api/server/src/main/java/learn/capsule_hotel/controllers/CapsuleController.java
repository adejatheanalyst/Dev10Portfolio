package learn.capsule_hotel.controllers;

import learn.capsule_hotel.domain.CapsuleService;
import learn.capsule_hotel.domain.Result;
import learn.capsule_hotel.models.Capsule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/capsule")
@CrossOrigin("*")
public class CapsuleController {
    private final CapsuleService service;

    public CapsuleController(CapsuleService service) {
        this.service = service;
    }

    @GetMapping
    public List<Capsule> findAll() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Capsule capsule) {
        Result<Capsule> result = service.checkIn(capsule);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{capsuleNumber}")
    public ResponseEntity<?> delete(@PathVariable int capsuleNumber) {
        Result<Capsule> result = service.checkOut(capsuleNumber);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
