import java.awt.*;
public abstract class ChessPiece {
	private Square loc;
	private Image img;
	public boolean Team;
	public static final boolean BLACK=true;
	public static final boolean WHITE=false;
	
	public ChessPiece(Square lc, String im, boolean t){
		loc=lc;
		Team=t;
		MediaTracker mt = new MediaTracker(new Component(){});
		img = Toolkit.getDefaultToolkit().getImage(im);
		mt.addImage(img, 1);
		try{mt.waitForAll();}
		catch(Exception ex){ex.printStackTrace();};
	}
	public void draw(Graphics g){
			g.drawImage(img,0, 0, 100, 100, null, null);
	}
	public boolean turnCheck(){
		if(!Team){
			if(this.getSquare().GetGameBoard().turn%2==0)
				return true;}
		else if(Team){
			if(this.getSquare().GetGameBoard().turn%2==1)
				return true;}
		return false;
	}
	public abstract boolean isMoveLegal(Square dest);
	public abstract String pieceName();
	public abstract boolean canPromote();
	public void setLocation(Square l){loc=l;}
	public Square getSquare(){return loc;}
}