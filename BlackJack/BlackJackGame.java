import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class BlackJackGame extends JFrame implements ActionListener{
	//	private member Variables
	//CAN BET $$$MONEY$$$
	// CHEAT: type "show me the money" and click bet to get $10,000.00!!!!
	private Deck mydeck = new Deck();
	private Hand p1 = new Hand();
	private Hand p2 = new Hand();
	private JButton hit, stand, play, bet;
	private String p1v="Test",p2v="Test";
	private JPanel table, menu;
	private JTextField warpgate, moola;//cant make an int TextField so can only bet certain numbers (50,100,500,all)
	private JLabel background;
	private int hitnum=0;
	private boolean begin=false;
	private boolean win1;
	private boolean OrbitalScan=false;
	Scanner moneymaker=new Scanner(System.in);
	public int turn=0,money=500,betmoney=0;//turns
	public BlackJackGame(boolean reveal){
		super("-------------------------------------------------------------Black Jack-------------------------------------------");
		//sets up table
		OrbitalScan=reveal;
		table = new JPanel();
		table.setBackground(new Color(0,112,60));
		this.add(table);
		//sets up menu
		menu = new JPanel();
		this.add(menu, BorderLayout.NORTH);
		menu.setLayout(new GridLayout(2,4,10,10));

		hit = new JButton("Hit");
		menu.add(hit, BorderLayout.NORTH);
		hit.addActionListener(this);
		
		stand = new JButton("Stand");
		menu.add(stand, BorderLayout.NORTH);
		stand.addActionListener(this);

		bet = new JButton("Bet");
		menu.add(bet, BorderLayout.NORTH);
		bet.addActionListener(this);
		
		play = new JButton("Deal Cards");
		menu.add(play, BorderLayout.SOUTH);
		play.addActionListener(this);
		
		warpgate = new JTextField();
		menu.add(warpgate, BorderLayout.SOUTH);
		
		moola = new JTextField();
		menu.add(moola, BorderLayout.SOUTH);
		//draw table
		ImageIcon icon = new ImageIcon("./pics/Blackjack-Club-Software.gif");
		background = new JLabel(icon);
		table.add(background);

		this.setSize(700,700);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "Instructions: Click Deal Cards to Play. Put amount to bet in textbox below bet button (Can only type numbers and the word all to bet all your money (Don't input any other strings)");
		BlackJackGame gooey = new BlackJackGame(false);
	}
	//paint
	public void paint(Graphics g){
		super.paint(g);
		g.setFont(new Font("Times New Roman",Font.BOLD,15));
		if(!OrbitalScan){
			warpgate.setText("Click Deal Cards to Play");
			moola.setText("Put amount to bet here. Click Bet to bet.");
		}
		if(begin)
			p2.hand.get(0).faceDown();
		if(win1)
			p2.hand.get(0).faceUp();

		//paints p1 hand
		for(int i=0; i<p1.hand.size();i++){
			g.drawImage(p1.hand.get(i).img,100+13*i,200,72,96,this);
		}
		//paints deck
		//if(OrbitalScan){
			for(int i=0;i<mydeck.deck.size();i++){
				Card xyz=(Card)mydeck.deck.Get(i);
				xyz.faceDown();
				xyz.draw(table.getGraphics(),270+2*i,10+7*i);
				xyz.faceUp();
			}
			p1v="Your Value: "+p1.evaluate();
			p2v="Dealer Value:"+p2.evaluate();
			g.drawString(p1v,100,330);
			g.drawString(p2v,500,330);
			g.drawString(("Money: $"+money), 100, 530);
			g.drawString(("Betting: $"+betmoney), 100, 550);
			if(!begin){
				g.drawString("PLACE YOUR BET NOW", 300, 630);
				g.drawString("Only type numbers and the word all to bet all. Then click Bet", 185, 650);
			}
			if(begin)
				g.drawString("There is a cheat that gives you money", 250, 630);
		//}
		if(mydeck.deck.size()<=3){
			mydeck=new Deck();
			JOptionPane.showMessageDialog(this, "Shuffle!");
		}
		//paints p2 hand
		for(int i=0; i<p2.hand.size();i++){
			g.drawImage(p2.hand.get(i).img,500+10*i,200,72,96,this);
		}
	}
	public void actionPerformed(ActionEvent e){
		boolean win =false;
		//hit command to add cards
		if(begin){
			if(e.getSource()==bet){
				String mala=moola.getText();
				if(mala.equals("show me the money"))//secret code gives you $10,000.00
					money+=10000;
			}
			this.repaint();
		}
		if(!begin){
			if(e.getSource()==bet){
				String mula=moola.getText();
				if(mula.equals("show me the money"))//secret code gives you $10,000.00
						money+=10000;
				else if(mula.equals("all")){
						betmoney=money;
				}
				else if(Integer.parseInt(moola.getText())<=money){
						betmoney=Integer.parseInt(moola.getText());
				}
			}
			this.repaint();
		}
		if(begin){
		if(e.getSource()==hit){
			if(hitnum<3){
				hitnum++;
				Card store = mydeck.deal();
				p1.insert(store);
				warpgate.setText("You've been dealt: "+store);
				if(((p2.evaluate()<17)||(p1.evaluate()>p2.evaluate()))&&(p1.evaluate()<=21)){
					p2.insert(mydeck.deal());
					this.repaint();
					warpgate.setText("Dealer has been dealt: "+p2.hand.get(p2.hand.size()-1));
				}
				this.repaint();
				//if player busts they lose
				if(p1.evaluate()>21){
					win1=true;
					//this.repaint();
					JOptionPane.showMessageDialog(this, "Bust! You Lose Loser. Click Deal Cards to Play Again");
					money-=betmoney;
					begin=false;
					this.repaint();
					betmoney=0;
				}
				if(p2.evaluate()>21){
					warpgate.setText("You Win");
					win1=true;
					//this.repaint();
					JOptionPane.showMessageDialog(this, "Dealer Busts! You Win. Click Deal Cards to Play Again");
					money+=betmoney;
					win=true;
					begin=false;
					this.repaint();
					betmoney=0;
				}
			}
		}
		//when player stands dealer starts to get cards until over 17
		if(e.getSource()==stand){
			for(int x=0; x<3; x++){
				if(((p2.evaluate()<17)||(p1.evaluate()>p2.evaluate()))&&(p1.evaluate()<=21)){
					p2.insert(mydeck.deal());
					this.repaint();
					warpgate.setText("Dealer has been dealt: "+p2.hand.get(p2.hand.size()-1));
					//if dealer busts player wins
					if(p2.evaluate()>21){
						warpgate.setText("You Win");
						win1=true;
						//this.repaint();
						JOptionPane.showMessageDialog(this, "Dealer Busts! You Win. Click Deal Cards to Play Again");
						money+=betmoney;
						win=true;
						begin=false;
						this.repaint();
						betmoney=0;
					}
				}
			}//if no one has lost from busting check cards
			if(!win){
				if(p2.evaluate()>=p1.evaluate()){
					warpgate.setText("You Lose Loser");
					win1=true;
					//this.repaint();
					JOptionPane.showMessageDialog(this, "You lose against the Dealer's "+ p2.evaluate()+". Click Deal Cards to Play Again");
					money-=betmoney;
					begin=false;
					this.repaint();
					betmoney=0;
				}
				else{
					warpgate.setText("You Win");
					win1=true;
					//this.repaint();
					JOptionPane.showMessageDialog(this, "You win against Dealer's "+ p2.evaluate()+". Click Deal Cards to Play Again");
					money+=betmoney;
					begin=false;
					this.repaint();
					betmoney=0;
				}
			}
		}}
		//starts game, deals 2 cards each
		if(e.getSource()==play){
			OrbitalScan=true;
			begin=true;
			win1=false;
			win=false;
			hitnum=0;
			//empty hands
			while(!p1.hand.isEmpty())
				p1.hand.remove(p1.hand.size()-1);
			while(!p2.hand.isEmpty())
				p2.hand.remove(p2.hand.size()-1);
			p1.insert(mydeck.deal());
			warpgate.setText("You've been dealt: "+p1.hand.get(p1.hand.size()-1));
			p1.insert(mydeck.deal());
			warpgate.setText("You've been dealt: "+p1.hand.get(p1.hand.size()-1));
			p2.insert(mydeck.deal());
			p2.insert(mydeck.deal());
			warpgate.setText("Dealer has been dealt: "+p2.hand.get(p2.hand.size()-2));
			this.repaint();
			if(p1.evaluate()==21){
				win1=true;win=true;
				JOptionPane.showMessageDialog(this, "BlackJack! You Win! Click Deal Cards to Play Again");
				money+=betmoney;
				begin=false;
				this.repaint();
				betmoney=0;
			}
			if(p2.evaluate()==21){
				win1=true;
				JOptionPane.showMessageDialog(this, "Dealer gets BlackJack! You Lose. Click Deal Cards to Play Again");
				money-=betmoney;
				this.repaint();
				betmoney=0;
			}
			
		}
	}
}