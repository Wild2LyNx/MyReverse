package my;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
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

public class PlayersManager {
	JFrame frame;
	DataPanel dataPanel;
	Player player1, player2;
	SettingsPanel player1Panel, player2Panel;
	String kindOfPlayer1, kindOfPlayer2;
	private static final String defPlayer1Name = new String("Player1 (Black)");
	private static final String defPlayer2Name = new String("Player2 (White)");

	String networkHostName;
	boolean server = false;
	boolean client = false;

	public PlayersManager(JFrame fr, DataPanel dp) {
		frame = fr;
		this.dataPanel = dp;
	}

	public void setPlayConfig() {
		final JDialog choiseDialog = new JDialog(frame, "Alternative choice",
				true);
		choiseDialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ArrayList<JRadioButton> variantButtons = new ArrayList<JRadioButton>();
		final ButtonGroup group = new ButtonGroup();
		JPanel selectButtonsPanel = new JPanel();
		selectButtonsPanel.setLayout(new BoxLayout(selectButtonsPanel,
				BoxLayout.Y_AXIS));

		variantButtons.add(new JRadioButton("New game"));
		variantButtons.add(new JRadioButton("Connect to network game"));
		variantButtons.add(new JRadioButton("Create network game"));

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
				if (command == "New game") {
					final JDialog settingDialog = createSettingsDialog();
					settingDialog.setVisible(true);
				}
				if (command == "Connect to network game") {
					final JDialog settingDialog = createConnectDialog();
					settingDialog.setVisible(true);
				}
				if (command == "Create network game") {
					final JDialog settingDialog = createServDialog();
					settingDialog.setVisible(true);
				}
				choiseDialog.setVisible(false);
				choiseDialog.dispose();
			}

			private JDialog createServDialog() {
				final JDialog settingDialog = new JDialog(frame,
						"Game settings", true);
				settingDialog
						.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				JPanel settingsPanel = new JPanel();
				final ServSettingsPanel servSettings = new ServSettingsPanel();

				JButton okButton = new JButton("Ok");

				settingsPanel.add(servSettings, BorderLayout.CENTER);
				settingsPanel.add(okButton, BorderLayout.AFTER_LAST_LINE);

				settingDialog.setContentPane(settingsPanel);
				settingDialog.pack();

				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (servSettings.portNumberIsCorrect) {
							process(servSettings);
							settingDialog.setVisible(false);
							settingDialog.dispose();
						} else {
							JOptionPane.showMessageDialog(settingDialog,
									"Some data is incorrect or isn't inputed.", "Error",
									JOptionPane.INFORMATION_MESSAGE, null);
						}
					}

					private void process(ServSettingsPanel servSettings) {	
						int descriptor = servSettings.colorDescriptor;
						if (descriptor == 0){
							String playerName = null;
							playerName = servSettings.playerName;
							dataPanel.setPlayerName(playerName, descriptor + 1);
							System.out.println("Player" + descriptor + 1 + " name: "
									+ playerName);
							
							player1 = new Human();
							player2 = new Server(servSettings.portNumber);
						} else if (descriptor == 1){
							String playerName = null;
							playerName = servSettings.playerName;
							dataPanel.setPlayerName(playerName, descriptor + 1);
							System.out.println("Player" + descriptor + 1 + " name: "
									+ playerName);
							
							player1 = new Server(servSettings.portNumber);
							player2 = new Human();
						}						

					}

				});

				settingDialog.setSize(new Dimension(400, 250));
				settingDialog.setLocationRelativeTo(frame);
				return settingDialog;
			}

			private JDialog createConnectDialog() {
				// TODO Auto-generated method stub
				return null;
			}

			private JDialog createSettingsDialog() {
				final JDialog settingDialog = new JDialog(frame,
						"Game settings", true);
				settingDialog
						.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				JPanel settingsPanel = new JPanel();
				initSettingsPanel(settingsPanel);
				JButton okButton = new JButton("Ok");

				settingsPanel.add(okButton, BorderLayout.AFTER_LAST_LINE);
				okButton.setSize(new Dimension(20, 10));
				settingsPanel.repaint();

				settingDialog.setContentPane(settingsPanel);
				settingDialog.pack();

				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						kindOfPlayer1 = player1Panel.getCommand();
						player1 = handle(kindOfPlayer1, 1);
						kindOfPlayer2 = player2Panel.getCommand();
						player2 = handle(kindOfPlayer2, 2);
						settingDialog.setVisible(false);
						settingDialog.dispose();
					}

					private Player handle(String kindOfPlayer, int plNumber) {
						if (kindOfPlayer == "Human") {
							String playerName = null;
							if (plNumber == 1)
								playerName = player1Panel.playerName;
							else if (plNumber == 2)
								playerName = player2Panel.playerName;
							dataPanel.setPlayerName(playerName, plNumber);
							System.out.println("Player" + plNumber + " name: "
									+ playerName);

							return (new Human());
						}

						if (kindOfPlayer == "Computer") {
							dataPanel.setNameForComp(plNumber - 1);
							return (new ComputerPlayer());
						}
						return null;
					}
				});
				settingDialog.setSize(new Dimension(700, 300));
				settingDialog.setLocationRelativeTo(frame);
				return settingDialog;
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

	private void initSettingsPanel(JPanel settingsPanel) {
		settingsPanel.setLayout(new BorderLayout());
		player1Panel = new SettingsPanel();
		player2Panel = new SettingsPanel();
		player1Panel
				.setBorder(BorderFactory.createTitledBorder(defPlayer1Name));
		player2Panel
				.setBorder(BorderFactory.createTitledBorder(defPlayer2Name));

		player1Panel.setSize(new Dimension(200, 100));
		player2Panel.setSize(new Dimension(200, 100));
		settingsPanel.add(player1Panel, BorderLayout.WEST);
		settingsPanel.add(player2Panel, BorderLayout.EAST);
		settingsPanel.setOpaque(true);
	}

	public void addListeners(GameField field) {
		if (kindOfPlayer1 == "Human")
			field.addMouseListener((MouseListener) player1);
		if (kindOfPlayer2 == "Human")
			field.addMouseListener((MouseListener) player2);
	}
}
