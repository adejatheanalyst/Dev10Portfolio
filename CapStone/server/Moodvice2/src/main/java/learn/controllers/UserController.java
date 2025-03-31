package learn.controllers;

import learn.domain.Results.ResultType;
import learn.domain.Results.UserResult;
import learn.domain.UserService;
import learn.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

@GetMapping("/{userId}")
    public ResponseEntity<Object> findById(@PathVariable int userId) {
        UserResult result = service.findByUserId(userId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getUser(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody User user) {
        UserResult result = service.create(user);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getUser(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user) {
        UserResult userResult = service.findByUsername(user.getUsername());

        if (userResult.getResultType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(userResult.getErrorMessages(), HttpStatus.NOT_FOUND);
        }

        if (userResult.getUser().getPassword().equals(user.getPassword())) {
            return new ResponseEntity<>(userResult.getUser(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userResult.getErrorMessages(), HttpStatus.UNAUTHORIZED);
        }
    }
    @PutMapping("/{userId}")
    public ResponseEntity<Object> update(@PathVariable int userId, @RequestBody User user, @RequestHeader Map<String, String> headers) {
        Integer authUserId = getUserId(headers);
        if (authUserId == null || authUserId != userId) {
            return new ResponseEntity<>("Not authorized to update account", HttpStatus.UNAUTHORIZED);
        }
        user.setUserId(userId);
        UserResult result = service.update(user);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getUser(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Object> delete(@PathVariable int userId, @RequestHeader Map<String, String> headers) {
        Integer authUserId = getUserId(headers);
        if (authUserId == null || authUserId != userId) {
            return new ResponseEntity<>("Not authorized to delete account", HttpStatus.UNAUTHORIZED);
        }
        UserResult result = service.deleteById(userId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
        }
    }
    private Integer getUserId(Map<String, String> headers) {
        if(headers.get("authorization") == null) {
            return null;
        }
        return Integer.parseInt(headers.get("authorization"));
    }
}
