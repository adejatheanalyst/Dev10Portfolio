package learn.fund.data;

import learn.fund.models.Campaign;
import learn.fund.models.Pledge;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.math.BigDecimal;
import java.util.List;

import static learn.fund.TestHelper.makeBacker;
import static learn.fund.TestHelper.makeCampaign;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PledgeJdbcClientRepositoryTest {

    static final int MISSING_ID = 100;
    static final BigDecimal ONE_HUNDRED = new BigDecimal("100.00");
    @Autowired
    JdbcClient jdbcClient;
    @Autowired
    PledgeJdbcClientRepository repository;

    @BeforeEach
    void setup() {
        jdbcClient.sql("call set_known_good_state();").update();
    }

    @Test
    void shouldFindById() {
        Campaign campaign = makeCampaign(1);
        Pledge expected = new Pledge(2, ONE_HUNDRED, campaign, makeBacker(2));
        Pledge actual = repository.findById(2);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindById() {
        assertNull(repository.findById(MISSING_ID));
    }

    @Test
    void shouldFindByCampaignId_1() {

        Campaign campaign = makeCampaign(1);

        List<Pledge> expected = List.of(
                new Pledge(1, ONE_HUNDRED, campaign, makeBacker(1)),
                new Pledge(2, ONE_HUNDRED, campaign, makeBacker(2))
        );
        List<Pledge> actual = repository.findByCampaignId(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByCampaignId_2() {
        List<Pledge> expected = List.of(
                new Pledge(3, ONE_HUNDRED, makeCampaign(2), makeBacker(1))
        );
        List<Pledge> actual = repository.findByCampaignId(2);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindByCampaignId_3() {
        List<Pledge> expected = List.of();
        List<Pledge> actual = repository.findByCampaignId(3);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByBackerId_1() {
        List<Pledge> expected = List.of(
                new Pledge(1, ONE_HUNDRED, makeCampaign(1), makeBacker(1)),
                new Pledge(3, ONE_HUNDRED, makeCampaign(2), makeBacker(1))
        );
        List<Pledge> actual = repository.findByBackerId(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByBackerId_2() {
        List<Pledge> expected = List.of(
                new Pledge(2, ONE_HUNDRED, makeCampaign(1), makeBacker(2))
        );
        List<Pledge> actual = repository.findByBackerId(2);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindByBackerId_3() {
        List<Pledge> expected = List.of();
        List<Pledge> actual = repository.findByBackerId(3);
        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd() {
        Pledge arg = new Pledge(0, ONE_HUNDRED, makeCampaign(2), makeBacker(2));
        Pledge expected = new Pledge(4, ONE_HUNDRED, makeCampaign(2), makeBacker(2));
        Pledge actual = repository.add(arg);
        assertEquals(expected, actual);

        actual = repository.findById(4);
        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdate() {
        Pledge expected = new Pledge(2,
                ONE_HUNDRED.add(BigDecimal.TEN), makeCampaign(1), makeBacker(2));
        assertTrue(repository.update(expected));
        Pledge actual = repository.findById(2);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdate() {
        Pledge arg = new Pledge(MISSING_ID, ONE_HUNDRED, makeCampaign(1), makeBacker(1));
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