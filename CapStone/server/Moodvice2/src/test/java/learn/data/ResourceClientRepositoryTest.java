package learn.data;

import learn.models.Resources;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ResourceClientRepositoryTest {

    @Autowired
    JdbcClient client;
    @Autowired
    ResourceClientRepository repository;

    @BeforeEach
    void setup() {
        client.sql("call set_known_good_state();").update();
    }


    @Test
    void findAll() {
        List<Resources> actual = repository.findAll();
        assertNotNull(actual);
        assertEquals(4, actual.size());
    }
}