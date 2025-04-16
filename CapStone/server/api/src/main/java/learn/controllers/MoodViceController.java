package learn.controllers;

import learn.domain.MoodViceService;
import learn.domain.Results.MoodViceResult;
import learn.models.MoodVice;
import learn.models.Reply;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/moodVice")
public class MoodViceController {
    private final MoodViceService service;

    public MoodViceController(MoodViceService service) {
        this.service = service;
    }

    @GetMapping
    public List<MoodVice> findAll() {
        return service.findAll();
    }

    @GetMapping("/mood/{moodId}")
    public ResponseEntity<?> findByMoodId(@PathVariable int moodId) {
        MoodViceResult result = service.findByMoodType(moodId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getMoodVices(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{moodViceId}")
    public MoodVice findByMoodViceId(@PathVariable int moodViceId) {
        return service.findByMoodViceId(moodViceId);
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody MoodVice moodVice, @RequestHeader Map<String, String> headers) {
        Integer userId = getUserId(headers);
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        moodVice.setUserId(userId);
        MoodViceResult result = service.create(moodVice);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getMoodVice(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/{moodViceId}")
    public ResponseEntity<?> update(@PathVariable int moodViceId,
                                    @RequestBody MoodVice moodVice,
                                    @RequestHeader Map<String, String> headers){
        Integer userId = getUserId(headers);
        if(userId == null){
            return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        moodVice.setUserId(userId);
       MoodVice existing = service.findByMoodViceId(moodViceId);
        if(existing.getUserId() != userId){
            return new ResponseEntity<>("You do not own this Moodvice",HttpStatus.UNAUTHORIZED);
        }
//        if(moodViceId != moodVice.getMoodViceId()){
//            return new ResponseEntity<>("Id cannot be set",HttpStatus.CONFLICT);
//        }
        moodVice.setMoodViceId(moodViceId);

        MoodViceResult result = service.update(moodVice);
        if(result.isSuccess()){
            return new ResponseEntity<>(result.getMoodVice(), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);


    }
    @DeleteMapping("/{moodViceId}")
    public ResponseEntity<?> deleteById(@PathVariable int moodViceId,
                                    @RequestHeader Map<String, String> headers){
        Integer userId = getUserId(headers);
        if(userId == null){
            return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        MoodVice existing = service.findByMoodViceId(moodViceId);
        if(existing.getUserId() != userId){
            return new ResponseEntity<>("You do not own this Moodvice",HttpStatus.UNAUTHORIZED);
        }


        MoodViceResult result = service.deleteById(moodViceId);
        if(result.isSuccess()){
            return new ResponseEntity<>("Successfully Deleted", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);


    }




    private Integer getUserId(Map<String, String> headers) {
        if(headers.get("authorization") == null) {
            return null;
        }
        return Integer.parseInt(headers.get("authorization"));
    }
}
