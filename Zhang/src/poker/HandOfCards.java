package poker;

import java.util.Arrays;

public class HandOfCards {
	private static final int HAND_CARD_NUM = 5;	//constant for the amount of cards allowed in the hand
	private static final int STRIAGHT_DIFF = 1; //constant use as difference between card game value to check for a straight
	
	private static final int ROYAL_FLUSH_DEFAULT = 300000;	//game value difference between KQJ109 of same suit and AKQJ10 of same suit is less than 300000
	private static final int STRAIGHT_FLUSH_DEFAULT = 250000;	//game value difference between AAAAK and 23456 of same suit is less than 250000
	private static final int FOUR_OF_A_KIND_DEFAULT = 200000;	//game value difference between AAAKK and 22223 is less than 200000
	private static final int FULL_HOUSE_DEFAULT = 100000;	//game value difference between AKQJ9 of same suit and 22233 is less than 100000
	private static final int FLUSH_DEFAULT = 80000;		//game value difference between AKQJ10 and 2,3,4,5,7 of same suit is less than 80000
	private static final int STRAIGHT_DEFAULT = 75000;	//game value difference between AAAKQ and 2,3,4,5,6 is less than 75000
	private static final int THREE_OF_A_KIND_DEFAULT = 3000;	//game value difference between AAKKQ and 22234 is less than 2000
	private static final int TWO_PAIR_DEFAULT = 1000;	//game value difference between AAKQJ and 22335 is less than 1000
	private static final int ONE_PAIR_DEFAULT = 50; //game value difference between AKQJ9 and 22345 is less than 50
	
	
	private PlayingCard[] CardHand = new PlayingCard[HAND_CARD_NUM]; //initializing an array of a size of HAND_CARD_NUM
	private DeckOfCards CardDeck; //private reference to the card deck
	
	public HandOfCards(DeckOfCards PlayingDeck){ //construct of the HandOfCards class which take in an instance of DeckOfCards
		PlayingDeck.shuffle(); //shuffle the deck
		for(int idx = 0; idx < HAND_CARD_NUM; idx++){ //loops through the CardHand array
			CardHand[idx] = PlayingDeck.dealNext(); //deal cards from Deck and save into the array
		}
		sort(); //sort the hand
		CardDeck = PlayingDeck;
	}
	
	public void sort(){		//sort the array using self-build method in Array 
		Arrays.sort(CardHand);
		
	}
	
	public boolean isRoyalFlush(){	//method checking if the hand is a royal flush
		if(CardHand[0].getCardType() == "A" && isStraightFlush()){
			return true;
		}
		
		else{
			return false;	
		}
	}
	
	public boolean isStraightFlush(){	//method checking if the hand is a straight flush
		if(isStraight() && isFlush() && CardHand[0].getCardType() != "A"){
			return true;
		}
		
		else{
			return false;	
		}
	}
	
	public boolean isFourOfAKind(){	//method checking if the hand is a four of a kind
		if(	(CardHand[0].getgameVal() ==  CardHand[3].getgameVal()) || (CardHand[1].getgameVal() == CardHand[4].getgameVal())){
			return true;
		}
		
		else{
			return false;	
		}
	}
	
	public boolean isThreeOfAKind(){	//method checking if the hand is a three of a kind
		if(	(CardHand[0].getgameVal() == CardHand[2].getgameVal() && CardHand[3].getgameVal() != CardHand[4].getgameVal()) || 
				(CardHand[1].getgameVal() == CardHand[3].getgameVal()) ||
				(CardHand[2].getgameVal() == CardHand[4].getgameVal() && CardHand[0].getgameVal() != CardHand[1].getgameVal()) &&
				!isFourOfAKind() && !isFullHouse()
				){
			return true;
		}
		
		else{
			return false;	
		}
	}
	
	public boolean isFullHouse(){	//method checking if the hand is a full house
		if((CardHand[0].getgameVal() ==  CardHand[2].getgameVal() && CardHand[3].getgameVal() == CardHand[4].getgameVal()) || 
				(CardHand[2].getgameVal() == CardHand[4].getgameVal() && CardHand[0].getgameVal() == CardHand[1].getgameVal())
				){
			return true;
		}
		
		else{
			return false;	
		}
	}
	
