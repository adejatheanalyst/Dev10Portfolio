package learn.solarfarm.domain;
import learn.solarfarm.data.DataAccessException;
import learn.solarfarm.data.SolarPanelRepository;
import learn.solarfarm.models.SolarPanel;
import learn.solarfarm.models.Material;

import java.util.ArrayList;
import java.util.List;

public class SolarPanelService {
    private final SolarPanelRepository repository;

    public SolarPanelService(SolarPanelRepository repository) {
        this.repository = repository;
    }

    public SolarPanelResult add(SolarPanel panel)  throws DataAccessException{
        List<String> errors = validate(panel);
        if (!errors.isEmpty()) {
            return new SolarPanelResult(false, String.join(", ", errors), (SolarPanel) null);
        }
        if (repository.create(panel)) {
            return new SolarPanelResult(true, "Solar panel added successfully.", panel);
        }
        return new SolarPanelResult(false, "Failed to add solar panel.", (SolarPanel) null);
    }
    public SolarPanelResult update(SolarPanel panel) throws DataAccessException {
        List<String> errors = validate(panel);
        if (!errors.isEmpty()) {
            return new SolarPanelResult(false, String.join(", ", errors), (SolarPanel) null);
        }
        if (repository.update(panel)) {
            return new SolarPanelResult(true, "Solar panel updated successfully.", panel);
        }
        return new SolarPanelResult(false, "Solar panel update failed.", (SolarPanel) null);
    }

    public SolarPanelResult findBySection(String section) throws DataAccessException {
        List<SolarPanel> panels = repository.findBySection(section);
        if (!panels.isEmpty()) {
            return new SolarPanelResult(true, "Solar panels found.", panels);
        }
        return new SolarPanelResult(false, "No solar panels found in the specified section.", (SolarPanel) null);
    }
    public SolarPanelResult findByKey(String section, int row, int column) throws DataAccessException {
        SolarPanel panel = repository.findByKey(section, row, column);
        if (panel != null) {
            return new SolarPanelResult(true, "Solar panel found.", panel);
        }
        return new SolarPanelResult(false, "No solar panel found with the specified key.", (SolarPanel) null);
    }

    public SolarPanelResult findAll() throws DataAccessException {
        return new SolarPanelResult(true, "All solar panels fetched.", repository.findAll());
    }
    public List<String> getAvailableSections() {
        List<SolarPanel> allPanels = repository.findAll();
        List<String> sections = new ArrayList<>();
        for (SolarPanel panel : allPanels) {
            if (!sections.contains(panel.getSection())) {
                sections.add(panel.getSection());
            }
        }
        return sections;
    }

    public SolarPanelResult deleteById(int id) throws DataAccessException {
        boolean success = repository.deleteById(id);
        if (success) {
            return new SolarPanelResult(true, "Solar panel deleted successfully.", (SolarPanel) null);
        }
        return new SolarPanelResult(false, "Failed to delete solar panel. ID not found.", (SolarPanel) null);
    }



    public List<String> validate(SolarPanel panel) throws DataAccessException {
        List<String> errors = new ArrayList<>();
        if (panel.getSection() == null || panel.getSection().trim().isEmpty()) {
            errors.add("Section is required.");
        }
        if (panel.getRow() < 1) {
            errors.add("Row must be 1 or greater.");
        }
        if (panel.getColumn() < 1) {
            errors.add("Column must be 1 or greater.");
        }
        if (panel.getYearInstalled() < 2000 || panel.getYearInstalled() > 2025) {
            errors.add("Year installed must be between 2000 and 2025.");
        }
        if (panel.getMaterial() == null) {
            errors.add("Material is required.");
        }
        return errors;
    }
}
