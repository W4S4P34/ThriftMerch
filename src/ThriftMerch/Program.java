package ThriftMerch;

import Actor.Actor;
import DataController.DataHandler;
import MVCModel.Controllers.ILoginViewController;
import MVCModel.Controllers.IMainMenuViewController;
import MVCModel.Controllers.ISignUpViewController;
import MVCModel.Realizations.LoginView;
import MVCModel.Realizations.MainMenuView;
import MVCModel.Realizations.SignUpView;
import MVCModel.Views.ILoginView;
import MVCModel.Views.IMainMenuView;
import MVCModel.Views.ISignUpView;

import java.awt.CardLayout;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.lang.ClassNotFoundException;
import java.lang.InstantiationException;
import java.lang.IllegalAccessException;
import javax.swing.UnsupportedLookAndFeelException;

public class Program extends JPanel {

	private static final long serialVersionUID = 1L;

	/* **************************************** */
    // #region Private Fields
    private Actor user = null;

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
		    
		    // #endregion
	    private CardLayout cardLayout;

	    private IMainMenuView mainMenuView;
	    private ILoginView loginView;
	    private ISignUpView signUpView;
		    
		// #endregion
	    
	    /* **************************************** */
	    // #region Constructors
	    public MainPanel() throws IOException {
	    	cardLayout = new CardLayout();
	        setLayout(cardLayout);
	        
	        mainMenuView = new MainMenuView(new MainMenuViewController());
	        loginView = new LoginView(new LoginViewController());
	        signUpView = new SignUpView(new SignUpViewController());
	        
	        add(mainMenuView.getView(), MAINMENU_VIEW);
	        add(loginView.getView(), LOGIN_VIEW);
	        add(signUpView.getView(), SIGNUP_VIEW);
	        
	        cardLayout.show(this, MAINMENU_VIEW);
	    }
	    
	    // #endregion
	    
	    /* **************************************** */
	    // #region Controllers Implementation
	    protected class MainMenuViewController implements IMainMenuViewController {

	    }
	    
	    protected class LoginViewController implements ILoginViewController {

	    }
	    
	    protected class SignUpViewController implements ISignUpViewController {
	    	
	    }
	    
	    // #endregion

    }
    
    // #endregion

    /* **************************************** */
    // #region Public Methods
    public void StartProgram() {
        LoadAllData();
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
          
	                JFrame mainFrame = new JFrame("ThriftMerch");
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

    public void EndProgram() {
        SaveAllData();
    }
    // #endregion

    /* **************************************** */
    // #region Private Methods
    private Actor SignIn() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.print("ID: ");
        String ID = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        try{
            return DataHandler.GetInstance().SignIn(ID,password);
        }
        catch (Exception exc){
            System.out.println("Error: " + exc.getMessage());
        }
        return null;
    }

    private Actor SignUp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Customer");
        System.out.println("2. Shipper");
        System.out.print("Choice: ");
        try {
            byte choice = scanner.nextByte();
            return DataHandler.GetInstance().SignUp(choice);
        }
        catch (Exception exc)
        {
            System.out.println("Error: " + exc.getMessage());
        }
        return null;
    }
    
    private void LoadAllData() {
        // Load all data from file
    }

    private void SaveAllData(){
        // Save all data to file
    }

    // #endregion
    
}
