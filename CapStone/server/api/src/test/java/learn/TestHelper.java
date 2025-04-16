package learn;

import learn.models.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TestHelper {
    public static User makeUser() {
        return new User(0, "testurl", "testfirstname", "testlastname", "test", "password", "testemail@test.com", LocalDate.now());
    }
    public static User makeUserExisting() {
        return new User(1, "testurl", "testfirstname", "testlastname", "test", "password", "testemail@test.com", LocalDate.now());
    }
    public static UserMoods makeUserMood(){
        return new UserMoods(0, 1, 4, "anothertest", LocalDate.now());
    }
    public static UserMoods makeUserMoodExisting(){
        return new UserMoods(1, 1, 1, "anothertest", LocalDate.now());
    }
    public static Media makeMedia(){
        return new Media(1, 1, "video", "testUrl","testDescription");
    }
    public static List<UserMoods> makeUserMoodsList(){
        return List.of(
                new UserMoods(1, 1, 2, "testmoodnotes", LocalDate.now()),
                new UserMoods(2, 1, 3, "testmoodnotes", LocalDate.now()),
                new UserMoods(3, 1, 4, "testmoodnotes", LocalDate.now())
        );
    }

    public static MoodVice makeMoodVice(){
        return new MoodVice(0,"testTitle3", "testbody800",  makeUserExisting().getUserId(), 2, "testurl", LocalDate.of(2024,1,2));
    }
    public static MoodVice makeMoodViceExisting(){
        return new MoodVice(1,"testTitle3", "testbody3",  makeUserExisting().getUserId(), 2, "testurl", LocalDate.of(2024,1,2));
    }

    public static Reply makeReply(){
        return new Reply(0,"replyTitle", "replyBody", makeUserExisting().getUserId(), 2, makeMoodViceExisting().getMoodViceId(), LocalDate.now());
    }
    public static Reply ReplyExisting(){
        return new Reply(1,"replyTitle", "replyBody", makeUserExisting().getUserId(), 2, makeMoodViceExisting().getMoodViceId(), LocalDate.now());
    }
}
