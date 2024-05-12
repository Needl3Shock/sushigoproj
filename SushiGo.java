import java.util.Scanner;
public class SushiGo{
    public static void main(String[] args){

        Deck deck = new Deck();
        deck.shuffle();
        

        
        Player[] players = createPlayers();

        initialHands(deck, players);
        
        int rounds = 3;
        int roundcards = cardsPerRound(players.length);
        for(int i = 1; i <= rounds; i++){
            System.out.println("------------------");
            System.out.println("Round " + i);
            System.out.println("------------------");

            for (int j = 0; j < roundcards; j++) {
                for (Player currentPlayer : players) {
                    playerTurn(currentPlayer, deck);
                    if(currentPlayer.hasPlayedChopsticks()){
                        playerTurn(currentPlayer, deck);
                        currentPlayer.setHasPlayedChopsticks(false);
                    }
                }
                circularSwitchHand(players);
            }
            refill(deck, players);
        }

		
    }

    public static Player[] createPlayers(){
		Scanner read = new Scanner(System.in);
		int maxPlayers = 0;

		Boolean validChoice = false;
		while(!validChoice){
			try{
				System.out.println("How many players would like to play? (Only 3 - 5 players)");
		        maxPlayers = Integer.parseInt(read.nextLine());
				if (maxPlayers > 2 && maxPlayers <= 5){
					System.out.println(maxPlayers + " players will play");
					validChoice = true;
				} else {
					System.out.println("Please pick a number between 3 to 5");
				}
			} catch (NumberFormatException e){
			    System.out.println("Invalid input. Please enter a valid number");
			}
        }
        Player[] players = new Player[maxPlayers];
		for(int i = 0; i < players.length; i++){
			System.out.println("Enter username for Player " + (i + 1));
			String playerName = read.nextLine();
			players[i] = new Player(playerName);
		}
		return players; 
    }

    public static void initialHands(Deck deck, Player[] players){
        int starterCards = cardsPerRound(players.length);
        for (int i = 0; i < starterCards; i++){
            for(Player player : players){
                player.addToHand(deck.drawTopCard());
            }
        }
    }

    public static int cardsPerRound(int nbPlayers){
        if(nbPlayers == 3){
            return 9;
        } else if(nbPlayers == 4){
            return 8;
        } else if(nbPlayers == 5){
            return 7;
        } else {
            throw new IllegalArgumentException("Unsupported number of players");
        }
    }

    /*public static void playerTurn(Player current){
        Scanner read = new Scanner(System.in);
        System.out.println(current.getName() + "'s turn:" + "\n" + "------------------");
        current.displayHand();
        System.out.println("------------------" + "\n" + current.getName() + "'s cards played:");
        current.displayChosenCards();
        System.out.println("------------------");

        Boolean validChoice = false;
        while(!validChoice){
            try{
                System.out.println("Enter the number of the card you want to use");
                int choice = Integer.parseInt(read.nextLine());

                DynamicCardArray hand = current.getHand();
                if (choice > 0 && choice <= hand.length()){
                    Card selected = hand.getCardAtIndex(choice - 1);
                    System.out.println(selected.toString());
                    current.getChosenCards().add(selected);
                    current.removeCardFromHand(choice - 1);
                    validChoice = true;
                } else {
                    System.out.println("Invalid choice");
                }
            } catch (NumberFormatException e){
                System.out.println("Invalid input. Please enter a valid number");
            }
        }
        System.out.println("------------------");
    }*/

    public static void playerTurn(Player current, Deck deck) {
        Scanner read = new Scanner(System.in);
        System.out.println(current.getName() + "'s turn:" + "\n" + "------------------");
        current.displayHand();
        System.out.println("------------------" + "\n" + current.getName() + "'s cards played:");
        current.displayChosenCards();
        System.out.println("------------------");

        System.out.println("Enter the number of the card you want to use");
        int choice = Integer.parseInt(read.nextLine());
    
        DynamicCardArray hand = current.getHand();
        if (choice > 0 && choice <= hand.length()) {
            Card selected = hand.getCardAtIndex(choice - 1);
            System.out.println(selected.toString());
            current.getChosenCards().add(selected);
            current.removeCardFromHand(choice - 1);
        } else {
            System.out.println("Invalid choice");
            playerTurn(current, deck); // Retry turn
        }
    
        System.out.println("------------------");
    }
    public static void handleChopsticks(Player current, int chopsticksIndex, Deck deck) {
        if (current.hasPlayedChopsticks()) {
            System.out.println("You've already used Chopsticks this round.");
            playerTurn(current, deck); // Retry turn
            return;
        }
    
        System.out.println("Choose 2 cards to play:");
        for (int i = 0; i < 2; i++) {
            playerTurn(current, deck);
        }
    
        current.setHasPlayedChopsticks(true);
        current.removeCardFromHand(chopsticksIndex); // Remove Chopsticks after using it
    }
    
    public static void circularSwitchHand(Player[] players){
        DynamicCardArray firstPlayerHand = players[0].getHand();

        for(int i = 0; i < players.length - 1; i++){
            Player current = players[i];
            Player next = players[i+1 % players.length];
            DynamicCardArray nextHand = next.getHand();

            DynamicCardArray temp = new DynamicCardArray();
            current.setHand(nextHand);
            next.setHand(temp);
        }

        players[players.length - 1].setHand(firstPlayerHand);
        
    }

    public static void refill(Deck deck, Player[] players){
        for(Player player : players){
            int maxHandSize = cardsPerRound(players.length);
            while(player.getHand().length() < maxHandSize){
                Card newCard = deck.drawTopCard();
                if(newCard != null){
                    player.addToHand(newCard);
                } else {
                    System.out.println("Deck is empty");
                    break;
                }
            }
        }
    }

    public static int countSameType(DynamicCardArray chosenCards, String type){

        int count = 0;
        for(int i = 0; i < chosenCards.length(); i++){
            Card card = chosenCards.getCardAtIndex(i);
            if(card.getType().equals(type)){
                count++;
            }
        }
        return count;
    }

    public static int calculateDumplingPoints(int nbDumplings){
        if(nbDumplings <= 5){
            switch (nbDumplings) {
                case 1:
                    return 1;
                case 2:
                    return 3;
                case 3:
                    return 6;
                case 4:
                    return 10;
                case 5:
                    return 15;
                default:
                    return 0;
            }
        } else {
            return 15;
        }
    }

    /*public static int calculatePoints(DynamicCardArray chosenCards){
        int total = 0;

        int numDumplings = countSameType(chosenCards, "DUMPLING");
        int numEggNigiri = countSameType(chosenCards, "EGGNIGIRI");
        int numSashimi = countSameType(chosenCards, "SASHIMI");
        int numTempura = countSameType(chosenCards, "TEMPURA")

        total += calculateDumplingPoints(numDumplings);
        total += numEggNigiri;
        total += (numSashimi / 3) * 10;
        total += (numTempura / 2) * 5;

        return total;
    }*/
}
