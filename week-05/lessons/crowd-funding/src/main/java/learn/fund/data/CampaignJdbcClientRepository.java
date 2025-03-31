package learn.fund.data;

import learn.fund.models.Campaign;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public class CampaignJdbcClientRepository implements CampaignRepository {

    private final JdbcClient jdbcClient;

    public CampaignJdbcClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Campaign findById(int campaignId) {

        final String sql = """
                select campaign_id, `name`, `description`,
                start_date, end_date, funding_goal
                from campaign
                where campaign_id = ?;""";

        return jdbcClient.sql(sql)
                .param(campaignId)
                .query(Campaign.class)
                .optional().orElse(null);
    }

    @Override
    public List<Campaign> findActive() {

        final String sql = """
                select campaign_id, `name`, `description`,
                start_date, end_date, funding_goal
                from campaign
                where ? between start_date and end_date
                order by start_date asc;
                """;

        return jdbcClient.sql(sql)
                .param(LocalDate.now())
                .query(Campaign.class)
                .list();
    }

    @Override
    public List<Campaign> findByDateRange(LocalDate start, LocalDate end) {

        final String sql = """
                select campaign_id, `name`, `description`,
                    start_date, end_date, funding_goal
                from campaign
                where not (end_date < :start
                    or start_date > :end)
                order by start_date asc;
                """;

        return jdbcClient.sql(sql)
                .param("start", start)
                .param("end", end)
                .query(Campaign.class)
                .list();
    }


    @Override
    public List<Campaign> findActiveAndFuture() {

        final String sql = """
                select campaign_id, `name`, `description`,
                start_date, end_date, funding_goal
                from campaign
                where end_date >= ?
                order by start_date asc;
                """;

        return jdbcClient.sql(sql)
                .param(LocalDate.now())
                .query(Campaign.class)
                .list();
    }

    @Override
    public Campaign add(Campaign campaign) {

        final String sql = """
                insert into campaign (`name`, `description`, start_date, end_date, funding_goal)
                values (:name, :description, :start_date, :end_date, :funding_goal);
                """;

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql(sql)
                .param("name", campaign.getName())
                .param("description", campaign.getDescription())
                .param("start_date", campaign.getStartDate())
                .param("end_date", campaign.getEndDate())
                .param("funding_goal", campaign.getFundingGoal())
                .update(keyHolder, "campaign_id");

        int campaignId = keyHolder.getKey().intValue();
        campaign.setCampaignId(campaignId);
        return campaign;
    }

    @Override
    public boolean update(Campaign campaign) {

        final String sql = """
                update campaign set
                    `name` = ?,
                    `description` = ?,
                    start_date = ?,
                    end_date = ?,
                    funding_goal = ?
                where campaign_id = ?;
                """;

        return jdbcClient.sql(sql)
                .param(campaign.getName())
                .param(campaign.getDescription())
                .param(campaign.getStartDate())
                .param(campaign.getEndDate())
                .param(campaign.getFundingGoal())
                .param(campaign.getCampaignId())
                .update() > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int campaignId) {

        jdbcClient.sql("delete from pledge where campaign_id = ?;")
                .param(campaignId)
                .update();

        return jdbcClient.sql("delete from campaign where campaign_id = ?;")
                .param(campaignId)
                .update() > 0;
    }
}
