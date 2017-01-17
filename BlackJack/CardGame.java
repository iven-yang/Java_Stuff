import java.util.*;
public class CardGame {
	public static void main(String[] args){
		int x=1;
		Deck bob = new Deck();
		Hand Handy=new Hand();
		Card Inserter;
		/*System.out.println("Deck: "+bob);
		System.out.println();
		while(bob.deck.isEmpty()==false){
				System.out.println("DEALT: "+bob.deal());
				System.out.println("Deck: "+bob);
				System.out.println(x);
				x++;
		}*/
		System.out.println("NEW GAME");
		for(int i=0;i!=4;i++){
			Inserter=bob.deal();
			Handy.insert(Inserter);
			System.out.println(Handy);
			int value=Handy.evaluate();
			System.out.println("Value: "+value);
			if(value>21){
				System.out.println("BUST!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				break;
			}
			if(value==21){
				System.out.println("BlackJack!");
				break;
			}
			System.out.println("***************************************");
		}
	}
}