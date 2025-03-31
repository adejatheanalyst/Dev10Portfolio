package learn.fund.domain;

import learn.fund.data.BackerRepository;
import learn.fund.data.CampaignRepository;
import learn.fund.data.PledgeRepository;
import learn.fund.models.Pledge;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static learn.fund.TestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PledgeServiceTest {

    @MockBean
    BackerRepository backerRepository;
    @MockBean
    CampaignRepository campaignRepository;
    @MockBean
    PledgeRepository pledgeRepository;

    @Autowired
    PledgeService service;

    // add
    @Test
    void shouldAdd() {
        Pledge arg = makePledge();

        when(campaignRepository.findById(anyInt())).thenReturn(makeCampaign(2));
        when(backerRepository.findById(anyInt())).thenReturn(makeBacker(1));
        when(pledgeRepository.add(any())).thenReturn(makePledge());

        Result<Pledge> expected = makePayloadResult(makePledge());
        Result<Pledge> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullPledge() {
        Result<Pledge> expected = makeInvalidResult("pledge may not be null");
        Result<Pledge> actual = service.add(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullCampaign() {
        Pledge arg = makePledge();
        arg.setCampaign(null);
        Result<Pledge> expected = makeInvalidResult("campaign is required");
        Result<Pledge> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddInvalidCampaignId() {
        Pledge arg = makePledge();
        arg.getCampaign().setCampaignId(0);
        Result<Pledge> expected = makeInvalidResult("campaign is required");
        Result<Pledge> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullBacker() {
        Pledge arg = makePledge();
        arg.setBacker(null);
        Result<Pledge> expected = makeInvalidResult("backer is required");
        Result<Pledge> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddInvalidBackerId() {
        Pledge arg = makePledge();
        arg.getBacker().setBackerId(0);
        Result<Pledge> expected = makeInvalidResult("backer is required");
        Result<Pledge> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullAmount() {
        Pledge arg = makePledge();
        arg.setAmount(null);
        Result<Pledge> expected = makeInvalidResult("amount is required");
        Result<Pledge> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddInvalidAmount() {
        Pledge arg = makePledge();
        arg.setAmount(BigDecimal.ZERO);
        Result<Pledge> expected = makeInvalidResult("amount must be greater than $0.00");
        Result<Pledge> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddMissingCampaign() {
        Pledge arg = makePledge();
        arg.getCampaign().setCampaignId(3);

        when(backerRepository.findById(anyInt())).thenReturn(makeBacker(1));

        Result<Pledge> expected = makeInvalidResult("could not find campaign id 3");
        Result<Pledge> actual = service.add(arg);
        assertEquals(expected, actual);
    }


    @Test
    void shouldNotAddMissingBacker() {
        Pledge arg = makePledge();
        arg.getBacker().setBackerId(3);

        when(campaignRepository.findById(anyInt())).thenReturn(makeCampaign(2));

        Result<Pledge> expected = makeInvalidResult("could not find backer id 3");
        Result<Pledge> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    // update
    @Test
    void shouldUpdate() {
        Pledge arg = makePledge();

        when(campaignRepository.findById(anyInt())).thenReturn(makeCampaign(2));
        when(backerRepository.findById(anyInt())).thenReturn(makeBacker(1));
        when(pledgeRepository.update(any())).thenReturn(true);

        Result<?> expected = makePayloadResult(null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullPledge() {
        Result<?> expected = makeInvalidResult("pledge may not be null");
        Result<?> actual = service.update(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullCampaign() {
        Pledge arg = makePledge();
        arg.setCampaign(null);
        Result<?> expected = makeInvalidResult("campaign is required");
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateInvalidCampaignId() {
        Pledge arg = makePledge();
        arg.getCampaign().setCampaignId(0);
        Result<?> expected = makeInvalidResult("campaign is required");
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullBacker() {
        Pledge arg = makePledge();
        arg.setBacker(null);
        Result<?> expected = makeInvalidResult("backer is required");
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateInvalidBackerId() {
        Pledge arg = makePledge();
        arg.getBacker().setBackerId(0);
        Result<?> expected = makeInvalidResult("backer is required");
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullAmount() {
        Pledge arg = makePledge();
        arg.setAmount(null);
        Result<?> expected = makeInvalidResult("amount is required");
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateInvalidAmount() {
        Pledge arg = makePledge();
        arg.setAmount(BigDecimal.ZERO);
        Result<?> expected = makeInvalidResult("amount must be greater than $0.00");
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateMissingCampaign() {
        Pledge arg = makePledge();
        arg.getCampaign().setCampaignId(3);

        when(backerRepository.findById(anyInt())).thenReturn(makeBacker(1));

        Result<?> expected = makeInvalidResult("could not find campaign id 3");
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }


    @Test
    void shouldNotUpdateMissingBacker() {
        Pledge arg = makePledge();
        arg.getBacker().setBackerId(3);

        when(campaignRepository.findById(anyInt())).thenReturn(makeCampaign(2));

        Result<?> expected = makeInvalidResult("could not find backer id 3");
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateInvalidId() {
        Pledge arg = makePledge();
        arg.setPledgeId(2);

        when(campaignRepository.findById(anyInt())).thenReturn(makeCampaign(2));
        when(backerRepository.findById(anyInt())).thenReturn(makeBacker(1));

        Result<?> expected = makeNotFoundResult();
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    // delete
    @Test
    void shouldDelete() {
        when(pledgeRepository.deleteById(anyInt())).thenReturn(true);
        Result<?> expected = makePayloadResult(null);
        Result<?> actual = service.deleteById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotDelete() {
        Result<?> expected = makeNotFoundResult();
        Result<?> actual = service.deleteById(2);
        assertEquals(expected, actual);
    }
}