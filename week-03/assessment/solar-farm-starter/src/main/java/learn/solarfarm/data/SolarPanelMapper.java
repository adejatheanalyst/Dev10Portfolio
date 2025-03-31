package learn.solarfarm.data;

import learn.solarfarm.models.Material;
import learn.solarfarm.models.SolarPanel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SolarPanelMapper implements RowMapper<SolarPanel> {

    @Override
    public SolarPanel mapRow(ResultSet rs, int rowNum) throws SQLException {
        String materialName = rs.getString("material");
        Material material = Material.fromValue(materialName);

        SolarPanel solarPanel = new SolarPanel();

        solarPanel.setId(rs.getInt("id"));
        solarPanel.setSection(rs.getString("section"));
        solarPanel.setRow(rs.getInt("row"));
        solarPanel.setColumn(rs.getInt("column"));
        solarPanel.setYearInstalled(rs.getInt("yearInstalled"));
        solarPanel.setMaterial(material);
        solarPanel.setTracking(rs.getBoolean("isTracking"));

        return solarPanel;
    }

    }

