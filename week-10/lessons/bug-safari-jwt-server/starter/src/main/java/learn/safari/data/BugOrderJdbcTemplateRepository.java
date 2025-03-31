package learn.safari.data;

import learn.safari.models.BugOrder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BugOrderJdbcTemplateRepository implements BugOrderRepository {

    private final JdbcTemplate jdbcTemplate;

    public BugOrderJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<BugOrder> findAll() {

        final String sql = "select bug_order_id, `name`, `description` from bug_order order by `name`;";

        return jdbcTemplate.query(sql, new BugOrderMapper());
    }

    @Override
    public BugOrder findById(int bugOrderId) {

        final String sql = "select bug_order_id, `name`, `description` from bug_order "
                + "where bug_order_id = ?;";

        return jdbcTemplate.query(sql, new BugOrderMapper(), bugOrderId)
                .stream().findFirst().orElse(null);
    }
}
