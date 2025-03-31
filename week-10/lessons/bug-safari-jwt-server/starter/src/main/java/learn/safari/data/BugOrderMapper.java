package learn.safari.data;

import learn.safari.models.BugOrder;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BugOrderMapper implements RowMapper<BugOrder> {
    @Override
    public BugOrder mapRow(ResultSet resultSet, int i) throws SQLException {
        BugOrder order = new BugOrder();
        order.setBugOrderId(resultSet.getInt("bug_order_id"));
        order.setName(resultSet.getString("name"));
        order.setDescription(resultSet.getString("description"));
        return order;
    }
}
