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

	private int offset;

	// #endregion

	/* ****************************** */
	// #region Swing Components

	private JLabel appTitle, searchLabel;
	private JLabel productNameLabel, productBrandLabel, productPriceLabel, productStatusLabel;
	private JLabel productImageLabel;

	private JScrollPane productScrollPanel;

	private JPanel titlePanel, footerPanel, utilsPanel, accountTitlePanel, utilsTitlePanel;
	private JPanel productPanel, contentPanel, productRowPanel, productInfoPanel;
	private JPanel productImagePanel, productNamePanel, productBrandPanel, productPricePanel, productButtonPanel,
			productStatusPanel;

	private JButton productAddToCartButton, productDetailsButton, productBuyNowButton;

	private JButton leftButton, rightButton, backwardButton, forwardButton;
	private JFormattedTextField pageTextField;
	private JLabel pageRecordLabel;
	NumberFormatter numberFormatter;

	private JTextField searchTextField;
	private JButton searchButton;
	private JButton endSearchButton;

	private JButton cartButton, logoutButton, listButton;

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
		productPanel.setBackground(new Color(0, 0, 0, 150));

		contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setBackground(Color.BLACK);

		// contentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		productScrollPanel = new JScrollPane(contentPanel);
		productScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		productScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		productScrollPanel.getVerticalScrollBar().setUnitIncrement(16);
		// productScrollPanel.setBounds(30, 10, 840, 450);

		// productPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		productPanel.add(productScrollPanel);

		/* *********************** */
		// Add footer with utilities
		footerPanel = new JPanel(new BorderLayout());
		footerPanel.setBackground(new Color(30, 30, 30));
		footerPanel.setPreferredSize(new Dimension(_MODIFIED_SCREEN_WIDTH, 55));

		SpringLayout springUtilsPanelLayout = new SpringLayout();
		utilsPanel = new JPanel(springUtilsPanelLayout);
		utilsPanel.setBackground(new Color(30, 30, 30));

		// utilsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		searchLabel = new JLabel("Search: ");
		searchLabel.setFont(new Font("Verdana", Font.BOLD, 18));
		searchLabel.setForeground(Color.WHITE);
		searchLabel.setHorizontalAlignment(SwingConstants.LEFT);

		springUtilsPanelLayout.putConstraint(SpringLayout.WEST, searchLabel, 45, SpringLayout.WEST, utilsPanel);
		springUtilsPanelLayout.putConstraint(SpringLayout.NORTH, searchLabel, 16, SpringLayout.NORTH, utilsPanel);

		searchTextField = new JTextField(33);
		searchTextField.setFont(new Font("Verdana", Font.PLAIN, 11));

		springUtilsPanelLayout.putConstraint(SpringLayout.WEST, searchTextField, 125, SpringLayout.WEST, utilsPanel);
		springUtilsPanelLayout.putConstraint(SpringLayout.NORTH, searchTextField, 18, SpringLayout.NORTH, utilsPanel);

		searchTextField.addActionListener((ActionEvent e) -> {

		});

		searchButton = new JButton("Search");
		searchButton.setBackground(new Color(30, 30, 30));

		springUtilsPanelLayout.putConstraint(SpringLayout.WEST, searchButton, 497, SpringLayout.WEST, utilsPanel);
		springUtilsPanelLayout.putConstraint(SpringLayout.NORTH, searchButton, 17, SpringLayout.NORTH, utilsPanel);

		searchButton.addActionListener((ActionEvent e) -> {
			String searchText = searchTextField.getText();
			if (!searchText.contentEquals("")) {

				updateSearchProductView(searchText, 1);

				if (endSearchButton == null) {
					endSearchButton = new JButton("Clear");
					endSearchButton.setBackground(new Color(30, 30, 30));

					springUtilsPanelLayout.putConstraint(SpringLayout.WEST, endSearchButton, 565, SpringLayout.WEST,
							utilsPanel);
					springUtilsPanelLayout.putConstraint(SpringLayout.NORTH, endSearchButton, 17, SpringLayout.NORTH,
							utilsPanel);

					endSearchButton.addActionListener((ActionEvent endEvent) -> {
						resetView();
					});

					utilsPanel.add(endSearchButton);
					utilsPanel.getParent().validate();
					utilsPanel.getParent().repaint();
				}
			} else {
				resetView();
			}

		});

		utilsPanel.add(searchLabel);
		utilsPanel.add(searchTextField);
		utilsPanel.add(searchButton);

		/* ****************************************************** */
		// Pagination
		Image leftImage = ImageIO.read(new File("Resources/Images/left-arrow.png"));
		Icon leftIcon = new ImageIcon(getScaledImage(leftImage, 12, 12));
		leftButton = new JButton(leftIcon);
		springUtilsPanelLayout.putConstraint(SpringLayout.WEST, leftButton, 665, SpringLayout.WEST, utilsPanel);
		springUtilsPanelLayout.putConstraint(SpringLayout.NORTH, leftButton, 18, SpringLayout.NORTH, utilsPanel);

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
		springUtilsPanelLayout.putConstraint(SpringLayout.WEST, rightButton, 735, SpringLayout.WEST, utilsPanel);
		springUtilsPanelLayout.putConstraint(SpringLayout.NORTH, rightButton, 18, SpringLayout.NORTH, utilsPanel);

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
		springUtilsPanelLayout.putConstraint(SpringLayout.WEST, backwardButton, 640, SpringLayout.WEST, utilsPanel);
		springUtilsPanelLayout.putConstraint(SpringLayout.NORTH, backwardButton, 18, SpringLayout.NORTH, utilsPanel);

		backwardButton.setPreferredSize(new Dimension(20, 20));
		backwardButton.setBackground(new Color(30, 30, 30));

		backwardButton.addActionListener((ActionEvent e) -> {
			pageTextField.setText(pageSize == 0 ? String.valueOf(0) : String.valueOf(1));

			try {
				pageTextField.commitEdit();
			} catch (ParseException exception) {
				exception.printStackTrace();
			}
		});

		Image forwardImage = ImageIO.read(new File("Resources/Images/forward.png"));
		Icon forwardIcon = new ImageIcon(getScaledImage(forwardImage, 12, 12));
		forwardButton = new JButton(forwardIcon);
		springUtilsPanelLayout.putConstraint(SpringLayout.WEST, forwardButton, 760, SpringLayout.WEST, utilsPanel);
		springUtilsPanelLayout.putConstraint(SpringLayout.NORTH, forwardButton, 18, SpringLayout.NORTH, utilsPanel);

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
		numberFormatter = new NumberFormatter(numberFormat);

		numberFormatter.setValueClass(Integer.class);
		numberFormatter.setMinimum(1);
		numberFormatter.setMaximum(pageSize);
		numberFormatter.setAllowsInvalid(true);
		numberFormatter.setOverwriteMode(false);

		pageTextField = new JFormattedTextField(numberFormatter);
		pageTextField.setFont(new Font("Verdana", Font.PLAIN, 11));

		springUtilsPanelLayout.putConstraint(SpringLayout.WEST, pageTextField, 690, SpringLayout.WEST, utilsPanel);
		springUtilsPanelLayout.putConstraint(SpringLayout.NORTH, pageTextField, 18, SpringLayout.NORTH, utilsPanel);

		pageTextField.setValue(new Integer(1));
		pageTextField.setColumns(3);

		pageTextField.addPropertyChangeListener("value", (PropertyChangeEvent e) -> {
			Object source = e.getSource();
			if (source == pageTextField) {
				try {
					pageTextField.commitEdit();
					if (endSearchButton != null) {
						updateSearchProductView(searchTextField.getText(), (Integer) pageTextField.getValue());
					} else {
						updateProductView((Integer) pageTextField.getValue());
					}
				} catch (ParseException exception) {
					exception.printStackTrace();
				}
			}
		});

		pageRecordLabel = new JLabel("of " + String.valueOf(pageSize));
		springUtilsPanelLayout.putConstraint(SpringLayout.WEST, pageRecordLabel, 785, SpringLayout.WEST, utilsPanel);
		springUtilsPanelLayout.putConstraint(SpringLayout.NORTH, pageRecordLabel, 18, SpringLayout.NORTH, utilsPanel);

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
		this.offset = offset;

		productList = DataHandler.GetInstance().GetAllProducts(_PRODUCT_LIMIT_ON_PAGE, offset);
		repaintContentPanel(productList);
	}

	@Override
	public void updateSearchProductView(String products, int offset) {
		this.offset = offset;

		productList = DataHandler.GetInstance().SearchProducts(products, _PRODUCT_LIMIT_ON_PAGE,
				offset == 0 ? 1 : offset);
		pageSize = DataHandler.GetInstance().GetPageNumberSearch(products, _PRODUCT_LIMIT_ON_PAGE);

		numberFormatter.setMinimum(pageSize == 0 ? 0 : 1);
		numberFormatter.setMaximum(pageSize);
		pageRecordLabel.setText("of " + pageSize);
		pageTextField.setText(pageSize == 0 ? String.valueOf(0) : String.valueOf(offset));

		repaintContentPanel(productList);
		footerPanel.getParent().validate();
		footerPanel.getParent().repaint();
	}

	@Override
	public void repaintContentPanel(ArrayList<Product> productList) {
		contentPanel.removeAll();
		/* ****************************************************** */
		if (productList == null) {
			contentPanel.getParent().validate();
			contentPanel.getParent().repaint();
			return;
		}

		/* ****************************************************** */
		// Add Content Panel
		int _DEFAULT_PRODUCT_FRAME_WIDTH = 215;
		int _DEFAULT_PRODUCT_FRAME_HEIGHT = 450;

		int availableProductSize = productList.size();
		int rowNumber = (int) Math.ceil(availableProductSize / (float) _PRODUCT_LIMIT_ON_ROW);

		boolean isRemained = (availableProductSize % _PRODUCT_LIMIT_ON_ROW != 0);
		int remainder = 0;
		if (isRemained) {
			remainder = availableProductSize % _PRODUCT_LIMIT_ON_ROW;
		}

		for (int i = 0; i < rowNumber; i++) {
			productRowPanel = new JPanel(new GridLayout(1, _PRODUCT_LIMIT_ON_ROW));
			productRowPanel.setBackground(Color.BLACK);

			Dimension dim = productPanel.getPreferredSize();
			productRowPanel.setPreferredSize(new Dimension(dim.width, _DEFAULT_PRODUCT_FRAME_HEIGHT));

			productRowPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

			for (int j = 0; j < _PRODUCT_LIMIT_ON_ROW; j++) {
				if (isRemained & i == (rowNumber - 1) & j > (remainder - 1)) {
					productInfoPanel = new JPanel();
					productInfoPanel.setBackground(Color.GRAY);

					productInfoPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

					productRowPanel.add(productInfoPanel);
					continue;
				}
				productInfoPanel = new JPanel();
				productInfoPanel.setLayout(new BoxLayout(productInfoPanel, BoxLayout.Y_AXIS));
				productInfoPanel.setBackground(Color.GRAY);

				productInfoPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

				/* *********************************** */
				productImagePanel = new JPanel(new BorderLayout());
				productImagePanel.setBackground(new Color(150, 150, 150));

				Dimension frameDim = productInfoPanel.getPreferredSize();
				productImagePanel.setPreferredSize(new Dimension(frameDim.width, 200));

				productImagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

				ImageIcon imageIcon = new ImageIcon(
						new ImageIcon(productList.get(i * _PRODUCT_LIMIT_ON_ROW + j).GetImagePath()).getImage()
								.getScaledInstance(_DEFAULT_PRODUCT_FRAME_WIDTH, 220, Image.SCALE_DEFAULT));
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
				productNameLabel.setToolTipText(productList.get(i * _PRODUCT_LIMIT_ON_ROW + j).GetName());

				productNamePanel.add(productNameLabel, BorderLayout.CENTER);

				/* *********************************** */
				productBrandPanel = new JPanel(new BorderLayout());
				productBrandPanel.setBackground(new Color(100, 100, 100));
				productBrandPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

				productBrandLabel = new JLabel(productList.get(i * _PRODUCT_LIMIT_ON_ROW + j).GetBrand(),
						SwingConstants.LEFT);
				productBrandLabel.setFont(new Font("Verdana", Font.BOLD, 16));
				productBrandLabel.setForeground(Color.WHITE);
				productBrandLabel.setToolTipText(productList.get(i * _PRODUCT_LIMIT_ON_ROW + j).GetBrand());

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
				productStatusPanel = new JPanel(new BorderLayout());
				productStatusPanel.setBackground(new Color(100, 100, 100));
				productStatusPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

				int quantity = productList.get(i * _PRODUCT_LIMIT_ON_ROW + j).GetQuantity();
				// int quantity = 0;

				if (quantity > 0) {
					productStatusLabel = new JLabel("In-stock: " + String.valueOf(quantity), SwingConstants.LEFT);
					productStatusLabel.setFont(new Font("Verdana", Font.BOLD, 16));
					productStatusLabel.setForeground(Color.WHITE);
				} else {
					productStatusLabel = new JLabel("SOLD OUT");
					productStatusLabel.setFont(new Font("Verdana", Font.BOLD, 16));
					productStatusLabel.setForeground(Color.RED);
				}

				productStatusPanel.add(productStatusLabel);

				/* *********************************** */
				int row = i, col = j;

				productButtonPanel = new JPanel(new GridLayout(3, 1));
				productButtonPanel.setBackground(new Color(100, 100, 100));

				frameDim = productInfoPanel.getPreferredSize();
				productButtonPanel.setPreferredSize(new Dimension(frameDim.width, 100));

				productAddToCartButton = new JButton("Add to Cart");
				productAddToCartButton.setBackground(new Color(100, 100, 100));

				if (Program.actor.IsInCart(productList.get(i * _PRODUCT_LIMIT_ON_ROW + j).GetId())) {
					productAddToCartButton.setEnabled(false);
				} else {
					/* Put here for fun, don't mind */
					JButton currentButton = productAddToCartButton;
					productAddToCartButton.addActionListener((ActionEvent event) -> {
						try {
							getViewController().addToCart(productList.get(row * _PRODUCT_LIMIT_ON_ROW + col),
									(isSuccess) -> {
										currentButton.setEnabled(!isSuccess);
									});
						} catch (IOException e) {
							e.printStackTrace();
						}
					});
				}

				productBuyNowButton = new JButton("Buy now");
				productBuyNowButton.setBackground(new Color(100, 100, 100));

				JLabel productStatus = productStatusLabel;
				productBuyNowButton.addActionListener((ActionEvent event) -> {
					// BuyNowEvent();
					try {
						getViewController().BuyNow(productList.get(row * _PRODUCT_LIMIT_ON_ROW + col), (isSuccess) -> {
							int quantity1 = productList.get(row * _PRODUCT_LIMIT_ON_ROW + col).GetQuantity() - 1;
							if (isSuccess) {
								productList.get(row * _PRODUCT_LIMIT_ON_ROW + col).SetQuantity(quantity1);
							}
							if (quantity1 <= 0) {
								productStatus.setText("SOLD OUT");
								productStatus.setForeground(Color.RED);
							} else {
								productStatus.setText("In-stock: " + quantity1);
							}
						});
					} catch (IOException e) {
						e.printStackTrace();
					}
				});

				productDetailsButton = new JButton("Details");
				productDetailsButton.setBackground(new Color(100, 100, 100));

				productDetailsButton.addActionListener((ActionEvent event) -> {
					getViewController().switchToDetails(DataHandler.GetInstance()
							.GetProduct(productList.get(row * _PRODUCT_LIMIT_ON_ROW + col).GetId()));
				});

				productButtonPanel.add(productAddToCartButton);
				productButtonPanel.add(productBuyNowButton);
				productButtonPanel.add(productDetailsButton);

				/* *********************************** */
				productInfoPanel.add(productImagePanel);
				productInfoPanel.add(productNamePanel);
				productInfoPanel.add(productBrandPanel);
				productInfoPanel.add(productPricePanel);
				productInfoPanel.add(productStatusPanel);
				productInfoPanel.add(productButtonPanel);

				productRowPanel.add(productInfoPanel);
			}

			contentPanel.add(productRowPanel);
		}

		contentPanel.getParent().validate();
		contentPanel.getParent().repaint();
	}

	@Override
	public void UpdateCurrentView() {
		if (endSearchButton != null) {
			updateSearchProductView(searchTextField.getText(), getCurrentOffset());
		} else {
			updateProductView(getCurrentOffset());
		}
	}

	@Override
	public void resetView() {
		pageSize = DataHandler.GetInstance().GetPageNumber(_PRODUCT_LIMIT_ON_PAGE);
		numberFormatter.setMinimum(1);
		numberFormatter.setMaximum(pageSize);

		pageRecordLabel.setText("of " + pageSize);
		pageTextField.setText("1");
		searchTextField.setText("");

		if (endSearchButton != null) {
			utilsPanel.remove(endSearchButton);
			endSearchButton = null;
		}
		updateProductView(1);
		utilsPanel.getParent().validate();
		utilsPanel.getParent().repaint();
	}

	@Override
	public int getCurrentOffset() {
		return this.offset;
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
