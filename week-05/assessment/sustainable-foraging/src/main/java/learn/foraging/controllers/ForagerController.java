package learn.foraging.controllers;

import learn.foraging.domain.ForagerService;
import learn.foraging.domain.Result;
import learn.foraging.models.Forager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/forager")
public class ForagerController {
    private final ForagerService service;

    public ForagerController(ForagerService service) {
        this.service = service;
    }
    @GetMapping("/{foragerId}")
    public ResponseEntity<?> findById(@PathVariable int foragerId) {
        Result<Forager> forager = service.findById(foragerId);
        if (forager.isSuccess()) {
            return new ResponseEntity<>(forager.getPayload(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(forager.getErrorMessages(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<?> add(@RequestBody Forager forager) {
        Result<Forager> result = service.add(forager);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{foragerId}")
    public ResponseEntity<?> deleteById(@PathVariable int foragerId) {
        Result<Forager> result = service.deleteById(foragerId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.NOT_FOUND);
        }
    }






}
