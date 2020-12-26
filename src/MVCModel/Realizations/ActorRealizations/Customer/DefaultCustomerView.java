package MVCModel.Realizations.ActorRealizations.Customer;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.NumberFormatter;

import DataController.DataHandler;
import DataController.Product;
import MVCModel.Controllers.ActorControllers.Customer.IDefaultCustomerViewController;
import MVCModel.Realizations.AbstractView;
import MVCModel.Views.ActorViews.Customer.IDefaultCustomerView;
import ThriftMerch.Program;

public class DefaultCustomerView extends AbstractView<IDefaultCustomerViewController> implements IDefaultCustomerView {

	private static final long serialVersionUID = 1L;

	/* ****************************** */
	// #region Static public field

	public static final int _MODIFIED_SCREEN_WIDTH = 900, _MODIFIED_SCREEN_HEIGHT = 600;
	public static final int _PRODUCT_LIMIT_ON_PAGE = 12, _PRODUCT_LIMIT_ON_ROW = 4;

	// #endregion

	/* ****************************** */
	// #region Private field

	private ArrayList<Product> productList;
	private int pageSize;

	// #endregion

	/* ****************************** */
	// #region Swing Components

	private JLabel appTitle, searchLabel;
	private JLabel productNameLabel, productBrandLabel, productPriceLabel;
	private JLabel productImageLabel;

	private JScrollPane productScrollPanel;

	private JPanel titlePanel, footerPanel, utilsPanel;
	private JPanel productPanel, contentPanel, productRowPanel, productInfoPanel;
	private JPanel productImagePanel, productNamePanel, productBrandPanel, productPricePanel, productButtonPanel;

	private JButton productAddToCartButton, productDetailsButton;

	private JButton leftButton, rightButton, backwardButton, forwardButton;
	private JFormattedTextField pageTextField;
	private JLabel pageRecordLabel;

	private JTextField searchTextField;
	private JButton searchButton;

	// #endregion

	/* ****************************** */
	// #region Construct Layout Process

