package MVCModel.Realizations.ActorRealizations.Shipper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

import DataController.ORDERSTATUS;
import DataController.Order;
import MVCModel.Controllers.ActorControllers.Shipper.IDefaultShipperViewController;
import MVCModel.Realizations.AbstractView;
import MVCModel.Views.ActorViews.Shipper.IDefaultShipperView;
import ThriftMerch.Program;

public class DefaultShipperView extends AbstractView<IDefaultShipperViewController> implements IDefaultShipperView {

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

	private JPanel orderPanel, orderInformationPanel, orderIDPanel, orderDatePanel, orderTotalPanel, orderStatusPanel,
			orderButtonPanel;
	private JLabel orderIDLabel, orderDateLabel, orderTotalLabel, orderStatusLabel;
	private JButton orderDetailsButton, orderTakeButton;

	// #endregion

	/* ****************************** */
	// #region Construct Layout Process

	public DefaultShipperView(IDefaultShipperViewController viewController) throws IOException {
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
	public void updateOrderView() {
		contentPanel.removeAll();

		if (Program.actor.ViewAllOrphanedOrder() == null) {
			contentPanel.getParent().validate();
			contentPanel.getParent().repaint();
			
			return;
		}

		Dimension productDim = productPanel.getPreferredSize();

		for (Order item : Program.actor.ViewAllOrphanedOrder()) {

			orderPanel = new JPanel(new BorderLayout());
			orderPanel.setBackground(new Color(30, 30, 30));
			orderPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
			orderPanel.setPreferredSize(new Dimension(productDim.width, 100));

			orderPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

			/* *********************************** */
			orderInformationPanel = new JPanel(new GridLayout(1, 4));
			orderInformationPanel.setBackground(new Color(30, 30, 30));

			/* *********************************** */
			orderIDPanel = new JPanel(new BorderLayout());
			orderIDPanel.setBackground(new Color(30, 30, 30));

			orderIDLabel = new JLabel(item.GetID());
			orderIDLabel.setFont(new Font("Verdana", Font.BOLD, 18));
			orderIDLabel.setForeground(Color.WHITE);
			orderIDLabel.setHorizontalAlignment(SwingConstants.LEFT);
			orderIDLabel.setToolTipText(item.GetID());

			orderIDPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

			orderIDPanel.add(orderIDLabel);

			/* *********************************** */
			orderDatePanel = new JPanel(new BorderLayout());
			orderDatePanel.setBackground(new Color(30, 30, 30));

			orderDateLabel = new JLabel(String.valueOf(item.GetDate()));
			orderDateLabel.setFont(new Font("Verdana", Font.BOLD, 18));
			orderDateLabel.setForeground(Color.WHITE);
			orderDateLabel.setHorizontalAlignment(SwingConstants.LEFT);
			orderDateLabel.setToolTipText(String.valueOf(item.GetDate()));

			orderDatePanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

			orderDatePanel.add(orderDateLabel);

			/* *********************************** */
			orderTotalPanel = new JPanel(new BorderLayout());
			orderTotalPanel.setBackground(new Color(30, 30, 30));

			orderTotalLabel = new JLabel("$" + String.valueOf(item.GetTotalPrice()));
			orderTotalLabel.setFont(new Font("Verdana", Font.BOLD, 18));
			orderTotalLabel.setForeground(Color.WHITE);
			orderTotalLabel.setHorizontalAlignment(SwingConstants.LEFT);
			orderTotalLabel.setToolTipText("$" + String.valueOf(item.GetTotalPrice()));

			orderTotalPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

			orderTotalPanel.add(orderTotalLabel);

			/* *********************************** */
			orderStatusPanel = new JPanel(new BorderLayout());
			orderStatusPanel.setBackground(new Color(30, 30, 30));

			ORDERSTATUS status = item.GetOrderStatus();

			orderStatusLabel = new JLabel(String.valueOf(status));
			orderStatusLabel.setFont(new Font("Verdana", Font.BOLD, 18));

			if (status == ORDERSTATUS.PLACED) {
				orderStatusLabel.setForeground(Color.RED);
			} else if (status == ORDERSTATUS.PROCESSED) {
				orderStatusLabel.setForeground(Color.YELLOW);
			} else if (status == ORDERSTATUS.DELIVERED) {
				orderStatusLabel.setForeground(Color.GREEN);
			}

			orderStatusLabel.setHorizontalAlignment(SwingConstants.LEFT);
			orderStatusLabel.setToolTipText(String.valueOf(item.GetOrderStatus()));

			orderStatusPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

			orderStatusPanel.add(orderStatusLabel);

			/* *********************************** */
			orderButtonPanel = new JPanel(new GridLayout(1, 2));
			orderButtonPanel.setBackground(new Color(30, 30, 30));

			/* *********************************** */
			orderDetailsButton = new JButton("Details");
			orderDetailsButton.setBackground(new Color(30, 30, 30));

			orderDetailsButton.addActionListener((ActionEvent e) -> {
				getViewController().switchToDetails(item);
			});

			/* *********************************** */
			orderTakeButton = new JButton("Take");
			orderTakeButton.setBackground(new Color(30, 30, 30));

			orderTakeButton.addActionListener((ActionEvent e) -> {
				Program.actor.UpdateOrder(item.GetID(), ORDERSTATUS.PROCESSED);
				
				updateOrderView();
			});

			/* *********************************** */
			orderInformationPanel.add(orderIDPanel);
			orderInformationPanel.add(orderDatePanel);
			orderInformationPanel.add(orderTotalPanel);
			orderInformationPanel.add(orderStatusPanel);

			/* *********************************** */
			orderButtonPanel.add(orderDetailsButton);
			orderButtonPanel.add(orderTakeButton);

			orderPanel.add(orderInformationPanel, BorderLayout.CENTER);
			orderPanel.add(orderButtonPanel, BorderLayout.LINE_END);

			contentPanel.add(orderPanel);
		}
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
