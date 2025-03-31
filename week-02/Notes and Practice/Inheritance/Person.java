public class Person {

    //parent class

    String first;
    String last;

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }
//constructor
    Person(String first, String last){
        this.first = first;
        this.last = last;

    }
    void showName(){
        System.out.println(this.first + " " + this.last);
    }


}
