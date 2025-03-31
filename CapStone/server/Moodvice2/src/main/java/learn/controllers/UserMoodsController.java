package learn.controllers;

import learn.domain.Results.UserMoodResult;
import learn.domain.UserMoodService;
import learn.models.UserMoods;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/userMood")
public class UserMoodsController {
    private final UserMoodService service;

    public UserMoodsController(UserMoodService service) {
        this.service = service;
    }
    @GetMapping
    public List<UserMoods> findAll() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserMoods userMood, @RequestHeader Map<String, String> headers) {
        Integer userId = getUserId(headers);
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        userMood.setUserId(userId);
        UserMoodResult result = service.create(userMood);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getUserMoods(), HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/myMoods/{date}")
    public ResponseEntity<?> findUserMoodsByDay(@RequestHeader Map<String, String> headers,
                                      @PathVariable LocalDate date) {
        Integer userId = getUserId(headers);
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        UserMoodResult result = service.getUserMoodsByDay(date, userId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getUserMoodsList(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }

    }

    private Integer getUserId(Map<String, String> headers) {
        if(headers.get("authorization") == null) {
            return null;
        }
        return Integer.parseInt(headers.get("authorization"));
    }
}
