public class Card {
    //field
    private Type type;
	//constructor
    public Card(Type type){
        this.type=type;
    }
    //setter
    public void setType(Type type){
        this.type = type;
    }
	//getter
    public Type getType(){
        return this.type;
    }
	//toString 
    public String toString(){
        return this.type.getName() + ": " + this.type.getDesc(); 
    }

}
