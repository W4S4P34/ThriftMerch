package DataController;

import Actor.*;
import java.sql.*;
import java.util.function.Function;

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
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(DB_URL,
                    USER, PASS);
            if(conn == null)
                return null;
            return function.apply(conn);
        }
        catch (SQLException exc)
        {
            System.out.println("Error: " + exc.getMessage());
        }
        return null;
    }
    public Actor SignUp(Function<Connection,Actor> function)
    {
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(DB_URL,
                    USER, PASS);
            if(conn == null)
                return null;
            conn.setAutoCommit(false);
            return function.apply(conn);
        }
        catch (SQLException exc)
        {
            System.out.println("Error: " + exc.getMessage());
        }
        return null;
    }
    // #endregion

    /* **************************************** */
    // #region Private Methods
    private DataHandler()
    {
        try{
            myDriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(myDriver);
        }
        catch (Exception exc) {
            System.out.println("Error: " + exc.getMessage());
        }
    }
    // #endregion
}
