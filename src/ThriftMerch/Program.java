package ThriftMerch;

import DataController.ORDERSTATUS;
import MVCModel.Controllers.ILoginViewController;
import MVCModel.Controllers.IMainMenuViewController;
import MVCModel.Controllers.ISignUpViewController;
import MVCModel.Controllers.ActorControllers.Customer.ICartCustomerViewController;
import MVCModel.Controllers.ActorControllers.Customer.IDefaultCustomerViewController;
import MVCModel.Controllers.ActorControllers.Customer.IDetailsCustomerViewController;
import MVCModel.Controllers.ActorControllers.Customer.IOrderDetailsCustomerViewController;
import MVCModel.Controllers.ActorControllers.Customer.IOrdersCustomerViewController;
import MVCModel.Controllers.ActorControllers.Shipper.IDefaultShipperViewController;
import MVCModel.Controllers.ActorControllers.Shipper.IOrderDetailsShipperViewController;
import MVCModel.Controllers.ActorControllers.Shipper.IOrdersShipperViewController;
import MVCModel.Controllers.ActorControllers.Shop.IAddProductShopViewController;
import MVCModel.Controllers.ActorControllers.Shop.IDefaultShopViewController;
import MVCModel.Controllers.ActorControllers.Shop.IOrderDetailsShopViewController;
import MVCModel.Controllers.ActorControllers.Shop.IOrdersShopViewController;
import MVCModel.Controllers.ActorControllers.Shop.IProductDetailsShopViewController;
import MVCModel.Realizations.LoginView;
import MVCModel.Realizations.MainMenuView;
import MVCModel.Realizations.SignUpView;
import MVCModel.Realizations.ActorRealizations.Customer.CartCustomerView;
import MVCModel.Realizations.ActorRealizations.Customer.DefaultCustomerView;
import MVCModel.Realizations.ActorRealizations.Customer.DetailsCustomerView;
import MVCModel.Realizations.ActorRealizations.Customer.OrderDetailsCustomerView;
import MVCModel.Realizations.ActorRealizations.Customer.OrdersCustomerView;
import MVCModel.Realizations.ActorRealizations.Shipper.DefaultShipperView;
import MVCModel.Realizations.ActorRealizations.Shipper.OrderDetailsShipperView;
import MVCModel.Realizations.ActorRealizations.Shipper.OrdersShipperView;
import MVCModel.Realizations.ActorRealizations.Shop.AddProductShopView;
import MVCModel.Realizations.ActorRealizations.Shop.DefaultShopView;
import MVCModel.Realizations.ActorRealizations.Shop.OrderDetailsShopView;
import MVCModel.Realizations.ActorRealizations.Shop.OrdersShopView;
import MVCModel.Realizations.ActorRealizations.Shop.ProductDetailsShopView;
import MVCModel.Views.ILoginView;
import MVCModel.Views.IMainMenuView;
import MVCModel.Views.ISignUpView;
import MVCModel.Views.ActorViews.Customer.ICartCustomerView;
import MVCModel.Views.ActorViews.Customer.IDefaultCustomerView;
import MVCModel.Views.ActorViews.Customer.IDetailsCustomerView;
import MVCModel.Views.ActorViews.Customer.IOrderDetailsCustomerView;
import MVCModel.Views.ActorViews.Customer.IOrdersCustomerView;
import MVCModel.Views.ActorViews.Shipper.IDefaultShipperView;
import MVCModel.Views.ActorViews.Shipper.IOrderDetailsShipperView;
import MVCModel.Views.ActorViews.Shipper.IOrdersShipperView;
import MVCModel.Views.ActorViews.Shop.IAddProductShopView;
import MVCModel.Views.ActorViews.Shop.IDefaultShopView;
import MVCModel.Views.ActorViews.Shop.IOrderDetailsShopView;
import MVCModel.Views.ActorViews.Shop.IOrdersShopView;
import MVCModel.Views.ActorViews.Shop.IProductDetailsShopView;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.*;

import java.lang.ClassNotFoundException;
import java.lang.InstantiationException;
import java.lang.IllegalAccessException;
import java.util.function.Consumer;

import Actor.*;
import DataController.Order;
import DataController.Product;

public class Program extends JPanel {

	private static final long serialVersionUID = 1L;

