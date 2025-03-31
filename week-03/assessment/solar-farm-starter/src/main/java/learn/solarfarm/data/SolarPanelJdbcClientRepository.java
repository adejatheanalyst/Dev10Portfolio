package learn.solarfarm.data;

import learn.solarfarm.models.SolarPanel;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
@Repository
public class SolarPanelJdbcClientRepository implements SolarPanelRepository {
    private final JdbcClient jdbcClient;
public String  findAll = "select id, section, `row`, `column`, yearInstalled, material, isTracking from solar_panels";
    public SolarPanelJdbcClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<SolarPanel> findAll() throws DataAccessException {
        final String sql = findAll;


        return jdbcClient.sql(sql)
                .query(new SolarPanelMapper())
                .list();
    }

    @Override
    public List<SolarPanel> findBySection(String section) throws DataAccessException {
        final String sql = findAll + " where section = ?;";

        return jdbcClient.sql(sql)
                .param(section)
                .query(new SolarPanelMapper())
                .list();
    }
    @Override
    public SolarPanel findByKey(String section, int row, int column) throws DataAccessException {
        final String sql = findAll + " where section = :section and `row` = :row and `column` = :column;";

        return jdbcClient.sql(sql)
                .param("section",section)
                .param("row",row)
                .param("column",column)
                .query(new SolarPanelMapper())
                .optional()
                .orElse(null);
    }
    @Override
    public SolarPanel create(SolarPanel solarPanel) throws DataAccessException {
        final String sql = """
                insert into solar_panels (section, `row`, `column`, yearInstalled, material, isTracking)
                values (?, ?, ?, ?, ?, ?);
                """;

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcClient.sql(sql)
                .param(solarPanel.getSection())
                .param(solarPanel.getRow())
                .param(solarPanel.getColumn())
                .param(solarPanel.getYearInstalled())
                .param(solarPanel.getMaterial().getName())
                .param(solarPanel.isTracking())
                .update(keyHolder, "solar_panel_id");

        solarPanel.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        return solarPanel;
    }

    @Override
    public boolean update(SolarPanel solarPanel) throws DataAccessException {
        final String sql = """
                update solar_panels
                set section = :section, `row` = :row, `column` = :column, yearInstalled = :yearInstalled, material = :material, isTracking = :isTracking
                where id = :id;
                """;

        int rowAffected = jdbcClient.sql(sql)
                .param("section",solarPanel.getSection())
                .param("row",solarPanel.getRow())
                .param("column",solarPanel.getColumn())
                .param("yearInstalled",solarPanel.getYearInstalled())
                .param("material", solarPanel.getMaterial().getName())
                .param("isTracking",solarPanel.isTracking())
                .param("id", solarPanel.getId())
                .update();
        return rowAffected == 1;
    }


    @Override
    public boolean delete(SolarPanel solarPanel) throws DataAccessException {
        final String sql = """
                delete from solar_panels
                where section = :section and `row` = :row and `column` = :column;
                """;
        int rowsAffected = jdbcClient.sql(sql)
                .param("section", solarPanel.getSection())
                .param("row", solarPanel.getRow())
                .param("column", solarPanel.getColumn())
                .update();
        return rowsAffected == 1;
}

}
