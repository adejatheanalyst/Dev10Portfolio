package learn.fund.domain;

import learn.fund.data.BackerRepository;
import learn.fund.data.CampaignRepository;
import learn.fund.data.PledgeRepository;
import learn.fund.models.Backer;
import learn.fund.models.Campaign;
import learn.fund.models.Pledge;
import org.springframework.dao.DuplicateKeyException;

import java.math.BigDecimal;
import java.util.List;

public class PledgeService {
    private final BackerRepository backerRepository;
    private final CampaignRepository campaignRepository;
    private final PledgeRepository pledgeRepository;

    public PledgeService(BackerRepository backerRepository,
                         CampaignRepository campaignRepository,
                         PledgeRepository pledgeRepository) {
        this.backerRepository = backerRepository;
        this.campaignRepository = campaignRepository;
        this.pledgeRepository = pledgeRepository;
    }

    public List<Pledge> findByCampaignId(int campaignId) {
        return pledgeRepository.findByCampaignId(campaignId);
    }

    public List<Pledge> findByBackerId(int backerId) {
        return pledgeRepository.findByBackerId(backerId);
    }

    public Result<Pledge> add(Pledge pledge) {
        Result<Pledge> result = validate(pledge);
        if (!result.isSuccess()) {
            return result;
        }

        try {
            Pledge p = pledgeRepository.add(pledge);
            result.setPayload(p);
        } catch (DuplicateKeyException ex) {
            result.addErrorMessage("this backer has already backed this campaign");
        }
        return result;
    }

    public Result<?> update(Pledge pledge) {
        Result<Pledge> result = validate(pledge);
        if (!result.isSuccess()) {
            return result;
        }

        boolean success = pledgeRepository.update(pledge);
        if (!success) {
            String msg = String.format(
                    "could not update pledge id %s", pledge.getPledgeId());
            result.addErrorMessage(msg);
        }

        return result;
    }

    public Result<?> deleteById(int pledgeId) {
        Result<?> result = new Result<>();
        boolean success = pledgeRepository.deleteById(pledgeId);
        if (!success) {
            String msg = String.format(
                    "could not delete pledge id %s", pledgeId);
            result.addErrorMessage(msg);
        }
        return result;
    }

    private Result<Pledge> validate(Pledge pledge) {
        Result<Pledge> result = new Result<>();
        if (pledge == null) {
            result.addErrorMessage("pledge may not be null");
            // return early to avoid null reference
            return result;
        }

        if (pledge.getCampaign() == null || pledge.getCampaign().getCampaignId() <= 0) {
            result.addErrorMessage("campaign is required");
        }

        if (pledge.getBacker() == null || pledge.getBacker().getBackerId() <= 0) {
            result.addErrorMessage("backer is required");
        }

        if (pledge.getAmount() == null) {
            result.addErrorMessage("amount is required");
            // return early to avoid null amount reference
            return result;
        }

        if (pledge.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            result.addErrorMessage("amount must be greater than $0.00");
        }

        if (!result.isSuccess()) {
            // early return to avoid null campaign or backer
            return result;
        }

        Campaign campaign = campaignRepository.findById(pledge.getCampaign().getCampaignId());
        if (campaign == null) {
            String msg = String.format("could not find campaign id %s",
                    pledge.getCampaign().getCampaignId());
            result.addErrorMessage(msg);
        }

        Backer backer = backerRepository.findById(pledge.getBacker().getBackerId());
        if (backer == null) {
            String msg = String.format("could not find backer id %s",
                    pledge.getBacker().getBackerId());
            result.addErrorMessage(msg);
        }

        return result;
    }
}
