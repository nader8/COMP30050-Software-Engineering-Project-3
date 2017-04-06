package poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class HandOfCards {
	static public final int HandSize=5;	// This is the size of the hand we can have
	static public final int GameValueAce=14;	// 14 is the Game value of an ace, so this is used to check if the hand contains a royal flush
	private ArrayList<PlayingCard> hand=new ArrayList<PlayingCard>();	// This is the hand that the player has drawn
	private DeckOfCards deck=new DeckOfCards();	// This maintains a reference to a deck object that has been passed into the constructor
	static public final int Rank=5;	// This helps compute values for cards at certain ranks

	static public final int RoyalFlushValue=10000000;	// By having 1000000 between each rank, this ensures rank hierarchy, no matter what combination of cards in a lower rank
	static public final int StraighhtFlushValue=9000000;	// This ensures that the hands are ranked correctly, as there is no possible combination 
	static public final int FourOfAKindValue=8000000;		// in any type of hand that will add 1000000 to the default value of that hand
	static public final int FullHouseValue=7000000;
	static public final int FlushValue=6000000;
	static public final int StraightValue=5000000;
	static public final int ThreeOfAKindValue=4000000;
	static public final int TwoPairsValue=3000000;
	static public final int OnePairValue=2000000;
	static public final int HighHandValue=1000000;

	private char dominantSuit;		// This records the dominant suit in a flush
	private int cardBreakingStraight;	// This records the index of the card breaking the straight
	private int oddCardOut;			// This records the index of the odd card out in a hand of two pairs
	private int firstCardInPair;	// This records the index of the first card in a pair in a hand of two pairs

	public HandOfCards(DeckOfCards deck){	// This constructs a hand of cards from the reference to DeckOfCards.cards
		for (int i=0;i<HandSize;i++){
			hand.add(deck.dealNext());
		}
		this.deck=deck;
		sort();
	}

	public void add(PlayingCard newCard){	// This makes it so that when a card is added to the hand, it is
		hand.add(0, newCard);				// placed at index 0 and doesn't interfere with other functions
	}
	
	public DeckOfCards getDeck(){	// returns the deck
		return deck;
	}
	
	public void remove(PlayingCard discardCard){
		hand.remove(discardCard);
	}

	public PlayingCard get(int position){	// This makes it easy to access PlayingCard objects in HandOfCards objects
		return hand.get(position);
	}
	
	public void sort(){	// This sorts the hand from highest gameValue to lowest gameValue
		for (int i=0;i<HandSize;i++){
			for (int j=i+1;j<HandSize;j++){
				if (hand.get(i).getGameValue()<hand.get(j).getGameValue()){
					Collections.swap(hand, i, j);
				}
			}
		}
	}

	public boolean isRoyalFlush(){	// Checks if the hand contains a royal flush
		if (hand.get(0).getGameValue()==GameValueAce && hand.get(0).getGameValue()==hand.get(1).getGameValue()+1
				&& hand.get(1).getGameValue()==hand.get(2).getGameValue()+1 && hand.get(2).getGameValue()==hand.get(3).getGameValue()+1
				&& hand.get(3).getGameValue()==hand.get(4).getGameValue()+1 && hand.get(0).getSuit()==hand.get(1).getSuit()
				&& hand.get(1).getSuit()==hand.get(2).getSuit() && hand.get(2).getSuit()==hand.get(3).getSuit() 
				&& hand.get(3).getSuit()==hand.get(4).getSuit()){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean isStraightFlush(){	// Checks if the hand contains a straight flush
		if (hand.get(0).getGameValue()==hand.get(1).getGameValue()+1 && hand.get(1).getGameValue()==hand.get(2).getGameValue()+1
				&& hand.get(2).getGameValue()==hand.get(3).getGameValue()+1 && hand.get(3).getGameValue()==hand.get(4).getGameValue()+1
				&& hand.get(0).getSuit()==hand.get(1).getSuit() && hand.get(1).getSuit()==hand.get(2).getSuit() 
				&& hand.get(2).getSuit()==hand.get(3).getSuit() && hand.get(3).getSuit()==hand.get(4).getSuit() && !isRoyalFlush()){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean isFourOfAKind(){	// Checks if the hand contains four of a kind
		if ((hand.get(0).getGameValue()==hand.get(1).getGameValue() && hand.get(1).getGameValue()==hand.get(2).getGameValue() 
				&& hand.get(2).getGameValue()==hand.get(3).getGameValue()) || (hand.get(1).getGameValue()==hand.get(2).getGameValue() 
				&& hand.get(2).getGameValue()==hand.get(3).getGameValue() && hand.get(3).getGameValue()==hand.get(4).getFaceValue())){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean isThreeOfAKind(){	// Checks if the hand contains three of a kind
		if(((hand.get(0).getGameValue()==hand.get(1).getGameValue() && hand.get(1).getGameValue()==hand.get(2).getGameValue())
				|| (hand.get(1).getGameValue()==hand.get(2).getGameValue() && hand.get(2).getGameValue()==hand.get(3).getGameValue())
				|| (hand.get(2).getGameValue()==hand.get(3).getGameValue() && hand.get(3).getGameValue()==hand.get(4).getGameValue()))
				&& !isFourOfAKind() && !isFullHouse()){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean isFullHouse(){	// Checks if the hand contains a full house
		if((hand.get(0).getGameValue()==hand.get(1).getGameValue() && hand.get(1).getGameValue()==hand.get(2).getGameValue()
				&& hand.get(3).getGameValue()==hand.get(4).getGameValue()) || (hand.get(0).getGameValue()==hand.get(1).getGameValue()
				&& hand.get(2).getGameValue()==hand.get(3).getGameValue() && hand.get(3).getGameValue()==hand.get(4).getGameValue())){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean isStraight(){	// Checks if the hand contains a straight
		if (hand.get(0).getGameValue()==hand.get(1).getGameValue()+1 && hand.get(1).getGameValue()==hand.get(2).getGameValue()+1
				&& hand.get(2).getGameValue()==hand.get(3).getGameValue()+1 && hand.get(3).getGameValue()==hand.get(4).getGameValue()+1
				&& !isStraightFlush() && !isRoyalFlush()){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean isFlush(){	// Checks if the hand contains a flush
		if (hand.get(0).getSuit()==hand.get(1).getSuit() && hand.get(1).getSuit()==hand.get(2).getSuit() && hand.get(2).getSuit()==hand.get(3).getSuit()
				&& hand.get(3).getSuit()==hand.get(4).getSuit() && !isRoyalFlush() && !isStraightFlush()){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean isTwoPair(){	// Checks if the hand contains two pairs
		if(((hand.get(0).getGameValue()==hand.get(1).getGameValue() && hand.get(2).getGameValue()==hand.get(3).getGameValue())
				|| (hand.get(0).getGameValue()==hand.get(1).getGameValue() && hand.get(3).getGameValue()==hand.get(4).getGameValue())
				|| (hand.get(1).getGameValue()==hand.get(2).getGameValue() && hand.get(3).getGameValue()==hand.get(4).getGameValue()))
				&& !isFullHouse() && !isFourOfAKind()){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean isOnePair(){	// Checks if the hand contains a pair
		if ((hand.get(0).getGameValue()==hand.get(1).getGameValue() || hand.get(1).getGameValue()==hand.get(2).getGameValue()
				|| hand.get(2).getGameValue()==hand.get(3).getGameValue() || hand.get(3).getGameValue()==hand.get(4).getGameValue()) 
				&& !isTwoPair() && !isThreeOfAKind() && !isFourOfAKind() && !isFullHouse()){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean isHighHand(){	// checks if the hand is a high hand
		if (!isFlush() && !isFourOfAKind() && !isFullHouse() &&!isOnePair() && !isTwoPair() && !isRoyalFlush() && !isStraight()
				&& !isThreeOfAKind() && !isStraightFlush()){
			return true;
		}
		else{
			return false;
		}
	}

	public int getGameValue(){	// Gets the value of the cards in your hand depending on the number of cards in your hand if there are multiple cards of the same value, or their rank in the hand if there is only one card of a particular value
		int value=0;
		if(isRoyalFlush()){	// This just loads in 10000000 into value, which no other combination of cards can reach.
			value+=RoyalFlushValue;
		}
		else if(isStraightFlush()){
			value=StraighhtFlushValue+straightFlushValue();
		}
		else if(isFourOfAKind()){	
			value=FourOfAKindValue+fourOfAKindValue();
		}
		else if(isThreeOfAKind()){
			value+=ThreeOfAKindValue+threeOfAKindValue();
		}
		else if(isFlush()){
			value+=FlushValue+flushValue();
		}
		else if(isStraight()){
			value+=StraightValue+straightValue();
		}
		else if(isFullHouse()){	
			value+=FullHouseValue+fullHouseValue();
		}
		else if(isTwoPair()){
			value+=TwoPairsValue+twoPairsValue();
		}
		else if(isOnePair()){
			value+=OnePairValue+onePairValue();
		}
		else if(isHighHand()){	// This calculates the value of a high hand.
			value+=HighHandValue+highHandValue();
		}
		return value;
	}

	public int getDiscardProbability(int cardPosition){		// This gets the probability of the card at cardPosition being discarded
		int discardProbability=0;
		Random ranPercentage=new Random();
		if(isFourOfAKind()){
			if(cardPosition==0 || cardPosition==4){	// This gets the probability of discarding a card in a four of a kind hand
				discardProbability=discardProbabilityInFourOfAKind(cardPosition);
			}
			else{
				discardProbability=100;
			}
		}
		else if(isBrokenFlush()){	// if the card in question is part of the dominant suit, then don't discard it. Otherwise do.
			if(hand.get(cardPosition).getSuit()==dominantSuit){
				discardProbability=0;
			}
			else{
				discardProbability=100;
			}
		}
		else if(isBrokenStraight()){	// if the hand is a broken straight, we discard the card breaking the straight
			if(cardPosition==cardBreakingStraight){
				discardProbability=100;
			}
			else{
				discardProbability=0;
			}
		}
		else if(isThreeOfAKind()){
			discardProbability=discardProbabilityInThreeOfAKind(cardPosition);
		}
		else if(isTwoPair()){
			getOddCardOut();
			if(oddCardOut==cardPosition){	// The odd card out is discarded in hope of completing a full house
				discardProbability=100;
			}
			else{
				discardProbability=0;
			}
		}
		else if(isOnePair()){
			getFirstCardInPair();
			if(cardPosition==firstCardInPair || cardPosition==firstCardInPair+1){
				discardProbability=0;
			}
			else{
				discardProbability=ranPercentage.nextInt(75)+1;	// If the card is not part of the pair, there is a random chance of it being discarded
			}
		}
		else if(isHighHand()){	// If cardPosition is 2, 3, or 4, the cards are discarded as there are cards with a higher game value
			if(cardPosition>1){
				discardProbability=100;
			}
		}
		return discardProbability;
	}

	private int discardProbabilityInFourOfAKind(int cardPosition){	// This calculates the discard probability of the card at CardPosition in a four of a kind hand
		int discardProbability=0;
		if((hand.get(0).getGameValue()==hand.get(1).getGameValue() && cardPosition==4) || (hand.get(3).getGameValue()==hand.get(4).getGameValue() && cardPosition==0)){	// This makes sure that we don't get the discard probability of a card not part of the four of a kind
			int keepProbability=(hand.get(cardPosition).getGameValue()-1)/13;	// This is used for calculating the discardProbability i.e there is a 13 out of 13 probability of keeping an ace
			discardProbability=100*(1-keepProbability);		// By subtracting the keepProbability from 1, we can get a fraction to multiply 100 by
		}													// to get the discardProbability of a card eg. an ace has a 1-13/13 (i.e. 0) chance of being discarded
		return discardProbability;
	}

	private int discardProbabilityInThreeOfAKind(int cardPosition){	// This calculates the discard probability of the card at CardPosition in a three of a kind hand
		int discardProbability=0;									// Depending on the positioning of the three of a kind, there are different max values for the random number generator
		Random ranPercentage=new Random();
		if((hand.get(0).getGameValue()==hand.get(1).getGameValue() && hand.get(1).getGameValue()==hand.get(2).getGameValue()) &&cardPosition>2 ){	// This makes sure that we don't get the discard probability of a card not part of the four of a kind
			if(cardPosition==3){
				discardProbability=ranPercentage.nextInt(75)+1;
			}
			else if(cardPosition==4){
				discardProbability=ranPercentage.nextInt(100)+1;
			}
		}
		else if(hand.get(1).getGameValue()==hand.get(2).getGameValue() && hand.get(2).getGameValue()==hand.get(3).getGameValue()){
			if(cardPosition==0){
				discardProbability=ranPercentage.nextInt(40)+1;
			}
			else if(cardPosition==4){
				discardProbability=ranPercentage.nextInt(100)+1;
			}
		}
		else if(hand.get(1).getGameValue()==hand.get(2).getGameValue() && hand.get(2).getGameValue()==hand.get(3).getGameValue()){
			if(cardPosition==0){
				discardProbability=ranPercentage.nextInt(25)+1;
			}
			else if(cardPosition==1){
				discardProbability=ranPercentage.nextInt(50)+1;
			}
		}
		return discardProbability;
	}

	private void getOddCardOut(){	// This gets the index of the card that is not part of a pair
		if(hand.get(0).getGameValue()==hand.get(1).getGameValue() && hand.get(2).getGameValue()==hand.get(3).getGameValue()){
			oddCardOut=4;
		}
		else if(hand.get(1).getGameValue()==hand.get(2).getGameValue() && hand.get(3).getGameValue()==hand.get(4).getGameValue()){
			oddCardOut=0;
		}
		else if(hand.get(0).getGameValue()==hand.get(1).getGameValue() && hand.get(3).getGameValue()==hand.get(4).getGameValue()){
			oddCardOut=2;
		}
	}

	private void getFirstCardInPair(){	// This gets the index of the first card in a pair
		if(hand.get(0).getGameValue()==hand.get(1).getGameValue()){
			firstCardInPair=0;
		}
		else if(hand.get(1).getGameValue()==hand.get(2).getGameValue()){
			firstCardInPair=1;
		}
		else if(hand.get(2).getGameValue()==hand.get(3).getGameValue()){
			firstCardInPair=2;
		}
		else if(hand.get(3).getGameValue()==hand.get(4).getGameValue()){
			firstCardInPair=3;
		}
	}

	private boolean isBrokenFlush(){	// This method calculates if a hand is a broken flush or not by counting the number of each
		int numFaces[]=new int[4];		// instance of a suit in a hand, and if a number is greater than 4, it returns true
		boolean answer=false;
		int heartsIndex=0, clubsIndex=1, spadesIndex=2, diamondsIndex=3;	// these are just indexes for the numFaces array
		for (int i=0;i<hand.size();i++){
			if(hand.get(i).getSuit()==PlayingCard.Hearts){
				numFaces[heartsIndex]+=1;
				if (numFaces[heartsIndex]>=3  && !isFlush()){
					dominantSuit=PlayingCard.Hearts;
					answer=true;
				}
			}
			else if(hand.get(i).getSuit()==PlayingCard.Clubs){
				numFaces[clubsIndex]+=1;
				if (numFaces[clubsIndex]>=3  && !isFlush()){
					dominantSuit=PlayingCard.Clubs;
					answer=true;
				}
			}
			else if(hand.get(i).getSuit()==PlayingCard.Spades){
				numFaces[spadesIndex]+=1;
				if (numFaces[spadesIndex]>=3  && !isFlush()){
					dominantSuit=PlayingCard.Spades;
					answer=true;
				}
			}
			else{
				numFaces[diamondsIndex]+=1;
				if (numFaces[diamondsIndex]>=3  && !isFlush()){
					dominantSuit=PlayingCard.Diamonds;
					answer=true;
				}
			}
		}
		return answer;
	}

	private boolean isBrokenStraight(){	// checks if a hand contains a broken straight and records the index of the card breaking the straight
		if(!isStraight()){	// These conditions cover all the possible combinations of cards that form a broken straight
			if((hand.get(0).getGameValue()-1==hand.get(1).getGameValue() && hand.get(1).getGameValue()-1==hand.get(2).getGameValue() && hand.get(2).getGameValue()-1==hand.get(3).getGameValue())
					|| (hand.get(0).getGameValue()-1==hand.get(1).getGameValue() && hand.get(2).getGameValue()-1==hand.get(3).getGameValue() && hand.get(1).getGameValue()-2==hand.get(2).getGameValue())
					|| (hand.get(1).getGameValue()-1==hand.get(2).getGameValue() && hand.get(2).getGameValue()-1==hand.get(3).getGameValue() && hand.get(0).getGameValue()-2==hand.get(1).getGameValue())){
				cardBreakingStraight=4;
				return true;
			}
			else if((hand.get(1).getGameValue()-1==hand.get(2).getGameValue() && hand.get(2).getGameValue()-1==hand.get(3).getGameValue() && hand.get(3).getGameValue()-1==hand.get(4).getGameValue())
					|| (hand.get(1).getGameValue()-1==hand.get(2).getGameValue() && hand.get(3).getGameValue()-1==hand.get(4).getGameValue() && hand.get(2).getGameValue()-2==hand.get(3).getGameValue())
					|| (hand.get(1).getGameValue()-1==hand.get(2).getGameValue() && hand.get(2).getGameValue()-1==hand.get(3).getGameValue() && hand.get(3).getGameValue()-2==hand.get(4).getGameValue())){
				cardBreakingStraight=0;
				return true;
			}
			else if((hand.get(2).getGameValue()-1==hand.get(3).getGameValue() && hand.get(3).getGameValue()-1==hand.get(4).getGameValue()) && (hand.get(1).getGameValue()-2==hand.get(2).getGameValue() || hand.get(0).getGameValue()-2==hand.get(2).getGameValue())){
				if(hand.get(0).getGameValue()-2==hand.get(2).getGameValue()){
					cardBreakingStraight=1;
					return true;
				}
				else{
					cardBreakingStraight=0;
					return true;
				}
			}
			else if((hand.get(0).getGameValue()-1==hand.get(1).getGameValue() && hand.get(1).getGameValue()-1==hand.get(2).getGameValue()) && (hand.get(2).getGameValue()-2==hand.get(3).getGameValue() || hand.get(2).getGameValue()-2==hand.get(4).getGameValue())){
				if(hand.get(2).getGameValue()-2==hand.get(3).getGameValue()){
					cardBreakingStraight=4;
					return true;
				}
				else{
					cardBreakingStraight=3;
					return true;
				}
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}

	private int straightFlushValue(){	// It doesn't matter what rank the cards are at because none of them can be used to break ties if hands are equal
		int value=0;
		for(int i=0;i<hand.size();i++){
			value+=Math.pow(hand.get(i).getGameValue(), Rank-i);
		}
		return value;
	}

	private int fourOfAKindValue(){// There is no need to consider kickers, because there can't be two identical instances of 4 of a kind
		int value=0;
		if (hand.get(0).getGameValue()==hand.get(1).getGameValue()){
			value+=Math.pow(hand.get(0).getGameValue(), 4);
		}
		else{
			value+=Math.pow(hand.get(1).getGameValue(), 4);
		}
		return value;
	}

	private int fullHouseValue(){// This calculates the value for the different types of full houses
		int value=0;
		if(hand.get(1).getGameValue()==hand.get(2).getGameValue()){
			value+=(int) (Math.pow(hand.get(2).getGameValue(), 3)+Math.pow(hand.get(3).getGameValue(), 2));
		}
		else{
			value+=(int) (Math.pow(hand.get(1).getGameValue(), 2)+Math.pow(hand.get(2).getGameValue(), 3));
		}
		return value;
	}

	private int flushValue(){	// This calculates the value of a flush
		int value=0;
		for(int i=0;i<hand.size();i++){
			value+=(int) Math.pow(hand.get(i).getGameValue(), Rank-i);	// this ensures that hands with a high top rank card and multiple lower ranks wont't lose to hands with multiple high ranks that aren't as high as the highest rank in the other hand
		}
		return value;
	}

	private int straightValue(){	// This calculates the value of a straight
		int value=0;
		for(int i=0;i<hand.size();i++){
			value+=hand.get(i).getGameValue();
		}
		return value;
	}

	private int threeOfAKindValue(){	// There is no need to consider kickers, because there can't be two identical instances of 3 of a kind
		int value=0;
		if(hand.get(0).getGameValue()==hand.get(1).getGameValue()){
			value+=Math.pow(hand.get(0).getGameValue(), 3);
		}
		else if(hand.get(1).getGameValue()==hand.get(2).getGameValue()){
			value+=Math.pow(hand.get(1).getGameValue(), 3);
		}
		else{
			value+=Math.pow(hand.get(2).getGameValue(), 3);
		}
		return value;
	}

	private int twoPairsValue(){	// This calculates the game values for the different types of two pairs
		int value=0;
		if(hand.get(0).getGameValue()==hand.get(1).getGameValue() && hand.get(2).getGameValue()==hand.get(3).getGameValue()){
			value+=Math.pow(hand.get(0).getGameValue(), 2)+Math.pow(hand.get(2).getGameValue(), 2)+hand.get(4).getGameValue();
		}
		else if(hand.get(0).getGameValue()==hand.get(1).getGameValue() && hand.get(3).getGameValue()==hand.get(4).getGameValue()){
			value+=Math.pow(hand.get(0).getGameValue(), 2)+Math.pow(hand.get(3).getGameValue(), 2)+hand.get(2).getGameValue();
		}
		else{
			value+=Math.pow(hand.get(1).getGameValue(), 2)+Math.pow(hand.get(3).getGameValue(), 2)+hand.get(0).getGameValue();
		}
		return value;
	}

	private int onePairValue(){	// There are multiple possibilities for one pair in the hand, so we need to take this into account when calculating the game value
		int value=0;
		if(hand.get(0).getGameValue()==hand.get(1).getGameValue()){
			value+=Math.pow(hand.get(0).getGameValue(), 4)+Math.pow(hand.get(2).getGameValue(), 3)+Math.pow(hand.get(3).getGameValue(), 2)+hand.get(4).getGameValue();
		}
		else if(hand.get(1).getGameValue()==hand.get(2).getGameValue()){
			value+=Math.pow(hand.get(1).getGameValue(), 4)+Math.pow(hand.get(0).getGameValue(), 3)+Math.pow(hand.get(3).getGameValue(), 2)+hand.get(4).getGameValue();
		}
		else if(hand.get(2).getGameValue()==hand.get(3).getGameValue()){
			value+=Math.pow(hand.get(2).getGameValue(), 4)+Math.pow(hand.get(0).getGameValue(), 3)+Math.pow(hand.get(1).getGameValue(), 2)+hand.get(4).getGameValue();
		}
		else{
			value+=Math.pow(hand.get(3).getGameValue(), 4)+Math.pow(hand.get(0).getGameValue(), 3)+Math.pow(hand.get(1).getGameValue(), 2)+hand.get(2).getGameValue();
		}
		return value;
	}

	private int highHandValue(){	// This calculates the value of a high hand.
		int value=0;
		for(int i=0;i<hand.size();i++){
			value+=Math.pow(hand.get(i).getGameValue(), Rank-i);	// There are 5 possible ranks in the hand, highest lowest. This makes it so that higher ranked cards give a higher value
		}
		return value;
	}

	public void clearHand(){	// This clears out our hand of cards so that we can add our own cards for the test. This method is only for testing.
		for (int i=0;i<hand.size();){
			hand.remove(i);
		}
	}

	public String toString(){
		String hand = new String();
		for(int i=0;i<this.hand.size();i++){
			hand+=this.hand.get(i).toString()+" ";
		}
		return hand;
	}
	
	public static void main(String[] args){		// This tests the getGameValue function
		DeckOfCards deck=new DeckOfCards();
		HandOfCards hand=new HandOfCards(deck);
		HandOfCards playerOne=new HandOfCards(deck);	// playerOne and playerTwo are just used for testing that different types of card hands
		HandOfCards playerTwo=new HandOfCards(deck);	// give different values
		hand.clearHand();
		playerOne.clearHand();
		playerTwo.clearHand();

		hand.hand.add(new PlayingCard("A", PlayingCard.Hearts, 1, 14));
		hand.hand.add(new PlayingCard("K", PlayingCard.Hearts, 13, 13));
		hand.hand.add(new PlayingCard("Q", PlayingCard.Hearts, 12, 12));
		hand.hand.add(new PlayingCard("J", PlayingCard.Hearts, 11, 11));
		hand.hand.add(new PlayingCard("10", PlayingCard.Hearts, 10, 10));
		System.out.println("Royal Flush");
		for (int i=0;i<5;i++){
			System.out.println(hand.hand.get(i).toString()+" "+hand.getDiscardProbability(i));
		}
		hand.clearHand();

		hand.hand.add(new PlayingCard("10", PlayingCard.Hearts, 10, 10));
		hand.hand.add(new PlayingCard("K", PlayingCard.Hearts, 13, 13));
		hand.hand.add(new PlayingCard("Q", PlayingCard.Hearts, 12, 12));
		hand.hand.add(new PlayingCard("J", PlayingCard.Hearts, 11, 11));
		hand.hand.add(new PlayingCard("9", PlayingCard.Hearts, 9, 9));
		hand.sort();
		System.out.println("\nStraight Flush");
		for (int i=0;i<5;i++){
			System.out.println(hand.hand.get(i).toString()+" "+hand.getDiscardProbability(i));
		}
		hand.clearHand();

		hand.hand.add(new PlayingCard("A", PlayingCard.Hearts, 1, 14));
		hand.hand.add(new PlayingCard("A", PlayingCard.Spades, 1, 14));
		hand.hand.add(new PlayingCard("A", PlayingCard.Clubs, 1, 14));
		hand.hand.add(new PlayingCard("A", PlayingCard.Diamonds, 1, 14));
		hand.hand.add(new PlayingCard("K", PlayingCard.Hearts, 13, 13));
		hand.sort();
		System.out.println("\nFour of a Kind");
		for (int i=0;i<5;i++){
			System.out.println(hand.hand.get(i).toString()+" "+hand.getDiscardProbability(i));
		}
		hand.clearHand();

		hand.hand.add(new PlayingCard("A", PlayingCard.Hearts, 1, 14));
		hand.hand.add(new PlayingCard("A", PlayingCard.Spades, 1, 14));
		hand.hand.add(new PlayingCard("A", PlayingCard.Clubs, 1, 14));
		hand.hand.add(new PlayingCard("K", PlayingCard.Diamonds, 13, 13));
		hand.hand.add(new PlayingCard("K", PlayingCard.Hearts, 13, 13));
		hand.sort();
		System.out.println("\nFull House");
		for (int i=0;i<5;i++){
			System.out.println(hand.hand.get(i).toString()+" "+hand.getDiscardProbability(i));
		}
		hand.clearHand();

		hand.hand.add(new PlayingCard("A", PlayingCard.Hearts, 1, 14));
		hand.hand.add(new PlayingCard("Q", PlayingCard.Hearts, 12, 12));
		hand.hand.add(new PlayingCard("5", PlayingCard.Hearts, 5, 5));
		hand.hand.add(new PlayingCard("9", PlayingCard.Hearts, 9, 9));
		hand.hand.add(new PlayingCard("K", PlayingCard.Hearts, 13, 13));
		hand.sort();
		System.out.println("\nFlush");
		for (int i=0;i<5;i++){
			System.out.println(hand.hand.get(i).toString()+" "+hand.getDiscardProbability(i));
		}
		hand.clearHand();

		hand.hand.add(new PlayingCard("9", PlayingCard.Hearts, 9, 9));
		hand.hand.add(new PlayingCard("10", PlayingCard.Spades, 10, 10));
		hand.hand.add(new PlayingCard("J", PlayingCard.Clubs, 11, 11));
		hand.hand.add(new PlayingCard("Q", PlayingCard.Diamonds, 12, 12));
		hand.hand.add(new PlayingCard("K", PlayingCard.Hearts, 13, 13));
		hand.sort();
		System.out.println("\nStraight");
		for (int i=0;i<5;i++){
			System.out.println(hand.hand.get(i).toString()+" "+hand.getDiscardProbability(i));
		}
		hand.clearHand();

		hand.hand.add(new PlayingCard("A", PlayingCard.Hearts, 1, 14));
		hand.hand.add(new PlayingCard("A", PlayingCard.Spades, 1, 14));
		hand.hand.add(new PlayingCard("A", PlayingCard.Clubs, 1, 14));
		hand.hand.add(new PlayingCard("10", PlayingCard.Diamonds, 10, 10));
		hand.hand.add(new PlayingCard("K", PlayingCard.Hearts, 13, 13));
		hand.sort();
		System.out.println("\nThree of a Kind");
		for (int i=0;i<5;i++){
			System.out.println(hand.hand.get(i).toString()+" "+hand.getDiscardProbability(i));
		}
		hand.clearHand();

		hand.hand.add(new PlayingCard("A", PlayingCard.Hearts, 1, 14));
		hand.hand.add(new PlayingCard("A", PlayingCard.Spades, 1, 14));
		hand.hand.add(new PlayingCard("Q", PlayingCard.Clubs, 12, 12));
		hand.hand.add(new PlayingCard("K", PlayingCard.Diamonds, 13, 13));
		hand.hand.add(new PlayingCard("K", PlayingCard.Hearts, 13, 13));
		hand.sort();
		System.out.println("\nTwo Pairs");
		for (int i=0;i<5;i++){
			System.out.println(hand.hand.get(i).toString()+" "+hand.getDiscardProbability(i));
		}
		hand.clearHand();

		hand.hand.add(new PlayingCard("A", PlayingCard.Hearts, 1, 14));
		hand.hand.add(new PlayingCard("A", PlayingCard.Spades, 1, 14));
		hand.hand.add(new PlayingCard("7", PlayingCard.Clubs, 7, 7));
		hand.hand.add(new PlayingCard("J", PlayingCard.Diamonds, 11, 11));
		hand.hand.add(new PlayingCard("K", PlayingCard.Hearts, 13, 13));
		hand.sort();
		System.out.println("\nOne Pair");
		for (int i=0;i<5;i++){
			System.out.println(hand.hand.get(i).toString()+" "+hand.getDiscardProbability(i));
		}
		hand.clearHand();

		hand.hand.add(new PlayingCard("A", PlayingCard.Hearts, 1, 14));
		hand.hand.add(new PlayingCard("9", PlayingCard.Spades, 9, 9));
		hand.hand.add(new PlayingCard("7", PlayingCard.Clubs, 7, 7));
		hand.hand.add(new PlayingCard("J", PlayingCard.Diamonds, 11, 11));
		hand.hand.add(new PlayingCard("K", PlayingCard.Hearts, 13, 13));
		hand.sort();
		System.out.println("\nHigh Hand");
		for (int i=0;i<5;i++){
			System.out.println(hand.hand.get(i).toString()+" "+hand.getDiscardProbability(i));
		}
		hand.clearHand();
		
		hand.hand.add(new PlayingCard("A", PlayingCard.Hearts, 1, 14));
		hand.hand.add(new PlayingCard("9", PlayingCard.Spades, 9, 9));
		hand.hand.add(new PlayingCard("Q", PlayingCard.Clubs, 12, 12));
		hand.hand.add(new PlayingCard("J", PlayingCard.Diamonds, 11, 11));
		hand.hand.add(new PlayingCard("K", PlayingCard.Hearts, 13, 13));
		hand.sort();
		System.out.println("\nBroken Straight");
		for (int i=0;i<5;i++){
			System.out.println(hand.hand.get(i).toString()+" "+hand.getDiscardProbability(i));
		}
		hand.clearHand();
		
		hand.hand.add(new PlayingCard("7", PlayingCard.Hearts, 7, 7));
		hand.hand.add(new PlayingCard("9", PlayingCard.Spades, 9, 9));
		hand.hand.add(new PlayingCard("8", PlayingCard.Clubs, 8, 8));
		hand.hand.add(new PlayingCard("J", PlayingCard.Diamonds, 11, 11));
		hand.hand.add(new PlayingCard("K", PlayingCard.Hearts, 13, 13));
		hand.sort();
		System.out.println("\nBroken Straight");
		for (int i=0;i<5;i++){
			System.out.println(hand.hand.get(i).toString()+" "+hand.getDiscardProbability(i));
		}
		hand.clearHand();
		
		hand.hand.add(new PlayingCard("7", PlayingCard.Hearts, 7, 7));
		hand.hand.add(new PlayingCard("9", PlayingCard.Spades, 9, 9));
		hand.hand.add(new PlayingCard("8", PlayingCard.Clubs, 8, 8));
		hand.hand.add(new PlayingCard("J", PlayingCard.Diamonds, 11, 11));
		hand.hand.add(new PlayingCard("J", PlayingCard.Clubs, 11, 11));
		hand.sort();
		System.out.println("\nBroken Straight");
		for (int i=0;i<5;i++){
			System.out.println(hand.hand.get(i).toString()+" "+hand.getDiscardProbability(i));
		}
		hand.clearHand();
		
		hand.hand.add(new PlayingCard("7", PlayingCard.Clubs, 7, 7));
		hand.hand.add(new PlayingCard("9", PlayingCard.Clubs, 9, 9));
		hand.hand.add(new PlayingCard("8", PlayingCard.Diamonds, 8, 8));
		hand.hand.add(new PlayingCard("J", PlayingCard.Diamonds, 11, 11));
		hand.hand.add(new PlayingCard("J", PlayingCard.Clubs, 11, 11));
		hand.sort();
		System.out.println("\nBroken Flush");
		for (int i=0;i<5;i++){
			System.out.println(hand.hand.get(i).toString()+" "+hand.getDiscardProbability(i));
		}
		hand.clearHand();
		
		hand.hand.add(new PlayingCard("A", PlayingCard.Clubs, 1, 14));
		hand.hand.add(new PlayingCard("K", PlayingCard.Clubs, 13, 13));
		hand.hand.add(new PlayingCard("8", PlayingCard.Spades, 8, 8));
		hand.hand.add(new PlayingCard("5", PlayingCard.Hearts, 5, 5));
		hand.hand.add(new PlayingCard("2", PlayingCard.Spades, 2, 2));
		hand.sort();
		System.out.println("\n");
		for (int i=0;i<5;i++){
			System.out.println(hand.hand.get(i).toString()+" "+hand.getDiscardProbability(i));
		}
		hand.clearHand();
	}
}
