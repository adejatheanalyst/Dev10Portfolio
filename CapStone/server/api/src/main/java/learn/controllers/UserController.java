package learn.controllers;

import learn.domain.Results.ResultType;
import learn.domain.Results.UserResult;
import learn.domain.UserService;
import learn.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @PostMapping("/{userId}/profileImg")
    public ResponseEntity<Object> updateProfileImg(@PathVariable int userId, @RequestParam("file") MultipartFile file, @RequestHeader Map<String, String> headers) throws Exception {
        Integer authUserId = getUserId(headers);
        if (authUserId == null || authUserId != userId) {
            return new ResponseEntity<>("Not authorized to update profile image", HttpStatus.UNAUTHORIZED);
        }
        String result = service.uploadUserImg(file, userId);
        if (result != null) {
            return new ResponseEntity<>("Your profile image was uploaded successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("We could not upload you profile image", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{userId}/profileImg")
    public ResponseEntity<?> getUserProfileImg(@PathVariable int userId, @RequestHeader Map<String, String> headers) throws Exception {
        Integer authUserId = getUserId(headers);
        if (authUserId == null || authUserId != userId) {
            return new ResponseEntity<>("Not authorized to view profile image", HttpStatus.UNAUTHORIZED);
        }
        byte[] result = service.getUserImg(userId);
        if (result != null) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(result);
        } else {
            return new ResponseEntity<>("Profile image not found", HttpStatus.NOT_FOUND);
        }
    }
    private Integer getUserId(Map<String, String> headers) {
        if(headers.get("authorization") == null) {
            return null;
        }
        return Integer.parseInt(headers.get("authorization"));
    }
}
