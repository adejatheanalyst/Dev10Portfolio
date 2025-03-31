package learn.rpg.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRepository<T> {

    private final String filePath;
    private final int fieldCount;
    private final boolean hasHeader;

    public BaseRepository(String filePath, int fieldCount, boolean hasHeader) {
        this.filePath = filePath;
        this.fieldCount = fieldCount;
        this.hasHeader = hasHeader;
    }

    public List<T> findAll() {
        ArrayList<T> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            if (hasHeader) {
                reader.readLine();
            }

            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",", -1);
                if (fields.length == fieldCount) {
                    result.add(deserialize(fields));
                }
            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return result;
    }

    protected abstract T deserialize(String[] fields);
}
