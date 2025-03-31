package learn.fund.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Pledge {
    private int pledgeId;
    private BigDecimal amount;
    private Campaign campaign;
    private Backer backer;

    public Pledge() {
    }

    public Pledge(int pledgeId, BigDecimal amount, Campaign campaign, Backer backer) {
        this.pledgeId = pledgeId;
        this.amount = amount;
        this.campaign = campaign;
        this.backer = backer;
    }

    public int getPledgeId() {
        return pledgeId;
    }

    public void setPledgeId(int pledgeId) {
        this.pledgeId = pledgeId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public Backer getBacker() {
        return backer;
    }

    public void setBacker(Backer backer) {
        this.backer = backer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pledge pledge = (Pledge) o;

        if (pledgeId != pledge.pledgeId) return false;
        if (!Objects.equals(amount, pledge.amount)) return false;
        if (!Objects.equals(campaign, pledge.campaign)) return false;
        return Objects.equals(backer, pledge.backer);
    }

    @Override
    public int hashCode() {
        int result = pledgeId;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (campaign != null ? campaign.hashCode() : 0);
        result = 31 * result + (backer != null ? backer.hashCode() : 0);
        return result;
    }
}
