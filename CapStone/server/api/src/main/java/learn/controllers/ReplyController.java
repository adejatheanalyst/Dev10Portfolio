package learn.controllers;

import learn.domain.ReplyService;
import learn.domain.Results.MoodViceResult;
import learn.domain.Results.ReplyResult;
import learn.models.MoodVice;
import learn.models.Reply;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/reply")
public class ReplyController {

    private  final ReplyService service;

    public ReplyController(ReplyService service) {
        this.service = service;
    }
    @GetMapping("/moodVice/{moodViceId}")
    public ResponseEntity<?> findReplyByMoodViceId(@PathVariable int moodViceId){
        ReplyResult result = service.findByMoodViceId(moodViceId);
        if (result.isSuccess()){
            return new ResponseEntity<>(result.getReplyList(), HttpStatus.OK);
        }else {
            return  new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{replyId}")
    public ResponseEntity<?> findById(@PathVariable int replyId){
        ReplyResult result = service.findById(replyId);
        if(result.isSuccess()){
            return new ResponseEntity<>(result.getReply(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/addReply/{moodViceId}")
    public ResponseEntity<?> create(@RequestBody Reply reply, @RequestHeader Map<String, String> headers, @PathVariable int moodViceId) {
        Integer userId = getUserId(headers);
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        reply.setUserId(userId);

        ReplyResult moodViceResult = service.findByMoodViceId(moodViceId);
        if(!moodViceResult.isSuccess()){
            return new ResponseEntity<>("MoodVice Not Found", HttpStatus.BAD_REQUEST);
        }else {
            reply.setMoodViceId(moodViceId);
        }

        ReplyResult result = service.create(reply);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getReply(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
    }


    @PutMapping("/edit/{moodViceId}/{replyId}")
    public ResponseEntity<?> update(@RequestBody Reply reply, @RequestHeader Map<String, String> headers, @PathVariable int moodViceId, @PathVariable int replyId) {
        Integer userId = getUserId(headers);
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        reply.setUserId(userId);

        ReplyResult replyResult = service.findById(replyId);
        if(!replyResult.isSuccess()){
            return new ResponseEntity<>(replyResult.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }else {
            reply.setReplyId(replyId);
        }
        ReplyResult moodViceResult = service.findByMoodViceId(moodViceId);
        if(!moodViceResult.isSuccess()){
            return new ResponseEntity<>(moodViceResult.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }else {
            reply.setMoodViceId(moodViceId);
        }

        Reply existing = service.findByKey(replyId, userId, reply.getMoodViceId());
        if(existing == null){
            return new ResponseEntity<>("You do not have permission to update this reply", HttpStatus.UNAUTHORIZED);
        }

        ReplyResult result = service.update(reply);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getReply(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{moodViceId}/{replyId}")
    public ResponseEntity<?> deleteById(@PathVariable int replyId, @PathVariable int moodViceId, @RequestHeader Map<String, String> headers) {
        Integer userId = getUserId(headers);
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Reply existing = service.findByKey(replyId, userId, moodViceId);;
        if(existing == null){
            return new ResponseEntity<>("You do not have permission to delete this reply", HttpStatus.NOT_FOUND);
        }
        ReplyResult result = service.deleteById(replyId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
