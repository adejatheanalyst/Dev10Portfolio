public class Exercise04 {

    public static void main(String[] args) {
        // 1. Replace `null` with a new Vault. Initialize its balance to zero.
        MoneyStorage storage = new Vault(0.0, "James Bond Vault");

        // 2. Run Exercise04.
        // The only output allowed is: Success!
        // For other outputs, look at the message and fix the issue.
        verifyVaultRules(storage);
    }

    static void verifyVaultRules(MoneyStorage vault) {

        if (vault == null) {
            System.out.println("vault cannot be null");
            return;
        }

        if (vault.getBalance() != 0.0) {
            System.out.println("vault balance should start at 0.0");
            return;
        }

        if (vault.deposit(-100.00)) {
            System.out.println("negative deposits are not allowed");
            return;
        }

        vault.deposit(100.00);
        if (!withinThreshold(100.0, vault.getBalance(), 0.01)) {
            System.out.println("when 100.0 is deposited, balance should be 100.0");
            return;
        }

        double result = vault.withdraw(50.0);
        if (!withinThreshold(50.0, result, 0.01)) {
            System.out.println("when 50.0 is withdrawn, its return value should be 50.0");
            return;
        }

        if (!withinThreshold(50.0, vault.getBalance(), 0.01)) {
            System.out.println("when 50.0 is withdrawn, balance should be 50.0");
            return;
        }

        result = vault.withdraw(60.0);
        if (!withinThreshold(50.0, result, 0.01)) {
            System.out.println("when 60.0 is withdrawn from a 50.0 balance, its return value should be 50.0");
            return;
        }

        if (!withinThreshold(0.0, vault.getBalance(), 0.01)) {
            System.out.println("when the remaining balance is withdrawn, balance should be 0.0");
            return;
        }

        System.out.println("Success!");
    }

    static boolean withinThreshold(double expected, double actual, double thresold) {
        double delta = expected - actual;
        delta = Math.abs(delta);
        return delta < thresold;
    }
}
