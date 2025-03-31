package learn.solarfarm.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import learn.solarfarm.domain.Result;
import learn.solarfarm.domain.ResultType;
import learn.solarfarm.domain.UserService;
import learn.solarfarm.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService service;
    private final SecretSigningKey secretSigningKey;

    public UserController(UserService service, SecretSigningKey secretSigningKey) {
        this.service = service;
        this.secretSigningKey = secretSigningKey;
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody User user) {
        Result<User> result = service.create(user);
        if (result.isSuccess()) {
            String jwt = Jwts.builder()
                    .claim("username", user.getUsername())
                    .claim("userId", result.getPayload().getUserId())
                    .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256))
                    .compact();
            Map<String, String> output = new HashMap<>();
            output.put("jwt", jwt);
            return new ResponseEntity<>(output, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user) {
        Result<User> userResult = service.findByUsername(user.getUsername());

        if (userResult.getResultType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(userResult.getErrorMessages(), HttpStatus.NOT_FOUND);
        }

//        if (userResult.getPayload().getPassword().equals(user.getPassword())) {
//            return new ResponseEntity<>(userResult.getPayload(), HttpStatus.OK);
        if(BCrypt.verifyer().verify(user.getPassword().toCharArray(), userResult.getPayload().getPassword().toCharArray()).verified) {
            String jwt = Jwts.builder()
                    .claim("username", user.getUsername())
                    .claim("userId", userResult.getPayload().getUserId())
                    .signWith(secretSigningKey.getKey())
                    .compact();
            Map<String, String> output = new HashMap<>();
            output.put("jwt", jwt);
            return new ResponseEntity<>(output, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(List.of("Username and password do not match"), HttpStatus.UNAUTHORIZED);
        }
    }


}

















//    @PostMapping("/login")
//    public ResponseEntity<Object> login(@RequestBody User user) {
//        Result<User> result = service.findByUsername(user.getUsername());
//            if (!result.isSuccess()) {
//            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.NOT_FOUND);
//        } else if (user.getPassword().equals(result.getPayload().getPassword())) {
//               String jwt = Jwts.builder().claim("username", user.getUsername())
//                        .claim("userId",user.getUserId())
//                        .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256))
//                        .compact();
//               Map<String, String> response = new HashMap<>();
//                response.put("jwt", jwt);
//                return new ResponseEntity<>(response, HttpStatus.OK);
//
//        } else {
//            return new ResponseEntity<>(List.of("Username or password is incorrect."), HttpStatus.UNAUTHORIZED);
//        }
//    }
//}
