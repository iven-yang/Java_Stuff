import java.awt.GridLayout;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Frame extends JFrame implements ActionListener{
	private JPanel panel;
	public static JButton done;
	private JTextField m1,m2,m3,m4;
	private boolean isdone=false;
	private ImageEditor parent;
	public Frame(ImageEditor p){
		super("Matrix");
		parent = p;
		panel = new JPanel(new GridLayout(3,2));
		
		done = new JButton("Done");
		done.addActionListener(parent);
		m1=new JTextField("1");
		m2=new JTextField("0");
		m3=new JTextField("0");
		m4=new JTextField("1");
		panel.add(m1);
		panel.add(m2);
		panel.add(m3);
		panel.add(m4);
		panel.add(done);
		
		this.add(panel);
		
		this.setSize(250,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void Set1(int val){
		m1.setText(val+"");
	}
	public void Set2(int val){
		m2.setText(val+"");
	}
	public void Set3(int val){
		m3.setText(val+"");
	}
	public void Set4(int val){
		m4.setText(val+"");
	}
	public double get1(){
		return Double.parseDouble(m1.getText());
	}
	public double get2(){
		return Double.parseDouble(m2.getText());
	}
	public double get3(){
		return Double.parseDouble(m3.getText());
	}
	public double get4(){
		return Double.parseDouble(m4.getText());
	}
	public boolean getdone(){
		return isdone;
	}
	
	/*public static void main(String[] args){
		new Frame(parent);
	}*/
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==done){
			isdone=true;
		}		
	}
}
