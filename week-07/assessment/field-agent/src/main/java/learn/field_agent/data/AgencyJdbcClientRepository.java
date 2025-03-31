package learn.field_agent.data;

import learn.field_agent.data.mappers.AgencyAgentMapper;
import learn.field_agent.data.mappers.AgencyMapper;
import learn.field_agent.data.mappers.LocationMapper;
import learn.field_agent.models.Agency;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AgencyJdbcClientRepository implements AgencyRepository {

    private final JdbcClient jdbcClient;

    public AgencyJdbcClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Agency> findAll() {
        // limit until we develop a paging solution
        final String sql = "select agency_id, short_name, long_name from agency limit 1000;";
        return jdbcClient.sql(sql).query(new AgencyMapper()).list();
    }

    @Override
    @Transactional
    public Agency findById(int agencyId) {

        final String sql = """
                select agency_id, short_name, long_name
                from agency
                where agency_id = ?;
                """;

        Agency result = jdbcClient.sql(sql)
                .param(agencyId)
                .query(new AgencyMapper())
                .optional().orElse(null);

        if (result != null) {
            addLocations(result);
            addAgents(result);
        }

        return result;
    }

    @Override
    public Agency add(Agency agency) {

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        final String sql = "insert into agency (short_name, long_name) values (?,?);";

        int rowsAffected = jdbcClient.sql(sql)
                .param(agency.getShortName())
                .param(agency.getLongName())
                .update(keyHolder, "agency_id");

        if (rowsAffected <= 0) {
            return null;
        }

        agency.setAgencyId(keyHolder.getKey().intValue());
        return agency;
    }

    @Override
    public boolean update(Agency agency) {

        final String sql = """
                update agency set
                short_name = :short_name,
                long_name = :long_name
                where agency_id = :agency_id
                """;

        return jdbcClient.sql(sql)
                .param("short_name", agency.getShortName())
                .param("long_name", agency.getLongName())
                .param("agency_id", agency.getAgencyId())
                .update() > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int agencyId) {
        jdbcClient.sql("delete from location where agency_id = ?").param(agencyId).update();
        jdbcClient.sql("delete from agency_agent where agency_id = ?").param(agencyId).update();
        return jdbcClient.sql("delete from agency where agency_id = ?").param(agencyId).update() > 0;
    }

    private void addLocations(Agency agency) {

        final String sql = """
                select location_id, name, address, city, region,
                country_code, postal_code, agency_id
                from location
                where agency_id = ?
                """;

        var locations = jdbcClient.sql(sql)
                .param(agency.getAgencyId())
                .query(new LocationMapper())
                .list();

        agency.setLocations(locations);
    }

    private void addAgents(Agency agency) {

        final String sql = """
                select aa.agency_id, aa.agent_id, aa.identifier, aa.activation_date, aa.is_active,
                sc.security_clearance_id, sc.name security_clearance_name,
                a.first_name, a.middle_name, a.last_name, a.dob, a.height_in_inches
                from agency_agent aa
                inner join agent a on aa.agent_id = a.agent_id
                inner join security_clearance sc on aa.security_clearance_id = sc.security_clearance_id
                where aa.agency_id = ?
                """;

        var agencyAgents = jdbcClient.sql(sql)
                .param(agency.getAgencyId())
                .query(new AgencyAgentMapper()).list();

        agency.setAgents(agencyAgents);
    }

}
