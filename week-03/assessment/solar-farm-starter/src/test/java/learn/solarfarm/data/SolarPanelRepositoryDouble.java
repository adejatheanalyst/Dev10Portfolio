package learn.solarfarm.data;

import learn.solarfarm.models.Material;
import learn.solarfarm.models.SolarPanel;

import java.util.ArrayList;
import java.util.List;

public class SolarPanelRepositoryDouble implements SolarPanelRepository {
    private final ArrayList<SolarPanel> solarPanels = new ArrayList<>();

    public SolarPanelRepositoryDouble() {
        solarPanels.add(new SolarPanel(1, "Section One", 1, 1, 2020, Material.POLY_SI, true));
        solarPanels.add(new SolarPanel(2, "Section One", 1, 2, 2020, Material.POLY_SI, true));
        solarPanels.add(new SolarPanel(3, "Section Two", 10, 11, 2000, Material.A_SI, false));
    }

    @Override
    public List<SolarPanel> findBySection(String section) throws DataAccessException {
        ArrayList<SolarPanel> result = new ArrayList<>();
        for (SolarPanel sp : solarPanels) {
            if (sp.getSection().equalsIgnoreCase(section)) {
                result.add(sp);
            }
        }
        return result;
    }

    @Override
    public SolarPanel findByKey(String section, int row, int column) throws DataAccessException {
        for (SolarPanel sp : solarPanels) {
            if (sp.isMatch(section, row, column)) {
                return sp;
            }
        }
        return null;
    }

    @Override
    public SolarPanel create(SolarPanel solarPanel) throws DataAccessException {
        return solarPanel;
    }

    // TODO: add an update method (must match with interface)
    public boolean update(SolarPanel solarPanel) throws DataAccessException {
        for (SolarPanel current : findAll()){
            if (current.getId() == solarPanel.getId()) {
    return true;
            }
        }return false;
    }
    // TODO: add a delete method (must match with interface)
    @Override
    public  boolean delete(SolarPanel solarPanel) throws DataAccessException {
        for(SolarPanel current: findAll()){
            if (current.getId() == (solarPanel.getId())){
                return true;
            }
        }
        return false;
    }

    public ArrayList<SolarPanel> findAll() throws DataAccessException{
        return solarPanels;
    }
}
