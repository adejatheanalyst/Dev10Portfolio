package learn.cards;

public enum Rank {
    TWO(2,"2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(11, "Jack"),
    QUEEN(12, "Queen"),
    KING(13, "King"),
    ACE(14, "Ace");
private final int value;
private final String rankName;

//    Rank(String displayRank) {
//        this.displayRank = displayRank;


    Rank(int value, String rankName) {
        this.value = value;
        this.rankName = rankName;
    }

    public String getName() {
        return rankName;
    }

    public int getValue() {
        return value;
    }

}