	public DefaultCustomerView(IDefaultCustomerViewController viewController) throws IOException {
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

		titlePanel.add(appTitle, BorderLayout.CENTER);

		/* *********************** */
		// Add product panel
		productPanel = new JPanel(null);
		productPanel.setBackground(new Color(0, 0, 0, 150));

		contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setBackground(Color.BLACK);

		// contentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		productScrollPanel = new JScrollPane(contentPanel);
		productScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		productScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		productScrollPanel.getVerticalScrollBar().setUnitIncrement(16);
		productScrollPanel.setBounds(30, 10, 840, 450);

		// productPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		productPanel.add(productScrollPanel);

		/* *********************** */
		// Add footer with utilities
		footerPanel = new JPanel(null);
		footerPanel.setBackground(new Color(30, 30, 30));
		footerPanel.setPreferredSize(new Dimension(_MODIFIED_SCREEN_WIDTH, 55));

		SpringLayout springUtilsPanelLayout = new SpringLayout();
		utilsPanel = new JPanel(springUtilsPanelLayout);
		utilsPanel.setBackground(new Color(30, 30, 30));
		utilsPanel.setBounds(30, 5, 840, 45);

		searchLabel = new JLabel("Search: ");
		searchLabel.setFont(new Font("Verdana", Font.BOLD, 18));
		searchLabel.setForeground(Color.WHITE);
		searchLabel.setHorizontalAlignment(SwingConstants.LEFT);

		springUtilsPanelLayout.putConstraint(SpringLayout.WEST, searchLabel, 5, SpringLayout.WEST, utilsPanel);
		springUtilsPanelLayout.putConstraint(SpringLayout.NORTH, searchLabel, 10, SpringLayout.NORTH, utilsPanel);

		searchTextField = new JTextField(45);

		searchTextField.addActionListener((ActionEvent e) -> {
			System.out.println("Press Enter");
		});

		springUtilsPanelLayout.putConstraint(SpringLayout.WEST, searchTextField, 85, SpringLayout.WEST, utilsPanel);
		springUtilsPanelLayout.putConstraint(SpringLayout.NORTH, searchTextField, 13, SpringLayout.NORTH, utilsPanel);

		searchButton = new JButton("Search");

		searchButton.setBackground(new Color(30, 30, 30));

		springUtilsPanelLayout.putConstraint(SpringLayout.WEST, searchButton, 455, SpringLayout.WEST, utilsPanel);
		springUtilsPanelLayout.putConstraint(SpringLayout.NORTH, searchButton, 11, SpringLayout.NORTH, utilsPanel);

		searchButton.addActionListener((ActionEvent event) -> {

		});

		utilsPanel.add(searchLabel);
		utilsPanel.add(searchTextField);
		utilsPanel.add(searchButton);

		/* ****************************************************** */
		// Pagination
		Image leftImage = ImageIO.read(new File("Resources/Images/left-arrow.png"));
		Icon leftIcon = new ImageIcon(getScaledImage(leftImage, 12, 12));
		leftButton = new JButton(leftIcon);
		springUtilsPanelLayout.putConstraint(SpringLayout.WEST, leftButton, 625, SpringLayout.WEST, utilsPanel);
		springUtilsPanelLayout.putConstraint(SpringLayout.NORTH, leftButton, 12, SpringLayout.NORTH, utilsPanel);

		leftButton.setPreferredSize(new Dimension(20, 20));
		leftButton.setBackground(new Color(30, 30, 30));

		leftButton.addActionListener((ActionEvent e) -> {
			Integer currentValue = (Integer) pageTextField.getValue() - (Integer) 1;

			if (currentValue >= 1)
				pageTextField.setText(currentValue.toString());

			try {
				pageTextField.commitEdit();
			} catch (ParseException exception) {
				exception.printStackTrace();
			}
		});

		Image rightImage = ImageIO.read(new File("Resources/Images/right-arrow.png"));
		Icon rightIcon = new ImageIcon(getScaledImage(rightImage, 12, 12));
		rightButton = new JButton(rightIcon);
		springUtilsPanelLayout.putConstraint(SpringLayout.WEST, rightButton, 700, SpringLayout.WEST, utilsPanel);
		springUtilsPanelLayout.putConstraint(SpringLayout.NORTH, rightButton, 12, SpringLayout.NORTH, utilsPanel);

		rightButton.setPreferredSize(new Dimension(20, 20));
		rightButton.setBackground(new Color(30, 30, 30));

		rightButton.addActionListener((ActionEvent e) -> {
			Integer currentValue = (Integer) pageTextField.getValue() + (Integer) 1;

			if (currentValue <= pageSize)
				pageTextField.setText(currentValue.toString());

			try {
				pageTextField.commitEdit();
			} catch (ParseException exception) {
				exception.printStackTrace();
			}
		});

		Image backwardImage = ImageIO.read(new File("Resources/Images/backward.png"));
		Icon backwardIcon = new ImageIcon(getScaledImage(backwardImage, 12, 12));
		backwardButton = new JButton(backwardIcon);
		springUtilsPanelLayout.putConstraint(SpringLayout.WEST, backwardButton, 600, SpringLayout.WEST, utilsPanel);
		springUtilsPanelLayout.putConstraint(SpringLayout.NORTH, backwardButton, 12, SpringLayout.NORTH, utilsPanel);

		backwardButton.setPreferredSize(new Dimension(20, 20));
		backwardButton.setBackground(new Color(30, 30, 30));

		backwardButton.addActionListener((ActionEvent e) -> {
			pageTextField.setText(String.valueOf(1));

			try {
				pageTextField.commitEdit();
			} catch (ParseException exception) {
				exception.printStackTrace();
			}
		});

		Image forwardImage = ImageIO.read(new File("Resources/Images/forward.png"));
		Icon forwardIcon = new ImageIcon(getScaledImage(forwardImage, 12, 12));
		forwardButton = new JButton(forwardIcon);
		springUtilsPanelLayout.putConstraint(SpringLayout.WEST, forwardButton, 725, SpringLayout.WEST, utilsPanel);
		springUtilsPanelLayout.putConstraint(SpringLayout.NORTH, forwardButton, 12, SpringLayout.NORTH, utilsPanel);

		forwardButton.setPreferredSize(new Dimension(20, 20));
		forwardButton.setBackground(new Color(30, 30, 30));

		forwardButton.addActionListener((ActionEvent e) -> {
			pageTextField.setText(String.valueOf(pageSize));

			try {
				pageTextField.commitEdit();
			} catch (ParseException exception) {
				exception.printStackTrace();
			}
		});

		/* ***************************** */
		pageSize = DataHandler.GetInstance().GetPageNumber(_PRODUCT_LIMIT_ON_PAGE);

		NumberFormat numberFormat = NumberFormat.getInstance();
		NumberFormatter numberFormatter = new NumberFormatter(numberFormat);

		numberFormatter.setValueClass(Integer.class);
		numberFormatter.setMinimum(1);
		numberFormatter.setMaximum(pageSize);
		numberFormatter.setAllowsInvalid(true);
		numberFormatter.setOverwriteMode(false);

		pageTextField = new JFormattedTextField(numberFormatter);
		springUtilsPanelLayout.putConstraint(SpringLayout.WEST, pageTextField, 650, SpringLayout.WEST, utilsPanel);
		springUtilsPanelLayout.putConstraint(SpringLayout.NORTH, pageTextField, 12, SpringLayout.NORTH, utilsPanel);

		pageTextField.setValue(new Integer(1));
		pageTextField.setColumns(5);

		pageTextField.addPropertyChangeListener("value", (PropertyChangeEvent e) -> {
			Object source = e.getSource();
			if (source == pageTextField) {
				try {
					pageTextField.commitEdit();
					updateProductView((Integer) pageTextField.getValue());
				} catch (ParseException exception) {
					exception.printStackTrace();
				}
			}
		});

		pageRecordLabel = new JLabel("of " + String.valueOf(pageSize));
		springUtilsPanelLayout.putConstraint(SpringLayout.WEST, pageRecordLabel, 750, SpringLayout.WEST, utilsPanel);
		springUtilsPanelLayout.putConstraint(SpringLayout.NORTH, pageRecordLabel, 12, SpringLayout.NORTH, utilsPanel);

		pageRecordLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		pageRecordLabel.setForeground(Color.WHITE);

		utilsPanel.add(leftButton);
		utilsPanel.add(rightButton);
		utilsPanel.add(backwardButton);
		utilsPanel.add(forwardButton);
		utilsPanel.add(pageTextField);
		utilsPanel.add(pageRecordLabel);

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
	public void updateProductView(int offset) {
		contentPanel.removeAll();
		/* ****************************************************** */
		// Add Content Panel
		int _DEFAULT_PRODUCT_FRAME_WIDTH = 205;
		int _DEFAULT_PRODUCT_FRAME_HEIGHT = 400;

		productList = DataHandler.GetInstance().GetAllProducts(_PRODUCT_LIMIT_ON_PAGE, offset);

		int availableProductSize = productList.size();
		int rowNumber = (int) Math.ceil(availableProductSize / (float) _PRODUCT_LIMIT_ON_ROW);

		boolean isRemained = (availableProductSize % _PRODUCT_LIMIT_ON_ROW != 0);
		int remainder = 0;
		if (isRemained) {
			remainder = availableProductSize % _PRODUCT_LIMIT_ON_ROW;
		}

		for (int i = 0; i < rowNumber; i++) {
			productRowPanel = new JPanel(null);
			// productRowPanel.setLayout(new BoxLayout(productRowPanel, BoxLayout.X_AXIS));
			productRowPanel.setBackground(Color.BLACK);
			productRowPanel.setPreferredSize(new Dimension(820, _DEFAULT_PRODUCT_FRAME_HEIGHT));

			productRowPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

			for (int j = 0; j < _PRODUCT_LIMIT_ON_ROW; j++) {
				if (isRemained & i == (rowNumber - 1) & j > (remainder - 1)) {
					break;
				}
				productInfoPanel = new JPanel();
				productInfoPanel.setLayout(new BoxLayout(productInfoPanel, BoxLayout.Y_AXIS));
				productInfoPanel.setBackground(Color.GRAY);
				productInfoPanel.setBounds(j * _DEFAULT_PRODUCT_FRAME_WIDTH, 0, _DEFAULT_PRODUCT_FRAME_WIDTH,
						_DEFAULT_PRODUCT_FRAME_HEIGHT);

				productInfoPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

				/* *********************************** */
				productImagePanel = new JPanel(new BorderLayout());
				productImagePanel.setBackground(new Color(150, 150, 150));
				productImagePanel.setPreferredSize(new Dimension(_DEFAULT_PRODUCT_FRAME_WIDTH, 200));

				productImagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

				ImageIcon imageIcon = new ImageIcon(
						new ImageIcon(productList.get(i * _PRODUCT_LIMIT_ON_ROW + j).GetImagePath()).getImage()
								.getScaledInstance(_DEFAULT_PRODUCT_FRAME_WIDTH, 250, Image.SCALE_DEFAULT));
				productImageLabel = new JLabel();
				productImageLabel.setIcon(imageIcon);
				productImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
				productImageLabel.setVerticalAlignment(SwingConstants.CENTER);

				productImagePanel.add(productImageLabel, BorderLayout.CENTER);

				/* *********************************** */
				productNamePanel = new JPanel(new BorderLayout());
				productNamePanel.setBackground(new Color(100, 100, 100));
				productNamePanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

				productNameLabel = new JLabel(productList.get(i * _PRODUCT_LIMIT_ON_ROW + j).GetName(),
						SwingConstants.LEFT);
				productNameLabel.setFont(new Font("Verdana", Font.BOLD, 12));
				productNameLabel.setForeground(Color.WHITE);

				productNamePanel.add(productNameLabel, BorderLayout.CENTER);

				/* *********************************** */
				productBrandPanel = new JPanel(new BorderLayout());
				productBrandPanel.setBackground(new Color(100, 100, 100));
				productBrandPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

				productBrandLabel = new JLabel(productList.get(i * _PRODUCT_LIMIT_ON_ROW + j).GetBrand(),
						SwingConstants.LEFT);
				productBrandLabel.setFont(new Font("Verdana", Font.BOLD, 16));
				productBrandLabel.setForeground(Color.WHITE);

				productBrandPanel.add(productBrandLabel, BorderLayout.CENTER);

				/* *********************************** */
				productPricePanel = new JPanel(new BorderLayout());
				productPricePanel.setBackground(new Color(100, 100, 100));
				productPricePanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

				productPriceLabel = new JLabel(
						"$" + String.valueOf(productList.get(i * _PRODUCT_LIMIT_ON_ROW + j).GetPrice()),
						SwingConstants.RIGHT);
				productPriceLabel.setFont(new Font("Verdana", Font.BOLD, 16));
				productPriceLabel.setForeground(Color.WHITE);

				productPricePanel.add(productPriceLabel, BorderLayout.CENTER);

				/* *********************************** */
				productButtonPanel = new JPanel(new GridLayout(1, 2));
				productButtonPanel.setBackground(new Color(100, 100, 100));
				productButtonPanel.setPreferredSize(new Dimension(_DEFAULT_PRODUCT_FRAME_WIDTH, 30));

				productAddToCartButton = new JButton("Add to Cart");
				productAddToCartButton.setBackground(new Color(100, 100, 100));

				productAddToCartButton.addActionListener((ActionEvent event) -> {

				});

				productDetailsButton = new JButton("Details");
				productDetailsButton.setBackground(new Color(100, 100, 100));

				productDetailsButton.addActionListener((ActionEvent event) -> {

				});

				productButtonPanel.add(productAddToCartButton);
				productButtonPanel.add(productDetailsButton);

				/* *********************************** */
				productInfoPanel.add(productImagePanel);
				productInfoPanel.add(productNamePanel);
				productInfoPanel.add(productBrandPanel);
				productInfoPanel.add(productPricePanel);
				productInfoPanel.add(productButtonPanel);

				productRowPanel.add(productInfoPanel);
				// productRowPanel.add(Box.createRigidArea(new Dimension(1, 0)));
			}

			contentPanel.add(productRowPanel);
			// contentPanel.add(Box.createRigidArea(new Dimension(0, 1)));
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
