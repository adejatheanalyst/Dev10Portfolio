package learn.foraging.data;

import learn.foraging.models.Forager;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

public class ForagerJdbcClientRepository implements ForagerRepository {

    private final JdbcClient jdbcClient;

    public ForagerJdbcClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Forager findById(int id) {
        return null;
    }

    @Override
    public List<Forager> findByLastName(String lastNamePrefix) {
        final String sql = """
                select forager_id, first_name, last_name, state_abbr
                from forager
                where last_name like ?;
                """;
        return jdbcClient.sql(sql)
                .param(lastNamePrefix + "%")
                .query(new ForagerMapper())
                .list();
    }

    @Override
    public List<Forager> findByState(String stateAbbr) {
        return null;
    }
}