	public boolean isStraight(){	//method checking if the hand is a straight
		if((CardHand[0].getgameVal() - CardHand[1].getgameVal() == STRIAGHT_DIFF && CardHand[1].getgameVal() - CardHand[2].getgameVal() == STRIAGHT_DIFF &&
				CardHand[2].getgameVal() - CardHand[3].getgameVal() == STRIAGHT_DIFF && CardHand[3].getgameVal() - CardHand[4].getgameVal() == STRIAGHT_DIFF) || 
				(CardHand[0].getCardType() == "A" && CardHand[1].getCardType() == "5" && CardHand[1].getgameVal() - CardHand[2].getgameVal() == STRIAGHT_DIFF &&
				CardHand[2].getgameVal() - CardHand[3].getgameVal() == STRIAGHT_DIFF && CardHand[3].getgameVal() - CardHand[4].getgameVal() == STRIAGHT_DIFF)){
			return true;
		}
		
		else{
			return false;	
		}
	}
	
	public boolean isFlush(){	//method checking if the hand is a flush
		if(CardHand[0].getCardSuit() == CardHand[1].getCardSuit() && CardHand[1].getCardSuit() == CardHand[2].getCardSuit() && 
				CardHand[2].getCardSuit() == CardHand[3].getCardSuit() && CardHand[3].getCardSuit() == CardHand[4].getCardSuit()){
			return true;
		}
		
		else{
			return false;	
		}
	}
	
	public boolean isTwoPair(){	//method checking if the hand is a two pair
		if(	(CardHand[0].getgameVal() == CardHand[1].getgameVal() && CardHand[2].getgameVal() == CardHand[3].getgameVal())
				|| (CardHand[1].getgameVal() == CardHand[2].getgameVal() && CardHand[3].getgameVal() == CardHand[4].getgameVal())
				|| (CardHand[0].getgameVal() == CardHand[1].getgameVal() && CardHand[3].getgameVal() == CardHand[4].getgameVal())
				&& !isFourOfAKind() && !isFullHouse()
				){
			return true;
		}
		
		else{
			return false;	
		}
	}
	
	public boolean isOnePair(){	//method checking if the hand is a one pair
		if(CardHand[0].getgameVal() == CardHand[1].getgameVal() || CardHand[1].getgameVal() == CardHand[2].getgameVal() ||
				CardHand[2].getgameVal() == CardHand[3].getgameVal() || CardHand[3].getgameVal() == CardHand[4].getgameVal() && !isTwoPair() 
				&& !isThreeOfAKind() && !isFourOfAKind() && !isFullHouse()){
			return true;
		}
		
		else{
			return false;	
		}
	}
	
	public boolean isHighHand(){	//method checking if the hand is a high hand
		if(!isRoyalFlush() && !isStraightFlush() && !isFourOfAKind() && !isThreeOfAKind() && !isFullHouse() && !isStraight() && !isFlush() 
				&& !isTwoPair() && !isOnePair()){
			return true;
		}
		
		else{
			return false;	
		}
	}
	
	public PlayingCard[] getHand(){	//returns the CardHand array
		return CardHand;
	}
	
	public DeckOfCards getDeck(){	//returns Deck reference
		return CardDeck;
	}
	
