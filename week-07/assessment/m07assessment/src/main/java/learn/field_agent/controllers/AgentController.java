package learn.field_agent.controllers;

import jakarta.validation.Valid;
import learn.field_agent.domain.AgentService;
import learn.field_agent.domain.Result;
import learn.field_agent.models.Agent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.List;

@RestController
@RequestMapping("/api/agent")
public class AgentController {

    private final AgentService service;

    public AgentController(AgentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Agent> findAll() {
        return service.findAll();
    }

    @GetMapping("/{agentId}")
    public Agent findById(@PathVariable int agentId) {
        return service.findById(agentId);
    }

    @PostMapping
    public ResponseEntity<Object> add(
            @RequestBody @Valid Agent agent, BindingResult result) {
        Result<Agent> result2 = service.add(agent);
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result2.getPayload(), HttpStatus.CREATED);
    }

    @PutMapping("/{agentId}")
    public ResponseEntity<Object> update(@PathVariable int agentId, @RequestBody Agent agent) {
        if (agentId != agent.getAgentId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Agent> result = service.update(agent);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{agentId}")
    public ResponseEntity<Void> deleteById(@PathVariable int agentId) {
        if (service.deleteById(agentId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
