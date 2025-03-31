package learn.field_agent.controllers;

import learn.field_agent.domain.AliasService;
import learn.field_agent.domain.Result;
import learn.field_agent.models.Alias;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alias")
public class AliasController {

    private final AliasService service;

    public AliasController(AliasService aliasService) {
        this.service = aliasService;
    }

    @GetMapping
    public List<Alias> findAll() throws DataAccessException {
        return service.findAll();

    }

    @GetMapping("/{aliasId}")
    public Alias findById(@PathVariable int aliasId) throws DataAccessException {
        return service.findById(aliasId);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Alias alias) throws DataAccessException {
        Result<Alias> result = service.add(alias);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{aliasId}")
    public ResponseEntity<Object> update(@PathVariable int aliasId, @RequestBody Alias alias) {
        if (alias.getAliasId() != aliasId) {
            return new ResponseEntity<>(List.of("ID must match"), HttpStatus.CONFLICT);
        }
        Result<Alias> result = service.update(alias);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }
    @DeleteMapping("/{aliasId}")
    public ResponseEntity<Object> delete(@PathVariable int aliasId){
        Result<Void> result = service.deleteById(aliasId);
        if(result.isSuccess()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }
}
