public class Hand {
	 
	private Card[] hand;   // The cards in the hand.
	   private int count; 
	   
	   /**
	    * Create a hand that is initially empty.
	    */
	   public Hand() {
	      hand = new Card[5];
		  count = 0;
	   }
	   
	   /**
	    * Remove all cards from the hand, leaving it empty.
	    */
	   public void clear() {
	      for(int i=0 ; i<hand.length; i++){ hand[i] = null; }
		  count = 0;
	   }
	   
	   /**
	    * Add a card to the hand.  It is added at the end of the current hand.
	    * @param c the non-null card to be added.
	    * @throws NullPointerException if the parameter c is null.
	    */
	   public void addCard(Card c) {
	      
		  for(int i=0 ; i<hand.length; i++){ 
			if (hand[i] == null){
				hand[i] = c;
				count = count + 1;
				break;
			}
		 }

		  
	   }
	   
	   /**
	    * Remove a card from the hand, if present.
	    * @param c the card to be removed.  If c is null or if the card is not in 
	    * the hand, then nothing is done.
	    */
	   public void removeCard(Card c) {

		for(int i=0 ; i<hand.length; i++){ 
			if (hand[i].equals(c)){
				hand[i] = null;
				count = count-1;
			}
		}

	   }
	   
	   /**
	    * Remove the card in a specified position from the hand.
	    * @param position the position of the card that is to be removed, where
	    * positions are starting from zero.
	    * @throws IllegalArgumentException if the position does not exist in
	    * the hand, that is if the position is less than 0 or greater than
	    * or equal to the number of cards in the hand.
	    */
	   public void removeCard(int position) {
	      if (position < 0 || position >= hand.length)
	         throw new IllegalArgumentException("Position does not exist in hand: "
	               + position);
	      hand[position] = null;
	   }

	   /**
	    * Returns the number of cards in the hand.
	    */
	   public int getCardCount() {
	      return count;
	   }
	   
	   /**
	    * Gets the card in a specified position in the hand.  (Note that this card
	    * is not removed from the hand!)
	    * @param position the position of the card that is to be returned
	    * @throws IllegalArgumentException if position does not exist in the hand
	    */
	   public Card getCard(int position) {
	      if (position < 0 || position >= hand.length)
	         throw new IllegalArgumentException("Position does not exist in hand: "
	               + position);
	       return hand[position];
	   }
	   
	   /**
	    * Sorts the cards in the hand so that cards of the same suit are
	    * grouped together, and within a suit the cards are sorted by value.
	    * Note that aces are considered to have the lowest value, 1.
	    */
	   public void sortBySuit() {
		  int size = count;
		  int nonnull = 0;
		  int index = 0;
		  
	      Card[] newHand = new Card[5];
	      while (size > 0) {
			 if (hand[nonnull] == null) { nonnull = nonnull+1; continue;}
	         int pos = nonnull;  // Position of minimal card.
	         Card c = hand[nonnull];  // Minimal card.
			 
	         for (int i = nonnull+1; i < hand.length; i++) {
	            Card c1 = hand[i];
				if (c1 != null){
					if ( c1.getSuit() < c.getSuit() ||
							(c1.getSuit() == c.getSuit() && c1.getValue() < c.getValue()) ) {
						pos = i;
						c = c1;
					}
				}
	         }
	         hand[pos] = null;
			 size = size - 1;
	         newHand[index++] = c;
			 nonnull = 0;
	      }
	      hand = newHand;
	   }
	   
	   /**
	    * Sorts the cards in the hand so that cards of the same value are
	    * grouped together.  Cards with the same value are sorted by suit.
	    * Note that aces are considered to have the lowest value, 1.
	    */
	   public void sortByValue() {
		  int size = count;
		  int nonnull = 0;
		  int index = 0;
		  
	      Card[] newHand = new Card[5];
	      while (size > 0) {
			 if (hand[nonnull] == null) { nonnull = nonnull+1; continue;}
	         int pos = nonnull;  // Position of minimal card.
	         Card c = hand[nonnull];  // Minimal card.
			 
	         for (int i = nonnull+1; i < hand.length; i++) {
	            
				Card c1 = hand[i];
	            if (c1 != null){
					if ( c1.getValue() < c.getValue() ||
							(c1.getValue() == c.getValue() && c1.getSuit() < c.getSuit()) ) {
						pos = i;
						c = c1;
					}
				}
	         }
	         hand[pos] = null;
			 size = size - 1;
	         newHand[index++] = c;
			 nonnull = 0;
	      }
	      hand = newHand;
	   }
	   
