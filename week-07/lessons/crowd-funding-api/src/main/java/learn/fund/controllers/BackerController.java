package learn.fund.controllers;

import learn.fund.domain.BackerService;
import learn.fund.domain.Result;
import learn.fund.models.Backer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static learn.fund.controllers.ResultToResponseEntity.toResponseEntity;

@RestController
@RequestMapping("/api/backer")
public class BackerController {

    private final BackerService service;

    public BackerController(BackerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Backer> findByNameContains(@RequestParam(required = true) String criteria) {
        return service.findNameContains(criteria);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Backer backer) {
        Result<Backer> result = service.add(backer);
        return toResponseEntity(result, HttpStatus.CREATED);
    }

    @PutMapping("/{backerId}")
    public ResponseEntity<?> update(@PathVariable int backerId, @RequestBody Backer backer) {
        if (backerId != backer.getBackerId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<?> result = service.update(backer);
        return toResponseEntity(result, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{backerId}")
    public ResponseEntity<?> deleteById(@PathVariable int backerId) {
        Result<?> result = service.deleteById(backerId);
        return toResponseEntity(result, HttpStatus.NO_CONTENT);
    }
}
