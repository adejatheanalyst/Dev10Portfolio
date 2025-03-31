package learn.foraging;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class
App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

//        ConsoleIO io = new ConsoleIO();
//        View view = new View(io);
//
//        JdbcClient jdbcClient = DataHelper.getJdbcClient();
//        ForageJdbcClientRepository forageRepository =
//                new ForageJdbcClientRepository(jdbcClient);
//        ForagerJdbcClientRepository foragerRepository =
//                new ForagerJdbcClientRepository(jdbcClient);
//        ItemJdbcClientRepository itemRepository =
//                new ItemJdbcClientRepository(jdbcClient);
//
//        ForagerService foragerService = new ForagerService(foragerRepository);
//        ForageService forageService =
//                new ForageService(forageRepository, foragerRepository, itemRepository);
//        ItemService itemService = new ItemService(itemRepository);
//
//        Controller controller = new Controller(foragerService, forageService, itemService, view);
//        controller.run();
    }
}
