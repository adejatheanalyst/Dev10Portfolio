public class Throw {
    public int addPositiveValues(int a, int b) throws NegativeValueException { // 4. Acknowledge the checked exception.
        // 1. Detect problem.
        if (a < 0 || b < 0) {
            // 2. Instantiate exception.
            NegativeValueException ex = new NegativeValueException("Arguments may not be negative.");
            // 3. Throw. Immediately terminates the method. No value is returned.
            throw ex;
        }
        return a + b;
    }
}


//        public String addDr(String name) {
//            // 1. Detect problem.
//            if (name == null || name.isEmpty()) {
//                // 2. Instantiate exception.
//                RequiredStringException ex = new RequiredStringException("`name` is required.");
//                // 3. Throw. Immediately terminates the method. No value is returned.
//                throw ex;
//            }
//            return "Dr. " + name;
//        }
//
//    }
//
//}
