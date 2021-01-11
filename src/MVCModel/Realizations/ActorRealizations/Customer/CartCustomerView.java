package MVCModel.Realizations.ActorRealizations.Customer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.*;

import DataController.Product;
import MVCModel.Controllers.ActorControllers.Customer.ICartCustomerViewController;
import MVCModel.Realizations.AbstractView;
import MVCModel.Views.ActorViews.Customer.ICartCustomerView;
import ThriftMerch.Program;

public class CartCustomerView extends AbstractView<ICartCustomerViewController> implements ICartCustomerView {

	private static final long serialVersionUID = 1L;

	/* ****************************** */
	// #region Static public field

	public static final int _MODIFIED_SCREEN_WIDTH = 900, _MODIFIED_SCREEN_HEIGHT = 600;

	// #endregion

	/* ****************************** */
	// #region Private field

	private int totalProductsPrice = 0;

	// #endregion

	/* ****************************** */
	// #region Swing Components

	private JPanel titlePanel, utilsTitlePanel, accountTitlePanel;
	private JLabel appTitle;
	private JButton cartButton, logoutButton, listButton;

	private JPanel productPanel;
	private JPanel contentPanel;
	private JScrollPane productScrollPanel;

	private JPanel footerPanel;
	private JPanel utilsPanel;
	private JButton backButton, homeButton;

	private JPanel productInCartPanel, productImagePanel, productNamePanel, productButtonPanel,
			productQuantityButtonPanel, productPricePanel, totalPricePanel;
	private JButton buyProductButton, productRemoveButton, increaseButton, decreaseButton;
	private JLabel productImageLabel, productNameLabel, quantityLabel, productPriceLabel, totalPriceLabel;

	// #endregion

	/* ****************************** */
	// #region Construct Layout Process
	public CartCustomerView(ICartCustomerViewController viewController) throws IOException {
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
		contentPanel.setBackground(new Color(99, 99, 99));
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

		productScrollPanel = new JScrollPane(contentPanel);
		productScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		productScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		productScrollPanel.getVerticalScrollBar().setUnitIncrement(16);

		productPanel.add(productScrollPanel, BorderLayout.CENTER);

		/* *********************** */
		// Add footer with utilities
		footerPanel = new JPanel(new GridLayout(1, 2));
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

		buyProductButton = new JButton("Buy");
		buyProductButton.setBackground(new Color(30, 30, 30));
		buyProductButton.setPreferredSize(new Dimension(100, 45));

		buyProductButton.addActionListener((ActionEvent e) -> {

		});

		utilsPanel.add(backButton);
		utilsPanel.add(homeButton);
		utilsPanel.add(buyProductButton);

		/* ********** */
		totalPricePanel = new JPanel(new GridLayout(1, 1));
		totalPricePanel.setBackground(new Color(30, 30, 30));

		totalPriceLabel = new JLabel("$0");
		totalPriceLabel.setFont(new Font("Verdana", Font.BOLD, 18));
		totalPriceLabel.setForeground(Color.WHITE);
		totalPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);

		totalPricePanel.add(totalPriceLabel);

		footerPanel.add(utilsPanel);
		footerPanel.add(totalPricePanel);

