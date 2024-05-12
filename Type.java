public enum Type {
    DUMPLING("Dumpling", "1/2/3/4/5 dumplings for 1/3/6/10/15 points"),
    EGGNIGIRI("Egg Nigiri", "1 point"),
    SASHIMI("Sashimi", "When a player collects three Sashimi cards, they earn 10 points"),
    TEMPURA("Tempura", "When a player collects two Tempura cards, they earn 5 points"),
    CHOPSTICKS("Chopsticks", "Allows a player to play two cards at once"),
    SALMONNIGIRI("Salmon Nigiri", "2 points"),
    WASABI("Wasabi", "3x the next Nigiri's points"),
    SQUIDNIGIRI("Squid Nigiri", "3 points"),
    MAKIROLL("Maki Roll", "Players with the most and second-most Maki Rolls receive 6 and 3 points"),
    PUDDING("Pudding", "The player with the most Pudding cards earns 6 points at the end, while the player with the fewest loses 6 points");
	//fields
    private final String name;
    private final String desc;
	//constructor
    private Type(final String name, final String desc){
        this.name = name;
        this.desc = desc;
    }
	//getters
    public String getName(){
        return this.name;
    }

    public String getDesc(){
        return this.desc;
    }

}
