package learn.field_agent.controllers;

import learn.field_agent.domain.Result;
import learn.field_agent.domain.SecurityClearanceService;
import learn.field_agent.models.SecurityClearance;


import org.springframework.dao.DataAccessException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/security_clearance")
public class SecurityClearanceController {

    private SecurityClearanceService service;

    public SecurityClearanceController(SecurityClearanceService service) {
        this.service = service;
    }

    @GetMapping("/{securityClearanceId}")
    public ResponseEntity<SecurityClearance> findById(@PathVariable int securityClearanceId) throws DataAccessException {
        SecurityClearance securityClearance = service.findById(securityClearanceId);
        if (securityClearance == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(securityClearance);
    }

    @GetMapping
    public List<SecurityClearance> findAll() throws DataAccessException {
        return service.findAll();
    }
    public ResponseEntity<Object> create(@RequestBody SecurityClearance securityClearance) throws DataAccessException {
        Result<SecurityClearance> result = service.add(securityClearance);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
        }
       return ErrorResponse.build(result);
    }
    @PutMapping("/{securityClearanceId}")
    public ResponseEntity<Object> update(@PathVariable int securityClearanceId, @RequestBody SecurityClearance securityClearance)throws  DataAccessException {
        if (securityClearance.getSecurityClearanceId() != securityClearanceId) {
            return new ResponseEntity<>(List.of("Id must match"), HttpStatus.CONFLICT);
        }
        Result<SecurityClearance> result = service.update(securityClearance);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);

    }
    @DeleteMapping("/{securityClearanceId}")
    public ResponseEntity<Object> delete(@PathVariable int securityClearanceId) throws DataAccessException{
        Result<Void> result =  service.deleteById(securityClearanceId);

        if(result.isSuccess()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
            return ErrorResponse.build(result);
    }
}
