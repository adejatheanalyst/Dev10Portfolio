package learn.fund.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Campaign {
    private int campaignId;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal fundingGoal;

    public Campaign() {
    }

    public Campaign(int campaignId, String name, String description, LocalDate startDate, LocalDate endDate, BigDecimal fundingGoal) {
        this.campaignId = campaignId;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fundingGoal = fundingGoal;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getFundingGoal() {
        return fundingGoal;
    }

    public void setFundingGoal(BigDecimal fundingGoal) {
        this.fundingGoal = fundingGoal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Campaign campaign = (Campaign) o;

        if (campaignId != campaign.campaignId) return false;
        if (!Objects.equals(name, campaign.name)) return false;
        if (!Objects.equals(description, campaign.description))
            return false;
        if (!Objects.equals(startDate, campaign.startDate)) return false;
        if (!Objects.equals(endDate, campaign.endDate)) return false;
        return Objects.equals(fundingGoal, campaign.fundingGoal);
    }

    @Override
    public int hashCode() {
        int result = campaignId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (fundingGoal != null ? fundingGoal.hashCode() : 0);
        return result;
    }
}
