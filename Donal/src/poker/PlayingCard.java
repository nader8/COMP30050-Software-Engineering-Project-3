package poker;

public class PlayingCard {
	static public final char Hearts='H';
	static public final char Clubs='C';
	static public final char Diamonds='D';
	static public final char Spades='S';

	// These are the values that store the data on what type of card it is
	private char suit;
	private String card;
	private int faceValue=0, gameValue=0;
	

	public PlayingCard(String card, char suit, int faceValue, int gameValue){	// This constructor initialises the relevant values
		this.card=card;
		this.suit=suit;
		this.faceValue=faceValue;
		this.gameValue=gameValue;
	}

	public String toString(){	// This function returns what the card actually is
		String ans=card+suit;
		return ans;
	}

	public int getGameValue(){	// This function is needed to return gameValue because the variable itself is private
		return gameValue;
	}
	
	public int getFaceValue(){	// This function is needed to return faceValue because the variable itself is private
		return faceValue;
	}
	
	public char getSuit(){	// This function is needed to return suit because the variable itself is private
		return suit;
	}

	public static void main(String[] args){
		PlayingCard deck[]=new PlayingCard[52];	// This creates a deck array
		int number=0;	// This keeps track of how many cards are in the array
		char suits[]={PlayingCard.Hearts, PlayingCard.Spades, PlayingCard.Diamonds, PlayingCard.Clubs};	// These arrays make it easier to populate the
		String faces[]={"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};				// deck array with the PlayingCard instances
		
		// These loops populate the deck array with 52 instances of PlayingCard
		for(int i=0;i<suits.length;i++){
			for (int j=0;j<faces.length;j++){
				if (j==0){	// This is for when we're creating an ace
					deck[number]=new PlayingCard(faces[j], suits[i], 1, 14);
					number++;
				}
				else{	// This is for any other card.
					deck[number]=new PlayingCard(faces[j], suits[i], j+1, j+1);
					number++;
				}
			}
		}
		
		// This prints out the cards that are in the deck array
		for(int i=0;i<52;i++){
			System.out.println(deck[i].toString());
		}
	}
}

