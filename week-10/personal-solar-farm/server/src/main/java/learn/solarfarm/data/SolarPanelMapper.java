package learn.solarfarm.data;

import learn.solarfarm.models.Material;
import learn.solarfarm.models.SolarPanel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SolarPanelMapper implements RowMapper<SolarPanel> {
    @Override
    public SolarPanel mapRow(ResultSet rs, int rowNum) throws SQLException {
        SolarPanel panel = new SolarPanel();
        panel.setId(rs.getInt("panel_id"));
        panel.setSection(rs.getString("section"));
        panel.setRow(rs.getInt("row"));
        panel.setColumn(rs.getInt("column"));
        panel.setYearInstalled(rs.getInt("year_installed"));
        panel.setMaterial(Material.valueOf(rs.getString("material")));
        panel.setTracking(rs.getBoolean("is_tracking"));
        panel.setUserId(rs.getInt("user_id"));
        return panel;
    }
}
