package learn.memories;

import learn.memories.data.MemoryFileRepository;
import learn.memories.domain.MemoryService;
import learn.memories.ui.ConsoleIO;
import learn.memories.ui.Controller;
import learn.memories.ui.View;

public class App {

    public static void main(String[] args) {
        // "./data/memories.txt" is the path to our "production" data file.
        MemoryFileRepository repository = new MemoryFileRepository("./data/memories.txt");
        // repository satisfies the MemoryRepository dependency.
        MemoryService service = new MemoryService(repository);

        ConsoleIO io = new ConsoleIO();
        // io satisfies the TextIO dependency.
        View view = new View(io);

        // view satisfies the View dependency
        // service satisfies the MemoryService dependency
        Controller controller = new Controller(view, service);

        // Run the app!
        controller.run();
    }

}
