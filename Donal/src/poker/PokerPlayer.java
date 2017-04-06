package poker;

import java.util.Random;

public class PokerPlayer {
	private HandOfCards hand;
	static public final int maxCardsDiscarded=3;	// This keeps track of the number of cards that can be discarded
	static public final int ranNumBound=101;	// This is used to generate numbers between 0 and 100 inclusive

	public PokerPlayer(DeckOfCards deck){	// This creates a PokerPlayer object with a hand drawn from 'deck'
		hand=new HandOfCards(deck);
	}

	public int discard(){	// This discards cards, replaces them, and returns the number of cards discarded
		Random ranNum=new Random();
		int numCards=0;
		for(int i=0;i<HandOfCards.HandSize;i++){
			if(numCards<maxCardsDiscarded){	// This makes it so that a max of 3 cards can be discarded
				int probability=hand.getDiscardProbability(i);
				if(probability==100){	// If a card has a discard probability of 100, it has to be discarded
					hand.getDeck().returnCard(hand.get(i));
					hand.remove(hand.get(i));
					hand.add(hand.getDeck().dealNext());
					numCards++;
				}
				else if(probability<100 && probability>0){			// If a card has a discard probability between 0 and 100, there is a random chance
					if(probability<ranNum.nextInt(ranNumBound)){	// of it being discarded, the greater the probability, the greater the chance
						hand.getDeck().returnCard(hand.get(i));
						hand.remove(hand.get(i));
						hand.add(hand.getDeck().dealNext());
						numCards++;
					}
				}
			}
		}
		hand.sort();
		return numCards;
	}
	
	public HandOfCards getHand(){	// This returns the hand of the PokerPlayer
		return hand;
	}
	
