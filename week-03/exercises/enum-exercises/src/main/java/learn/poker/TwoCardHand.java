package learn.poker;

import learn.cards.Card;

public class TwoCardHand implements Comparable<TwoCardHand> {

    private final Card one;
    private final Card two;

    public TwoCardHand(Card one, Card two) {
        this.one = one;
        this.two = two;
    }

    public Card getOne() {
        return one;
    }

    public Card getTwo() {
        return two;
    }


//    private boolean isPair(){
//    return one.getRank() == two.getRank();}



    @Override
    public int compareTo(TwoCardHand o) {
        // 1. Complete the compareTo method.
        // If the current TwoCardHand(`this`) has a lower score than the TwoCardHand parameter, compareTo returns
        // an int less than 0.
        // If `this` has a higher score than the TwoCardHand parameter, compareTo returns an int greater than 0.
        // If `this` and the TwoCardHand parameter have the same score, compareTo returns 0.
        // See Exercise04.md for scoring rules.


        // establish variables for each instance
        int thisHigh = Math.max(one.getPoints(), two.getPoints());
        int thisLow = Math.min(one.getPoints(), two.getPoints());

        int otherHigh = Math.max(o.one.getPoints(), o.two.getPoints());
        int oLow = Math.min(o.one.getPoints(), o.two.getPoints());

        // Check for pairs
        boolean thisIsPair = one.getPoints() == two.getPoints();
        boolean oIsPair = o.one.getPoints() == o.two.getPoints();

        // Pair beats non-pair
        if (thisIsPair && !oIsPair) return 1;
        if (!thisIsPair && oIsPair) return -1;

        // Both pairs: Compare pair values
        if (thisIsPair) { //if player one has two of the same cards and second player does as well, compare values
            return Integer.compare(thisHigh, otherHigh); // if high card is less than low card return < 0
                                                        // if high card is greater than low card > 0
        }

        // No pairs: Compare highest cards
        if (thisHigh != otherHigh) { // if high cards are not equal compare their values
            return Integer.compare(thisHigh, otherHigh); //if high card is less than other
        }

        // Tie on highest cards: Compare second-highest cards
        return Integer.compare(thisLow, oLow);
    }
}
