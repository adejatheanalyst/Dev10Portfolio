package learn.solarfarm.data;

import learn.solarfarm.models.Material;
import learn.solarfarm.models.SolarPanel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class SolarPanelJdbcClientRepository implements SolarPanelRepository {
    private final JdbcClient jdbcClient;

    public SolarPanelJdbcClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<SolarPanel> findAll() throws DataAccessException {

        final String sql = """
                select id, section, `row`, `column`, year_installed, material, is_tracking
                from solar_panel
                order by section, `row`, `column`;
                """;

        return jdbcClient.sql(sql).query(SolarPanel.class).list();
    }

    @Override
    public List<SolarPanel> findBySection(String section) throws DataAccessException {

        // SQL injection
        // '; drop table solar_panel;

//        final String sql = String.format("select id, section, `row`, `column`, year_installed, material, is_tracking " +
//                "from solar_panel " +
//                "where section = '%s' " +
//                "order by section, `row`, `column`;", section);

        final String sql = """
                select id, section, `row`, `column`, year_installed, material, is_tracking
                from solar_panel
                where section = ?
                order by section, `row`, `column`;
                """;
        return jdbcClient.sql(sql)
                .param(section)
                .query(SolarPanel.class)
                .list();
    }

    @Override
    public SolarPanel findById(int id) throws DataAccessException {
        final String sql = """
                select id, section, `row`, `column`, year_installed, material, is_tracking
                from solar_panel
                where id = ?
                """;

//        try {
//            return jdbcTemplate.queryForObject(sql, mapper, id);
//        } catch (EmptyResultDataAccessException ex) {
//            return null;
//        }

        // This approach avoids the exception from being thrown.
        return jdbcClient.sql(sql)
                .param(id)
                .query(SolarPanel.class)
                .optional().orElse(null);
    }

    @Override
    public SolarPanel create(SolarPanel solarPanel) throws DataAccessException {

        final String sql = """
                insert into solar_panel (section, `row`, `column`, year_installed, material, is_tracking)
                values (:section, :row, :column, :year_installed, :material, :is_tracking);
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcClient.sql(sql)
                .param("section", solarPanel.getSection())
                .param("row", solarPanel.getRow())
                .param("column", solarPanel.getColumn())
                .param("year_installed", solarPanel.getYearInstalled())
                .param("material", solarPanel.getMaterial().toString())
                .param("is_tracking", solarPanel.isTracking())
                .update(keyHolder, "id");

        if (rowsAffected == 0) {
            return null;
        }

        solarPanel.setId(keyHolder.getKey().intValue());

        return solarPanel;
    }

    @Override
    public boolean update(SolarPanel solarPanel) throws DataAccessException {
        final String sql = """
                update solar_panel set
                section = :section,
                `row` = :row,
                `column` = :column,
                year_installed = :year_installed,
                material = :material,
                is_tracking = :is_tracking
                where id = :id;
                """;

        int rowsUpdated = jdbcClient.sql(sql)
                .param("section", solarPanel.getSection())
                .param("row", solarPanel.getRow())
                .param("column", solarPanel.getColumn())
                .param("year_installed", solarPanel.getYearInstalled())
                .param("material", solarPanel.getMaterial().toString())
                .param("is_tracking", solarPanel.isTracking())
                .param("id", solarPanel.getId())
                .update();

        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteById(int id) throws DataAccessException {
        final String sql = "delete from solar_panel where id = ?;";
        return jdbcClient.sql(sql).param(id).update() > 0;
    }
}
