import java.awt.*;
import javax.swing.*;

import java.io.*;
import java.awt.event.*;
public class ImageEditor extends JFrame implements ActionListener{
	private ImageCanvas orig, alter;
	String file = "flower.jpg";
	private JMenuItem open;
	private JMenuItem dick;
	private JMenuItem flower;
	private JMenuItem pally;
	private JMenuItem wiz;
	private JMenuItem oryx;
	private JMenuItem Mc;
	private JButton reset;
	private JMenuItem BlackandWhite;
	private JMenuItem sepia;
	private JMenuItem red;
	private JMenuItem green;
	private JMenuItem blue;
	private JMenuItem negative;
	private JMenuItem vertMirror;
	private JMenuItem horizMirror;
	private JMenuItem blur;
	private JMenuItem sharpen;
	private JMenuItem rotate;
	private JMenuItem resize;
	private JMenuItem HStretch;
	private JMenuItem VStretch;
	private JMenuItem transform;
	private JMenuItem HEdgeDetect;
	private JMenuItem VEdgeDetect;
	private JMenuItem Emboss;
	private JMenuItem Brightness;
	private JMenuItem Contrast;
	
	private Frame framer;
	
	public ImageEditor(){
		super("Image Editor");
		makeMenu();
		orig = new ImageCanvas();
		orig.setImage(new File(file));
		alter = new ImageCanvas();
		alter.setImage(new File(file));
		alter.tester();
		
		framer=new Frame(this);
		framer.setVisible(false);
		
		JPanel stuff = new JPanel();
		stuff.setLayout(new GridLayout(1,2));
		stuff.add(new JScrollPane(orig));
		stuff.add(new JScrollPane(alter));
		this.add(stuff, BorderLayout.CENTER);
		//finishing up
		this.setSize(1280,800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void makeMenu(){
		JMenuBar bar = new JMenuBar();
		bar.setLayout(new GridLayout(2,2));
		JMenu file = new JMenu("File");
		JMenu color = new JMenu("Color");
		JMenu trans = new JMenu("Transformations");
		JMenu other = new JMenu("Other Stuff");
		JMenu pics = new JMenu("Choose a Picture");
		
		open = new JMenuItem("Open");
		open.addActionListener(this);
		
		dick = new JMenuItem("Dick Cheney");
		dick.addActionListener(this);
		
		flower = new JMenuItem("Flower");
		flower.addActionListener(this);
		
		pally = new JMenuItem("Paladin");
		pally.addActionListener(this);
		
		wiz = new JMenuItem("Wizard");
		wiz.addActionListener(this);
		
		oryx = new JMenuItem("Oryx");
		oryx.addActionListener(this);
		
		Mc = new JMenuItem("Master Chief");
		Mc.addActionListener(this);
		
		
		reset = new JButton("CLICK HERE TO RESET PICTURE");
		reset.addActionListener(this);
		//reset.setBackground(Color.BLACK);
		
		BlackandWhite = new JMenuItem("Black and White");
		BlackandWhite.addActionListener(this);

		sepia = new JMenuItem("Sepia");
		sepia.addActionListener(this);
		
		red = new JMenuItem("Red");
		red.addActionListener(this);

		green = new JMenuItem("Green");
		green.addActionListener(this);

		blue = new JMenuItem("Blue");
		blue.addActionListener(this);
		
		negative = new JMenuItem("Negative");
		negative.addActionListener(this);

		vertMirror = new JMenuItem("Vertically Mirror");
		vertMirror.addActionListener(this);

		horizMirror = new JMenuItem("Horizontally Mirror");
		horizMirror.addActionListener(this);
		
		blur = new JMenuItem("Blur");
		blur.addActionListener(this);

		sharpen = new JMenuItem("Sharpen");
		sharpen.addActionListener(this);
		
		rotate = new JMenuItem("Rotate");
		rotate.addActionListener(this);
		
		resize = new JMenuItem("Scale");
		resize.addActionListener(this);
		
		HStretch = new JMenuItem("Horizontally Stretch");
		HStretch.addActionListener(this);
		
		VStretch = new JMenuItem("Vertically Stretch");
		VStretch.addActionListener(this);
		
		transform = new JMenuItem("Manual Transform");
		transform.addActionListener(this);
		
		HEdgeDetect = new JMenuItem("Horizontal Edge Detect");
		HEdgeDetect.addActionListener(this);
		
		VEdgeDetect = new JMenuItem("Vertical Edge Detect");
		VEdgeDetect.addActionListener(this);
		
		Emboss = new JMenuItem("Emboss");
		Emboss.addActionListener(this);
		
		Brightness = new JMenuItem("Brightness");
		Brightness.addActionListener(this);
		
		Contrast = new JMenuItem("Contrast");
		Contrast.addActionListener(this);
		
		file.add(open);
		color.add(BlackandWhite);
		color.add(sepia);
		color.add(red);
		color.add(green);
		color.add(blue);
		color.add(negative);
		trans.add(vertMirror);
		trans.add(horizMirror);
		other.add(blur);
		other.add(sharpen);
		trans.add(rotate);
		trans.add(resize);
		trans.add(HStretch);
		trans.add(VStretch);
		trans.add(transform);
		other.add(HEdgeDetect);
		other.add(VEdgeDetect);
		other.add(Emboss);
		other.add(Brightness);
		other.add(Contrast);
		
		pics.add(dick);
		pics.add(flower);
		pics.add(pally);
		pics.add(wiz);
		pics.add(oryx);
		pics.add(Mc);
		
		bar.add(file);
		bar.add(color);
		bar.add(trans);
		bar.add(other);
		bar.add(pics);
		bar.add(reset);
		bar.add(new JTextField("Welcome to Image Editor > 9000"));
		//bar.setEnabled(false);
		
		this.setJMenuBar(bar);
	}
	
	public void reset(){
		alter.setImage(new File(file));
	}
	
	public void completereset(){
		orig.setImage(new File(file));
		alter.setImage(new File(file));
	}
	
	public static void main(String[] args){new ImageEditor();}

	public void actionPerformed(ActionEvent e){
		if(e.getSource()==open){
			JFileChooser jfc = new JFileChooser();
			int result = jfc.showOpenDialog(this);
			if(result == JFileChooser.CANCEL_OPTION)
				return;
			File f = jfc.getSelectedFile();
			file=f.getAbsolutePath();
			//orig.setImage(f);
			//alter.setImage(f);
			completereset();
			this.repaint();
		}
		if(e.getSource()==dick){
			file = "DickCheney.jpg";
			completereset();
			this.repaint();
		}
		if(e.getSource()==flower){
			file = "flower.jpg";
			completereset();
			this.repaint();
		}
		if(e.getSource()==pally){
			file = "Maxed Paladin Death.jpg";
			completereset();
			this.repaint();
		}
		if(e.getSource()==wiz){
			file = "Maxed Wizard Death.jpg";
			completereset();
			this.repaint();
		}
		if(e.getSource()==oryx){
			file = "Decorified Oryx.jpg";
			completereset();
			this.repaint();
		}
		if(e.getSource()==Mc){
			file = "MC.jpg";
			completereset();
			this.repaint();
		}
		if(e.getSource()==reset){
			reset();
			this.repaint();
		}
		if(e.getSource()==BlackandWhite){
			reset();
			alter.BlackandWhite();
			this.repaint();
		}
		if(e.getSource()==sepia){
			reset();
			alter.Sepia();
			this.repaint();
		}
		if(e.getSource()==red){
			reset();
			alter.red();
			this.repaint();
		}
		if(e.getSource()==green){
			reset();
			alter.green();
			this.repaint();
		}
		if(e.getSource()==blue){
			reset();
			alter.blue();
			this.repaint();
		}
		if(e.getSource()==negative){
			alter.negative();
			this.repaint();
		}
		if(e.getSource()==vertMirror){
			alter.mirror(false);
			this.repaint();
		}
		if(e.getSource()==horizMirror){
			alter.mirror(true);
			this.repaint();
		}
		if(e.getSource()==blur){
			reset();
			int bluramt = Integer.parseInt(JOptionPane.showInputDialog(null));
			alter.blur(bluramt);
			this.repaint();
		}
		if(e.getSource()==sharpen){
			reset();
			int sharpamt = Integer.parseInt(JOptionPane.showInputDialog(null));
			alter.sharpen(sharpamt);
			this.repaint();
		}
		if(e.getSource()==rotate){
			double angle = Double.parseDouble(JOptionPane.showInputDialog(null));
			reset();
			alter.rotate(angle);
			this.repaint();
		}
		if(e.getSource()==resize){
			double percentsize = Double.parseDouble(JOptionPane.showInputDialog(null));
			reset();
			alter.resize(percentsize);
			this.repaint();
		}
		if(e.getSource()==HStretch){
			double factor = Double.parseDouble(JOptionPane.showInputDialog(null));
			reset();
			alter.HorizStretch(factor);
			this.repaint();
		}
		if(e.getSource()==VStretch){
			double factor = Double.parseDouble(JOptionPane.showInputDialog(null));
			reset();
			alter.VertStretch(factor);
			this.repaint();
		}
		if(e.getSource()==transform){
			/*double c1 = Double.parseDouble(JOptionPane.showInputDialog("Top Left Cell:"));
			double c2 = Double.parseDouble(JOptionPane.showInputDialog("Top Right Cell:"));
			double c3 = Double.parseDouble(JOptionPane.showInputDialog("Bottom Left Cell:"));
			double c4 = Double.parseDouble(JOptionPane.showInputDialog("Bottom Right Cell:"));*/
			
			framer.setVisible(true);
		}
		if(e.getSource()==framer.done){
			framer.setVisible(false);
			
				double c1=framer.get1();
				double c2=framer.get2();
				double c3=framer.get3();
				double c4=framer.get4();
			
			framer.Set1(1);
			framer.Set2(0);
			framer.Set3(0);
			framer.Set4(1);
				
			double[][] matrix = new double[2][2];
			matrix[0][0]=c1;
			matrix[0][1]=c2;
			matrix[1][0]=c3;
			matrix[1][1]=c4;
			reset();
			alter.transform(matrix);
			this.repaint();
			System.out.println("You have to RESIZE window");
		}
		if(e.getSource()==HEdgeDetect){
			double val = Double.parseDouble(JOptionPane.showInputDialog("EdgeDetect (2 is best):"));
			reset();
			alter.EdgeDetect(false, val);
			this.repaint();
		}
		if(e.getSource()==VEdgeDetect){
			double val = Double.parseDouble(JOptionPane.showInputDialog("EdgeDetect (2 is best):"));
			reset();
			alter.EdgeDetect(true, val);
			this.repaint();
		}
		if(e.getSource()==Brightness){
			int bf = Integer.parseInt(JOptionPane.showInputDialog("Brightness Value (-255 to 255):"));
			reset();
			alter.Brightness(bf);
			this.repaint();
		}
		if(e.getSource()==Contrast){
			int percent = Integer.parseInt(JOptionPane.showInputDialog("Contrast (-100 to 100):"));
			reset();
			alter.contrast(percent);
			this.repaint();
		}
		if(e.getSource()==Emboss){
			int val = Integer.parseInt(JOptionPane.showInputDialog("Emboss (2 is best):"));
			reset();
			alter.Emboss(val);
			this.repaint();
		}
	}
}
/*   
                            
         \   \
          \   \
          _\   \__
         (   \     )
          \___\_ _/
*/