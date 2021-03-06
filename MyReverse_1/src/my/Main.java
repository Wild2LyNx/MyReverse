package my;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				createAndShowGUI();
			}
		});

	}

	private static void createAndShowGUI() {
		JFrame frame = new JFrame("My Precious");
		JPanel buttonPanel = new JPanel();
		DataPanel dataPanel = new DataPanel(frame);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(150, 100, 900, 500);

		PlayersManager plManager = new PlayersManager(frame, dataPanel);
		plManager.setPlayConfig();

		GameField field = new GameField(frame, dataPanel.moveAndGameInfo, plManager);
		plManager.addListeners(field);
		initButtons(buttonPanel, dataPanel, field, frame, plManager);

		Container container = frame.getContentPane();
		container.setLayout(new BorderLayout());

		container.add(dataPanel, BorderLayout.WEST);
		container.add(buttonPanel, BorderLayout.EAST);

		container.add(field, BorderLayout.CENTER);

		frame.setVisible(true);

	}

	private static void initButtons(JPanel buttonPanel, DataPanel dataPanel,
			GameField field, JFrame frame, PlayersManager plManager) {
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 400, 10));

		ArrayList<JButton> gameButtons = new ArrayList<JButton>();
		gameButtons.add(new JButton("New game"));
		gameButtons.add(new JButton("Undo"));
		gameButtons.add(new JButton("Redo"));
		// gameButtons.add(new JButton("Stop it!"));

		GameButtonsListener buttonsListener = new GameButtonsListener(frame,
				field, buttonPanel, dataPanel, plManager);

		for (int i = 0; i < gameButtons.size(); i++) {
			gameButtons.get(i).addActionListener(buttonsListener);
			buttonPanel.add(gameButtons.get(i));
			buttonPanel.add(Box.createVerticalStrut(5));
			gameButtons.get(i).setAlignmentX(JComponent.CENTER_ALIGNMENT);
		}
	}

}
