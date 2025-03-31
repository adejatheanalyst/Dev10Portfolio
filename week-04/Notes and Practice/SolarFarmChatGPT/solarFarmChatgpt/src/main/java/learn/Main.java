package learn;

import learn.solarfarm.data.DataAccessException;
import learn.solarfarm.data.SolarPanelFileRepository;
import learn.solarfarm.domain.SolarPanelService;
import learn.solarfarm.ui.ConsoleIO;
import learn.solarfarm.ui.Controller;
import learn.solarfarm.ui.TextIO;
import learn.solarfarm.ui.View;

public class Main {
    public static void main(String[] args) throws DataAccessException {


        // Initialize repository, service, and view
        TextIO io = new ConsoleIO();

        SolarPanelFileRepository repository = new SolarPanelFileRepository("data/solarpanels.txt");
        SolarPanelService service = new SolarPanelService(repository);
        View view = new View(io);

        // Initialize and run the controller
        Controller controller = new Controller(service, view);
        controller.run();
    }
}