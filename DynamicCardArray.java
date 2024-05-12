import java.util.Random;
public class DynamicCardArray{
    //fields
    private Card[] cards;
    private Random rng;
    private int size;

    //constructor
    public DynamicCardArray(){
        this.cards = new Card[108]; //initialize it in deck file
        this.rng = new Random();
        this.size = 0;

    }
    //to String
    public String toString(){
        String builder = "";
        for(int i = 0; i < this.cards.length; i++){
            builder += this.cards[i] + "\n";
        }
        return builder;
    }
    //draws the card on the top of the pile
    public Card drawTopCard(){
        if(this.size > 0){
            this.size--;
            Card top = this.cards[this.size];
            this.cards[this.size] = null;
            return top;
        } else {
            throw new NullPointerException("No more cards to draw");
        }
    }
    //shuffles the cards
    public void shuffle(){ 
        for(int i = 0; i < this.cards.length; i++){
            int pos = i + this.rng.nextInt(this.cards.length - i);
            Card save = this.cards[i];
            this.cards[i] = this.cards[pos];
            this.cards[pos] = save;
        }
    }
    //adds a new card in the pile
    public void add(Card c){
        if(this.size >= this.cards.length){
            System.out.println("Card array is full");
            return;
        }
        this.cards[this.size] = c;
        this.size++;
    }
    //shows the length of the pile
    public int length(){
        return this.size;
    }
    //removes a card from the pile
    public void remove(int index){
        if(index >= 0 && index < this.size && this.cards[index] != null){
            for(int i = index; i < this.size - 1; i++){
                this.cards[i] = this.cards[i+1];
            }
            this.cards[this.size - 1] = null;
            this.size--;
        } else {
            System.out.println("Invalid Index");
        }
    }
    //clears the entire pile
    public void clear(){
        this.cards = new Card[108];
        this.size = 0;
    }

    //inserts a card in a pile
    public void insert(int index, Card card){
        if(index < 0 || index > this.size){
            System.out.println("Invalid index");
        }
        if(this.size >= this.cards.length){
            System.out.println("Card array is full");
        }
        for(int i = this.size; i > index; i--){
            this.cards[i] = this.cards[i - 1];
        }
        this.cards[index] = card;
        this.size++;
    }
    //finds if its true that a card is inside a pile
    public Boolean contains(Card card){
        for(int i = 0; i < this.size; i++){
            if(this.cards[i].getType().equals(card.getType())){
                return true;
            }
        }
        return false;
    }
    
    
    //gets the card at a specific index
    public Card getCardAtIndex(int index){
        if(index >= 0 && index < this.size){
            return this.cards[index];
        } else {
            System.out.println("Invalid Index");
            return null;
        }
    }
}