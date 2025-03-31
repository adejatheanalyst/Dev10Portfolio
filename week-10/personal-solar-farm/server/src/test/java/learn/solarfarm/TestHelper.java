package learn.solarfarm;

import learn.solarfarm.domain.SolarPanelResult;
import learn.solarfarm.models.Material;
import learn.solarfarm.models.SolarPanel;
import learn.solarfarm.models.User;

public class TestHelper {

    public static final int NEXT_ID = 6;
    public static final int MISSING_ID = 25;


    public static SolarPanel makePanel(int id) {
        SolarPanel panel = new SolarPanel();
        panel.setId(id);
        if (id <= 3) {
            panel.setSection("red");
            panel.setRow(1);
            panel.setColumn(id);
        } else {
            panel.setSection("blue");
            panel.setRow(id);
            panel.setColumn(1);
        }
        panel.setYearInstalled(2020);
        panel.setMaterial(Material.MONO_SI);
        panel.setTracking(true);
        panel.setUserId(1);
        return panel;
    }

    public static SolarPanelResult makeResult(String message, SolarPanel panel) {
        SolarPanelResult result = new SolarPanelResult();
        if (message != null) {
            result.addErrorMessage(message);
        } else if (panel != null) {
            result.setSolarPanel(panel);
        }
        return result;
    }
    public static User makeUser() {
       return new User(1, "testUsername", "testPassword");
    }
}
