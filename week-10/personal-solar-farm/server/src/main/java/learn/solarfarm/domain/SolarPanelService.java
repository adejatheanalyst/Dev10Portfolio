package learn.solarfarm.domain;

import learn.solarfarm.data.SolarPanelRepository;
import learn.solarfarm.data.UserRepository;
import learn.solarfarm.models.SolarPanel;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
public class SolarPanelService {
    public final static int MAX_ROW_COLUMN = 250;

    private final SolarPanelRepository repository;
    private  final UserRepository userRepository;

    public SolarPanelService(SolarPanelRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public static int getMaxInstallationYear() {
        return Year.now().getValue();
    }

    public List<SolarPanel> findAll() {
        return repository.findAll();
    }

    public SolarPanelResult findById(int id) {
        SolarPanelResult result = new SolarPanelResult();
        SolarPanel panel = repository.findById(id);
        if (panel == null) {
            result.setNotFound();
        } else {
            result.setSolarPanel(panel);
        }
        return result;
    }

    public List<SolarPanel> findBySection(String section) {
        return repository.findBySection(section);
    }

    public SolarPanel findByKey(String section, int row, int column) {
        return repository.findByKey(section, row, column);
    }
    public List<SolarPanel> findByUserId(int userId) {
        return repository.findByUserId(userId);
    }

    public SolarPanelResult create(SolarPanel solarPanel) {
        SolarPanelResult result = validate(solarPanel);

        if (solarPanel != null && solarPanel.getId() > 0) {
            result.addErrorMessage("SolarPanel `id` should not be set.");
        }
        if(userRepository.findById(solarPanel.getUserId()) == null) {
            result.addErrorMessage("User does not exist.");
        }
        if (result.isSuccess()) {
            solarPanel = repository.create(solarPanel);
            result.setSolarPanel(solarPanel);
        }

        return result;
    }

    public SolarPanelResult update(SolarPanel solarPanel) {
        SolarPanelResult result = validate(solarPanel);

        if (solarPanel != null && solarPanel.getId() <= 0) {
            result.addErrorMessage("SolarPanel `id` should be set.");
        }

        if (result.isSuccess()) {
            boolean success = repository.update(solarPanel);
            // If the solar panel couldn't be found and updated:
            if (!success) {
                result.setNotFound();
            }
        }

        return result;
    }

    public SolarPanelResult deleteById(int id) {
        SolarPanelResult result = new SolarPanelResult();
        boolean success = repository.deleteById(id);
        if (!success) {
            result.addErrorMessage(String.format("Could not delete panel id %s.", id));
            result.setResultType(ResultType.NOT_FOUND);
        }
        return result;
    }

    private SolarPanelResult validate(SolarPanel solarPanel) {
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
