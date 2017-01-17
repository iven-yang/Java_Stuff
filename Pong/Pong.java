import java.applet.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class Pong extends Applet implements MouseMotionListener, KeyListener, WindowListener{
	int my,bx,by,px,py,compx,compy,width,height,speedx=25,speedy=25,bwidth,bheight,pwidth,pheight,score,highscore=0,window=900;
	boolean started;
	Color cool=Color.WHITE;
	private Timer timer1;
	public void init(){
		this.setVisible(true);
		this.setSize(1250,window);
		
		width = getSize().width;
		height = getSize().height;
		setBackground(Color.DARK_GRAY);
		pheight = 140;
		pwidth = 30;
		bheight = 30;
		bwidth = 30;
		addKeyListener(this);
		addMouseMotionListener(this);
		px = 35;
		compx = width - 35 - pwidth;
		newgame();
		timer1 = new Timer(10,new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			height = getSize().height;
    			width = getSize().width;
				bx += speedx;
				by += speedy;
				if (by <= 0 || by + bheight >= height){
					speedy = -speedy;
				}
				if (bx <= px + pwidth && by + bheight >= py && by <= py + pheight && bx > px){
					speedx = -speedx;
					++score;
				}
				if(bx + bwidth >= compx && by + bheight >= compy && by <= compy + pheight && bx < compx + pwidth){
					speedx = -speedx;
				}
				if(speedx < 0){
					if (compy + pheight/2 != height/2){
						if (compy + pheight/2 > height/2){
							compy -= -speedx;
						}
						else{
							compy += -speedx;
						}
					}
				}
				else{
					if(by + bheight/2 <= compy + pheight/2){
						compy -= speedx;
					}
					else{
						compy += speedx;
					}
				}
				if(compy < 0){
					compy = 0;
				}
				if(compy + pheight > height){
					compy = height - pheight;
				}
				if(bx + bwidth < 0){
					py = height/2 - pheight/2;
					timer1.stop();
					started = false;
				}
				repaint();
    		}
		});
	}
 
	public void mouseMoved(MouseEvent e){
		if(started){
			my = e.getY();
			if(my + pheight/2 > height){
				my = height - pheight/2;
			}
			if(my < pheight/2){
				my = pheight/2;
			}
			py = my-pheight/2;
			//repaint();
			if(((double)Math.random())>0.98){
				if(by < height-60){
					window-=(int)((double)Math.random()*50)+1;
					repaint();
				}
			}
			if(((double)Math.random())<0.02){
				window+=(int)((double)Math.random()*50)+1;
				repaint();
			}
			if(window<450)
				window=450;
			if(window>900)
				window=900;
			this.setSize(1250,window);
		}
	}
	public void mouseDragged(MouseEvent e){}
	public void paint(Graphics g){
		Font font1 = new Font("Arial", Font.BOLD, 18);
		Font font2 = new Font("Arial", Font.BOLD,40);
		g.setColor(cool);
		g.drawRect(0,0,width - 1,height - 1);
		g.fillRect(px,py,pwidth,pheight);
		g.fillRect(compx,compy,pwidth,pheight);
		g.setFont(font1);
		g.setColor(Color.WHITE);
		g.drawString("Score: " + score,20,20);
		g.drawString("Speed: " + Math.abs(speedx),20,40);
		if(started){
			g.fillArc(bx,by,bwidth,bheight,0,360);
			//g.fillArc(bx-50,by-50,bwidth,bheight,0,360);
		}
		else{
			g.setFont(font2);
			g.setColor(Color.BLUE);
			g.drawString("Pong",width/2 - 46,height/2 - 16);
			g.setFont(font1);
			g.drawString("Press 'Space' to start",width/2 - 85, height/2 + 30);
			if(score>highscore)
				highscore = score;
			g.drawString("High Score: " + highscore,width/2 - 55, height/2 + 65);
		}
	}
 
	public void newgame(){
		py = height/2 - pheight/2;
		compy = py;
		bx = width/2 - bwidth/2;
		by = height/2 - bheight/2;
		speedx=Math.abs(speedx);
		speedy=Math.abs(speedy);
		pheight = 140;
		pwidth = 30;
		score = 0;
		window=900;
		this.setSize(1250,window);
		repaint();
	}
 
	public void keyPressed(KeyEvent e){
		if(e.getKeyChar() == ' '){
			started = true;
			newgame();
			timer1.start();
		}
		else if(e.getKeyChar() == 'i'){
			if(cool==Color.WHITE)
				cool=this.getBackground();
			else if(cool==this.getBackground())
				cool=Color.WHITE;
			repaint();
		}
		else if(e.getKeyChar() == '='){
			if(pheight<600){
				if(score>0){
					pheight+=10;
					score--;
					repaint();
				}
			}
		}
		else if(e.getKeyChar() == '-'){
			if(pheight>10){
				pheight-=10;
				score++;
				repaint();
			}
		}
		else if(e.getKeyChar() == '*'){
			score++;
		}
		else if(started==false){
			speedx=Math.abs(speedx);
			speedy=Math.abs(speedy);
			if(e.getKeyChar() == '0'){
				if(speedx<57){
					speedx++;
					speedy++;
					repaint();
				}	
			}
			else if(e.getKeyChar() == '9'){
				if(speedx>5){
					speedx--;
					speedy--;
					repaint();
				}
			}
			else if(e.getKeyChar() == 'r'){
				window=900;
				this.setSize(1250,window);
				pheight = 140;
				pwidth = 30;
				speedx=20;
				speedy=20;
				repaint();
			}
		}
		else if(e.getKeyChar()!=' '){
			exit();
		}
	}
 
	private void exit(){
		this.stop();
		//System.out.println("Exiting...");
	}

	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}

	//Overiding for WindowListener Begins Here
	public void windowActivated(WindowEvent arg0){
		System.out.println("Loading...");
	}
	public void windowClosed(WindowEvent arg0){}
	public void windowClosing(WindowEvent e){
		System.out.println("Exiting...");
	}
	public void windowDeactivated(WindowEvent arg0){}
	public void windowDeiconified(WindowEvent arg0){}
	public void windowIconified(WindowEvent arg0){}
	public void windowOpened(WindowEvent arg0){}
	//Overiding for WindowListener Ends Here
}