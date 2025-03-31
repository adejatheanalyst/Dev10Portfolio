package learn.UI;

import learn.Domain.*;
import learn.Model.Location;
import learn.Model.Reservation;
import learn.Model.User;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private final ReservationService reservationService;
    private final UserService userService;
    private final LocationService locationService;
    private final View view;
    private boolean stillRunning = true;

    public Controller(ReservationService reservationService, UserService userService, LocationService locationService, View view) {
        this.reservationService = reservationService;
        this.userService = userService;
        this.locationService = locationService;
        this.view = view;

    }

    public void run() {
        view.displayHeader("Dont Wreck My House");
        try {
            runAppLoop();
        } catch (Exception ex) {
            view.displayException(ex);
        }
    }

    private void runAppLoop() {
        view.displayMessage("Welcome to Dont Wreck My House");
        String userInput = null;
        while (stillRunning) {
            userInput = view.getMenuChoice();
            switch (userInput) {
                case "1" -> viewReservations();
                case "2" -> makeReservation();
                case "3" -> editReservation();
                case "4" -> cancelReservation();
                case "5" -> exit();
                default -> view.displayMessage("Invalid input");
            }
        }
    }

    private void exit() {
        stillRunning = false;
        view.displayMessage("Goodbye");
    }

    private void viewReservations() {
        view.displayHeader("View Reservations");
        String hostEmail = view.getHostEmail();
        UserResult<User> hostResult = userService.findByEmail(hostEmail);
        if (!hostResult.isSuccess()) {
            view.displayErrorMessage(hostResult.getErrorMessages());
            return;
        }
        User host = hostResult.getUser();
        List<Location> locations = locationService.repository.findByUserId(host);
        view.displayLocationsForHost(locations);
        Location hostLocation = view.chooseLocation(locations);
        List<Reservation> reservations = reservationService.findByLocationId(hostLocation).getReservations();
        if (reservations.isEmpty()) {
            view.displayMessage("No reservations found for chosen location!");
            return;
        }
            for (Reservation reservation : reservations) {
                User user = reservation.getUser();
                view.displayReservationsForHost(reservations, user);
            }
        }

    private void makeReservation() {
        view.displayHeader("Make a Reservation");
        String hostEmail = view.getHostEmail();
        UserResult<User> hostResult = userService.findByEmail(hostEmail);
        if (!hostResult.isSuccess()) {
            view.displayErrorMessage(hostResult.getErrorMessages());
            return;
        }
        User host = hostResult.getUser();
        List<Location> locations = locationService.repository.findByUserId(host);
        view.displayLocationsForHost(locations);
        Location hostLocation = view.chooseLocation(locations);
        List<Reservation> reservations = reservationService.findByLocationId(hostLocation).getReservations();
        if (reservations.isEmpty()) {
            view.displayMessage("No Futures reservations found! Please add a reservation.");
        }

        for (Reservation reservation : reservations) {
            User user = reservation.getUser();
            view.displayReservationsForHost(reservations, user);
        }

        String guestEmail = view.getGuestEmail();
        UserResult<User> guestResult = userService.findByEmail(guestEmail);
        if (!guestResult.isSuccess()) {
            view.displayErrorMessage(guestResult.getErrorMessages());
            return;
        }
        User guest = guestResult.getUser();

        Reservation reservation = view.createReservation(hostLocation, guest);
        reservation.setEmail(hostEmail);
        boolean confirmation = view.displayTotalSummary(reservation);
        if (!confirmation) {
            view.displayMessage("Reservation cancelled");
            return;
        }
        boolean success = reservationService.add(reservation).isSuccess();
        if (success) {
            view.displayMessage("Reservation added successfully");
        } else {
            view.displayMessage("Reservation not added");
        }
    }
    private void editReservation() {
        view.displayHeader("Edit Reservation");
        String hostEmail = view.getHostEmail();
        String guestEmail = view.getGuestEmail();
        //find guest and user
        UserResult<User> hostResult = userService.findByEmail(hostEmail);
        if (!hostResult.isSuccess()) {
            view.displayErrorMessage(hostResult.getErrorMessages());
            return;
        }
        UserResult<User> guestResult = userService.findByEmail(guestEmail);
        if (!guestResult.isSuccess()) {
            view.displayErrorMessage(guestResult.getErrorMessages());
            return;
        }
        User host = hostResult.getUser();
        User guest = guestResult.getUser();
// find locations for host
        List<Location> locations = locationService.repository.findByUserId(host);
        view.displayLocationsForHost(locations);
        Location hostLocation = view.chooseLocation(locations);

        List<Reservation> reservations = reservationService.findByLocationId(hostLocation).getReservations();
        if(reservations.isEmpty()){
            view.displayMessage("No reservations found for chosen location!");
        }
        view.displayReservationsForHost(reservations, guest);
        Reservation reservation = view.chooseReservation(reservations);
        if (reservation == null) {
            view.displayMessage("Invalid reservation");
            return;
        }
        Location currentLocation = reservation.getLocation();
        Reservation updatedReservation = view.updateReservation(reservation, currentLocation);
        boolean confirmation = view.displayTotalSummary(updatedReservation);
        if (!confirmation) {
            view.displayMessage("Reservation not updated");
            return;
        }
        boolean success = reservationService.update(updatedReservation).isSuccess();
        if (success) {
            view.displayMessage("Reservation updated successfully");
        } else {
            view.displayMessage("Reservation not updated");
        }
    }

private void cancelReservation() {
        view.displayHeader("Cancel Reservation");
        String hostEmail = view.getHostEmail();
        String guestEmail = view.getGuestEmail();
        //find guest and user
        UserResult<User> hostResult = userService.findByEmail(hostEmail);
        if (!hostResult.isSuccess()) {
            view.displayErrorMessage(hostResult.getErrorMessages());
            return;
        }
        UserResult<User> guestResult = userService.findByEmail(guestEmail);
        if (!guestResult.isSuccess()) {
            view.displayErrorMessage(guestResult.getErrorMessages());
            return;
        }
        User host = hostResult.getUser();
        User guest = guestResult.getUser();
// find locations for host
    List<Location> locations = locationService.repository.findByUserId(host);
    view.displayLocationsForHost(locations);
    Location hostLocation = view.chooseLocation(locations);

    List<Reservation> reservations = reservationService.findByLocationId(hostLocation).getReservations();
    if(reservations.isEmpty()){
        view.displayMessage("No reservations found for chosen location!");
    }
    view.displayReservationsForHost(reservations, guest);
    Reservation reservation = view.chooseReservation(reservations);
    if (reservation == null) {
        view.displayMessage("Invalid reservation");
        return;
    }
        boolean success = reservationService.delete(reservation).isSuccess();
        if (success) {
            view.displayMessage("Reservation cancelled successfully");
        } else {
            view.displayMessage("Reservation not cancelled");
        }
    }
}



















