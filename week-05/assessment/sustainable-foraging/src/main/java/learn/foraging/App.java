package learn.foraging;

import learn.foraging.data.ForageJdbcClientRepository;
import learn.foraging.data.ForagerJdbcClientRepository;
import learn.foraging.data.ItemJdbcClientRepository;
import learn.foraging.domain.ForageService;
import learn.foraging.domain.ForagerService;
import learn.foraging.domain.ItemService;
import learn.foraging.ui.ConsoleIO;
import learn.foraging.ui.Controller;
import learn.foraging.ui.View;
import org.springframework.jdbc.core.simple.JdbcClient;

public class
App {
    public static void main(String[] args) {

        ConsoleIO io = new ConsoleIO();
        View view = new View(io);

        JdbcClient jdbcClient = DataHelper.getJdbcClient();
        ForageJdbcClientRepository forageRepository =
                new ForageJdbcClientRepository(jdbcClient);
        ForagerJdbcClientRepository foragerRepository =
                new ForagerJdbcClientRepository(jdbcClient);
        ItemJdbcClientRepository itemRepository =
                new ItemJdbcClientRepository(jdbcClient);

        ForagerService foragerService = new ForagerService(foragerRepository);
        ForageService forageService =
                new ForageService(forageRepository, foragerRepository, itemRepository);
        ItemService itemService = new ItemService(itemRepository);

        Controller controller = new Controller(foragerService, forageService, itemService, view);
        controller.run();
    }
}
