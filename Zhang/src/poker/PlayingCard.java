package poker;

public class PlayingCard implements Comparable<PlayingCard>{
	
	static public final char HEARTS = 'H';
	static public final char DIAMONDS = 'D';
	static public final char SPADES = 'S';
	static public final char CLUBS = 'C';
	
	private String CardType;
	private char CardSuit;
	private int faceVal;
	private int gameVal;
	
	public PlayingCard (String CardType, char CardSuit, int faceVal, int gameVal){
		//Constructor method assigns variable CardType, CardSuit, faceVal and gameVal
		this.CardType = CardType;
		this.CardSuit = CardSuit;
		this.faceVal = faceVal;
		this.gameVal = gameVal;
	}
	
	public String getCardType(){
		//get method returns CardType
		return CardType;
	}
	
	public char getCardSuit(){
		//get method returns CardSuit
		return CardSuit;
	}
	
	public int getfaceVal(){
		//get method returns faceVal
		return faceVal;
	}
	
	public int getgameVal(){
		//get method returns gameVal
		return gameVal;
	}
	
	public String toString(){
		//toString method returns string of CardType with CardSuit
		String str = CardType + CardSuit;
		return str;
	}
	
	
	public boolean equals(PlayingCard othercard) {	//comparing if two string of PlayingCard is the same
		return this.toString().equals(othercard.toString());
	}

	public int compareTo(PlayingCard othercard) {	//comparing the game value of the PlayingCard for sorting in largest to smallest order
		return othercard.gameVal - this.gameVal;
	}
		 	
}
