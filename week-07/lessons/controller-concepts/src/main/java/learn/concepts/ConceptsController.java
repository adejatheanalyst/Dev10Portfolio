package learn.concepts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConceptsController {
    @GetMapping("/")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping("/get")
    public List<String> doGet() {
        return List.of("apple", "orange", "pear", "grape");
    }

    @PostMapping("/post")
    public ResponseEntity<Pet> doPost(@RequestBody Pet pet) {
        if (!isValid(pet)) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(pet, HttpStatus.CREATED);
    }


    @PutMapping("/put")
    public ResponseEntity<Void> doPut(@RequestBody Pet pet) {
        if (pet.getPetId() != 15) {
            return ResponseEntity.notFound().build();
        } else if (!isValid(pet)) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    private boolean isValid(Pet pet) {
        return pet != null
                && pet.getName() != null
                && pet.getName().trim().length() > 0;
    }
    @DeleteMapping("/delete")
    public void doDelete() {
    }
    @RequestMapping(path = "/multi", method = {RequestMethod.OPTIONS, RequestMethod.TRACE})
    public void handleMultiple() {
    }
    @PostMapping("/add/{a}/{b}")
    public int add(@PathVariable int a, @PathVariable int b) {
        return a + b;
    }

    @GetMapping("/product/{name}/more/path")
    public String echo(@PathVariable String name) {
        return name;
    }
    @PostMapping("/urlencoded")
    public void readFromBody(String name, int age, boolean likesCookies) {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Likes Cookies?: " + likesCookies);
    }




}