	/* **************************************** */
	// #region Public Fields
	public static JFrame mainFrame;
	public static final String comName = "THRIFTMERCH";

	public static final int _DEFAULT_SCREEN_WIDTH = 616;
	public static final int _DEFAULT_SCREEN_HEIGHT = 452;

	public static Actor actor;

	// #endregion

	/* **************************************** */
	// #region Main Panel
	public class MainPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		/* **************************************** */
		// #region Private Fields
		// #region Scene Definition
		protected static final String MAINMENU_VIEW = "View.mainmenu";
		protected static final String LOGIN_VIEW = "View.login";
		protected static final String SIGNUP_VIEW = "View.signup";
		protected static final String DEFAULTCUSTOMER_VIEW = "View.customer.default";
		protected static final String DEFAULTSHOP_VIEW = "View.shop.default";
		protected static final String DEFAULTSHIPPER_VIEW = "View.shipper.default";

		protected static final String ADDPRODUCTSHOP_VIEW = "View.shop.add.product";
		protected static final String PRODUCTDETAILSSHOP_VIEW = "View.shop.product.details";
		protected static final String ORDERSSHOP_VIEW = "View.shop.orders";
		protected static final String ORDERDETAILSSHOP_VIEW = "View.shop.order.details";

		protected static final String DETAILSCUSTOMER_VIEW = "View.customer.details";
		protected static final String CARTCUSTOMER_VIEW = "View.customer.cart";
		protected static final String ORDERSCUSTOMER_VIEW = "View.customer.orders";
		protected static final String ORDERDETAILSCUSTOMER_VIEW = "View.customer.order.details";

		protected static final String ORDERDETAILSSHIPPER_VIEW = "View.shipper.order.details";
		protected static final String ORDERSSHIPPER_VIEW = "View.shipper.orders";

		// #endregion
		private CardLayout cardLayout;
		private IMainMenuView mainMenuView;
		private ILoginView loginView;
		private ISignUpView signUpView;

		private IDefaultShopView defaultShopView;
		private IDefaultCustomerView defaultCustomerView;
		private IDefaultShipperView defaultShipperView;

		private IAddProductShopView addProductShopView;
		private IProductDetailsShopView productDetailsShopView;
		private IOrdersShopView ordersShopView;
		private IOrderDetailsShopView orderDetailsShopView;

		private IDetailsCustomerView detailsCustomerView;
		private ICartCustomerView cartCustomerView;
		private IOrdersCustomerView ordersCustomerView;
		private IOrderDetailsCustomerView orderDetailsCustomerView;

		private IOrderDetailsShipperView orderDetailsShipperView;
		private IOrdersShipperView ordersShipperView;

		// #endregion

		/* **************************************** */
		// #region Constructors
		public MainPanel() throws IOException {
			cardLayout = new CardLayout();
			setLayout(cardLayout);

			mainMenuView = new MainMenuView(new MainMenuViewController());
			loginView = new LoginView(new LoginViewController());
			signUpView = new SignUpView(new SignUpViewController());

			defaultShopView = new DefaultShopView(new DefaultShopViewController());
			defaultCustomerView = new DefaultCustomerView(new DefaultCustomerViewController());
			defaultShipperView = new DefaultShipperView(new DefaultShipperViewController());

			addProductShopView = new AddProductShopView(new AddProductShopViewController());
			productDetailsShopView = new ProductDetailsShopView(new ProductDetailsShopViewController());
			ordersShopView = new OrdersShopView(new OrdersShopViewController());
			orderDetailsShopView = new OrderDetailsShopView(new OrderDetailsShopViewController());

			detailsCustomerView = new DetailsCustomerView(new DetailsCustomerViewController());
			cartCustomerView = new CartCustomerView(new CartCustomerViewController());
			ordersCustomerView = new OrdersCustomerView(new OrdersCustomerViewController());
			orderDetailsCustomerView = new OrderDetailsCustomerView(new OrderDetailsCustomerViewController());

			orderDetailsShipperView = new OrderDetailsShipperView(new OrderDetailsShipperViewController());
			ordersShipperView = new OrdersShipperView(new OrdersShipperViewController());

			add(mainMenuView.getView(), MAINMENU_VIEW);
			add(loginView.getView(), LOGIN_VIEW);
			add(signUpView.getView(), SIGNUP_VIEW);

			add(defaultShopView.getView(), DEFAULTSHOP_VIEW);
			add(defaultCustomerView.getView(), DEFAULTCUSTOMER_VIEW);
			add(defaultShipperView.getView(), DEFAULTSHIPPER_VIEW);

			add(addProductShopView.getView(), ADDPRODUCTSHOP_VIEW);
			add(productDetailsShopView.getView(), PRODUCTDETAILSSHOP_VIEW);
			add(ordersShopView.getView(), ORDERSSHOP_VIEW);
			add(orderDetailsShopView.getView(), ORDERDETAILSSHOP_VIEW);

			add(detailsCustomerView.getView(), DETAILSCUSTOMER_VIEW);
			add(cartCustomerView.getView(), CARTCUSTOMER_VIEW);
			add(ordersCustomerView.getView(), ORDERSCUSTOMER_VIEW);
			add(orderDetailsCustomerView.getView(), ORDERDETAILSCUSTOMER_VIEW);

			add(orderDetailsShipperView.getView(), ORDERDETAILSSHIPPER_VIEW);
			add(ordersShipperView.getView(), ORDERSSHIPPER_VIEW);

			cardLayout.show(this, MAINMENU_VIEW);
		}

