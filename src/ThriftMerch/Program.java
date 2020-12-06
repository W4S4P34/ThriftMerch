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
import java.io.IOException;

import javax.swing.JFrame;
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
			public void exitProgram() {
				SwingUtilities.windowForComponent(MainPanel.this).dispose();
			}

			@Override
			public void switchToSignIn() {
				cardLayout.show(MainPanel.this, LOGIN_VIEW);
			}

			@Override
			public void switchToSignUp() {
				cardLayout.show(MainPanel.this, SIGNUP_VIEW);
			}

	    }
	    
	    protected class LoginViewController implements ILoginViewController {

			@Override
			public void backToMainMenu() {
				cardLayout.show(MainPanel.this, MAINMENU_VIEW);
			}

			@Override
			public void signinSuccessful(Actor actor) {
				switch (actor.GetActorType()) {
				case SHOP:
					cardLayout.show(MainPanel.this, DEFAULTSHOP_VIEW);
					break;
				case CUSTOMER:
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
			public void signinFailed() {
				// TODO Auto-generated method stub
				
			}

	    }
	    
	    protected class SignUpViewController implements ISignUpViewController {
	    	
	    }
	    
	    protected class DefaultShopViewController implements IDefaultShopViewController {
	    	
	    }
	    
	    protected class DefaultCustomerViewController implements IDefaultCustomerViewController {
	    	
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
	                
	                mainFrame.setLocationRelativeTo(null);
	                mainFrame.setResizable(false);
	 
	                mainFrame.setVisible(true);
	                
                } catch (IOException exception) {
				  	System.out.println("Error: " + exception.getMessage());
				} catch (ClassNotFoundException 
                		| InstantiationException 
                		| IllegalAccessException 
                		| UnsupportedLookAndFeelException exception) {
                	System.out.println("Error: " + exception.getMessage());
                }
            }
        });
    }

    // #endregion
    
}
