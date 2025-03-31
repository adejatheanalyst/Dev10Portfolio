package learn.fund.domain;

import learn.fund.data.BackerRepositoryDouble;
import learn.fund.data.CampaignRepositoryDouble;
import learn.fund.data.PledgeRepositoryDouble;
import learn.fund.models.Pledge;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static learn.fund.TestHelper.makePledge;
import static learn.fund.TestHelper.makeResult;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PledgeServiceTest {

    PledgeService service = new PledgeService(new BackerRepositoryDouble(),
            new CampaignRepositoryDouble(), new PledgeRepositoryDouble());

    // add
    @Test
    void shouldAdd() {
        Pledge arg = makePledge();
        Result<Pledge> expected = makeResult(null, makePledge());
        Result<Pledge> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullPledge() {
        Result<Pledge> expected = makeResult("pledge may not be null", null);
        Result<Pledge> actual = service.add(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullCampaign() {
        Pledge arg = makePledge();
        arg.setCampaign(null);
        Result<Pledge> expected = makeResult("campaign is required", null);
        Result<Pledge> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddInvalidCampaignId() {
        Pledge arg = makePledge();
        arg.getCampaign().setCampaignId(0);
        Result<Pledge> expected = makeResult("campaign is required", null);
        Result<Pledge> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullBacker() {
        Pledge arg = makePledge();
        arg.setBacker(null);
        Result<Pledge> expected = makeResult("backer is required", null);
        Result<Pledge> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddInvalidBackerId() {
        Pledge arg = makePledge();
        arg.getBacker().setBackerId(0);
        Result<Pledge> expected = makeResult("backer is required", null);
        Result<Pledge> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullAmount() {
        Pledge arg = makePledge();
        arg.setAmount(null);
        Result<Pledge> expected = makeResult("amount is required", null);
        Result<Pledge> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddInvalidAmount() {
        Pledge arg = makePledge();
        arg.setAmount(BigDecimal.ZERO);
        Result<Pledge> expected = makeResult("amount must be greater than $0.00", null);
        Result<Pledge> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddMissingCampaign() {
        Pledge arg = makePledge();
        arg.getCampaign().setCampaignId(3);
        Result<Pledge> expected = makeResult("could not find campaign id 3", null);
        Result<Pledge> actual = service.add(arg);
        assertEquals(expected, actual);
    }


    @Test
    void shouldNotAddMissingBacker() {
        Pledge arg = makePledge();
        arg.getBacker().setBackerId(3);
        Result<Pledge> expected = makeResult("could not find backer id 3", null);
        Result<Pledge> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    // update
    @Test
    void shouldUpdate() {
        Pledge arg = makePledge();
        Result<?> expected = makeResult(null, null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullPledge() {
        Result<?> expected = makeResult("pledge may not be null", null);
        Result<?> actual = service.update(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullCampaign() {
        Pledge arg = makePledge();
        arg.setCampaign(null);
        Result<?> expected = makeResult("campaign is required", null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateInvalidCampaignId() {
        Pledge arg = makePledge();
        arg.getCampaign().setCampaignId(0);
        Result<?> expected = makeResult("campaign is required", null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullBacker() {
        Pledge arg = makePledge();
        arg.setBacker(null);
        Result<?> expected = makeResult("backer is required", null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateInvalidBackerId() {
        Pledge arg = makePledge();
        arg.getBacker().setBackerId(0);
        Result<?> expected = makeResult("backer is required", null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullAmount() {
        Pledge arg = makePledge();
        arg.setAmount(null);
        Result<?> expected = makeResult("amount is required", null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateInvalidAmount() {
        Pledge arg = makePledge();
        arg.setAmount(BigDecimal.ZERO);
        Result<?> expected = makeResult("amount must be greater than $0.00", null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateMissingCampaign() {
        Pledge arg = makePledge();
        arg.getCampaign().setCampaignId(3);
        Result<?> expected = makeResult("could not find campaign id 3", null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }


    @Test
    void shouldNotUpdateMissingBacker() {
        Pledge arg = makePledge();
        arg.getBacker().setBackerId(3);
        Result<?> expected = makeResult("could not find backer id 3", null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateInvalidId() {
        Pledge arg = makePledge();
        arg.setPledgeId(2);
        Result<?> expected = makeResult("could not update pledge id 2", null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    // delete
    @Test
    void shouldDelete() {
        Result<?> expected = makeResult(null, null);
        Result<?> actual = service.deleteById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotDelete() {
        Result<?> expected = makeResult("could not delete pledge id 2", null);
        Result<?> actual = service.deleteById(2);
        assertEquals(expected, actual);
    }
}