package learn.field_agent.data;

import learn.field_agent.models.Alias;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AliasRepository {
    List<Alias> findAll() throws DataAccessException;
    Alias findById(int aliasId) throws DataAccessException;
    Alias add(Alias alias) throws DataAccessException;
    boolean update(Alias alias) throws DataAccessException;
    boolean deleteById(int aliasId) throws DataAccessException;
}
