package learn;

public class Music {
//    1. Create a class to represent something that interests you. If you're a collector, maybe the thing you collect?
//    Ideas: garden plants, video game characters, novelists, Studio Ghibli movies, the tallest buildings in the world...
//    Add appropriate fields, getters and setters, and constructors.
//            2. Create a second class, `Exercise14`, and add a `main` method.
//3. Create an `ArrayList<T>` to hold instances of your new class.
//            4. Instantiate several objects and add them to the list.
//5. Loop through the instances and print their values.
    public String genre;
    public String artist;
    public int rating;

    public Music(String genre, String artist, int rating) {
        this.genre = genre;
        this.artist = artist;
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    public String toString() {
        return "Music I {" +
                "Genre: " + genre + '\'' +
                ", Artist: " + artist +
                ", Rating: " + rating +
                 '\'' +
                '}';
    }


}
