package learn.solarfarm.ui;

import learn.solarfarm.data.DataAccessException;
import learn.solarfarm.domain.SolarPanelResult;
import learn.solarfarm.domain.SolarPanelService;

import learn.solarfarm.models.SolarPanel;

import java.util.List;

public class Controller {
    private final View view;
    private final SolarPanelService service;



    public Controller(View view, SolarPanelService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        view.displayHeader("Welcome to Solar Farm");
        try {
            runApp();
        } catch (DataAccessException ex) {
            view.displayErrors(List.of(ex.getMessage()));
        }
        view.displayMessage("Goodbye!");
    }

    private void runApp() throws DataAccessException {
        for (int option = view.chooseMenuOption();
             option > 0;
             option = view.chooseMenuOption()) {

            switch (option) {
                case 1:
                    findSolarPanelsBySection();
                    break;
                case 2:
                    addSolarPanel();
                    break;
                case 3:
                    updateSolarPanel();
                    break;
                case 4:

                    deleteSolarPanel();
                    break;
            }
        }
    }

    private void findSolarPanelsBySection() throws DataAccessException {
        view.displayHeader("Find Panels by Section");
        String section = view.getSection();
        List<SolarPanel> solarPanels = service.findBySection(section);
        if (solarPanels.isEmpty()) {
            view.displayMessage("There are no panels in this section.");
        } else {
            view.displaySolarPanels(section, solarPanels);
        }
    }

    private void addSolarPanel() throws DataAccessException {
        SolarPanel solarPanel = view.addSolarPanel();
        SolarPanelResult result = service.create(solarPanel);
        if (result.isSuccess()) {
            view.displayMessage("[Success]%nPanel %s added.", result.getSolarPanel().getKey());
        } else {
            view.displayErrors(result.getErrorMessages());
        }
    }

    private void updateSolarPanel() throws DataAccessException {
        view.displayHeader("Update a Panel");
        view.displayMessage("Enter your Key");

        String solarPanelUpdate = view.getSection();
        int getRow = view.getRow();
        int getCol = view.getColumn();

        SolarPanel found = service.findByKey(solarPanelUpdate, getRow, getCol);
        if (found == null) {
            view.displayMessage("Solar Panel was not found!");
            return;
        }
        view.displayMessage("Enter details you would like to update.");
        SolarPanel updatePanel = view.addSolarPanel();

        updatePanel.setId(found.getId());

        SolarPanelResult result = service.update(updatePanel);
        if (result.isSuccess()) {
            view.displayMessage("Successfully updated panel %s-%s-%s", solarPanelUpdate, getRow, getCol );
        } else {
            view.displayErrors(result.getErrorMessages());
        }
    }

    private void deleteSolarPanel() throws DataAccessException {
        view.displayHeader("Delete a Panel");
        view.displayMessage("Enter your Key");
        String solarPanelDelete = view.getSection();
        int getRow = view.getRow();
        int getCol = view.getColumn();

        SolarPanel found = service.findByKey(solarPanelDelete, getRow, getCol);
        if (found == null) {
            view.displayMessage("Cannot find Panel %s-%s-%s to delete! ", solarPanelDelete, getRow, getCol);
            return;
        }
//        found.setId(found.getId());
        SolarPanelResult solarDelete = service.delete(found);
        if (solarDelete.isSuccess()) {
            view.displayMessage("Successfully deleted panel %s-%s-%s ! ", solarPanelDelete, getRow, getCol );
        } else {
            view.displayErrors(solarDelete.getErrorMessages());
        }



    }


}



