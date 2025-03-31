package learn.controllers;

import learn.domain.MediaService;
import learn.domain.Results.MediaResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/media")
public class MediaController {
    private final MediaService service;

    public MediaController(MediaService service) {
        this.service = service;
    }
    @GetMapping("/mood/{moodId}")
    public ResponseEntity<?> findByMoodId(@PathVariable int moodId){
        MediaResult result = service.getRandomMediaByMoodId(moodId);
        if(result.isSuccess()){
            return new ResponseEntity<>(result.getMediaList(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{mediaId}")
    public ResponseEntity<?> findByMediaId(@PathVariable int mediaId){
        MediaResult result = service.findByMediaId(mediaId);
        if(result.isSuccess()){
            return new ResponseEntity<>(result.getMedia(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping
//    public ResponseEntity<?> findRandomMedia(@RequestParam int moodId){
//        MediaResult result = service.getRandomMediaByMoodId(moodId);
//        if(result.isSuccess()){
//            return new ResponseEntity<>(result.getMedia(), HttpStatus.OK);
//        }else {
//            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
//        }
//
//    }
}
