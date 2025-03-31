Build Order
1. Model Layer 
-User class------------------------------------------------------------------------------------------------
     int user_id;
     String first_name;
     String last_name;
     String email;
     String phone;
     boolean is_host;
//Contructor
    public User(int user_id, String first_name, String last_name, String email, String phone, boolean is_host) {
 
    }
        --Getters and Setters

-Location class------------------------------------------------------------------------------------------------
     int location_id;
     User user_id;
     String address;
     String city;
     String zip;
     State state;
     BigDecimal standard_rate;
     BigDecimal weekend_rate;
//Contructor
    Location(int location_id, User user_id, String address, String city, String zip, State state_id, BigDecimal standard_rate, BigDecimal weekend_rate) {
    }
    getlocation_id();
    setLocation_id();...

    --Getters and Setters

-Reservation class------------------------------------------------------------------------------------------------
    -Reservation class
    private int reservation_id;
    private User user_id;
    private Location location_id;
    private LocalDate start_date;
    private LocalDate end_date;
    private BigDecimal total;
    User is_host;

    public Reservation(int reservation_id, User user_id, Location location_id, LocalDate start_date, LocalDate end_date, BigDecimal total, User is_host) {
   
    }
    --Getters and Setters
-State class(enum)------------------------------------------------------------------------------------------------
    public enum State{ Alabama, "AL", ETC}
   
    
