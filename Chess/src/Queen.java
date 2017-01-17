import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Queen extends ChessPiece{
//constructor
	public Queen(Square loc, String img, boolean t) {
		super(loc, img, t);
		
	}
	public boolean isMoveLegal(Square dest) {
		int row1, col1;
		col1=this.getSquare().columns-dest.columns; //col math
		row1=this.getSquare().rows-dest.rows;  //row math
		if((dest.piece!=null && dest.piece.Team==this.Team))//makes sure cant eat team
			return false;
		if((((Math.abs(row1)== Math.abs(col1))|| (row1==0 && col1!=0)||(row1!=0 && col1==0)))&& !this.getSquare().GetGameBoard().isBlocked(this.getSquare(), dest))
			return true;
		return false;
		
	}
	public boolean canPromote(){//cant promote since its a queen
		
		return false;
	}
	
	public String pieceName() {//string name
		if(Team)
			return "Black Queen";
		else
			return "White Queen";
	}
}