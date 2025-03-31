package learn.field_agent.data;

import learn.field_agent.data.mappers.AgentAgencyMapper;
import learn.field_agent.data.mappers.AgentMapper;
import learn.field_agent.models.Agent;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Repository
public class AgentJdbcClientRepository implements AgentRepository {

    private final JdbcClient jdbcClient;

    public AgentJdbcClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Agent> findAll() {
        final String sql = """
                select agent_id, first_name, middle_name, last_name, dob, height_in_inches
                from agent limit 1000;
                """;
        return jdbcClient.sql(sql).query(new AgentMapper()).list();
    }

    @Override
    @Transactional
    public Agent findById(int agentId) {

        final String sql = """
                select agent_id, first_name, middle_name, last_name, dob, height_in_inches
                from agent
                where agent_id = ?;
                """;

        Agent agent = jdbcClient.sql(sql).param(agentId)
                .query(new AgentMapper())
                .optional().orElse(null);

        if (agent != null) {
            addAgencies(agent);
        }

        return agent;
    }

    @Override
    public Agent add(Agent agent) {

        final String sql = """
                insert into agent (first_name, middle_name, last_name, dob, height_in_inches)
                values (:first_name, :middle_name, :last_name, :dob, :height_in_inches);
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcClient.sql(sql)
                .param("first_name", agent.getFirstName())
                .param("middle_name", agent.getMiddleName())
                .param("last_name", agent.getLastName())
                .param("dob", agent.getDob() == null ? null : Date.valueOf(agent.getDob()))
                .param("height_in_inches", agent.getHeightInInches())
                .update(keyHolder, "agent_id");

        if (rowsAffected <= 0) {
            return null;
        }

        agent.setAgentId(keyHolder.getKey().intValue());
        return agent;
    }

    @Override
    public boolean update(Agent agent) {

        final String sql = """
                update agent set
                first_name = :first_name,
                middle_name = :middle_name,
                last_name = :last_name,
                dob = :dob,
                height_in_inches = :height_in_inches
                where agent_id = :agent_id;
                """;

        return jdbcClient.sql(sql)
                .param("first_name", agent.getFirstName())
                .param("middle_name", agent.getMiddleName())
                .param("last_name", agent.getLastName())
                .param("dob", agent.getDob())
                .param("height_in_inches", agent.getHeightInInches())
                .param("agent_id", agent.getAgentId())
                .update() > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int agentId) {
        jdbcClient.sql("delete from agency_agent where agent_id = ?;").param(agentId).update();
        return jdbcClient.sql("delete from agent where agent_id = ?;").param(agentId).update() > 0;
    }

    private void addAgencies(Agent agent) {

        final String sql = """
                select aa.agency_id, aa.agent_id, aa.identifier, aa.activation_date, aa.is_active,
                sc.security_clearance_id, sc.name security_clearance_name,
                a.short_name, a.long_name
                from agency_agent aa
                inner join agency a on aa.agency_id = a.agency_id
                inner join security_clearance sc on aa.security_clearance_id = sc.security_clearance_id
                where aa.agent_id = ?;
                """;

        var agentAgencies = jdbcClient.sql(sql)
                .param(agent.getAgentId())
                .query(new AgentAgencyMapper())
                .list();

        agent.setAgencies(agentAgencies);
    }
}
