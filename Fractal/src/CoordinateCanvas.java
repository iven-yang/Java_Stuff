import javax.swing.*;
import java.awt.*;

public class CoordinateCanvas extends JPanel{
	public CoordinateCanvas(){
		super();	
	}		
	public void paint(Graphics g){
	// PUT YOUR CODE IN HERE :)
	
	// 1. equilateral triangle	
	Coordinate tri1 = new Coordinate(100,500);
	Coordinate tri2 = new Coordinate(100,600);
	Coordinate tri3 = new Coordinate(tri1);
	tri1.drawLine(tri2, g);
	tri3.rotate(Math.PI/3,tri2);
	tri3.drawLine(tri2, g);
	tri3.drawLine(tri1, g);
		
	// 2.square with no horizontal
	Coordinate one = new Coordinate(100,100);
	Coordinate two = new Coordinate(200,100);	
	Coordinate three = new Coordinate(100,200);
	Coordinate four = new Coordinate(200,200);
	one.drawLine(three, g);
	four.drawLine(two, g);
	
	// 3. regular pentagon
	Coordinate uno = new Coordinate(300,300);
	Coordinate dos = new Coordinate(300,400);
	Coordinate tres = new Coordinate(dos);
	Coordinate cuatro = new Coordinate(uno);
	Coordinate cinco = new Coordinate(uno);
	tres.rotate(Math.PI/(1+(double)2/3), uno);
	cuatro.rotate(-1*Math.PI/(1+(double)2/3),dos);
	cinco.rotate(Math.PI/(1+(double)2/3),tres);
	uno.drawLine(dos, g);
	uno.drawLine(tres, g);
	dos.drawLine(cuatro, g);
	cinco.drawLine(tres, g);
	cinco.drawLine(cuatro, g);
	// 4. 2 equilateral triangles that share the vertex
	//1st triangle
	Coordinate tria1 = new Coordinate(500,500);
	Coordinate tria2 = new Coordinate(500,600);
	Coordinate tria3 = new Coordinate(tria1);
	tria1.drawLine(tria2, g);
	tria3.rotate(Math.PI/3,tria2);
	tria3.drawLine(tria2, g);
	tria3.drawLine(tria1, g);
	//2nd
	Coordinate tria4 = new Coordinate(325,500);
	Coordinate tria5 = new Coordinate(325,600);
	Coordinate tria6 = new Coordinate(tria4);
	tria4.drawLine(tria5, g);
	tria6.rotate(-1*Math.PI/3,tria5);
	tria6.drawLine(tria5, g);
	tria6.drawLine(tria4, g);
	
	//5. 45-45-90 with no horizontal
	Coordinate un = new Coordinate(100,300);
	Coordinate deux = new Coordinate(200,300);
	Coordinate trois = new Coordinate(100,400);
	un.drawLine(trois, g);
	deux.drawLine(trois, g);

	//6. 30-60-90 triangle (with NO horizontal sides)
	Coordinate t1 = new Coordinate(600,400);
	Coordinate t2 = new Coordinate(700,500);
	Coordinate t3 = new Coordinate(t2);
	t3.rotate(Math.PI/6, t1);
	t3.scale(Math.sqrt(3)/2, t1);
	t1.drawLine(t2, g);
	t1.drawLine(t3, g);
	t2.drawLine(t3, g);
	
	
	//7. four equilateral triangles whose bases form a square.\
	//1st triangle
	Coordinate trian1 = new Coordinate(700,500);
	Coordinate trian2 = new Coordinate(700,600);
	Coordinate trian3 = new Coordinate(trian1);
	trian1.drawLine(trian2, g);
	trian3.rotate(Math.PI/3,trian2);
	trian3.drawLine(trian2, g);
	trian3.drawLine(trian1, g);
	//2nd
	Coordinate trian4 = new Coordinate(800,500);
	Coordinate trian5 = new Coordinate(800,600);
	Coordinate trian6 = new Coordinate(trian4);
	trian4.drawLine(trian5, g);
	trian6.rotate(-1*Math.PI/3,trian5);
	trian6.drawLine(trian5, g);
	trian6.drawLine(trian4, g);
	//3rd
	trian5.drawLine(trian2, g);
	Coordinate trian7 = new Coordinate(trian5);
	trian7.rotate(-1*Math.PI/3, trian2);
	trian7.drawLine(trian5, g);
	trian7.drawLine(trian2, g);
	//4th
	trian1.drawLine(trian4, g);
	Coordinate trian8 = new Coordinate(trian4);
	trian8.rotate(Math.PI/3, trian1);
	trian8.drawLine(trian4, g);
	trian8.drawLine(trian1, g);
	
	//8. Star
	Coordinate star1 = new Coordinate(500,200);
	Coordinate star2 = new Coordinate(500,300);
	Coordinate star3 = new Coordinate(star2);
	Coordinate star4 = new Coordinate(star1);
	Coordinate star5 = new Coordinate(star1);
	star3.rotate(Math.PI/(1+(double)2/3), star1);
	star4.rotate(-1*Math.PI/(1+(double)2/3),star2);
	star5.rotate(Math.PI/(1+(double)2/3),star3);

	star5.drawLine(star1, g);
	star3.drawLine(star2, g);
	star3.drawLine(star4, g);
	star4.drawLine(star1, g);
	star2.drawLine(star5, g);
	//9. two 30-60-90 triangles such that the hypotenuse of one is the longer leg of the second
	Coordinate right1 = new Coordinate(600,700);
	Coordinate right2 = new Coordinate(700,800);
	Coordinate right3 = new Coordinate(right2);
	right3.rotate(Math.PI/6, right1);
	right3.scale(Math.sqrt(3)/2, right1);
	right1.drawLine(right2, g);
	right1.drawLine(right3, g);
	right2.drawLine(right3, g);
	Coordinate right4 = new Coordinate (right3);
	right4.rotate(-1*Math.PI/6, right2);
	right4.drawLine(right2, g);
	right4.drawLine(right1, g);
	
	}
	
	public static void main(String[] args){
		JFrame app = new JFrame("Coordinate Canvas");
		app.setBackground(Color.WHITE);
		app.getContentPane().add(new CoordinateCanvas());
		app.setSize(1000,1000);
		app.setVisible(true);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
}
