/*
 * File: FractalFrame.java
 * Purpose:  To display your FractalPanel and save your fractal
 * Author: Mr. Reed
 */
/**
 * 
 * Goes with Iven Yang's
 * FractalPanel  
 * 
 * 
 */
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FractalFrame extends JFrame implements ActionListener{
	JLabel numLabel;
	JTextField numField;
	JButton draw, up, down, save;
	FractalPanel panel;
	JPanel buttonPanel;
	JScrollPane sp;
	int x=0;
		
	/************************************************
	 * Constructor
	*************************************************/
	public FractalFrame(){
		super("Fractal Frame!");
		Container c = this.getContentPane();
		makeButtonPanel();
		c.add(buttonPanel, BorderLayout.NORTH);
		panel = new FractalPanel();
		sp = new JScrollPane(panel);
		sp.setPreferredSize(new Dimension(100,100));
		c.add(sp);
	}
	
	/*Preconditions: NONE
	 *Postconditions: the buttons and fields are created and added
	 *  	to buttonPanel
	 */
	private void makeButtonPanel(){
		buttonPanel = new JPanel();
		numLabel = new JLabel("Levels");
		numField = new JTextField(5);
		numField.setText("0");
		draw = new JButton("Draw");
		up = new JButton(">");
		down=new JButton("<");
		draw.addActionListener(this);
		up.addActionListener(this);
		down.addActionListener(this);
		
		buttonPanel.add(numLabel);
		buttonPanel.add(numField);
		buttonPanel.add(draw);
		buttonPanel.add(down);
		buttonPanel.add(up);
		
		save = new JButton("Save Image");
		this.add(save, BorderLayout.SOUTH);
		save.addActionListener(this);
	}
	
	
	
	/**************************************************
	 Handles the button event
	 **************************************************/
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==draw){
		x=0;
		try{
			x = Integer.parseInt(numField.getText());
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, "Enter an Integer!");
		}
		panel.setInput(x);
		panel.beginFract();
		panel.repaint();
		}
		if(e.getSource()==save){
			String fileNm = JOptionPane.showInputDialog(this, "File Name: ","Save to PNG file.",JOptionPane.QUESTION_MESSAGE);
			if(fileNm == null)
				return;
			if(fileNm.indexOf(".")<0)
				fileNm+=".png";
			File savedFile = new File(fileNm);
			panel.saveFile(savedFile);
		}
		if(e.getSource()==up){
				x++;
				numField.setText(""+x);
				panel.setInput(x);
				panel.beginFract();
				panel.repaint();
		}
		if(e.getSource()==down){
			x--;
			if(x<0)
				x=0;
			numField.setText(""+x);
			panel.setInput(x);
			panel.beginFract();
			panel.repaint();
	}
	}

/*****************************************************
 * MAIN FUNCTION
 ******************************************************/	
	public static void main(String[] args)
	{
		FractalFrame win = new FractalFrame();
		win.setSize(1280,980);
		
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.show();
	}

}

