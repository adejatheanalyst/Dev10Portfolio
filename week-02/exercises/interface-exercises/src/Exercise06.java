public class Exercise06 {

    public static void main(String[] args) {
        // 1. Replace `null` with a new BankAccount. Initialize its balance to zero.
        MoneyStorage storage = new BankAccount(0.0, "154548664548");

        // 2. Run Exercise06.
        // The only output allowed is: Success!
        // For other outputs, look at the message and fix the issue.
        verifyBankAccountRules(storage);
    }

    static void verifyBankAccountRules(MoneyStorage account) {

        if (account == null) {
            System.out.println("account cannot be null");
            return;
        }

        if (account.getBalance() != 0.0) {
            System.out.println("account balance should start at 0.0");
            return;
        }

        if (account.deposit(-100.00)) {
            System.out.println("negative deposits are not allowed");
            return;
        }

        account.deposit(100.00);
        if (!Exercise04.withinThreshold(100.0, account.getBalance(), 0.01)) {
            System.out.println("when 100.0 is deposited, balance should be 100.0");
            return;
        }

        double result = account.withdraw(50.0);
        if (!Exercise04.withinThreshold(50.0, result, 0.01)) {
            System.out.println("when 50.0 is withdrawn, its return value should be 50.0");
            return;
        }

        if (!Exercise04.withinThreshold(50.0, account.getBalance(), 0.01)) {
            System.out.println("when 50.0 is withdrawn, balance should be 50.0");
            return;
        }

        result = account.withdraw(60.0);
        if (!Exercise04.withinThreshold(60.0, result, 0.01)) {
            System.out.println("when 60.0 is withdrawn from a 50.0 balance, its return value should be 60.0 (can overdraw)");
            return;
        }

        if (!Exercise04.withinThreshold(-10.0, account.getBalance(), 0.01)) {
            System.out.println("when 60.0 is withdrawn from a 50.0 balance, balance should be -10.0");
            return;
        }

        System.out.println("Success!");
    }
}
