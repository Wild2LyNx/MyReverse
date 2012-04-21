package my;



import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class DataPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JFrame frame;
	private static final String defPlayer1Name = new String("Player1 (Black) VS");
	private static final String defPlayer2Name = new String("Player2 (White)");
	String player1Name = defPlayer1Name;
	String player2Name = defPlayer2Name;
	JPanel names, setButtons;
	JTextArea moveAndGameInfo;

	JLabel head = new JLabel("Players info:");
	JLabel player1, player2;

	public DataPanel(JFrame frame) {
		this.frame = frame;
		createNameLabels();
		createChangeButtons();
		createMoveAndGameInfo();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(30, 0, 400, 10));
				
		head.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		names.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		setButtons.setAlignmentX(JComponent.CENTER_ALIGNMENT);	
	}

	private void createMoveAndGameInfo() {
		moveAndGameInfo = new JTextArea();		
		moveAndGameInfo.setBackground(null);	
		moveAndGameInfo.setFont(new Font(Font.DIALOG, Font.BOLD, 12));	
		moveAndGameInfo.setEditable(false);
		add(moveAndGameInfo);
	}

	private void createNameLabels() {
		add(head);

		player1 = new JLabel(player1Name);
		player2 = new JLabel(player2Name);

		names = new JPanel();
		names.add(player1);
		names.add(player2);		
//		names.setFont(new Font("Serif", Font.ITALIC, 36));
		add(names);
		add(Box.createVerticalStrut(3));
		
		head.setLabelFor(names);		
	}

	private void createChangeButtons() {
		
		setButtons = new JPanel();
		JButton p1 = new JButton("Set Player's 1 name");
		JButton p2 = new JButton("Set Player's 2 name");

		setButtons.add(p1);
		setButtons.add(p2);
		add(setButtons);

		ChangeNameButtonListener changeNameListener = new ChangeNameButtonListener(
				frame, this, player1, player2);

		p1.addActionListener(changeNameListener);
		p2.addActionListener(changeNameListener);

	}

	public void setNameForComp(int descriptor) {
		if (descriptor == 0) {
			player1.setText("Computer (Black) VS");
			player2.setText(player2Name);
//			setButtons.remove(0);
		}
		
		if (descriptor == 1) {
			player1.setText(player1Name);
			player2.setText("Computer (White)");
//			setButtons.remove(1);
		}
	}

	public void reInit() {
		remove(setButtons);
		createChangeButtons();
		
	}

	public void setPlayerName(String playerName, int plNumber) {
		if (playerName != null){
			if (plNumber == 1) {
				player1Name = playerName;
				player1.setText(playerName  + " (Black)  VS ");
			}
			else if (plNumber == 2) {
				player2Name = playerName;
				player2.setText(playerName + " (White)");
			}
		}
	}

}
