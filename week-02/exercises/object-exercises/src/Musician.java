public class Musician {

    private String name;
    private int rating;
    private boolean isOver;

    /**
     * @param name   The name of the musician.
     * @param rating A number representing how much a musician is loved relative to other musicians.
     */
    // constructor
    public Musician(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }
    //constructor
    public Musician(){
    }

//getters
    public String getName() {
        return name;
    }
    public int getRating(){return rating;}

    public String toLine(){
        return String.format("Musician name: " + name + " Rating: " + rating);
    }
//setters
    public void setName(String name){
        this.name = name;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
}
