package MVCModel.Realizations.ActorRealizations.Shop;

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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import MVCModel.Controllers.ActorControllers.Shop.IAddProductShopViewController;
import MVCModel.Realizations.AbstractView;
import MVCModel.Views.ActorViews.Shop.IAddProductShopView;
import ThriftMerch.Program;
import Utils.SpringUtilities;

public class AddProductShopView extends AbstractView<IAddProductShopViewController> implements IAddProductShopView {

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

	private JPanel accountTitlePanel, titlePanel, footerPanel, utilsTitlePanel;
	private JPanel productPanel, contentPanel;

	private JButton logoutButton, listButton, addProductButton;

	private JPanel utilsPanel;
	private JButton backButton, homeButton, addButton;

	private JLabel nameLabel, brandLabel, priceLabel, quantityLabel, imagePathLabel, descriptionLabel,
			imageChooserLabel;
	private JTextField nameTextField, brandTextField, priceTextField, quantityTextField;
	private JButton imageChooserButton;
	private JFileChooser fileChooser;
	private JTextArea descriptionTextArea;

	// #endregion

	/* ****************************** */
	// #region Construct Layout Process

	public AddProductShopView(IAddProductShopViewController viewController) throws IOException {
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

		Image addImage = ImageIO.read(new File("Resources/Images/plus.png"));
		Icon addIcon = new ImageIcon(getScaledImage(addImage, 32, 32));
		addProductButton = new JButton(addIcon);
		addProductButton.setBackground(new Color(30, 30, 30));

		addProductButton.addActionListener((ActionEvent e) -> {

		});

		Image listImage = ImageIO.read(new File("Resources/Images/list.png"));
		Icon listIcon = new ImageIcon(getScaledImage(listImage, 32, 32));
		listButton = new JButton(listIcon);
		listButton.setBackground(new Color(30, 30, 30));

		listButton.addActionListener((ActionEvent e) -> {
			getViewController().switchToOrders();
		});

		utilsTitlePanel.add(addProductButton);
		utilsTitlePanel.add(listButton);

		titlePanel.add(accountTitlePanel, BorderLayout.LINE_START);
		titlePanel.add(appTitle, BorderLayout.CENTER);
		titlePanel.add(utilsTitlePanel, BorderLayout.LINE_END);

		/* *********************** */
		// Add product panel
		productPanel = new JPanel(new BorderLayout());
		productPanel.setBackground(new Color(99, 99, 99));

		contentPanel = new JPanel(new SpringLayout());
		// contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setBackground(new Color(99, 99, 99));

		contentPanel.setBorder(BorderFactory.createEmptyBorder(100, 15, 100, 15));

		productPanel.add(contentPanel);

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

		addButton = new JButton("Add");
		addButton.setBackground(new Color(30, 30, 30));
		addButton.setPreferredSize(new Dimension(100, 45));

		addButton.addActionListener((ActionEvent event) -> {

		});

		utilsPanel.add(backButton);
		utilsPanel.add(homeButton);
		utilsPanel.add(addButton);

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
	public void updateAddProductView() {
		contentPanel.removeAll();

		String[] labels = { "Name: ", "Brand: ", "Price: ", "Quantity: ", "Image Path: ", "Descriptions: " };
		int numPairs = labels.length;

		/* *********************************** */
		nameLabel = new JLabel("Name: ", JLabel.TRAILING);

		nameLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		nameTextField = new JTextField(256);
		nameLabel.setLabelFor(nameTextField);
		nameTextField.setFont(new Font("Verdana", Font.PLAIN, 16));

		contentPanel.add(nameLabel);
		contentPanel.add(nameTextField);

		/* *********************************** */
		brandLabel = new JLabel("Brand: ", JLabel.TRAILING);

		brandLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		brandLabel.setForeground(Color.WHITE);
		brandLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		brandTextField = new JTextField(256);
		brandLabel.setLabelFor(brandTextField);
		brandTextField.setFont(new Font("Verdana", Font.PLAIN, 16));

		contentPanel.add(brandLabel);
		contentPanel.add(brandTextField);

		/* *********************************** */
		priceLabel = new JLabel("Price: ", JLabel.TRAILING);

		priceLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		priceLabel.setForeground(Color.WHITE);
		priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		priceTextField = new JTextField(256);
		priceLabel.setLabelFor(priceTextField);
		priceTextField.setFont(new Font("Verdana", Font.PLAIN, 16));

		contentPanel.add(priceLabel);
		contentPanel.add(priceTextField);

		/* *********************************** */
		quantityLabel = new JLabel("Quantity: ", JLabel.TRAILING);

		quantityLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		quantityLabel.setForeground(Color.WHITE);
		quantityLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		quantityTextField = new JTextField(256);
		quantityLabel.setLabelFor(quantityTextField);
		quantityTextField.setFont(new Font("Verdana", Font.PLAIN, 16));

		contentPanel.add(quantityLabel);
		contentPanel.add(quantityTextField);

		/* *********************************** */
		imagePathLabel = new JLabel("Image Path: ", JLabel.TRAILING);

		imagePathLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		imagePathLabel.setForeground(Color.WHITE);
		imagePathLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		fileChooser = new JFileChooser();

		FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image files",
				ImageIO.getReaderFileSuffixes());

		fileChooser.addChoosableFileFilter(imageFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);

		imagePathLabel.setLabelFor(fileChooser);

		JPanel chooserButtonPanel = new JPanel(new BorderLayout());
		chooserButtonPanel.setBackground(new Color(99, 99, 99));

		imageChooserButton = new JButton("Choose file");
		imageChooserButton.setFont(new Font("Verdana", Font.BOLD, 16));
		imageChooserButton.setBackground(new Color(99, 99, 99));

		imageChooserButton.addActionListener((ActionEvent e) -> {
			int returnValue = fileChooser.showSaveDialog(Program.mainFrame);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();

				imageChooserLabel.setText(file.getAbsolutePath());
				imageChooserLabel.setToolTipText(file.getAbsolutePath());

				imageChooserLabel.getParent().validate();
				imageChooserLabel.getParent().repaint();

			} else {
				// Cancel
			}

		});

		imageChooserLabel = new JLabel("");
		imageChooserLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
		imageChooserLabel.setForeground(Color.WHITE);
		imageChooserLabel.setHorizontalAlignment(SwingConstants.LEFT);

		imageChooserLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

		chooserButtonPanel.add(imageChooserButton, BorderLayout.LINE_START);
		chooserButtonPanel.add(imageChooserLabel, BorderLayout.CENTER);

		contentPanel.add(imagePathLabel);
		contentPanel.add(chooserButtonPanel);

		/* *********************************** */
		descriptionLabel = new JLabel("Description: ", JLabel.TRAILING);

		descriptionLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		descriptionLabel.setForeground(Color.WHITE);
		descriptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		descriptionTextArea = new JTextArea(5, 0);
		descriptionTextArea.setLineWrap(true);
		descriptionTextArea.setWrapStyleWord(true);
		descriptionTextArea.setFont(new Font("Verdana", Font.PLAIN, 16));

		JScrollPane descriptionScrollPanel = new JScrollPane(descriptionTextArea);
		descriptionScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		descriptionScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		descriptionScrollPanel.getVerticalScrollBar().setUnitIncrement(16);

		descriptionLabel.setLabelFor(quantityTextField);

		contentPanel.add(descriptionLabel);
		contentPanel.add(descriptionScrollPanel);

		/* *********************************** */
		SpringUtilities.makeCompactGrid(contentPanel, numPairs, 2, // rows, cols
				0, 0, // initX, initY
				5, 5); // xPad, yPad

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
