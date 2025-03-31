public class Exercise12 {

    public static void main(String[] args) {
        // 1. Change likesCookies and isBlue to make mayBeCookieMonster true.
        // Don't change the assignment to mayBeCookieMonster.
        boolean likesCookies = true;
        boolean isBlue = true;

        boolean mayBeCookieMonster = likesCookies && isBlue;

        System.out.println("Cookie Monster?: " + mayBeCookieMonster);
    }
}
