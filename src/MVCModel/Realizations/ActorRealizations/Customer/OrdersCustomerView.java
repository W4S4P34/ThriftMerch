package MVCModel.Realizations.ActorRealizations.Customer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import MVCModel.Controllers.ActorControllers.Customer.IOrdersCustomerViewController;
import MVCModel.Realizations.AbstractView;
import MVCModel.Views.ActorViews.Customer.IOrdersCustomerView;
import ThriftMerch.Program;

public class OrdersCustomerView extends AbstractView<IOrdersCustomerViewController> implements IOrdersCustomerView {

	private static final long serialVersionUID = 1L;

	/* ****************************** */
	// #region Static public field

	public static final int _MODIFIED_SCREEN_WIDTH = 900, _MODIFIED_SCREEN_HEIGHT = 600;

	// #endregion

	/* ****************************** */
	// #region Private field

	// #endregion

	/* ****************************** */
	// #region Swing Components

	private JPanel titlePanel, utilsTitlePanel, accountTitlePanel;
	private JLabel appTitle;
	private JButton cartButton, logoutButton, listButton;

	private JPanel productPanel;
	private JPanel contentPanel;

	private JPanel footerPanel;
	private JPanel utilsPanel;
	private JButton backButton, homeButton;

	// #endregion

	/* ****************************** */
	// #region Construct Layout Process
	public OrdersCustomerView(IOrdersCustomerViewController viewController) throws IOException {
		super(viewController);

		setLayout(new BorderLayout());

		// Add title
		titlePanel = new JPanel();
		titlePanel.setBackground(new Color(30, 30, 30));
		titlePanel.setPreferredSize(new Dimension(_MODIFIED_SCREEN_WIDTH, 50));
		titlePanel.setLayout(new BorderLayout());

		appTitle = new JLabel(Program.comName);
		appTitle.setFont(new Font("Verdana", Font.BOLD, 18));
		appTitle.setForeground(Color.WHITE);
		appTitle.setHorizontalAlignment(SwingConstants.CENTER);

		accountTitlePanel = new JPanel(new GridLayout(1, 2));
		accountTitlePanel.setBackground(new Color(30, 30, 30));

		Image logoutImage = ImageIO.read(new File("Resources/Images/logout.png"));
		Icon logoutIcon = new ImageIcon(getScaledImage(logoutImage, 32, 32));
		logoutButton = new JButton(logoutIcon);
		logoutButton.setBackground(new Color(30, 30, 30));

		logoutButton.addActionListener((ActionEvent e) -> {
			getViewController().switchToMainMenu();
		});

		accountTitlePanel.add(logoutButton);

		utilsTitlePanel = new JPanel(new GridLayout(1, 2));
		utilsTitlePanel.setBackground(new Color(30, 30, 30));

		Image cartImage = ImageIO.read(new File("Resources/Images/shopping_cart.png"));
		Icon cartIcon = new ImageIcon(getScaledImage(cartImage, 32, 32));
		cartButton = new JButton(cartIcon);
		cartButton.setBackground(new Color(30, 30, 30));

		cartButton.addActionListener((ActionEvent e) -> {
			getViewController().switchToCart();
		});

		Image listImage = ImageIO.read(new File("Resources/Images/list.png"));
		Icon listIcon = new ImageIcon(getScaledImage(listImage, 32, 32));
		listButton = new JButton(listIcon);
		listButton.setBackground(new Color(30, 30, 30));

		listButton.addActionListener((ActionEvent e) -> {

		});

		utilsTitlePanel.add(cartButton);
		utilsTitlePanel.add(listButton);

		titlePanel.add(accountTitlePanel, BorderLayout.LINE_START);
		titlePanel.add(appTitle, BorderLayout.CENTER);
		titlePanel.add(utilsTitlePanel, BorderLayout.LINE_END);

		/* *********************** */
		// Add product panel
		productPanel = new JPanel(new BorderLayout());
		productPanel.setBackground(new Color(99, 99, 99));

		contentPanel = new JPanel();
		contentPanel.setBackground(new Color(99, 99, 99));

		productPanel.add(contentPanel, BorderLayout.CENTER);

		/* *********************** */
		// Add footer with utilities
		footerPanel = new JPanel(new BorderLayout());
		footerPanel.setBackground(new Color(30, 30, 30));
		footerPanel.setPreferredSize(new Dimension(_MODIFIED_SCREEN_WIDTH, 55));

		utilsPanel = new JPanel(new FlowLayout());
		utilsPanel.setBackground(new Color(30, 30, 30));

		// utilsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		backButton = new JButton("Back");
		backButton.setBackground(new Color(30, 30, 30));
		backButton.setPreferredSize(new Dimension(100, 45));

		backButton.addActionListener((ActionEvent event) -> {
			getViewController().switchToPreviousView();
		});

		homeButton = new JButton("Home");
		homeButton.setBackground(new Color(30, 30, 30));
		homeButton.setPreferredSize(new Dimension(100, 45));

		homeButton.addActionListener((ActionEvent event) -> {
			getViewController().switchToDefault();
		});

		utilsPanel.add(backButton);
		utilsPanel.add(homeButton);

		footerPanel.add(utilsPanel);

		/* *********************************** */
		add(titlePanel, BorderLayout.PAGE_START);
		add(productPanel, BorderLayout.CENTER);
		add(footerPanel, BorderLayout.PAGE_END);
	}

	// #endregion

	/* ****************************** */
	// #region Helpers

	private Image getScaledImage(Image srcImg, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}

	// #endregion
}