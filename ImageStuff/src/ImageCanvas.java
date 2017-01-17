import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
public class ImageCanvas extends JPanel{
	private BufferedImage img; //the image that appears on this canvas
	private int[][] pix, pix2;
	private int w, h;
	private static final int TYPE = BufferedImage.TYPE_INT_ARGB_PRE;

	public static final int A=0, R=1, G=2, B=3;
	//returns only the red value of the pixel
	//   EX: pixel = 0x004f2ca5 --> returns 4f
	public int howRed(int pixel){return (pixel & 0x00FF0000) >> 16;}
	//returns only the green value of the pixel
	//   EX: pixel = 0x004f2ca5 --> returns 2c 
	public int howGreen(int pixel){return (pixel & 0x0000FF00) >> 8;}
	//returns only the blue value of the pixel
	//   EX: pixel = 0x004f2ca5 --> returns a5
	public int howBlue(int pixel){return pixel & 0x000000FF ;}
	//returns a new pixel with the specified alpha
	//red, green, and blue values
	public int combine(int a, int r, int g, int b){
		if(r>255) r= 255;
		if(r<0) r = 0;
		if(g>255) g= 255;
		if(g<0) g = 0;
		if(b>255) b= 255;
		if(b<0) b = 0;
		return (a<<32)|(r<<16)|(g<<8)|b;
	}
    /*******************************************************/
	 public ImageCanvas(){
		super();
		this.setBackground(Color.gray);
		this.setPreferredSize(new Dimension(400,400));
		img = new BufferedImage(100,100,TYPE);
		w = img.getWidth();
		h = img.getHeight();
		pix = imgToArray();
	}

	public BufferedImage getImage(){return img;}
	public void setImage(File file){
		try{ 
			img = ImageIO.read((file));
			MediaTracker mt = new MediaTracker(new Component(){});
			mt.addImage(img, 0);
			mt.waitForAll();
		}
		catch(Exception ex){ex.printStackTrace();}
		//gg
		w = img.getWidth();
		h = img.getHeight();
		pix = imgToArray();
		this.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
	}
	//*********************Easy pixel manips************************
	public void BlackandWhite(){
		int[][] ep = imgToArray();
		for(int y=0;y<h;y++)
			for(int x=0; x<w;x++){
				int red = ep[y][x] & 0x00FF0000 >> 16;//gg
				int green = ep[y][x] & 0x0000FF00 >> 8;
				int blue = howBlue(ep[y][x]);
				ep[y][x]=combine(0,red,green,blue);
			}
		arrayToImg(ep);
		System.out.println("BLACK & WHITE MODE ACTIVATED");
	}
	public void Sepia(){
		int[][] ep = imgToArray();
		int gray =0;
		for(int y=0;y<h;y++)
			for(int x=0; x<w;x++){
				gray=(howRed(ep[y][x])+howBlue(ep[y][x])+howGreen(ep[y][x]))/3;//gray
				ep[y][x]=combine(0,gray+60,gray+25,gray);
			}
		arrayToImg(ep);
	}
	public void red(){
		int[][] cosmicwhole = imgToArray();//gg//gg
		for(int y=0;y<h;y++){
			for(int x=0; x<w;x++){
				int red = howRed(cosmicwhole[y][x]);
				int green = howGreen(cosmicwhole[y][x]);
				int blue = howBlue(cosmicwhole[y][x]);
				cosmicwhole[y][x]=combine(0,red,0,0);
				//gg
			}
		}
		arrayToImg(cosmicwhole);
		System.out.println("RED MODE ACTIVATED");
	}
	public void green(){
		int[][] cosmicwhole = imgToArray();
		//gg//gg
		for(int y=0;y<h;y++)
			for(int x=0; x<w;x++){
				int red = howRed(cosmicwhole[y][x]);
				int green = howGreen(cosmicwhole[y][x]);
				int blue = howBlue(cosmicwhole[y][x]);
				cosmicwhole[y][x]=combine(0,0,green,0);
			}
		arrayToImg(cosmicwhole);
		System.out.println("GREEN MODE ACTIVATED");
	}
	public void blue(){
		int[][] astral = imgToArray();
		for(int y=0;y<h;y++)
			for(int x=0; x<w;x++){
				int red = howRed(astral[y][x]);
				int green = howGreen(astral[y][x]);
				int blue = howBlue(astral[y][x]);
				astral[y][x]=combine(0,0,0,blue);
			}
		arrayToImg(astral);
		System.out.println("BLUE MODE ACTIVATED");//gg
	}
	public void negative(){
		int[][] skysplitter = imgToArray();
		for(int y=0;y<h;y++)
			for(int x=0; x<w;x++){
				int red = howRed(skysplitter[y][x]);
				int green = howGreen(skysplitter[y][x]);
				int blue = howBlue(skysplitter[y][x]);
				skysplitter[y][x]=combine(00,255-red,255-green,255-blue);

			}
		arrayToImg(skysplitter);
		System.out.println("NEGATIVE MODE ACTIVATED");
	}
	public void mirror(boolean stuff){
		int[][] acclaim = imgToArray(), crystal= new int[h][w];
		if(stuff){
			for(int r=0;r<h;r++)
				for(int c=0; c<w;c++)
					//gg
					crystal[r][c]=acclaim[r][Math.abs(c-(w-1))];
			arrayToImg(crystal);}
		else{
			for(int r=0;r<h;r++)
				for(int c=0; c<w;c++)
					crystal[r][c]=acclaim[Math.abs(r-(h-1))][c];
			arrayToImg(crystal);
		}
	}
	//*********************END Easy pixel manips************************
	
