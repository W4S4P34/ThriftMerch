package MVCModel.Realizations;

import java.awt.BorderLayout;
// import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import MVCModel.Controllers.ILoginViewController;
import MVCModel.Views.ILoginView;
import Misc.ActorType;

public class LoginView extends AbstractView<ILoginViewController> implements ILoginView {

	private static final long serialVersionUID = 1L;
	
	/* ****************************** */
	// #region Swing Components
	
	private JLabel title;
	
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	
	private JTextField username;
    private JPasswordField password;

    private JButton signinButton;
    private JButton backButton;
	
	// #endregion

	/* ****************************** */
	// #region Construct Layout Process
	public LoginView(ILoginViewController viewController) {
		super(viewController);
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(150, 120, 150, 120));
		
		// Main panel at center
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		// mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		// Default constraints
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 1;
		
		// Add title
		title = new JLabel("SIGNIN", JLabel.CENTER);
		
		// Constraints
		constraints.gridx = 0;
		constraints.gridy = 0;
		
		constraints.gridwidth = 4;
		
		mainPanel.add(title, constraints);
		
		// Reset Constraints
		constraints.gridwidth = 1;
		
		// Add username label
		usernameLabel = new JLabel("Username:", JLabel.RIGHT);
		
		// Constraints
		constraints.gridx = 0;
		constraints.gridy = 1;
				
		mainPanel.add(usernameLabel, constraints);
		
		// Reset Constraints
		
		// Add username textfield
		username = new JTextField(30);
		
		// Constraints
		constraints.gridx = 1;
		constraints.gridy = 1;

		constraints.fill = GridBagConstraints.NONE;
		constraints.gridwidth = 3;
		
		mainPanel.add(username, constraints);
		
		// Reset Constraints
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridwidth = 1;
		
		// Add password label
		passwordLabel = new JLabel("Password:", JLabel.RIGHT);
		
		// Constraints
		constraints.gridx = 0;
		constraints.gridy = 2;
				
		mainPanel.add(passwordLabel, constraints);
		
		// Reset Constraints
		
		// Add username textfield
		password = new JPasswordField(30);
				
		// Constraints
		constraints.gridx = 1;
		constraints.gridy = 2;
		
		constraints.gridwidth = 2;

		constraints.fill = GridBagConstraints.NONE;
		
		mainPanel.add(password, constraints);
		
		// Reset Constraints
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridwidth = 1;
		
		// Add radio buttons -> Actors
		JPanel radioButtonPanel = new JPanel();
		radioButtonPanel.setLayout(new GridLayout(0, 3));
		
		// Shop button
		JPanel shopPanel = new JPanel();
		
		JRadioButton shopRadioButton = new JRadioButton();
		shopRadioButton.setText("Shop");
		
		shopPanel.add(shopRadioButton);

		// Customer button
		JPanel customerPanel = new JPanel();
		
		JRadioButton customerRadioButton = new JRadioButton();
		customerRadioButton.setText("Customer");
		
		customerPanel.add(customerRadioButton);
		
		// Shipper button
		JPanel shipperPanel = new JPanel();
		
		JRadioButton shipperRadioButton = new JRadioButton();
		shipperRadioButton.setText("Shipper");
		
		shipperPanel.add(shipperRadioButton);
		
		ButtonGroup actorButtonGroup = new ButtonGroup();
		
		actorButtonGroup.add(shopRadioButton);
		actorButtonGroup.add(customerRadioButton);
		actorButtonGroup.add(shipperRadioButton);
		
		// Add radio buttons panel to panel	
		radioButtonPanel.add(shopPanel);
		radioButtonPanel.add(customerPanel);
		radioButtonPanel.add(shipperPanel);
		
		// Constraints
		constraints.gridx = 0;
		constraints.gridy = 3;
		
		constraints.gridwidth = 4;
		
		mainPanel.add(radioButtonPanel, constraints);

		// Reset constraints
		constraints.gridwidth = 1;

		// Add buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0, 2, 10, 0));
		
		// Add signin button
		signinButton = new JButton("Signin");
		
		buttonPanel.add(signinButton);
		
		// Events
		signinButton.addActionListener((ActionEvent event) -> {
			// Pass to another controller to handle -> Receive the result from it
			// Actor actor;
			String username = this.username.getText(), 
				   password = new String(this.password.getPassword());
			
			if (shopRadioButton.isSelected()) {
				// actor = new Shop().SignIn(username, password);
			}
			else if (customerRadioButton.isSelected()) {
				// actor = new Customer().SignIn(username, password);
			}
			else if (shipperRadioButton.isSelected()) {
				// actor = new Shipper().SignIn(username, password);
			}			
			
			System.out.println(username + " " + password);
			
			boolean isValid = false;
			
			// TODO
			if (isValid) {
				getViewController().signinSuccessful();
			}
			else {
				getViewController().signinFailed();
			}
			
		});
		
		// Add back button
		backButton = new JButton("Back");
		
		buttonPanel.add(backButton);
		
		// Events
		backButton.addActionListener((ActionEvent event) -> {
			getViewController().backToMainMenu();
		});
						
		// Constraints
		constraints.gridx = 0;
		constraints.gridy = 4;

		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.insets = new Insets(0, 10, 0, 10);
		
		mainPanel.add(buttonPanel, constraints);
		
		// Reset Constraints
		constraints.gridwidth = 1;
		constraints.insets = new Insets(0, 0, 0, 0);
				
		// Add components to current view
		add(mainPanel, BorderLayout.CENTER);	
	}
	
	// #endregion

}
