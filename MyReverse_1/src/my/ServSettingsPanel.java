package my;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ServSettingsPanel extends JPanel {
	protected static final String nameString = "nameTextField";

	JPanel infoPanel;
	String playerName, hostName;
	int portNumber = 0;
	JTextField inputHostName, inputPort;
	boolean hostNameIsCorrect = false;
	boolean portNumberIsCorrect = false;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServSettingsPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

		playerName = null;
		inputHostName = new JTextField();
		inputPort = new JTextField();

		JButton selectButton = new JButton("Set host name");
		selectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hostName = inputHostName.getText();
				if ((hostName != null) && (!hostName.isEmpty())) {
					hostNameIsCorrect = true;
				}

				if (hostName.isEmpty())
					JOptionPane.showMessageDialog(infoPanel,
							"You didn't input the name of server!", "Error",
							JOptionPane.INFORMATION_MESSAGE, null);
			}
		});

		JButton setPort = new JButton("Set port number");
		setPort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				portNumber = Integer.parseInt(inputPort.getText());
				if ((portNumber != 0) && (!inputPort.getText().isEmpty())) {
					portNumberIsCorrect = true;
				}

				else
					JOptionPane.showMessageDialog(infoPanel,
							"Port number is invalid.", "Error",
							JOptionPane.INFORMATION_MESSAGE, null);
			}
		});
		add(Box.createVerticalStrut(5));
		add(inputHostName);
		add(Box.createVerticalStrut(5));
		add(selectButton);
		add(Box.createVerticalStrut(5));
		add(inputPort);
		add(setPort);
	}
}