	//********************KERNEL STUFF *********************************
	public void blur(int squareDim){
		int[][] blah = imgToArray(), blurImg = imgToArray();
		double rV=0,gV=0,bV=0;
		if((squareDim%2)==0)//makes it odd if not odd
			squareDim++;
		double area=squareDim*squareDim;
		for(int y=0;y<h;y++)//gg
			for(int x=0; x<w;x++){//gg
				int xcenter=x,ycenter=y;
				int startx=xcenter-(squareDim/2);//side length/2 made into an int is the first x value
				int starty=ycenter-(squareDim/2);//side length/2 made into an int is the first y value
				int endx=xcenter+(squareDim/2);
				int endy=ycenter+(squareDim/2);
				gV = 0; rV =0; bV = 0; area=0;
				for(int hght=starty; hght<endy;hght++)
					for(int bse=startx; bse<endx;bse++){
						try{
							gV+=howGreen(blah[hght][bse]);//green factors
							rV+=howRed(blah[hght][bse]);//red factors
							bV+=howBlue(blah[hght][bse]);//blue factors
							area++;
						}catch(ArrayIndexOutOfBoundsException e){}						
					}
				blurImg[y][x]=combine(00,(int)Math.round(rV/area),(int)Math.round(gV/area),(int)Math.round(bV/area));//sets up pixel		
			}
		arrayToImg(blurImg);
	}
	public void sharpen(int squareDim){
		int[][] blah = imgToArray(), sharpImg = imgToArray();
		double rV=0,gV=0,bV=0;
		if((squareDim%2)==0)
			squareDim++;
		double area=squareDim*squareDim;
		for(int y=0;y<h;y++)
			for(int x=0; x<w;x++){
				int xcenter=x,ycenter=y;
				int startx=xcenter-(squareDim/2);//side length/2 made into an int is the first x value
				int starty=ycenter-(squareDim/2);//side length/2 made into an int is the first y value
				int endx=xcenter+(squareDim/2);
				int endy=ycenter+(squareDim/2);
				gV = 0; rV =0; bV = 0; area=0;
				for(int hght=starty; hght<endy;hght++)
					for(int bse=startx; bse<endx;bse++){
						try{
							gV+=howGreen(blah[hght][bse]);//green factors
							rV+=howRed(blah[hght][bse]);//add factors
							bV+=howBlue(blah[hght][bse]);//blue factors
							area++;
						}catch(ArrayIndexOutOfBoundsException e){}						
					}
				double sharpR =  2*howRed(blah[y][x]) -  rV/area;
				double sharpG = 2*howGreen(blah[y][x]) -  gV/area;
				double sharpB =  2*howBlue(blah[y][x]) -  bV/area;
				//if over 255 or 0 fix
				if(sharpR>255) sharpR = 255;
				if(sharpR<0) sharpR = 0;//gg
				if(sharpG>255) sharpG = 255;
				if(sharpG<0) sharpG = 0;
				if(sharpB>255) sharpB = 255;
				if(sharpB<0) sharpB = 0;
				sharpImg[y][x]=combine(00,(int)Math.round(sharpR),(int)Math.round(sharpG),(int)Math.round(sharpB));
			}
		arrayToImg(sharpImg);
	}
	/***************MATRIX STUFF **********************************/
	public void resize(double ratio){
		double[][] matrix= new double[2][2];
		matrix[0][0]=ratio;
		matrix[0][1]=0;
		matrix[1][0]=0;
		matrix[1][1]=ratio;
		transform(matrix);
	}
	public void rotate(double angle){
		System.out.println("Rotating by :"+angle+" degrees");
		angle = angle/180.0*Math.PI;
		double[][] matrix= new double[2][2];
		matrix[0][0]=Math.cos(angle);//gg
		matrix[0][1]=-1*Math.sin(angle);
		matrix[1][0]=Math.sin(angle);
		matrix[1][1]=Math.cos(angle);	
		transform(matrix);
	}
	public void HorizStretch(double factor){
		double[][] matrix= new double[2][2];
		matrix[0][0]=factor;
		matrix[0][1]=0;
		matrix[1][0]=0;
		matrix[1][1]=1;
		transform(matrix);
	}
	public void VertStretch(double factor){
		double[][] matrix= new double[2][2];
		matrix[0][0]=1;
		matrix[0][1]=0;
		matrix[1][0]=0;
		matrix[1][1]=factor;
		transform(matrix);
	}
	public void EdgeDetect(boolean v, double val){
		int squareDim=3;
		int[][] blah = imgToArray(), blurImg = imgToArray();
		double redVal=0,greenVal=0,blueVal=0;
		for(int y=0;y<h;y++)
			for(int x=0; x<w;x++){
				int xcenter=x,ycenter=y;
				int startx=xcenter-(squareDim/2);//side length divided by two made into an int is the first x value
				int starty=ycenter-(squareDim/2);//side length divided by two made into an int is the first y value
				int endx=xcenter+(squareDim/2);
				int endy=ycenter+(squareDim/2);
				if(v){//vertical
					
					greenVal = 0; redVal =0; blueVal = 0;
					for(int hght=starty; hght<=endy;hght++)
						for(int bse=startx; bse<=endx;bse++){
							
							try{
								if(bse<x){//if not middle
									greenVal+=-1*val*howGreen(blah[hght][bse]);//add the green factors
									redVal+=-1*val*howRed(blah[hght][bse]);//add the red factors
									blueVal+=-1*val*howBlue(blah[hght][bse]);//add the blue factors
								}
								else if(bse>x){//if not middle
									greenVal+=val*howGreen(blah[hght][bse]);//add the green factors
									redVal+=val*howRed(blah[hght][bse]);//add the red factors
									blueVal+=val*howBlue(blah[hght][bse]);//add the blue factors
								}
							}catch(ArrayIndexOutOfBoundsException e){}						
							blurImg[y][x]=combine(00,(int)Math.round(redVal),(int)Math.round(greenVal),(int)Math.round(blueVal));
						}
				}
				else{//horizontal
					greenVal = 0; redVal =0; blueVal = 0;
					for(int hght=starty; hght<=endy;hght++)
						for(int bse=startx; bse<=endx;bse++){
						
							try{
								if(hght<y){//if not middle
									greenVal+=-1*val*howGreen(blah[hght][bse]);//add the green factors
									redVal+=-1*val*howRed(blah[hght][bse]);//add the red factors
									blueVal+=-1*val*howBlue(blah[hght][bse]);//add the blue factors
								}
								else if(hght>y){//if not middle
									greenVal+=val*howGreen(blah[hght][bse]);//add the green factors
									redVal+=val*howRed(blah[hght][bse]);//add the red factors
									blueVal+=val*howBlue(blah[hght][bse]);//add the blue factors
								}
							}catch(ArrayIndexOutOfBoundsException e){}						
						}
					blurImg[y][x]=combine(00,(int)Math.round(redVal),(int)Math.round(greenVal),(int)Math.round(blueVal));}
			}
		arrayToImg(blurImg);
	}
	/*******Other stuff start******/
	public void transform(double[][] matrix){
		int[][] orig = imgToArray();
		int[][][] trans = new int[h][w][2];
		int currentX = 0, currentY = 0;

		int maxX = Integer.MIN_VALUE;//gg//gg
		int maxY = Integer.MIN_VALUE;
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		int[][] imgArray;
		for(int y=0;y<h;y++)
			for(int x=0; x<w;x++){
				trans[y][x][0]=(int)Math.round( matrix[0][0]*x+matrix[0][1]*y );
				if(minX>trans[y][x][0])
					minX=trans[y][x][0];
				if(maxX<trans[y][x][0])
					maxX=trans[y][x][0];
				trans[y][x][1]=(int)Math.round( matrix[1][0]*x+matrix[1][1]*y );
				if(minY>trans[y][x][1])
					minY=trans[y][x][1];
				if(maxY<trans[y][x][1])
					maxY=trans[y][x][1];
			}
		System.out.println("height: "+(maxY-minY+1)+", width: "+(maxX-minX+1));
		imgArray = new int[maxY-minY+1][maxX-minX+1];
		for(int y=0;y<h;y++)
			for(int x=0; x<w;x++){
				imgArray[trans[y][x][1]-minY][trans[y][x][0]-minX]=orig[y][x];
			}
		w = maxX-minX+1;
		h = maxY-minY+1;
		//scrolly
		this.setPreferredSize(new Dimension(w,h));	//gg
		arrayToImg(imgArray);
		this.repaint();
	}
	public int cF(int percent, int val){
		//y-y1 = m(x-x1)
		double percent2=(double)percent/100+1;
		int x1=0;
		int x2=255;
		double y1, y2, slope;
		y1=(int) (128-128*percent2);
		y2=(int) (128+128*percent2);
		slope = (y2-y1)/(x2-x1);
		return (int)Math.round( slope*(val-x1)+y1 );
	}
	public void contrast(int percent){
		int red, green, blue;
		int [][] oldarray = imgToArray();
		for(int y=0;y<h;y++)
			for(int x=0; x<w;x++){
				red=cF(percent,howRed(oldarray[y][x]));
				green=cF(percent,howGreen(oldarray[y][x]));
				blue=cF(percent,howBlue(oldarray[y][x]));
				oldarray[y][x]=combine(0,red,green,blue);
			}
		arrayToImg(oldarray);
	}
	
