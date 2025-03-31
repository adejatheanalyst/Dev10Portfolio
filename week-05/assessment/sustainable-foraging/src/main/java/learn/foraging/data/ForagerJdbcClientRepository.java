package learn.foraging.data;

import learn.foraging.models.Forager;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ForagerJdbcClientRepository implements ForagerRepository {

    private final JdbcClient jdbcClient;

    public ForagerJdbcClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Forager findById(int id) {
        final String sql = """
                select forager_id, first_name, last_name, state_abbr
                from forager
                where forager_id = ?;
                """;
        return jdbcClient.sql(sql)
                .param(id)
                .query(new ForagerMapper())
                .optional()
                .orElse(null);
    }

    @Override
    public Forager create(Forager forager) {
        final String sql = "insert into forager (first_name, last_name, state_abbr) values (:first_name, :last_name, :state_abbr); ";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql(sql)
                .param("first_name", forager.getFirstName())
                .param("last_name", forager.getLastName())
                .param("state_abbr", forager.getState())
                .update(keyHolder, "forager_id");

        int foragerId = keyHolder.getKey().intValue();
        forager.setId(foragerId);

        return forager;

    }

    @Override
    public boolean deleteById(int id) {
        final String sql = "delete from forager where forager_id = ?;";
        int rowsAffected = jdbcClient.sql(sql)
                .param(id)
                .update();
        return rowsAffected > 0;
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
        final String sql = """
                select forager_id, first_name, last_name, state_abbr
                from forager
                where state_abbr = ?;
                """;
        return jdbcClient.sql(sql)
                .param(stateAbbr)
                .query(new ForagerMapper())
                .list();
    }
}
