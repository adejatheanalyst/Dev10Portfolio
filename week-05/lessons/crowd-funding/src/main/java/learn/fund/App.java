package learn.fund;

import learn.fund.data.*;
import learn.fund.domain.BackerService;
import learn.fund.domain.CampaignService;
import learn.fund.domain.PledgeService;
import learn.fund.ui.Controller;
import org.springframework.jdbc.core.simple.JdbcClient;

public class App {
    public static void main(String[] args) {

        JdbcClient jdbcClient = DataHelper.getJdbcClient();

        // repositories
        BackerJdbcClientRepository backerRepository
                = new BackerJdbcClientRepository(jdbcClient);
        CampaignJdbcClientRepository campaignRepository
                = new CampaignJdbcClientRepository(jdbcClient);
        PledgeJdbcClientRepository pledgeRepository
                = new PledgeJdbcClientRepository(jdbcClient);

        // services
        BackerService backerService = new BackerService(backerRepository);
        CampaignService campaignService = new CampaignService(campaignRepository);
        PledgeService pledgeService = new PledgeService(
                backerRepository, campaignRepository, pledgeRepository);

        // controller
        Controller controller = new Controller(backerService, campaignService, pledgeService);
        controller.run();
    }
}
