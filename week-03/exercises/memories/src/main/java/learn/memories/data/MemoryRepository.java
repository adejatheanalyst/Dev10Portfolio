package learn.memories.data;
import learn.memories.models.Memory;
import java.util.List;
public interface MemoryRepository {

    List<Memory> findAll() throws DataAccessException;

    Memory findById(int memoryId) throws DataAccessException;

    List<Memory> findShareable(boolean shareable) throws DataAccessException;

    Memory add(Memory memory) throws DataAccessException;

    boolean update(Memory memory) throws DataAccessException;

    boolean deleteById(int memoryId) throws DataAccessException;



}
