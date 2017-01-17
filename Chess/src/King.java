import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class King extends ChessPiece{
	//constructor
	public King(Square loc, String img, boolean t){
		super(loc, img, t);
	}
	
	public boolean isMoveLegal(Square q) {
		int br=0,bc=0;
		int row1, col1;
		int wc=q.columns;
		int wr=q.rows;
		if(Team==false){
		bc=GameBoard.WhiteKing.getSquare().columns;
		br=GameBoard.WhiteKing.getSquare().rows;
		}
		if(Team==true){
		bc=GameBoard.BlackKing.getSquare().columns;
		br=GameBoard.BlackKing.getSquare().rows;
		}
		col1=this.getSquare().columns-q.columns; //col math
		row1=this.getSquare().rows-q.rows;  //row math
		if(Math.abs(wc-bc)<=1 && Math.abs(wr-br)<=1)
					return false;
		if((q.piece!=null && q.piece.Team==this.Team))//cant eat teammates
			return false;
		if((Math.abs(row1)==1 && Math.abs(col1)==1)||(row1==0 && Math.abs(col1)==1)||(Math.abs(row1)==1 && col1==0))// if only moves one spot return true
			return true;
		return false;
	}
	public boolean canCastle(Square q){
		int br=0,bc=0;
		int wc=q.columns;
		int wr=q.rows;
		bc=GameBoard.WhiteKing.getSquare().columns;
		br=GameBoard.WhiteKing.getSquare().rows;
		if(bc==wc && br==wr){
			return true;
		}
		return false;
	}
	public boolean canPromote(){return false;}

	public String pieceName() {//string name
		if(Team)
			return "Black King";
		else
			return "White King";
	}

}
