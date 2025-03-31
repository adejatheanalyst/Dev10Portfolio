package learn.fund.data;

import learn.fund.models.Backer;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BackerJdbcClientRepository implements BackerRepository{
    private final JdbcClient jdbcClient;

    public BackerJdbcClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Backer findById(int backerId) {

        final String sql = """
                select backer_id, `name`, email_address
                from backer
                where backer_id = ?;
                """;

        return jdbcClient.sql(sql)
                .param(backerId)
                .query(Backer.class)
                .optional()
                .orElse(null);
    }

    @Override
    public List<Backer> findNameContains(String snippet) {

        final String sql = """
                select backer_id, `name`, email_address
                from backer
                where `name` like ?;
                """;

        return jdbcClient.sql(sql)
                .param("%" + snippet + "%")
                .query(Backer.class)
                .list();
    }

    @Override
    public Backer findByEmailAddress(String emailAddress) {

        final String sql = """
                select backer_id, `name`, email_address
                from backer
                where email_address = ?;
                """;

        return jdbcClient.sql(sql)
                .param( emailAddress )
                .query(Backer.class)
                .optional()
                .orElse(null);
    }

    @Override
    public Backer add(Backer backer) {

        final String sql = """
                insert into backer (`name`, email_address)
                values (:name, :email_address);
                """;

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcClient.sql(sql)
                .param("name", backer.getName())
                .param("email_address", backer.getEmailAddress())
                .update(keyHolder);

        int backerId = keyHolder.getKey().intValue();
        backer.setBackerId(backerId);
        return backer;
    }

    @Override
    public boolean update(Backer backer) {

        final String sql = """
                update backer set
                    `name` = ?,
                    email_address = ?
                where backer_id = ?;
                """;

        return jdbcClient.sql(sql)
                .param(backer.getName())
                .param(backer.getEmailAddress())
                .param(backer.getBackerId())
                .update() > 0;
    }

    @Override
    public boolean deleteById(int backerId) {
        jdbcClient.sql("delete from pledge where backer_id = ?;")
                .param(backerId)
                .update();
        return jdbcClient.sql("delete from backer where backer_id = ?;")
                .param(backerId)
                .update() > 0;
    }
}
