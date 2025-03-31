package learn.SulSulOverFlow.controllers;

import learn.SulSulOverFlow.domain.Result;
import learn.SulSulOverFlow.domain.ResultType;
import learn.SulSulOverFlow.domain.UserService;
import learn.SulSulOverFlow.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/user")
public class UserController {
    UserService service;


    public UserController(UserService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody User user){
        Result<User> result = service.create(user);
        if(result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user) {
        Result<User> result = service.findByUsername(user.getUsername());
        if (result.getResultType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.NOT_FOUND);
        }
        if (result.getPayload().getPassword().equals(user.getPassword())) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
        } else if (result.getPayload() == null) {
            return new ResponseEntity<>("Username is nonexistent", HttpStatus.UNAUTHORIZED);
        }else {
            return new ResponseEntity<>("Username and password do not match", HttpStatus.UNAUTHORIZED);
    }
    }
}
