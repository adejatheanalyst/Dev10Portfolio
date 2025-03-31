package learn.fund.controllers;

import learn.fund.domain.CampaignService;
import learn.fund.domain.Result;
import learn.fund.models.Campaign;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static learn.fund.controllers.ResultToResponseEntity.toResponseEntity;

@RestController
@RequestMapping("/api/campaign")
public class CampaignController {

    private final CampaignService service;

    public CampaignController(CampaignService service) {
        this.service = service;
    }

    @GetMapping("/active")
    public List<Campaign> findActive() {
        return service.findActive();
    }

    @GetMapping
    public List<Campaign> findByDateRange(@RequestParam LocalDate start, @RequestParam LocalDate end) {
        return service.findByDateRange(start, end);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Campaign campaign) {
        Result<?> result = service.add(campaign);
        return toResponseEntity(result, HttpStatus.CREATED);
    }

    @PutMapping("/{campaignId}")
    public ResponseEntity<?> update(@PathVariable int campaignId, @RequestBody Campaign campaign) {
        if (campaignId != campaign.getCampaignId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<?> result = service.update(campaign);
        return toResponseEntity(result, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{campaignId}")
    public ResponseEntity<?> deleteById(@PathVariable int campaignId) {
        Result<?> result = service.deleteById(campaignId);
        return toResponseEntity(result, HttpStatus.NO_CONTENT);
    }
}
