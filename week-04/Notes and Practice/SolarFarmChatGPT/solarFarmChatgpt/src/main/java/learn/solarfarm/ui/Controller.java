package learn.solarfarm.ui;
import learn.solarfarm.data.DataAccessException;
import learn.solarfarm.domain.SolarPanelService;
import learn.solarfarm.models.Material;
import learn.solarfarm.models.SolarPanel;
public class Controller {
    private final SolarPanelService service;
    private final View view;

    public Controller(SolarPanelService service, View view) {
        this.service = service;
        this.view = view;
    }

    public void run() throws DataAccessException {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = view.readInt("Choose an option: ");
            switch (choice) {
                case 1 -> viewPanels();
                case 2 -> addPanel();
                case 3 -> findBySection();
                case 4 -> updatePanel();
                case 5 -> deletePanel();
                case 0 -> running = false;
                default -> view.println("Invalid choice.");
            }
        }
    }

    private void printMenu() throws DataAccessException {
        view.println("1. View Solar Panels");
        view.println("2. Add Solar Panel");
        view.println("3. Find by Section");
        view.println("4. Update Solar Panel");
        view.println("5. Delete Solar Panel by ID");
        view.println("0. Exit");
    }

    private void viewPanels() throws DataAccessException {
        var result = service.findAll();
        if (result.isSuccess()) {
            for (SolarPanel panel : result.getPanels()) {
                view.println(panel.toString());
            }
        } else {
            view.printError(result.getMessage());
        }
    }

    private void addPanel() throws DataAccessException {
        String section = view.readString("Enter section: ");
        int row = view.readInt("Enter row: ");
        int column = view.readInt("Enter column: ");
        int year = view.readInt("Enter year installed: ");
        Material material = view.selectMaterial();
        boolean tracking = view.readBoolean("Is it tracking?");
        try {
            SolarPanel panel = new SolarPanel(0, section, row, column, year,
                    material, tracking);
            var result = service.add(panel);
            if (result.isSuccess()) {
                view.println(result.getMessage());
            } else {
                view.printError(result.getMessage());
            }
        } catch (IllegalArgumentException e) {
            view.printError("Invalid material type. Please try again.");
        }
    }
    private void findBySection() throws DataAccessException{
        view.println("Available sections:");
        for (String section : service.getAvailableSections()) {
            view.println(" - " + section);
        }
        String section = view.readString("Enter section: ");
        var result = service.findBySection(section);
        if (result.isSuccess()) {
            for (SolarPanel panel : result.getPanels()) {
                view.println(panel.toString());
            }
        } else {
            view.printError(result.getMessage());
        }
    }


    private void deletePanel() throws DataAccessException {
        int id = view.readInt("Enter the ID of the solar panel to delete: ");
        var result = service.deleteById(id);
        if (result.isSuccess()) {
            view.println(result.getMessage());
        } else {
            view.printError(result.getMessage());
        }
    }
    private void updatePanel() throws DataAccessException{
        String section = view.readString("Enter section: ");
        int row = view.readInt("Enter row: ");
        int column = view.readInt("Enter column: ");
        var result = service.findByKey(section, row, column);


        if (result.isSuccess()) {
            SolarPanel panel = result.getSolarPanel();
            view.println("Current panel: " + panel);
            String newSection = view.readString("Enter new section (" + panel.getSection() + "): ");
            int newRow = view.readInt("Enter new row (" + panel.getRow() + "): ");
            int newColumn = view.readInt("Enter new column (" + panel.getColumn() + "): ");
            int newYear = view.readInt("Enter year installed (" + panel.getYearInstalled() + "): ");
            Material newMaterial = view.selectMaterial();
            boolean newTracking = view.readBoolean("Is it tracking (" + panel.isTracking() + ")?");

            panel.setSection(newSection.isEmpty() ? panel.getSection() : newSection);
            panel.setRow(newRow > 0 ? newRow : panel.getRow());
            panel.setColumn(newColumn > 0 ? newColumn : panel.getColumn());
            panel.setYearInstalled(newYear > 0 ? newYear : panel.getYearInstalled());
            panel.setMaterial(newMaterial);
            panel.setTracking(newTracking);

            var updateResult = service.update(panel);
            if (updateResult.isSuccess()) {
                view.println(updateResult.getMessage());
            } else {
                view.printError(updateResult.getMessage());
            }
        } else {
            view.printError(result.getMessage());
        }
    }




}
