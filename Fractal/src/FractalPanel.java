import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.image.*;
import com.sun.image.codec.jpeg.*;
import java.io.*;
/**
 * @author Iyang
 * 
 * NOTICE:
 * THIS CUSTOM PROGRAM NEEDS MY FRACTALFRAME CODE TO RUN PLEASE (I UPLOADED IT)
 * THANK YOU
 * 
 * 
 * Doesn't need EZPolygon to color :)
 */
public class FractalPanel extends JPanel{
	Color abs=new Color(250,128,114),ads=new Color(65,105,225),ags=new Color(205,189,12),yams=new Color(139,0,169);
	Color[] rainbow = {Color.LIGHT_GRAY,ads,abs,ags,yams};
	BufferedImage img;//image	
	int input;//level
	Graphics2D g;
	
	//Constructor
	//Preconditions: NONE
	//Postconditions:  The panel is created 640x480 with a blank image	
	public FractalPanel(){
		super();
		this.setSize(new Dimension(1280,980));
		this.setPreferredSize(new Dimension(1280,980));
		input = 0; // no fractal yet
		img = new BufferedImage(this.getWidth(), this.getHeight(),  BufferedImage.TYPE_INT_RGB);
		//Make the image blank and white
		setBackground(Color.WHITE);
		Graphics2D ig = img.createGraphics();
		ig = img.createGraphics();
		ig.setBackground(Color.WHITE);
		ig.clearRect(0,0,img.getWidth(),img.getHeight());	
	}
	/*Preconditions: none
	 *Postconditions: The fractal is drawn
	 */
	public void beginFract(){
		//the image is cleared (set to blank & white)
		Graphics2D ig = img.createGraphics();
		ig.setBackground(Color.WHITE);
		ig.clearRect(0,0,img.getWidth(),img.getHeight());

/********************************************************
 * Place the FIRST call to recursive drawing function here!!
/********************************************************/
		//draw first star
		int in=input;
		Coordinate star11 = new Coordinate(500,200);
		Coordinate star22 = new Coordinate(500,300);
		Coordinate star33 = new Coordinate(star22);
		Coordinate star44 = new Coordinate(star11);
		Coordinate star55 = new Coordinate(star11);
		
		star33.rotate(Math.PI/(1+(double)2/3), star11);
		star44.rotate(-1*Math.PI/(1+(double)2/3),star22);
		star55.rotate(Math.PI/(1+(double)2/3),star33);
		
		star55.drawLine(star11, ig, Color.CYAN);
		star33.drawLine(star22, ig, Color.CYAN);
		star33.drawLine(star44, ig, Color.CYAN);
		star44.drawLine(star11, ig, Color.CYAN);
		star22.drawLine(star55, ig, Color.CYAN);
		colorin(star11, star22, star33, star44, star55,ig,rainbow[input%rainbow.length]);
		recursiveDraw( star11, star22, star33, star44, star55,ig,rainbow[input%rainbow.length], in);
	}

/************************************************
 * Here is your recursive drawing function!!!!
 * Notice that it is empty :)
 * But I have thrown you a bone by showing you some of the parameters
 * that it will need.
 * You will, of course, need some more parameters depending on the type
 * of fractal that you make.
 ***************************************/	
	/*Preconditions:
	 * 	g is the graphics object for the bufferedImage to which the fractal will be drawn
	 *  c is the color for this level of the fractal
	 *  num is the remaining number of desired fractal levels
	 *Postconditions: Part of the current level is drawn, and recurvsive calls are executed
	 *      that will draw the next level of the fractal 
	 */
	private void recursiveDraw(Coordinate star1, Coordinate star2, Coordinate star3, Coordinate star4, Coordinate star5, Graphics2D g, Color c, int lvl){
		System.out.println(lvl);
		if(lvl==0)//stop
			return;
		//star1
		if(lvl==input){
			Coordinate st2 = new Coordinate(star2);
			Coordinate st3 = new Coordinate(star3);
			Coordinate st4 = new Coordinate(star4);
			Coordinate st5 = new Coordinate(star5);
			
			st2.rotate(Math.PI, star1);
			st3.rotate(Math.PI, star1);
			st4.rotate(Math.PI, star1);
			st5.rotate(Math.PI, star1);
			
			st2.scale(.3, star1);
			st3.scale(.3, star1);
			st4.scale(.3, star1);
			st5.scale(.3, star1);
			star1.drawLine(st5, g);
			star1.drawLine(st4, g);
			st5.drawLine(st2, g);
			st3.drawLine(st2, g);
			st3.drawLine(st4, g);
			//color star1
			colorin( star1,  st2, st3,st4,  st5, g,  rainbow[lvl%rainbow.length]);
			recursiveDraw( star1,  st2,  st3,  st4,  st5,  g,  rainbow[lvl%rainbow.length],  lvl-1);
		}
		//star2
		Coordinate st2p2 = new Coordinate(star1);
		Coordinate st2p3 = new Coordinate(star3);
		Coordinate st2p4 = new Coordinate(star4);
		Coordinate st2p5 = new Coordinate(star5);
		
		st2p2.rotate(Math.PI, star2);
		st2p3.rotate(Math.PI, star2);
		st2p4.rotate(Math.PI, star2);
		st2p5.rotate(Math.PI, star2);
		
		st2p2.scale(.3, star2);
		st2p3.scale(.3, star2);
		st2p4.scale(.3, star2);
		st2p5.scale(.3, star2);
		
		star2.drawLine(st2p5, g);
		star2.drawLine(st2p3, g);
		st2p3.drawLine(st2p4, g);
		st2p2.drawLine(st2p5,g);
		st2p2.drawLine(st2p4,g);
		//color star2
		colorin( star2,  st2p2,  st2p4,  st2p3,  st2p5, g,  rainbow[lvl%rainbow.length]);
		recursiveDraw( star2,  st2p2,  st2p4,  st2p3,  st2p5,  g,  rainbow[lvl%rainbow.length],  lvl-1);
		
		//star3
		Coordinate st3p2 = new Coordinate(star1);
		Coordinate st3p3 = new Coordinate(star2);
		Coordinate st3p4 = new Coordinate(star4);
		Coordinate st3p5 = new Coordinate(star5);
		
		st3p2.rotate(Math.PI, star3);
		st3p3.rotate(Math.PI, star3);
		st3p4.rotate(Math.PI, star3);
		st3p5.rotate(Math.PI, star3);
		
		st3p2.scale(.3, star3);
		st3p3.scale(.3, star3);
		st3p4.scale(.3, star3);
		st3p5.scale(.3, star3);
		
		star3.drawLine(st3p3, g);
		star3.drawLine(st3p4, g);
		st3p3.drawLine(st3p5, g);
		st3p4.drawLine(st3p2, g);
		st3p2.drawLine(st3p5, g);
		//color star3
		colorin(star3, st3p2, st3p5, st3p3, st3p4, g, rainbow[lvl%rainbow.length]);
		recursiveDraw(star3, st3p2, st3p5, st3p3, st3p4, g, rainbow[lvl%rainbow.length],  lvl-1);
		
		//star4
		Coordinate st4p2 = new Coordinate(star1);
		Coordinate st4p3 = new Coordinate(star2);
		Coordinate st4p4 = new Coordinate(star3);
		Coordinate st4p5 = new Coordinate(star5);
		
		st4p2.rotate(Math.PI, star4);
		st4p3.rotate(Math.PI, star4);
		st4p4.rotate(Math.PI, star4);
		st4p5.rotate(Math.PI, star4);
		
		st4p2.scale(.3, star4);
		st4p3.scale(.3, star4);
		st4p4.scale(.3, star4);
		st4p5.scale(.3, star4);
		
		star4.drawLine(st4p4, g);
		star4.drawLine(st4p2, g);
		st4p2.drawLine(st4p5, g);
		st4p5.drawLine(st4p3, g);
		st4p3.drawLine(st4p4, g);
		//color star4
		colorin(star4, st4p3, st4p5, st4p2, st4p4, g, rainbow[lvl%rainbow.length]);
		recursiveDraw(star4, st4p3, st4p5, st4p2, st4p4, g, rainbow[lvl%rainbow.length],  lvl-1);
		
		//star5
		Coordinate str2 = new Coordinate(star1);
		Coordinate str3 = new Coordinate(star2);
		Coordinate str4 = new Coordinate(star3);
		Coordinate str5 = new Coordinate(star4);
		
		str2.rotate(Math.PI, star5);
		str3.rotate(Math.PI, star5);
		str4.rotate(Math.PI, star5);
		str5.rotate(Math.PI, star5);
		
		str2.scale(.3, star5);
		str3.scale(.3, star5);
		str4.scale(.3, star5);
		str5.scale(.3, star5);
		
		star5.drawLine(str2, g);
		star5.drawLine(str3, g);
		str3.drawLine(str4, g);
		str2.drawLine(str5, g);
		str4.drawLine(str5, g);
		//color star5
		colorin(star5, str4, str5, str2, str3, g, rainbow[lvl%rainbow.length]);
		recursiveDraw(star5, str4, str5, str2, str3, g, rainbow[lvl%rainbow.length],  lvl-1);	
	}	
	
	/*
	 *Preconditions: none
	 *Postconditions:  n is set to tmp
	 */
	public void setInput(int tmp){
		input = tmp;
	}
	/*
	 *Preconditions: Gives information on star and graphics+color.
	 *Postconditions:  Colors in star, yay!
	 */
	public void colorin(Coordinate c1, Coordinate c2, Coordinate c3, Coordinate c4, Coordinate c5, Graphics2D g, Color c){
		GeneralPath p = new GeneralPath();
		p.moveTo(c1.getX(), c1.getY());
		p.lineTo(c5.getX(), c5.getY());
		p.lineTo(c2.getX(), c2.getY());
		p.lineTo(c3.getX(), c3.getY());
		p.lineTo(c4.getX(), c4.getY());
		p.lineTo(c1.getX(), c1.getY());
		p.closePath();
		g.setColor(c);
		g.fill(p);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D ig = (Graphics2D)g;
		ig.drawImage(img,0,0,this);
	}
	public void saveFile(File f){
		try{
			  ImageIO.write(img, "png", f);	
		  }
		  catch(IOException e)
		  {
			JOptionPane.showMessageDialog(null, e.getMessage());
		  }
	}
}