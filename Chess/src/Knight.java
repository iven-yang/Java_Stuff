import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Knight extends ChessPiece{
	
	//constructor
	public Knight(Square loc, String img, boolean t) {
		super(loc, img, t);	
	}
	public boolean isMoveLegal(Square dest) {
		int row1, col1;
		col1=this.getSquare().columns-dest.columns; //col math
		row1=this.getSquare().rows-dest.rows;  //row math
		if(dest.piece!=null && (dest.piece.Team==this.Team))//can eat teammates
			return false;
		else
			if((Math.abs(col1)==1 && Math.abs(row1)==2) || (Math.abs(col1)==2 && Math.abs(row1)==1))//making it sure its moving right
				return true;
		return false;
	}
	public boolean canPromote(){

		return false;}

	public String pieceName() {//string name
		if(Team)
			return "Black Knight";
		else
			return "White Knight";
	}

}
