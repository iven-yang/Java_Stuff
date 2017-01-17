import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Rook extends ChessPiece{
	public Rook(Square loc, String img, boolean t){
		super(loc,img,t);
	}
	public boolean isMoveLegal(Square dest) {
		int row1, col1;
		col1=this.getSquare().columns-dest.columns; //col math
		row1=this.getSquare().rows-dest.rows;  //row math
		if((dest.piece!=null && dest.piece.Team==this.Team))//cant eat teammates
			return false;
		if(((row1==0 && col1!=0)||(row1!=0 && col1==0))&& !this.getSquare().GetGameBoard().isBlocked(this.getSquare(), dest))//if moving by only cols or rows return true
			return true;
		return false;
	}
	public boolean canPromote(){
		return false;
		}

	public String pieceName() {//string name 
		if(Team)
			return "Black Rook";
		else
			return "White Rook";
	}
}