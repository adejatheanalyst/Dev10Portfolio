package learn.solarfarm.domain;

import learn.solarfarm.data.DataAccessException;
import learn.solarfarm.data.SolarPanelRepository;
import learn.solarfarm.models.SolarPanel;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
public class SolarPanelService {
    public final static int MAX_ROW_COLUMN = 250;

    private final SolarPanelRepository repository;

    public SolarPanelService(SolarPanelRepository repository) {
        this.repository = repository;
    }

    public static int getMaxInstallationYear() {
        return Year.now().getValue();
    }

    public List<SolarPanel> findAll() throws DataAccessException {
        return repository.findAll();
    }

    public List<SolarPanel> findBySection(String section) throws DataAccessException {
        return repository.findBySection(section);
    }

    public SolarPanel findById(int id) throws DataAccessException {
        return repository.findById(id);
    }

    public SolarPanelResult create(SolarPanel solarPanel) throws DataAccessException {
        SolarPanelResult result = validate(solarPanel);

        if (solarPanel != null && solarPanel.getId() > 0) {
            result.addErrorMessage("SolarPanel `id` should not be set.", ResultType.INVALID);
        }

        if (result.isSuccess()) {
            solarPanel = repository.create(solarPanel);
            result.setSolarPanel(solarPanel);
        }

        return result;
    }

    public SolarPanelResult update(SolarPanel solarPanel) throws DataAccessException {
        SolarPanelResult result = validate(solarPanel);

        if (solarPanel.getId() <= 0) {
            result.addErrorMessage("SolarPanel `id` is required.", ResultType.INVALID);
        }

        if (result.isSuccess()) {
            if (repository.update(solarPanel)) {
                result.setSolarPanel(solarPanel);
            } else {
                result.addErrorMessage("SolarPanel id %s was not found.", ResultType.NOT_FOUND, solarPanel.getId());
            }
        }
        return result;
    }

    public SolarPanelResult deleteById(int id) throws DataAccessException {
        SolarPanelResult result = new SolarPanelResult();
        if (!repository.deleteById(id)) {
            result.addErrorMessage("SolarPanel id %s was not found.", ResultType.NOT_FOUND, id);
        }
        return result;
    }

    private SolarPanelResult validate(SolarPanel solarPanel) throws DataAccessException {
        SolarPanelResult result = new SolarPanelResult();

        if (solarPanel == null) {
            result.addErrorMessage("SolarPanel cannot be null.", ResultType.INVALID);
            return result;
        }

        if (solarPanel.getSection() == null || solarPanel.getSection().isBlank()) {
            result.addErrorMessage("SolarPanel `section` is required.", ResultType.INVALID);
        }

        if (solarPanel.getRow() < 1 || solarPanel.getRow() >= MAX_ROW_COLUMN) {
            result.addErrorMessage("SolarPanel `row` must be a positive number less than or equal to %s.",
                    ResultType.INVALID, MAX_ROW_COLUMN);
        }

        if (solarPanel.getColumn() < 1 || solarPanel.getColumn() >= MAX_ROW_COLUMN) {
            result.addErrorMessage("SolarPanel `column` must be a positive number less than or equal to %s.",
                    ResultType.INVALID, MAX_ROW_COLUMN);
        }

        if (solarPanel.getYearInstalled() > getMaxInstallationYear()) {
            result.addErrorMessage("SolarPanel `yearInstalled` must be in the past.", ResultType.INVALID);
        }

        if (solarPanel.getMaterial() == null) {
            result.addErrorMessage("SolarPanel `material` is required.", ResultType.INVALID);
        }

        // If everything is successful so far, then check if the combined values
        // of **Section**, **Row**, and **Column** are unique (i.e. the natural key).
        if (result.isSuccess()) {
            List<SolarPanel> existingSolarPanels = repository.findBySection(solarPanel.getSection());

            for (SolarPanel existingSolarPanel : existingSolarPanels) {
                // If an existing panel was found for the provided **Section**, **Row**, and **Column** values
                // add an error message if the id values don't match (i.e. they're not the same record).
                if (existingSolarPanel.getId() != solarPanel.getId() &&
                        existingSolarPanel.getSection().equalsIgnoreCase(solarPanel.getSection()) &&
                        existingSolarPanel.getRow() == solarPanel.getRow() &&
                        existingSolarPanel.getColumn() == solarPanel.getColumn()) {
                    result.addErrorMessage("SolarPanel `section`, `row`, and `column` must be unique.",
                            ResultType.INVALID);
                }
            }
        }

        return result;
    }
}
