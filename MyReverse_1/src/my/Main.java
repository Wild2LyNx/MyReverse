package my;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
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
		JFrame frame = new JFrame ("MyRverse");
		JPanel buttonPanel = new JPanel();
		DataPanel dataPanel = new DataPanel(frame);
		JTextArea moveAndGameInfo = new JTextArea();
		moveAndGameInfo.setBackground(null);	
		moveAndGameInfo.setFont(new Font(Font.DIALOG, Font.BOLD, 12));		
		dataPanel.add(moveAndGameInfo);		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(150, 100, 900, 500);
		
		GameField field = new GameField(frame, moveAndGameInfo);
		
		PlayersManager plManager = new PlayersManager(frame, field);
		plManager.setPlayConfig();
		
		initButtons(buttonPanel, field);		
		
		Container container = frame.getContentPane();
		container.setLayout(new BorderLayout());
		
		container.add(dataPanel, BorderLayout.WEST);
		container.add(buttonPanel, BorderLayout.EAST);
		
		container.add(field, BorderLayout.CENTER);

		frame.setVisible(true);
		
	}	
	
	private static void initButtons(JPanel buttonPanel, GameField field) {
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 400, 10));
		
		ArrayList<JButton> gameButtons = new ArrayList<JButton>();
		gameButtons.add(new JButton("New game"));
		gameButtons.add(new JButton("Undo"));
		gameButtons.add(new JButton("Redo"));
		
		GameButtonsListener buttonsListener = new GameButtonsListener(field, buttonPanel);
		
		for (int i = 0; i < gameButtons.size(); i++){
			gameButtons.get(i).addActionListener(buttonsListener);
			buttonPanel.add(gameButtons.get(i));
			buttonPanel.add(Box.createVerticalStrut(5));
			gameButtons.get(i).setAlignmentX(JComponent.CENTER_ALIGNMENT);			
		}				
	}

}
