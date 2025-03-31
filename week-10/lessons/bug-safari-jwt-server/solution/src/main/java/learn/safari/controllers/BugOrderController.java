package learn.safari.controllers;

import learn.safari.domain.BugOrderService;
import learn.safari.models.BugOrder;
import learn.safari.models.BugSighting;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class BugOrderController {

    private final BugOrderService service;

    public BugOrderController(BugOrderService service) {
        this.service = service;
    }

    @GetMapping
    public List<BugOrder> findAll() {
        return service.findAll();
    }
}
