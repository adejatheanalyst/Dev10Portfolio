package learn.solarfarm;

import learn.solarfarm.data.SolarPanelFileRepository;
import learn.solarfarm.data.SolarPanelJdbcClientRepository;
import learn.solarfarm.data.SolarPanelRepository;
import learn.solarfarm.domain.SolarPanelService;
import learn.solarfarm.ui.ConsoleIO;
import learn.solarfarm.ui.Controller;
import learn.solarfarm.ui.TextIO;
import learn.solarfarm.ui.View;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.simple.JdbcClient;
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
//        TextIO io = new ConsoleIO();
//        View view = new View(io);
//        JdbcClient jdbcClient = DataHelper.getJdbcClient();


////        SolarPanelRepository repository = new SolarPanelFileRepository("data/solarfarm-seed.txt");
//
//        SolarPanelJdbcClientRepository repository = new SolarPanelJdbcClientRepository(jdbcClient);
//
//
//        SolarPanelService service = new SolarPanelService(repository);
//
//        Controller controller = new Controller(view,service);
//
//
//        controller.run();

        }

    }

