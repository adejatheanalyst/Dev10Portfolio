package learn.SulSulOverFlow;

import learn.SulSulOverFlow.models.Answers;
import learn.SulSulOverFlow.models.Questions;
import learn.SulSulOverFlow.models.User;

import java.time.LocalDate;

public class TestHelper {
    public static User makeUser(){
        return new User(1, "alice", "password1");
    }

    public static Questions makeQuestion(){
        return new Questions(1, "What does SulSul even mean?", "I want to know what sul sul means. Ive been playing sims for years", 1);
    }
    public static Answers makeAnswer(){
        return new Answers(1, "42", 1, 1);
    }




}