		// #endregion

		/* **************************************** */
		// #region Controllers Implementation
		protected class MainMenuViewController implements IMainMenuViewController {

			@Override
			public void switchToSignIn() {
				cardLayout.show(MainPanel.this, LOGIN_VIEW);
			}

			@Override
			public void switchToSignUp() {
				cardLayout.show(MainPanel.this, SIGNUP_VIEW);
			}

			@Override
			public void exitProgram() {
				SwingUtilities.windowForComponent(MainPanel.this).dispose();
			}

		}

		protected class LoginViewController implements ILoginViewController {

			@Override
			public void switchToMainMenu() {
				cardLayout.show(MainPanel.this, MAINMENU_VIEW);
			}

			@Override
			public void signinSuccessful(Actor actor) {
				mainFrame.setSize(new Dimension(DefaultCustomerView._MODIFIED_SCREEN_WIDTH,
						DefaultCustomerView._MODIFIED_SCREEN_HEIGHT));
				mainFrame.setLocationRelativeTo(null);

				switch (actor.GetActorType()) {
				case SHOP:
					defaultShopView.getViewController().setActor(actor);

					defaultShopView.resetView();

					cardLayout.show(MainPanel.this, DEFAULTSHOP_VIEW);
					break;
				case CUSTOMER:
					defaultCustomerView.getViewController().setActor(actor);

					defaultCustomerView.resetView();
					cartCustomerView.ResetView();
					ordersCustomerView.updateViewOrder();

					cardLayout.show(MainPanel.this, DEFAULTCUSTOMER_VIEW);
					break;
				case SHIPPER:
					defaultShipperView.getViewController().setActor(actor);

					defaultShipperView.updateOrderView();

					cardLayout.show(MainPanel.this, DEFAULTSHIPPER_VIEW);
					break;
				default:
					break;
				}
			}

			@Override
			public void signinFailed(String message) {
				JOptionPane.showMessageDialog(mainFrame, message, "Login Failed", JOptionPane.ERROR_MESSAGE);
			}

		}

		protected class SignUpViewController implements ISignUpViewController {

			@Override
			public void switchToMainMenu() {
				cardLayout.show(MainPanel.this, MAINMENU_VIEW);
			}

			@Override
			public void signupSuccessful(Actor actor) {
				JOptionPane.showMessageDialog(mainFrame, "Sign up successfully", "Signup Failed",
						JOptionPane.INFORMATION_MESSAGE);
			}

			@Override
			public void signupFailed(String message) {
				JOptionPane.showMessageDialog(mainFrame, message, "Signup Failed", JOptionPane.ERROR_MESSAGE);
			}

		}

		protected class DefaultShopViewController implements IDefaultShopViewController {

			@Override
			public void setActor(Actor actor) {
				Program.actor = actor;
			}

