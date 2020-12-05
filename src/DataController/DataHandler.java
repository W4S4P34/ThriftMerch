package DataController;

import Actor.Actor;

import java.awt.event.ActionListener;
import java.sql.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/*
    DataHandler controls all data of this program
*/
public class DataHandler {

    /* **************************************** */
	// #region Private Fields
    private static DataHandler instance = null;
    private Driver myDriver;
    private final String DB_URL = "jdbc:mysql://localhost/qlch";
    private final String USER = "root";
    private final String PASS = "";
    // #endregion



    /* **************************************** */
    // #region Public Fields

    // #endregion

    /* **************************************** */
    // #region Getters and Setters
    public static DataHandler GetInstance(){
        if(instance == null)
        {
            instance = new DataHandler();
        }
        return instance;
    }
    // #endgion

    /* **************************************** */
    // #region Public Methods
    public Actor SignIn(Function<Connection,Actor> function)
    {
        Statement stmt = null;
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(DB_URL,
                    USER, PASS);
            if(conn == null)
                return null;
            stmt = conn.prepareStatement("");
        }
        catch (SQLException exc)
        {
            System.out.println("Error: " + exc.getMessage());
        }
        return function.apply(conn);
    }
    // #endregion

    /* **************************************** */
    // #region Private Methods
    private DataHandler()
    {
        try{
            myDriver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(myDriver);

        }
        catch (SQLException exc) {
            System.out.println("Error: " + exc.getMessage());
        }
    }
    // #endregion
}
