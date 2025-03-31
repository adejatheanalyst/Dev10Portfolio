package learn.SulSulOverFlow.controllers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import learn.SulSulOverFlow.domain.*;
import learn.SulSulOverFlow.models.Questions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/question")
public class QuestionController {
    private final QuestionService service;
    private final UserService userService;

    public QuestionController(QuestionService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }
    @GetMapping
    public List<Questions> findAll() {
        return service.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        QuestionResult result = service.findById(id);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getQuestions());
        } else if (result.getResultType() == ResultType.NOT_FOUND) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.badRequest().body(result.getErrorMessages());
        }
    }
   @GetMapping("/myQuestions")
           public Object findUserQuestions(@RequestHeader Map<String, String> headers) {
              Integer userId = getUserId(headers);
               if (userId == null) {
                   return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
               }
               return service.findByUserId(userId);
           }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Questions questions, @RequestHeader Map<String, String> headers) {
        Integer userId = getUserId(headers);
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        questions.setUserId(userId);
        QuestionResult result = service.create(questions);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getQuestions(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/{questionId}")
    public ResponseEntity<?> update(@PathVariable int questionId, @RequestBody Questions question, @RequestHeader Map<String, String> headers) {
        Integer userId = getUserId(headers);
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        question.setUserId(userId);
        QuestionResult questionsResult = service.findById(questionId);
        if (questionsResult.getResultType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(questionsResult.getErrorMessages(),HttpStatus.NOT_FOUND);
        }
        Questions existing = questionsResult.getQuestions();
        Integer existingUserId = existing.getUserId();
        if (!existingUserId.equals(userId)) {
            return new ResponseEntity<>( questionsResult.getErrorMessages(), HttpStatus.FORBIDDEN);
        }
        question.setQuestionId(questionId);

        QuestionResult result = service.update(question);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{questionId}")
    public ResponseEntity<?> deleteById(@PathVariable int questionId, @RequestHeader Map<String, String> headers) {
        Integer userId = getUserId(headers);
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
//       Questions questions = service.findById(questionId).getQuestions();
//        if (questions.getUserId() != userId) {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
        QuestionResult result = service.deleteById(questionId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (result.getResultType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
