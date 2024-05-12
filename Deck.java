public class Deck {
    //field
    private DynamicCardArray cards;
    //constructor
    public Deck(){
        
        this.cards = new DynamicCardArray();

        cardInitialize();
    }
    //initializes the amount of cards for each type of card
    public void cardInitialize(){
        int[] quantity = {14, 5, 14, 14, 4, 10, 6, 5, 26, 10};

        for (int i = 0; i < quantity.length; i++){
            for (int j = 0; j < quantity[i]; j++){
                Card newCard = new Card(Type.values()[i]);
                this.cards.add(newCard);
            }
        }
    }
    //shuffles the deck
    public void shuffle(){
        this.cards.shuffle();
    }
    //draws the card on the top of the deck
    public Card drawTopCard(){
        return this.cards.drawTopCard();
    }
    //to string
    public String toString(){
        return this.cards.toString();
    }
    //shows the length of the deck
    public int length(){
        return this.cards.length();
    }

}
