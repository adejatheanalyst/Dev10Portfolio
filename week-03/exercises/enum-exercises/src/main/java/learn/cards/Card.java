package learn.cards;


public class Card {

    // 1. Add a Suit and Rank field to the Card class.
private final Suit suit;
private final Rank rank;
    // 2. Add a constructor that accepts a Suit and Rank and sets the appropriate fields.

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }
    // 3. Add getters for both suit and rank.

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    // 4. Complete the getName method.
    // Given a card's suit and suit
    public String getName() {
//     String rankName =
//        switch (rank){
//         case ACE -> "Ace";
//            case TWO -> "2";
//            default -> "example";
//        };
//        String suitName = suit.name().substring(0,1).toUpperCase() + suit.name().substring(1).toLowerCase();


        // "[rank] of [suit]"

        // Examples:
        // Ace of Clubs
        // 5 of Diamonds
        // King of Hearts
        // 9 of Spades
        // Note: it's unlikely you'll be able to use the enum name directly since enum naming conventions
        // don't match the required output.
        return getRank().getName() + " of " + getSuit().getSuitName();
    }

    public int getPoints(){
        return getRank().getValue();
    }

}
