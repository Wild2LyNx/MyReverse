package my;

import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientSettingsPanel extends JPanel implements ActionListener{
	protected static final String nameString = "nameTextField";
	protected static final String setHostName = "Set host name";
	protected static final String setPortNumber = "Set port number";
	protected static final String defaultName = "Player";

	JPanel infoPanel;
	JLabel confirmInput = new JLabel();
	String playerName, hostName;
	int portNumber = 0;
	JTextField inputHostName, inputPort;
	boolean hostNameIsCorrect = false;
	boolean portNumberIsCorrect = false;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClientSettingsPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		playerName = null;
		inputHostName = new JTextField("localhost");
		inputPort = new JTextField("42");

		JLabel headForName = new JLabel();
		headForName.setText("Input your name, please, and press Enter: ");
		JTextField name = new JTextField(10);
		headForName.setLabelFor(name);
		name.setActionCommand(nameString);
		name.addActionListener(this);

		JButton selectButton = new JButton("Set host name");
		selectButton.setActionCommand(setHostName);
		selectButton.addActionListener(this);

		JButton setPort = new JButton("Set port number");
		setPort.setActionCommand(setPortNumber);
		setPort.addActionListener(this);

		add(Box.createVerticalStrut(5));
		add(headForName);
		add(name);
		add(Box.createVerticalStrut(5));
		add(inputHostName);
		add(Box.createVerticalStrut(5));
		add(selectButton);
		add(Box.createVerticalStrut(5));
		add(inputPort);
		add(setPort);
		add(Box.createVerticalStrut(5));
		add(confirmInput);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (nameString.equals(e.getActionCommand())) {
			JTextField source = (JTextField) e.getSource();
			playerName = source.getText();
			if ((playerName == null)|(playerName.isEmpty())) playerName = defaultName;
			confirmInput.setText("Player name: " + playerName);
		} 
		else if (setHostName.equals(e.getActionCommand())) {
			hostName = inputHostName.getText();
			if ((hostName != null) && (!hostName.isEmpty())) {
				hostNameIsCorrect = true;
			}

			if (hostName.isEmpty())
				JOptionPane.showMessageDialog(infoPanel,
						"You didn't input the name of server!", "Error",
						JOptionPane.INFORMATION_MESSAGE, null);
		} 
		else if (setPortNumber.equals(e.getActionCommand())) {
			try {
				portNumber = Integer.parseInt(inputPort.getText());
			} catch (NumberFormatException �) {
				JOptionPane.showMessageDialog(infoPanel,
						"Port number is invalid.", "Error",
						JOptionPane.INFORMATION_MESSAGE, null);
			}
			if ((portNumber != 0) && (!inputPort.getText().isEmpty())) {
				portNumberIsCorrect = true;
			}
		}
	}
}
