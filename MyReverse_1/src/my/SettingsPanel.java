package my;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SettingsPanel extends JPanel implements ActionListener {	
	JPanel infoPanel;
	String playerName;
	JLabel headForName, confirmInput;
	String command;
	boolean server, client;
	boolean hostNameIsCorrect = false;
	protected static final String nameString = "nameTextField";
	protected static final String selectedNewVar = "newPlayVariant";	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SettingsPanel() {
		super(new BorderLayout());
		
		String[] playStrings = { "Human", "Computer"};

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
		add(infoPanel, BorderLayout.CENTER);
		setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
		setOpaque(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (nameString.equals(e.getActionCommand())) {
            JTextField source = (JTextField)e.getSource();
            playerName = source.getText();
            confirmInput.setText("Player name: " + playerName);
        } else if (selectedNewVar.equals(e.getActionCommand())){
        	JComboBox cb = (JComboBox)e.getSource();
            String playVar = (String)cb.getSelectedItem();
            command = playVar;
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
			headForName = new JLabel();
			confirmInput = new JLabel();
			headForName.setMinimumSize(new Dimension(300, 100));
			headForName.setMaximumSize(new Dimension(300, 100));
			headForName.setText("Input your name, please, and press Enter: ");
			JTextField name = new JTextField(10);
			name.setMaximumSize(new Dimension(250, 20));
			headForName.setLabelFor(name);
			name.setActionCommand(nameString);
			name.addActionListener(this);

			newSettingsPanel.add(headForName);
			newSettingsPanel.add(name);			
			newSettingsPanel.add(confirmInput);
		}
		
		if (selectedVariant == "Computer"){	
			newSettingsPanel = null;
			playerName = "Computer";
		}		
		
		infoPanel = newSettingsPanel;
		repaint();
	}
	
	public String getCommand(){
		return command;
	}

	public void setServClient(boolean s, boolean c) {
		s = server;
		c = client;		
	}

}
