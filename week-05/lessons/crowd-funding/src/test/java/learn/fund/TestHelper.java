package learn.fund;

import learn.fund.domain.Result;
import learn.fund.models.Backer;
import learn.fund.models.Campaign;
import learn.fund.models.Pledge;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestHelper {

    public static Campaign makeCampaign(int campaignId) {

        LocalDate now = LocalDate.now();
        LocalDate startDate = now.plusDays(-30);
        LocalDate endDate = now.plusDays(30);

        if (campaignId == 1) {
            startDate = now.plusDays(-60);
            endDate = now.plusDays(-30);
        }

        return new Campaign(
                campaignId,
                String.format("Campaign #%s", campaignId),
                String.format("Description #%s", campaignId),
                startDate,
                endDate,
                new BigDecimal("30000.00")
        );
    }

    public static Backer makeBacker(int backerId) {
        return new Backer(
                backerId,
                String.format("Backer #%s", backerId),
                String.format("backer%s@example.com", backerId)
        );
    }

    public static Pledge makePledge() {
        return new Pledge(1, new BigDecimal("100.00"), makeCampaign(2), makeBacker(1));
    }

    public static <T> Result<T> makeResult(String message, T payload) {
        Result<T> result = new Result<>();
        if (message != null) {
            result.addErrorMessage(message);
        }
        if (payload != null) {
            result.setPayload(payload);
        }
        return result;
    }
}
