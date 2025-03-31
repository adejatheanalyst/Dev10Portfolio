package learn.field_agent.data;

import learn.field_agent.data.mappers.SecurityClearanceMapper;
import learn.field_agent.models.SecurityClearance;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SecurityClearanceJdbcClientRepository implements SecurityClearanceRepository {

    private final JdbcClient jdbcClient;

    public SecurityClearanceJdbcClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public SecurityClearance findById(int securityClearanceId) {

        final String sql = """
                select security_clearance_id, name security_clearance_name
                from security_clearance
                where security_clearance_id = ?;
                """;

        return jdbcClient.sql(sql)
                .param(securityClearanceId)
                .query(new SecurityClearanceMapper())
                .optional().orElse(null);
    }

    @Override
    public List<SecurityClearance> findAll() {
        final String sql = """
                select security_clearance_id, name security_clearance_name
                from security_clearance;
           
                """;
        return jdbcClient.sql(sql)
                .query(new SecurityClearanceMapper())
                .list();
    }

    @Override
    public List<SecurityClearance> findActive() {
        final String sql = """
                 select DISTINCT sc.security_clearance_id, name security_clearance_name
                from security_clearance sc
                join agency_agent aa on  aa.security_clearance_id = sc.security_clearance_id
              
                """;
        return jdbcClient.sql(sql)
                .query(new SecurityClearanceMapper())
                .list();
    }

    @Override
    public SecurityClearance add(SecurityClearance securityClearance) {
        final String sql = """
                insert into security_clearance(name) values
                (:name);
                """;
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcClient.sql(sql)
                .param("name",securityClearance.getName())
                .update(keyHolder, "security_clearance_id");

//        int securityClearance_Id = (Objects.requireNonNull(keyHolder.getKey())).intValue();
        securityClearance.setSecurityClearanceId(keyHolder.getKey().intValue());
        return securityClearance;
    }

    @Override
    public boolean update(SecurityClearance securityClearance) {
        final String sql = "UPDATE security_clearance set name = :name where security_clearance_id = :security_clearance_id";

        int rowsAffected = jdbcClient.sql(sql)
                .param("name", securityClearance.getName())
                .param("security_clearance_id", securityClearance.getSecurityClearanceId())
                .update();
        return rowsAffected == 1;
    }

    @Override
    public boolean deleteById(int securityClearanceId) {
        final String sql = "delete from security_clearance where security_clearance_id = :security_clearance_id;";
             int rowsAffected = jdbcClient.sql(sql)
                .param("security_clearance_id",securityClearanceId)
                .update();
             return rowsAffected == 1;
    }
}
