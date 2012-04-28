package my;

import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ServSettingsPanel extends JPanel implements ActionListener {
	protected static final String nameString = "nameTextField";
	protected static final String selectedNewVar = "newPlayVariant";
	protected static final String setPortNumber = "Set port number";
	protected static final String defaultName = "Player";

	JPanel infoPanel;
	String playerName;
	int portNumber = 0;
	int colorDescriptor = 0;
	JTextField inputPort;
	boolean portNumberIsCorrect = false;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServSettingsPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		playerName = null;
		inputPort = new JTextField("42");

		String[] playStrings = { "Black", "White" };

		JComboBox playVars = new JComboBox(playStrings);
		playVars.setSelectedIndex(0);

		playVars.setActionCommand(selectedNewVar);
		playVars.addActionListener(this);

		JLabel headForName = new JLabel();
		headForName.setText("Input your name, please, and press Enter: ");
		JTextField name = new JTextField(10);
		headForName.setLabelFor(name);
		name.setActionCommand(nameString);
		name.addActionListener(this);

		JButton setPort = new JButton("Set port number");
		setPort.setActionCommand(setPortNumber);
		setPort.addActionListener(this);

		add(Box.createVerticalStrut(5));
		add(headForName);
		add(name);
		add(Box.createVerticalStrut(5));
		add(playVars);
		add(Box.createVerticalStrut(5));
		add(inputPort);
		add(setPort);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (nameString.equals(e.getActionCommand())) {
			JTextField source = (JTextField) e.getSource();
			playerName = source.getText();
			if ((playerName == null)|(playerName.isEmpty())) playerName = defaultName;
		} 
		else if (selectedNewVar.equals(e.getActionCommand())) {
			JComboBox cb = (JComboBox) e.getSource();
			String color = (String) cb.getSelectedItem();
			setColor(color);
		} 
		else if (setPortNumber.equals(e.getActionCommand())) {
			try {
				portNumber = Integer.parseInt(inputPort.getText());
			} catch (NumberFormatException å) {
				JOptionPane.showMessageDialog(infoPanel,
						"Port number is invalid.", "Error",
						JOptionPane.INFORMATION_MESSAGE, null);
			}
			if ((portNumber != 0) && (!inputPort.getText().isEmpty())) {
				portNumberIsCorrect = true;
			}
		}
	}

	private void setColor(String color) {
		if (color == "Black")
			colorDescriptor = 0;
		else if (color == "White")
			colorDescriptor = 1;
	}
}
