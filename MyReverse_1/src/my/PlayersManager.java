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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PlayersManager {
	JFrame frame;
	GameField field;
	DataPanel dataPanel;
	int compDescriptor = 3;
	String networkHostName;
	JTextField hostName;
	boolean server = false;
	boolean client = false;

	public PlayersManager(JFrame fr, GameField fd, DataPanel dp) {
		frame = fr;
		field = fd;
		this.dataPanel = dp;
	}

	public void setPlayConfig() {
		final JDialog choiseDialog = new JDialog(frame, "Alternative choice",
				true);
		ArrayList<JRadioButton> variantButtons = new ArrayList<JRadioButton>();
		final ButtonGroup group = new ButtonGroup();
		JPanel selectButtonsPanel = new JPanel();
		selectButtonsPanel.setLayout(new BoxLayout(selectButtonsPanel,
				BoxLayout.Y_AXIS));

		variantButtons.add(new JRadioButton("Two players"));
		variantButtons.add(new JRadioButton("Player vs Computer"));
		variantButtons.add(new JRadioButton("Network game"));

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
				if (command == "Network game") {
					String hostName = networkParamsDialog();
					NetworkComponentsFactory creator = new NetworkComponentsFactory();
					field.addMouseListener(creator.create(hostName, server, client));
				}
				choiseDialog.setVisible(false);
				choiseDialog.dispose();
			}

			private String networkParamsDialog() {
				final JDialog networkParamsDialog = new JDialog(frame,
						"Choose your status", true);
				ArrayList<JRadioButton> variantButtons = new ArrayList<JRadioButton>();
				final ButtonGroup group = new ButtonGroup();
				JPanel selectButtonsPanel = new JPanel();
				selectButtonsPanel.setLayout(new BoxLayout(selectButtonsPanel,
						BoxLayout.Y_AXIS));
				final String serverCommand = new String(
						"Initiator (Your comp will be server)");
				final String clientCommand = new String(
						"Invitee (Your comp will be client on the server, mentioned above)");

				variantButtons.add(new JRadioButton(serverCommand));
				variantButtons.add(new JRadioButton(clientCommand));

				for (int i = 0; i < variantButtons.size(); i++) {
					JRadioButton curButton = variantButtons.get(i);
					curButton.setActionCommand(curButton.getText());
					group.add(curButton);
					selectButtonsPanel.add(curButton);
					selectButtonsPanel.add(Box.createVerticalStrut(5));
					curButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
				}

				variantButtons.get(0).setSelected(true);

				hostName = new JTextField();

				JButton selectButton = new JButton("Set host name");
				selectButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						networkHostName = hostName.getText();

						String command = group.getSelection()
								.getActionCommand();
						if (command == serverCommand) {
							server = true;
							client = false;
						}

						if (command == clientCommand) {
							server = false;
							client = true;
						}

						if ((networkHostName != null)
								&& (!networkHostName.isEmpty())) {
							networkParamsDialog.setVisible(false);
							networkParamsDialog.dispose();
						}

						if (networkHostName.isEmpty())
							JOptionPane.showMessageDialog(field,
									"You didn't input the name of server!",
									"Error", JOptionPane.INFORMATION_MESSAGE,
									null);

					}
				});
				JPanel hostNameChooserContentPane = new JPanel(
						new BorderLayout());
				hostNameChooserContentPane.add(selectButtonsPanel,
						BorderLayout.NORTH);
				hostNameChooserContentPane.add(hostName, BorderLayout.CENTER);
				hostNameChooserContentPane.add(selectButton,
						BorderLayout.PAGE_END);
				hostNameChooserContentPane.setOpaque(true);
				networkParamsDialog.setContentPane(hostNameChooserContentPane);

				networkParamsDialog.setSize(new Dimension(500, 150));
				networkParamsDialog.setLocationRelativeTo(frame);
				networkParamsDialog.setVisible(true);
				return networkHostName;
			}

			private int generateChoiceDialog() {
				final JDialog setColorDialog = new JDialog(frame,
						"Choose your color", true);
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

				JButton selectButton = new JButton("Set my playing color");
				selectButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String command = group.getSelection()
								.getActionCommand();
						if (command == "Black")
							compDescriptor = 1;
						if (command == "White")
							compDescriptor = 0;
						setColorDialog.setVisible(false);
						setColorDialog.dispose();
					}
				});
				JPanel colorChooserContentPane = new JPanel(new BorderLayout());
				colorChooserContentPane.add(selectButtonsPanel,
						BorderLayout.CENTER);
				colorChooserContentPane
						.add(selectButton, BorderLayout.PAGE_END);
				colorChooserContentPane.setOpaque(true);
				setColorDialog.setContentPane(colorChooserContentPane);

				setColorDialog.setSize(new Dimension(300, 150));
				setColorDialog.setLocationRelativeTo(frame);
				setColorDialog.setVisible(true);
				return compDescriptor;
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
