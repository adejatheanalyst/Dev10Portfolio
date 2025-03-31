public class Hero {

    private  String name;
    private  String symbol = "H ";
    private int x;
    private int y;

    // Create a hero with a name and an initial position.
    public Hero(String name, int x, int y, String symbol) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.symbol = symbol + " ";
    }

    // getters
    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
void setSymbol(String symbol){
        this.symbol = symbol;

}
    // movement
    public void moveLeft() {
        x--;
    }

    public void moveRight() {
        x++;
    }

    public void moveUp() {
        y--;
    }

    public void moveDown() {
        y++;
    }
}
