package learn.cards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    // 1. Instantiate cards with the appropriate suit and rank for each test.
    // 2. Run the tests and confirm they pass. Do NOT change existing assertions.
    // 3. Add a couple more tests to confirm everything is working as intended.

    @Test
    void shouldNameTwoOfHearts() {
        // TODO: instantiate Card with arguments.
        Card card = new Card(Suit.HEARTS, Rank.TWO);
        assertEquals("2 of Hearts", card.getName());
    }

    @Test
    void shouldNameQueenOfDiamonds() {
        // TODO: instantiate Card with arguments.
        Card card = new Card(Suit.DIAMONDS, Rank.QUEEN);
        assertEquals("Queen of Diamonds", card.getName());
    }

    @Test
    void shouldNameTenOfClubs() {
        // TODO: instantiate Card with arguments.
        Card card = new Card(Suit.CLUBS, Rank.TEN);
        assertEquals("10 of Clubs", card.getName());
    }

    @Test
    void shouldNameAceOfSpades() {
        // TODO: instantiate Card with arguments.
        Card card = new Card(Suit.SPADES, Rank.ACE);
        assertEquals("Ace of Spades", card.getName());
    }
}