package learn.pets.controllers;

import learn.pets.domain.PetService;
import learn.pets.domain.Result;
import learn.pets.models.Pet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@CrossOrigin("*")
public class PetController {
    private final PetService service;

    public PetController(PetService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pet> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> findById(@PathVariable int id) {
        Pet pet = service.findById(id);
        if (pet == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(pet);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Pet pet) {
        Result<Pet> result = service.add(pet);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }

        return ErrorResponse.build(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Pet pet) {
        if (id != pet.getPetId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Pet> result = service.update(pet);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable int id) {
        Result<Pet> result = service.deleteById(id);

        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }
}
