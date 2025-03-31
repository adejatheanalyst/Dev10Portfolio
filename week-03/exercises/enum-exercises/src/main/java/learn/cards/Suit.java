package learn.cards;

public enum Suit {
    HEARTS ("Hearts"),
    CLUBS ("Clubs"),
    SPADES ("Spades"),
    DIAMONDS ("Diamonds");

    private final String suitName;


    Suit(String suitName) {
        this.suitName = suitName;
    }

    public String getSuitName() {
        return suitName;
    }
}