package learn.fund.data;

import learn.fund.models.Campaign;

import java.time.LocalDate;
import java.util.List;

public interface CampaignRepository {
    Campaign findById(int campaignId);

    List<Campaign> findActive();

    List<Campaign> findActiveAndFuture();

    List<Campaign> findByDateRange(LocalDate start, LocalDate end);

    Campaign add(Campaign campaign);

    boolean update(Campaign campaign);

    boolean deleteById(int campaignId);
}
