public class StringMethods {
    public static boolean startsWithDayOfWeek(String input){
//        return input.startsWith()
                return input.startsWith("Mon") ||
                        input.startsWith("Tue") ||
                        input.startsWith("Wed") ||
                        input.startsWith("Thu") ||
                        input.startsWith("Fri") ||
                        input.startsWith("Sat") ||
                        input.startsWith("Sun");
    }
    public static boolean containsDayOfWeek(String input){
      return  input.contains("Mon") ||
                input.contains("Tue") ||
                input.contains("Wed") ||
                input.contains("Thu") ||
                input.contains("Fri") ||
                input.contains("Sat") ||
                input.contains("Sun");

    }


    public static String removeVowelFromBetweenX(String input){
return input.replaceAll("x[aeiouAEIOU]x", "xx");

    }





}
