public class Person {

    private final String firstName;
    private final String lastName;
    private MoneyStorage myWallet;


    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.myWallet = new Wallet();
    }

    //getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public MoneyStorage getMyWallet() {
        return myWallet;
    }

    //methods

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

    public void deposit(double amount) {
        myWallet.deposit(amount);
    }

    public String getDescription() {
        return getFirstName() + "'s Wallet";
    }

        public double getBalance () {
            return myWallet.getBalance();
        }
    }