			@Override
			public void switchToMainMenu() {
				Program.actor = null;
				mainFrame.setSize(new Dimension(_DEFAULT_SCREEN_WIDTH, _DEFAULT_SCREEN_HEIGHT));
				mainFrame.setLocationRelativeTo(null);
				cardLayout.show(MainPanel.this, MAINMENU_VIEW);
			}

			@Override
			public void switchToAddProduct() {
				addProductShopView.getViewController().setPreviousView(DEFAULTSHOP_VIEW);

				addProductShopView.updateAddProductView();

				cardLayout.show(MainPanel.this, ADDPRODUCTSHOP_VIEW);
			}

			@Override
			public void switchToOrders() {
				ordersShopView.getViewController().setPreviousView(DEFAULTSHOP_VIEW);

				ordersShopView.updateOrderView();

				cardLayout.show(MainPanel.this, ORDERSSHOP_VIEW);
			}

			@Override
			public void switchToDetails(Product product) {
				productDetailsShopView.getViewController().setPreviousView(DEFAULTSHOP_VIEW);

				productDetailsShopView.updateProductDetailsView(product);

				cardLayout.show(MainPanel.this, PRODUCTDETAILSSHOP_VIEW);
			}

		}

		protected class OrdersShopViewController implements IOrdersShopViewController {

			private String previousView = "";

			@Override
			public void setPreviousView(String view) {
				this.previousView = view;
			}

			@Override
			public void switchToPreviousView() {
				cardLayout.show(MainPanel.this, previousView);
			}

			@Override
			public void switchToDefault() {
				cardLayout.show(MainPanel.this, DEFAULTSHOP_VIEW);
			}

			@Override
			public void switchToMainMenu() {
				Program.actor = null;
				mainFrame.setSize(new Dimension(_DEFAULT_SCREEN_WIDTH, _DEFAULT_SCREEN_HEIGHT));
				mainFrame.setLocationRelativeTo(null);
				cardLayout.show(MainPanel.this, MAINMENU_VIEW);
			}

			@Override
			public void switchToAddProduct() {
				addProductShopView.getViewController().setPreviousView(ORDERSSHOP_VIEW);

				addProductShopView.updateAddProductView();

				cardLayout.show(MainPanel.this, ADDPRODUCTSHOP_VIEW);
			}

			@Override
			public void switchToDetails(Order order) {
				orderDetailsShopView.getViewController().setPreviousView(ORDERSSHOP_VIEW);

				orderDetailsShopView.updateMainOrderDetailsView(order);

				cardLayout.show(MainPanel.this, ORDERDETAILSSHOP_VIEW);
			}

		}

		protected class OrderDetailsShopViewController implements IOrderDetailsShopViewController {

			private String previousView = "";

			@Override
			public void setPreviousView(String view) {
				this.previousView = view;
			}

			@Override
			public void switchToPreviousView() {
				cardLayout.show(MainPanel.this, previousView);
			}

			@Override
			public void switchToDefault() {
				cardLayout.show(MainPanel.this, DEFAULTSHOP_VIEW);
			}

			@Override
			public void switchToMainMenu() {
				Program.actor = null;
				mainFrame.setSize(new Dimension(_DEFAULT_SCREEN_WIDTH, _DEFAULT_SCREEN_HEIGHT));
				mainFrame.setLocationRelativeTo(null);
				cardLayout.show(MainPanel.this, MAINMENU_VIEW);
			}

			@Override
			public void switchToAddProduct() {
				addProductShopView.getViewController().setPreviousView(ORDERDETAILSSHOP_VIEW);

				addProductShopView.updateAddProductView();

				cardLayout.show(MainPanel.this, ADDPRODUCTSHOP_VIEW);
			}

			@Override
			public void switchToOrders() {
				ordersShopView.updateOrderView();

				cardLayout.show(MainPanel.this, ORDERSSHOP_VIEW);
			}

		}

		protected class AddProductShopViewController implements IAddProductShopViewController {

			private String previousView = "";

			@Override
			public void setPreviousView(String view) {
				this.previousView = view;
			}

			@Override
			public void switchToPreviousView() {
				cardLayout.show(MainPanel.this, previousView);
			}

			@Override
			public void switchToDefault() {
				cardLayout.show(MainPanel.this, DEFAULTSHOP_VIEW);
			}