	public int getGameValue(){	//method returns the game value of the hand
		int gameValue = 0;
		
		if (isRoyalFlush()){ //check if it is a royal flush	
			for (int idx = 0; idx < HAND_CARD_NUM; idx++){	//loop through the card hand
				gameValue += CardHand[idx].getgameVal();	//function for royal flush is sum of gameval. Of card on hand + ROYAL_FLUSH_DEFAULT (in royal flush, its rank is already highest hand in poker, no need to reinforce its importance)
			}
			
			gameValue += ROYAL_FLUSH_DEFAULT;
		}
		
		else if (isStraightFlush()){//check if it is a straight		
			for (int idx = 0; idx < HAND_CARD_NUM; idx++){	//loop through the card hand
				gameValue += CardHand[idx].getgameVal();	//function for straight flush is sum of gameval. Of card on hand + STRAIGHT_FLUSH_DEFAULT (in straight flush, the highest card would determine its rank, no need to reinforce its importance)
			}
			
			gameValue += STRAIGHT_FLUSH_DEFAULT;
		}
		
		else if (isFourOfAKind()){//check if it is a four of a kind
			//function of four of kind is square(four of kind sum) + sum of gameval. Of remaining card + FOUR_OF_A_KIND_DEFAULT (give higher importance to the four of kind)
			if (CardHand[0].getgameVal() == CardHand[3].getgameVal()){	//check if the first card is one of the four of kind
				gameValue =  (int) (Math.pow((CardHand[0].getgameVal() * 4), 2) + CardHand[4].getgameVal() + FOUR_OF_A_KIND_DEFAULT);
			}
			else if (CardHand[1].getgameVal() == CardHand[4].getgameVal()){	//check if the last card is one of the four of kind
				gameValue =  (int) (Math.pow((CardHand[4].getgameVal() * 4), 2) + CardHand[0].getgameVal() + FOUR_OF_A_KIND_DEFAULT);
			}
		}
		
		else if (isFullHouse()){//check if it is a full house
			//function of full house is cube(three of kind sum) + square(pair sum) + FULL_HOUSE_DEFAULT (give highest importance to the three of kind and high importance to the pair)
			
			if(CardHand[0].getgameVal() == CardHand[2].getgameVal()){ //if first card and third card is same, then first three card is three of kind
				gameValue = (int) (Math.pow((CardHand[0].getgameVal() * 3), 3) + Math.pow((CardHand[3].getgameVal() * 2), 2) + FULL_HOUSE_DEFAULT);
			}
			else{ //if third card and last card is same, then last three card is three of kind
				gameValue = (int) (Math.pow((CardHand[2].getgameVal() * 3), 3) + Math.pow((CardHand[0].getgameVal() * 2), 2) + FULL_HOUSE_DEFAULT);
			}
		}
		
		else if (isFlush()){//check if it is a flush
			//function of flush is square(gameVal of highest card + 10) + sum of gameval. Of rest card + FLUSH_DEFAULT (give higher importance to the highest card is the hand)
			for (int idx = 1; idx < HAND_CARD_NUM; idx++){	//loop through the card hand other than first(highest) card
				gameValue += CardHand[idx].getgameVal();
			}
			
			gameValue += Math.pow((CardHand[0].getgameVal() + 10), 2) + FLUSH_DEFAULT;
		}
		
		else if (isStraight()){//check if it is a straight
			//function for straight is Sum of gameval. Of card on hand + STRAIGHT_DEFAULT (in straight, the highest card would determine its rank, no need to reinforce its importance)
			for (int idx = 0; idx < HAND_CARD_NUM; idx++){	//loop through the card hand
				gameValue += CardHand[idx].getgameVal();
			}
			
			gameValue += STRAIGHT_DEFAULT;
		}
		
		else if (isThreeOfAKind()){//check if it is a three of a kind		
			if(CardHand[0].getgameVal() == CardHand[2].getgameVal()){ //if first card and third card is same, then first three card is three of kind
				gameValue = (int) (Math.pow((CardHand[0].getgameVal() * 3), 3) + CardHand[3].getgameVal() + CardHand[4].getgameVal() + THREE_OF_A_KIND_DEFAULT);
			}
			else if (CardHand[1].getgameVal() == CardHand[3].getgameVal()){ //if second card and fourth card is same, then middle three card is three of kind
				gameValue = (int) (Math.pow((CardHand[1].getgameVal() * 3), 3) + CardHand[0].getgameVal() + CardHand[4].getgameVal() + THREE_OF_A_KIND_DEFAULT);
			}
			else if (CardHand[2].getgameVal() == CardHand[4].getgameVal()){ //if third card and last card is same, then last three card is three of kind
				gameValue = (int) (Math.pow((CardHand[2].getgameVal() * 3), 3) + CardHand[0].getgameVal() + CardHand[1].getgameVal() + THREE_OF_A_KIND_DEFAULT);
			}
			
			//function for Three of kind is cube(three of kind sum) + sum of gameval. Of remain card + THREE_OF_A_KIND_DEFAULT (give higher importance to the three of kind)
		}
		
		else if (isTwoPair()){//calculate game value for a two pair			
			if(CardHand[0].getgameVal() == CardHand[1].getgameVal() && CardHand[2].getgameVal() == CardHand[3].getgameVal()){
				gameValue = (int) (Math.pow((CardHand[0].getgameVal() * 2), 2) + Math.pow((CardHand[2].getgameVal() * 2), 2) + CardHand[4].getgameVal() + TWO_PAIR_DEFAULT);
			}
			else if(CardHand[1].getgameVal() == CardHand[2].getgameVal() && CardHand[3].getgameVal() == CardHand[4].getgameVal()){
				gameValue = (int) (Math.pow((CardHand[1].getgameVal() * 2), 2) + Math.pow((CardHand[3].getgameVal() * 2), 2) + CardHand[0].getgameVal() + TWO_PAIR_DEFAULT);
			}
			else if(CardHand[0].getgameVal() == CardHand[1].getgameVal() && CardHand[3].getgameVal() == CardHand[4].getgameVal()){
				gameValue = (int) (Math.pow((CardHand[1].getgameVal() * 2), 2) + Math.pow((CardHand[3].getgameVal() * 2), 2) + CardHand[2].getgameVal() + TWO_PAIR_DEFAULT);
			}
		}
		
		else if (isOnePair()){//calculate game value for a one pair		
			if(CardHand[0].getgameVal() == CardHand[1].getgameVal()){
				gameValue = (int) (Math.pow((CardHand[0].getgameVal() * 2), 2) + CardHand[2].getgameVal() + CardHand[3].getgameVal() + CardHand[4].getgameVal() + ONE_PAIR_DEFAULT);
			}
			
			else if(CardHand[1].getgameVal() == CardHand[2].getgameVal()){
				gameValue = (int) (Math.pow((CardHand[1].getgameVal() * 2), 2) + CardHand[0].getgameVal() + CardHand[3].getgameVal() + CardHand[4].getgameVal() + ONE_PAIR_DEFAULT);
			}
			else if(CardHand[2].getgameVal() == CardHand[3].getgameVal()){
				gameValue = (int) (Math.pow((CardHand[2].getgameVal() * 2), 2) + CardHand[0].getgameVal() + CardHand[1].getgameVal() + CardHand[4].getgameVal() + ONE_PAIR_DEFAULT);
			}
			else if(CardHand[3].getgameVal() == CardHand[4].getgameVal()){
				gameValue = (int) (Math.pow((CardHand[3].getgameVal() * 2), 2) + CardHand[0].getgameVal() + CardHand[1].getgameVal() + CardHand[2].getgameVal() + ONE_PAIR_DEFAULT);
			}
		}
		
		else if (isHighHand()){//calculate game value for a high hand
			for (int idx = 0; idx < HAND_CARD_NUM; idx++){	//loop through the card hand
				gameValue += CardHand[idx].getgameVal();	//function for high hand is Sum of game value Of card on hand
			}
		}
		
		return gameValue;
	}
	
