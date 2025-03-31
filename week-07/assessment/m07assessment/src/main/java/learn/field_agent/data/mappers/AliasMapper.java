package learn.field_agent.data.mappers;

import learn.field_agent.models.Agent;
import learn.field_agent.models.Alias;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AliasMapper implements RowMapper<Alias> {
    @Override
    public Alias mapRow(ResultSet rs, int rowNum) throws SQLException {

        Alias alias = new Alias();
        alias.setAliasId(rs.getInt("alias_id"));
        alias.setName(rs.getString("name"));
        alias.setPersona(rs.getString("persona"));

        AgentMapper agentMapper = new AgentMapper();
        alias.setAgent(agentMapper.mapRow(rs, rowNum));
        return alias;
    }
}