	public void Brightness(int val){
		int[][] blah = imgToArray();
		for(int y=0;y<h;y++)
			for(int x=0; x<w;x++){
				//adds val to all colors
				int red = howRed(blah[y][x])+val;
				int green = howGreen(blah[y][x])+val;
				int blue = howBlue(blah[y][x])+val;
				blah[y][x]=combine(0,red,green,blue);
			}
		arrayToImg(blah);
	}
	public void Emboss(int power){
		int red = 0,green = 0, blue = 0;
		int[][] blurImg = imgToArray(), blah = imgToArray();
		double redVal=0,greenVal=0,blueVal=0;
		for(int y=1;y<h-1;y++)
			for(int x=1; x<w-1;x++){
					//proper cells time power
					red=howRed(blah[y-1][x-1])*power +howRed(blah[y-1][x])*power+howRed(blah[y][x-1])*power+howRed(blah[y][x+1])*-1*power+howRed(blah[y+1][x])*-1*power+howRed(blah[y+1][x+1])*power*-1;
					green=howGreen(blah[y-1][x-1])*power +howGreen(blah[y-1][x])*power+howGreen(blah[y][x-1])*power+howGreen(blah[y][x+1])*-1*power+howGreen(blah[y+1][x])*-1*power+howGreen(blah[y+1][x+1])*power*-1;
					blue=howBlue(blah[y-1][x-1])*power +howBlue(blah[y-1][x])*power+howBlue(blah[y][x-1])*power+howBlue(blah[y][x+1])*-1*power+howBlue(blah[y+1][x])*-1*power+howBlue(blah[y+1][x+1])*power*-1;
					
					//kill the colors
					int gray = (red+green+blue)/3+128;
					
					
				blurImg[y][x]=combine(00,gray,gray,gray);
			}
		

		arrayToImg(blurImg);
	}
	/*******Other stuff end******/
	public void paint(Graphics g){
		super.paint(g);
		((Graphics2D)g).drawImage(img,null,0,0);
		//g.drawImage(img, 0, 0, null);
	}
	//have kids do this first! Just take the pixels and replace them
	public void tester(){
		int[][] blah = imgToArray();
		arrayToImg(blah);
	}
	//Postconditions: all of the pixels from the original image have been stored
	//into a 2d array and that 2d array has been returned
	private int[][] imgToArray(){
		//this puts the pixels into a 1d array.  You want to move them into a 2d array
		int[] pix = img.getRGB(0, 0, w, h, null, 0, w);
		int[][] arry = new int[h][w];
		int sz=0;
		for(int r=0;r<h;r++)
			for(int c=0; c<w;c++)
				arry[r][c]=pix[sz++];		
		return arry;//gg
	}
	//Postconditions: the pixel values from the given 2d array have been loaded onto
	//the image
	//HINT: use this function--> img.setRGB(x,y,val);
	private void arrayToImg(int[][] pix){
		w = pix[0].length;
		h = pix.length;
		this.setPreferredSize(new Dimension(w,h));
		img = new BufferedImage(w,h,img.getType());
		for(int r=0;r<h; r++)
			for(int c=0;c<w; c++)
				img.setRGB(c, r, pix[r][c]);
		this.repaint();
	}
}