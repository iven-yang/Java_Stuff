import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Pawn extends ChessPiece{
	//private member variables
	private int direction;
	public boolean HasMoved = false;
	public Pawn(Square loc, String img, boolean t){
		super(loc,img,t);
		if(Team)
			direction=1;
		else 
			direction=-1;

	}
	public boolean canPromote(){//if the piece can be promoted it will return true
		if(this.getSquare().rows==0 || this.getSquare().rows==7)// if in the last row 
			return true;
		return false;
	}

	public boolean isMoveLegal(Square dest){
		int row1, col1;
		col1=this.getSquare().columns-dest.columns;//col math
		row1=this.getSquare().rows-dest.rows;//row math
	
		if(this.Team && dest.piece==null){ //Black moving to empty space
			if(HasMoved!=true){ //if its the first move
				if((row1==-1 && col1==0) || (row1==-2 && col1==0 && !this.getSquare().GetGameBoard().isBlocked(this.getSquare(), dest))){ //if move 1 forward or 2 forward its ok
					HasMoved=true;
					return true;
				}
			}
			else {
				if(row1==-1 && col1==0)
					return true;
			}
		}
		else if(!this.Team && dest.piece==null){// if destination not null and white
			if(HasMoved!=true){//if it has has moved already 
				if(row1==1 && col1==0 || row1==2 && col1==0 && !this.getSquare().GetGameBoard().isBlocked(this.getSquare(), dest)){
					HasMoved=true;
					return true;
					}
			}
			else{
				if(row1==1 && col1==0)
					return true;
				}

		}
		else if( dest.piece!=null && this.Team && !dest.piece.Team){ //black eats
				if(row1==-1 && col1==1 || row1==-1 && col1==-1 ){ //take
					HasMoved=true;
					return true;}
		}
		else if(dest.piece!=null && !this.Team && dest.piece.Team){ //white eats
				if(row1==1 && col1==-1 || row1==1 && col1==1 ){
					HasMoved=true;
					return true;}
		}
		return false;
	}

	public String pieceName() {//string name
		if(Team)
			return "Black Pawn";
		else
			return "White Pawn";

	}
}
