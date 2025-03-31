package learn.solarfarm.domain;

import learn.solarfarm.models.SolarPanel;
import java.util.List;

public class SolarPanelResult {
    private final boolean success;
    private final String message;
    private final SolarPanel solarPanel;
    private final List<SolarPanel> panels;

    public SolarPanelResult(boolean success, String message, SolarPanel solarPanel) {
        this.success = success;
        this.message = message;
        this.solarPanel = solarPanel;
        this.panels = null;
    }

    public SolarPanelResult(boolean success, String message, List<SolarPanel> panels) {
        this.success = success;
        this.message = message;
        this.panels = panels;
        this.solarPanel = null;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public SolarPanel getSolarPanel() {
        return solarPanel;
    }

    public List<SolarPanel> getPanels() {
        return panels;
    }
}

