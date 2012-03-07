package my;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PlayersManager {
	JFrame frame;
	GameField field;
	
	public PlayersManager(JFrame fr, GameField fd){
		frame = fr;
		field = fd;
	}

	public void setPlayConfig() {
		final JDialog choiseDialog = new JDialog(frame, "Alternative choise");
		ArrayList<JRadioButton> variantButtons = new ArrayList<JRadioButton>();
		final ButtonGroup group = new ButtonGroup();
		JPanel selectButtonsPanel = new JPanel();
		selectButtonsPanel.setLayout(new BoxLayout(selectButtonsPanel, BoxLayout.Y_AXIS));		
		
		variantButtons.add(new JRadioButton("Two players"));
		variantButtons.add(new JRadioButton("Player vs Computer"));
		
		for (int i = 0; i< variantButtons.size(); i++){
			JRadioButton curButton = variantButtons.get(i);
			curButton.setActionCommand(curButton.getName());
			group.add(curButton);
			selectButtonsPanel.add(curButton);
			selectButtonsPanel.add(Box.createVerticalStrut(5));
			curButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		}
		
		variantButtons.get(0).setSelected(true);
		
		JButton selectButton = new JButton("Select!");
		selectButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String command = group.getSelection().getActionCommand();
				if (command == "Two players"){
					field.addMouseListener(new GameListener(field));
				}
				choiseDialog.setVisible(false);
                choiseDialog.dispose();
			}
		});
		
		JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(selectButtonsPanel, BorderLayout.CENTER);
        contentPane.add(selectButton, BorderLayout.PAGE_END);
        contentPane.setOpaque(true);
        choiseDialog.setContentPane(contentPane);
        
        choiseDialog.setSize(new Dimension(300, 150));
		choiseDialog.setLocationRelativeTo(frame);
        choiseDialog.setVisible(true);		
	}
	
	
	

}
