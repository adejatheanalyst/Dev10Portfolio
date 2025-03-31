package learn.safari.domain;

import learn.safari.data.BugOrderRepository;
import learn.safari.models.BugOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BugOrderServiceTest {

    @MockBean
    BugOrderRepository repository;

    @Autowired
    BugOrderService service;

    @Test
    void shouldFindAll() {
        List<BugOrder> bugOrders = List.of(
                new BugOrder(2, "Lepidoptera", "butterflies, moths"),
                new BugOrder(1, "Coleptera", "beetles")
        );

        when(repository.findAll()).thenReturn(bugOrders);

        assertEquals(bugOrders, service.findAll());
    }
}