	public void setHand(PlayingCard card1, PlayingCard card2, PlayingCard card3, PlayingCard card4, PlayingCard card5){ //temporary function to set card hand for testing
		CardHand[0] = card1;
		CardHand[1] = card2;
		CardHand[2] = card3;
		CardHand[3] = card4;
		CardHand[4] = card5;
	}
	
	public int getDiscardProbability(int cardPosition){
		int probability = 0, numGoodCard = 0, cardInDeck = 47;
		
		if (cardPosition > 4 || cardPosition < 0){ // invalid card position
            return -1 ;
        }
		
		if (isRoyalFlush()){ //check if it is a royal flush
			numGoodCard = 0; //if hand is a royal flush then no point discard a card
		}
		
		else if (isStraightFlush()){//check if it is a straight
			numGoodCard = StraightFlushPossibleGoodCards(cardPosition);
		}
		
		else if (isFourOfAKind()){//check if it is a four of a kind
			numGoodCard = FourOfAKindPossibleGoodCards(cardPosition);
		}
		
		else if (isFullHouse()){//check if it is a full house
			numGoodCard = FullHousePossibleGoodCards(cardPosition);
		}
		
		else if (isStraight()){//check if it is a straight
			numGoodCard = StraightPossibleGoodCards(cardPosition);
		}
		
		else if (isFlush()){//check if it is a flush
			numGoodCard = FlushPossibleGoodCards(cardPosition);
		}
		
		else if (isThreeOfAKind()){//check if it is a three of a kind
			numGoodCard = ThreeOfAKindPossibleGoodCards(cardPosition);
		}
		
		else if (isTwoPair()){//check if it is a two pair
			numGoodCard = TwoPairPossibleGoodCards(cardPosition);
		}
		
		else if (isOnePair()){//check if it is a one pair
			numGoodCard = OnePairPossibleGoodCards(cardPosition);
		}
		
		else if (isHighHand()){//check if it is a high hand
			numGoodCard = HighHandPossibleGoodCards(cardPosition);
		}
		//probability function = (numGoodCard / cardInDeck) * 100
		probability = (int) (((double)numGoodCard / (double)cardInDeck) * 100);
		return probability;
	}
	