			@Override
			public void switchToMainMenu() {
				Program.actor = null;
				mainFrame.setSize(new Dimension(_DEFAULT_SCREEN_WIDTH, _DEFAULT_SCREEN_HEIGHT));
				mainFrame.setLocationRelativeTo(null);
				cardLayout.show(MainPanel.this, MAINMENU_VIEW);
			}

			@Override
			public void switchToOrders() {
				ordersShopView.getViewController().setPreviousView(ADDPRODUCTSHOP_VIEW);

				ordersShopView.updateOrderView();

				cardLayout.show(MainPanel.this, ORDERSSHOP_VIEW);
			}

		}

		protected class ProductDetailsShopViewController implements IProductDetailsShopViewController {

			private String previousView = "";

			@Override
			public void setPreviousView(String view) {
				this.previousView = view;
			}

			@Override
			public void switchToPreviousView() {
				cardLayout.show(MainPanel.this, previousView);
			}

			@Override
			public void switchToDefault() {
				cardLayout.show(MainPanel.this, DEFAULTSHOP_VIEW);
			}

			@Override
			public void switchToMainMenu() {
				Program.actor = null;
				mainFrame.setSize(new Dimension(_DEFAULT_SCREEN_WIDTH, _DEFAULT_SCREEN_HEIGHT));
				mainFrame.setLocationRelativeTo(null);
				cardLayout.show(MainPanel.this, MAINMENU_VIEW);
			}

			@Override
			public void switchToAddProduct() {
				addProductShopView.getViewController().setPreviousView(PRODUCTDETAILSSHOP_VIEW);

				addProductShopView.updateAddProductView();

				cardLayout.show(MainPanel.this, ADDPRODUCTSHOP_VIEW);
			}

			@Override
			public void switchToOrders() {
				ordersShopView.getViewController().setPreviousView(PRODUCTDETAILSSHOP_VIEW);

				ordersShopView.updateOrderView();

				cardLayout.show(MainPanel.this, ORDERSSHOP_VIEW);
			}

		}

		protected class DefaultCustomerViewController implements IDefaultCustomerViewController {

			@Override
			public void setActor(Actor actor) {
				Program.actor = actor;
			}

			@Override
			public void switchToDetails(Product product) {
				detailsCustomerView.getViewController().SetCurrentProductId(product.GetId());
				detailsCustomerView.updateProductView(product);
				cardLayout.show(MainPanel.this, DETAILSCUSTOMER_VIEW);
			}

			@Override
			public void switchToMainMenu() {
				Program.actor = null;
				mainFrame.setSize(new Dimension(_DEFAULT_SCREEN_WIDTH, _DEFAULT_SCREEN_HEIGHT));
				mainFrame.setLocationRelativeTo(null);
				cardLayout.show(MainPanel.this, MAINMENU_VIEW);
			}

			@Override
			public void switchToCart() throws IOException {
				cartCustomerView.getViewController().setPreviousView(DEFAULTCUSTOMER_VIEW);
				cardLayout.show(MainPanel.this, CARTCUSTOMER_VIEW);
			}

			@Override
			public void switchToOrders() {
				ordersCustomerView.getViewController().setPreviousView(DEFAULTCUSTOMER_VIEW);
				cardLayout.show(MainPanel.this, ORDERSCUSTOMER_VIEW);
			}

			@Override
			public void addToCart(Product product, Consumer<Boolean> callback) throws IOException {
				boolean isSuccess = actor.AddToCart(product.GetId(), 1, (String message) -> {
					JOptionPane.showMessageDialog(Program.mainFrame, message, "Error", JOptionPane.ERROR_MESSAGE);
				});
				if (isSuccess) {
					cartCustomerView.updateViewCart();
				}
				callback.accept(isSuccess);
			}

			@Override
			public void BuyNow(Product product, Consumer<Boolean> callback) throws IOException {
				int input = JOptionPane.showOptionDialog(null, "Buy this item immediately?", "ARE YOU SURE?",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				boolean isBuySuccess;
				if (input == 0) {
					isBuySuccess = Program.actor.BuyNow(product.GetId(), 1, Program.this::DisplayError);
					if (isBuySuccess) {
						cartCustomerView.updateViewCart();
						ordersCustomerView.updateViewOrder();
						JOptionPane.showMessageDialog(null, "Buy successfully ^.^!", "Success!",
								JOptionPane.INFORMATION_MESSAGE);
					}

					callback.accept(isBuySuccess);

				}
			}
		}

