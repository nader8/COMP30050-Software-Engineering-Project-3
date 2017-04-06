package poker;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class DeckOfCards {
	public static final int DECK_SIZE = 52;
	public static final int SUIT_SIZE = 13;
	public static final int SUIT_NUM = 4;
	
	private PlayingCard[] Deck = new PlayingCard[DECK_SIZE];
//	private PlayingCard[] CardDealt = new PlayingCard[DECK_SIZE];
	private ArrayList<PlayingCard> CardDealt = new ArrayList<PlayingCard>();
	private static int element_num = 0;
	
	public DeckOfCards(){
		initializeDeck();
	}
	
	private void initializeDeck(){
		String[] CardTypeSet = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		char[] CardSuitSet = {PlayingCard.CLUBS, PlayingCard.DIAMONDS, PlayingCard.HEARTS, PlayingCard.SPADES};
		int[] faceValSet = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
		int[] gameValSet = {14, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
		//set elements in the deck.
		
		int count = 0; //counter that loops through the Deck Array
		
		while (count < DECK_SIZE){
			//set boundary so only loops PlayingCard.DECK_SIZE times
			for (int suitCount = 0; suitCount < SUIT_NUM; suitCount++){
				//loops for N times where N = PlayingCard.SUIT_NUM
				char CardSuit = CardSuitSet[suitCount];
				for (int cardCount = 0; cardCount < SUIT_SIZE; cardCount++){
					//loops for N times where N = PlayingCard.SUIT_SIZE
					String CardType = CardTypeSet[cardCount];
					int faceVal = faceValSet[cardCount];
					int gameVal = gameValSet[cardCount];
					PlayingCard card = new PlayingCard(CardType, CardSuit, faceVal, gameVal);
					Deck[count] = card;
					count++;
				}
			}	
		}
	}
	
	public PlayingCard dealNext(){
		//deal the next element_num card of the array Deck
		PlayingCard dealCard = null;
		
		if (element_num < DECK_SIZE){
			dealCard = Deck[element_num];
			CardDealt.add(dealCard);
			Deck[element_num] = null;
			element_num +=1;
		}
		
		else{
			reset();
			dealNext();
		}
		return dealCard;
	}
	
	public void shuffle(){
		//shuffle the deck square the size of the deck
		int cycle_num = DECK_SIZE * DECK_SIZE;
		Random randomGenerator = new Random();
		
		for(int idx = 0; idx < cycle_num; idx++){
			int randomInt = randomGenerator.nextInt(DECK_SIZE);
			int randomInt2 = randomGenerator.nextInt(DECK_SIZE);
			PlayingCard temp = Deck[randomInt];
			Deck[randomInt] = Deck[randomInt2];
			Deck[randomInt2] = temp;
			//swapping place of the element at randomInt and randomInt2
		}
		
	}
	
	public void reset(){
		//create a new instance of DeckOfCards class and reinitialize Deck array to the new instance after shuffled
		initializeDeck();
		shuffle();
		element_num = 0;
	}
	
	public void returnCard(PlayingCard discarded){
		String str = "";
		for (PlayingCard card : CardDealt) {
			if (card.equals(discarded)) {
				str = "Card put back to the deck";
				int index = 0;
				while(index < Deck.length){
					if(Deck[index] == null){
						Deck[index] = discarded;
						break;
					}
					
					else{
						index++;
					}
				}
				
				break;
			}
			
			else{
				str = "Invalid Card";
			}
		}
		
		System.out.println(str);
		
	}
	
	public PlayingCard[] getDeck(){
		return Deck;
	}
	
	public String toString(){
		//toString method returns string of CardType with CardSuit
		String str = "";
		for (int x = 0; x < DECK_SIZE; x++){
			str += Deck[x] + "\n";
			
		}
		return str;
	}
	
	public static void main(String[] args){
		DeckOfCards cardDeck = new DeckOfCards();
		Scanner scanner = new Scanner(System.in);

		int count = 0;
		
		cardDeck.shuffle();
		System.out.println(cardDeck.dealNext());
		System.out.println(cardDeck.dealNext());		
		System.out.println(cardDeck.dealNext());
		
		System.out.println("What would you like to do now?");
		String input = scanner.nextLine();
		if (input.equals("reset")){
			cardDeck.reset();
			System.out.println("deck of card is reseted");
		}
		else if(input.equals("return card")){
			System.out.println("What is the suit of the card that you would like to return?");
			String suit = scanner.nextLine();
			
			System.out.println("What is the type of the card that you would like to return?");
			String cardType = scanner.nextLine();
			
			System.out.println("What is the face value of the card that you would like to return?");
			String str1 = scanner.nextLine();
			int faceVal = Integer.parseInt(str1);
			
			System.out.println("What is the game value of the card that you would like to return?");
			String str2 = scanner.nextLine();
			int gameVal = Integer.parseInt(str2);
			
			char cardSuit = suit.toUpperCase().charAt(0);
			cardType = cardType.toUpperCase().substring(0, 1);
			System.out.println(cardSuit);
			System.out.println(cardType);
			
			PlayingCard card = new PlayingCard(cardType, cardSuit, faceVal, gameVal);
			cardDeck.returnCard(card);
		}
	}
	
}
