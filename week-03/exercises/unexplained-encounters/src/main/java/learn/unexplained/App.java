package learn.unexplained;

import learn.unexplained.data.EncounterFileRepository;
import learn.unexplained.domain.EncounterService;
import learn.unexplained.ui.Controller;
import learn.unexplained.ui.View;

import java.io.Console;

public class App {

    public static void main(String[] args) {
        EncounterFileRepository repository = new EncounterFileRepository("./data/encounters.csv");

        EncounterService service = new EncounterService(repository);

        View view = new View();
        Controller controller = new Controller(service, view);
        controller.run();
















    }
}
