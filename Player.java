public class Player {
	//fields
	private String name;
	private DynamicCardArray hand;
	private DynamicCardArray chosenCards;
	private boolean hasPlayedChopsticks;
	//constructor
	public Player(String name){
		this.name = name;
		this.hand = new DynamicCardArray();
		this.chosenCards = new DynamicCardArray();
		this.hasPlayedChopsticks = false;
	}
	//setter
	public void setHand(DynamicCardArray newHand){
		this.hand = newHand;
	}

	public void setHasPlayedChopsticks(boolean playedChopsticks){
		this.hasPlayedChopsticks = playedChopsticks;
	}
	//getters
	public String getName(){
		return this.name;
	}

	public DynamicCardArray getHand(){
		return this.hand;
	}

	public DynamicCardArray getChosenCards(){
		return this.chosenCards;
	}

	public boolean hasPlayedChopsticks(){
		return this.hasPlayedChopsticks;
	}
	//shows the cards that are in this player's hand
	public void displayHand(){
		for(int i = 0; i < this.hand.length(); i++){
			Card card = this.hand.getCardAtIndex(i);
			if(card != null){
				System.out.println((i + 1) + ". " + card);
			}
		}
	}

	public void displayChosenCards(){
		for(int i = 0; i < this.chosenCards.length(); i++){
			Card card = this.chosenCards.getCardAtIndex(i);
			System.out.println(card);
		}
	}

	//adds a card to the player's hand
	public void addToHand(Card card){
		this.hand.add(card);
	}

	public void removeCardFromHand(int index){
		if(index >= 0 && index < this.hand.length()){
			hand.remove(index);
		} else {
			System.out.println("Invalid index");
		}
	}
	//removes a card from the hand dynamic array then gets added to chosenCards dynamic array
	public void chooseCard(int index){
		if(index >= 0 && index < this.hand.length()){
			Card chosen = this.hand.getCardAtIndex(index);
			this.hand.remove(index);
			this.chosenCards.add(chosen);
		} else {
			System.out.println("Invalid Index");
		}
	}
	
}
