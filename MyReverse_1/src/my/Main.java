package my;

import java.awt.BorderLayout;
import java.awt.Container;

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
		JFrame frame = new JFrame ("MyRverse");
		JPanel buttonPanel = new JPanel();
		DataPanel dataPanel = new DataPanel(frame);
		dataPanel.setUpLayout();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 500);
		
		GameField field = new GameField(frame);
		field.addMouseListener(new GameListener(field));
		
		initButtons(buttonPanel);		
		
		Container container = frame.getContentPane();
		container.setLayout(new BorderLayout());
		
		container.add(dataPanel, BorderLayout.WEST);
		container.add(buttonPanel, BorderLayout.EAST);
		
		container.add(field, BorderLayout.CENTER);

		frame.setVisible(true);
		
	}	

	private static void initButtons(JPanel buttonPanel) {
		// TODO Auto-generated method stub
		
	}

}
