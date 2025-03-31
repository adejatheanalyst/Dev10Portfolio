package learn.safari.data;

import learn.safari.models.BugOrder;

import java.util.List;

public interface BugOrderRepository {
    List<BugOrder> findAll();

    BugOrder findById(int bugOrderId);
}
