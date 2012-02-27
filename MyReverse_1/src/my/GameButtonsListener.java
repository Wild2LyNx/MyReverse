package my;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameButtonsListener implements ActionListener {
	GameField field;
	JPanel buttonPanel;

	public GameButtonsListener(GameField f, JPanel p) {
		this.field = f;
		this.buttonPanel = p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button.getText().compareTo("New game") == 0) {
			if (field.moveIsPossible()) {
				int n = JOptionPane
						.showConfirmDialog(
								field,
								"The game isn't finished. Are You sure you want to finish the game?",
								"Are You sure?", JOptionPane.YES_NO_OPTION);
				if (n == 0) System.out.println("Yes");
			}
		}
	}
}
