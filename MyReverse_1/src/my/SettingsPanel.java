package my;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SettingsPanel extends JPanel implements ActionListener {	
	JPanel infoPanel;
	String playerName, hostName;
	JTextField inputHostName;
	String command;
	boolean server, client;
	boolean hostNameIsCorrect = false;
	protected static final String nameString = "nameTextField";
	protected static final String selectedNewVar = "newPlayVariant";
	final String serverCommand = new String(
			"Initiator (Your comp will be server)");
	final String clientCommand = new String(
			"Invitee (Your comp will be client on the server, mentioned above)");

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SettingsPanel() {
		super(new BorderLayout());
		
		String[] playStrings = { "Human", "Computer", "Network partner" };

		// Create the combo box, select the item at index 0.
		// Indices start at 0, so 0 specifies the "Human".
		JComboBox playVars = new JComboBox(playStrings);
		playVars.setSelectedIndex(0);
		
		playVars.setActionCommand(selectedNewVar);
		playVars.addActionListener(this);
		
		infoPanel = new JPanel();
		updateSettingsPanel(playStrings[playVars.getSelectedIndex()]);
		
		add(playVars, BorderLayout.PAGE_START);
		playVars.setBounds(new Rectangle(20, 5));
		add(infoPanel, BorderLayout.PAGE_END);
		setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
		setOpaque(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (nameString.equals(e.getActionCommand())) {
            JTextField source = (JTextField)e.getSource();
            playerName = source.getText();
        } else if (selectedNewVar.equals(e.getActionCommand())){
        	JComboBox cb = (JComboBox)e.getSource();
            String playVar = (String)cb.getSelectedItem();
            updateSettingsPanel(playVar);
        }
	}

	private void updateSettingsPanel(String selectedVariant) {
		command = selectedVariant;
		playerName = null;
		System.out.println(selectedVariant);
		
		JPanel newSettingsPanel = new JPanel();
		newSettingsPanel.setLayout(new BoxLayout(newSettingsPanel, BoxLayout.Y_AXIS));
		newSettingsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		
		if (selectedVariant == "Human"){			
			JLabel headForName = new JLabel();
			headForName.setText("Input your name, please, and press Enter: ");
			JTextField name = new JTextField(10);
			headForName.setLabelFor(name);
			name.setActionCommand(nameString);
			name.addActionListener(this);

			newSettingsPanel.add(headForName);
			newSettingsPanel.add(name);			
		}
		
		if (selectedVariant == "Computer"){	
			newSettingsPanel = null;
			playerName = "Computer";
		}
		
		if (selectedVariant == "Network partner"){
			playerName = null;
						
			final ButtonGroup bgroup = new ButtonGroup();
			JPanel selectButtonsPanel = new JPanel();
			initSelectButtons(selectButtonsPanel, bgroup);
			
			inputHostName = new JTextField();

			JButton selectButton = new JButton("Set host name");
			selectButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					hostName = inputHostName.getText();

					String command = bgroup.getSelection()
							.getActionCommand();
					if (command == serverCommand) {
						server = true;
						client = false;
					}

					if (command == clientCommand) {
						server = false;
						client = true;
					}

					if ((hostName != null)
							&& (!hostName.isEmpty())|(server)) {
						hostNameIsCorrect = true;
					}

					if (hostName.isEmpty()&&(client))
						JOptionPane.showMessageDialog(infoPanel,
								"You didn't input the name of server!",
								"Error", JOptionPane.INFORMATION_MESSAGE,
								null);

				}
			});			
			newSettingsPanel.add(selectButtonsPanel);
			newSettingsPanel.add(Box.createVerticalStrut(5));
			newSettingsPanel.add(inputHostName);
			newSettingsPanel.add(Box.createVerticalStrut(5));
			newSettingsPanel.add(selectButton);			
		}
		
		infoPanel = newSettingsPanel;

	}

	private void initSelectButtons(JPanel selectButtonsPanel, ButtonGroup bgroup) {
		ArrayList<JRadioButton> variantButtons = new ArrayList<JRadioButton>();
		selectButtonsPanel.setLayout(new BoxLayout(selectButtonsPanel,
				BoxLayout.Y_AXIS));		

		variantButtons.add(new JRadioButton(serverCommand));
		variantButtons.add(new JRadioButton(clientCommand));

		for (int i = 0; i < variantButtons.size(); i++) {
			JRadioButton curButton = variantButtons.get(i);
			curButton.setActionCommand(curButton.getText());
			bgroup.add(curButton);
			selectButtonsPanel.add(curButton);
			selectButtonsPanel.add(Box.createVerticalStrut(5));
			curButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		}

		variantButtons.get(0).setSelected(true);		
	}
	
	public String getCommand(){
		return command;
	}

	public void setServClient(boolean s, boolean c) {
		s = server;
		c = client;		
	}

}