2. Repository/Data Layer
-UserRepository------------------------------------------------------------------------------------------------
User findyById(int id);
List<User> findAll();
User findByEmail(String email);
-UserJdbcClientRepository------------------------------------------------------------------------------------------------
    public User findById(int id) {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        return jdbcClient.sql(sql)
            .param(id)
            .query(UserMapper);
    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        return jdbcClient.sql(sql)
            .query(UserMapper)
            .list();
    public User findByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email like %?%";
        return jdbcClient.sql(sql)
            .param(email)
            .query(UserMapper)
            .list();
-UserMapper------------------------------------------------------------------------------------------------
        User mapRow(SqlRow row) {
            return new User(
                row.getInt("user_id"),
                row.getString("first_name"),
                row.getString("last_name"),
                row.getString("email"),
                row.getString("phone")
            );
        }
-DataHelper------------------------------------------------------------------------------------------------
DataSource getDataSource() {
    return new DataSource();
 JdbcClient getJdbcClient() {
        return new JdbcClient();
    }
 -UserRepositoryDouble implements UserRepository------------------------------------------------------------------------------------------------
    ArrayList<User> users = new ArrayList<User>();
    User findById(int id) {
        return users.get(id);
    }
    List<User> findAll() {
        return users;
    }
    User findByEmail(String email) {
        return users.get(email);
    }
-UserJdbcClientRepositoryTest------------------------------------------------------------------------------------------------
jdbcClient = DataHelper.getjdbcClient();
userRepository = new UserJdbcClientRepository(jdbcClient);
setup() set known good state. update();
 @Tests
    void findById() PASS AND FAILL TEST CASES;
    void findAll() PASS AND FAILL TEST CASES;
    void findByEmail() PASS AND FAILL TEST CASES;

-LocationRepository------------------------------------------------------------------------------------------------
Location findById(int id);
List<Location> findByState(State state);
List<Location> findAll();
Location findByCity(String city);
Location weekend_rate(weekend_rate);
Location standard_rate(standard_rate);
User user_id(int user_id);
-LocationJdbcClientRepository------------------------------------------------------------------------------------------------
     Location findById(int id) {
        return jdbcClient.sql()
    List<Location> findByState(State state) {
        return jdbcClient.sql()
    List<Location> findAll() {
        return jdbcClient.sql()
    Location findByCity(String city) {
        return jdbcClient.sql()
    User user_id(int user_id) {
        return jdbcClient.sql()
    Location weekend_rate(weekend_rate) {
        return jdbcClient.sql()
    Location standard_rate(standard_rate) {
        return jdbcClient.sql()
-LocationMapper------------------------------------------------------------------------------------------------
    Location mapRow(SqlRow row) {
        return new Location();
    }
-LocationRepositoryDouble implements LocationRepository------------------------------------------------------------------------------------------------
    ArrayList<Location> locations = new ArrayList<Location>();
    Location findById(int id) {
        return locations;
    }
    List<Location> findByState(State state) {
        return locations;
    }
    List<Location> findAll() {
        return locations;
    }
    Location findByCity(String city) {
        return locations;
    }
    User user_id(int user_id) {
        return locations;}
    Location weekend_rate(weekend_rate) {
        return locations;
    }
    Location standard_rate(standard_rate) {
        return locations;
    }
-LocationJdbcClientRepositoryTest------------------------------------------------------------------------------------------------
jdbcClient = DataHelper.getjdbcClient();
locationRepository = new LocationJdbcClientRepository(jdbcClient);
setup() set known good state. update();
 @Tests
    void findById() PASS AND FAILL TEST CASES;
    void findByState() PASS AND FAILL TEST CASES;
    void findAll() PASS AND FAILL TEST CASES;
    void findByCity() PASS AND FAILL TEST CASES;
    void user_id() PASS AND FAILL TEST CASES;
    void weekend_rate() PASS AND FAILL TEST CASES;
    void standard_rate() PASS AND FAILL TEST CASES;
-ReservationRepository------------------------------------------------------------------------------------------------
Reservation findById(int id);
List<Reservation> findByLocation(Location location);
List<Reservation> findByUser(User user);
List<Reservation> findAll();
List<Reservation> findByDate(LocalDate date);
Reservation add(Reservation reservation);
Reservation update(Reservation reservation);
Reservation delete(Reservation reservation);
Reservation findByEmail(String email);
Reservation calculateTotal(Reservation reservation);
-ReservationJdbcClientRepository------------------------------------------------------------------------------------------------
    Reservation findById(int id) {
        return jdbcClient.sql()
    List<Reservation> findByLocation(Location location) {
        return jdbcClient.sql()
    List<Reservation> findByUser(User user) {
        return jdbcClient.sql()
    List<Reservation> findAll() {
        return jdbcClient.sql()
    List<Reservation> findByDate(LocalDate date) {
        return jdbcClient.sql()
    Reservation add(Reservation reservation) {
        return jdbcClient.sql()
    Reservation update(Reservation reservation) {
        return jdbcClient.sql()
    Reservation delete(Reservation reservation) {
        return jdbcClient.sql()
    Reservation findByEmail(String email) {
        return jdbcClient.sql()
    Reservation calculateTotal(Reservation reservation) {
        return jdbcClient.sql()
-ReservationMapper------------------------------------------------------------------------------------------------
    Reservation mapRow(SqlRow row) {
        return new Reservation();
    }
-ReservationRepositoryDouble implements ReservationRepository------------------------------------------------------------------------------------------------
    ArrayList<Reservation> reservations = new ArrayList<Reservation>();
    Reservation findById(int id) {
        return reservations;
    }
    List<Reservation> findByLocation(Location location) {
        return reservations;
    }
    List<Reservation> findByUser(User user) {
        return reservations;
    }
    List<Reservation> findAll() {
        return reservations;
    }
    List<Reservation> findByDate(LocalDate date) {
        return reservations;
    }
    Reservation add(Reservation reservation) {
        return reservations;
    }
    Reservation update(Reservation reservation) {
        return reservations;
    }
    Reservation delete(Reservation reservation) {
        return reservations;
    }
    Reservation findByEmail(String email) {
        return reservations;
    }
    Reservation calculateTotal(Reservation reservation) {
        return reservations;
    }
-ReservationJdbcClientRepositoryTest------------------------------------------------------------------------------------------------
jdbcClient = DataHelper.getjdbcClient();
reservationRepository = new ReservationJdbcClientRepository(jdbcClient);
setup() set known good state. update();
 @Tests
    void findById() PASS AND FAILL TEST CASES;
    void findByLocation() PASS AND FAILL TEST CASES;
    void findByUser() PASS AND FAILL TEST CASES;
    void findAll() PASS AND FAILL TEST CASES;
    void findByDate() PASS AND FAILL TEST CASES;
    void add() PASS AND FAILL TEST CASES;
    void update() PASS AND FAILL TEST CASES;
    void delete() PASS AND FAILL TEST CASES;
    void findByEmail() PASS AND FAILL TEST CASES;
    void calculateTotal() PASS AND FAILL TEST CASES;

3. Domain /Service Layer
-UserService------------------------------------------------------------------------------------------------
List<User> findAll();
User findById(int id);
User findByEmail(String email);
-UserServiceTest------------------------------------------------------------------------------------------------
userService Service = new userService(new UserRepositoryDouble());
 @Tests
    void findAll() PASS AND FAILL TEST CASES;
    void findById() PASS AND FAILL TEST CASES;
    void findByEmail() PASS AND FAILL TEST CASES;

-LocationService------------------------------------------------------------------------------------------------
List<Location> findAll();
Location findByLocationId(int id);
List<Location> findByState(State state);
Location findByCity(String city);
Location findByUserId(int user_id);
-LocationServiceTest------------------------------------------------------------------------------------------------
locationService Service = new locationService(new LocationRepositoryDouble());
 @Tests
    void findAll() PASS AND FAILL TEST CASES;
    void findByLocationId() PASS AND FAILL TEST CASES;
    void findByState() PASS AND FAILL TEST CASES;
    void findByCity() PASS AND FAILL TEST CASES;
    void findByUserId() PASS AND FAILL TEST CASES;

-ReservationService------------------------------------------------------------------------------------------------
List<Reservation> findAll();
Reservation findById(int id);
List<Reservation> findByLocation(Location location);
List<Reservation> findByUser(User user);
List<Reservation> findByDate(LocalDate date);
Reservation add(Reservation reservation);
Reservation update(Reservation reservation);
Reservation delete(Reservation reservation);
Reservation findByEmail(String email);
Reservation calculateTotal(Reservation reservation);
-ReservationServiceTest------------------------------------------------------------------------------------------------
reservationService Service = new reservationService(new ReservationRepositoryDouble());
 @Tests
    void findAll() PASS AND FAILL TEST CASES;
    void findById() PASS AND FAILL TEST CASES;
    void findByLocation() PASS AND FAILL TEST CASES;
    void findByUser() PASS AND FAILL TEST CASES;
    void findByDate() PASS AND FAILL TEST CASES;
    void add() PASS AND FAILL TEST CASES;
    void update() PASS AND FAILL TEST CASES;
    void delete() PASS AND FAILL TEST CASES;
    void findByEmail() PASS AND FAILL TEST CASES;
    void calculateTotal() PASS AND FAILL TEST CASES;

-Result------------------------------------------------------------------------------------------------
public class ReservationResult {
   
   private ArrayList<String> messages = new ArrayList<String>();
    private Reservation reservation;
    private boolean success;
    List<Reservation> getErrorMessages() {
        return null;
    }
    void addErrorMessage(String message) {
        messages.add(message);
    }
    Reservation getReservation() {
        return reservation;
    }
    void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    boolean isSuccess() {
        return null;
    }
}
4. Ui Layer
-Controller------------------------------------------------------------------------------------------------
View view;
UserService userService;
LocationService locationService;
ReservationService reservationService;
boolean isRunning;

Controller(View view, UserService userService, LocationService locationService, ReservationService reservationService) {
}

void run() {
}
void displayMenu() {
    case 1:
        findLocationByEmail();
        break;
    case 2:
        makeReservation();
        break;
    case 3:
        updateReservation();
        break;
    case 4:
        deleteReservation();
        break;
    case 5:
        exit()
        break;
}
void findLocationByEmail() {
}
void makeReservation() {
}
void updateReservation() {
}
void deleteReservation() {
}
void exit() {
}

-TextIO------------------------------------------------------------------------------------------------
void print(String message) {
}
void println(String message) {
}
String readString(String prompt) {
}
int readInt(String prompt) {
}
LocalDate readDate(String prompt) {
}
BigDecimal readBigDecimal(String prompt) {
}
-ConsoleIO------------------------------------------------------------------------------------------------
String INVALID_EMAIL = "Invalid email. Please try again.";
String REQUIRED = "This field is required.";
String INVALID_DATE = "Invalid date. Please try again.";
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

void print(String message) {
}
void println(String message) {
}
String readString(String prompt) {
}
int readInt(String prompt) {
}
LocalDate readDate(String prompt) {
}
BigDecimal readBigDecimal(String prompt) {
}
boolean readBoolean(String prompt) {
}
void printf(String format, Object... args) {
}
-View------------------------------------------------------------------------------------------------
ConsoleIO io;
View(ConsoleIO io) {
}

void displayMenu() {
}
void displayLocations(List<Location> locations) {
}
getHostEmail() {
}
void displayReservation(Reservation reservation) {
}
void displayErrorMessages(List<String> messages) {
}
void displaySuccessMessage(String message) {
}
void displayExitMessage() {
}
getGuestEmail() {
}
void makeReservation() {
}
add more if needed.

5.App Class------------------------------------------------------------------------------------------------
ConsoleIO io = new ConsoleIO();
View view = new View(io);
JdbcClient jdbcClient = DataHelper.getJdbcClient();
UserService userService = new UserService(new UserJdbcClientRepository(jdbcClient));
LocationService locationService = new LocationService(new LocationJdbcClientRepository(jdbcClient));
ReservationService reservationService = new ReservationService(new ReservationJdbcClientRepository(jdbcClient));
Controller controller = new Controller(view, userService, locationService, reservationService);
controller.run();

