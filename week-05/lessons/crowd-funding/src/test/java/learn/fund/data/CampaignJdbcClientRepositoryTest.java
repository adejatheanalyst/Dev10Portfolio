package learn.fund.data;

import learn.fund.DataHelper;
import learn.fund.models.Campaign;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static learn.fund.TestHelper.makeCampaign;
import static org.junit.jupiter.api.Assertions.*;

class CampaignJdbcClientRepositoryTest {

    static final int MISSING_ID = 12;

    JdbcClient jdbcClient = DataHelper.getJdbcClient();
    CampaignJdbcClientRepository repository = new CampaignJdbcClientRepository(jdbcClient);

    @BeforeEach
    void setup() {
        jdbcClient.sql("call set_known_good_state()").update();
    }

    @Test
    void shouldFindById() {
        Campaign expected = makeCampaign(2);
        Campaign actual = repository.findById(2);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindById() {
        Campaign actual = repository.findById(MISSING_ID);
        assertNull(actual);
    }

    @Test
    void shouldFindActive() {
        List<Campaign> expected = List.of(
                makeCampaign(2)
        );
        List<Campaign> actual = repository.findActive();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindActiveAndFuture() {
        List<Campaign> expected = List.of(
                makeCampaign(2)
        );
        List<Campaign> actual = repository.findActiveAndFuture();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByDateRange() {
        List<Campaign> expected = List.of(
                makeCampaign(1),
                makeCampaign(2)
        );
        LocalDate now = LocalDate.now();
        List<Campaign> actual = repository.findByDateRange(now.plusDays(-45), now.plusDays(45));
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindByDateRange() {
        List<Campaign> expected = List.of();
        LocalDate now = LocalDate.now();
        List<Campaign> actual = repository.findByDateRange(now.plusDays(-180), now.plusDays(-120));
        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd() {
        Campaign arg = makeCampaign(3);
        arg.setCampaignId(0);
        Campaign expected = makeCampaign(3);
        Campaign actual = repository.add(arg);
        assertEquals(expected, actual);

        actual = repository.findById(3);
        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdate() {
        Campaign expected = makeCampaign(2);
        expected.setName("Updated");
        expected.setDescription("Updated");
        expected.setStartDate(expected.getStartDate().plusDays(5));
        expected.setEndDate(expected.getEndDate().plusDays(5));
        expected.setFundingGoal(expected.getFundingGoal().add(BigDecimal.TEN));
        assertTrue(repository.update(expected));

        Campaign actual = repository.findById(2);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdate() {
        Campaign arg = makeCampaign(MISSING_ID);
        assertFalse(repository.update(arg));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById(1));
    }

    @Test
    void shouldNotDelete() {
        assertFalse(repository.deleteById(MISSING_ID));
    }
}