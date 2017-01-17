import java.awt.*;
import java.util.*;
import javax.swing.*;
public class MazeCell extends JPanel{
	private boolean[] borders;
	private static final int UP = 0, RIGHT = 1, DOWN=2, LEFT = 3;
	public static final int BLANK=0, VISITED=1, DEAD=2;
	private Color[] colors = {Color.BLACK, Color.BLUE, Color.GRAY};
	
	private int status;
	private int rows, cols;
	public MazeCell(int r, int c){
		super();
		rows =r;
		cols = c;
		borders = new boolean[4];
		for(int i=0; i<4; i++)
			borders[i] = true;
		this.setBackground(Color.BLACK);
		status = BLANK;
	}

	public int row(){return rows;}
	public int col(){return cols;}
	
	public void setStatus(int x){
		status = x;
		this.setBackground(colors[status]);
		this.paint(this.getGraphics());
	}
	public boolean isBlank(){return status==BLANK;}
	public boolean isVisited(){return status==VISITED;}
	public boolean isDead(){return status==DEAD;}
	
	public boolean blocked(int dir){return borders[dir];}
	
	public void ArcLite(int dir){borders[dir] = false; repaint();}
	public void clearUp(){borders[UP] = false; repaint();}
	public void clearDown(){borders[DOWN] = false; repaint();}
	public void clearRight(){borders[RIGHT] = false; repaint();}
	public void clearLeft(){borders[LEFT] = false; repaint();}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		if(borders[UP])
			g.drawLine(0,0,this.getWidth(),0);
		if(borders[RIGHT])
			g.drawLine(this.getWidth(),0,this.getWidth(),this.getHeight());
		if(borders[DOWN])
			g.drawLine(0,this.getHeight(),this.getWidth(),this.getHeight());
		if(borders[LEFT])
			g.drawLine(0,0,0,this.getHeight());
		g.setColor(Color.BLACK);
		//g.drawString(this.toString(), 5, 15);
		//g.setColor(colors[status]);
		//g.fillOval(this.getWidth()/3,this.getHeight()/3,this.getWidth()/3,this.getHeight()/3 );
	}

	public String toString(){
		return "("+rows+", "+cols+")";
	}
}
