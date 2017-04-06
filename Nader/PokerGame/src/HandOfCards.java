
import java.util.Arrays;

public class HandOfCards {

    private final int magicNumber = 5; // number of hand cards
    private PlayingCard[] hand; // array of hand cards
    private DeckOfCards cards; // object from deck of cards
    // kinds of hands values
    public final int ROYAL_FLUSH = 70000 ;
    public final int STRAIGHT_FLUSH = 50000 ;
    public final int FOUR_OF_KIND = 10000 ;
    public final int FULL_HOUSE = 6000 ;
    public final int FLUSH = 5000 ;
    public final int STRAIHGT = 4000 ;
    public final int THREE_OF_KIND = 1000 ;
    public final int TWO_PAIR = 500 ;
    public final int ONE_PAIR = 200 ;
    public final int HIGH_HAND = 100 ;

    public HandOfCards() {
        hand = new PlayingCard[magicNumber];
        
        cards = new DeckOfCards();
        cards.shuffle(); // shuffle cards

        // initalize 5 hand cards
        for(int i = 0 ; i < magicNumber ; i++ )
            hand[i] = cards.dealNext();

        // sort hand cards 
        sort();
    }
    
    /**
     * isRoyalFlush method return if hand is royal flush or not
     * 
     * @return boolean
     */
    public boolean isRoyalFlush(){
        if((hand[0].getFaceValue().equals("1")) && (hand[1].getFaceValue().equals("K")) && (hand[2].getFaceValue().equals("Q")) && (hand[3].getFaceValue().equals("J")) && (hand[4].getFaceValue().equals("10")) && isFlush()){
            return true;
        }
        return false ;
    }
    
    /**
     * isStraightFlush method return if hand is Straight lush or not
     * 
     * @return boolean
     */
    public boolean isStraightFlush() {
        return (isStraight() && isFlush()) ;
    }
    
    /**
     * isFourOfAKind method return if hand is four of kind or not
     * 
     * @return boolean
     */
    public boolean isFourOfAKind() {
        boolean flag = false;

        for (int i = 0; i < 2; i++) {
            int firstCard = hand[i].getGameValue();
            int counter = 1;
            for (int j = i+1 ; j < magicNumber; j++) {
                if (hand[j].getGameValue() == firstCard) 
                    counter++;
            }
            if (counter == 4) 
                return true;  
        }
        return flag;
    }
    
    /**
     * isThreeOfAKind method return if hand is three of kind or not
     * 
     * @return boolean
     */
    public boolean isThreeOfAKind(){
        boolean flag = false;

        for (int i = 0; i < 3; i++) {
            int firstCard = hand[i].getGameValue();
            int counter = 1;
            for (int j = i+1 ; j < magicNumber; j++) {
                if (hand[j].getGameValue() == firstCard) 
                    counter++;
            }
            if (counter == 3) 
                return true;  
        }
        return flag;
    }
    
    /**
     * isFullHouse method return if hand is full house or not
     * 
     * @return boolean
     */
    public boolean isFullHouse(){
        if( (hand[0].getGameValue() == hand[1].getGameValue()) && (hand[2].getGameValue() == hand[3].getGameValue()) && (hand[3].getGameValue() == hand[4].getGameValue()) )
            return true;
        if( (hand[0].getGameValue() == hand[1].getGameValue()) && (hand[1].getGameValue() == hand[2].getGameValue()) && (hand[3].getGameValue() == hand[4].getGameValue()) )
            return true;
        return false ;
    }
    
    /**
     * isStraight method return if hand is straight or not
     * 
     * @return boolean
     */
    public boolean isStraight(){
        boolean straighFlag = true;
        int firstCard = hand[0].getGameValue();
        for (int i = 1; i < magicNumber; i++) {
            if (hand[i].getGameValue() != --firstCard) 
                straighFlag = false;
        }
        return straighFlag;
    }
    
    /**
     * isFlush method return if hand is flush or not
     * 
     * @return boolean
     */
    public boolean isFlush(){
        boolean suitFlag = true;
        for (int i = 1; i < magicNumber; i++) 
            if (!(hand[i].getSuitOfCard() == hand[0].getSuitOfCard())) 
                suitFlag = false;
        return suitFlag;
    }
    