		protected class DetailsCustomerViewController implements IDetailsCustomerViewController {
			private String currentProductId;

			@Override
			public void switchToDefault() {
				cardLayout.show(MainPanel.this, DEFAULTCUSTOMER_VIEW);
			}

			@Override
			public void switchToMainMenu() {
				Program.actor = null;
				mainFrame.setSize(new Dimension(_DEFAULT_SCREEN_WIDTH, _DEFAULT_SCREEN_HEIGHT));
				mainFrame.setLocationRelativeTo(null);
				cardLayout.show(MainPanel.this, MAINMENU_VIEW);
			}

			@Override
			public void switchToCart() throws IOException {
				cartCustomerView.getViewController().setPreviousView(DETAILSCUSTOMER_VIEW);
				cardLayout.show(MainPanel.this, CARTCUSTOMER_VIEW);
			}

			@Override
			public void switchToOrders() {
				ordersCustomerView.getViewController().setPreviousView(DETAILSCUSTOMER_VIEW);
				cardLayout.show(MainPanel.this, ORDERSCUSTOMER_VIEW);
			}

			@Override
			public void addToCart(Product product, Consumer<Boolean> callback) throws IOException {
				boolean isSuccess = actor.AddToCart(product.GetId(), 1, (String message) -> {
					JOptionPane.showMessageDialog(Program.mainFrame, message, "Error", JOptionPane.ERROR_MESSAGE);
				});
				if (isSuccess) {
					defaultCustomerView.UpdateCurrentView();
					cartCustomerView.updateViewCart();
				}
				callback.accept(isSuccess);
			}

			@Override
			public void BuyNow(Product product, Consumer<Boolean> callback) throws IOException {
				int input = JOptionPane.showOptionDialog(null, "Buy this item immediately?", "ARE YOU SURE?",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				boolean isBuySuccess;
				if (input == 0) {
					isBuySuccess = Program.actor.BuyNow(product.GetId(), 1, Program.this::DisplayError);
					if (isBuySuccess) {
						cartCustomerView.updateViewCart();
						defaultCustomerView.UpdateCurrentView();
						ordersCustomerView.updateViewOrder();
						JOptionPane.showMessageDialog(null, "Buy successfully ^.^!", "Success!",
								JOptionPane.INFORMATION_MESSAGE);
					}
					callback.accept(isBuySuccess);
				}
			}

			@Override
			public void SetCurrentProductId(String productId) {
				currentProductId = productId;
			}

			@Override
			public String GetCurrentProductId() {
				return currentProductId;
			}

		}

		protected class CartCustomerViewController implements ICartCustomerViewController {

			private String previousView = "";
			private int totalProductsPrice = 0;

			@Override
			public void setPreviousView(String view) {
				this.previousView = view;
			}

			@Override
			public void switchToPreviousView() {
				cardLayout.show(MainPanel.this, previousView);
			}

			@Override
			public void switchToDefault() {
				cardLayout.show(MainPanel.this, DEFAULTCUSTOMER_VIEW);
			}

			@Override
			public void switchToMainMenu() {
				Program.actor = null;
				totalProductsPrice = 0;
				mainFrame.setSize(new Dimension(_DEFAULT_SCREEN_WIDTH, _DEFAULT_SCREEN_HEIGHT));
				mainFrame.setLocationRelativeTo(null);
				cardLayout.show(MainPanel.this, MAINMENU_VIEW);
			}

			@Override
			public void switchToOrders() {
				ordersCustomerView.getViewController().setPreviousView(previousView);

				ordersCustomerView.updateViewOrder();

				cardLayout.show(MainPanel.this, ORDERSCUSTOMER_VIEW);
			}

			@Override
			public void removeItemFromCart() {
				defaultCustomerView.updateProductView(defaultCustomerView.getCurrentOffset());
			}

			@Override
			public int getTotalPrice() {
				return totalProductsPrice;
			}

			@Override
			public void setTotalPrice(int price) {
				totalProductsPrice = price;
			}

