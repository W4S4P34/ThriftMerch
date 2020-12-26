package DataController;

import Actor.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.function.Function;

/*
    DataHandler controls all data of this program
*/
public class DataHandler {

    /* **************************************** */
	// #region Private Fields
    private static DataHandler instance = null;
    private final String DB_URL = "jdbc:mysql://localhost/qlch";
    private final String USER = "root";
    private final String PASS = "password";
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
        Connection conn;
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
        Connection conn;
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
/*
    * Get all products based on pagination
    *   + Limit: the limit of product in a page.
    *   + Offset: the current page.
    *       Example: 1,2,3,....
 */
    public ArrayList<Product> GetAllProducts(int limit,int offset){
        Connection conn;
        try{
            conn = DriverManager.getConnection(DB_URL,
                    USER, PASS);
            if(conn == null)
                return null;
            offset = (offset-1)*limit;
            String sql = String.format("select * from product limit %s offset %s",limit,offset);
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            ArrayList<Product> listProduct = null;
            if(resultSet.next()){
                listProduct = new ArrayList<>();
                do {
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("name");
                    String brand = resultSet.getString("brand");
                    int price = resultSet.getInt("price");
                    int quantity = resultSet.getInt("quantity");
                    String imagePath = resultSet.getString("imagePath");
                    Date date = resultSet.getDate("date");
                    String description = resultSet.getString("description");
                    listProduct.add(new Product(id, name,brand,price,quantity,imagePath,date,description));
                }while (resultSet.next());
            }
            stmt.close();
            conn.close();
            return listProduct;
        }
        catch (SQLException exc)
        {
            System.out.println("Error: " + exc.getMessage());
        }
        return null;
    }
    public ArrayList<Product> GetNewArrivalsProducts(){
        final int LIMIT = 5;
        Connection conn;
        try{
            conn = DriverManager.getConnection(DB_URL,
                    USER, PASS);
            if(conn == null)
                return null;
            String sql = String.format("select * from product order by `date` desc limit %s",LIMIT);
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            ArrayList<Product> listProduct = null;
            if(resultSet.next()){
                listProduct = new ArrayList<>();
                do {
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("name");
                    String brand = resultSet.getString("brand");
                    int price = resultSet.getInt("price");
                    int quantity = resultSet.getInt("quantity");
                    String imagePath = resultSet.getString("imagePath");
                    Date date = resultSet.getDate("date");
                    String description = resultSet.getString("description");
                    listProduct.add(new Product(id,name,brand,price,quantity,imagePath,date,description));
                }while (resultSet.next());
            }
            stmt.close();
            conn.close();
            return listProduct;
        }
        catch (SQLException exc)
        {
            System.out.println("Error: " + exc.getMessage());
        }
        return null;
    }
    public int GetAllProductsSize(){
        Connection conn;
        try{
            conn = DriverManager.getConnection(DB_URL,
                    USER, PASS);
            if(conn == null)
                return 0;
            String sql = "select count(*) as size from product";
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            int productSize = 0;
            if(resultSet.next()){
                productSize = resultSet.getInt("size");
            }
            stmt.close();
            conn.close();
            return productSize;
        }
        catch (SQLException exc)
        {
            System.out.println("Error: " + exc.getMessage());
        }
        return 0;
    }
    public int GetPageNumber(int limit){
        return (int) Math.ceil((double)GetAllProductsSize()/limit);
    }
    // #endregion

    //#region Public methods for customers
    /*
     * Get a product by their ID
     */
    public Product GetProduct(String productId){
        Connection conn;
        try{
            conn = DriverManager.getConnection(DB_URL,
                    USER, PASS);
            if(conn == null)
                return null;
            String sql = "select * from product where product.id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,productId);
            ResultSet resultSet = stmt.executeQuery();
            Product product = null;
            if(resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String brand = resultSet.getString("brand");
                int price = resultSet.getInt("price");
                int quantity = resultSet.getInt("quantity");
                String imagePath = resultSet.getString("imagePath");
                Date date = resultSet.getDate("date");
                String description = resultSet.getString("description");
                product = new Product(id,name,brand,price,quantity,imagePath,date,description);
            }
            stmt.close();
            conn.close();
            return product;
        }
        catch (SQLException exc)
        {
            System.out.println("Error: " + exc.getMessage());
        }
        return null;
    }
    public ArrayList<Product> SearchProducts(String request){
        Connection conn;
        try{
            conn = DriverManager.getConnection(DB_URL,
                    USER, PASS);
            if(conn == null)
                return null;
            String sql = String.format("select * from product where match(name,brand) against ('%s')",request);
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            ArrayList<Product> listProduct = null;
            if(resultSet.next()){
                listProduct = new ArrayList<>();
                do {
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("name");
                    String brand = resultSet.getString("brand");
                    int price = resultSet.getInt("price");
                    int quantity = resultSet.getInt("quantity");
                    String imagePath = resultSet.getString("imagePath");
                    Date date = resultSet.getDate("date");
                    String description = resultSet.getString("description");
                    listProduct.add(new Product(id, name,brand,price,quantity,imagePath,date,description));
                }while (resultSet.next());
            }
            stmt.close();
            conn.close();
            return listProduct;
        }
        catch (SQLException exc)
        {
            System.out.println("Error: " + exc.getMessage());
        }
        return null;
    }
    //#endregion
    /* **************************************** */
    //#region Private Methods
    private DataHandler()
    {
        try {
            Driver myDriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(myDriver);
        }
        catch (Exception exc) {
            System.out.println("Error: " + exc.getMessage());
        }
    }
    //#endregion
}
