public class Color1 {
    public static void main(String[] args) {
        System.out.println(Color.BLUE.getTraditionalCompliment());
        System.out.println(Color.YELLOW.getSpanishName());
        System.out.println(Color.findByHex("FF0000"));
        System.out.println(Color.ORANGE.getTraditionalCompliment().getSpanishName());
        System.out.println(Color.findByHex("ffA500"));
    }
}