	   public void printHand(){
		   
		   for(int i=0; i<hand.length; i++){
			   if (hand[i] != null){
				   System.out.println(hand[i]);
			   }
		   }
		   System.out.println();
	   }

public int numPairs(){
		   sortByValue();
		   int count=0;
		   for (int k=0; k<hand.length-1; k=k+2){
		   if ((hand[k].getValue())==(hand[k+1].getValue())){

		   count++;
		   }
		   }
		   return count;
	   }
	
public boolean hasTriplet() {
	int i=0;
	sortByValue();
	if (hand[i].getValue()==hand[i+1].getValue() && hand[i+1].getValue()==hand[i+2].getValue()||hand[i+1].getValue()==hand[i+2].getValue() && hand[i+2].getValue()==hand[i+3].getValue()||hand[i+2].getValue()==hand[i+3].getValue() && hand[i+3].getValue()==hand[i+4].getValue())
		return true;
		else 
			return false;

		}
public boolean hasFlush() {
	
	int k= hand[0].getSuit();
	
	for (int i=0;i<=hand.length-1;i++) {
	if (hand[i].getSuit()!= k) {
		return false;}
		}
	return true;

}	
public boolean hasStraight() {
	sortByValue();
	int num1; 
	int num2;
	for (int k=0;k<hand.length-1;k++) {
			 num1= hand[k].getValue();
				 num2= hand[k+1].getValue();
				 if (num1!=num2-1) {
			return false;}
	}
	return true;
}
public boolean hasFullHouse() {
	sortByValue();
	int k=0;
		if ((hand[k].getValue()==hand[k+1].getValue()&&hand[k].getValue()==hand[k+2].getValue()&&hand[k+3].getValue()==hand[k+4].getValue()) || (hand[k].getValue()==hand[k+1].getValue()&&hand[k+2].getValue()==hand[k+3].getValue()&&hand[k+3].getValue()==hand[k+4].getValue()))
			return true;
		else
	return false;
}
public boolean hasFourOfAKind() {
	sortByValue();
	int k=0;
	if ((hand[k].getValue()==hand[k+1].getValue()&&hand[k+1].getValue()==hand[k+2].getValue()&&hand[k+2].getValue()==hand[k+3].getValue()) || (hand[k+1].getValue()==hand[k+2].getValue()&&hand[k+2].getValue()==hand[k+3].getValue()&&hand[k+3].getValue()==hand[k+4].getValue()))
	return true;
	else
		return false;
}
public Card highestValue() {
	sortByValue();
	Card k= null;
	k= hand[4];
	 return k;
	
}

public Card highestDuplicate() {
	if (this.getCardCount() < 2) {
return null;	}
sortByValue();
	Card highest = null;	
	Card temp = hand[0];
	for (int i = 1; i < this.getCardCount(); i++) {
		if (hand[i].getValue() == temp.getValue()) {
			if (highest == null) {
				highest = hand[i];}
				else if (highest.getValue() < hand[i].getValue()) {
					highest = hand[i];}
}
		temp = hand[i];}
	return highest;}

public int compareTo(Hand h) {
    if(this.hasStraight() && this.hasFlush()){
        if(!(h.hasFlush() && h.hasStraight()))
        	return 1;
        else {
        	if(h.highestValue().getValue()>this.highestValue().getValue())
        		return -1;
        		else if(h.highestValue().getValue()<this.highestValue().getValue())
        			return 1;
        		else
        			return 0;}
 }
    if(h.hasStraight() && h.hasFlush()) {	
    	return -1; }
    if(this.hasFourOfAKind()){
    		if(!(h.hasFourOfAKind()))
    			return 1;
    		else{
    			if(h.highestDuplicate().getValue()>this.highestDuplicate().getValue())
    				return -1;
    			else if(h.highestDuplicate().getValue()<this.highestDuplicate().getValue())
    				return 1;
    			else
    				return 0;}
}
    if(h.hasFourOfAKind()){
    	return -1;}
    		if(this.hasFullHouse()) {
    			if(!(h.hasFullHouse()))
    				return 1;
    			else{
    				if(h.highestDuplicate().getValue()>this.highestDuplicate().getValue())
    					return -1;
    				else if(h.highestDuplicate().getValue()<this.highestDuplicate().getValue())
    					return 1;
    				else
    					return 0;  }
}
    		if(h.hasFullHouse()){
    			return -1;}
    		if(this.hasFlush()){
    			if(!(h.hasFlush()))
    				return 1;
    			else{
    				if(h.highestValue().getValue()>this.highestValue().getValue())
    					return -1;
    				else if(h.highestValue().getValue()<this.highestValue().getValue())
    					return 1;
    				else
    					return 0;}
 }
    		if(h.hasFlush()){
    			return -1;}
    		if(this.hasStraight()){
    			if(!(h.hasFlush()))
    				return 1;
    			else{
    				if(h.highestValue().getValue()>this.highestValue().getValue())
    					return -1;
    				else if(h.highestValue().getValue()<this.highestValue().getValue())
    					return 1;
    				else
    					return 0;}
 }
    		if(h.hasStraight()){
    			return -1;}
    		if(this.hasTriplet()){
    			if(!(h.hasTriplet()))
    				return 1;
    			else{
    				if(h.highestDuplicate().getValue()>this.highestDuplicate().getValue())
    					return -1;
    				else if(h.highestDuplicate().getValue()<this.highestDuplicate().getValue())
    					return 1;
    				else
    					return 0;}
 }
    		if(h.hasTriplet()){
    			return -1;}
    		if(this.numPairs()==2){
    			if(h.numPairs()<2)
    				return 1;
    			else{
    				if(h.highestDuplicate().getValue()>this.highestDuplicate().getValue())
    					return -1;
    				else if(h.highestDuplicate().getValue()<this.highestDuplicate().getValue())
    					return 1;
    				else
    					return 0;}
}
    		if(h.numPairs()==2){
    			return -1;}
    		if(this.numPairs()==1){
    			if(h.numPairs()<1)
    				return 1;
    			else{
    				if(h.highestDuplicate().getValue()>this.highestDuplicate().getValue())
    					return -1;
    				else if(h.highestDuplicate().getValue()<this.highestDuplicate().getValue())
    					return 1;
    				else
    					return 0; }
}
    		if(h.numPairs()==1){
    			return -1;}
    		if(h.highestValue().getValue()>this.highestValue().getValue())
    			return -1;
    		else if(h.highestValue().getValue()<this.highestValue().getValue())
    			return 1;
    		else
    			return 0;}
	
}
        