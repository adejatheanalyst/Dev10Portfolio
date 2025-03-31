import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
public class FileIO {
    public static void main(String[] args) {
//      // CREATING A FILE
//        File file = new File("colors.txt");
//        try {
//            if (file.createNewFile()){
//                System.out.println("color.txt created.");
//            }else {
//                System.out.println("color.txt already exists.");
//            }
//        }catch (IOException ex){
//            ex.printStackTrace();
//        }
// //WRITING A FILE (try with resources)
//        try (PrintWriter writer = new PrintWriter("colors.txt")){
//            writer.println("Red");
//            writer.println("Blue");
//            writer.println("Yellow");
//        }catch (FileNotFoundException ex){
//            ex.printStackTrace();
//
//        }// APPENDING TO A FILE
        // "colors.txt" is a relative path.
// The file will be created in the project root.
//        try (FileWriter fileWriter = new FileWriter("colors.txt", true);
//        PrintWriter writer = new PrintWriter(fileWriter)){
//            writer.println("Green");
//            writer.println("Orange");
//            writer.println("Purple");
//        }catch (IOException ex){
//            ex.printStackTrace();
//
//        }
        // READING FROM A FILE
//        System.out.println("File contents: ");
//try (FileReader fileReader = new FileReader("colors.txt");
//BufferedReader reader = new BufferedReader(fileReader)){
//
//    // when there are no more lines to read, readLine() return null.
//    for (String line = reader.readLine(); line != null; line = reader.readLine()) {
//        System.out.println(line);
//    }
//}catch (IOException ex){
//    ex.printStackTrace();
//}
// Deleting a file
//
//        File file = new File("colors.txt");
//        boolean success = file.delete();
//        if (success) {
//            System.out.println("colors.txt was deleted.");
//        } else {
//            System.out.println("colors.txt was NOT deleted.");
//        }

        ArrayList<String> lines = new ArrayList<>();
        lines.add("hydrogen");
        lines.add("helium");
        lines.add("lithium");

// Create a file and write to it.
//        Path path = Paths.get("elements.txt");
//        try {
//            Files.write(path, lines, StandardOpenOption.CREATE);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }

        lines.clear();
        lines.add("beryllium");
        lines.add("boron");
        lines.add("carbon");

// Append to a file.
//        try {
//            Files.write(path, lines, StandardOpenOption.APPEND);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }

// Read from the file.
//        try {
//            for (String line : Files.readAllLines(path)) {
//                System.out.println(line);
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }

// Delete the file.
//        try {
//            boolean success = Files.deleteIfExists(path);
//            if (success) {
//                System.out.println("elements.txt was deleted.");
//            } else {
//                System.out.println("elements.txt was NOT deleted.");
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
        File workingDirectory = new File(".");
        for (String path : workingDirectory.list()) {
            File entry = new File(path);
            if (entry.isDirectory()) {
                System.out.print("Directory: ");
            } else {
                System.out.print("File     : ");
            }
            System.out.println(entry.getName());
        }

//make a directory
        {
                String directoryPath = "/path/to/parent/directory";

                File directory = new File(directoryPath);

                if (directory.mkdirs()) {
                    System.out.println("Directory created successfully: " + directoryPath);
                } else {
                    System.out.println("Failed to create directory: " + directoryPath);
                }
            }
        }

    }

}



    }

    }













