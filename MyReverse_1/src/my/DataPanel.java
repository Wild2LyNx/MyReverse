package my;



import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DataPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JFrame frame;
	String player1Name = new String("Player 1 (Black)  VS");
	String player2Name = new String("Player 2 (White)");
	JPanel names, setButtons;

	JLabel head = new JLabel("Players info:");
	JLabel player1, player2;

	public DataPanel(JFrame frame) {
		this.frame = frame;
		createNameLabels();
//		createScoreLabel();
		createChangeButtons();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(30, 0, 400, 10));
				
		head.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		names.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		setButtons.setAlignmentX(JComponent.CENTER_ALIGNMENT);	
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

	/*private void createScoreLabel() {
		// TODO Auto-generated method stub

	}*/

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

	public void setUpLayout() {

	}

}
