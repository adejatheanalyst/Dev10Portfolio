package learn.fund.domain;

import learn.fund.data.CampaignRepositoryDouble;
import learn.fund.models.Campaign;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static learn.fund.TestHelper.makeCampaign;
import static learn.fund.TestHelper.makeResult;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CampaignServiceTest {

    CampaignService service = new CampaignService(new CampaignRepositoryDouble());

    // add
    @Test
    void shouldAdd() {
        Campaign arg = makeValidCampaign();
        Result<Campaign> expected = makeResult(null, makeCampaign(2));
        Result<Campaign> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullCampaign() {
        Result<Campaign> expected = makeResult("campaign may not be null", null);
        Result<Campaign> actual = service.add(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullName() {
        Campaign arg = makeValidCampaign();
        arg.setName(null);
        Result<Campaign> expected = makeResult("name is required", null);
        Result<Campaign> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddEmptyName() {
        Campaign arg = makeValidCampaign();
        arg.setName(" ");
        Result<Campaign> expected = makeResult("name is required", null);
        Result<Campaign> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullDescription() {
        Campaign arg = makeValidCampaign();
        arg.setDescription(null);
        Result<Campaign> expected = makeResult("description is required", null);
        Result<Campaign> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddEmptyDescription() {
        Campaign arg = makeValidCampaign();
        arg.setDescription(" ");
        Result<Campaign> expected = makeResult("description is required", null);
        Result<Campaign> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullStartDate() {
        Campaign arg = makeValidCampaign();
        arg.setStartDate(null);
        Result<Campaign> expected = makeResult("start date is required", null);
        Result<Campaign> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullEndDate() {
        Campaign arg = makeValidCampaign();
        arg.setEndDate(null);
        Result<Campaign> expected = makeResult("end date is required", null);
        Result<Campaign> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddEndDateBeforeStartDate() {
        Campaign arg = makeValidCampaign();
        arg.setStartDate(LocalDate.now().plusDays(75));
        Result<Campaign> expected = makeResult("end date must be after start date", null);
        Result<Campaign> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullFundingGoal() {
        Campaign arg = makeValidCampaign();
        arg.setFundingGoal(null);
        Result<Campaign> expected = makeResult("funding goal is required", null);
        Result<Campaign> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddFundingGoalsLessThan100() {
        Campaign arg = makeValidCampaign();
        arg.setFundingGoal(BigDecimal.TEN);
        Result<Campaign> expected = makeResult("funding goal must be at least $100.00", null);
        Result<Campaign> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    // update
    @Test
    void shouldUpdate() {
        Campaign arg = makeValidCampaign();
        Result<?> expected = makeResult(null, null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullCampaign() {
        Result<?> expected = makeResult("campaign may not be null", null);
        Result<?> actual = service.update(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullName() {
        Campaign arg = makeValidCampaign();
        arg.setName(null);
        Result<?> expected = makeResult("name is required", null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateEmptyName() {
        Campaign arg = makeValidCampaign();
        arg.setName(" ");
        Result<?> expected = makeResult("name is required", null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullDescription() {
        Campaign arg = makeValidCampaign();
        arg.setDescription(null);
        Result<?> expected = makeResult("description is required", null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateEmptyDescription() {
        Campaign arg = makeValidCampaign();
        arg.setDescription(" ");
        Result<?> expected = makeResult("description is required", null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullStartDate() {
        Campaign arg = makeValidCampaign();
        arg.setStartDate(null);
        Result<?> expected = makeResult("start date is required", null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullEndDate() {
        Campaign arg = makeValidCampaign();
        arg.setEndDate(null);
        Result<?> expected = makeResult("end date is required", null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateEndDateBeforeStartDate() {
        Campaign arg = makeValidCampaign();
        arg.setStartDate(LocalDate.now().plusDays(75));
        Result<?> expected = makeResult("end date must be after start date", null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullFundingGoal() {
        Campaign arg = makeValidCampaign();
        arg.setFundingGoal(null);
        Result<?> expected = makeResult("funding goal is required", null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateFundingGoalsLessThan100() {
        Campaign arg = makeValidCampaign();
        arg.setFundingGoal(BigDecimal.TEN);
        Result<?> expected = makeResult("funding goal must be at least $100.00", null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateInvalidId() {
        Campaign arg = makeValidCampaign();
        arg.setCampaignId(1);
        Result<?> expected = makeResult("could not update campaign id 1", null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    // delete
    @Test
    void shouldDelete() {
        Result<?> expected = makeResult(null, null);
        Result<?> actual = service.deleteById(2);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotDelete() {
        Result<?> expected = makeResult("could not delete campaign id 1", null);
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