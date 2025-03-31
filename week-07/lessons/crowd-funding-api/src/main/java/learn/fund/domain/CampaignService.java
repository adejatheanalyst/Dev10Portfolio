package learn.fund.domain;

import learn.fund.data.CampaignRepository;
import learn.fund.models.Campaign;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static learn.fund.domain.Validation.isNullOrBlank;

@Service
public class CampaignService {
    private final CampaignRepository repository;

    public CampaignService(CampaignRepository repository) {
        this.repository = repository;
    }

    public List<Campaign> findActive() {
        return repository.findActive();
    }

    public List<Campaign> findByDateRange(LocalDate start, LocalDate end) {
        return repository.findByDateRange(start, end);
    }

    public Result<Campaign> add(Campaign campaign) {
        Result<Campaign> result = validate(campaign);
        if (!result.isSuccess()) {
            return result;
        }

        Campaign c = repository.add(campaign);
        result.setPayload(c);
        return result;
    }

    public Result<?> update(Campaign campaign) {
        Result<Campaign> result = validate(campaign);
        if (!result.isSuccess()) {
            return result;
        }

        boolean success = repository.update(campaign);
        if (!success) {
            result.setNotFound();
        }

        return result;
    }

    public Result<?> deleteById(int campaignId) {
        Result<?> result = new Result<>();
        boolean success = repository.deleteById(campaignId);
        if (!success) {
            result.setNotFound();
        }
        return result;
    }

    public Result<Campaign> validate(Campaign campaign) {
        Result<Campaign> result = new Result<>();
        if (campaign == null) {
            result.addErrorMessage("campaign may not be null");
            // return early to avoid null reference
            return result;
        }

        if (isNullOrBlank(campaign.getName())) {
            result.addErrorMessage("name is required");
        }

        if (isNullOrBlank(campaign.getDescription())) {
            result.addErrorMessage("description is required");
        }

        if (campaign.getStartDate() == null || campaign.getEndDate() == null) {
            if (campaign.getStartDate() == null) {
                result.addErrorMessage("start date is required");
            }
            if (campaign.getEndDate() == null) {
                result.addErrorMessage("end date is required");
            }
            // return early to avoid null date reference
            return result;
        }

        if (!campaign.getEndDate().isAfter(campaign.getStartDate())) {
            result.addErrorMessage("end date must be after start date");
        }

        if (campaign.getFundingGoal() == null) {
            result.addErrorMessage("funding goal is required");
            // return early to avoid null funding goal reference
            return result;
        }

        if (campaign.getFundingGoal().compareTo(new BigDecimal("100.00")) < 0) {
            result.addErrorMessage("funding goal must be at least $100.00");
        }

        return result;
    }
}
