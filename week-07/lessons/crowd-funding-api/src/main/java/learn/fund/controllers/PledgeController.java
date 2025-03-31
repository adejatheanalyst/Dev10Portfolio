package learn.fund.controllers;

import learn.fund.domain.PledgeService;
import learn.fund.domain.Result;
import learn.fund.models.Pledge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static learn.fund.controllers.ResultToResponseEntity.toResponseEntity;

@RestController
public class PledgeController {

    private final PledgeService service;

    public PledgeController(PledgeService service) {
        this.service = service;
    }

    @GetMapping("/api/campaign/{campaignId}/pledges")
    public List<Pledge> findByCampaignId(@PathVariable int campaignId) {
        return service.findByCampaignId(campaignId);
    }

    @GetMapping("/api/backer/{backerId}/pledges")
    public List<Pledge> findByBackerId(@PathVariable int backerId) {
        return service.findByBackerId(backerId);
    }

    @PostMapping("/api/pledge")
    public ResponseEntity<?> add(@RequestBody Pledge pledge) {
        Result<Pledge> result = service.add(pledge);
        return toResponseEntity(result, HttpStatus.CREATED);
    }

    @PutMapping("/api/pledge/{pledgeId}")
    public ResponseEntity<?> update(@PathVariable int pledgeId, @RequestBody Pledge pledge) {
        if (pledgeId != pledge.getPledgeId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<?> result = service.update(pledge);
        return toResponseEntity(result, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/api/pledge/{pledgeId}")
    public ResponseEntity<?> deleteById(@PathVariable int pledgeId) {
        Result<?> result = service.deleteById(pledgeId);
        return toResponseEntity(result, HttpStatus.NO_CONTENT);
    }
}
