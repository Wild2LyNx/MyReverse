package my;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
		JPanel dataPanel = new JPanel();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 500);
		
		GameField field = new GameField();
		
		initButtons(buttonPanel);
		initData(dataPanel);
		
		Container container = frame.getContentPane();
		container.setLayout(new BorderLayout());
		
		container.add(dataPanel, BorderLayout.WEST);
		container.add(buttonPanel, BorderLayout.EAST);
		
		container.add(field, BorderLayout.CENTER);

		frame.setVisible(true);
		
	}

	private static void initData(JPanel dataPanel) {
		JTextArea playersInfo = new JTextArea;
		
	}

	private static void initButtons(JPanel buttonPanel) {
		// TODO Auto-generated method stub
		
	}

}
