package my;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DataPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	String player1Name = new String ("Player 1 (Black)");
	String player2Name = new String ("Player 2 (White)");;
	
	JLabel head = new JLabel("Players info:"); 
	JLabel player1, player2;
	
	public DataPanel(){
		createNameLabels();
		createScoreLabel();
		createChangeButtons();		
	}
	
	private void createNameLabels() {		
		player1 = new JLabel(player1Name);
		player2 = new JLabel(player2Name);
		
		JPanel names = new JPanel();
		names.add(player1);
		names.add(player2);
		head.setLabelFor(names);
		
		names.setFont(new Font("Serif", Font.ITALIC, 36));
	}

	private void createScoreLabel() {
		// TODO Auto-generated method stub
		
	}	
	
	private void createChangeButtons() {
		// TODO Auto-generated method stub
		
	}
	
	public void setUpLayout(){
		
	}
	

}
