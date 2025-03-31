package learn.fund.data;

import learn.fund.models.Pledge;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PledgeJdbcClientRepository implements PledgeRepository{

    private final JdbcClient jdbcClient;

    public PledgeJdbcClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
    @Override
    public Pledge findById(int pledgeId) {

        final String sql = """
                select p.pledge_id, p.amount,
                    c.campaign_id, c.`name` campaign_name, c.`description`,
                    c.start_date, c.end_date, c.funding_goal,
                    b.backer_id, b.`name` backer_name, b.email_address
                from pledge p
                inner join campaign c on p.campaign_id = c.campaign_id
                inner join backer b on p.backer_id = b.backer_id
                where p.pledge_id = ?;
                """;

        return jdbcClient.sql(sql)
                .param(pledgeId)
                .query(new PledgeMapper())
                .optional()
                .orElse(null);
    }

    @Override
    public List<Pledge> findByCampaignId(int campaignId) {

        final String sql = """
                select p.pledge_id, p.amount,
                    c.campaign_id, c.`name` campaign_name, c.`description`,
                    c.start_date, c.end_date, c.funding_goal,
                    b.backer_id, b.`name` backer_name, b.email_address
                from pledge p
                inner join campaign c on p.campaign_id = c.campaign_id
                inner join backer b on p.backer_id = b.backer_id
                where p.campaign_id = ?;
                """;

        return jdbcClient.sql(sql)
                .param(campaignId)
                .query(new PledgeMapper())
                .list();
    }

    @Override
    public List<Pledge> findByBackerId(int backerId) {

        final String sql = """
                select p.pledge_id, p.amount,
                    c.campaign_id, c.`name` campaign_name, c.`description`,
                    c.start_date, c.end_date, c.funding_goal,
                    b.backer_id, b.`name` backer_name, b.email_address
                from pledge p
                inner join campaign c on p.campaign_id = c.campaign_id
                inner join backer b on p.backer_id = b.backer_id
                where p.backer_id = ?;
                """;

        return jdbcClient.sql(sql)
                .param(backerId)
                .query(new PledgeMapper())
                .list();
    }

    @Override
    public Pledge add(Pledge pledge) {

        final String sql = """
                insert into pledge (campaign_id, backer_id, amount)
                values (:campaign_id, :backer_id, :amount);
                """;

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcClient.sql(sql)
                .param("campaign_id", pledge.getCampaign().getCampaignId())
                .param("backer_id", pledge.getBacker().getBackerId())
                .param("amount", pledge.getAmount())
                .update(keyHolder, "pledge_id");

        int pledgeId = keyHolder.getKey().intValue();
        pledge.setPledgeId(pledgeId);
        return pledge;
    }

    @Override
    public boolean update(Pledge pledge) {

        final String sql = """
                update pledge set
                    amount = ?
                where pledge_id = ?;
                """;

        return jdbcClient.sql(sql)
                .param(pledge.getAmount())
                .param(pledge.getPledgeId())
                .update() > 0;
    }

    @Override
    public boolean deleteById(int pledgeId) {
        return jdbcClient.sql("delete from pledge where pledge_id = ?;")
                .param(pledgeId)
                .update() > 0;
    }
}
