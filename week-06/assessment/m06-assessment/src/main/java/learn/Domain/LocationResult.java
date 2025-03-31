package learn.Domain;

import learn.Model.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LocationResult<L> {
    private static ArrayList<String> messages = new ArrayList<String>();
    private List<Location> locationList;
    private Location location;


    public List<String> getErrorMessages() {
        return new ArrayList<>(messages);
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

    public static ArrayList<String> getMessages() {
        return messages;
    }

    public static void setMessages(ArrayList<String> messages) {
        LocationResult.messages = messages;
    }

    public static void addErrorMessage(String message) {
        messages.add(message);
    }
    public boolean isSuccess() {
        return messages.isEmpty();
    }
    public Location getLocation() {
            return location;
        }
        public void setLocation(Location location) {
            this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LocationResult<?> that = (LocationResult<?>) o;
        return Objects.equals(locationList, that.locationList) && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationList, location);
    }
}
