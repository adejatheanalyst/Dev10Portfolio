package learn.field_agent.data;

import learn.field_agent.models.AgencyAgent;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AgencyAgentJdbcClientRepository implements AgencyAgentRepository {

    private final JdbcClient jdbcClient;

    public AgencyAgentJdbcClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public boolean add(AgencyAgent agencyAgent) {

        final String sql = """
                insert into agency_agent (agency_id, agent_id, identifier, security_clearance_id,
                activation_date, is_active) values (:agency_id, :agent_id, :identifier,
                :security_clearance_id, :activation_date, :is_active);
                """;

        return jdbcClient.sql(sql)
                .param("agency_id", agencyAgent.getAgencyId())
                .param("agent_id", agencyAgent.getAgent().getAgentId())
                .param("identifier", agencyAgent.getIdentifier())
                .param("security_clearance_id", agencyAgent.getSecurityClearance().getSecurityClearanceId())
                .param("activation_date", agencyAgent.getActivationDate())
                .param("is_active", agencyAgent.isActive())
                .update() > 0;
    }

    @Override
    public boolean update(AgencyAgent agencyAgent) {

        final String sql = """
                update agency_agent set
                    identifier = :identifier,
                    security_clearance_id = :security_clearance_id,
                    activation_date = :activation_date,
                    is_active = :is_active
                where agency_id = :agency_id and agent_id = :agent_id;
                """;

        return jdbcClient.sql(sql)
                .param("agency_id", agencyAgent.getAgencyId())
                .param("agent_id", agencyAgent.getAgent().getAgentId())
                .param("identifier", agencyAgent.getIdentifier())
                .param("security_clearance_id", agencyAgent.getSecurityClearance().getSecurityClearanceId())
                .param("activation_date", agencyAgent.getActivationDate())
                .param("is_active", agencyAgent.isActive())
                .update() > 0;
    }

    @Override
    public boolean deleteByKey(int agencyId, int agentId) {

        final String sql = "delete from agency_agent "
                + "where agency_id = ? and agent_id = ?;";

        return jdbcClient.sql(sql)
                .param(agencyId)
                .param(agentId)
                .update() > 0;
    }
}
