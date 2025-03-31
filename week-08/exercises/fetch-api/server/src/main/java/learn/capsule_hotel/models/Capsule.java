package learn.capsule_hotel.models;

import java.util.Objects;

public class Capsule {
    private int capsuleNumber;
    private String guestName;

    public Capsule() { }

    public Capsule(int capsuleNumber, String guestName) {
        this.capsuleNumber = capsuleNumber;
        this.guestName = guestName;
    }

    public int getCapsuleNumber() {
        return capsuleNumber;
    }

    public void setCapsuleNumber(int capsuleNumber) {
        this.capsuleNumber = capsuleNumber;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    @Override
    public String toString() {
        return "Capsule{" +
                "capsuleNumber=" + capsuleNumber +
                ", guestName='" + guestName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Capsule capsule = (Capsule) o;
        return capsuleNumber == capsule.capsuleNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(capsuleNumber);
    }
}
