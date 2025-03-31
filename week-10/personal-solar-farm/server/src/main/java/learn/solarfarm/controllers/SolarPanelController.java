package learn.solarfarm.controllers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import learn.solarfarm.domain.ResultType;
import learn.solarfarm.domain.SolarPanelResult;
import learn.solarfarm.domain.SolarPanelService;
import learn.solarfarm.models.SolarPanel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/solarpanel")
public class SolarPanelController {

    private final SolarPanelService service;
    private final SecretSigningKey secretSigningKey;

    public SolarPanelController(SolarPanelService service, SecretSigningKey secretSigningKey) {
        this.service = service;
        this.secretSigningKey = secretSigningKey;
    }
    // GET /api/panel?section=Hill

    @GetMapping
    public List<SolarPanel> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        SolarPanelResult result = service.findById(id);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getSolarPanel());
        } else if (result.getResultType() == ResultType.NOT_FOUND) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.badRequest().body(result.getErrorMessages());
        }
    }
    @GetMapping("/myPanels")
    public Object findPersonalPanels(@RequestHeader Map<String, String> headers) {
        Integer userId = getUserId(headers);
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return service.findByUserId(userId);

    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody SolarPanel panel, @RequestHeader Map<String, String> headers) {
        Integer userId = getUserId(headers);
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        panel.setUserId(userId);
        SolarPanelResult result = service.create(panel);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getSolarPanel(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{panelId}")
    public ResponseEntity<?> update(@PathVariable int panelId, @RequestBody SolarPanel panel, @RequestHeader Map<String, String> headers) {
        Integer userId = getUserId(headers);
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        SolarPanel existing = service.findById(panelId).getSolarPanel();
        if (existing == null) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        if (existing.getUserId() != userId) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if (panelId != panel.getId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        SolarPanelResult result = service.update(panel);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{panelId}")
    public ResponseEntity<?> deleteById(@PathVariable int panelId, @RequestHeader Map<String, String> headers) {
        Integer userId = getUserId(headers);
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        SolarPanel panel = service.findById(panelId).getSolarPanel();
        if (panel.getUserId() != userId) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        SolarPanelResult result = service.deleteById(panelId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (result.getResultType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteByKey(@RequestParam String section,
                                         @RequestParam int row,
                                         @RequestParam int column) {

        SolarPanel panel = service.findByKey(section, row, column);
        if(panel  == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        SolarPanelResult result = service.deleteById(panel.getId());
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
//        return Integer.parseInt(headers.get("authorization"));
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(secretSigningKey.getKey())
                    .build().parseClaimsJws(headers.get("authorization"));
            return claims.getBody().get("userId", Integer.class);
        } catch (Exception e) {
            return null;
        }
    }
}
