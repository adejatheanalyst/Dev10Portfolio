package learn.memories.data;
import learn.memories.models.Memory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MemoryFileRepository implements MemoryRepository{
    // [id]~[from]~[content]~[shareable]
    //1~Coach Danahar~Really appreciated your hustle and dedication. Excellent jobs these last 3 years.~true
    //2~Uncle Sherwin~When you were two, you burned your finger and said you had an "owie minger". So cute.~true
    //3~Sibelle~I can't believe we pantsed Ennis Caldicot and got away with it! Life would be boring without you.~false
    private final String filePath;
    private final String delimiter = "~";

    public MemoryFileRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Memory> findAll() throws DataAccessException {
        ArrayList<Memory> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                Memory m = lineToMemory(line);
                if (m != null) {
                    result.add(m);
                }
            }
        } catch (FileNotFoundException ex) {
            // If the file doesn't exist, no big deal.
            // We'll create it when we add a new memory.
            // No file just means no memories yet.
        } catch (IOException ex) {
            throw new DataAccessException("Could not open the file path: " + filePath, ex);
        }
        return result;
    }
    @Override
    public Memory findById(int memoryId) throws DataAccessException {
        List<Memory> all = findAll();
        for (Memory memory : all) {
            if (memory.getId() == memoryId) {
                return memory;
            }
        }
        return null;
    }

    @Override
    public List<Memory> findShareable(boolean shareable) throws DataAccessException {
        ArrayList<Memory> result = new ArrayList<>();
        for (Memory memory : findAll()) {
            if (memory.isShareable() == shareable) {
                result.add(memory);
            }
        }
        return result;
    }
    @Override
    public Memory add(Memory memory) throws DataAccessException {
        List<Memory> all = findAll();
        int nextId = getNextId(all);
        memory.setId(nextId);
        all.add(memory);
        writeToFile(all);
        return memory;
    }
    @Override
    public boolean update(Memory memory) throws DataAccessException {
        List<Memory> all = findAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId() == memory.getId()) {
                all.set(i, memory);
                writeToFile(all);
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean deleteById(int memoryId) throws DataAccessException {
        List<Memory> all = findAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId() == memoryId) {
                all.remove(i);
                writeToFile(all);
                return true;
            }
        }
        return false;
    }

    private int getNextId(List<Memory> memories) {
        int maxId = 0;
        for (Memory memory : memories) {
            if (maxId < memory.getId()) {
                maxId = memory.getId();
            }
        }
        return maxId + 1;
    }

    private void writeToFile(List<Memory> memories) throws DataAccessException {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            for (Memory memory : memories) {
                writer.println(memoryToLine(memory));
            }
        } catch (IOException ex) {
            throw new DataAccessException("Could not write to file path: " + filePath, ex);
        }
    }

    private Memory lineToMemory(String line) {
        String[] fields = line.split(delimiter);

        if (fields.length != 4) {
            return null;
        }

        return new Memory(
                Integer.parseInt(fields[0]),
                fields[1],
                fields[2],
                "true".equals(fields[3])
        );
    }

    private String memoryToLine(Memory memory) {
        StringBuilder buffer = new StringBuilder(100);
        buffer.append(memory.getId()).append(delimiter);
        buffer.append(cleanField(memory.getFrom())).append(delimiter);
        buffer.append(cleanField(memory.getContent())).append(delimiter);
        buffer.append(memory.isShareable());
        return buffer.toString();
    }

    private String cleanField(String field) {
        // If the file delimiter, a carriage return, or a newline was written to the file,
        // it would ruin our ability to read the memory.
        // Here, we ensure those characters don't end up in the file.
        return field.replace(delimiter, "")
                .replace("\r", "")
                .replace("\n", "");
    }





}
