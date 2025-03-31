package learn.fund.ui;

import learn.fund.domain.Result;
import learn.fund.models.Backer;
import learn.fund.models.Campaign;
import learn.fund.models.Pledge;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;

public class View {
    private final ConsoleIO io = new ConsoleIO();

    public MainMenuOption selectMainMenuOption() {
        io.printHeader("Main Menu");
        MainMenuOption[] values = MainMenuOption.values();
        for (int i = 0; i < values.length; i++) {
            io.printf("%s. %s%n", i, values[i].getLabel());
        }
        String msg = String.format("Select [0-%s]: ", values.length - 1);
        int index = io.readInt(msg, 0, values.length - 1);
        io.println();
        return values[index];
    }

    public void displayMessage(String message) {
        io.println(message);
    }

    public void displayHeader(String message) {
        io.printHeader(message);
    }

    public void displayResult(Result<?> result) {
        if (!result.isSuccess()) {
            io.println("[ERRORS]");
            for (String message : result.getErrorMessages()) {
                io.printf("- %s.%n", message);
            }
        } else {
            io.println("[SUCCESS!]");
        }
        io.readString("Press [Enter] to continue.");
    }

    public void displayException(Exception ex) {
        io.println("[FATAL ERR]");
        io.println(ex.getMessage());
        io.println();
    }

    public void displayPledges(List<Pledge> pledges) {
        if (pledges.size() == 0) {
            io.println("No pledges found.");
            return;
        }

        TerminalTable tt = new TerminalTable();
        tt.addHeader("Campaign", "Backer", "Amount");
        for (Pledge p : pledges) {
            tt.addRow(
                    p.getCampaign().getName(),
                    p.getBacker().getName(),
                    NumberFormat.getCurrencyInstance().format(p.getAmount())
            );
        }
        io.println(tt.render());
        io.readString("Press [Enter] to continue.");
    }

    public void displayCampaigns(List<Campaign> campaigns) {
        if (campaigns == null || campaigns.size() == 0) {
            io.println("No campaigns found.");
            return;
        }

        TerminalTable tt = new TerminalTable();
        tt.addHeader("Campaign", "Start", "End", "Goal");

        for (Campaign c : campaigns) {
            tt.addRow(
                    c.getName(),
                    io.dateFormatter.format(c.getStartDate()),
                    io.dateFormatter.format(c.getEndDate()),
                    NumberFormat.getCurrencyInstance().format(c.getFundingGoal()));
        }

        io.println(tt.render());
        io.readString("Press [Enter] to continue.");
    }

    public String readSnippet() {
        return io.readRequiredString("Backer name contains: ");
    }

    public LocalDate readLocalDate(String message) {
        return io.readLocalDate(message);
    }

    public Backer buildBacker() {
        displayHeader(MainMenuOption.ADD_BACKER.getLabel());
        Backer backer = new Backer();
        backer.setName(io.readRequiredString("Name: "));
        backer.setEmailAddress(io.readRequiredString("Email: "));
        return backer;
    }

    public Campaign buildCampaign() {
        displayHeader(MainMenuOption.ADD_CAMPAIGN.getLabel());
        Campaign campaign = new Campaign();
        campaign.setName(io.readRequiredString("Name: "));
        campaign.setDescription(io.readRequiredString("Description: "));
        campaign.setStartDate(io.readLocalDate("Start Date [MM/dd/yyyy]: "));
        campaign.setEndDate(io.readLocalDate("End Date [MM/dd/yyyy]: "));
        campaign.setFundingGoal(io.readBigDecimal("Funding Goal: "));
        return campaign;
    }

    public Pledge buildPledge(Campaign campaign, Backer backer) {

        Pledge pledge = new Pledge();
        pledge.setCampaign(campaign);
        pledge.setBacker(backer);

        io.println("Pledge");
        io.printf("Campaign: %s%n", campaign.getName());
        io.printf("Backer: %s%n", backer.getName());
        pledge.setAmount(io.readBigDecimal("Amount: ", BigDecimal.ONE, campaign.getFundingGoal()));

        return pledge;
    }

    public Campaign selectCampaign(List<Campaign> campaigns) {

        if (campaigns.size() == 0) {
            io.println("[ERR] No campaigns found.");
            return null;
        }

        TerminalTable tt = new TerminalTable();
        tt.addHeader("#", "Campaign", "Start", "End", "Goal");
        tt.addRow("0", "Cancel");

        for (int i = 0; i < campaigns.size(); i++) {
            Campaign c = campaigns.get(i);
            tt.addRow(
                    Integer.toString(i + 1),
                    c.getName(),
                    io.dateFormatter.format(c.getStartDate()),
                    io.dateFormatter.format(c.getEndDate()),
                    NumberFormat.getCurrencyInstance().format(c.getFundingGoal()));
        }
        io.print(tt.render());

        String msg = String.format("Select [1-%s, 0 Cancels]: ", campaigns.size());
        int index = io.readInt(msg, 0, campaigns.size());
        if (index == 0) {
            io.println("Nothing selected.");
            return null;
        }
        Campaign selected = campaigns.get(index - 1);
        io.printf("Name: %s%n", selected.getName());
        io.printf("Description: %s%n", selected.getDescription());
        return selected;
    }

    public Backer selectBacker(List<Backer> backers) {

        if (backers.size() == 0) {
            io.println("[ERR] No backers found.");
            return null;
        }

        TerminalTable tt = new TerminalTable();
        tt.addHeader("#", "Name", "Email");
        tt.addRow("0", "Cancel");

        for (int i = 0; i < backers.size(); i++) {
            Backer b = backers.get(i);
            tt.addRow(
                    Integer.toString(i + 1),
                    b.getName(),
                    b.getEmailAddress());
        }

        io.print(tt.render());

        String msg = String.format("Select [1-%s, 0 Cancels]: ", backers.size());
        int index = io.readInt(msg, 0, backers.size());
        if (index == 0) {
            io.println("Nothing selected.");
            return null;
        }
        Backer selected = backers.get(index - 1);
        io.printf("Name: %s%n", selected.getName());
        return selected;
    }


}
