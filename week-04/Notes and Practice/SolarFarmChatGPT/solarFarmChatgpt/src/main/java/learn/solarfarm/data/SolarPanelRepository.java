package learn.solarfarm.data;
import learn.solarfarm.models.SolarPanel;
import java.util.List;


public interface SolarPanelRepository {
    List<SolarPanel> findBySection(String section) throws DataAccessException;
    SolarPanel findByKey(String section, int row, int column) throws DataAccessException;
    boolean create(SolarPanel solarPanel) throws DataAccessException;
    boolean update(SolarPanel solarPanel) throws DataAccessException;
    boolean deleteById(int id) throws DataAccessException;
    List<SolarPanel> findAll() throws DataAccessException;
    int getNextId(List<SolarPanel> panels) throws DataAccessException;
}
