package learn.poker;

import learn.cards.Card;
import learn.cards.Rank;
import learn.cards.Suit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoCardHandTest {

    // 1. Uncomment the tests below.
    // 2. Instantiate hands with the appropriate suit and rank for each test.
    // 3. Run the tests and confirm they pass. Do NOT edit existing assertions.
    // 4. Add a couple more tests to confirm everything is working as intended.


    @Test
    void twoQueensShouldBeatTwo10s() {
        // TODO: instantiate Cards and TwoCardHands with appropriate arguments
        TwoCardHand queenQueen = new TwoCardHand(new Card(Suit.SPADES, Rank.QUEEN), new Card(Suit.HEARTS, Rank.QUEEN));
        TwoCardHand tenTen = new TwoCardHand(new Card(Suit.SPADES, Rank.TEN), new Card(Suit.HEARTS, Rank.TEN));

        int result = queenQueen.compareTo(tenTen);

        assertTrue(result > 0);
    }


    @Test
    void two9sShouldBeatJack10() {
        // TODO: instantiate Cards and TwoCardHands with appropriate arguments
        TwoCardHand nineNine = new TwoCardHand(new Card(Suit.CLUBS, Rank.NINE), new Card(Suit.SPADES, Rank.NINE));
        TwoCardHand jackTen = new TwoCardHand(new Card(Suit.DIAMONDS, Rank.JACK), new Card(Suit.SPADES, Rank.TEN));
        assertTrue(nineNine.compareTo(jackTen) > 0);
    }

    @Test
    void queen4ShouldBeatJack10() {
        // TODO: instantiate Cards and TwoCardHands with appropriate arguments
        TwoCardHand queenFour = new TwoCardHand(new Card(Suit.DIAMONDS, Rank.QUEEN), new Card(Suit.DIAMONDS, Rank.FOUR));
        TwoCardHand jackTen = new TwoCardHand(new Card(Suit.CLUBS, Rank.JACK), new Card(Suit.CLUBS, Rank.TEN));
        assertTrue(queenFour.compareTo(jackTen) > 0);
    }

    @Test
    void queen5ShouldBeatQueen3() {
        // TODO: instantiate Cards and TwoCardHands with appropriate arguments
        TwoCardHand queenFive = new TwoCardHand(new Card(Suit.DIAMONDS, Rank.QUEEN), new Card(Suit.DIAMONDS, Rank.FIVE));
        TwoCardHand queenThree = new TwoCardHand(new Card(Suit.DIAMONDS, Rank.QUEEN), new Card(Suit.HEARTS, Rank.THREE));
        assertTrue(queenFive.compareTo(queenThree) > 0);
    }

    @Test
    void two5sShouldTieTwo5s() {
        // TODO: instantiate Cards and TwoCardHands with appropriate arguments
        TwoCardHand firstFiveFive = new TwoCardHand(new Card(Suit.HEARTS, Rank.FIVE), new Card(Suit.HEARTS, Rank.FIVE));
        TwoCardHand secondFiveFive = new TwoCardHand(new Card(Suit.DIAMONDS, Rank.FIVE), new Card(Suit.SPADES, Rank.FIVE));
        assertEquals(0, firstFiveFive.compareTo(secondFiveFive));
    }

    @Test
    void jack4ShouldTieJack4() {
        // TODO: instantiate Cards and TwoCardHands with appropriate arguments
        TwoCardHand firstJackFour = new TwoCardHand(new Card(Suit.SPADES, Rank.JACK), new Card(Suit.DIAMONDS, Rank.JACK));
        TwoCardHand secondJackFour = new TwoCardHand(new Card(Suit.HEARTS, Rank.JACK), new Card(Suit.CLUBS, Rank.JACK));
        assertEquals(0, firstJackFour.compareTo(secondJackFour));
    }

}