	private int StraightFlushPossibleGoodCards(int cardPosition){
		int numGoodCard = 0;
		
		if(cardPosition == 4){
			numGoodCard = 1;	//only probability of making a straight flush better is making the last card one bigger than the first card
								//eg 9D 8D 7D 6D 5D into 10D 9D 8D 7D 6D
		}
		
		return numGoodCard;
	}
	
	private int FourOfAKindPossibleGoodCards(int cardPosition){
		int numGoodCard = 0;
		
		if(cardPosition == 4){
			numGoodCard = 14 - CardHand[4].getgameVal(); //only way to improve hand if you can get a bigger single card eg 77773 to 77779
		}
		
		return numGoodCard;
	}
	
	private int FullHousePossibleGoodCards(int cardPosition){
		//you can improve a full house into a four of a kind
		int numGoodCard = 0;
		
		if(CardHand[2].getfaceVal() == CardHand[0].getfaceVal() && cardPosition > 2){
			numGoodCard = 1;	//you can improve a full house by making it into a four of a kind eg 77733 into 77773
		}
		else if(CardHand[2].getfaceVal() == CardHand[4].getfaceVal() && cardPosition < 2){
			numGoodCard = 1;
		}
		else if(CardHand[2].getfaceVal() == CardHand[4].getfaceVal() && cardPosition > 2){
			numGoodCard = 2; //you can also try to improve a full house by making a larger three of a kind in it eg 77333 into 77733
		}
		
		return numGoodCard;
	}
	
	private int StraightPossibleGoodCards(int cardPosition){
		//you can improve a straight into a straight/royal flush
		int numGoodCard = 0;
		
		if(CardHand[0].getfaceVal() != 1){	
			//you can't improve A5432 or AKQJ10 as a straight
			if(cardPosition == 4){
				//if card position is the last card and the first card is not ace then it is possible to make the straight bigger
				if(!isBrokenFlush()){
					numGoodCard = 4;	//you can improve a straight by making it bigger eg 87654 into 98765
				}
				
				if (isBrokenFlush() && isBrokenFlushCard(cardPosition)){
					numGoodCard = 5;	//in the case that the card position is the last card and its a broken flush
										//the hand can be improved like 8D 7D 6D 5D 4S into 8D 7D 6D 5D 4D or 9 8D 7D 6D 5D
				}
			}
		}
		
		else{
			//the only possible case here is A5432 or AKQJ10
			if (isBrokenFlush() && isBrokenFlushCard(cardPosition)){
				numGoodCard = 1;	//you can only improve it to straight/royal flush
			}
		}
		
		return numGoodCard;
	}
	
	private int FlushPossibleGoodCards(int cardPosition){
		//flush can be improved into a straight or straight/royal flush
		int numGoodCard = 0;
		
		if(isBrokenStraight() && isBrokenStraightCard(cardPosition)){
			numGoodCard = 4 + (14 - CardHand[0].getgameVal());	//you can only improve a flush if it is a broken straight into a straight,
																//straight flush or you can make the smallest card in hand into a bigger card
																//with same suit eg. 9S 7S 6S 5S 4S into 8S 7S 6S 5S 4S or 8D 7S 6S 5S 4S or
																//JS 7S 6S 5S 4S with 0 as card position
		}
		
		else if(!isBrokenStraight()){
			numGoodCard = (14 - CardHand[0].getgameVal());	//if card hand is not a broken straight, then the hand can only be improved to
															//a higher flush
		}
		
		return numGoodCard;
	}
	
