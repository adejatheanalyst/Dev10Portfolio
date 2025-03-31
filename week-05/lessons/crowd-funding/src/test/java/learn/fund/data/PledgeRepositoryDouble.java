package learn.fund.data;

import learn.fund.models.Pledge;

import java.util.List;

import static learn.fund.TestHelper.makePledge;

public class PledgeRepositoryDouble implements PledgeRepository {
    @Override
    public Pledge findById(int pledgeId) {
        return makePledge();
    }

    @Override
    public List<Pledge> findByCampaignId(int campaignId) {
        if (campaignId == 2) {
            List.of(makePledge());
        }
        return List.of();
    }

    @Override
    public List<Pledge> findByBackerId(int backerId) {
        if (backerId == 1) {
            List.of(makePledge());
        }
        return List.of();
    }

    @Override
    public Pledge add(Pledge pledge) {
        return makePledge();
    }

    @Override
    public boolean update(Pledge pledge) {
        return pledge.getPledgeId() == 1;
    }

    @Override
    public boolean deleteById(int pledgeId) {
        return pledgeId == 1;
    }
}
