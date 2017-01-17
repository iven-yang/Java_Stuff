import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
public class GameBoard extends JFrame {
	//private member variables
	
	//**************NOTICE*****************//
	//pawns will auto pawn promote to queens
	//has check enabled
	//kings can't move next to each other
	private Square[][] squares= new Square[8][8];///chessboard
	private Square orig, dest;//squares
	public int turn=0;//turns
	private ArrayList<ChessPiece> pieces = new ArrayList();//checks stuff
	public static ChessPiece WhiteKing;//tabs on kings
	public static ChessPiece BlackKing;
	public boolean check;//for check
	public GameBoard(){
		super("Chess");
		this.setLayout(new GridLayout(8,8));
			for(int row=0; row<8;row++)
				for(int col=0; col<8;col++){
					if((row+col)%2==0){
						squares[row][col]= new Square(row,col,this,new Color(238,221,130));
						this.add(squares[row][col]);
					}
					else{
						squares[row][col]= new Square(row,col,this,new Color(26,15,0));
						this.add(squares[row][col]);
					}
				}
		//**************PIECES***************//
			for(int i=0;i<8;i++){//pawns
				squares[6][i].setPiece(new Pawn(squares[6][i], "WhitePawn.png", false));
				squares[1][i].setPiece(new Pawn(squares[1][i], "BlackPawn.png", true));
			}
		//rooks
		squares[0][0].setPiece(new Rook(squares[0][0],"BlackRook.png" , true));
		squares[0][7].setPiece(new Rook(squares[0][7],"BlackRook.png" , true));
		squares[7][0].setPiece(new Rook(squares[7][0],"WhiteRook.png" , false));
		squares[7][7].setPiece(new Rook(squares[7][7],"WhiteRook.png" , false));
		
		//knights
		squares[0][1].setPiece(new Knight(squares[0][1],"BlackKnight.png" , true));
		squares[0][6].setPiece(new Knight(squares[0][6],"BlackKnight.png" , true));
		squares[7][1].setPiece(new Knight(squares[7][1],"WhiteKnight.png" , false));
		squares[7][6].setPiece(new Knight(squares[7][6],"WhiteKnight.png" , false));
		
		//bishops
		squares[0][2].setPiece(new Bishop(squares[0][2],"BlackBishop.png" , true));
		squares[0][5].setPiece(new Bishop(squares[0][5],"BlackBishop.png" , true));
		squares[7][2].setPiece(new Bishop(squares[7][2],"WhiteBishop1.png" , false));
		squares[7][5].setPiece(new Bishop(squares[7][5],"WhiteBishop2.png" , false));
		
		//queens
		squares[0][3].setPiece(new Queen(squares[0][4],"BlackQueen.png" , true));
		squares[7][3].setPiece(new Queen(squares[7][4],"WhiteQueen.png" , false));
		
		//kings
		squares[0][4].setPiece(WhiteKing=new King(squares[0][4],"BlackKing.png" , true));
		squares[7][4].setPiece(BlackKing=new King(squares[7][4],"WhiteKing.png" , false));

		for(int i=0;i<=7;i++)//w pawn
		pieces.add(squares[1][i].piece);
	
		for(int i=0;i<=7;i++)//b pawn
		pieces.add(squares[6][i].piece);
	
		//black rook
		pieces.add(squares[0][0].piece);
		pieces.add(squares[0][7].piece);
		//white rook
		pieces.add(squares[7][0].piece);
		pieces.add(squares[7][7].piece);
		//black queen
		pieces.add(squares[0][3].piece);
		//white queen
		pieces.add(squares[7][3].piece);
		//black knight
		pieces.add(squares[0][1].piece);
		pieces.add(squares[0][6].piece);
		//white knight
		pieces.add(squares[7][1].piece);
		pieces.add(squares[7][6].piece);
		//black bishop
		pieces.add(squares[0][2].piece);
		pieces.add(squares[0][5].piece);
		//white bishop
		pieces.add(squares[7][2].piece);
		pieces.add(squares[7][5].piece);
		//black king
		pieces.add(squares[0][4].piece);
		//white king
		pieces.add(squares[7][4].piece);
		
		//board
		this.setSize(1050,980);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void Clicked(Square s){
	
		//click
		if(orig==null){
			if(s.piece!=null){
				orig=s;
				orig.setBackground(new Color(153,50,204));}	
		}
		//click
		else{
			dest=s;
			if(orig.piece!=null && orig.piece.isMoveLegal(dest) && orig.piece.turnCheck()){
				ChessPiece checkpiece = dest.piece;//for check
				if(dest.piece!=null) 
					dest.piece.setLocation(null);
				
				dest.setPiece(orig.piece);//moving
				dest.piece.setLocation(dest);
				orig.setPiece(null);
				System.out.println("you moved from "+orig.rows+", "+orig.columns+" to "+dest.rows+", "+dest.columns);
				if(this.check()&& (turn%2==0 && !check)) {//white check
					JOptionPane.showMessageDialog(null,"The Protoss: " +
							"Tassadar is under attack! (Move out of check)");
					orig.setPiece(dest.piece);
					dest.setPiece(checkpiece);
					turn--;
				}
				if(this.check()&& (turn%2==1 && check)){//black check
					JOptionPane.showMessageDialog(null,"The Terran: " +
							"Commandar is under Attack! (Move out of check)");
					orig.setPiece(dest.piece);
					dest.setPiece(checkpiece);
					turn--;}
				orig.repaint();
				dest.repaint();
				turn++;
				if(this.check()&& !check)//white check
					JOptionPane.showMessageDialog(this, "The Protoss: " +
							"Base is under attack!");
				if(this.check()&& check)//black check
					JOptionPane.showMessageDialog(this, "The Terran: " +
							"Base is under attack!");
			}
			else if(orig.piece!=null &&!orig.piece.turnCheck()){
				JOptionPane.showMessageDialog(null,"Wait your turn");}//turn wrong
			else{
				JOptionPane.showMessageDialog(null, "Unacceptable Command");}//weird move
			orig.setBackground(orig.getColor());
			
			orig=null;
			dest=null;
			if(s.piece!=null && s.piece.canPromote()){
				int awe = pieces.indexOf(s.piece);
				pieces.set(awe,s.piece);
				if(s.piece.pieceName()=="Black Pawn")
					s.piece.Team=true;
				else
					s.piece.Team=false;	
				promote(s.piece);
			}
		}
			
	}
	public boolean isBlocked(Square start, Square end){//moves not blocked
		int row1, col1;
		col1=end.columns-start.columns;//col
		row1=end.rows-start.rows;//row
		
		if(col1!=0)
			col1= col1/(Math.abs(col1));
		if(row1!=0)
			row1= row1/(Math.abs(row1));
		
		int rowIndex = start.rows+row1;
		int colIndex = start.columns+col1;
		
		while(rowIndex!=end.rows || colIndex!=end.columns){
			System.out.println(squares[rowIndex][colIndex]+": "+squares[rowIndex][colIndex].piece!=null);
			if(squares[rowIndex][colIndex].piece!=null)
				return true;
			rowIndex+=row1;
			colIndex+=col1;
		}
		return false;
	}
	public static void main(String[] args) {
		new GameBoard();
	}
	public boolean canattack(ChessPiece p){//can attack king
		if(p.isMoveLegal(WhiteKing.getSquare())){//white check
			check=true;
			return true;}
		if(p.isMoveLegal(BlackKing.getSquare())){//black check
			check=false;
			return true;}
		return false;
	}
	public boolean CheckMate(ChessPiece p){//doesn't work, i got stuck argh
		if(p.isMoveLegal(WhiteKing.getSquare())){//white checkmate
			check=true;
			return true;
		}
		if(p.isMoveLegal(BlackKing.getSquare())){//black checkmate
			check=false;
			return true;
		}
		return false;
	}
	public boolean check(){//checks for check
		for(int i=0; i<pieces.size(); i++){
			if(pieces.get(i).getSquare()!=null && this.canattack(pieces.get(i))){
					/*if(pieces.get(i).getSquare()!=null && this.CheckMate(pieces.get(i))){
						JOptionPane.showMessageDialog(this, "CheckMate!");
					}*/
				return true;
			}
		}
		return false;
	}
	public void promote(ChessPiece pez){
		Square x=pez.getSquare();
		int b=x.columns;
		int a=x.rows;
		if(pez.Team)
			squares[a][b].promotePiece(new Queen(squares[a][b],"BlackQueen.png" , false));
		if(!pez.Team)
			squares[a][b].promotePiece(new Queen(squares[a][b],"WhiteQueen.png" , false));
	}
	public Square getOrig(){return orig;}
	public Square getDest(){return dest;}
}