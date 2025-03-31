package learn.field_agent.data;

import learn.field_agent.data.mappers.AliasMapper;
import learn.field_agent.models.Alias;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AliasJdbcClientRepository implements AliasRepository{

    private final JdbcClient jdbcClient;

    public AliasJdbcClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    private final String selectAll = """ 
         select
             a.first_name,
             a.agent_id,
             a.middle_name,
             a.last_name,
             a.dob,
             a.height_in_inches,
             p.alias_id,
             p.name,
             p.persona,
             a.agent_id
         from alias p
         join agent a on p.agent_id = a.agent_id
         
         """;
    @Override
    public List<Alias> findAll() throws DataAccessException {
        final String sql = selectAll;
        return jdbcClient.sql(sql)
                .query(new AliasMapper())
                .list();
    }

    @Override
    public Alias findById(int aliasId) throws DataAccessException {
        final String sql = selectAll + " where p.alias_id = ?";
        return jdbcClient.sql(sql)
                .param(aliasId)
                .query(new AliasMapper())
                .optional().orElse(null);
    }

    @Override
    public Alias add(Alias alias) throws DataAccessException {
        final String sql = """
INSERT into alias (name, persona, agent_id) values (:name, :persona, :agent_id);
""";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcClient.sql(sql)
                .param("name", alias.getName())
                .param("persona", alias.getPersona())
                .param("agent_id", alias.getAgent().getAgentId())
                .update(keyHolder, "alias_id");
        if (rowsAffected <0){
            return null;
        }
        alias.setAliasId(keyHolder.getKey().intValue());
        return alias;
    }

    @Override
    public boolean update(Alias alias) throws DataAccessException {
        final String sql = """
                update alias set
                name = :name,
                persona = :persona
                where agent_id = :agent_id and alias_id = :alias_id
                """;
        return jdbcClient.sql(sql)
                .param("name", alias.getName())
                .param("persona", alias.getPersona())
                .param("agent_id", alias.getAgent().getAgentId())
                .param("alias_id", alias.getAliasId())
                .update() > 0;
    }

    @Override
    public boolean deleteById(int aliasId) throws DataAccessException {
        final String sql = "delete from alias where alias_id = :alias_id ";
        return jdbcClient.sql(sql)
                .param("alias_id", aliasId)
                .update() > 0;
    }
}
