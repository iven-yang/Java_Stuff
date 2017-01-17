import java.awt.*;
import java.util.*;
import java.util.Timer;
import javax.swing.*;
import java.awt.event.*;
public class Maze extends JFrame implements ActionListener{
	private JPanel mazeP, buttonP;
	private int numRow, numCol;
	private MazeCell[][] grid;
	private static final int UP = 0, RIGHT = 1, DOWN =2, LEFT = 3;
	public static final int BLANK=0, VISITED=1, DEAD=2;
	private JButton Reset, Solve;
	//private Timer timer=new Timer();
	private boolean first=true;
	private Stack<MazeCell> solver;
	private Stack<MazeCell> creator=new Stack();
	private MazeCell end,start;
	private double sec=0;
	private int realtime,min;
	private JTextField Timer;
	public Maze(int ro,int co){
		super("----------------------------------------------------------------------------------------Sonar Maze-------------------------------------------------------------------------");
		
		solver=new Stack();
		
		numRow=ro;
		numCol=co;
		
		mazeP = new JPanel();
		mazeP.setLayout(new GridLayout(numRow, numCol));
	
		buttonP = new JPanel();
		buttonP.setLayout(new GridLayout(1,3,10,10));
		
		Solve = new JButton("Solve");
		Solve.addActionListener(this);
		Reset = new JButton("Make Default Maze");
		Reset.addActionListener(this);
		Timer=new JTextField("Time- 0.00 sec");
		
		grid = new MazeCell[numRow][numCol];
		
		for(int r=0; r<numRow; r++)
			for(int c=0; c<numCol; c++){
				grid[r][c] = new MazeCell(r,c);
				mazeP.add(grid[r][c]);
			}
		
		this.add(mazeP, BorderLayout.CENTER);
		this.add(buttonP, BorderLayout.NORTH);
		
		buttonP.add(Solve, BorderLayout.WEST);
		buttonP.add(Reset, BorderLayout.EAST);
		buttonP.add(Timer, BorderLayout.EAST);

		this.setSize(983,983);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
	public static void main(String[] args) {
		Maze CornField=new Maze(30,30);
	}
	
	public boolean isValid(int r, int c){
		if(r>-1 && r<numRow)
			if(c>-1 && c<numCol)
				return true;
		return false;
	}
	
	public boolean StepMaze(){
		if(solver.peek().col()==numCol-1){
			System.out.println("Bombardment Complete");		
			JOptionPane.showMessageDialog(this, "The Maze is Solved. I am a Genius.    "+Timer.getText());	
			return true;	
		}
		if(moveRight())
			return false;
		else if(moveDown())
			return false;
		else if(moveLeft())
			return false;
		else if(moveUp())
			return false;
		else{ 
			solver.peek().setStatus(DEAD);
			solver.pop();
			return false;
		}
	}
	
	public boolean moveLeft(){
		if(this.isValid(solver.peek().row(),solver.peek().col()-1) && grid[solver.peek().row()][solver.peek().col()-1].isBlank())
			if(!solver.peek().blocked(LEFT)){
				solver.push(grid[solver.peek().row()][solver.peek().col()-1]);
				grid[solver.peek().row()][solver.peek().col()].setStatus(VISITED);
				return true;
			}
		return false;
	}
	
	public boolean moveUp(){
		if(this.isValid(solver.peek().row()-1,solver.peek().col()) && grid[solver.peek().row()-1][solver.peek().col()].isBlank())
			if(!solver.peek().blocked(UP)){
				solver.push(grid[solver.peek().row()-1][solver.peek().col()]);
				grid[solver.peek().row()][solver.peek().col()].setStatus(VISITED);
				return true;
			}
		return false;
	}
	
	public boolean moveRight(){
		if(this.isValid(solver.peek().row(),solver.peek().col()+1) && grid[solver.peek().row()][solver.peek().col()+1].isBlank())
			if(!solver.peek().blocked(RIGHT)){
				solver.push(grid[solver.peek().row()][solver.peek().col()+1]);
				grid[solver.peek().row()][solver.peek().col()].setStatus(VISITED);
				return true;
			}
		return false;
	}
	
	public boolean moveDown(){
		if(this.isValid(solver.peek().row()+1,solver.peek().col()) && grid[solver.peek().row()+1][solver.peek().col()].isBlank())
			if(!solver.peek().blocked(DOWN)){
				solver.push(grid[solver.peek().row()+1][solver.peek().col()]);
				grid[solver.peek().row()][solver.peek().col()].setStatus(VISITED);
				return true;
			}
		return false;
	}
	
	public MazeCell getNeighbor(MazeCell cells, int dir){//gets neighbors
		if(dir==UP && cells.row()!=0)
			return grid[cells.row()-1][cells.col()];
		if(dir==RIGHT && cells.col() != numCol-1)
			return grid[cells.row()][cells.col()+1];
		if(dir==DOWN && cells.row()!=numRow-1)
			return grid[cells.row()+1][cells.col()];
		if(dir==LEFT && cells.col()!=0)
			return grid[cells.row()][cells.col()-1];
		return cells;
	}

	public ArrayList<MazeCell> blankNeighbors(MazeCell cells){//gets blank neighbors
		ArrayList<MazeCell> Stalkers = new ArrayList();
		for(int i=0; i<4;i++){
			if(this.isValid(this.getNeighbor(cells, i).row(),this.getNeighbor(cells, i).col())&& this.getNeighbor(cells, i).isBlank())
				Stalkers.add(this.getNeighbor(cells, i));
		}
		return Stalkers;
	}
	
	public int directionFrom(MazeCell orig, MazeCell dest){
		if(orig.row()-dest.row()==1)
			return UP;
		if(orig.row()-dest.row()==-1)
			return DOWN;
		if(orig.col()-dest.col()==1)
			return LEFT;
		if(orig.col()-dest.col()==-1)
			return RIGHT;
		return -1;
	}
	
	public void makeMove(){
		boolean mademove = false;
		int rand;
		while(!mademove){
			rand = (int)(Math.random()*4);
			if(rand == UP && isValid(creator.peek().row()-1,creator.peek().col()) && grid[creator.peek().row()-1][creator.peek().col()].isBlank() && grid[creator.peek().row()-1][creator.peek().col()].blocked(DOWN)){
				creator.peek().clearUp();
				this.getNeighbor(creator.peek(), UP).clearDown();
				creator.push(this.getNeighbor(creator.peek(), UP));
				creator.peek().setStatus(VISITED);
				mademove=true;					
			}
			else if(rand == RIGHT && isValid(creator.peek().row(),creator.peek().col()+1) && grid[creator.peek().row()][creator.peek().col()+1].isBlank() && grid[creator.peek().row()][creator.peek().col()+1].blocked(LEFT)){
				creator.peek().clearRight();
				this.getNeighbor(creator.peek(), RIGHT).clearLeft();
				creator.push(this.getNeighbor(creator.peek(), RIGHT));
				creator.peek().setStatus(VISITED);
				mademove=true;
			}
			else if(rand == DOWN && isValid(creator.peek().row()+1,creator.peek().col())&& grid[creator.peek().row()+1][creator.peek().col()].isBlank() && grid[creator.peek().row()+1][creator.peek().col()].blocked(UP)){
				creator.peek().clearDown();
				this.getNeighbor(creator.peek(), DOWN).clearUp();
				creator.push(this.getNeighbor(creator.peek(), DOWN));
				creator.peek().setStatus(VISITED);
				mademove=true;
			}
			else if(rand == LEFT && isValid(creator.peek().row(),creator.peek().col()-1) && grid[creator.peek().row()][creator.peek().col()-1].isBlank() && grid[creator.peek().row()][creator.peek().col()-1].blocked(RIGHT)){
				creator.peek().clearLeft();
				this.getNeighbor(creator.peek(), LEFT).clearRight();
				creator.push(this.getNeighbor(creator.peek(), LEFT));
				creator.peek().setStatus(VISITED);
				mademove=true;
			}
			else if(blankNeighbors(creator.peek()).size()==0){
				creator.peek().setStatus(DEAD);
				creator.pop();
				mademove=true;
			}
		}
		if(!creator.isEmpty())
		creator.peek().setStatus(VISITED);
		mademove=true;
	}
	
	public void MakeMaze(){
		while(!creator.isEmpty())
			creator.pop();
		while(!solver.isEmpty())
			solver.pop();
		
		int a=(int)(Math.random()*(numRow));
		creator.push(grid[a][0]);
		creator.peek().clearRight();
		start=creator.peek();
		
		creator.peek().setStatus(MazeCell.VISITED);
		
		for(int i=0;i<numRow;i++)
			if(i!=a)
				grid[i][0].setStatus(MazeCell.DEAD);
		
		a=(int)(Math.random()*(numRow));
		end=grid[a][numCol-1];
		end.setStatus(MazeCell.VISITED);
		end.ArcLite(LEFT);
		this.getNeighbor(end, LEFT).clearRight();
		
		for(int i=0;i<numRow;i++)
			if(i!=a)
				grid[i][numCol-1].setStatus(MazeCell.DEAD);
		
		makeMove();
		while(creator.peek()!=start){
			makeMove();
		}
		
		for(int r=0; r<numRow; r++)
			for(int c=0; c<numCol; c++){
				grid[r][c].setStatus(BLANK);
			}
		solver.push(creator.peek());
		this.repaint();
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==Solve){
			System.out.println("Engaging Bombardment...");
			System.out.println();
			//this.StepMaze();
			min=0;
			sec=0;
			while(!this.StepMaze()){
				try{Thread.sleep(1);} catch (Exception ex){ex.printStackTrace();}
				
				sec+=0.52*((double)2/100);
				realtime=(int)((double)sec*1000);
				sec=(double)realtime/1000;
				
				if(sec>=60){
					sec-=60;
					min++;
				}
				if(min==0)
					Timer.setText("Time- "+sec+" seconds");
				else
					Timer.setText("Time- "+min+" min "+sec+" sec");
				Timer.paint(Timer.getGraphics());
				System.out.println("Fire the Laser!");
			}
		}
		if(e.getSource()==Reset){
			if(first){
				this.remove(mazeP);	
				mazeP = new JPanel();
				
				mazeP.setLayout(new GridLayout(numRow, numCol));
				
				grid = new MazeCell[numRow][numCol];
				
				for(int r=0; r<numRow; r++)
					for(int c=0; c<numCol; c++){
						grid[r][c] = new MazeCell(r,c);
						mazeP.add(grid[r][c]);
					}
				this.add(mazeP, BorderLayout.CENTER);
				repaint();
				MakeMaze();
				first=false;
				Reset.setText("New Maze");
			}
			else{
				numRow = Integer.parseInt(JOptionPane.showInputDialog(this, "Number of rows:"));
				numCol = Integer.parseInt(JOptionPane.showInputDialog(this, "Number of columns:"));
				
				this.remove(mazeP);	
				mazeP = new JPanel();
				
				mazeP.setLayout(new GridLayout(numRow, numCol));
				
				grid = new MazeCell[numRow][numCol];
				
				for(int r=0; r<numRow; r++)
					for(int c=0; c<numCol; c++){
						grid[r][c] = new MazeCell(r,c);
						mazeP.add(grid[r][c]);
					}
				this.add(mazeP, BorderLayout.CENTER);
				
				repaint();
				
				Timer.setText("Time- 0.00 sec");
				
				MakeMaze();
				
				
				this.validate();
				first=false;
			}
		}
	}
}