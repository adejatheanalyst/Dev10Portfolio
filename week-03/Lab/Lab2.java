import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
public class Lab2 {





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
                        createFiles(splitLine);
                        break;
                    case "APPEND":
                        appendFiles(splitLine);
                        break;
                    case "COPY":
                        copyFiles(splitLine);
                    default:
                        System.out.println("Error: Cannot do that action");
                }
            }
            private static void createFiles (String[] splitLine) throws IOException {
                File file = new File(splitLine[1]);
                if (file.createNewFile()) {
                    System.out.printf("file name %s created at location %s", splitLine[0], splitLine[1]);
                } else if (file.exists()) {
                    System.out.println("file already exists");
                } else {
                    System.out.println("Failed to create file.");
                }
            }


    private static void appendFiles(String [] splitLine) throws IOException{

                Path path = Paths.get(splitLine[1]);
                StringBuilder message = new StringBuilder();
                for (int i = 2; i < splitLine.length; i++){
                   if (i > 2 ) message.append(splitLine[i]);
                }
                message.append(System.lineSeparator());

                try {
                    Files.write(path, message.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    System.out.println("Content appended to file: " + splitLine[1]);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

    private static void copyFiles(String[] splitLine) throws IOException {
                File sourceFile = new File(splitLine[1]);
                File destinationFile = new File(splitLine[2]);
                if (!sourceFile.exists()) {
                    System.out.println("File does not exits!");
                    return;
                }
                File directoryFile = destinationFile.getParentFile();
                if (directoryFile != null && !directoryFile.exists()) {
                    if (!directoryFile.mkdirs()) {
                        System.out.println("Failed to created directory!");
                        return;
                    }
                }
                    try (BufferedReader sourceReader = new BufferedReader(new FileReader(sourceFile));
                        BufferedWriter destinationWriter = new BufferedWriter(new FileWriter(destinationFile, true))) {
                        String sourceLine;
                        while ((sourceLine = sourceReader.readLine()) != null) {
                            destinationWriter.write(sourceLine);
                            destinationWriter.newLine();
                        }
                        System.out.printf("File coped from %s to %s%n", splitLine[1], splitLine[2]);
                    }
    }
}




