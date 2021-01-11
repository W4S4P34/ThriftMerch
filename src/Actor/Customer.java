package Actor;

import DataController.*;
import Misc.ActorType;
import Utils.*;

import java.sql.*;

import java.util.*;
import java.util.function.Consumer;

public class Customer extends Actor {
	// #region private fields
	private HashMap<String, Product> myShoppingCart = null;
	// #endregion

	public Customer() {

	}

	public Customer(String name, String phoneNumber, String address) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	// #region Core Methods
	@Override
	public ActorType GetActorType() {
		return ActorType.CUSTOMER;
	}

	@Override
	public Actor SignIn(String id, String password, Consumer<String> signInFailed) {
		// Check valid input
		if (id.equals("") || password.equals("")) {
			signInFailed.accept("Invalid input! Empty input.");
			return null;
		}
		return DataHandler.GetInstance().SignIn((conn) -> {
			// Static getInstance method is called with hashing SHA
			String sql = "select * from customer where id = ? and password = ?";
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, id);
				stmt.setString(2, SHA256Digest.Hash(password));
				ResultSet resultSet = stmt.executeQuery();
				Actor actor = null;
				if (resultSet.next()) {
					InitData(resultSet.getString("id"), resultSet.getString("password"), resultSet.getString("name"),
							resultSet.getString("phone_number"), resultSet.getString("address"));
					actor = this;
				}
				// Close resource
				stmt.close();
				conn.close();
				if (actor == null) {
					signInFailed.accept("Username or password is not correct.");
				}
				return actor;
			} catch (SQLException exc) {
				signInFailed.accept("Exception Error: " + exc.getMessage());
			}
			return null;
		});
	}

	@Override
	public Actor SignUp(String id, String password, String name, String phoneNumber, String address,
			Consumer<String> signUpFailed) {
		// Check valid input
		if (id.equals("") || password.equals("") || phoneNumber.equals("") || address.equals("") || name.equals("")) {
			signUpFailed.accept("Invalid input! Empty input.");
			return null;
		}
		if (!Helper.IsOnlyDigit(phoneNumber)) {
			signUpFailed.accept("Invalid phone number!");
			return null;
		}
		return DataHandler.GetInstance().SignUp((conn) -> {
			String sql = "select * from customer where id = ? or phone_number = ?";
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, id);
				stmt.setString(2, phoneNumber);
				ResultSet resultSet = stmt.executeQuery();
				if (resultSet.next()) {
					signUpFailed.accept("Account is already existed");
					return null;
				}
				sql = "insert into customer values(?,?,?,?,?)";
				stmt = conn.prepareStatement(sql);
				InitData(id, SHA256Digest.Hash(password), name, phoneNumber, address);
				stmt.setString(1, id);
				stmt.setString(2, SHA256Digest.Hash(password));
				stmt.setString(3, name);
				stmt.setString(4, phoneNumber);
				stmt.setString(5, address);
				stmt.executeUpdate();
				// Close resource
				conn.commit();
				stmt.close();
				conn.close();
				return this;
			} catch (SQLException exc) {
				try {
					conn.rollback();
				} catch (SQLException ignored) {
				}
				System.out.println("Error: " + exc.getMessage());
			}
			return null;
		});
	}

	@Override
	public void Display() {
		System.out.println("ID: " + account.ID);
		System.out.println("Password: " + account.password);
		System.out.println("Name: " + name);
		System.out.println("Phone number: " + phoneNumber);
	}

	@Override
	public boolean AddToCart(String productId, int quantity, Consumer<String> consumer) {
		if (productId.equals("")) {
			consumer.accept("ProductID is empty!");
			return false;
		}
		Product product = DataHandler.GetInstance().GetProduct(productId);
		if (product != null) {
			if(product.GetQuantity() <= 0){
				consumer.accept("Out of stock");
				return false;
			}
			if (!myShoppingCart.containsKey(productId)) {
				myShoppingCart.put(productId, new Product(productId, product.GetName(), product.GetBrand(),
						product.GetPrice(), 0, product.GetImagePath(), product.GetDate(), product.GetDescription()));
			}
			quantity += myShoppingCart.get(productId).GetQuantity();
			if (quantity > product.GetQuantity()) {
				consumer.accept("Exceed quantity of product!");
				return false;
			}
			myShoppingCart.get(productId).SetQuantity(quantity);
		}
		return true;
	}

	@Override
	public boolean IsInCart(String productId){
		if(myShoppingCart != null && myShoppingCart.containsKey(productId))
			return true;
		return false;
	}

	@Override
	public boolean RemoveItemFromCart(String productId, Consumer<String> consumer) {
		if (productId.equals("")) {
			consumer.accept("ProductID is empty!");
			return false;
		}
		if (myShoppingCart != null && !myShoppingCart.isEmpty() && myShoppingCart.containsKey(productId)) {
			Product product = myShoppingCart.get(productId);
			if (product.GetQuantity() <= 1) {
				consumer.accept("Need at least 1 item in cart");
				return false;
			}
			product.SetQuantity(product.GetQuantity() - 1);
		}
		return true;
	}

	@Override
	public boolean RemoveAllItemFromCart(String productId) {
		if (myShoppingCart != null && !myShoppingCart.isEmpty() && myShoppingCart.containsKey(productId)) {
			myShoppingCart.remove(productId);
			return true;
		}
		return false;
	}

	@Override
	public HashMap<String, Product> GetMyCart() {
		if (myShoppingCart == null || myShoppingCart.size() == 0)
			return null;
		return myShoppingCart;
	}

	@Override
	public boolean MakeOrder(Consumer<String> consumer) {
		HashMap<String, Product> shoppingCart = GetMyCart();
		if (shoppingCart == null || shoppingCart.size() == 0) {
			consumer.accept("Shopping cart is empty");
			return false;
		}
		int totalPrice = 0;
		for (Map.Entry<String, Product> item : shoppingCart.entrySet())
			totalPrice += item.getValue().GetPrice() * item.getValue().GetQuantity();
		String orderId = Helper.RandomNumberOnly(12);
		String date = Helper.GetCurrentDate();
		boolean isSuccess = DataHandler.GetInstance().MakeOrder(orderId, totalPrice, shoppingCart, account.ID, date);
		if(isSuccess)
			myShoppingCart.clear();
		return isSuccess;
	}

	@Override
	public boolean BuyNow(String productId, int quantity, Consumer<String> consumer) {
		if (productId.isEmpty()) {
			consumer.accept("ProductID is empty");
			return false;
		}
		Product product = DataHandler.GetInstance().GetProduct(productId);
//		if (quantity > product.GetQuantity()) {
//			consumer.accept("Exceed quantity of product!");
//			return false;
//		}
		if (product.GetQuantity() <= 0) {
			consumer.accept("Out of stock!");
			return false;
		}
		HashMap<String, Product> mapProduct = new HashMap<>();
		mapProduct.put(productId, new Product(productId, product.GetName(), product.GetBrand(), product.GetPrice(),
				quantity, product.GetImagePath(), product.GetDate(), product.GetDescription()));
		String orderId = Helper.RandomNumberOnly(12);
		String date = Helper.GetCurrentDate();
		int totalPrice = quantity * product.GetPrice();
		boolean isSuccess = DataHandler.GetInstance().MakeOrder(orderId, totalPrice, mapProduct, account.ID, date);
		if(isSuccess && myShoppingCart.containsKey(productId)){
			int cardQuantity = myShoppingCart.get(productId).GetQuantity();
			if(cardQuantity >= product.GetQuantity()){
				myShoppingCart.get(productId).SetQuantity(myShoppingCart.get(productId).GetQuantity()-1);
			}
		}
		return isSuccess;
	}

	@Override
	public ArrayList<Order> ViewMyOrder() {
		return DataHandler.GetInstance().ViewMyOrder((conn) -> {
			try {
				String sql = "select * from `order` where customerId = ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, account.ID);
				ResultSet resultSet = stmt.executeQuery();
				ArrayList<Order> listOrder = null;
				if (resultSet.next()) {
					listOrder = new ArrayList<>();
					do {
						String id = resultSet.getString("id");
						int status = resultSet.getInt("status");
						int totalPrice = resultSet.getInt("totalPrice");
						String customerId = resultSet.getString("customerId");
						java.sql.Date date = resultSet.getDate("date");
						ArrayList<Product> listOrderItem = DataHandler.GetInstance().GetOrderItem(id, conn);
						Shipper shipper = DataHandler.GetInstance().GetShipper(customerId, conn);
						listOrder.add(new Order(id, date, ORDERSTATUS.SetValue(status), listOrderItem, totalPrice,
								shipper, this));
					} while (resultSet.next());
				}
				stmt.close();
				conn.close();
				return listOrder;
			} catch (SQLException exc) {
				System.out.println("Error: " + exc.getMessage());
			}
			return null;
		});
	}
	// #endregion

	// #region Private Methods
	private void InitData(String id, String password, String name, String phoneNumber, String address) {
		this.account.ID = id;
		this.account.password = password;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		myShoppingCart = new HashMap<>();
	}
	// #endregion
}