	private int ThreeOfAKindPossibleGoodCards(int cardPosition){
		//three of a kind can be improved into a full house or four of a kind
		int numGoodCard = 0;
		
		if(CardHand[2].getfaceVal() == CardHand[0].getfaceVal() && cardPosition > 2){
			numGoodCard = 5;	//you can improve a three of a kind by making it into a four of a kind or a full house
								//eg 77333 into 77733 or 73333
		}
		else if(CardHand[2].getfaceVal() == CardHand[4].getfaceVal() && cardPosition < 2){
			numGoodCard = 5;
		}
		
		return numGoodCard;
	}
	
	private int TwoPairPossibleGoodCards(int cardPosition){
		//two pair can be improved into a three of a kind, full house
		int numGoodCard = 0;
		
		if((CardHand[0].getgameVal() == CardHand[1].getgameVal() && CardHand[2].getgameVal() == CardHand[3].getgameVal() && cardPosition == 4)
				|| (CardHand[1].getgameVal() == CardHand[2].getgameVal() && CardHand[3].getgameVal() == CardHand[4].getgameVal() && cardPosition == 0)
				|| (CardHand[0].getgameVal() == CardHand[1].getgameVal() && CardHand[3].getgameVal() == CardHand[4].getgameVal() && cardPosition == 2)){
				numGoodCard = 4;	//you can improve a two pair into a full house or three of a kind
									//eg 77633 into 77733 or 77333
		}
			
		else{
			numGoodCard = 4;	//this you can only improve a two pair into a three of a kind
								//eg 77633 into 77763 or 76333
		}
		
		return numGoodCard;
	}
	
	private int OnePairPossibleGoodCards(int cardPosition){
		//you can improve a one pair into a two pair, straight, flush, straight/royal flush, three of a kind
		int numGoodCard = 0;
		
		if(isBrokenFlush() && isBrokenStraight()){
			if(isBrokenFlushCard(cardPosition) && isBrokenStraightCard(cardPosition)){
				//eg 9D [8S] 8D 7D 6D into JD 9D 8D 7D 6D or 10D 9D 8D 7D 6D or 10S 9D 8D 7D 6D
				//the broken flush && broken straight can only be the pair card therefore to improve, it can
				//only improve to straight, flush, straight flush
				numGoodCard = 13;
			}
			else if(!isBrokenFlushCard(cardPosition) && !isBrokenStraightCard(cardPosition)){
				//eg 9D 8S 8D [7D] 6D into 9D 9C 8S 8D 6D or JD 9D 8S 8D 6D
				numGoodCard = (14 - CardHand[0].getgameVal()) + 6 + 2;
			}
			//the broken flush card and broken straight card are always the same in this situation therefore there is only 2 cases
		}
		
		else if(!isBrokenFlush() && isBrokenStraight()){
			if(isBrokenStraightCard(cardPosition)){
				//eg 9D 8S [8C] 7D 6D into 9D 9S 8S 7D 6D or 10D 9D 8S 7D 6D
				numGoodCard = 4 + ((CardHand[0].getgameVal() - CardHand[cardPosition].getgameVal()) * 3);
			}
			else if(!isBrokenStraightCard(cardPosition)){
				//eg 9D 8S 8C 7D [6D] into 9D 9S 8S 8C 7D or 9D 8S 8C 8D 7D
				numGoodCard = 6 + 2 + (14 - CardHand[0].getgameVal());
			}
		}
		
		else if(isBrokenFlush() && !isBrokenStraight()){
			if(isBrokenFlushCard(cardPosition)){
				//eg 9D [8S] 8D 7D 3D into 9D 9S 8D 7D 3D or 9D 8D 7D 3D 2D
				numGoodCard = 9 + ((CardHand[0].getgameVal() - CardHand[cardPosition].getgameVal()) * 3);
			}
			else if(!isBrokenFlushCard(cardPosition)){
				//eg 9D 8S 8D [7D] 3D into 9D 9S 8S 8D 3D or JS 9D 8S 8D 3D
				numGoodCard = (14 - CardHand[0].getgameVal()) + 6 + 2;
			}
		}
		
		else if(!isBrokenFlush() && !isBrokenStraight()){
			if(isPairCard(cardPosition)){
				//eg 9D [8S] 8D 7D 3C into 9D 9C 8D 7D 3S
				numGoodCard = ((CardHand[0].getgameVal() - CardHand[cardPosition].getgameVal()) * 3);
			}
			else if(!isPairCard(cardPosition)){
				//eg 9D 8S 8D [7D] 3C into 9D 9C 8S 8D 3S or 9D 8C 8S 8D 3C
				numGoodCard = 8;
			}
		}
		
		return numGoodCard;
	}
	
