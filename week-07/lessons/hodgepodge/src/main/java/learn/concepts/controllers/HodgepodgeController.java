package learn.concepts.controllers;

import learn.concepts.SheepValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HodgepodgeController {
    static int sheepCount = 0;
    static ArrayList<String> todos = new ArrayList<>(
            List.of("Feed the cat", "Walk the dog", "Water the plants")
    );

    @GetMapping("/name")
    public String doGet(){
        return "Adeja";
    }

    @GetMapping("/current/time")
    public LocalDateTime doGetTime(){
        return LocalDateTime.now();
    }

    @GetMapping("/greet/{name}")
    public String doGetGreet(@PathVariable String name){
        return "Hello, " + name + "!";
    }
    @PutMapping("/sheep")
    public void doPutSheep() {
        sheepCount++;
    }
    @GetMapping("/sheep")
    public int doGetSheep() {
        return sheepCount;
    }

    @PutMapping("/sheep/{count}")
    public void doPutSheep(@PathVariable int count) {
       sheepCount += count;
    }

    @PostMapping("/sheep")
    public void doPostSheep(@RequestBody SheepValue value) {
        sheepCount += SheepValue.getAmount();
    }

    @DeleteMapping("/sheep")
    public void doDeleteSheep() {
        sheepCount -= 1;
    }

    @GetMapping("/todo")
    public ArrayList<String> doGetTodo() {
        if (todos == null) {
            todos = new ArrayList<>();
        }
        return todos;
    }
    @PutMapping("/todo")
    public void doPutToDo(@RequestBody List<String> items) {
        todos.addAll(items);
    }

    @PutMapping("/todo/{item}")
    public void doPutToDoItem(@PathVariable String item) {
        todos.add(item);
    }
    @DeleteMapping("/todo/{index}")
    public ResponseEntity<String> doDeleteToDoItem(@PathVariable int index) {
        if (index >= 0 && index < todos.size()) {
           return new ResponseEntity<>(todos.remove(index), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

@PostMapping("/todo)")
    public ResponseEntity<String> replaceList(@RequestBody List<String> items) {
        todos = new ArrayList<>(items);
        return new ResponseEntity<>("Replaced", HttpStatus.OK);
    }

}
