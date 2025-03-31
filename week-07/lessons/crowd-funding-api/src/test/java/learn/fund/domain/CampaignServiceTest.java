package learn.fund.domain;

import learn.fund.data.CampaignRepository;
import learn.fund.models.Campaign;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;

import static learn.fund.TestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CampaignServiceTest {

    @MockBean
    CampaignRepository repository;

    @Autowired
    CampaignService service;

    // add
    @Test
    void shouldAdd() {
        Campaign arg = makeValidCampaign();

        when(repository.add(any())).thenReturn(makeCampaign(2));

        Result<Campaign> expected = makePayloadResult(makeCampaign(2));
        Result<Campaign> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullCampaign() {
        Result<Campaign> expected = makeInvalidResult("campaign may not be null");
        Result<Campaign> actual = service.add(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullName() {
        Campaign arg = makeValidCampaign();
        arg.setName(null);
        Result<Campaign> expected = makeInvalidResult("name is required");
        Result<Campaign> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddEmptyName() {
        Campaign arg = makeValidCampaign();
        arg.setName(" ");
        Result<Campaign> expected = makeInvalidResult("name is required");
        Result<Campaign> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullDescription() {
        Campaign arg = makeValidCampaign();
        arg.setDescription(null);
        Result<Campaign> expected = makeInvalidResult("description is required");
        Result<Campaign> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddEmptyDescription() {
        Campaign arg = makeValidCampaign();
        arg.setDescription(" ");
        Result<Campaign> expected = makeInvalidResult("description is required");
        Result<Campaign> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullStartDate() {
        Campaign arg = makeValidCampaign();
        arg.setStartDate(null);
        Result<Campaign> expected = makeInvalidResult("start date is required");
        Result<Campaign> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullEndDate() {
        Campaign arg = makeValidCampaign();
        arg.setEndDate(null);
        Result<Campaign> expected = makeInvalidResult("end date is required");
        Result<Campaign> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddEndDateBeforeStartDate() {
        Campaign arg = makeValidCampaign();
        arg.setStartDate(LocalDate.now().plusDays(75));
        Result<Campaign> expected = makeInvalidResult("end date must be after start date");
        Result<Campaign> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullFundingGoal() {
        Campaign arg = makeValidCampaign();
        arg.setFundingGoal(null);
        Result<Campaign> expected = makeInvalidResult("funding goal is required");
        Result<Campaign> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddFundingGoalsLessThan100() {
        Campaign arg = makeValidCampaign();
        arg.setFundingGoal(BigDecimal.TEN);
        Result<Campaign> expected = makeInvalidResult("funding goal must be at least $100.00");
        Result<Campaign> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    // update
    @Test
    void shouldUpdate() {
        Campaign arg = makeValidCampaign();
        when(repository.update(any())).thenReturn(true);
        Result<?> expected = makePayloadResult(null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullCampaign() {
        Result<?> expected = makeInvalidResult("campaign may not be null");
        Result<?> actual = service.update(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullName() {
        Campaign arg = makeValidCampaign();
        arg.setName(null);
        Result<?> expected = makeInvalidResult("name is required");
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateEmptyName() {
        Campaign arg = makeValidCampaign();
        arg.setName(" ");
        Result<?> expected = makeInvalidResult("name is required");
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullDescription() {
        Campaign arg = makeValidCampaign();
        arg.setDescription(null);
        Result<?> expected = makeInvalidResult("description is required");
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateEmptyDescription() {
        Campaign arg = makeValidCampaign();
        arg.setDescription(" ");
        Result<?> expected = makeInvalidResult("description is required");
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullStartDate() {
        Campaign arg = makeValidCampaign();
        arg.setStartDate(null);
        Result<?> expected = makeInvalidResult("start date is required");
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullEndDate() {
        Campaign arg = makeValidCampaign();
        arg.setEndDate(null);
        Result<?> expected = makeInvalidResult("end date is required");
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateEndDateBeforeStartDate() {
        Campaign arg = makeValidCampaign();
        arg.setStartDate(LocalDate.now().plusDays(75));
        Result<?> expected = makeInvalidResult("end date must be after start date");
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullFundingGoal() {
        Campaign arg = makeValidCampaign();
        arg.setFundingGoal(null);
        Result<?> expected = makeInvalidResult("funding goal is required");
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateFundingGoalsLessThan100() {
        Campaign arg = makeValidCampaign();
        arg.setFundingGoal(BigDecimal.TEN);
        Result<?> expected = makeInvalidResult("funding goal must be at least $100.00");
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateInvalidId() {
        Campaign arg = makeValidCampaign();
        arg.setCampaignId(1);
        Result<?> expected = makeNotFoundResult();
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    // delete
    @Test
    void shouldDelete() {
        when(repository.deleteById(anyInt())).thenReturn(true);
        Result<?> expected = makePayloadResult(null);
        Result<?> actual = service.deleteById(2);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotDelete() {
        Result<?> expected = makeNotFoundResult();
        Result<?> actual = service.deleteById(1);
        assertEquals(expected, actual);
    }

    Campaign makeValidCampaign() {
        Campaign campaign = makeCampaign(2);
        campaign.setStartDate(LocalDate.now().plusDays(5));
        campaign.setEndDate(LocalDate.now().plusDays(65));
        return campaign;
    }
}