			@Override
			public void MakeOrder(Consumer<Boolean> callback) throws IOException {
				int input = JOptionPane.showOptionDialog(null, "Make order!\nTotal price: " + getTotalPrice(),
						"ARE YOU SURE?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				boolean isBuySuccess;
				if (input == 0) {
					isBuySuccess = Program.actor.MakeOrder(Program.this::DisplayError);
					if (isBuySuccess) {
						cartCustomerView.updateViewCart();
						defaultCustomerView.UpdateCurrentView();
						ordersCustomerView.updateViewOrder();
						if (previousView.equals(DETAILSCUSTOMER_VIEW)) {
							detailsCustomerView
									.updateProductView(detailsCustomerView.getViewController().GetCurrentProductId());
						}
						JOptionPane.showMessageDialog(null, "Success make order ^.^!", "Success!",
								JOptionPane.INFORMATION_MESSAGE);
					}
					callback.accept(isBuySuccess);
				}
			}

		}

		protected class OrdersCustomerViewController implements IOrdersCustomerViewController {

			private String previousView = "";

			@Override
			public void setPreviousView(String view) {
				this.previousView = view;
			}

			@Override
			public void switchToPreviousView() {
				cardLayout.show(MainPanel.this, previousView);
			}

			@Override
			public void switchToDefault() {
				cardLayout.show(MainPanel.this, DEFAULTCUSTOMER_VIEW);
			}

			@Override
			public void switchToMainMenu() {
				Program.actor = null;
				mainFrame.setSize(new Dimension(_DEFAULT_SCREEN_WIDTH, _DEFAULT_SCREEN_HEIGHT));
				mainFrame.setLocationRelativeTo(null);
				cardLayout.show(MainPanel.this, MAINMENU_VIEW);
			}

			@Override
			public void switchToCart() throws IOException {
				cartCustomerView.getViewController().setPreviousView(previousView);

				cartCustomerView.updateViewCart();

				cardLayout.show(MainPanel.this, CARTCUSTOMER_VIEW);
			}

			@Override
			public void switchToOrderDetails(Order order) {
				orderDetailsCustomerView.updateViewOrderDetails(order);

				cardLayout.show(MainPanel.this, ORDERDETAILSCUSTOMER_VIEW);
			}

		}

		protected class OrderDetailsCustomerViewController implements IOrderDetailsCustomerViewController {

			@Override
			public void switchToOrders() {
				ordersCustomerView.updateViewOrder();

				cardLayout.show(MainPanel.this, ORDERSCUSTOMER_VIEW);
			}

			@Override
			public void switchToDefault() {
				cardLayout.show(MainPanel.this, DEFAULTCUSTOMER_VIEW);
			}

			@Override
			public void switchToMainMenu() {
				Program.actor = null;
				mainFrame.setSize(new Dimension(_DEFAULT_SCREEN_WIDTH, _DEFAULT_SCREEN_HEIGHT));
				mainFrame.setLocationRelativeTo(null);
				cardLayout.show(MainPanel.this, MAINMENU_VIEW);
			}

			@Override
			public void switchToCart() throws IOException {
				cartCustomerView.getViewController().setPreviousView(ORDERDETAILSCUSTOMER_VIEW);

				cartCustomerView.updateViewCart();

				cardLayout.show(MainPanel.this, CARTCUSTOMER_VIEW);
			}

		}

		protected class DefaultShipperViewController implements IDefaultShipperViewController {

			@Override
			public void setActor(Actor actor) {
				Program.actor = actor;
			}

			@Override
			public void switchToMainMenu() {
				Program.actor = null;
				mainFrame.setSize(new Dimension(_DEFAULT_SCREEN_WIDTH, _DEFAULT_SCREEN_HEIGHT));
				mainFrame.setLocationRelativeTo(null);
				cardLayout.show(MainPanel.this, MAINMENU_VIEW);
			}

			@Override
			public void switchToDetails(Order order) {
				orderDetailsShipperView.getViewController().setPreviousView(DEFAULTSHIPPER_VIEW);

				orderDetailsShipperView.updateOrderDetailsView(order);

				cardLayout.show(MainPanel.this, ORDERDETAILSSHIPPER_VIEW);
			}

