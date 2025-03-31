package learn.field_agent.data;

import learn.field_agent.data.mappers.LocationMapper;
import learn.field_agent.models.Location;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class LocationJdbcClientRepository implements LocationRepository {

    private final JdbcClient jdbcClient;

    public LocationJdbcClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Location findById(int locationId) {

        final String sql = """
                select location_id, name, address, city, region, country_code, postal_code, agency_id
                from location
                where location_id = ?;
                """;

        return jdbcClient.sql(sql)
                .param(locationId)
                .query(new LocationMapper())
                .optional().orElse(null);
    }

    @Override
    public Location add(Location location) {

        final String sql = """
                insert into location (name, address, city, region, country_code, postal_code, agency_id)
                values (:name, :address, :city, :region, :country_code, :postal_code, :agency_id);
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcClient.sql(sql)
                .param("name", location.getName())
                .param("address", location.getAddress())
                .param("city", location.getCity())
                .param("region", location.getRegion())
                .param("country_code", location.getCountryCode())
                .param("postal_code", location.getPostalCode())
                .param("agency_id", location.getAgencyId())
                .update(keyHolder, "location_id");

        if (rowsAffected <= 0) {
            return null;
        }

        location.setLocationId(keyHolder.getKey().intValue());
        return location;
    }

    @Override
    public boolean update(Location location) {

        // don't allow agency_id updates for now
        final String sql = """
                update location set
                name = :name,
                address = :address,
                city = :city,
                region = :region,
                country_code = :country_code,
                postal_code = :postal_code
                where location_id = :location_id;
                """;

        return jdbcClient.sql(sql)
                .param("name", location.getName())
                .param("address", location.getAddress())
                .param("city", location.getCity())
                .param("region", location.getRegion())
                .param("country_code", location.getCountryCode())
                .param("postal_code", location.getPostalCode())
                .param("agency_id", location.getAgencyId())
                .param("location_id", location.getLocationId())
                .update() > 0;
    }

    @Override
    public boolean deleteById(int locationId) {
        return jdbcClient.sql("delete from location where location_id = ?")
                .param(locationId)
                .update() > 0;
    }
}
