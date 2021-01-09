package MVCModel.Realizations.ActorRealizations.Customer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import DataController.Product;
import MVCModel.Controllers.ActorControllers.Customer.IDetailsCustomerViewController;
import MVCModel.Realizations.AbstractView;
import MVCModel.Views.ActorViews.Customer.IDetailsCustomerView;
import ThriftMerch.Program;

public class DetailsCustomerView extends AbstractView<IDetailsCustomerViewController> implements IDetailsCustomerView {

	private static final long serialVersionUID = 1L;

	/* ****************************** */
	// #region Static public field

	public static final int _MODIFIED_SCREEN_WIDTH = 900, _MODIFIED_SCREEN_HEIGHT = 600;
	public static final int _PRODUCT_LIMIT_ON_PAGE = 12, _PRODUCT_LIMIT_ON_ROW = 4;

	// #endregion

	/* ****************************** */
	// #region Private field

	// #endregion

	/* ****************************** */
	// #region Swing Components

	private JPanel titlePanel;
	private JLabel appTitle;
	private JButton cartButton, logoutButton;
	
	private JPanel productPanel;
	private JPanel contentPanel;
	private JScrollPane productScrollPanel;
	
	private JPanel footerPanel;
	private JPanel utilsPanel;
	private JButton backButton;

	// #endregion

	/* ****************************** */
	// #region Construct Layout Process
	public DetailsCustomerView(IDetailsCustomerViewController viewController) throws IOException {
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

		Image logoutImage = ImageIO.read(new File("Resources/Images/logout.png"));
		Icon logoutIcon = new ImageIcon(getScaledImage(logoutImage, 32, 32));
		logoutButton = new JButton(logoutIcon);
		logoutButton.setBackground(new Color(30, 30, 30));
		
		logoutButton.addActionListener((ActionEvent e) -> {
			getViewController().switchToMainMenu();
		});
		
		Image cartImage = ImageIO.read(new File("Resources/Images/shopping_cart.png"));
		Icon cartIcon = new ImageIcon(getScaledImage(cartImage, 32, 32));
		cartButton = new JButton(cartIcon);
		cartButton.setBackground(new Color(30, 30, 30));
		
		cartButton.addActionListener((ActionEvent e) -> {
			
		});

		titlePanel.add(logoutButton, BorderLayout.LINE_START);
		titlePanel.add(appTitle, BorderLayout.CENTER);
		titlePanel.add(cartButton, BorderLayout.LINE_END);

		// Add product panel
		productPanel = new JPanel(new BorderLayout());
		productPanel.setBackground(new Color(99, 99, 99));
		
		contentPanel = new JPanel();
		contentPanel.setBackground(Color.WHITE);
		
		productScrollPanel = new JScrollPane(contentPanel);
		productScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		productScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		productScrollPanel.getVerticalScrollBar().setUnitIncrement(16);
		
		productPanel.add(productScrollPanel, BorderLayout.CENTER);
		
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
			getViewController().switchToDefault();
		});
		
		utilsPanel.add(backButton);
		
		footerPanel.add(utilsPanel);

		/* *********************************** */
		add(titlePanel, BorderLayout.PAGE_START);
		add(productPanel, BorderLayout.CENTER);
		add(footerPanel, BorderLayout.PAGE_END);
	}

	// #endregion

	/* ****************************** */
	// #region Helpers
	
	@Override
	public void updateProductView(Product product) {
		contentPanel.removeAll();
		contentPanel.setLayout(new BorderLayout());
		/* ****************************************************** */
		// Add content panel
		
		JLabel test = new JLabel(product.GetBrand());
		
		/* ***************************** */
		contentPanel.add(test);
		
		/* ****************************************************** */
		contentPanel.getParent().validate();
		contentPanel.getParent().repaint();
	}
	
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
