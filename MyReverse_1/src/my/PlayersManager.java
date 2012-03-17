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
	DataPanel dataPanel;
	int descriptor = 3;

	public PlayersManager(JFrame fr, GameField fd, DataPanel dp) {
		frame = fr;
		field = fd;
		this.dataPanel = dp;
	}

	public void setPlayConfig() {
		final JDialog choiseDialog = new JDialog(frame, "Alternative choise", true);
		ArrayList<JRadioButton> variantButtons = new ArrayList<JRadioButton>();
		final ButtonGroup group = new ButtonGroup();
		JPanel selectButtonsPanel = new JPanel();
		selectButtonsPanel.setLayout(new BoxLayout(selectButtonsPanel,
				BoxLayout.Y_AXIS));

		variantButtons.add(new JRadioButton("Two players"));
		variantButtons.add(new JRadioButton("Player vs Computer"));

		for (int i = 0; i < variantButtons.size(); i++) {
			JRadioButton curButton = variantButtons.get(i);
			curButton.setActionCommand(curButton.getText());
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
				if (command == "Two players") {
					field.addMouseListener(new GameListenerFor2P(field));
				}
				if (command == "Player vs Computer") {
					int compDescriptor = generateChoiceDialog();
					dataPanel.reInit();
					dataPanel.setNameForComp(compDescriptor);
					field.addMouseListener(new GameListenerForComp(field,
							compDescriptor));
				}
				choiseDialog.setVisible(false);
				choiseDialog.dispose();
			}

			private int generateChoiceDialog() {
				final JDialog setColorDialog = new JDialog(frame,
						"Choose comp color", true);
				ArrayList<JRadioButton> variantButtons = new ArrayList<JRadioButton>();
				final ButtonGroup group = new ButtonGroup();
				JPanel selectButtonsPanel = new JPanel();
				selectButtonsPanel.setLayout(new BoxLayout(selectButtonsPanel,
						BoxLayout.Y_AXIS));

				variantButtons.add(new JRadioButton("Black"));
				variantButtons.add(new JRadioButton("White"));

				for (int i = 0; i < variantButtons.size(); i++) {
					JRadioButton curButton = variantButtons.get(i);
					curButton.setActionCommand(curButton.getText());
					group.add(curButton);
					selectButtonsPanel.add(curButton);
					selectButtonsPanel.add(Box.createVerticalStrut(5));
					curButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
				}

				variantButtons.get(0).setSelected(true);

				JButton selectButton = new JButton("Set computer's color");
				selectButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String command = group.getSelection()
								.getActionCommand();
						if (command == "Black")
							descriptor = 0;
						if (command == "White")
							descriptor = 1;
						setColorDialog.setVisible(false);
						setColorDialog.dispose();
					}
				});
				JPanel colorChooserContentPane = new JPanel(new BorderLayout());
				colorChooserContentPane.add(selectButtonsPanel, BorderLayout.CENTER);
				colorChooserContentPane.add(selectButton, BorderLayout.PAGE_END);
				colorChooserContentPane.setOpaque(true);
				setColorDialog.setContentPane(colorChooserContentPane);

				setColorDialog.setSize(new Dimension(300, 150));
				setColorDialog.setLocationRelativeTo(frame);
				setColorDialog.setVisible(true);
				return descriptor;
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
