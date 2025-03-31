package learn.solarfarm.data;

import learn.solarfarm.models.SolarPanel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class SolarPanelJdbcTemplateRepository implements SolarPanelRepository {

    private final JdbcTemplate jdbcTemplate;
    private final String SELECT = """
            select panel_id, section, `row`, `column`,
            year_installed, material, is_tracking, user_id
            from panel
            """;

    public SolarPanelJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<SolarPanel> findAll() {
        return jdbcTemplate.query(SELECT + ";", new SolarPanelMapper());
    }

    @Override
    public List<SolarPanel> findBySection(String section) {
        String sql = SELECT + "where section = ?;";
        return jdbcTemplate.query(sql, new SolarPanelMapper(), section);
    }

    @Override
    public SolarPanel findByKey(String section, int row, int column) {
        String sql = SELECT + """
                where section = ?
                and `row` = ?
                and `column` = ?;
                """;
        return jdbcTemplate.query(sql, new SolarPanelMapper(), section, row, column)
                .stream().findFirst().orElse(null);
    }

    @Override
    public SolarPanel findById(int id) {
        String sql = SELECT + "where panel_id = ?;";
        return jdbcTemplate.query(sql, new SolarPanelMapper(), id)
                .stream().findFirst().orElse(null);
    }

    @Override
    public List<SolarPanel> findByUserId(int userId) {
        String sql = SELECT + "where user_id = ?;";
        return jdbcTemplate.query(sql, new SolarPanelMapper(), userId);

    }

    @Override
    public SolarPanel create(SolarPanel solarPanel) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("panel")
                .usingColumns("section", "`row`", "`column`",
                        "year_installed", "material", "is_tracking", "user_id")
                .usingGeneratedKeyColumns("panel_id");

        HashMap<String, Object> args = new HashMap<>();
        args.put("section", solarPanel.getSection());
        args.put("`row`", solarPanel.getRow());
        args.put("`column`", solarPanel.getColumn());
        args.put("year_installed", solarPanel.getYearInstalled());
        args.put("material", solarPanel.getMaterial());
        args.put("is_tracking", solarPanel.isTracking());
        args.put("user_id", solarPanel.getUserId());

        int panelId = insert.executeAndReturnKey(args).intValue();
        solarPanel.setId(panelId);

        return solarPanel;
    }

    @Override
    public boolean update(SolarPanel panel) {

        String sql = """
                update panel set
                    section = ?,
                    `row` = ?,
                    `column` = ?,
                    year_installed = ?,
                    material = ?,
                    is_tracking = ?
                where panel_id = ?;
                """;

        return jdbcTemplate.update(sql,
                panel.getSection(),
                panel.getRow(),
                panel.getColumn(),
                panel.getYearInstalled(),
                panel.getMaterial().toString(),
                panel.isTracking(),
                panel.getId()) > 0;
    }

    @Override
    public boolean deleteById(int id) {
        return jdbcTemplate.update("delete from panel where panel_id = ?;", id) > 0;
    }
}
