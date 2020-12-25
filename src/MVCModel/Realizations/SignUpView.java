package MVCModel.Realizations;

import java.awt.BorderLayout;
// import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Actor.*;
import MVCModel.Controllers.ISignUpViewController;
import MVCModel.Views.ISignUpView;

public class SignUpView extends AbstractView<ISignUpViewController> implements ISignUpView {
	
	private static final long serialVersionUID = 1L;
	
	/* ****************************** */
	// #region Swing Components
	
	private JLabel title;
	
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JLabel nameLabel;
	private JLabel phoneLabel;
	
	private JTextField username;
    private JPasswordField password;
    private JTextField name;
    private JTextField phone;

    private JButton registerButton;
    private JButton backButton;
	
	// #endregion

	/* ****************************** */
	// #region Construct Layout Process
	public SignUpView(ISignUpViewController viewController) {
		super(viewController);
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(140, 120, 140, 120));
		
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
		title = new JLabel("SIGNUP", JLabel.CENTER);
		
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
		constraints.insets = new Insets(1, 3, 1, 3);
		
		mainPanel.add(username, constraints);
		
		// Reset Constraints
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(0, 0, 0, 0);
		
		// Add password label
		passwordLabel = new JLabel("Password:", JLabel.RIGHT);
		
		// Constraints
		constraints.gridx = 0;
		constraints.gridy = 2;
				
		mainPanel.add(passwordLabel, constraints);
		
		// Reset Constraints
		
		// Add password textfield
		password = new JPasswordField(30);
				
		// Constraints
		constraints.gridx = 1;
		constraints.gridy = 2;
		
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridwidth = 3;
		constraints.insets = new Insets(1, 3, 1, 3);
		
		mainPanel.add(password, constraints);
		
		// Reset Constraints
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(0, 0, 0, 0);
		
		// Add name label
		nameLabel = new JLabel("Name:", JLabel.RIGHT);
		
		// Constraints
		constraints.gridx = 0;
		constraints.gridy = 3;
				
		mainPanel.add(nameLabel, constraints);
		
		// Reset Constraints
		
		// Add name textfield
		name = new JTextField(30);
				
		// Constraints
		constraints.gridx = 1;
		constraints.gridy = 3;
		
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridwidth = 3;
		constraints.insets = new Insets(1, 3, 1, 3);
		
		mainPanel.add(name, constraints);
		
		// Reset Constraints
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(0, 0, 0, 0);
				
		// Add phone label
		phoneLabel = new JLabel("Phone:", JLabel.RIGHT);
		
		// Constraints
		constraints.gridx = 0;
		constraints.gridy = 4;
				
		mainPanel.add(phoneLabel, constraints);
		
		// Reset Constraints
		
		// Add phone textfield
		phone = new JTextField(30);
				
		// Constraints
		constraints.gridx = 1;
		constraints.gridy = 4;
		
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridwidth = 3;
		constraints.insets = new Insets(1, 3, 1, 3);
		
		mainPanel.add(phone, constraints);
		
		// Reset Constraints
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(0, 0, 0, 0);

		// Add buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0, 2, 10, 0));
		
		// Add signin button
		registerButton = new JButton("Register");
		
		buttonPanel.add(registerButton);
		
		// Events
		registerButton.addActionListener((ActionEvent event) -> {
			// Pass to another controller to handle -> Receive the result from it

			String username = this.username.getText();
			String password = new String(this.password.getPassword());
			String name = this.name.getText();
			String phone = this.phone.getText();
			Actor actor = new Customer().SignUp(username,password,name,phone,"Address",getViewController()::signupFailed);
			if(actor != null){
				getViewController().signupSuccessful(actor);
			}
		});
		
		// Add back button
		backButton = new JButton("Back");
		
		buttonPanel.add(backButton);
		
		// Events
		backButton.addActionListener((ActionEvent event) -> {
			getViewController().switchToMainMenu();
		});
						
		// Constraints
		constraints.gridx = 0;
		constraints.gridy = 5;

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
