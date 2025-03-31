package learn.fund.data;

import learn.fund.models.Pledge;

import java.util.List;

public interface PledgeRepository {
    Pledge findById(int pledgeId);

    List<Pledge> findByCampaignId(int campaignId);

    List<Pledge> findByBackerId(int backerId);

    Pledge add(Pledge pledge);

    boolean update(Pledge pledge);

    boolean deleteById(int pledgeId);
}
