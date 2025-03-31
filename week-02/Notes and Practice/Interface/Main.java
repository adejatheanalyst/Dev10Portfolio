public class Main {
    public static void main(String[] args) {
Rabbit rabbit = new Rabbit();
Fish fish = new Fish();
Hawk hawk = new Hawk();

rabbit.flee();
fish.flee();
hawk.hunt();
fish.hunt();
    }
}
