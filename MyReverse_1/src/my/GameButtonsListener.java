package my;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameButtonsListener implements ActionListener {
	JFrame frame;
	GameField field;
	JPanel buttonPanel;
	DataPanel dataPanel;
	PlayersManager plManager;
	boolean fieldIsStopped = false;

	public GameButtonsListener(JFrame fr, GameField f, JPanel bp, DataPanel dp, PlayersManager plMng) {
		this.frame = fr;
		this.field = f;
		this.buttonPanel = bp;
		this.dataPanel = dp;
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
			if ((field.moveCounter == 1)|(field.undoAllCells.isEmpty())|(field.gameOver)){
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
		
		/*if (button.getText().compareTo("Stop it!") == 0){
			MouseListener[] mls = (MouseListener[])(field.getListeners(MouseListener.class));
		
			if (!fieldIsStopped){
				try {
					mls[0].notify();
					mls[0].wait();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				fieldIsStopped = true;
				System.out.println("Stopped");
			}
			
			else if (fieldIsStopped){
				mls[0].notify();
				fieldIsStopped = false;
			}
		}*/
	}

	private void makeNewGame() {
		System.out.println("New game");
		frame.setVisible(false);		
		plManager.setPlayConfig();
		field.newGame();
		frame.setVisible(true);
	}
}
