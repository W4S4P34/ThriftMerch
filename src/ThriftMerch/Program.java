package ThriftMerch;

import MVCModel.Controllers.ILoginViewController;
import MVCModel.Controllers.IMainMenuViewController;
import MVCModel.Controllers.ISignUpViewController;
import MVCModel.Controllers.ActorControllers.Customer.IDefaultCustomerViewController;
import MVCModel.Controllers.ActorControllers.Shipper.IDefaultShipperViewController;
import MVCModel.Controllers.ActorControllers.Shop.IDefaultShopViewController;
import MVCModel.Realizations.LoginView;
import MVCModel.Realizations.MainMenuView;
import MVCModel.Realizations.SignUpView;
import MVCModel.Realizations.ActorRealizations.Customer.DefaultCustomerView;
import MVCModel.Realizations.ActorRealizations.Shipper.DefaultShipperView;
import MVCModel.Realizations.ActorRealizations.Shop.DefaultShopView;
import MVCModel.Views.ILoginView;
import MVCModel.Views.IMainMenuView;
import MVCModel.Views.ISignUpView;
import MVCModel.Views.ActorViews.Customer.IDefaultCustomerView;
import MVCModel.Views.ActorViews.Shipper.IDefaultShipperView;
import MVCModel.Views.ActorViews.Shop.IDefaultShopView;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.lang.ClassNotFoundException;
import java.lang.InstantiationException;
import java.lang.IllegalAccessException;
import javax.swing.UnsupportedLookAndFeelException;

import Actor.Actor;

public class Program extends JPanel {

	private static final long serialVersionUID = 1L;

	/* **************************************** */
	// #region Public Fields
	public static JFrame mainFrame;
	public static final String comName = "THRIFTMERCH";

	public static final int _DEFAULT_SCREEN_WIDTH = 616;
	public static final int _DEFAULT_SCREEN_HEIGHT = 452;

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

		// #endregion
		private CardLayout cardLayout;
		private IMainMenuView mainMenuView;
		private ILoginView loginView;
		private ISignUpView signUpView;

		private IDefaultShopView defaultShopView;
		private IDefaultCustomerView defaultCustomerView;
		private IDefaultShipperView defaultShipperView;

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

			add(mainMenuView.getView(), MAINMENU_VIEW);
			add(loginView.getView(), LOGIN_VIEW);
			add(signUpView.getView(), SIGNUP_VIEW);

			add(defaultShopView.getView(), DEFAULTSHOP_VIEW);
			add(defaultCustomerView.getView(), DEFAULTCUSTOMER_VIEW);
			add(defaultShipperView.getView(), DEFAULTSHIPPER_VIEW);

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
					cardLayout.show(MainPanel.this, DEFAULTSHOP_VIEW);
					break;
				case CUSTOMER:
					defaultCustomerView.updateProductView(1);
					defaultCustomerView.getViewController().setActor(actor);

					cardLayout.show(MainPanel.this, DEFAULTCUSTOMER_VIEW);
					break;
				case SHIPPER:
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

			@Override
			public void getMessage(String msg) {
				// TODO Auto-generated method stub

			}

		}

		protected class DefaultShopViewController implements IDefaultShopViewController {

		}

		protected class DefaultCustomerViewController implements IDefaultCustomerViewController {

			@SuppressWarnings("unused")
			private Actor actor;

			@Override
			public void setActor(Actor actor) {
				this.actor = actor;
			}

			@Override
			public void logoutAccount() {
				this.actor = null;
			}

		}

		protected class DefaultShipperViewController implements IDefaultShipperViewController {

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

	// #endregion

}
