package learn.solarfarm.controllers;

import com.mysql.cj.conf.StringProperty;
import learn.solarfarm.domain.SolarPanelResult;
import learn.solarfarm.domain.SolarPanelService;
import learn.solarfarm.models.SolarPanel;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solarpanels")
public class SolarPanelController {
    private final SolarPanelService service;

    public SolarPanelController(SolarPanelService service) {
        this.service = service;
    }

    public SolarPanelService getService() {
        return service;
    }

    @GetMapping("/byKey/{section}/{row}/{column}")
    public ResponseEntity<Object> findByKey(@PathVariable String section, @PathVariable int row, @PathVariable int column) throws DataAccessException, learn.solarfarm.data.DataAccessException {
        SolarPanel result = service.findByKey(section, row, column);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @GetMapping("/bySection/{section}")
    public ResponseEntity<Object> findBySection(@PathVariable String section) throws DataAccessException, learn.solarfarm.data.DataAccessException {
        List<SolarPanel> result = service.findBySection(section);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody SolarPanel solarPanel) throws DataAccessException, learn.solarfarm.data.DataAccessException {
        SolarPanelResult result = service.create(solarPanel);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getSolarPanel(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }
    }
@PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody SolarPanel solarPanel, @PathVariable int id) throws DataAccessException, learn.solarfarm.data.DataAccessException {
        if (id != solarPanel.getId()) {
            return new ResponseEntity<>("Id in path must match id in request body", HttpStatus.CONFLICT);
        }

        SolarPanelResult result = service.update(solarPanel);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getSolarPanel(), HttpStatus.OK);
        } else {
            if (result.getErrorMessages().get(0).contains("does not exist")) {
                return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
            }
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<SolarPanel>> findAll() throws DataAccessException, learn.solarfarm.data.DataAccessException {
        List<SolarPanel> allSolarPanels = service.findAll();
        return new ResponseEntity<>(allSolarPanels, HttpStatus.OK);
    }
    @DeleteMapping("/byKey/{section}/{row}/{column}")
    public ResponseEntity<Object> delete(@PathVariable String section, @PathVariable int row, @PathVariable int column) throws DataAccessException, learn.solarfarm.data.DataAccessException {
        SolarPanel solarPanel = service.findByKey(section, row, column);
        SolarPanel result = service.delete(solarPanel).getSolarPanel();
        if (result == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
        }
    }







}


