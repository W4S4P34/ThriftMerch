package MVCModel.Realizations.ActorRealizations.Shipper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import DataController.DataHandler;
import DataController.ORDERSTATUS;
import DataController.Order;
import DataController.Product;
import MVCModel.Controllers.ActorControllers.Shipper.IOrderDetailsShipperViewController;
import MVCModel.Realizations.AbstractView;
import MVCModel.Views.ActorViews.Shipper.IOrderDetailsShipperView;
import ThriftMerch.Program;

public class OrderDetailsShipperView extends AbstractView<IOrderDetailsShipperViewController>
		implements IOrderDetailsShipperView {

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

	private JLabel appTitle;

	private JScrollPane productScrollPanel;

	private JPanel accountTitlePanel, titlePanel, footerPanel, utilsTitlePanel;
	private JPanel productPanel, contentPanel;

	private JButton logoutButton, listButton;

	private JPanel orderDetailsPanel, orderIDPanel, orderAddressPanel, orderDatePanel, orderStatusPanel,
			orderTotalPricePanel, orderTotalItemsPanel;
	private JPanel productImagePanel, productNamePanel, productSubInfoPanel, productPricePanel, productQuantityPanel;
	private JLabel orderIDLabel, orderAddressLabel, orderDateLabel, orderStatusLabel, orderTotalPriceLabel,
			orderTotalItemsLabel;
	private JLabel productImageLabel, productNameLabel, productPriceLabel, productQuantityLabel;

	private JPanel utilsPanel;
	private JButton backButton, homeButton, takeProductButton, removeProductButton, orderDoneButton;

	// #endregion

	/* ****************************** */
	// #region Construct Layout Process

	public OrderDetailsShipperView(IOrderDetailsShipperViewController viewController) throws IOException {
		super(viewController);
		setLayout(new BorderLayout());

		/* *********************** */
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

		Image listImage = ImageIO.read(new File("Resources/Images/list.png"));
		Icon listIcon = new ImageIcon(getScaledImage(listImage, 32, 32));
		listButton = new JButton(listIcon);
		listButton.setBackground(new Color(30, 30, 30));

		listButton.addActionListener((ActionEvent e) -> {
			getViewController().switchToTakenOrder();
		});

		utilsTitlePanel.add(listButton);

		titlePanel.add(accountTitlePanel, BorderLayout.LINE_START);
		titlePanel.add(appTitle, BorderLayout.CENTER);
		titlePanel.add(utilsTitlePanel, BorderLayout.LINE_END);

		/* *********************** */
		// Add product panel
		productPanel = new JPanel(new BorderLayout());
		productPanel.setBackground(new Color(99, 99, 99));

		contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setBackground(new Color(99, 99, 99));

		// contentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		productScrollPanel = new JScrollPane(contentPanel);
		productScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		productScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		productScrollPanel.getVerticalScrollBar().setUnitIncrement(16);

		// productPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		productPanel.add(productScrollPanel);

		/* *********************** */
		// Add footer with utilities
		footerPanel = new JPanel(new BorderLayout());
		footerPanel.setBackground(new Color(30, 30, 30));
		footerPanel.setPreferredSize(new Dimension(_MODIFIED_SCREEN_WIDTH, 55));

		/* *********************************** */
		add(titlePanel, BorderLayout.PAGE_START);
		add(productPanel, BorderLayout.CENTER);
		add(footerPanel, BorderLayout.PAGE_END);
	}

	// #endregion

	/* ****************************** */
	// #region Helpers

	@Override
	public void updateMainOrderDetailsView(Order order) {
		contentPanel.removeAll();

		/* *********************************** */
		Dimension productDim = productPanel.getPreferredSize();

		orderDetailsPanel = new JPanel(new BorderLayout());
		orderDetailsPanel.setBackground(new Color(30, 30, 30));
		orderDetailsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		orderDetailsPanel.setPreferredSize(new Dimension(productDim.width, 100));

		/* *********************************** */
		orderIDPanel = new JPanel(new BorderLayout());
		orderIDPanel.setBackground(new Color(30, 30, 30));

		orderIDLabel = new JLabel("ID: " + order.GetID());
		orderIDLabel.setFont(new Font("Verdana", Font.BOLD, 18));
		orderIDLabel.setForeground(Color.WHITE);
		orderIDLabel.setHorizontalAlignment(SwingConstants.LEFT);
		orderIDLabel.setToolTipText(order.GetID());

		orderIDPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

		orderIDPanel.add(orderIDLabel);

		orderDetailsPanel.add(orderIDPanel, BorderLayout.CENTER);

		/* *********************************** */
		contentPanel.add(orderDetailsPanel);

		/* *********************************** */
		orderDetailsPanel = new JPanel(new BorderLayout());
		orderDetailsPanel.setBackground(new Color(30, 30, 30));
		orderDetailsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		orderDetailsPanel.setPreferredSize(new Dimension(productDim.width, 100));

		/* *********************************** */
		orderAddressPanel = new JPanel(new BorderLayout());
		orderAddressPanel.setBackground(new Color(30, 30, 30));

		orderAddressLabel = new JLabel("Address: " + Program.actor.GetAddress());
		orderAddressLabel.setFont(new Font("Verdana", Font.BOLD, 18));
		orderAddressLabel.setForeground(Color.WHITE);
		orderAddressLabel.setHorizontalAlignment(SwingConstants.LEFT);
		orderAddressLabel.setToolTipText(order.GetID());

		orderAddressPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

		orderAddressPanel.add(orderAddressLabel);

		orderDetailsPanel.add(orderAddressPanel, BorderLayout.CENTER);

		/* *********************************** */
		contentPanel.add(orderDetailsPanel);

		/* *********************************** */
		orderDetailsPanel = new JPanel(new BorderLayout());
		orderDetailsPanel.setBackground(new Color(30, 30, 30));
		orderDetailsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		orderDetailsPanel.setPreferredSize(new Dimension(productDim.width, 100));

		/* *********************************** */
		orderDatePanel = new JPanel(new BorderLayout());
		orderDatePanel.setBackground(new Color(30, 30, 30));

		orderDateLabel = new JLabel("Date: " + String.valueOf(order.GetDate()));
		orderDateLabel.setFont(new Font("Verdana", Font.BOLD, 18));
		orderDateLabel.setForeground(Color.WHITE);
		orderDateLabel.setHorizontalAlignment(SwingConstants.LEFT);
		orderDateLabel.setToolTipText(order.GetID());

		orderDatePanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

		orderDatePanel.add(orderDateLabel);

		orderDetailsPanel.add(orderDatePanel, BorderLayout.CENTER);

		/* *********************************** */
		contentPanel.add(orderDetailsPanel);

		/* *********************************** */
		orderDetailsPanel = new JPanel(new BorderLayout());
		orderDetailsPanel.setBackground(new Color(30, 30, 30));
		orderDetailsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		orderDetailsPanel.setPreferredSize(new Dimension(productDim.width, 100));

		/* *********************************** */
		orderStatusPanel = new JPanel(new BorderLayout());
		orderStatusPanel.setBackground(new Color(30, 30, 30));

		ORDERSTATUS status = order.GetOrderStatus();

		orderStatusLabel = new JLabel("Status: " + String.valueOf(status));
		orderStatusLabel.setFont(new Font("Verdana", Font.BOLD, 18));

		if (status == ORDERSTATUS.PLACED) {
			orderStatusLabel.setForeground(Color.RED);
		} else if (status == ORDERSTATUS.PROCESSED) {
			orderStatusLabel.setForeground(Color.YELLOW);
		} else if (status == ORDERSTATUS.DELIVERED) {
			orderStatusLabel.setForeground(Color.GREEN);
		}

		orderStatusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		orderStatusLabel.setToolTipText(order.GetID());

		orderStatusPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

		orderStatusPanel.add(orderStatusLabel);

		orderDetailsPanel.add(orderStatusPanel, BorderLayout.CENTER);

		/* *********************************** */
		contentPanel.add(orderDetailsPanel);

		/* *********************************** */
		orderDetailsPanel = new JPanel(new BorderLayout());
		orderDetailsPanel.setBackground(new Color(30, 30, 30));
		orderDetailsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		orderDetailsPanel.setPreferredSize(new Dimension(productDim.width, 100));

		/* *********************************** */
		orderTotalPricePanel = new JPanel(new BorderLayout());
		orderTotalPricePanel.setBackground(new Color(30, 30, 30));

		orderTotalPriceLabel = new JLabel("$" + String.valueOf(order.GetTotalPrice()));
		orderTotalPriceLabel.setFont(new Font("Verdana", Font.BOLD, 18));
		orderTotalPriceLabel.setForeground(Color.WHITE);
		orderTotalPriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		orderTotalPriceLabel.setToolTipText(order.GetID());

		orderTotalPricePanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

		orderTotalPricePanel.add(orderTotalPriceLabel);

		orderDetailsPanel.add(orderTotalPricePanel, BorderLayout.CENTER);

		/* *********************************** */
		contentPanel.add(orderDetailsPanel);

		/* *********************************** */
		orderDetailsPanel = new JPanel(new BorderLayout());
		orderDetailsPanel.setBackground(new Color(30, 30, 30));
		orderDetailsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		orderDetailsPanel.setPreferredSize(new Dimension(productDim.width, 100));

		/* *********************************** */
		orderTotalItemsPanel = new JPanel(new BorderLayout());
		orderTotalItemsPanel.setBackground(new Color(30, 30, 30));

		orderTotalItemsLabel = new JLabel("Totel Products: " + String.valueOf(order.GetOrderItem().size()));
		orderTotalItemsLabel.setFont(new Font("Verdana", Font.BOLD, 18));
		orderTotalItemsLabel.setForeground(Color.WHITE);
		orderTotalItemsLabel.setHorizontalAlignment(SwingConstants.LEFT);
		orderTotalItemsLabel.setToolTipText(order.GetID());

		orderTotalItemsPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

		orderTotalItemsPanel.add(orderTotalItemsLabel);

		orderDetailsPanel.add(orderTotalItemsPanel, BorderLayout.CENTER);

		/* *********************************** */
		contentPanel.add(orderDetailsPanel);

		/* *********************************** */
		for (Product item : order.GetOrderItem()) {

			orderDetailsPanel = new JPanel(new BorderLayout());
			orderDetailsPanel.setBackground(new Color(30, 30, 30));
			orderDetailsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
			orderDetailsPanel.setPreferredSize(new Dimension(productDim.width, 100));

			orderDetailsPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

			/* *********************************** */
			productImagePanel = new JPanel(new BorderLayout());
			productImagePanel.setBackground(new Color(30, 30, 30));

			ImageIcon imageIcon = new ImageIcon(
					new ImageIcon(item.GetImagePath()).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
			productImageLabel = new JLabel();
			productImageLabel.setIcon(imageIcon);
			productImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
			productImageLabel.setVerticalAlignment(SwingConstants.CENTER);

			productImagePanel.add(productImageLabel, BorderLayout.CENTER);

			/* *********************************** */
			productNamePanel = new JPanel(new BorderLayout());
			productNamePanel.setBackground(new Color(30, 30, 30));

			productNameLabel = new JLabel(item.GetName());
			productNameLabel.setFont(new Font("Verdana", Font.BOLD, 18));
			productNameLabel.setForeground(Color.WHITE);
			productNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
			productNameLabel.setToolTipText(item.GetName());

			productNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

			productNamePanel.add(productNameLabel);

			/* ******************* */
			productSubInfoPanel = new JPanel(new GridLayout(1, 2));
			productSubInfoPanel.setBackground(new Color(30, 30, 30));

			/* ******************* */
			productPricePanel = new JPanel(new GridLayout(1, 1));
			productPricePanel.setBackground(new Color(30, 30, 30));

			productPriceLabel = new JLabel("$" + String.valueOf(item.GetPrice() * item.GetQuantity()));
			productPriceLabel.setFont(new Font("Verdana", Font.BOLD, 18));
			productPriceLabel.setForeground(Color.WHITE);
			productPriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);

			productPricePanel.add(productPriceLabel);

			/* ******************* */
			productQuantityPanel = new JPanel(new GridLayout(1, 1));
			productQuantityPanel.setBackground(new Color(30, 30, 30));

			productQuantityLabel = new JLabel(String.valueOf(item.GetQuantity()));
			productQuantityLabel.setBackground(new Color(30, 30, 30));
			productQuantityLabel.setFont(new Font("Verdana", Font.BOLD, 18));
			productQuantityLabel.setForeground(Color.WHITE);
			productQuantityLabel.setHorizontalAlignment(SwingConstants.CENTER);

			productQuantityPanel.add(productQuantityLabel);

			/* ******************* */
			productSubInfoPanel.add(productPricePanel);
			productSubInfoPanel.add(productQuantityPanel);

			/* ******************* */
			orderDetailsPanel.add(productImagePanel, BorderLayout.LINE_START);
			orderDetailsPanel.add(productNamePanel, BorderLayout.CENTER);
			orderDetailsPanel.add(productSubInfoPanel, BorderLayout.LINE_END);

			/* ******************* */
			contentPanel.add(orderDetailsPanel);

		}
		contentPanel.getParent().validate();
		contentPanel.getParent().repaint();
	}

	@Override
	public void updateOrderDetailsView(Order order) {
		updateMainOrderDetailsView(order);

		footerPanel.removeAll();

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

		takeProductButton = new JButton("Take");
		takeProductButton.setBackground(new Color(30, 30, 30));
		takeProductButton.setPreferredSize(new Dimension(100, 45));

		takeProductButton.addActionListener((ActionEvent e) -> {
			Program.actor.UpdateOrder(order.GetID(), ORDERSTATUS.PROCESSED);

			getViewController().switchToDefault();
		});

		utilsPanel.add(backButton);
		utilsPanel.add(homeButton);
		utilsPanel.add(takeProductButton);

		footerPanel.add(utilsPanel);

		footerPanel.getParent().validate();
		footerPanel.getParent().repaint();
	}

	@Override
	public void updateTakenOrderDetailsView(Order order) {
		updateMainOrderDetailsView(order);
		getViewController().SetCurrentOrder(order);
		footerPanel.removeAll();

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

		if (order.GetOrderStatus() == ORDERSTATUS.PROCESSED) {

			removeProductButton = new JButton("Remove");
			removeProductButton.setBackground(new Color(30, 30, 30));
			removeProductButton.setPreferredSize(new Dimension(100, 45));

			removeProductButton.addActionListener((ActionEvent e) -> {
				Program.actor.RemoveOrder(order.GetID());
				order.SetShipper(null);
				order.SetOrderStatus(ORDERSTATUS.PLACED);
				updateOrderDetailsView(order);
				getViewController().switchToTakenOrder();
			});
			
			orderDoneButton = new JButton("Done");
			orderDoneButton.setBackground(new Color(30, 30, 30));
			orderDoneButton.setPreferredSize(new Dimension(100, 45));

			orderDoneButton.addActionListener((ActionEvent e) -> {
				Program.actor.UpdateOrder(order.GetID(), ORDERSTATUS.DELIVERED);
				order.SetOrderStatus(ORDERSTATUS.DELIVERED);
				updateOrderDetailsView(order);
				getViewController().switchToTakenOrder();
			});

			utilsPanel.add(removeProductButton);
			utilsPanel.add(orderDoneButton);
		}

		footerPanel.add(utilsPanel);

		footerPanel.getParent().validate();
		footerPanel.getParent().repaint();
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
