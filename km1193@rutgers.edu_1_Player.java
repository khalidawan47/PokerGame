public class Player{
    //declare your fields here
    double Amount;
    Hand myhand = new Hand();

    //initialize your fields in the constructor
    public Player(double balance){
        Amount=balance;
    }

    public void deal(Card c){
        myhand.addCard(c);
    }
    //Returns an array of Cards that the Player wishes to discard.
    //The game engine will call deal() on this player for each card
    //that exists in the return value. MS2 Instructions: Print the hand to
    //the terminal using System.out.println and ask the user which cards 
    //they would like to discard. The user will first the number of cards they
    //wish to discard, followed by the indices, one at a time, of
    //the card(s) they would like to discard, 
    //Return an array with the appropriate Card objects
    //that have been discarded, and remove the Card objects from this
    //player's hand. Use IO.readInt() for all inputs. In cases of error
    //re-ask the user for input.
    //
    // Example call to discard():
    //
    // This is your hand:
    // 0: Ace of Hearts
    // 1: 2 of Diamonds
    // 2: 5 of Hearts
    // 3: Jack of Spades
    // 4: Ace of Clubs
    // How many cards would you like to discard?
    // 2
    // 1
    // 2
    //
    // The resultant array will contain the 2 of Diamonds and the 5 of hearts in that order
    // This player's hand will now only have 3 cards
    public Card[] discard(){
        showHand();
        boolean discardCardAmount = true;
        boolean discardCardNumber = true;
        System.out.println("How many cards would you like to discard?");
        int temp;
        int TotaldiscardedCard = 0;

        while(discardCardAmount) {
            temp = IO.readInt();
            if(temp >=0 && temp <= myhand.getCardCount()) {
                discardCardAmount = false;
                TotaldiscardedCard = temp;
            }
            
            if(discardCardAmount== true) {
                temp = 0;
                System.out.println("Enter number of card to discard: ");
            }
            
        }
        
        Card[] numOfCardToDiscard = new Card[TotaldiscardedCard];
        int[] Indexes = new int[TotaldiscardedCard];
        
        for(int i = 0; i < Indexes.length; i++) {
            Indexes[i] = -10;
        }
        int count = 0;
        int cardToDiscard = 0;
        for (int i = 0; i<TotaldiscardedCard; i++) {
            discardCardNumber = true;
            System.out.println("Enter in index of card you want to discard: ");
            
            while(discardCardNumber) {
                cardToDiscard = IO.readInt();
                if( ((cardToDiscard >= 0 && cardToDiscard < 5) && (myhand.getCard(cardToDiscard) != null)) && (cardToDiscard != Indexes[i])) {
                    numOfCardToDiscard [i] = myhand.getCard(cardToDiscard);
                    discardCardNumber = false;
                }
                if(discardCardNumber == true) {
                    System.out.println("Enter a index: ");
                }
            }
            Indexes[count] = cardToDiscard;
            count++;
            myhand.removeCard(cardToDiscard);
        }
        return numOfCardToDiscard ;
    }

    //Returns the amount that this player would like to wager, returns
    //-1.0 to fold hand. Any non zero wager should immediately be deducted
    //from this player's balance. This player's balance can never be below
    // 0 at any time. This player's wager must be >= to the parameter min
    // MS2 Instructions: Show the user the minimum bet via the terminal 
    //(System.out.println), and ask the user for their wager. Use
    //IO.readDouble() for input. In cases of error re-ask the user for 
    //input.
    // 
    // Example call to wager()
    //
    // How much do you want to wager?
    // 200
    //
    // This will result in this player's balance reduced by 200

    public double wager(double min){
        boolean badamount=true;
        double  WagerAmount=0.0;
        System.out.println("The minimum bet is: " + min);
        System.out.println("How much do you want to wager?");
        while(badamount) {
            WagerAmount = IO.readDouble();
            
            if(WagerAmount == -1) {
                return -1;
            }
            if(WagerAmount <= Amount && WagerAmount >= min) {
                badamount = false;
                Amount -= WagerAmount;
            }
            if(badamount == true) {
                System.out.println("Please enter a valid amount.");
            }
        }
        return WagerAmount;
    }

    //Returns this player's hand
    public Hand showHand(){
        return myhand; 
    }

    //Returns this player's current balance
    public double getBalance(){
        return Amount;
    }
    

    //Increase player's balance by the amount specified in the parameter,
    //then reset player's hand in preparation for next round. Amount will
    //be 0 if player has lost hand
    public void winnings(double amount){
        Amount += amount;
        myhand.clear();
    }
}