    /**
     * isTwoPair method return if hand is two pair or not
     * 
     * @return boolean
     */
    public boolean isTwoPair(){
        if (hand[0].getGameValue() == hand[1].getGameValue()
                && hand[2].getGameValue() == hand[3].getGameValue()) {
            return true;
        } else if ((hand[1].getGameValue() == hand[2].getGameValue())
                && (hand[3].getGameValue() == hand[4].getGameValue())) {
            return true;
        } else if ((hand[0].getGameValue() == hand[1].getGameValue())
                && (hand[3].getGameValue() == hand[4].getGameValue())) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * isOnePair method return if hand is one pair or not
     * 
     * @return boolean
     */
    public boolean isOnePair(){
        boolean flag = false;

        for (int i = 0; i < 3; i++) {
            int firstCard = hand[i].getGameValue();
            int counter = 1;
            for (int j = i+1 ; j < magicNumber; j++) {
                if (hand[j].getGameValue() == firstCard) 
                    counter++;
            }
            if (counter == 2) 
                return true;  
        }
        return flag;
    }
    
    /**
     * isHighHand method return if hand is high hand or not
     * 
     * @return boolean
     */
    public boolean isHighHand(){
        if(!isFlush() && !isFourOfAKind() && !isFullHouse() && !isOnePair() && !isRoyalFlush() && !isStraight() && !isStraightFlush() && !isThreeOfAKind() && !isTwoPair())
            return true;
        return false;
    }
    
    /**
     * sort method sort the hand of cards
     * 
     */
    public void sort() {

        boolean flag = true;   // set flag to true to begin first pass
        
        while (flag) {
            flag = false;    //set flag to false awaiting a possible swap
            for (int j = 0; j < magicNumber - 1; j++) {
                if (hand[j].getGameValue() < hand[j + 1].getGameValue()) // change to > for ascending sort
                {
                    PlayingCard temp = hand[j];                //swap elements
                    hand[j] = hand[j + 1];
                    hand[j + 1] = temp;
                    flag = true;              //shows a swap occurred  
                }
            }
        }
        
    }

    /**
     * getDeckOfCards method return object from DeckOfCards
     * 
     * @return DeckOfCards
     */
    public DeckOfCards getDeckOfCards() {
        return cards;
    }
    
    /**
     * getDiscardProbability method return probability of discard card 
     * 
     * @param cardPosition int
     * @return int
     */
    public int getDiscardProbability(int cardPosition) {
        if ( cardPosition > 4 || cardPosition < 0){ // invalid card position
            return -1 ;
        }
        
        if (isRoyalFlush() || isStraightFlush() || isFourOfAKind() || isStraight() || isFlush()) { // these cases no chance to get better card when change
            return 0;
        }else if(isFullHouse()){ // case full house hand
            return fullHouseProbability(cardPosition) ; 
        }else if (isThreeOfAKind()){ // case three of a kind hand
            return threeOfAKindProbability(cardPosition);
        } else if (isTwoPair()) { // case two pair hand
            return twoPairProbability(cardPosition);
        } else if (isOnePair()) { // case one pair hand
            return onePairProbability(cardPosition);
        } else if (isHighHand()) { // case high hand hand
            return highHandProbability(cardPosition);
        }
        return 0;
    }

    /**
     * fullHouseProbability method return probability of discard card in special case (Hand is full house)
     * 
     * @param cardPosition int
     * @return int
     */
    private int fullHouseProbability(int cardPosition) {
        int count = 0 ;
        for(int i = 0 ; i < magicNumber ; i++){ // search for number repeated two time
            if(i == cardPosition)
                continue;
            if(hand[cardPosition].getGameValue() == hand[i].getGameValue())
                count++;
        }
        if(count == 1){ // when change we have small chance to get four of a kind or lose full house and get three of kind
            return 10 ;
        }else{
            return 0 ; // if three repeat dont change you will lose full house
        }
    }

    /**
     * threeOfAKindProbability method return probability of discard card in special case (Hand is three of kind)
     * 
     * @param cardPosition int
     * @return int
     */
    private int threeOfAKindProbability(int cardPosition) { 
        int count = 0 ;
        for(int i = 0 ; i < magicNumber ; i++){ // search for number of repeated
            if(i == cardPosition)
                continue;
            if(hand[cardPosition].getGameValue() == hand[i].getGameValue())
                count++;
        }
        if(count == 0){ // when change we have small chance to get four of a kind but we will not lose three of kind
            return 20 ;
        }else{
            return 0 ; // if three repeat dont change you will lose three of kind
        }
    }

    /**
     * twoPairProbability method return probability of discard card in special case (Hand is two pair)
     * 
     * @param cardPosition int
     * @return int
     */
    private int twoPairProbability(int cardPosition) {
        int count = 0 ;
        for(int i = 0 ; i < magicNumber ; i++){ // search for number of repeated
            if(i == cardPosition)
                continue;
            if(hand[cardPosition].getGameValue() == hand[i].getGameValue())
                count++;
        }
        if(count == 0){ // when change we have small chance to get full house but we will not lose two pair
            return 40 ;
        }else if(count == 1){ // when change we have small chance to get three of a kind but we will  lose two pair
            return 30 ;
        }
        
        return 0 ; // no other probability
    }

    /**
     * onePairProbability method return probability of discard card in special case (Hand is one pair)
     * 
     * @param cardPosition int
     * @return int
     */
    private int onePairProbability(int cardPosition) {
        boolean flush = true ;
        int count = 0 ;
        for(int i = 0 ; i < magicNumber ; i++){ // search for number not repeated and if there chance to be flush
            if(i == cardPosition)
                continue;
            if(hand[cardPosition].getSuitOfCard() != hand[i].getSuitOfCard())
                flush = false ;
            
            if(hand[cardPosition].getGameValue() == hand[i].getGameValue())
                count++;
        }
        if (flush){ // // when change we have small chance to get flush 
            return 70 ;
        }else if(count == 0){ // when change we have small chance to get two pair but we will not lose one pair
            return 60 ;
        }else if(count == 1){ // when change we have small chance to get straight but we will lose one pair
            return 50 ;
        }
        
        return 0 ; // no other probability
    }

    /**
     * highHandProbability method return probability of discard card in special case (Hand is high hand)
     * 
     * @param cardPosition int
     * @return int
     */
    private int highHandProbability(int cardPosition) {
         boolean flush = true ;
        
        for(int i = 0 ; i < magicNumber ; i++){ // search for chance to be flush
            if(i == cardPosition)
                continue;
            if(hand[cardPosition].getSuitOfCard() != hand[i].getSuitOfCard())
                flush = false ;
            
            
        }
        if (flush){
            return 90 ; // when change we have small chance to get flush
        }else{
            return 80 ; // we may have any hand kind 
        }
    }
    
    /**
     * toString method return description for hand cards
     * 
     * @return String
     */
    public String toString(){
        String output = "";
        for (int i = 0 ; i < magicNumber ; i++ ) {
            output += hand[i] + " " ;
        }
        return output;
    }
    
    /**
     * replace method return description for hand cards
     * 
     * @param position int
     * @param newCard PlayingCard
     */
    public void replace(int position, PlayingCard newCard){
        if(position >= 0 && position <= 5 && newCard != null){
            hand[position] = newCard ;
        }
    } 
    
    /**
     * getGameValue method return value of hand depends on kind of hand and cards
     * 
     * @return int
     */
    public int getGameValue(){
        if(isRoyalFlush()){ // kind of hand is royal flush
            return ROYAL_FLUSH ;
        }else if(isStraightFlush()){
            return STRAIGHT_FLUSH + hand[0].getGameValue() + hand[1].getGameValue() + hand[2].getGameValue() + hand[3].getGameValue() + hand[4].getGameValue();
        }else if(isFourOfAKind()){ // kind of hand is four of kind
            if(hand[0].getGameValue() == hand[2].getGameValue())
                return FOUR_OF_KIND + (int)Math.pow((double)hand[0].getGameValue(),(double)4) ;
            else
                return FOUR_OF_KIND + (int)Math.pow((double)hand[2].getGameValue(),(double)4) ;
        }else if(isFullHouse()){ // kind of hand is full house
            if(hand[0].getGameValue() == hand[2].getGameValue())
                return FULL_HOUSE + (int)Math.pow((double)hand[0].getGameValue(),(double)3) + (int)Math.pow((double)hand[4].getGameValue(),(double)2)  ;
            else
                return FULL_HOUSE + (int)Math.pow((double)hand[0].getGameValue(),(double)2) + (int)Math.pow((double)hand[4].getGameValue(),(double)3)  ;
        }else if(isFlush()){ // kind of flush 
            return FLUSH + hand[0].getGameValue() + hand[1].getGameValue() + hand[2].getGameValue() + hand[3].getGameValue() + hand[4].getGameValue() ;
        }else if(isStraight()){ // kind od straight
            return STRAIHGT + hand[0].getGameValue() + hand[1].getGameValue() + hand[2].getGameValue() + hand[3].getGameValue() + hand[4].getGameValue() ;
        }else if(isThreeOfAKind()){ // kind of hand three of kind
            if(hand[0].getGameValue() == hand[2].getGameValue())
                return THREE_OF_KIND + (int)Math.pow((double)hand[0].getGameValue(),(double)3) ;
            else if(hand[1].getGameValue() == hand[3].getGameValue())
                return THREE_OF_KIND + (int)Math.pow((double)hand[1].getGameValue(),(double)3) ;
            else 
                return THREE_OF_KIND + (int)Math.pow((double)hand[2].getGameValue(),(double)3) ;
        }else if(isTwoPair()){ // kind of hand is two pair 
             if (hand[0].getGameValue() == hand[1].getGameValue()
                    && hand[2].getGameValue() == hand[3].getGameValue()) {
                return TWO_PAIR + (int)Math.pow((double)hand[0].getGameValue(),(double)2) + (int)Math.pow((double)hand[2].getGameValue(),(double)2);
            } else if ((hand[1].getGameValue() == hand[2].getGameValue())
                    && (hand[3].getGameValue() == hand[4].getGameValue())) {
                return TWO_PAIR + (int)Math.pow((double)hand[1].getGameValue(),(double)2) + (int)Math.pow((double)hand[3].getGameValue(),(double)2);
            } else {
                return TWO_PAIR + (int)Math.pow((double)hand[0].getGameValue(),(double)2) + (int)Math.pow((double)hand[3].getGameValue(),(double)2);
            }
        } else if (isOnePair()){ // kind of hand is one pair 
            if(hand[0].getGameValue() == hand[1].getGameValue())
                return ONE_PAIR + (int)Math.pow((double)hand[0].getGameValue(),(double)2) ;
            else if (hand[1].getGameValue() == hand[2].getGameValue())
                return ONE_PAIR + (int)Math.pow((double)hand[1].getGameValue(),(double)2) ;
            else if (hand[2].getGameValue() == hand[3].getGameValue())
                return ONE_PAIR + (int)Math.pow((double)hand[2].getGameValue(),(double)2) ;
            else
                return ONE_PAIR + (int)Math.pow((double)hand[3].getGameValue(),(double)2) ;
        } else { // kind of hand is high hand
            return HIGH_HAND + hand[0].getGameValue() + hand[1].getGameValue() + hand[2].getGameValue() + hand[3].getGameValue() + hand[4].getGameValue() ;
        }
    }
    
    public static void main(String[] args) {
        // creat two hands 
        HandOfCards hand1 = new HandOfCards();
        HandOfCards hand2 = new HandOfCards() ;
        // print two hands details
        System.out.println("   Hand1\t\t\t     Hand2");
        System.out.println(hand1 + "\t\t\t" + hand2);
        System.out.println("Kind of Hand\tState\t\tKind of Hand\tState");
        System.out.println("RoyalFlush\t"+hand1.isRoyalFlush()+"\t\tRoyalFlush\t"+hand2.isRoyalFlush());
        System.out.println("StraightFlush\t"+hand1.isStraightFlush()+"\t\tStraightFlush\t"+hand2.isStraightFlush());
        System.out.println("FourOfKhind\t"+hand1.isFourOfAKind()+"\t\tFourOfKhind\t"+hand2.isFourOfAKind());
        System.out.println("FullHouse\t"+hand1.isFullHouse()+"\t\tFullHouse\t"+hand2.isFullHouse());
        System.out.println("Flush\t\t"+hand1.isFlush()+"\t\tFlush\t\t"+hand2.isFlush());
        System.out.println("Straight\t"+hand1.isStraight()+"\t\tStraight\t"+hand2.isStraight());
        System.out.println("ThreeOfAKind\t"+hand1.isThreeOfAKind()+"\t\tThreeOfAKind\t"+hand2.isThreeOfAKind());
        System.out.println("TwoPair\t\t"+hand1.isTwoPair()+"\t\tTwoPair\t\t"+hand2.isTwoPair());
        System.out.println("OnePair\t\t"+hand1.isOnePair()+"\t\tOnePair\t\t"+hand2.isOnePair());
        System.out.println("HighHand\t"+hand1.isHighHand()+"\t\tHighHand\t"+hand2.isHighHand());
        // print which hand is bigger
        System.out.println((hand1.getGameValue() > hand2.getGameValue())? "\nHand1 > Hand2":"\nHand1 < Hand2"); 
        
        // print chance to change each card of hand1 and hand2
        System.out.println("Chance to change card");
        for(int i = 0 ; i < 5 ; i++){
            System.out.println((i+1)+") " + hand1.getDiscardProbability(i)+"\t\t"+hand2.getDiscardProbability(i));
        }
    }
    
}