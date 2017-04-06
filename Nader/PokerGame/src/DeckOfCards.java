
import java.util.Random;

public class DeckOfCards {

    private int pointer = 0; //pointer to the position of the next card
    private final int sizeOfDeck = 52; // deck size 
    private final int squareSizeOfDeck = sizeOfDeck * sizeOfDeck; // number of swap the cards
    private PlayingCard[] deckOfCards = new PlayingCard[sizeOfDeck]; // array of deck

    /**
     * Constructor initialize array of deck
     */
    public DeckOfCards() {
        initalizeCards();
    }

    /**
     * initalizeCards method is initialize array of deck
     */
    public void initalizeCards() {
        int cards = 0; // decrease number of stored cards
        //Hearts 13 card
        for (int i = 14; i >= 2; i--) {
            if (i == 14) {
                deckOfCards[cards++] = new PlayingCard("A", PlayingCard.HEARTS, "1", 14);
            } else if (i == 13) {
                deckOfCards[cards++] = new PlayingCard("K", PlayingCard.HEARTS, "K", 13);
            } else if (i == 12) {
                deckOfCards[cards++] = new PlayingCard("Q", PlayingCard.HEARTS, "Q", 12);
            } else if (i == 11) {
                deckOfCards[cards++] = new PlayingCard("J", PlayingCard.HEARTS, "J", 11);
            } else {
                deckOfCards[cards++] = new PlayingCard(String.valueOf(i), PlayingCard.HEARTS, String.valueOf(i), i);
            }
        }
        //Clubs 13 card
        for (int i = 14; i >= 2; i--) {
            if (i == 14) {
                deckOfCards[cards++] = new PlayingCard("A", PlayingCard.CLUBS, "1", 14);
            } else if (i == 13) {
                deckOfCards[cards++] = new PlayingCard("K", PlayingCard.CLUBS, "K", 13);
            } else if (i == 12) {
                deckOfCards[cards++] = new PlayingCard("Q", PlayingCard.CLUBS, "Q", 12);
            } else if (i == 11) {
                deckOfCards[cards++] = new PlayingCard("J", PlayingCard.CLUBS, "J", 11);
            } else {
                deckOfCards[cards++] = new PlayingCard(String.valueOf(i), PlayingCard.CLUBS, String.valueOf(i), i);
            }
        }
        //Diamond 13 card
        for (int i = 14; i >= 2; i--) {
            if (i == 14) {
                deckOfCards[cards++] = new PlayingCard("A", PlayingCard.DIAMONDS, "1", 14);
            } else if (i == 13) {
                deckOfCards[cards++] = new PlayingCard("K", PlayingCard.DIAMONDS, "K", 13);
            } else if (i == 12) {
                deckOfCards[cards++] = new PlayingCard("Q", PlayingCard.DIAMONDS, "Q", 12);
            } else if (i == 11) {
                deckOfCards[cards++] = new PlayingCard("J", PlayingCard.DIAMONDS, "J", 11);
            } else {
                deckOfCards[cards++] = new PlayingCard(String.valueOf(i), PlayingCard.DIAMONDS, String.valueOf(i), i);
            }
        }
        //spades 13 card
        for (int i = 14; i >= 2; i--) {
            if (i == 14) {
                deckOfCards[cards++] = new PlayingCard("A", PlayingCard.SPADES, "1", 14);
            } else if (i == 13) {
                deckOfCards[cards++] = new PlayingCard("K", PlayingCard.SPADES, "K", 13);
            } else if (i == 12) {
                deckOfCards[cards++] = new PlayingCard("Q", PlayingCard.SPADES, "Q", 12);
            } else if (i == 11) {
                deckOfCards[cards++] = new PlayingCard("J", PlayingCard.SPADES, "J", 11);
            } else {
                deckOfCards[cards++] = new PlayingCard(String.valueOf(i), PlayingCard.SPADES, String.valueOf(i), i);
            }
        }
    }

    /**
     * shuffle method is shuffle array of cards by swap positions
     */
    public void shuffle() {
        pointer = 0; // return all cards 
        for (int shuffle = 0; shuffle < squareSizeOfDeck; shuffle++) { // number of shuffles
            int random1 = (int) (Math.random() * sizeOfDeck); // random access
            int random2 = (int) (Math.random() * sizeOfDeck); // random access
            // swap two cards
            PlayingCard temp = deckOfCards[random1];
            deckOfCards[random1] = deckOfCards[random2];
            deckOfCards[random2] = temp;
        }
    }

    /**
     * reset method is reinitailize the array of cards and shuffle it
     */
    public void reset() {
        pointer = 0; // return all cards
        initalizeCards(); // reinitialize cards 
        shuffle(); // shuffle cards
    }

    /**
     * dealNext method return next dealt card
     *
     * @return PlayingCard
     */
    public PlayingCard dealNext() {
        if (pointer < sizeOfDeck) { // if there is playing cards
            return deckOfCards[pointer++]; 
        } else { // if no playing cards
            return null;
        }
    }

    /**
     * returnCard method return card that discarded to game
     *
     * @param discarded PlayingCard
     */
    public void returnCard(PlayingCard discarded) {
        for (int i = 0; i < pointer; i++) { // search for card
            if (deckOfCards[i].toString().equals(discarded.toString())) {
                if (i == (pointer - 1)) { // if last discarded
                    pointer--;
                } else {
                    PlayingCard temp = deckOfCards[i];
                    deckOfCards[i] = deckOfCards[pointer-1];
                    deckOfCards[pointer-1] = temp;
                    pointer--;
                }
            }
        }
    }

    // main method to test class
    public static void main(String[] args) {
        
        DeckOfCards cards = new DeckOfCards();
        cards.shuffle();
        for (int i = 0; i < cards.sizeOfDeck+3; i++) {
            System.out.println(cards.dealNext());
        }
        
    }
}
