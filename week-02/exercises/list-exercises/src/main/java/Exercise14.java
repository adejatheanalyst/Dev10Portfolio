import learn.BoardGame;
import learn.Music;

import java.util.ArrayList;
import java.util.Arrays;
public class Exercise14 {
    public static void main(String[] args) {

        //    1. Create a class to represent something that interests you. If you're a collector, maybe the thing you collect?
//    Ideas: garden plants, video game characters, novelists, Studio Ghibli movies, the tallest buildings in the world...
//    Add appropriate fields, getters and setters, and constructors.
//            2. Create a second class, `Exercise14`, and add a `main` method.
//3. Create an `ArrayList<T>` to hold instances of your new class.
        ArrayList<Music> music = new ArrayList<>();
//            4. Instantiate several objects and add them to the list.

        Music hiphop = new Music("Hip Hop", "Kendrick Lamar", 10);
        music.add(hiphop);
        Music pop = new Music("POP", "Britney Spears", 6);
        music.add(pop);
        Music kpop = new Music("K-Pop", "BTS", 9);
        music.add(kpop);
        Music rnb = new Music("R&B", "Beyonce", 10);
        music.add(rnb);
        Music soul = new Music("Soul", "Musiq Soulchild", 10);
        music.add(soul);
        Music rock = new Music("Rock", "Panic at the Disco", 7);
        music.add(rock);
        Music under = new Music("Underground", "Jcole", 4);
        music.add(under);

        listAllMusic(music);
    }
//5. Loop through the instances and print their values.
public static void listAllMusic (ArrayList<Music> music){
    for (Music music1  : music) {
        System.out.println(music1);
//        System.out.printf("%s: \n %s: \n %s: ",
//                music1.getGenre(),
//                music1.getArtist(),
//                music1.getRating());

    }
}



}
