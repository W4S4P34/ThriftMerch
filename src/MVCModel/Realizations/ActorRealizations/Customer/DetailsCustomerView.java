package MVCModel.Realizations.ActorRealizations.Customer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.imageio.ImageIO;
import javax.swing.*;

import DataController.DataHandler;
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
	private JButton backButton;

	private JPanel productImagePanel, informationPanel;
	private JLabel productImageLabel;

	private JPanel productNamePanel, productBrandPanel, productDatePanel, productPricePanel, productQuantityPanel,
			productDescriptionPanel, productButtonPanel;
	private JLabel productNameLabel, productBrandLabel, productDateLabel, productPriceLabel, productQuantityLabel;
	private JTextArea productDescriptionTextArea;
	private JButton productAddToCartButton, productBuyNowButton;
	private JScrollPane productDescriptionScrollPanel;

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
			try {
				getViewController().switchToCart();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		Image listImage = ImageIO.read(new File("Resources/Images/list.png"));
		Icon listIcon = new ImageIcon(getScaledImage(listImage, 32, 32));
		listButton = new JButton(listIcon);
		listButton.setBackground(new Color(30, 30, 30));

		listButton.addActionListener((ActionEvent e) -> {
			getViewController().switchToOrders();
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
		contentPanel.setLayout(new GridLayout(1, 2));
		contentPanel.setBackground(new Color(99, 99, 99));

		// contentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		/* ****************************************************** */
		// Add content panel
		productImagePanel = new JPanel(new BorderLayout());
		productImagePanel.setBackground(new Color(99, 99, 99));

		ImageIcon imageIcon = new ImageIcon(new ImageIcon(product.GetImagePath()).getImage().getScaledInstance(
				(int) DefaultCustomerView._MODIFIED_SCREEN_WIDTH / 2,
				(int) (DefaultCustomerView._MODIFIED_SCREEN_HEIGHT / 1.4), Image.SCALE_DEFAULT));
		productImageLabel = new JLabel();
		productImageLabel.setIcon(imageIcon);
		productImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		productImageLabel.setVerticalAlignment(SwingConstants.CENTER);

		productImagePanel.add(productImageLabel, BorderLayout.CENTER);

		/* ***************************** */
		informationPanel = new JPanel();
		informationPanel.setLayout(new BoxLayout(informationPanel, BoxLayout.Y_AXIS));
		informationPanel.setBackground(new Color(99, 99, 99));
		informationPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

		productNamePanel = new JPanel(new BorderLayout());
		productNamePanel.setBackground(new Color(99, 99, 99));

		productNameLabel = new JLabel("<html><p style='font-size: 150%; color: white; font-family: \"Verdana\";'><b>"
				+ product.GetName() + "</b></p></html>");

		productPricePanel = new JPanel(new BorderLayout());
		productPricePanel.setBackground(new Color(99, 99, 99));

		productPriceLabel = new JLabel("<html><p style='font-size: 120%; color: white; font-family: \"Verdana\";'><b>"
				+ "$ " + String.valueOf(product.GetPrice()) + "</b></p></html>", SwingConstants.RIGHT);

		productBrandPanel = new JPanel(new BorderLayout());
		productBrandPanel.setBackground(new Color(99, 99, 99));

		productBrandLabel = new JLabel("<html><p style='font-size: 120%; color: white; font-family: \"Verdana\";'><b>"
				+ product.GetBrand() + "</b></p></html>");

		productDatePanel = new JPanel(new BorderLayout());
		productDatePanel.setBackground(new Color(99, 99, 99));

		productDateLabel = new JLabel("<html><p style='font-size: 120%; color: white; font-family: \"Verdana\";'><b>"
				+ String.valueOf(product.GetDate()) + "</b></p></html>");

		productQuantityPanel = new JPanel(new BorderLayout());
		productQuantityPanel.setBackground(new Color(99, 99, 99));

		if(product.GetQuantity() <= 0){
			productQuantityLabel = new JLabel("<html><p style='font-size: 120%; color: red; font-family: \"Verdana\";'><b>SOLD OUT</b></p></html>");
		}else{
			productQuantityLabel = new JLabel(
					"<html><p style='font-size: 120%; color: white; font-family: \"Verdana\";'><b>" + "In-stock: "
							+ String.valueOf(product.GetQuantity()) + "</b></p></html>");

		}


		productDescriptionPanel = new JPanel();
		productDescriptionPanel.setLayout(new BoxLayout(productDescriptionPanel, BoxLayout.Y_AXIS));
		productDescriptionPanel.setBackground(new Color(99, 99, 99));

		productDescriptionTextArea = new JTextArea(product.GetDescription(), 0, 0);
		productDescriptionTextArea.setEditable(false);
		productDescriptionTextArea.setLineWrap(true);
		productDescriptionTextArea.setWrapStyleWord(true);
		productDescriptionTextArea.setFont(new Font("Verdana", Font.BOLD, 16));
		productDescriptionTextArea.setForeground(Color.WHITE);
		productDescriptionTextArea.setBackground(new Color(99, 99, 99));

		productDescriptionScrollPanel = new JScrollPane(productDescriptionPanel);
		productDescriptionScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		productDescriptionScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		productDescriptionScrollPanel.getVerticalScrollBar().setUnitIncrement(16);
		productDescriptionScrollPanel
				.setPreferredSize(new Dimension((int) DefaultCustomerView._MODIFIED_SCREEN_WIDTH / 2, 150));

		productButtonPanel = new JPanel(new GridLayout(1, 2));
		productButtonPanel.setBackground(new Color(99, 99, 99));

		productAddToCartButton = new JButton("Add to Cart");

		if (Program.actor.IsInCart(product.GetId())) {
			productAddToCartButton.setEnabled(false);
		}
		else{
			productAddToCartButton.setBackground(new Color(99, 99, 99));
			productAddToCartButton.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
			productAddToCartButton.addActionListener((ActionEvent event) -> {
				try{
					getViewController().addToCart(product,(isSuccess)->{
						productAddToCartButton.setEnabled(!isSuccess);
					});
				}catch (Exception e){
					System.out.println("Error: " + e);
				}
			});
		}


		productBuyNowButton = new JButton("Buy now");
		productBuyNowButton.setBackground(new Color(99, 99, 99));
		productBuyNowButton.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));

		productBuyNowButton.addActionListener((ActionEvent event) -> {
			try {
				getViewController().BuyNow(product,(isBuySucess)->{
					if(isBuySucess){
						product.SetQuantity(product.GetQuantity()-1);
						int quantity = product.GetQuantity();
						if(quantity <= 0){
							productQuantityLabel.setText("<html><p style='font-size: 120%; color: red; font-family: \"Verdana\";'><b>SOLD OUT</b></p></html>");
						}else{
							productQuantityLabel.setText("<html><p style='font-size: 120%; color: white; font-family: \"Verdana\";'><b>" + "In-stock: "
									+ String.valueOf(quantity) + "</b></p></html>");
						}
					}
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		/* ***************************** */
		productNamePanel.add(productNameLabel);
		productPricePanel.add(productPriceLabel);
		productBrandPanel.add(productBrandLabel);
		productDatePanel.add(productDateLabel);
		productQuantityPanel.add(productQuantityLabel);
		productDescriptionPanel.add(productDescriptionTextArea);
		productButtonPanel.add(productAddToCartButton);
		productButtonPanel.add(productBuyNowButton);

		informationPanel.add(productNamePanel);
		informationPanel.add(productPricePanel);
		informationPanel.add(productBrandPanel);
		informationPanel.add(productDatePanel);
		informationPanel.add(productQuantityPanel);
		informationPanel.add(productDescriptionScrollPanel);
		informationPanel.add(productButtonPanel);

		/* ***************************** */
		contentPanel.add(productImagePanel);
		contentPanel.add(informationPanel);

		/* ****************************************************** */
		contentPanel.getParent().validate();
		contentPanel.getParent().repaint();
	}

	@Override
	public void updateProductView(String productId) {
		updateProductView(DataHandler.GetInstance().GetProduct(productId));
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
