import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Deck {
	public CircularLinkedList deck = new CircularLinkedList();

	public Deck(){
		for(int x=1; x<14; x++){
			deck.add(new Card(x,'h'));
		}
		for(int z=1; z<14; z++){
			deck.add(new Card(z,'s'));
		}
		for(int c=1; c<14; c++){
			deck.add(new Card(c,'d'));
		}
		for(int b=1; b<14;b++){
			deck.add(new Card(b,'c'));
		}
	}
	
	public Card deal(){//removes a card
		int x=(int)(Math.random()*deck.size());
		while(x>=deck.size())
			x--;
		Card c = (Card)deck.Get(x);
		deck.Remove(x);
		return c;
	}
	
	public String toString(){
		if(deck.isEmpty())
			return "";
		return deck.toString();
	}	
}