			@Override
			public void switchToTakenOrder() {
				ordersShipperView.getViewController().setPreviousView(DEFAULTSHIPPER_VIEW);

				ordersShipperView.updateOrderView();

				cardLayout.show(MainPanel.this, ORDERSSHIPPER_VIEW);
			}

		}

		protected class OrderDetailsShipperViewController implements IOrderDetailsShipperViewController {

			private String previousView = "";
			private Order currentOrder;

			@Override
			public void setPreviousView(String view) {
				this.previousView = view;
			}

			@Override
			public void switchToPreviousView() {
				cardLayout.show(MainPanel.this, previousView);
			}

			@Override
			public void SetCurrentOrder(Order order) {
				currentOrder = order;
			}

			@Override
			public Order GetCurrentUrder() {
				return currentOrder;
			}

			@Override
			public void switchToDefault() {
				defaultShipperView.updateOrderView();
				cardLayout.show(MainPanel.this, DEFAULTSHIPPER_VIEW);
			}

			@Override
			public void switchToMainMenu() {
				Program.actor = null;
				mainFrame.setSize(new Dimension(_DEFAULT_SCREEN_WIDTH, _DEFAULT_SCREEN_HEIGHT));
				mainFrame.setLocationRelativeTo(null);
				cardLayout.show(MainPanel.this, MAINMENU_VIEW);
			}

			@Override
			public void switchToTakenOrder() {
				ordersShipperView.updateOrderView();

				cardLayout.show(MainPanel.this, ORDERSSHIPPER_VIEW);
			}

		}

		protected class OrdersShipperViewController implements IOrdersShipperViewController {

			private String previousView = "";

			@Override
			public void setPreviousView(String view) {
				this.previousView = view;
				if (view.equals(ORDERDETAILSSHIPPER_VIEW)) {
					orderDetailsShipperView
							.updateOrderDetailsView(orderDetailsShipperView.getViewController().GetCurrentUrder());
				}
			}

			@Override
			public void switchToPreviousView() {
				cardLayout.show(MainPanel.this, previousView);
			}

			@Override
			public void RemoveOrder() {
				Order currentOrder = orderDetailsShipperView.getViewController().GetCurrentUrder();
				currentOrder.SetOrderStatus(ORDERSTATUS.PLACED);
				currentOrder.SetShipper(null);
			}

			@Override
			public void DeliveryDone() {
				Order currentOrder = orderDetailsShipperView.getViewController().GetCurrentUrder();
				currentOrder.SetOrderStatus(ORDERSTATUS.DELIVERED);
			}

			@Override
			public void switchToDefault() {
				defaultShipperView.updateOrderView();
				cardLayout.show(MainPanel.this, DEFAULTSHIPPER_VIEW);
			}

			@Override
			public void switchToMainMenu() {
				Program.actor = null;
				mainFrame.setSize(new Dimension(_DEFAULT_SCREEN_WIDTH, _DEFAULT_SCREEN_HEIGHT));
				mainFrame.setLocationRelativeTo(null);
				cardLayout.show(MainPanel.this, MAINMENU_VIEW);
			}

			@Override
			public void switchToDetails(Order order) {
				orderDetailsShipperView.getViewController().setPreviousView(ORDERSSHIPPER_VIEW);

				orderDetailsShipperView.updateTakenOrderDetailsView(order);

				cardLayout.show(MainPanel.this, ORDERDETAILSSHIPPER_VIEW);
			}

		}

		// #endregion

	}

	// #endregion

	/* **************************************** */
	// #region Public Methods
	public void StartProgram() {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

					mainFrame = new JFrame("ThriftMerch");
					mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

					mainFrame.add(new MainPanel());

					mainFrame.pack();

					mainFrame.setSize(new Dimension(_DEFAULT_SCREEN_WIDTH, _DEFAULT_SCREEN_HEIGHT));

					mainFrame.setLocationRelativeTo(null);
					mainFrame.setResizable(false);

					mainFrame.setVisible(true);

				} catch (IOException exception) {
					System.out.println("Error: " + exception.getMessage());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException exception) {
					System.out.println("Error: " + exception.getMessage());
				}
			}
		});
	}

	private void DisplayError(String message) {
		JOptionPane.showMessageDialog(null, message, "Something is wrong!", JOptionPane.ERROR_MESSAGE);
	}
	// #endregion

}
