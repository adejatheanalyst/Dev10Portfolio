import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class Finally {
    public static void main(String[] args) {
        FileReader reader = null;

        try {
            reader = new FileReader("data.txt");
            System.out.println(reader.read());
        } catch (FileNotFoundException ex) {
            System.out.println("Couldn't find the file.");
        } catch (IOException ex) {
            System.out.println("Couldn't read from the file.");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    System.out.println("Couldn't close the file.");
                }
            }
        }
    }
}












