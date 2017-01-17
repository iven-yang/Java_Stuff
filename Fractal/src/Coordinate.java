import java.awt.*;
public class Coordinate {
	private int x, y;  // x and y values
	
	/************CONSTRUCTORS********************/
	/*
	 * Preconditions: x and y are any integer
	 * Postconditions:  the coordinate is created at (x,y)
	 */
	public Coordinate(int x, int y){
		this.x = x;
		this.y = y;
	}
	/*
	 * Preconditions: c is a valid & instantiated coordinate
	 * Postconditions: this coordinate is a replica of c
	 */
	public Coordinate(Coordinate c){
		x = c.x;
		y = c.y;
	}
	/****************ACCESSORS************/
	
	/* Preconditions: none
	 * Postconditions: the current xvalue is returned*/	
	public int getX(){return x;	}
	/* Preconditions: none
	 * Postconditions: the current yvalue is returned*/
	public int getY(){return y;	}
	
	/**************MUTATORS***********/
	/* Preconditions: r is some ratio (r>0)
	 * Postconditions: this coordinate is moved so that its 
	 * 		distance FROM THE ORIGIN is now equal to r*(original distance)
	 */
	public void scale(double r){
		x = (int)Math.round(r*x);
		y = (int)Math.round(r*y);
	}
	
	/*Preconditions: ratio is the scale factor, center is a valid coordinate
	 *Postconditions: this coordinate is adjusted so that it's distance from 
	 *	the specified coordinate, CENTER, is now equal to ratio*(original distance).
	 *  The angle from center to this coordinate is preserved.
	 */
	public void scale(double ratio, Coordinate center){
		int newx, newy;
		newx = (int)Math.round(ratio*this.x+ (1-ratio)*center.x);
		newy = (int)Math.round(ratio*this.y + (1-ratio)*center.y);
		
		x = newx;
		y = newy;
	}
	/* Preconditions:  theta is some angle in RADIANS
	 * Postconditions: this coordinate is rotated by the 
	 * 		angle theta COUNTERCLOCKWISE about the ORIGIN
	 */
	public void rotate(double theta){
		int oldx=x;
		int oldy=y;
		//multiply by rotation matrix
		x =(int)Math.round(oldx*Math.cos(theta) + oldy*Math.sin(theta));
		y = (int)Math.round(-1*oldx*Math.sin(theta)+ oldy*Math.cos(theta));
	}
	/* Preconditions:  theta is some angle in RADIANS, and center is a valid coordinate
	 * Postconditions: this coordinate is rotated by the 
	 * 		angle theta COUNTERCLOCKWISE about the coordinate CENTER
	 */
	public void rotate(double theta, Coordinate center){
			//this coordinate is some distance D from center
			
			//translate so that this coordinate is now distance D from the origin
			translate(-center.getX(), -center.getY());
			//rotate
			rotate(theta);
			//now translate so that we are the distance D from center
			translate(center.getX(), center.getY());
				
	}
	/*
	 * Preconditions: theta is an angle in radians
	 * 				  center is a valid coordinate
	 * 				  scale is some ratio (>0)
	 * Postconditions:  this coordinate is now rotated by angle theta
	 * 		about center AND scaled to be a distance of scale*(original distance)
	 * 		from center
	 */
	public void rotateScale(double theta, Coordinate center, double scale){
		translate(-center.getX(), -center.getY());
		scale(scale);
		rotate(theta);
		translate(center.getX(), center.getY());		
	}
	
	/* Preconditions:  deltaX and deltaY are any double values
	 * Postconditions:  This coordinate is shifted horizontally by 
	 * 		deltaX and vertically by deltaY
	 */
	public void translate(double deltaX, double deltaY){
		x+=deltaX;
		y+=deltaY;
	}
	
	/* Preconditions:  end is a valid coordinate representing 
	 * 			the endpoint of the desired segment.
	 * 			g is a valid Graphics object. 
	 * Postconditions:  a line is drawn from this coordinate
	 * 			to end
	 */
	public void drawLine(Coordinate end, Graphics g){
		g.drawLine( x, y, end.getX(), end.getY() );		
	}
	
	/* Preconditions:  end is a valid coordinate representing 
	 * 			the endpoint of the desired segment.
	 * 			g is a valid Graphics object.
	 * 			c is a valid Color 
	 * Postconditions:  a line of Color c is drawn from this coordinate
	 * 			to end 
	 */
	public void drawLine(Coordinate end, Graphics g, Color c){
		g.setColor(c);
		g.drawLine( x, y, end.getX(), end.getY() );		
	}
	public void translateTo(Coordinate dest){
		x = dest.x;
		y = dest.y;
	}
	
	public String toString(){
		return "(" + x + ", "+y+")";
	}
	
	/*Preconditions:  c is a valid coordinate
	 *Postconditions:  true is returned if this coordinate resides
	 *	at the same place in the cartesian plane.
	 *	False is returned otherwise
	 */
	public boolean equals(Coordinate c){
		if(x==c.x && y == c.y)
			return true;
		else
			return false;
	}

}
