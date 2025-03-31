import java.io.*;
import java.nio.file.*;
import java.nio.file.StandardOpenOption;

public class FileProcessor {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("README.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processLine(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void processLine(String line) throws IOException {
        String[] splitLine = line.split("\\s+");
        if (splitLine.length == 0 || splitLine[0].startsWith("#")) {
            // Skip comments or empty lines
            return;
        }

        switch (splitLine[0].toUpperCase()) {
            case "CREATE":
                handleCreate(splitLine);
                break;
            case "APPEND":
                handleAppend(splitLine);
                break;
            case "COPY":
                handleCopy(splitLine);
                break;
            case "DELETE":
                handleDelete(splitLine);
                break;
            default:
                System.out.println("Unknown command: " + splitLine[0]);
        }
    }

    private static void handleCreate(String[] splitLine) throws IOException {
        if (splitLine.length < 2) {
            System.out.println("Invalid CREATE command");
            return;
        }

        File file = new File(splitLine[1]);
        if (file.exists()) {
            System.out.println("File already exists: " + splitLine[1]);
        } else {
            if (file.createNewFile()) {
                System.out.printf("File created: %s%n", file.getAbsolutePath());
            } else {
                System.out.println("Failed to create file: " + splitLine[1]);
            }
        }
    }

    private static void handleAppend(String[] splitLine) throws IOException {
        if (splitLine.length < 3) {
            System.out.println("Invalid APPEND command");
            return;
        }

        Path path = Paths.get(splitLine[1]);
        StringBuilder message = new StringBuilder();
        for (int i = 2; i < splitLine.length; i++) {
            if (i > 2) message.append(" ");
            message.append(splitLine[i]);
        }
        message.append(System.lineSeparator());

        try {
            Files.write(path, message.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Content appended to file: " + splitLine[1]);
        } catch (IOException e) {
            System.err.println("Failed to append to file: " + e.getMessage());
        }
    }

    private static void handleCopy(String[] splitLine) throws IOException {
        if (splitLine.length < 3) {
            System.out.println("Invalid COPY command");
            return;
        }

        File sourceFile = new File(splitLine[1]);
        File destinationFile = new File(splitLine[2]);

        if (!sourceFile.exists()) {
            System.out.println("Source file does not exist: " + splitLine[1]);
            return;
        }

        // Ensure the destination directory exists
        File parentDir = destinationFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                System.out.println("Failed to create directory: " + parentDir.getAbsolutePath());
                return;
            }
        }

        // Copy contents
        try (BufferedReader sourceReader = new BufferedReader(new FileReader(sourceFile));
             BufferedWriter destinationWriter = new BufferedWriter(new FileWriter(destinationFile, true))) {
            String sourceLine;
            while ((sourceLine = sourceReader.readLine()) != null) {
                destinationWriter.write(sourceLine);
                destinationWriter.newLine();
            }
            System.out.printf("File copied from %s to %s%n", splitLine[1], splitLine[2]);
        } catch (IOException e) {
            System.err.println("Failed to copy file: " + e.getMessage());
        }
    }
    private static void handleDelete(String[] splitLine) {
        if (splitLine.length < 2) {
            System.out.println("Invalid DELETE command");
            return;
        }

        File fileToDelete = new File(splitLine[1]);
        if (fileToDelete.exists() && fileToDelete.delete()) {
            System.out.println("File deleted: " + splitLine[1]);
        } else {
            System.out.println("File not found or could not be deleted: " + splitLine[1]);
        }
    }

}
