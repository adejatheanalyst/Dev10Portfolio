package learn.Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Reservation {
    private int reservation_id;
    private User user;
    private Location location;
    private LocalDate start_date;
    private LocalDate end_date;
    private BigDecimal total;
    private BigDecimal standard_rate;
    private BigDecimal weekend_rate;
    private String email;
    private String first_name;
    private String last_name;
    private int location_id;

    public Reservation(int reservation_id, LocalDate start_date, LocalDate end_date, BigDecimal total) {
        this.reservation_id = reservation_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.total = total;
    }
    public Reservation(){
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getStandard_rate() {
        return standard_rate;
    }

    public void setStandard_rate(BigDecimal standard_rate) {
        this.standard_rate = standard_rate;
    }

    public BigDecimal getWeekend_rate() {
        return weekend_rate;
    }

    public void setWeekend_rate(BigDecimal weekend_rate) {
        this.weekend_rate = weekend_rate;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Reservation(LocalDate start_date, LocalDate end_date) {
        this.start_date = start_date;
        this.end_date = end_date;
}

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }



    public LocalDate getStart_date() {
        return start_date;
    }

    public LocalDate setStart_date(LocalDate start_date) {
        this.start_date = start_date;
        return start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public LocalDate setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
        return end_date;
    }

    public BigDecimal getTotal() {
        return this.total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return reservation_id == that.reservation_id && location_id == that.location_id && Objects.equals(user, that.user) && Objects.equals(location, that.location) && Objects.equals(start_date, that.start_date) && Objects.equals(end_date, that.end_date) && Objects.equals(total, that.total) && Objects.equals(standard_rate, that.standard_rate) && Objects.equals(weekend_rate, that.weekend_rate) && Objects.equals(email, that.email) && Objects.equals(first_name, that.first_name) && Objects.equals(last_name, that.last_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservation_id, user, location, start_date, end_date, total, standard_rate, weekend_rate, email, first_name, last_name, location_id);
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal calculateTotal() {
        return total;
    }
}