	private int HighHandPossibleGoodCards(int cardPosition){
		//you can improve a high hand into one pair, straight, flush or straight/royal flush
		int numGoodCard = 0;
		
		if(isBrokenFlush() && isBrokenStraight()){
			if(isBrokenFlushCard(cardPosition) && isBrokenStraightCard(cardPosition)){
				//eg [JS] 8D 7D 6D 5D into 9D 8D 7D 6D 5D or 8D 7D 6D 5D 4D or 8S 8D 7D 6D 5D or KS 8D 7D 6D 5D
				//for broken straight and broken flush to happen at the same time, it can only be the first card or
				//the last card
				numGoodCard = 12 + ((14 - CardHand[0].getgameVal()) * 4) + 8;
			}
			
			else if(isBrokenFlushCard(cardPosition) && !isBrokenStraightCard(cardPosition)){
				//eg JD 8D [7S] 6D 5D into JD 8D 7D 6D 5D or JD 8D 8S 6D 5D
				numGoodCard = 12 + 9 + ((14 - CardHand[0].getgameVal()) * 4);
			}
			
			else if(!isBrokenFlushCard(cardPosition) && isBrokenStraightCard(cardPosition)){
				//eg [JD] 8D 7S 6D 5D into 9S 8D 7S 6D 5D or 8S 8D 7S 6D 5D
				numGoodCard = 12 + 8 + ((14 - CardHand[0].getgameVal()) * 4);
			}
			
			else if(!isBrokenFlushCard(cardPosition) && !isBrokenStraightCard(cardPosition)){
				//eg JD [8D] 7S 6D 5D into JD JS 7S 6D 5D or KS JD 7S 6D 5D
				numGoodCard = ((14 - CardHand[0].getgameVal()) * 4) + 12;
			}
		}
		
		else if(!isBrokenFlush() && isBrokenStraight()){
			if(isBrokenStraightCard(cardPosition)){
				//eg [JD] 8D 7S 6C 5D into KD 8D 7S 6C 5D or 9D 8D 7S 6C 5D or 8D 7S 6C 5D 4C or 8D 7C 7S 6C 5D
				numGoodCard = 8 + 12 + ((14 - CardHand[0].getgameVal()) * 4);
			}
			else if(!isBrokenStraightCard(cardPosition)){
				//eg JD 8D [7S] 6C 5D into KS JD 8D 6C 5D or JD 8D 6S 6C 5D
				numGoodCard = 12 + ((14 - CardHand[0].getgameVal()) * 4);
			}
		}
		
		else if(isBrokenFlush() && !isBrokenStraight()){
			if(isBrokenFlushCard(cardPosition)){
				//eg KD [9S] 6D 3D 2D into KD 8D 6D 3D 2D or AS KD 6D 3D 2D or KD 6D 3S 3D 2D
				numGoodCard = 9 + 12 + ((14 - CardHand[0].getgameVal()) * 4);
			}
			else if(!isBrokenFlushCard(cardPosition)){
				//eg KD 9S 6D [3D] 2D into KD 9S 6D 2C 2D or AS KD 9S 6D 2D
				numGoodCard = 12 + ((14 - CardHand[0].getgameVal()) * 4);
			}
		}
		
		else if(!isBrokenFlush() && !isBrokenStraight()){
			//eg KD 9S 7H [6D] 2C into AS KD 9S 7H 2C or KD 9S 7H 7S 2C
			numGoodCard = 12 + ((14 - CardHand[0].getgameVal()) * 4);
		}
		
		return numGoodCard;
	}
	
	private boolean isPairCard(int cardPosition){
		int count = 0;
		for (int idx = 0; idx < HAND_CARD_NUM; idx++){
			if(CardHand[cardPosition].getgameVal() == CardHand[idx].getgameVal()){
				count++;
			}
		}
		
		if(count == 2){
			return true;
		}
		
		else{
			return false;
		}
	}
	private boolean isBrokenFlush(){ //find if hand is a broken flush
		int[] numEqualSuit = {0, 0, 0, 0, 0};
		char suit;
		for(int idx = 0; idx < HAND_CARD_NUM; idx++){
			suit = CardHand[idx].getCardSuit();
			for(int count = 0; count < HAND_CARD_NUM; count++){
				if(suit == CardHand[count].getCardSuit()){
					numEqualSuit[idx]++;
				}
			}
		}
		
		int largestNum = numEqualSuit[0];
		for (int i = 1; i < numEqualSuit.length; i++) {
		    if (numEqualSuit[i] > largestNum) {
		      largestNum = numEqualSuit[i];
		    }
		}
		
		if(largestNum == 4){ //if there is four card with same suit, then its a broken flush
			return true;
		}
		else{
			return false;
		}
	}
	
