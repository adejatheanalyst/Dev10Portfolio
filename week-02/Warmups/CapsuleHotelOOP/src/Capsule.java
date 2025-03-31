public class Capsule {

    //    In a your-classwork-dir/week-02/warmups folder, use intellij to make a new project.
//    In it, create a class called CapsuleHotelOOP and another class called Capsule.
//    The Capsule class should have one String field called guestName.
//    Give it the following methods: checkIn that accepts a guestName string and checks them in,
//    checkOut that sets the guestName field to null,
//    isVacant that returns true if the guestName field is null and false otherwise,
//    and isOccupied that returns the opposite boolean of isVacant.
//    Run some basic tests on this from the CapsuleHotelOOP's main method to verify that they are working.
//    Exercise each of its methods.
    //1. Create Fields
    private String guestName;

    // 2. constructors
    public Capsule(String guestName) {
        this.guestName = guestName;}

    public Capsule() {
    }

    // 3.getters
    public String getGuestName() {
        return guestName;
    }

    //4. Setters
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }
//5. Write methods

    // check in Method
    public void checkIn(String guestName) {
        if (isVacant()) {
//                    if (this.guestName == null) {
            setGuestName(guestName);
            System.out.println(guestName + " checked into the capsule.");
        } else {
            System.out.println("You cant check into this capsule");
        }
    }

    // check out method
    public void checkOut() {
        if (this.guestName != null) {
            System.out.println(guestName + " checked out of Capsule.");
            setGuestName(null);
        } else {
            System.out.println("The capsule is already vacant");
        }
    }

    // isVacant Method
    public boolean isVacant() {
        if (guestName == null) {
            return true;
        } else {
            return false;
        }
    }

    //isOccupied method
    public boolean isOccupied() {
        return !isVacant();
    }
}







