package my;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameButtonsListener implements ActionListener {
	JFrame frame;
	GameField field;
	JPanel buttonPanel;
	PlayersManager plManager;

	public GameButtonsListener(JFrame fr, GameField f, JPanel p, PlayersManager plMng) {
		this.frame = fr;
		this.field = f;
		this.buttonPanel = p;
		this.plManager = plMng;
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
					makeNewGame();
				}
			} else{
				makeNewGame();
			}
		}
		
		if (button.getText().compareTo("Undo") == 0){
			if ((field.moveCounter == 0)|(field.undoAllCells.isEmpty())|(field.gameOver)){
				JOptionPane.showMessageDialog(field, "Sorry, it's impossible!", "What a pity :(", JOptionPane.INFORMATION_MESSAGE);
			}
			else if (!field.undoAllCells.isEmpty()) field.undo();
		}
		
		if (button.getText().compareTo("Redo") == 0){
			if ((field.redoCounter == 0)|(field.redoAllCells.isEmpty()|field.gameOver)){
				JOptionPane.showMessageDialog(field, "Sorry, it's impossible!", "What a pity :(", JOptionPane.INFORMATION_MESSAGE);
			}
			else if (!(field.redoCounter == 0)) field.redo();
		}
	}

	private void makeNewGame() {
		field.newGame();
		field.repaint();
		
		MouseListener[] mls = (MouseListener[])(field.getListeners(MouseListener.class));
		try {
			 field.removeMouseListener(mls[0]);
		}
		catch (ArrayIndexOutOfBoundsException ex) {
		}
		
		plManager.setPlayConfig();
	}
}
