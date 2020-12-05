package MVCModel.Realizations;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
// import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import MVCModel.Controllers.IMainMenuViewController;
import MVCModel.Views.IMainMenuView;
import Misc.BackgroundPanel;

public class MainMenuView extends AbstractView<IMainMenuViewController> implements IMainMenuView {

	private static final long serialVersionUID = 1L;
	
	/* ****************************** */
	// #region Swing Components
	
	BackgroundPanel backgroundPanel;
	JPanel region_1, region_2, region_3;
	JPanel offset_1, offset_2;
	JButton signinButton, signupButton, exitButton;
	
	// #endregion

	/* ****************************** */
	// #region Construct Layout Process
	public MainMenuView(IMainMenuViewController viewController) throws IOException {
		super(viewController);
		setLayout(new BorderLayout());

		// Get background image
		this.backgroundPanel = 
				new BackgroundPanel(
						ImageIO.read(new File("Resources/Images/Background.jpg")),
						BackgroundPanel.SCALED);

		this.backgroundPanel.setLayout(new GridLayout(0, 3));
		this.backgroundPanel.setTransparentAdd(false);
		
		// Seperated panels
		region_1 = new JPanel();
		
		region_1.setBackground(new Color(0, 0, 0, 150));
		
		this.backgroundPanel.add(region_1);
		
		region_2 = new JPanel();		
		
		region_2.setBackground(new Color(0, 0, 0, 0));
		
		this.backgroundPanel.add(region_2);

		region_3 = new JPanel();
		
		region_3.setBackground(new Color(0, 0, 0, 0));
		
		this.backgroundPanel.add(region_3);

		// First region
		region_1.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		// Default constraints
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 1;

		// Add offset
		offset_1 = new JPanel();
		offset_1.setOpaque(false);
		
		// offset_1.setBorder(BorderFactory.createLineBorder(Color.black));
		
		// Constraints
		constraints.gridx = 0;
		constraints.gridy = 0;
		
		constraints.ipady = 200;
		constraints.gridheight = 2;
		
		region_1.add(offset_1, constraints);
		
		// Reset constraints
		constraints.ipady = 0;
		constraints.gridheight = 1;

		// Add signin button
		signinButton = new JButton("Signin");
		
		signinButton.setBackground(new Color(0, 0, 0, 0));
		
		// Constraints
		constraints.gridx = 0;
		constraints.gridy = 2;
		
		constraints.insets = new Insets(10, 20, 10, 20);
				
		region_1.add(signinButton, constraints);
		
		// Events
		signinButton.addActionListener((ActionEvent event) -> {
			getViewController().switchToSignIn();
		});

		// Reset constraints
		constraints.insets = new Insets(0, 0, 0, 0);
				
		// Add signup button
		signupButton = new JButton("Signup");
		
		signupButton.setBackground(new Color(0, 0, 0, 0));
		
		// Constraints
		constraints.gridx = 0;
		constraints.gridy = 3;
		
		constraints.insets = new Insets(10, 20, 10, 20);
				
		region_1.add(signupButton, constraints);
		
		// Events
		signupButton.addActionListener((ActionEvent event) -> {
			getViewController().switchToSignUp();
		});
		
		// Reset constraints
		constraints.insets = new Insets(0, 0, 0, 0);
		
		// Add exit button
		exitButton = new JButton("Exit");
		
		exitButton.setBackground(new Color(0, 0, 0, 0));
		
		// Constraints
		constraints.gridx = 0;
		constraints.gridy = 4;
		
		constraints.insets = new Insets(10, 20, 10, 20);
				
		region_1.add(exitButton, constraints);
		
		// Events
		exitButton.addActionListener((ActionEvent event) -> {
			getViewController().exitProgram();
		});
		
		// Reset constraints
		constraints.insets = new Insets(0, 0, 0, 0);

		// Add offset
		offset_2 = new JPanel();
		offset_2.setOpaque(false);
		
		// offset_2.setBorder(BorderFactory.createLineBorder(Color.black));
		
		// Constraints
		constraints.gridx = 0;
		constraints.gridy = 5;
				
		region_1.add(offset_2, constraints);

		// Reset constraints
		
		// Set background image lastly - Add background panel to main panel
		add(this.backgroundPanel);
	}
	
	// #endregion
}
