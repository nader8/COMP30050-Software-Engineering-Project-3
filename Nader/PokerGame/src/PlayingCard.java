
public class PlayingCard {

    // declare variables
    static public final char HEARTS = 'H';
    static public final char CLUBS = 'C';
    static public final char DIAMONDS = 'D';
    static public final char SPADES = 'S';
    private String typrOfCard;
    private char suitOfCard;
    private String faceValue;
    private int gameValue;

    /**
     * constructor to initialize variables
     *
     * @param typrOfCard String
     * @param suitOfCard char
     * @param faceValue String
     * @param gameValue int
     */
    public PlayingCard(String typrOfCard, char suitOfCard, String faceValue, int gameValue) {
        this.typrOfCard = typrOfCard;
        this.suitOfCard = suitOfCard;
        this.faceValue = faceValue;
        this.gameValue = gameValue;
    }

    /**
     * suit of card accessor
     *
     * @param suitOfCard char
     */
    public void setSuitOfCard(char suitOfCard) {
        this.suitOfCard = suitOfCard;
    }

    /**
     * face value accessor
     *
     * @param faceValue String
     */
    public void setFaceValue(String faceValue) {
        this.faceValue = faceValue;
    }

    /**
     * game value accessor
     *
     * @param gameValue int
     */
    public void setGameValue(int gameValue) {
        this.gameValue = gameValue;
    }

    // suit of card mutator
    public char getSuitOfCard() {
        return suitOfCard;
    }

    /**
     * face value mutator
     *
     * @return String
     */
    public String getFaceValue() {
        return faceValue;
    }

    /**
     * game value mutator
     *
     * @return int
     */
    public int getGameValue() {
        return gameValue;
    }

    /**
     * return type and suit of card to descripe it
     *
     * @return String
     */
    public String toString() {
        return typrOfCard + suitOfCard;
    }

    /**
     * main method to test the class
     */
    public static void main(String[] args) {
        PlayingCard[] _52cards = new PlayingCard[52]; // creat array of lenght 52
        int cards = 0; // decrease number of stored cards
        //Hearts 13 card
        _52cards[cards++] = new PlayingCard("A", PlayingCard.HEARTS, "1", 14);
        _52cards[cards++] = new PlayingCard("K", PlayingCard.HEARTS, "K", 13);
        _52cards[cards++] = new PlayingCard("Q", PlayingCard.HEARTS, "Q", 12);
        _52cards[cards++] = new PlayingCard("J", PlayingCard.HEARTS, "J", 11);
        for (int i = 10; i >= 2; i--) {
            _52cards[cards++] = new PlayingCard(String.valueOf(i), PlayingCard.HEARTS, String.valueOf(i), i);
        }
        //Clubs 13 card
        _52cards[cards++] = new PlayingCard("A", PlayingCard.CLUBS, "1", 14);
        _52cards[cards++] = new PlayingCard("K", PlayingCard.CLUBS, "K", 13);
        _52cards[cards++] = new PlayingCard("Q", PlayingCard.CLUBS, "Q", 12);
        _52cards[cards++] = new PlayingCard("J", PlayingCard.CLUBS, "J", 11);
        for (int i = 10; i >= 2; i--) {
            _52cards[cards++] = new PlayingCard(String.valueOf(i), PlayingCard.CLUBS, String.valueOf(i), i);
        }
        //Diamond 13 card
        _52cards[cards++] = new PlayingCard("A", PlayingCard.DIAMONDS, "1", 14);
        _52cards[cards++] = new PlayingCard("K", PlayingCard.DIAMONDS, "K", 13);
        _52cards[cards++] = new PlayingCard("Q", PlayingCard.DIAMONDS, "Q", 12);
        _52cards[cards++] = new PlayingCard("J", PlayingCard.DIAMONDS, "J", 11);
        for (int i = 10; i >= 2; i--) {
            _52cards[cards++] = new PlayingCard(String.valueOf(i), PlayingCard.DIAMONDS, String.valueOf(i), i);
        }
        //spades 13 card
        _52cards[cards++] = new PlayingCard("A", PlayingCard.SPADES, "1", 14);
        _52cards[cards++] = new PlayingCard("K", PlayingCard.SPADES, "K", 13);
        _52cards[cards++] = new PlayingCard("Q", PlayingCard.SPADES, "Q", 12);
        _52cards[cards++] = new PlayingCard("J", PlayingCard.SPADES, "J", 11);
        for (int i = 10; i >= 2; i--) {
            _52cards[cards++] = new PlayingCard(String.valueOf(i), PlayingCard.SPADES, String.valueOf(i), i);
        }
        // print 52 card by using toString
        for (PlayingCard i : _52cards) {
            System.out.println(i.toString());
        }
    }
}