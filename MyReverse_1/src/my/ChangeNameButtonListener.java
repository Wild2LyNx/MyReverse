package my;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChangeNameButtonListener implements ActionListener {
	
	JPanel dataPanel;
	JFrame frame;
	JLabel player1, player2;
	
	public ChangeNameButtonListener(JFrame f, JPanel p, JLabel p1, JLabel p2){
		this.frame = f;
		this.dataPanel = p;
		this.player1 = p1;
		this.player2 = p2;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		
		String newName = (String)JOptionPane.showInputDialog(
		                    frame,
		                    "Input Your name, please:\n",
		                    "Set name",
		                    JOptionPane.PLAIN_MESSAGE);

		if ((newName != null) && (newName.length() > 0)) {
			if (button.getText().compareTo("Set Player's 1 name") == 0)
			player1.setText(newName + " (Black)  VS");
			
			if (button.getText().compareTo("Set Player's 2 name") == 0)
				player2.setText(newName + " (White)");			
		    return;
		}		
	}

}
