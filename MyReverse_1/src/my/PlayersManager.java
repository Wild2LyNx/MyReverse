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
	ArrayList<Player> listeners = new ArrayList<Player>();
	ArrayList<Human> humans = new ArrayList<Human>();
	private static final String defPlayer1Name = new String("Player1 (Black)");
	private static final String defPlayer2Name = new String("Player2 (White)");

	String networkHostName;

	public PlayersManager(JFrame fr, DataPanel dp) {
		frame = fr;
		this.dataPanel = dp;
	}

	public void setPlayConfig() {
		listeners.clear();
		humans.clear();
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
									"Some data is incorrect or isn't inputed.",
									"Error", JOptionPane.INFORMATION_MESSAGE,
									null);
						}
					}

					private void process(ServSettingsPanel servSettings) {
						int descriptor = servSettings.colorDescriptor;
						if (descriptor == 0) {
							String playerName = servSettings.playerName;
							dataPanel.setPlayerName(playerName, descriptor + 1);
							System.out.println("Player" + descriptor + 1
									+ " name: " + playerName);

							player1 = new Human();
							humans.add((Human) player1);
							player2 = new Server(servSettings.portNumber,
									descriptor, playerName);

							listeners.add(player2);
						} else if (descriptor == 1) {
							String playerName = servSettings.playerName;
							dataPanel.setPlayerName(playerName, descriptor + 1);
							System.out.println("Player" + descriptor + 1
									+ " name: " + playerName);

							player1 = new Server(servSettings.portNumber,
									descriptor, playerName);
							listeners.add(player1);
							player2 = new Human();
							humans.add((Human) player2);
						}

					}

				});

				settingDialog.setSize(new Dimension(400, 250));
				settingDialog.setLocationRelativeTo(frame);
				return settingDialog;
			}

			private JDialog createConnectDialog() {
				final JDialog settingDialog = new JDialog(frame,
						"Game settings", true);
				settingDialog
						.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				JPanel settingsPanel = new JPanel();
				final ClientSettingsPanel clientSettings = new ClientSettingsPanel();

				JButton okButton = new JButton("Ok");

				settingsPanel.add(clientSettings, BorderLayout.CENTER);
				settingsPanel.add(okButton, BorderLayout.AFTER_LAST_LINE);

				settingDialog.setContentPane(settingsPanel);
				settingDialog.pack();

				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if ((clientSettings.portNumberIsCorrect)
								| (clientSettings.hostNameIsCorrect)) {
							process(clientSettings);
							settingDialog.setVisible(false);
							settingDialog.dispose();
						} else {
							JOptionPane.showMessageDialog(settingDialog,
									"Some data is incorrect or isn't inputed.",
									"Error", JOptionPane.INFORMATION_MESSAGE,
									null);
						}
					}

					private void process(ClientSettingsPanel clientSettings) {
						String playerName = clientSettings.playerName;
						Client client = new Client(clientSettings.portNumber,
								clientSettings.hostName, playerName);
						int descriptor = client.getColor();
						if (descriptor == 0) {
							dataPanel.setPlayerName(playerName,
									(descriptor + 1));
							System.out.println("Player" + (descriptor + 1)
									+ " name: " + playerName);

							player1 = new ComputerPlayer();
//							humans.add((Human) player1);
							player2 = client;
							System.out.println("Player" + (descriptor + 1)
									+ ": " + player2);

							listeners.add(player2);
						} else if (descriptor == 1) {
							dataPanel.setPlayerName(playerName,
									(descriptor + 1));
							System.out.println("Player" + (descriptor + 1)
									+ " name: " + playerName);

							player1 = client;
							listeners.add(player1);
							player2 = new ComputerPlayer();
//							humans.add((Human) player2);
						}

					}

				});

				settingDialog.setSize(new Dimension(400, 250));
				settingDialog.setLocationRelativeTo(frame);
				return settingDialog;
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
						String playerName = null;
						if (player1Panel.getCommand() == "Human") {
							player1 = new Human();
							humans.add((Human) player1);
							playerName = player1Panel.playerName;
							dataPanel.setPlayerName(playerName, 1);
							System.out.println("Player" + 1 + " name: "
									+ playerName);
						} else if (player1Panel.getCommand() == "Computer") {
							player1 = new ComputerPlayer();
							dataPanel.setNameForComp(0);
						}

						if (player2Panel.getCommand() == "Human") {
							player2 = new Human();
							humans.add((Human) player2);
							playerName = player2Panel.playerName;
							dataPanel.setPlayerName(playerName, 2);
							System.out.println("Player" + 1 + " name: "
									+ playerName);
						} else if (player2Panel.getCommand() == "Computer") {
							player2 = new ComputerPlayer();
							dataPanel.setNameForComp(1);
						}

						settingDialog.setVisible(false);
						settingDialog.dispose();
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
		for (int i = 0; i < humans.size(); i++) {
			field.addMouseListener((MouseListener) humans.get(i));
		}
		field.setListeners(listeners);
	}
}
