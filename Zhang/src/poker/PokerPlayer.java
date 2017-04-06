package poker;

import java.util.Scanner;

public class PokerPlayer {
	private DeckOfCards CardDeck;
	private HandOfCards Hand;
	private int discardNum = 0;
	private static final int MAX_DISCARD_NUM = 3, HAND_CARD_NUM = 5;	//constant for largest amount to discard and largest amount of cards in hand
	private PlayingCard[] CardHand = new PlayingCard[HAND_CARD_NUM];
	Scanner reader = new Scanner(System.in);	//use to read input from user
	private int maxProbOfHand = 0;
	
	public PokerPlayer(DeckOfCards Deck){	//constructor, takes one Deck of card and keep the reference, create one HandOfCard instance
											//and an array of playing cards 
		CardDeck = Deck;
		Hand = new HandOfCards(CardDeck);
		CardHand = Hand.getHand();
	}
	
	private void printHand(){	//print the cards in hand
		for (int idx = 0; idx < CardHand.length; idx++){	//print out the hand of cards
			System.out.print(CardHand[idx] + " ");
		}
		System.out.print("\n");
	}
	
	public int discard(){	//returns an integer of amount of card discarded but also a function used to ask user which card to discard
		PlayingCard Card;
			
		while(discardNum < MAX_DISCARD_NUM){	//loops if and only if the amount of card discarded is less than MAX_DISCARD_NUM
			System.out.println("Which card would you like to discard?(0 - 4)");
			int cardPosition = reader.nextInt();
			
			if(Hand.getDiscardProbability(cardPosition) == 0){ //if probability is zero, we should not discard
				System.out.println("Discard	probability is zero");
			}
			
			else if (Hand.getDiscardProbability(cardPosition) > 0 && Hand.getDiscardProbability(cardPosition) <= 100){
				// discard card is its probability is greater than 0 but less or equal to 100
				Card = CardDeck.dealNext();	//deals card from deck
				CardDeck.returnCard(CardHand[cardPosition]);	//return discarded card to deck
				CardHand[cardPosition] = Card;	//add deal card into card hand
				discardNum++;	//increase discard number
				Hand.setHand(CardHand[0], CardHand[1], CardHand[2], CardHand[3], CardHand[4]);	//set card hand
				Hand.sort(); //sort the cards in hand
				CardHand = Hand.getHand(); //redefine the playing card array to the sorted one
				printHand();
			}
		}
		
		if(discardNum == MAX_DISCARD_NUM){	//if discard number already equals to MAX_DISCARD_NUM then no more card can be discarded
			System.out.println("You already discarded 3 cards, no more card can be discarded");
		}
		
		return discardNum;	//returns amount of card discard;
	}
	
	public PlayingCard[] getHand(){	//returns the CardHand array
		return CardHand;
	}
	
	public static void main(String[] args){
		DeckOfCards PlayingDeck = new DeckOfCards();
		PokerPlayer player = new PokerPlayer(PlayingDeck);
		PlayingCard[] HandOfCards = player.getHand(); 
		
		for (int idx = 0; idx < HandOfCards.length; idx++){	//print out the hand of cards
			System.out.print(HandOfCards[idx] + " ");
		}
		
		System.out.print("\n");
		player.discard();
	}
}
