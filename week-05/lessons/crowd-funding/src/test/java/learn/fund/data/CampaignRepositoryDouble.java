package learn.fund.data;

import learn.fund.models.Campaign;

import java.time.LocalDate;
import java.util.List;

import static learn.fund.TestHelper.makeCampaign;

public class CampaignRepositoryDouble implements CampaignRepository {
    @Override
    public Campaign findById(int campaignId) {
        if (campaignId == 2) {
            return makeCampaign(2);
        }
        return null;
    }

    @Override
    public List<Campaign> findActive() {
        return List.of(makeCampaign(2));
    }

    @Override
    public List<Campaign> findActiveAndFuture() {
        return List.of(makeCampaign(2));
    }

    @Override
    public List<Campaign> findByDateRange(LocalDate start, LocalDate end) {
        return List.of(makeCampaign(2));
    }

    @Override
    public Campaign add(Campaign campaign) {
        return makeCampaign(2);
    }

    @Override
    public boolean update(Campaign campaign) {
        return campaign.getCampaignId() == 2;
    }

    @Override
    public boolean deleteById(int campaignId) {
        return campaignId == 2;
    }
}
