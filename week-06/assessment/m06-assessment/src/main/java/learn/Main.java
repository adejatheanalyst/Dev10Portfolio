package learn;

import learn.Data.LocationJdbcClientRepository;
import learn.Data.ReservationJdbcClientRepository;
import learn.Data.UserJdbcClientRepository;
import learn.Domain.LocationService;
import learn.Domain.ReservationService;
import learn.Domain.UserService;
import learn.Model.Location;
import learn.Model.Reservation;
import learn.UI.ConsoleIO;
import learn.UI.Controller;
import learn.UI.View;
import org.springframework.jdbc.core.simple.JdbcClient;

public class Main {

    public static void main(String[] args) {
        ConsoleIO io = new ConsoleIO();
        View view = new View(io);

        JdbcClient jdbcClient = DataHelper.getJdbcClient();
        ReservationJdbcClientRepository repository = new ReservationJdbcClientRepository(jdbcClient);
        UserJdbcClientRepository userRepository = new UserJdbcClientRepository(jdbcClient);
        LocationJdbcClientRepository locationRepository = new LocationJdbcClientRepository(jdbcClient);


        UserService UserService = new UserService(userRepository);
        LocationService locationService = new LocationService(locationRepository);
        ReservationService reservationService = new ReservationService(locationRepository, repository);

        Controller controller = new Controller(reservationService, UserService, locationService, view);
        controller.run();





    }
}