	public static void main(String args[]){	// There may be some problems with the tests such that two of the same card exist, but that's because I
		DeckOfCards deck=new DeckOfCards();	// hard-coded in playing cards, so their copies are still in the deck
		PokerPlayer bot1=new PokerPlayer(deck);
		
		System.out.println(bot1.hand.toString());	// This test works using the first 5 cards drawn from the deck, so it differs every test run
		System.out.println(bot1.discard()+"\n"+bot1.getHand().toString()+"\n");
		bot1.getHand().clearHand();
		
		// The tests below are hard-coded, so there is a chance that you will draw a card that you already had (e.g. 2 8's of spades), so the
		// number of cards discarded may look wrong, but this is only because of the afore mentioned reason.
		// The tests are only meant to show that there is a different possibility of a card being discarded every time
		// Each test prints the original hand, the number of cards discarded, and the hand after discard.
		
		bot1.getHand().add(new PlayingCard("A", PlayingCard.Hearts, 1, 14));
		bot1.getHand().add(new PlayingCard("A", PlayingCard.Spades, 1, 14));
		bot1.getHand().add(new PlayingCard("A", PlayingCard.Clubs, 1, 14));
		bot1.getHand().add(new PlayingCard("K", PlayingCard.Diamonds, 13, 13));
		bot1.getHand().add(new PlayingCard("K", PlayingCard.Hearts, 13, 13));
		bot1.getHand().sort();
		System.out.println(bot1.getHand().toString());
		System.out.println(bot1.discard()+"\n"+bot1.getHand().toString()+"\n");
		bot1.getHand().clearHand();
		
		bot1.getHand().add(new PlayingCard("A", PlayingCard.Hearts, 1, 14));
		bot1.getHand().add(new PlayingCard("A", PlayingCard.Spades, 1, 14));
		bot1.getHand().add(new PlayingCard("Q", PlayingCard.Clubs, 12, 12));
		bot1.getHand().add(new PlayingCard("K", PlayingCard.Diamonds, 13, 13));
		bot1.getHand().add(new PlayingCard("K", PlayingCard.Hearts, 13, 13));
		bot1.getHand().sort();
		System.out.println(bot1.getHand().toString());
		System.out.println(bot1.discard()+"\n"+bot1.getHand().toString()+"\n");
		bot1.getHand().clearHand();

		bot1.getHand().add(new PlayingCard("A", PlayingCard.Hearts, 1, 14));
		bot1.getHand().add(new PlayingCard("A", PlayingCard.Spades, 1, 14));
		bot1.getHand().add(new PlayingCard("7", PlayingCard.Clubs, 7, 7));
		bot1.getHand().add(new PlayingCard("J", PlayingCard.Diamonds, 11, 11));
		bot1.getHand().add(new PlayingCard("K", PlayingCard.Hearts, 13, 13));
		bot1.getHand().sort();
		System.out.println(bot1.getHand().toString());
		System.out.println(bot1.discard()+"\n"+bot1.getHand().toString()+"\n");
		bot1.getHand().clearHand();

		bot1.getHand().add(new PlayingCard("A", PlayingCard.Hearts, 1, 14));
		bot1.getHand().add(new PlayingCard("9", PlayingCard.Spades, 9, 9));
		bot1.getHand().add(new PlayingCard("7", PlayingCard.Clubs, 7, 7));
		bot1.getHand().add(new PlayingCard("J", PlayingCard.Diamonds, 11, 11));
		bot1.getHand().add(new PlayingCard("K", PlayingCard.Hearts, 13, 13));
		bot1.getHand().sort();
		System.out.println(bot1.getHand().toString());
		System.out.println(bot1.discard()+"\n"+bot1.getHand().toString()+"\n");
		bot1.getHand().clearHand();
		
		bot1.getHand().add(new PlayingCard("A", PlayingCard.Hearts, 1, 14));
		bot1.getHand().add(new PlayingCard("9", PlayingCard.Spades, 9, 9));
		bot1.getHand().add(new PlayingCard("Q", PlayingCard.Clubs, 12, 12));
		bot1.getHand().add(new PlayingCard("J", PlayingCard.Diamonds, 11, 11));
		bot1.getHand().add(new PlayingCard("K", PlayingCard.Hearts, 13, 13));
		System.out.println(bot1.getHand().toString());
		System.out.println(bot1.discard()+"\n"+bot1.getHand().toString()+"\n");
		bot1.getHand().clearHand();
		
		bot1.getHand().add(new PlayingCard("7", PlayingCard.Hearts, 7, 7));
		bot1.getHand().add(new PlayingCard("9", PlayingCard.Spades, 9, 9));
		bot1.getHand().add(new PlayingCard("8", PlayingCard.Clubs, 8, 8));
		bot1.getHand().add(new PlayingCard("J", PlayingCard.Diamonds, 11, 11));
		bot1.getHand().add(new PlayingCard("K", PlayingCard.Hearts, 13, 13));
		bot1.getHand().sort();
		System.out.println(bot1.getHand().toString());
		System.out.println(bot1.discard()+"\n"+bot1.getHand().toString()+"\n");
		bot1.getHand().clearHand();
		
		bot1.getHand().add(new PlayingCard("7", PlayingCard.Hearts, 7, 7));
		bot1.getHand().add(new PlayingCard("9", PlayingCard.Spades, 9, 9));
		bot1.getHand().add(new PlayingCard("8", PlayingCard.Clubs, 8, 8));
		bot1.getHand().add(new PlayingCard("J", PlayingCard.Diamonds, 11, 11));
		bot1.getHand().add(new PlayingCard("J", PlayingCard.Clubs, 11, 11));
		bot1.getHand().sort();
		System.out.println(bot1.getHand().toString());
		System.out.println(bot1.discard()+"\n"+bot1.getHand().toString()+"\n");
		bot1.getHand().clearHand();
		
		bot1.getHand().add(new PlayingCard("7", PlayingCard.Clubs, 7, 7));
		bot1.getHand().add(new PlayingCard("9", PlayingCard.Clubs, 9, 9));
		bot1.getHand().add(new PlayingCard("8", PlayingCard.Diamonds, 8, 8));
		bot1.getHand().add(new PlayingCard("J", PlayingCard.Diamonds, 11, 11));
		bot1.getHand().add(new PlayingCard("J", PlayingCard.Clubs, 11, 11));
		bot1.getHand().sort();
		System.out.println(bot1.getHand().toString());
		System.out.println(bot1.discard()+"\n"+bot1.getHand().toString()+"\n");
		bot1.getHand().clearHand();
		
		bot1.getHand().add(new PlayingCard("A", PlayingCard.Clubs, 1, 14));
		bot1.getHand().add(new PlayingCard("K", PlayingCard.Clubs, 13, 13));
		bot1.getHand().add(new PlayingCard("8", PlayingCard.Spades, 8, 8));
		bot1.getHand().add(new PlayingCard("5", PlayingCard.Hearts, 5, 5));
		bot1.getHand().add(new PlayingCard("2", PlayingCard.Spades, 2, 2));
		bot1.getHand().sort();
		System.out.println(bot1.getHand().toString());
		System.out.println(bot1.discard()+"\n"+bot1.getHand().toString()+"\n");
		bot1.getHand().clearHand();
	}
}
