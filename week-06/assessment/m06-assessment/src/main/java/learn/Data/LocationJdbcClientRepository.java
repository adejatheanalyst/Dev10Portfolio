package learn.Data;

import learn.Data.Mappers.LocationMapper;
import learn.Data.Mappers.WeekendRateMapper;
import learn.Model.Location;
import learn.Model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

public class LocationJdbcClientRepository implements LocationRepository {
    private final JdbcClient jdbcClient;
    final String selectAll = "select l.location_id, u.user_id, l.address, l.city, l.postal_code, s.state_id, l.standard_rate, l.weekend_rate\n" +
            "        from location l\n" +
            "        inner join user u on l.user_id = u.user_id\n" +
            "        inner join state s on l.state_id = s.state_id ";

    public LocationJdbcClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Location findById(int location_id) throws DataAccessException {
        final String sql = selectAll + " where l.location_id = ?; ";
        return jdbcClient.sql(sql)
                .param(location_id)
                .query(new LocationMapper())
                .optional().orElse(null);

    }

    @Override
    public List<Location> findByState(int state_id) throws DataAccessException {
        final String sql = """
        select l.location_id, u.user_id, l.address, l.city, l.postal_code, s.state_id, l.standard_rate, l.weekend_rate
        from location l
        inner join user u on l.user_id = u.user_id
        inner join state s on l.state_id = s.state_id
        where s.state_id = ?;
""";

        return jdbcClient.sql(sql)
                .param(state_id)
                .query(new LocationMapper())
                .stream().toList();
    }

    @Override
    public List<Location> findAll() throws DataAccessException {
        final String sql = selectAll;

        return jdbcClient.sql(sql)
                .query(new LocationMapper())
                .list();

    }

    @Override
    public List<Location> findByCity(String city) throws DataAccessException {
        final String sql = selectAll + "where l.city = ? order by l.location_id;";
        return jdbcClient.sql(sql)
                .param(city)
                .query(new LocationMapper())
                .list();
    }


@Override
    public List<Location> findByUserId(User user_id) throws DataAccessException {
        final String sql = selectAll + "where u.user_id = ? order by l.location_id;";
        return jdbcClient.sql(sql)
                .param(user_id.getUser_id())
                .query(new LocationMapper())
                .list();
    }

    @Override
    public Location findStandardRate(int location_id) throws DataAccessException {
        final String sql = " select standard_rate, location_id, user_id from location where location_id = ?;";
        return jdbcClient.sql(sql)
                .param(location_id)
                .query(new LocationMapper())
                .single();
    }

    @Override
    public Location findWeekendRate(int location_id) throws DataAccessException {
        final String sql = " select weekend_rate, location_id, user_id from location where location_id = ?;";
        return jdbcClient.sql(sql)
                .param(location_id)
                .query(new WeekendRateMapper())
                .optional().orElse(null);
    }


}
