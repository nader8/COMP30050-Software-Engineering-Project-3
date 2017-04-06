
public class PokerPlayer {

    private final int magicNumber = 5; // number of hand cards
    private DeckOfCards deckOfCards; // deck of cards
    private HandOfCards hand; // hand cards

    /**
     * Constructor to initialize variables
     */
    public PokerPlayer() {
        hand = new HandOfCards();
        deckOfCards = hand.getDeckOfCards();
    }

    /**
     * discard method return number of discarded
     * 
     * @return int
     */
    public int discard() {
        int discarded = 0;
        for (int i = 0; i < magicNumber; i++) {
            if (hand.getDiscardProbability(i) > 0 && discarded < 3) { // card probability > 0 and discarded less than 3 
                hand.replace(i, deckOfCards.dealNext());
                hand.sort();
                discarded++ ;
            }
        }
        return discarded;
    }

    // main class to test code
    public static void main(String[] args) {
        PokerPlayer poker = new PokerPlayer(); // poker player object
        System.out.println(poker.hand + "\tHand Value\t" + poker.hand.getGameValue()); // print hand value before discard
        System.out.println(poker.discard()); // print number od discarded (Up to 3)
        System.out.println(poker.hand + "\tHand Value\t" + poker.hand.getGameValue()); // print hand value after discard
    }

}