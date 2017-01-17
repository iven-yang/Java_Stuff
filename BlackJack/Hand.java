import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
public class Hand {
	//private member variables
	public ArrayList<Card> hand= new ArrayList<Card>();
	public Hand(){}
	public void Draw(Graphics g, int x, int y){}
	public String toString(){
		if(hand.isEmpty())
			return null;
		return hand.toString();
	}
	//value
	public int evaluate(){
		int vrCount =0;
		int aces=0;
		for(int i=0;i<hand.size();i++){
			//faces
			if(hand.get(i).getValue()>10)
				vrCount+=10;
			//aces 1 or 11
			else if(hand.get(i).getValue()==1){
				vrCount+=11;
				aces++;}
			else
				vrCount+=hand.get(i).getValue();
		}
		//if bust, ace becomes 1
		while(vrCount>21 && aces>0){
			aces--;
			vrCount-=10;
		}
		return vrCount;
	}
	//inserts card
	public void insert(Card card){
		hand.add(card);
	}
}