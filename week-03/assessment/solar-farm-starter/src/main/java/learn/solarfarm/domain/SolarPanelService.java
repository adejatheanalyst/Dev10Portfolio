package learn.solarfarm.domain;

import learn.solarfarm.data.DataAccessException;
import learn.solarfarm.data.SolarPanelRepository;
import learn.solarfarm.models.Material;
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
    public List<SolarPanel> findAll() throws DataAccessException {
        return repository.findAll();
    }

    public static int getMaxInstallationYear() {
        return Year.now().getValue();
    }

    public List<SolarPanel> findBySection(String section) throws DataAccessException {
        return repository.findBySection(section);
    }

    public SolarPanel findByKey(String section, int row, int column) throws DataAccessException {
        return repository.findByKey(section, row, column);
    }

    public SolarPanelResult create(SolarPanel solarPanel) throws DataAccessException {
        SolarPanelResult result = validate(solarPanel);

        if (solarPanel != null && solarPanel.getId() > 0) {
            result.addErrorMessage("SolarPanel `id` should not be set.");
        }

        if (result.isSuccess()) {
            solarPanel = repository.create(solarPanel);
            result.setSolarPanel(solarPanel);
        }

        return result;
    }

    // TODO: add an update method
    public SolarPanelResult update(SolarPanel solarPanel) throws DataAccessException {

        SolarPanelResult result = validate(solarPanel);

        if (!result.isSuccess()) {
            return result;
        }
        boolean found = repository.update(solarPanel);
        if (!found) {
            result.addErrorMessage("Solar Panel Not Found");
        } else {
            result.setSolarPanel(solarPanel);
        }
        return result;
    }



        // TODO: add a delete method (possibly deleteById?)
        public SolarPanelResult delete( SolarPanel solarPanel) throws DataAccessException {
            boolean found = repository.delete(solarPanel);

            if (found) {
                SolarPanelResult result = new SolarPanelResult();
                result.setSolarPanel(new SolarPanel());
                return result;
            } else {
                SolarPanelResult result = new SolarPanelResult();
                result.addErrorMessage("Could not find Solar panel to delete.");
                return result;
            }
        }


        private SolarPanelResult validate (SolarPanel solarPanel) throws DataAccessException {
            SolarPanelResult result = new SolarPanelResult();
            if (solarPanel == null) {
                result.addErrorMessage("SolarPanel cannot be null.");
                return result;
            }

            if (solarPanel.getSection() == null || solarPanel.getSection().isBlank()) {
                result.addErrorMessage("SolarPanel `section` is required.");
            }

            if (solarPanel.getRow() < 1 || solarPanel.getRow() >= MAX_ROW_COLUMN) {
                result.addErrorMessage("SolarPanel `row` must be a positive number less than or equal to %s.", MAX_ROW_COLUMN);
            }

            if (solarPanel.getColumn() < 1 || solarPanel.getColumn() >= MAX_ROW_COLUMN) {
                result.addErrorMessage("SolarPanel `column` must be a positive number less than or equal to %s.", MAX_ROW_COLUMN);
            }

            if (solarPanel.getYearInstalled() > getMaxInstallationYear()) {
                result.addErrorMessage("SolarPanel `yearInstalled` must be in the past.");
            }

            if (solarPanel.getMaterial() == null) {
                result.addErrorMessage("SolarPanel `material` is required.");
            }

            // If everything is successful so far, then check if the combined values
            // of **Section**, **Row**, and **Column** are unique (i.e. the natural key).
            if (result.isSuccess()) {
                SolarPanel existingSolarPanel = repository.findByKey(solarPanel.getSection(),
                        solarPanel.getRow(), solarPanel.getColumn());

                // If an existing panel was found for the provided **Section**, **Row**, and **Column** values
                // add an error message if the id values don't match (i.e. they're not the same record).
                if (existingSolarPanel != null && existingSolarPanel.getId() != solarPanel.getId()) {
                    result.addErrorMessage("SolarPanel `section`, `row`, and `column` must be unique.");
                }
            }

            return result;
        }
    }

