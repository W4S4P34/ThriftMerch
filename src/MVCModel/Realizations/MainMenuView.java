package MVCModel.Realizations;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
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
	
	// #endregion

	/* ****************************** */
	// #region Construct Layout Process
	public MainMenuView(IMainMenuViewController viewController) throws IOException {
		super(viewController);
		setLayout(new BorderLayout());

		// Get background image
		this.backgroundPanel = 
				new BackgroundPanel(
						ImageIO.read(new File("imgs/Background.jpg")),
						BackgroundPanel.SCALED);

		// Add test button
		JPanel controls = new JPanel();
		JButton testButton = new JButton("Test");
		
		controls.add(testButton);
		
		this.backgroundPanel.add(controls);

		add(this.backgroundPanel);
	}
	
	// #endregion
}
