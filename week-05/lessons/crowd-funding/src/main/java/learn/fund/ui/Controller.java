package learn.fund.ui;

import learn.fund.domain.BackerService;
import learn.fund.domain.CampaignService;
import learn.fund.domain.PledgeService;
import learn.fund.domain.Result;
import learn.fund.models.Backer;
import learn.fund.models.Campaign;
import learn.fund.models.Pledge;

import java.time.LocalDate;
import java.util.List;

public class Controller {
    private final BackerService backerService;
    private final CampaignService campaignService;
    private final PledgeService pledgeService;
    private final View view = new View();

    public Controller(BackerService backerService, CampaignService campaignService, PledgeService pledgeService) {
        this.backerService = backerService;
        this.campaignService = campaignService;
        this.pledgeService = pledgeService;
    }

    public void run() {
        view.displayHeader("Welcome to Crowd Fund");
        try {
            runApp();
        } catch (Exception ex) {
            view.displayException(ex);
        }
        view.displayMessage("Good bye.");
    }

    private void runApp() {

        MainMenuOption option;
        do {
            option = view.selectMainMenuOption();
            switch (option) {
                case ADD_BACKER -> addBacker();
                case ADD_CAMPAIGN -> addCampaign();
                case VIEW_CAMPAIGNS -> viewCampaigns();
                case ADD_PLEDGE -> addPledge();
                case VIEW_CAMPAIGN_PLEDGES -> viewCampaignPledges();
                case VIEW_BACKER_PLEDGES -> viewBackerPledges();
            }
        } while (option != MainMenuOption.EXIT);
    }

    private void addBacker() {
        Backer backer = view.buildBacker();
        Result<Backer> result = backerService.add(backer);
        view.displayResult(result);
    }

    private void addCampaign() {
        Campaign campaign = view.buildCampaign();
        Result<Campaign> result = campaignService.add(campaign);
        view.displayResult(result);
    }

    private void viewCampaigns() {
        view.displayHeader(MainMenuOption.VIEW_CAMPAIGNS.getLabel());
        LocalDate start = view.readLocalDate("Start [MM/dd/yyyy]: ");
        LocalDate end = view.readLocalDate("End [MM/dd/yyyy]: ");
        List<Campaign> campaigns = campaignService.findByDateRange(start, end);
        view.displayCampaigns(campaigns);
    }

    private void addPledge() {
        view.displayHeader(MainMenuOption.ADD_PLEDGE.getLabel());
        Campaign campaign = selectCampaign();
        if (campaign == null) {
            return;
        }
        Backer backer = selectBacker();
        if (backer == null) {
            return;
        }
        Pledge pledge = view.buildPledge(campaign, backer);
        Result<Pledge> result = pledgeService.add(pledge);
        view.displayResult(result);
    }

    private void viewBackerPledges() {
        view.displayHeader(MainMenuOption.VIEW_BACKER_PLEDGES.getLabel());
        Backer backer = selectBacker();
        if (backer == null) {
            return;
        }
        view.displayPledges(pledgeService.findByBackerId(backer.getBackerId()));
    }

    private void viewCampaignPledges() {
        view.displayHeader(MainMenuOption.VIEW_CAMPAIGN_PLEDGES.getLabel());
        Campaign campaign = selectCampaign();
        if (campaign == null) {
            return;
        }
        view.displayPledges(pledgeService.findByCampaignId(campaign.getCampaignId()));
    }

    private Backer selectBacker() {
        String snippet = view.readSnippet();
        List<Backer> backers = backerService.findNameContains(snippet);
        return view.selectBacker(backers);
    }

    private Campaign selectCampaign() {
        List<Campaign> activeCampaigns = campaignService.findActive();
        return view.selectCampaign(activeCampaigns);
    }
}
