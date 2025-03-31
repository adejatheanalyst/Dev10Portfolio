public class Exercise11 {

    public static void main(String[] args) {
        // MANAGER FEATURE REQUESTS
        // You have three managers: A, B, and C.
        // Occasionally, they ask you to add features to your company software.
        // Use if/else statements to enforce the following rules:
        // - If all three ask for the feature, print "Feature in progress."
        // - If any two of the three ask, print "Adding feature to schedule."
        // - If only one of the three ask, print "Going to hold off for a bit."
        // - If none of the managers ask, print "Nothing to do..."

        boolean managerAAsked = false;
        boolean managerBAsked = false;
        boolean managerCAsked = false;

        // 1. Add decisions statements to cover all scenarios.
        if (managerAAsked && managerBAsked && managerCAsked){
            System.out.println("Feature in progress.");
        } else if ((managerAAsked && managerBAsked)|| (managerAAsked && managerCAsked) || (managerBAsked && managerCAsked)) {
            System.out.println("Adding feature to schedule.");
        } else if (managerAAsked || managerBAsked || managerCAsked) {
            System.out.println("Going to hold off for a bit.");
        }else {
            System.out.println("Nothing to do...");
        }
        // 2. Change manager variables to test all scenarios.
    }
}
