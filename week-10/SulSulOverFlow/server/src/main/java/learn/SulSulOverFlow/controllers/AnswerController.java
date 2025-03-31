package learn.SulSulOverFlow.controllers;

import learn.SulSulOverFlow.domain.AnswerResult;
import learn.SulSulOverFlow.domain.AnswerService;
import learn.SulSulOverFlow.domain.Result;
import learn.SulSulOverFlow.domain.ResultType;
import learn.SulSulOverFlow.models.Answers;
import learn.SulSulOverFlow.models.Questions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/answer")
public class AnswerController {
    private final AnswerService service;

    public AnswerController(AnswerService service) {
        this.service = service;
    }
    @GetMapping
    public List<Answers> findAll() {
        return service.findAll();
    }
    @GetMapping("/{answerId}")
    public ResponseEntity<Object> findByAnswerId(@PathVariable int answerId) {
        AnswerResult result = service.findById(answerId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getAnswers());
        } else if (result.getResultType() == ResultType.NOT_FOUND) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.badRequest().body(result.getErrorMessages());
        }
    }
    @GetMapping("/questionId/{questionId}")
    public ResponseEntity<Object> findByQuestionId(@PathVariable int questionId) {
        AnswerResult result = service.findById(questionId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getAnswers());
        } else if (result.getResultType() == ResultType.NOT_FOUND) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.badRequest().body(result.getErrorMessages());
        }
    }
    @GetMapping("/userId/{userId}")
    public ResponseEntity<Object> findByUserId(@PathVariable int userId) {
        AnswerResult result = service.findById(userId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getAnswers());
        } else if (result.getResultType() == ResultType.NOT_FOUND) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.badRequest().body(result.getErrorMessages());
        }
    }
    @GetMapping("/myAnswers")
    public Object findUserAnswers(@RequestHeader Map<String, String> headers) {
        Integer userId = getUserId(headers);
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return service.findByUserId(userId);
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Answers answer, @RequestHeader Map<String, String> headers) {
        Integer userId = getUserId(headers);
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        answer.setUserId(userId);
        AnswerResult result = service.create(answer);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getAnswers(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/{answerId}")
    public ResponseEntity<?> update(@PathVariable int answerId, @RequestBody Answers answer, @RequestHeader Map<String, String> headers) {
        Integer userId = getUserId(headers);
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        answer.setUserId(userId);

        Answers existing = service.findById(answerId).getAnswers();
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        answer.setAnswerId(answerId);
//        if (existing.getUserId() != userId) {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
//
//        if (answerId != answer.getAnswerId()) {
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }

        AnswerResult result = service.update(answer);
        result.setUserId(userId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{answerId}")
    public ResponseEntity<?> deleteById(@PathVariable int answerId, @RequestHeader Map<String, String> headers) {
        Integer userId = getUserId(headers);
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        AnswerResult answers = service.findById(answerId);
        if (answers.getAnswers().getUserId() != userId) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        AnswerResult result = service.deleteById(answerId);
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
