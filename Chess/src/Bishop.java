import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Bishop extends ChessPiece{
	
	public Bishop(Square loc, String img, boolean t) {
		super(loc, img, t);
		
	}
	public boolean isMoveLegal(Square dest) {
		int row1, col1;
		col1=this.getSquare().columns-dest.columns; //col math
		row1=this.getSquare().rows-dest.rows;  //row math
		if((dest.piece!=null && dest.piece.Team==this.Team))
			return false;
		if(((Math.abs(row1)== Math.abs(col1))&& !this.getSquare().GetGameBoard().isBlocked(this.getSquare(), dest)))
			return true;
		return false;
		
	}
	public boolean canPromote(){
		return false;
		}

	public String pieceName() {
		if(Team)
			return "Black Bishop";
		else
			return "White Bishop";
	}
}