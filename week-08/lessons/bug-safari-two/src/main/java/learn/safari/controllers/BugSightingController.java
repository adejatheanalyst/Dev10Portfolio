package learn.safari.controllers;

import learn.safari.domain.BugSightingResult;
import learn.safari.domain.BugSightingService;
import learn.safari.models.BugSighting;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sighting")
@CrossOrigin
public class BugSightingController {

    private final BugSightingService service;

    public BugSightingController(BugSightingService service) {
        this.service = service;
    }

    @GetMapping
    public List<BugSighting> findAll() {
        return service.findAll();
    }

    @GetMapping("/{sightingId}")
    public ResponseEntity<BugSighting> findById(@PathVariable int sightingId) {
        BugSighting sighting = service.findById(sightingId);
        if (sighting == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sighting);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody BugSighting sighting) {
        BugSightingResult result = service.add(sighting);
        return getResponse(result, HttpStatus.CREATED, result.getSighting());
    }

    @PutMapping("/{sightingId}")
    public ResponseEntity<?> update(@PathVariable int sightingId, @RequestBody BugSighting sighting) {
        if (sighting != null && sighting.getSightingId() != sightingId) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BugSightingResult result = service.update(sighting);
        return getResponse(result, HttpStatus.NO_CONTENT, null);
    }

    @DeleteMapping("/{sightingId}")
    public ResponseEntity<?> delete(@PathVariable int sightingId) {
        BugSightingResult result = service.deleteById(sightingId);
        return getResponse(result, HttpStatus.NO_CONTENT, null);
    }

    private ResponseEntity<?> getResponse(BugSightingResult result, HttpStatus statusDefault, Object bodyDefault) {
        HttpStatus status = getStatus(result, statusDefault);
        switch (status) {
            case NO_CONTENT:
            case NOT_FOUND:
                return ResponseEntity.status(status).build();
            case BAD_REQUEST:
                return ResponseEntity.status(status).body(result.getMessages());
            default:
                return ResponseEntity.status(status).body(bodyDefault);
        }
    }

    private HttpStatus getStatus(BugSightingResult result, HttpStatus statusDefault) {
        switch (result.getStatus()) {
            case INVALID:
            case DUPLICATE:
                return HttpStatus.BAD_REQUEST;
            case NOT_FOUND:
                return HttpStatus.NOT_FOUND;
        }
        return statusDefault;
    }

}
