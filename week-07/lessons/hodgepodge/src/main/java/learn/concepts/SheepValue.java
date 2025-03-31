package learn.concepts;

import java.util.ArrayList;
import java.util.List;

public class SheepValue {

    private static int amount;
    static int sheepCount = 0;
    static ArrayList<String> todos = new ArrayList<>(
            List.of("Feed the cat", "Walk the dog", "Water the plants")
    );

    public static int getSheepCount() {
        return sheepCount;
    }

    public static void setSheepCount(int sheepCount) {
        SheepValue.sheepCount = sheepCount;
    }

    public static ArrayList<String> getTodos() {
        return todos;
    }

    public static void setTodos(ArrayList<String> todos) {
        SheepValue.todos = todos;
    }

    public static int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

