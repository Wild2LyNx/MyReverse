package my;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;


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
		final JDialog settingDialog = new JDialog(frame, "Game settings", true);
		settingDialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
				if (kindOfPlayer == "Human"){
					String playerName = null;
					if (plNumber == 1) playerName = player1Panel.playerName;
					else if (plNumber == 2) playerName = player2Panel.playerName;
					dataPanel.setPlayerName(playerName, plNumber);
					System.out.println("Player" + plNumber + " name: " + playerName );
					
					return (new Human());
				}
				
				if (kindOfPlayer == "Computer"){
					dataPanel.setNameForComp(plNumber - 1);
					return (new ComputerPlayer());
				}
				
				if (kindOfPlayer == "Network partner"){		
					String hostName = null;
					if (plNumber == 1) {
						hostName = player1Panel.hostName;	
						player1Panel.setServClient(server, client);
					}
					if (plNumber == 2) {
						hostName = player2Panel.hostName;
						player2Panel.setServClient(server, client);
					}
					return (new MyProtocolHandler(hostName, server, client));
				}
				
				return null;
			}
		});
		settingDialog.setSize(new Dimension(700, 300));
		settingDialog.setLocationRelativeTo(frame);
		settingDialog.setVisible(true);
	}

	private void initSettingsPanel(JPanel settingsPanel) {
		settingsPanel.setLayout(new BorderLayout());
		player1Panel = new SettingsPanel();
		player2Panel = new SettingsPanel();
		player1Panel.setBorder(BorderFactory.createTitledBorder(defPlayer1Name));
		player2Panel.setBorder(BorderFactory.createTitledBorder(defPlayer2Name));
		settingsPanel.add(player1Panel, BorderLayout.WEST);
		settingsPanel.add(player2Panel, BorderLayout.EAST);
		player1Panel.setSize(new Dimension(200, 100));
		player2Panel.setSize(new Dimension(200, 100));
		settingsPanel.setOpaque(true);
	}

	public void addListeners(GameField field) {
		if (kindOfPlayer1 == "Human") field.addMouseListener((MouseListener) player1);
		if (kindOfPlayer2 == "Human") field.addMouseListener((MouseListener) player2);
	}
}
