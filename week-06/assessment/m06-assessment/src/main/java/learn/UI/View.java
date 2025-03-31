package learn.UI;

import learn.Domain.ReservationService;
import learn.Model.Location;
import learn.Model.Reservation;
import learn.Model.User;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class View {
    private final ConsoleIO io;
    private final Scanner userInput = new Scanner(System.in);
    ReservationService reservationService = new ReservationService();

    public View(ConsoleIO io) {
        this.io = io;
    }


    public void displayHeader(String message) {
        io.println(message);
        io.println("=".repeat(message.length()));
        io.println();
    }

    public String getMenuChoice() {
        io.println("Main Menu");
        io.println("1. View Reservations by Host");
        io.println("2. Make a Reservation");
        io.println("3. Edit a Reservation");
        io.println("4. Cancel a Reservation");
        io.println("5. Exit");
        return io.readString("Choose [1-5]: ");
    }


    public void displayLocationsForHost(List<Location> locations) {
        if (locations == null || locations.isEmpty()) {
            io.println("No Locations found");
            return;
        }
        for (Location location : locations) {
            io.printf("Location ID: %s%n", location.getLocation_id());
            io.printf("Location: %s%n", location.getAddress());
            io.printf("City: %s%n", location.getCity());
            io.printf("State: %s%n", location.getStateId());
            io.printf("Standard Rate: %s%n", location.getStandard_rate());
            io.printf("Weekend Rate: %s%n", location.getWeekend_rate());
            io.println();
        }

    }


    public void displayReservationsForHost(List<Reservation> reservations, User user) throws DataAccessException {
        if (reservations == null || reservations.isEmpty()) {
            io.println("No reservations found");
            return;
        }
        LocalDate today = LocalDate.now();
        boolean hasReservations = false;

        io.printf("Guest: %s %s%n", user.getFirst_name(), user.getLast_name());
        for (Reservation reservation : reservations) {
            if (reservation.getStart_date().isAfter(today) && reservation.getUser().getUser_id() == user.getUser_id()) {
                hasReservations = true;
                io.printf("Reservation ID: %s%n", reservation.getReservation_id());
                io.printf("Location: %s%n", reservation.getLocation().getCity());
                io.printf("Start Date: %s%n", reservation.getStart_date());
                io.printf("End Date: %s%n", reservation.getEnd_date());
                io.printf("Total: %s%n", reservation.getTotal());
                io.println();
            }
        }
        if(!hasReservations){
            io.printf("No reservations found!");
        }
    }

    public void displayFutureReservations(List<Reservation> reservations) throws DataAccessException {
        if (reservations == null || reservations.isEmpty()) {
            io.println("No reservations found");
            return;
        }
        LocalDate today = LocalDate.now();

        boolean hasFutureReservations = false;
        for (Reservation reservation : reservations) {
            if (reservation.getStart_date().isAfter(today)) {
                hasFutureReservations = true;
                io.printf("Reservation ID: %s%n", reservation.getReservation_id());
                io.printf("Location: %s%n %s%n", reservation.getLocation().getCity(), reservation.getLocation().getPostal_code());
                io.printf("Guest: %s%n %s%n", reservation.getUser().getFirst_name(), reservation.getUser().getLast_name());;
                io.printf("Start Date: %s%n", reservation.getStart_date());
                io.printf("End Date: %s%n", reservation.getEnd_date());
                io.printf("Total: %s%n", reservation.getTotal());
                io.println();
            }
        }
        if (!hasFutureReservations) {
            io.println("No future reservations found.");
        }
    }


    public boolean displayTotalSummary(Reservation reservation) {
        io.printf("Start Date: %s%n", reservation.getStart_date());
        io.printf("End Date: %s%n", reservation.getEnd_date());
        io.printf("Estimated Total: %s%n", reservation.getTotal());
        return io.readBoolean("Would you like to confirm this reservation? (y/n): ");
    }


    public LocalDate AdminConfirmation(LocalDate result) {
        LocalDate input = io.readLocalDate("Press enter to keep the information the same (current: " + result + "): ");
        if (input == null) {
            return result;  //
        } else {
            return (input);
        }
    }

    public void displayStatus(boolean success, String message) {
        displayStatus(success, List.of(message));
    }

    public void displayStatus(boolean success, List<String> messages) {
        displayHeader(success ? "Success" : "Error");
        for (String message : messages) {
            io.println(message);
        }
    }

    public void displayException(Exception ex) {
        displayHeader("Exception");
        io.println(ex.getMessage());
    }


    public void displayErrorMessage(List<String> message) {
        displayHeader("Error");
        io.println(message.toString());
    }

    public String getHostEmail() {
        String email = io.readEmail("Enter the host email: ");
        if (email == null || email.isBlank()) {
            io.println("Invalid email");
        }
        return email;
    }

    public String getGuestEmail() {
        String email = io.readEmail("Enter the guest email: ");
        if (email == null || email.isBlank()) {
            io.println("Invalid email");
        }
        return email;
    }

    public LocalDate getStartDate() {
        LocalDate startDate = io.readLocalDate("Enter the start date (MM/dd/yyyy): ");
        if (startDate == null) {
            io.println("Invalid date");
        }
        return startDate;
    }

    public LocalDate getEndDate() {
        LocalDate endDate = io.readLocalDate("Enter the end date (MM/dd/yyyy): ");
        if (endDate == null) {
            io.println("Invalid date");
        }
        return endDate;
    }
    public Reservation createReservation(Location location, User user) {
        io.println("Create a new reservation");
        Reservation reservation = new Reservation();
        reservation.setReservation_id(reservation.getReservation_id());
        reservation.setUser(user);
        user.setEmail(user.getEmail());
        user.setUser_id(user.getUser_id());
        user.setFirst_name(user.getFirst_name());
        user.setLast_name(user.getLast_name());
        int location_id = location.getLocation_id();
        reservation.setLocation_id(location_id);
        reservation.setFirst_name(user.getFirst_name());
        reservation.setLast_name(user.getLast_name());
        location.setLocation_id(location.getLocation_id());
        reservation.setLocation(location);
        reservation.setStandard_rate(location.getStandard_rate());
        reservation.setWeekend_rate(location.getWeekend_rate());
        User host = location.getUser_id();
        location.setUser_id(host);
        reservation.setStart_date(getStartDate());
        reservation.setEnd_date(getEndDate());
      BigDecimal total = reservationService.calculateTotal(reservation.getStart_date(), reservation.getEnd_date(), location.getStandard_rate(), location.getWeekend_rate());
        reservation.setTotal(total);
        return reservation;
    }

    public Reservation updateReservation(Reservation reservation, Location location) {
        io.println("Update reservation");
        LocalDate newStartDate = AdminConfirmation(reservation.getStart_date());
        reservation.setStart_date(newStartDate);
        LocalDate newEndDate = AdminConfirmation(reservation.getEnd_date());
        reservation.setEnd_date(newEndDate);
        BigDecimal total = reservationService.calculateTotal(reservation.getStart_date(), reservation.getEnd_date(), location.getStandard_rate(), location.getWeekend_rate());
        reservation.setTotal(total);
        return reservation;
    }

    public Reservation deleteReservation(Reservation reservation) {
        io.println("Delete Reservation");
        reservation.setEmail(getGuestEmail());
        return reservation;
    }

    public Location chooseLocation(List<Location> locations) {
        if (locations == null || locations.isEmpty()) {
            io.println("No locations found");
            return null;
        }
        io.println("Choose a location");
        for (int i = 0; i < locations.size(); i++) {
            io.printf("%s. %s%n", i + 1, locations.get(i).getAddress());
        }
        int choice = userInput.nextInt();
        userInput.nextLine();
        if (choice < 1 || choice > locations.size()) {
            io.println("Invalid choice");
            return chooseLocation(locations);
        }
        return locations.get(choice - 1);
    }
    public Reservation chooseReservation(List<Reservation> reservations) {
        if (reservations == null || reservations.isEmpty()) {
            io.println("No reservations found");
            return null;
        }
        io.println("Choose a reservation");
        for (int i = 0; i < reservations.size(); i++) {
            io.printf("%s. %s%n", i + 1, reservations.get(i).getReservation_id());
        }
        int choice = userInput.nextInt();
        userInput.nextLine();
        if (choice < 1 || choice > reservations.size()) {
            io.println("Invalid choice");
            return chooseReservation(reservations);
        }
        return reservations.get(choice - 1);
    }


    public void displayMessage(String message) {
        io.println(message);
    }

}







