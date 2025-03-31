public class Power {
//    //1. Add a class to the project named `Power` which represents a hero's super power.
//            2. Add one private field: `String name`.
//            3. Add a constructor that accepts a string name and sets the name field.
//4. Add a getter for `name`.

    private String name;

    //constructer
    public Power(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

