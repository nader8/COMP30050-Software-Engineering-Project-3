package poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class DeckOfCards {
	private ArrayList<PlayingCard> deck=new ArrayList<PlayingCard>();	// This is the deck of cards that haven't been dealt yet
	private ArrayList<PlayingCard> discardPile=new ArrayList<PlayingCard>();	// This is the deck of cards that have been discarded
	private ArrayList<PlayingCard> inHand=new ArrayList<PlayingCard>();	// This keeps track of the cards not in the deck or discardPile
		
	public DeckOfCards(){
		char suits[]={PlayingCard.Hearts, PlayingCard.Spades, PlayingCard.Diamonds, PlayingCard.Clubs};	// These arrays make it easier to populate the
		String faces[]={"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};				// deck array with the PlayingCard instances

		// These loops populate the deck array with 52 instances of PlayingCard
		for(int i=0;i<suits.length;i++){
			for (int j=0;j<faces.length;j++){
				if (j==0){	// This is for when we're creating an ace
					deck.add(new PlayingCard(faces[j], suits[i], 1, 14));
				}
				else{	// This is for any other card.
					deck.add(new PlayingCard(faces[j], suits[i], j+1, j+1));
				}
			}
		}
		shuffle();	// This shuffles the deck after it's been created
	}

	public void shuffle(){	// This 'shuffles' the deck by randomly selecting two cards and swapping them
		Random ranNum=new Random();	// Random objects are more efficient than Maths.random
		int cardOne=ranNum.nextInt(deck.size());
		int cardTwo=ranNum.nextInt(deck.size());
		for (int i=0;i<(deck.size()*deck.size());i++){	// This swaps 2 random cards and selects 2 more to swap
			Collections.swap(deck, cardOne, cardTwo);
			cardOne=ranNum.nextInt(deck.size());
			cardTwo=ranNum.nextInt(deck.size());
		}
	}

	public void reset(){	// This reinitialises the deck arraylist
		new DeckOfCards();
	}

	public PlayingCard dealNext(){	// This 'deals' the top card of the deck
		if (deck.isEmpty()){	// If the deck is empty, we add the discard pile to the deck and deal a new card
			while (!discardPile.isEmpty()){
				deck.add(discardPile.remove(0));
			}
		}
		PlayingCard nextCard=deck.get(0);
		deck.remove(0);
		return nextCard;
	}

	public void returnCard(PlayingCard discarded){	// This removes the discarded card from inHand, adds it to discardPile and deals a new card
		discardPile.add(discarded);
//		int i=inHand.indexOf(discarded);		// These lines are only needed for running the test in this class, otherwise this interferes
//		inHand.remove(i);						// with other methods in other classes
//		inHand.add(dealNext());
	}
	
	public ArrayList<PlayingCard> getInHand(){
		return inHand;
	}

	public int size(){	// This just makes it less confusing to get the size of the deck arraylist
		return deck.size();
	}

	public static void main(String[] args){
		Scanner scanner = new Scanner( System.in );	// This allows user inpur for what cards to discard
		DeckOfCards deck=new DeckOfCards();
		for (int i=0;i<5;i++){	// This deals cards to our hand
			deck.inHand.add(deck.dealNext());
		}
		System.out.println(deck.deck);
		System.out.println("Your hand is: "+deck.inHand);
		int i=0;
		while(i<60){	// This test that discarded cards cannot be drawn again after they are discarded, and that the returnCard and dealNext functions are working
			System.out.println("Which card will you discard?");
			String discard=scanner.next();
			for (int j=0;j<deck.inHand.size();j++){	// This loops through the cards in your hand and checks if any of them match the card you want to discard
				if (deck.inHand.get(j).toString().equalsIgnoreCase(discard)){
					PlayingCard discardCard=deck.inHand.get(j);
					deck.returnCard(discardCard);
					System.out.println("Your hand is "+deck.inHand);
					break;
				}
			}
			
			System.out.println(deck.deck);	// This prints the cards that can be dealt at the end of each turn
			i++;
		}
		scanner.close();	// This closes the scanner object
	}
}
