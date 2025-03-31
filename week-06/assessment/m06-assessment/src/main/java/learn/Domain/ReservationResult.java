package learn.Domain;

import learn.Model.Reservation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReservationResult<R> {
    private R payload;
    private boolean success;
    private String message;
    private Reservation reservation;
    private List<Reservation> reservationList;
    private static ArrayList<String> messages = new ArrayList<>();

    public List<String> getErrorMessages() {
        return new ArrayList<>(messages);
    }
    public static void addErrorMessage(String message) {
        messages.add(message);
    }
    public ReservationResult() {
    }

    public List<Reservation> getReservations() {
        return reservationList;
    }

    public void setReservations(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public static ArrayList<String> getMessages() {
        return messages;
    }

    public static void setMessages(ArrayList<String> messages) {
        ReservationResult.messages = messages;
    }

    public ReservationResult(R payload, boolean success, String message) {
        this.payload = payload;
        this.success = success;
        this.message = message;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public R getPayload() {
        return payload;
    }

    public void setPayload(R payload) {
        this.payload = payload;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ReservationResult<?> that = (ReservationResult<?>) o;
        return success == that.success && Objects.equals(payload, that.payload) && Objects.equals(message, that.message) && Objects.equals(reservation, that.reservation) && Objects.equals(reservationList, that.reservationList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payload, success, message, reservation, reservationList);
    }

    public void setTotal(BigDecimal total) {
    }
    public void getTotal(BigDecimal total) {
    }
}
