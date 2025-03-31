package learn.fund.data;

import learn.fund.models.Backer;
import learn.fund.models.Campaign;
import learn.fund.models.Pledge;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PledgeMapper implements RowMapper<Pledge> {
    @Override
    public Pledge mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pledge pledge = new Pledge();
        pledge.setPledgeId(rs.getInt("pledge_id"));
        pledge.setAmount(rs.getBigDecimal("amount"));

        Campaign campaign = new Campaign();
        campaign.setCampaignId(rs.getInt("campaign_id"));
        campaign.setName(rs.getString("campaign_name"));
        campaign.setDescription(rs.getString("description"));
        campaign.setStartDate(rs.getDate("start_date").toLocalDate());
        campaign.setEndDate(rs.getDate("end_date").toLocalDate());
        campaign.setFundingGoal(rs.getBigDecimal("funding_goal"));
        pledge.setCampaign(campaign);

        Backer backer = new Backer();
        backer.setBackerId(rs.getInt("backer_id"));
        backer.setName(rs.getString("backer_name"));
        backer.setEmailAddress(rs.getString("email_address"));
        pledge.setBacker(backer);

        return pledge;
    }
}
