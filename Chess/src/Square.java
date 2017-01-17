import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Square extends JPanel implements MouseListener{
	//private member variables
	Color color = new Color(65,105,225);
	private static GameBoard board;
	public int rows,columns,r,c;
	private boolean first=true;
	public ChessPiece piece;
	//add chess piece variable
	
	public Square(int r, int c, GameBoard g, Color d){
		rows=r;
		columns=c;
		board=g;
		color=d;
		this.addMouseListener(this);
		this.setBackground(d);
	}
	
	/**Mouse Stuff START**/
	public void mouseClicked(MouseEvent arg0){
		
	}

	public void mouseEntered(MouseEvent arg0){
		if(board.getOrig()!=null&&board.getDest()==null)
			if(this==board.getOrig())
				this.setBackground(new Color(70,130,180));//click
		if(this!=board.getOrig()||(board.getOrig()!=null&&board.getDest()==null))
			this.setBackground(new Color(65,105,225));//highlight
	this.repaint();
	}

	public void mouseExited(MouseEvent arg0){
		if(this!=board.getOrig())
			this.setBackground(color);
		this.repaint();
	}

	public void mousePressed(MouseEvent arg0){
		board.Clicked(this);
	}

	public void mouseReleased(MouseEvent arg0){
		
	}
	
	/**Mouse Stuff END**/
	
	
	
	//override paint component
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(piece!=null)
			piece.draw(g);
	}
	
	public void setPiece(ChessPiece p){
		piece = p;
		if(p!=null)
			piece.setLocation(this);
	}
	
	public void promotePiece(ChessPiece q){
		piece = q;
			piece.setLocation(this);
	}
	public Color getColor(){return color;}//gets color
	public GameBoard GetGameBoard(){return board;}//gets gameboard
	public String toString(){return "("+rows+","+columns+")";}//tostring
	public ChessPiece getPiece(){return piece;}
}