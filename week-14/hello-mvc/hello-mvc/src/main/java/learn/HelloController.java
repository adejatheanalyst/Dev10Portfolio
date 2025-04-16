package learn;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;

@RestController
public class HelloController {

    @GetMapping
    public String get() {
        return "Hello & 200 OK";
    }

    @GetMapping("/hello/mvc/message")
    public String getEnvironmentVariable(@Value("${hello.mvc.message}") String message) {
        return message;
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody HashMap<String, String> values) {
        values.put("timestamp", LocalDateTime.now().toString());
        return ResponseEntity.ok(values);
    }
}
