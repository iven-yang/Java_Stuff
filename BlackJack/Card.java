import java.awt.*;
import javax.swing.JOptionPane;
public class Card {
	private int value;
	private char suit;
	public Image img;
	private Image front;
	private static Image back=Toolkit.getDefaultToolkit().getImage("./pics/b2fv.png");
	//constructor
	public Card(int v, char s){
		value=v;
		suit=s;
		loadImage();
	}
	//prints out card in words
	public String toString(){
		String realsuite = "";
		String stringy = "";
		if(suit=='h')
			realsuite = "Hearts";
		if(suit=='d')
			realsuite = "Diamonds";
		if(suit=='c')
			realsuite = "Clubs";
		if(suit=='s')
			realsuite = "Spades ";
		
		if(value==1)
			return "Ace of "+realsuite;
		if(value==11)
			return "Jack of "+realsuite;
		if(value==12)
			return "Queen of "+realsuite;
		if(value==13)
			return "King of "+realsuite;
		return value+" of "+realsuite;
	}	
	public void draw(Graphics g, int x, int y){
		g.drawImage(img, x,y,null);
	}	
	public void faceUp(){
		loadImage();
	}
	public void faceDown(){
		img = back;
	}	
	public Image loadImage(){
		//String filename = "./pics/C8.png";
		img = Toolkit.getDefaultToolkit().getImage("./pics/"+suit+""+value+".png");
	    MediaTracker tracker = new MediaTracker (new Component () {});
	    tracker.addImage(img, 0);
	    tracker.addImage(back,1);
	    //block while reading image
	    try { tracker.waitForAll (); }
	    catch (InterruptedException e) {
	        JOptionPane.showMessageDialog(null, "Error reading file");
	    }
	    return img;
	}
	public int getValue(){return value;}//gets value
	public char getSuit(){return suit;}//gets suit (char)
}
