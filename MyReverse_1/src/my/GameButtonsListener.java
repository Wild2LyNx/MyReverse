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
								"The game is not finished. Are You sure that You want to finish the game?",
								"Warning", JOptionPane.YES_NO_OPTION);
				if (n == 0) {
					field.newGame();
					field.repaint();
				}
			}
		}
		
		if (button.getText().compareTo("Undo") == 0){
			if ((field.moveCounter == 0)|(field.undoAllCells.isEmpty())){
				JOptionPane.showMessageDialog(field, "Sorry, it's impossible!", "What a pity :(", JOptionPane.INFORMATION_MESSAGE);
			}
			if (!field.undoAllCells.isEmpty()) field.undo();
		}
		
		if (button.getText().compareTo("Redo") == 0){
			if ((field.redoCounter == 0)|(field.redoAllCells.isEmpty())){
				JOptionPane.showMessageDialog(field, "Sorry, it's impossible!", "What a pity :(", JOptionPane.INFORMATION_MESSAGE);
			}
			if (!(field.redoCounter == 0)) field.redo();
		}
	}
}
