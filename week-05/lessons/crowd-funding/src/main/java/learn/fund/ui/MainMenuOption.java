package learn.fund.ui;

public enum MainMenuOption {
    EXIT("Exit"),
    ADD_BACKER("Add a Backer"),
    ADD_CAMPAIGN("Add a Campaign"),
    VIEW_CAMPAIGNS("View Campaigns"),
    ADD_PLEDGE("Add a Pledge"),
    VIEW_CAMPAIGN_PLEDGES("View Pledges by Campaign"),
    VIEW_BACKER_PLEDGES("View Pledges by Backer");

    private String label;

    public String getLabel() {
        return label;
    }

    MainMenuOption(String label) {
        this.label = label;
    }
}