		/* *********************************** */
		add(titlePanel, BorderLayout.PAGE_START);
		add(productPanel, BorderLayout.CENTER);
		add(footerPanel, BorderLayout.PAGE_END);
	}

	// #endregion

	/* ****************************** */
	// #region Helpers

	@Override
	public void addToCart() throws IOException {
		contentPanel.removeAll();

		if (Program.actor.GetMyCart() == null) {
			return;
		}

		Dimension productDim = productPanel.getPreferredSize();

		for (Entry<String, Product> item : Program.actor.GetMyCart().entrySet()) {

			productInCartPanel = new JPanel(new BorderLayout());
			productInCartPanel.setBackground(new Color(30, 30, 30));
			productInCartPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
			productInCartPanel.setPreferredSize(new Dimension(productDim.width, 100));

			productInCartPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

			/* *********************************** */
			productImagePanel = new JPanel(new BorderLayout());
			productImagePanel.setBackground(new Color(30, 30, 30));

			ImageIcon imageIcon = new ImageIcon(new ImageIcon(item.getValue().GetImagePath()).getImage()
					.getScaledInstance(100, 100, Image.SCALE_DEFAULT));
			productImageLabel = new JLabel();
			productImageLabel.setIcon(imageIcon);
			productImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
			productImageLabel.setVerticalAlignment(SwingConstants.CENTER);

			productImagePanel.add(productImageLabel, BorderLayout.CENTER);

			/* *********************************** */
			productNamePanel = new JPanel(new BorderLayout());
			productNamePanel.setBackground(new Color(30, 30, 30));

			productNameLabel = new JLabel(item.getValue().GetName());
			productNameLabel.setFont(new Font("Verdana", Font.BOLD, 18));
			productNameLabel.setForeground(Color.WHITE);
			productNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
			productNameLabel.setToolTipText(item.getValue().GetName());

			productNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

			productNamePanel.add(productNameLabel);

			/* *********************************** */
			productButtonPanel = new JPanel(new GridLayout(1, 3));
			productButtonPanel.setBackground(new Color(30, 30, 30));

			Dimension productInCartDim = productInCartPanel.getPreferredSize();
			productButtonPanel.setPreferredSize(new Dimension(250, productInCartDim.height));

			/* ******************* */
			productPricePanel = new JPanel(new GridLayout(1, 1));
			productPricePanel.setBackground(new Color(30, 30, 30));

			productPriceLabel = new JLabel(
					"$" + String.valueOf(item.getValue().GetPrice() * item.getValue().GetQuantity()));
			productPriceLabel.setFont(new Font("Verdana", Font.BOLD, 12));
			productPriceLabel.setForeground(Color.WHITE);
			productPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);

			productPricePanel.add(productPriceLabel);

			/* ******************* */
			productQuantityButtonPanel = new JPanel(new GridLayout(3, 1));
			productQuantityButtonPanel.setBackground(new Color(30, 30, 30));

			quantityLabel = new JLabel(String.valueOf(item.getValue().GetQuantity()));
			quantityLabel.setBackground(new Color(30, 30, 30));
			quantityLabel.setFont(new Font("Verdana", Font.BOLD, 12));
			quantityLabel.setForeground(Color.WHITE);
			quantityLabel.setHorizontalAlignment(SwingConstants.CENTER);

			Image increaseImage = ImageIO.read(new File("Resources/Images/plus.png"));
			Icon increaseIcon = new ImageIcon(getScaledImage(increaseImage, 32, 32));
			increaseButton = new JButton(increaseIcon);
			increaseButton.setBackground(new Color(30, 30, 30));

			JLabel currentPriceLabel = productPriceLabel;
			JLabel currentQuantityLabel = quantityLabel;

			increaseButton.addActionListener((ActionEvent e) -> {
				if (Program.actor.AddToCart(item.getValue().GetId(), 1, (String message) -> {
					JOptionPane.showMessageDialog(Program.mainFrame, message, "Error", JOptionPane.ERROR_MESSAGE);
				})) {
					currentQuantityLabel.setText(String.valueOf(item.getValue().GetQuantity()));
					currentPriceLabel
							.setText("$" + String.valueOf(item.getValue().GetPrice() * item.getValue().GetQuantity()));

					totalProductsPrice += item.getValue().GetPrice();

					totalPriceLabel.setText("$" + totalProductsPrice);

					contentPanel.getParent().validate();
					contentPanel.getParent().repaint();

					totalPriceLabel.getParent().validate();
					totalPriceLabel.getParent().repaint();
				}

			});

			Image decreaseImage = ImageIO.read(new File("Resources/Images/minus.png"));
			Icon decreaseIcon = new ImageIcon(getScaledImage(decreaseImage, 32, 32));
			decreaseButton = new JButton(decreaseIcon);
			decreaseButton.setBackground(new Color(30, 30, 30));

			decreaseButton.addActionListener((ActionEvent e) -> {
				if (Program.actor.RemoveItemFromCart(item.getValue().GetId(), (String message) -> {
					JOptionPane.showMessageDialog(Program.mainFrame, message, "Error", JOptionPane.ERROR_MESSAGE);
				})) {

					currentQuantityLabel.setText(String.valueOf(item.getValue().GetQuantity()));
					currentPriceLabel
							.setText("$" + String.valueOf(item.getValue().GetPrice() * item.getValue().GetQuantity()));

					totalProductsPrice -= item.getValue().GetPrice();

					totalPriceLabel.setText("$" + totalProductsPrice);

					contentPanel.getParent().validate();
					contentPanel.getParent().repaint();

					totalPriceLabel.getParent().validate();
					totalPriceLabel.getParent().repaint();
				}
			});

			productQuantityButtonPanel.add(increaseButton);
			productQuantityButtonPanel.add(quantityLabel);
			productQuantityButtonPanel.add(decreaseButton);

			/* ******************* */
			productRemoveButton = new JButton("Remove");
			productRemoveButton.setBackground(new Color(30, 30, 30));

			JPanel currentProductInCartPanel = productInCartPanel;

			productRemoveButton.addActionListener((ActionEvent e) -> {
				totalProductsPrice -= item.getValue().GetPrice() * item.getValue().GetQuantity();

				Program.actor.RemoveAllItemFromCart(item.getValue().GetId());
				getViewController().removeItemFromCart();

				totalPriceLabel.setText("$" + totalProductsPrice);

				removeFromCart(currentProductInCartPanel);
			});

			/* ******************* */
			productButtonPanel.add(productPricePanel);
			productButtonPanel.add(productQuantityButtonPanel);
			productButtonPanel.add(productRemoveButton);

			/* *********************************** */
			productInCartPanel.add(productImagePanel, BorderLayout.LINE_START);
			productInCartPanel.add(productNamePanel, BorderLayout.CENTER);
			productInCartPanel.add(productButtonPanel, BorderLayout.LINE_END);

			contentPanel.add(productInCartPanel);
		}
		contentPanel.getParent().validate();
		contentPanel.getParent().repaint();
	}

	@Override
	public void removeFromCart(Component component) {
		contentPanel.remove(component);

		contentPanel.getParent().validate();
		contentPanel.getParent().repaint();

		totalPriceLabel.getParent().validate();
		totalPriceLabel.getParent().repaint();
	}

	@Override
	public void resetView() {
		contentPanel.removeAll();

		contentPanel.getParent().validate();
		contentPanel.getParent().repaint();
	}

	@Override
	public int getTotalPrice() {
		return this.totalProductsPrice;
	}

	@Override
	public void setTotalPrice(int total) {
		this.totalProductsPrice = total;

		totalPriceLabel.setText("$" + totalProductsPrice);

		totalPriceLabel.getParent().validate();
		totalPriceLabel.getParent().repaint();
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