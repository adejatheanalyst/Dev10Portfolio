package learn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Consumer;

public abstract class FileDataStore {

    private final String filePath;
    private final int columnCount;
    private final boolean hasHeaders;

    public FileDataStore(String filePath, int columnCount, boolean hasHeaders) {
        this.filePath = filePath;
        this.columnCount = columnCount;
        this.hasHeaders = hasHeaders;
    }

    public void runPerLine(Consumer<String[]> lineFunc) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            if (hasHeaders) { // throw out header...
                reader.readLine();
            }

            String line;
            while ((line = reader.readLine()) != null) {

                String[] fields = line.split(",", -1);

                if (fields.length == columnCount) {
                    lineFunc.accept(fields);
                }
            }
        }
    }

}
