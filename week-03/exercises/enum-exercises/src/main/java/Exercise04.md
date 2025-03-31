# Exercise04

(Hint: You are not required to make the `Rank` enum stateful, but it may make some tasks easier. 
For example, track a numeric value for a rank to help decide which rank is bigger.)

Two-card Poker is a Poker variant with simplified rules. A hand consists of two cards. See `learn.poker.TwoCardHand`.

In two-card poker, there are no straights and no flushes. Scoring is based on pairs, then high card, then high card with
a tie-breaker. 

Summarized:

- A pair always beats a non-pair.
- If two hands have pairs, the highest pair wins.
- If two hands have the same pair, it's a tie.
- If two hands don't have pairs, the highest card wins.
- If two hands have the same high card, the highest second card wins.
- If two hands have the same high/low card, it's a tie.
- Aces are high. e.g. They are worth more than a King. They're not worth 1.

`TwoCardHand` implements the `Comparable<TwoCardHand>` interface. Its only method is `compareTo`, 
which ranks another `TwoCardHand` as smaller, the same as, or greater than another `TwoCardHand`. 

1. Complete the `compareTo` method using two-card poker scoring rules.
2. Find the `TwoCardHandTest` class in `test/java/learn/poker`. 
3. Complete the tasks inside.