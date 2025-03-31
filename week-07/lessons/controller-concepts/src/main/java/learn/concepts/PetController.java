package learn.concepts;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
public class PetController {

    @GetMapping
    public void findAll() {
    }

    @GetMapping("/{petId}")
    public void findById(@PathVariable int petId) {
    }

    @PostMapping
    public void create(@RequestBody Pet pet) {
        System.out.println(pet);
    }

    @PutMapping("/{petId}")
    public void update(@PathVariable int petId, @RequestBody Pet pet) {
        System.out.println(pet);
    }


    @DeleteMapping("/{petId}")
    public void delete(@PathVariable int petId) {
    }
}

