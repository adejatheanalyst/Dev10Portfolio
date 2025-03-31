package learn.domain;

import learn.data.ResourceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ResourceServiceTest {
    @Autowired
    ResourceService service;
    @Autowired
    ResourceRepository repository;

    @Test
    void findAll() {
        assertEquals(4, service.findAll().size());
    }
}