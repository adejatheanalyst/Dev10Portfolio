public class BankAccount implements MoneyStorage {
//    1. Add a new class to the project named `BankAccount`.
//            2. `BankAccount` must implement `MoneyStorage`.
//            3. Complete the implementation. Add fields, constructors, and getters as required.
//            (Refer to `Mortgage` for inspiration, but with a positive balance.)
//            5. Rules:
//            - Deposits must be positive values.
//    - Can overdraw up to -25.00 dollars, but no lower.
//            (The balance is allowed to go negative.)
//}

    private double balance;
    private String accountNumber;

    public BankAccount(double startingBalance, String accountNumber) {
        this.balance = -startingBalance;
        this.accountNumber = accountNumber;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getDescription() {
        return String.format("Account Number #%s", accountNumber);
    }

    public String getAccountNumber() {
        return String.format("Account Number #%s", accountNumber);
    }

    @Override
    public boolean deposit(double amount) {
        if (amount > 0.0) {
            balance += amount;
            return true;
        }
        return false;
    }

    @Override
    public double withdraw(double amount) {
        if (balance - amount < -25.00) {
//        return 0.0;
            balance = -25;
            return 25 + balance;
        } else {
            balance -= amount;
            return amount;
        }
    }
}


