package learn.Model;

import java.math.BigDecimal;
import java.util.Objects;

public class Location {
 public int location_id;
    public User user_id;
    private String address;
    private String city;
    private String postal_code;
    private int state_id;
    private BigDecimal standard_rate;
    private BigDecimal weekend_rate;



    public Location(int location_id, User user_id, String address, String city, String zip, int state_id, BigDecimal standard_rate, BigDecimal weekend_rate) {
        this.location_id = location_id;
        this.user_id = user_id;
        this.address = address;
        this.city = city;
        this.postal_code = zip;
        this.state_id = state_id;
        this.standard_rate = standard_rate;
        this.weekend_rate = weekend_rate;
    }
    public Location(){

    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public int getState_id() {
        return state_id;
    }

    public void setState_id(int state_id) {
        this.state_id = state_id;
    }

    public int getLocation_id() {
        return location_id;
    }
    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String setPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public int getStateId() {
        return state_id;
    }

    public void setStateId(int state_id) {
        this.state_id = state_id;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return location_id == location.location_id && state_id == location.state_id && Objects.equals(user_id, location.user_id) && Objects.equals(address, location.address) && Objects.equals(city, location.city) && Objects.equals(postal_code, location.postal_code) && Objects.equals(standard_rate, location.standard_rate) && Objects.equals(weekend_rate, location.weekend_rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location_id, user_id, address, city, postal_code, state_id, standard_rate, weekend_rate);
    }
}
