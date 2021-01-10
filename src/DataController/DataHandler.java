package DataController;

import Actor.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
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
	public static DataHandler GetInstance() {
		if (instance == null) {
			instance = new DataHandler();
		}
		return instance;
	}
	// #endgion

	/* **************************************** */
	// #region Public Methods
	public Actor SignIn(Function<Connection, Actor> function) {
		Connection conn;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if (conn == null)
				return null;
			return function.apply(conn);
		} catch (SQLException exc) {
			System.out.println("Error: " + exc.getMessage());
		}
		return null;
	}

	public Actor SignUp(Function<Connection, Actor> function) {
		Connection conn;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if (conn == null)
				return null;
			conn.setAutoCommit(false);
			return function.apply(conn);
		} catch (SQLException exc) {
			System.out.println("Error: " + exc.getMessage());
		}
		return null;
	}

	/*
	 * Get all products based on pagination + Limit: the limit of product in a page.
	 * + Offset: the current page. Example: 1,2,3,....
	 */
	public ArrayList<Product> GetAllProducts(int limit, int offset) {
		Connection conn;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if (conn == null)
				return null;
			offset = (offset - 1) * limit;
			String sql = String.format("select * from product limit %s offset %s", limit, offset);
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);
			ArrayList<Product> listProduct = null;
			if (resultSet.next()) {
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
					listProduct.add(new Product(id, name, brand, price, quantity, imagePath, date, description));
				} while (resultSet.next());
			}
			stmt.close();
			conn.close();
			return listProduct;
		} catch (SQLException exc) {
			System.out.println("Error: " + exc.getMessage());
		}
		return null;
	}

	public ArrayList<Product> GetNewArrivalsProducts() {
		final int LIMIT = 5;
		Connection conn;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if (conn == null)
				return null;
			String sql = String.format("select * from product order by `date` desc limit %s", LIMIT);
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);
			ArrayList<Product> listProduct = null;
			if (resultSet.next()) {
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
					listProduct.add(new Product(id, name, brand, price, quantity, imagePath, date, description));
				} while (resultSet.next());
			}
			stmt.close();
			conn.close();
			return listProduct;
		} catch (SQLException exc) {
			System.out.println("Error: " + exc.getMessage());
		}
		return null;
	}

	public int GetAllProductsSize() {
		Connection conn;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if (conn == null)
				return 0;
			String sql = "select count(*) as size from product";
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);
			int productSize = 0;
			if (resultSet.next()) {
				productSize = resultSet.getInt("size");
			}
			stmt.close();
			conn.close();
			return productSize;
		} catch (SQLException exc) {
			System.out.println("Error: " + exc.getMessage());
		}
		return 0;
	}

	public int GetPageNumber(int limit) {
		return (int) Math.ceil((double) GetAllProductsSize() / limit);
	}
	// #endregion

	//#region Public methods for customers
	/*
	 * Get a product by their ID
	 */
	public Product GetProduct(String productId) {
		Connection conn;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if (conn == null)
				return null;
			String sql = "select * from product where product.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, productId);
			ResultSet resultSet = stmt.executeQuery();
			Product product = null;
			if (resultSet.next()) {
				String id = resultSet.getString("id");
				String name = resultSet.getString("name");
				String brand = resultSet.getString("brand");
				int price = resultSet.getInt("price");
				int quantity = resultSet.getInt("quantity");
				String imagePath = resultSet.getString("imagePath");
				Date date = resultSet.getDate("date");
				String description = resultSet.getString("description");
				product = new Product(id, name, brand, price, quantity, imagePath, date, description);
			}
			stmt.close();
			conn.close();
			return product;
		} catch (SQLException exc) {
			System.out.println("Error: " + exc.getMessage());
		}
		return null;
	}
	public ArrayList<Product> SearchProducts(String request,int limit,int offset) {
		Connection conn;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if (conn == null)
				return null;
			String sql = String.format("select * from product where match(name,brand,description) against ('%s') limit %s offset %s", request, limit, offset);
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);
			ArrayList<Product> listProduct = null;
			if (resultSet.next()) {
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
					listProduct.add(new Product(id, name, brand, price, quantity, imagePath, date, description));
				} while (resultSet.next());
			}
			stmt.close();
			conn.close();
			return listProduct;
		} catch (SQLException exc) {
			System.out.println("Error: " + exc.getMessage());
		}
		return null;
	}
	public int GetPageNumberSearch(String request,int limit){
		Connection conn;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if (conn == null)
				return 0;
			String sql = String.format("select count(*) as size from product where match(name,brand,description) against ('%s')", request);
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);
			int size = 0;
			if (resultSet.next()) {
				size = resultSet.getInt("size");
			}
			stmt.close();
			conn.close();
			return (int) Math.ceil((double) size / limit);
		} catch (SQLException exc) {
			System.out.println("Error: " + exc.getMessage());
		}
		return 0;
	}
	public boolean MakeOrder(String orderId, int totalPrice ,HashMap<String,Product> orderItem,String customerId,String date){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			if(conn == null)
				return false;
			conn.setAutoCommit(false);
			String sql = "insert into `order` values (?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1,orderId);
			stmt.setInt(2,ORDERSTATUS.PLACED.GetValue());
			stmt.setInt(3,totalPrice);
			stmt.setString(4,customerId);
			stmt.setString(5,null);
			stmt.setString(6,date);
			if(stmt.executeUpdate() == 0){
				System.out.println("Error: " + "insert into order failed");
				return false;
			}
			for (Map.Entry<String,Product> item : orderItem.entrySet()) {
				sql = "insert into `orderItem` values (?,?,?)";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1,item.getValue().GetId());
				stmt.setString(2,orderId);
				stmt.setInt(3,item.getValue().GetQuantity());
				if(stmt.executeUpdate() == 0){
					System.out.println("Error: " + "insert into orderItem failed");
					return false;
				}
			}
			// Close resource
			conn.commit();
			stmt.close();
			conn.close();
			return true;
		}catch (SQLException exc){
			try {
				conn.rollback();
			} catch (SQLException ignored) { }
			System.out.println("Error: " + exc.getMessage());
		}
		return false;
	}
	// For customer and shipper
	public ArrayList<Order> ViewMyOrder(Function<Connection,ArrayList<Order>> function){
		Connection conn;
		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			if(conn == null)
				return null;
			return function.apply(conn);
		}catch (SQLException exc){
			System.out.println("Error: " + exc.getMessage());
		}
		return null;
	}
	//#endregion

	//#region Public methods for shipper
	public ArrayList<Order> ViewAllOrphanedOrder(){
		Connection conn;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if (conn == null)
				return null;
			String sql = "select * from `order` where status = 0";
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);
			ArrayList<Order> listOrder = null;
			if (resultSet.next()) {
				listOrder = new ArrayList<>();
				do {
					String id = resultSet.getString("id");
					int totalPrice = resultSet.getInt("totalPrice");
					String customerId = resultSet.getString("customerId");
					Date date = resultSet.getDate("date");
					ArrayList<Product> listOrderItem = GetOrderItem(id,conn);
					Customer customer = GetCustomer(customerId,conn);
					listOrder.add(new Order(id,date,ORDERSTATUS.SetValue(0),listOrderItem,totalPrice,null,customer));
				} while (resultSet.next());
			}
			stmt.close();
			conn.close();
			return listOrder;
		} catch (SQLException exc) {
			System.out.println("Error: " + exc.getMessage());
		}
		return null;
	}
	public void UpdateOrder(Consumer<Connection> consumer){
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			if(conn == null)
				return;
			conn.setAutoCommit(false);
			consumer.accept(conn);
		}catch (SQLException exc){
			try {
				conn.rollback();
			}catch (SQLException ignore) { }
			System.out.println("Error: " + exc.getMessage());
		}
	}
	//#endregion

	//#region Public methods for shop
	public boolean AddNewProduct(Product product){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			if(conn == null)
				return false;
			conn.setAutoCommit(false);
			String sql = "insert into `product` (`id`,`name`,`brand`,`price`,`quantity`,`imagePath`,`date`,`description`) values (?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1,product.GetId());
			stmt.setString(2,product.GetName());
			stmt.setString(3,product.GetBrand());
			stmt.setInt(4,product.GetPrice());
			stmt.setInt(5,product.GetQuantity());
			stmt.setString(6, product.GetImagePath());
			stmt.setDate(7,product.GetDate());
			stmt.setString(8, product.GetDescription());
			if(stmt.executeUpdate() == 0){
				System.out.println("Error: " + "insert into product failed");
				return false;
			}
			// Close resource
			conn.commit();
			stmt.close();
			conn.close();
			return true;
		}catch (SQLException exc){
			try {
				conn.rollback();
			} catch (SQLException ignored) { }
			System.out.println("Error: " + exc.getMessage());
		}
		return false;
	}
	public boolean UpdateProduct(Consumer<Connection> consumer){
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			if(conn == null)
				return false;
			conn.setAutoCommit(false);
			consumer.accept(conn);
		}catch (SQLException exc){
			try {
				conn.rollback();
			}catch (SQLException ignore) { }
			System.out.println("Error: " + exc.getMessage());
			return false;
		}
		return true;
	}
	public ArrayList<Order> ViewAllOrder(){
		Connection conn;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if (conn == null)
				return null;
			String sql = "select * from `order`";
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);
			ArrayList<Order> listOrder = null;
			if (resultSet.next()) {
				listOrder = new ArrayList<>();
				do {
					String id = resultSet.getString("id");
					int totalPrice = resultSet.getInt("totalPrice");
					int status = resultSet.getInt("status");
					String customerId = resultSet.getString("customerId");
					String shipperId = resultSet.getString("shipperId");
					Date date = resultSet.getDate("date");
					ArrayList<Product> listOrderItem = GetOrderItem(id, conn);
					Customer customer = GetCustomer(customerId, conn);
					Shipper shipper = GetShipper(shipperId, conn);
					if(customer != null){
						System.out.println(customer.GetName());
					}
					if(shipper != null){
						System.out.println(shipper.GetName());
					}
					listOrder.add(new Order(id,date,ORDERSTATUS.SetValue(status),listOrderItem,totalPrice,shipper,customer));
				} while (resultSet.next());
			}
			stmt.close();
			conn.close();
			return listOrder;
		} catch (SQLException exc) {
			System.out.println("Error: " + exc.getMessage());
		}
		return null;
	}
	//#endregion

	/* **************************************** */
	// #region Private Methods
	public ArrayList<Product> GetOrderItem(String orderId,Connection conn) throws SQLException {
		String sql = "select *,`orderItem`.quantity as itemQuantity from `orderItem` join `product` on productId = id and orderId = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1,orderId);
		ResultSet resultSet = stmt.executeQuery();
		ArrayList<Product> listOrderItem = null;
		if(resultSet.next()){
			listOrderItem = new ArrayList<>();
			do {
				String id = resultSet.getString("id");
				String name = resultSet.getString("name");
				String brand = resultSet.getString("brand");
				int price = resultSet.getInt("price");
				int quantity = resultSet.getInt("itemQuantity");
				String imagePath = resultSet.getString("imagePath");
				Date date = resultSet.getDate("date");
				String description = resultSet.getString("description");
				listOrderItem.add(new Product(id, name, brand, price, quantity, imagePath, date, description));
			}while (resultSet.next());
		}
		return listOrderItem;
	}
	public Customer GetCustomer(String customerId,Connection conn) throws SQLException {
		if(customerId == null)
			return null;
		String sql = "select * from `customer` where id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1,customerId);
		ResultSet resultSet = stmt.executeQuery();

		Customer customer = null;
		if(resultSet.next()){
			customer = new Customer(resultSet.getString("name"),resultSet.getString("phone_number"),resultSet.getString("address"));
		}
		return customer;
	}
	public Shipper GetShipper(String shipperId,Connection conn) throws SQLException {
		if (shipperId == null)
			return null;
		String sql = "select * from `shipper` where id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1,shipperId);
		ResultSet resultSet = stmt.executeQuery();
		Shipper shipper = null;
		if(resultSet.next()){
			shipper = new Shipper();
			shipper.SetName(resultSet.getString("name"));
			shipper.SetAddress(resultSet.getString("address"));
			shipper.SetPhoneNumber(resultSet.getString("phone_number"));
			shipper.SetAge((byte)resultSet.getInt("age"));
			shipper.SetGender(resultSet.getString("gender"));
		}
		return shipper;
	}
	private DataHandler() {
		try {
			Driver myDriver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(myDriver);
		} catch (Exception exc) {
			System.out.println("Error: " + exc.getMessage());
		}
	}
	// #endregion
}