	private boolean isBrokenStraight(){ //find if hand is a broken straight
		if(CardHand[0].getgameVal() - CardHand[3].getgameVal() == 3 && CardHand[3].getgameVal() - CardHand[4].getgameVal() != 1){
			return true;
		}
		
		else if(CardHand[0].getgameVal() - CardHand[1].getgameVal() != 1 && CardHand[1].getgameVal() - CardHand[4].getgameVal() == 3){
			return true;
		}
		
		else if(CardHand[0].getgameVal() - CardHand[1].getgameVal() == 1 && CardHand[2].getgameVal() - CardHand[4].getgameVal() == 2 && CardHand[1].getgameVal() - CardHand[2].getgameVal() != 1){
			return true;
		}
		
		else if(CardHand[3].getgameVal() - CardHand[4].getgameVal() == 1 && CardHand[0].getgameVal() - CardHand[2].getgameVal() == 2 && CardHand[2].getgameVal() - CardHand[3].getgameVal() != 1){
			return true;
		}
		
		else if(CardHand[0].getfaceVal() == 1 && CardHand[1].getgameVal() - CardHand[4].getgameVal() == 3){
			return true;
		}
		
		else{
			return false;
		}
	}
	
	private boolean isBrokenFlushCard(int cardPosition){ //find if card have different suit to all other card in hand
		if((cardPosition != 2 && CardHand[cardPosition].getCardSuit() != CardHand[(4-cardPosition)].getCardSuit()) || (cardPosition == 2 && CardHand[2].getCardSuit() != CardHand[4].getCardSuit())){
			return true;
		}

		else{
			return false;
		}
	}
	
	private boolean isBrokenStraightCard(int cardPosition){
		boolean value = false;
		
		if(isBrokenStraight()){
			if(cardPosition == 0 && CardHand[0].getgameVal() - CardHand[1].getgameVal() != 1 && CardHand[1].getgameVal() - CardHand[4].getgameVal() == 3){
				value = true;
			}
			
			else if(cardPosition == 4 && CardHand[0].getgameVal() - CardHand[3].getgameVal() == 3 && CardHand[3].getgameVal() - CardHand[4].getgameVal() != 1){
				value = true;
			}
			
			else if(cardPosition == 1 && CardHand[0].getgameVal() - CardHand[1].getgameVal() != 1 && CardHand[1].getgameVal() - CardHand[4].getgameVal() == 3){
				value = true;
			}
			
			else if(cardPosition == 3 && CardHand[0].getgameVal() - CardHand[3].getgameVal() == 3 && CardHand[3].getgameVal() - CardHand[4].getgameVal() != 1){
				value = true;
			}
		}
		
		return value;
	}
	
	public static void main(String[] args){
		DeckOfCards PlayingDeck = new DeckOfCards();
		HandOfCards CardHand = new HandOfCards(PlayingDeck);
		PlayingCard[] HandOfCards = CardHand.getHand(); 
		
		PlayingCard card1, card2, card3, card4, card5;
		
		card1 = new PlayingCard("A", PlayingCard.CLUBS, 1, 14);
		card2 = new PlayingCard("K", PlayingCard.HEARTS, 13, 13);
		card3 = new PlayingCard("K", PlayingCard.CLUBS, 13, 13);
		card4 = new PlayingCard("J", PlayingCard.CLUBS, 11, 11);
		card5 = new PlayingCard("9", PlayingCard.SPADES, 9, 9);
		CardHand.setHand(card1, card2, card3, card4, card5);
		
		for (int idx = 0; idx < HandOfCards.length; idx++){	//print out the hand of cards
			System.out.print(HandOfCards[idx] + " ");
		}
		
		System.out.println("\n" + CardHand.getDiscardProbability(4) + "%");
	}
}
