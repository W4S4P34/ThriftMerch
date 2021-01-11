package MVCModel.Controllers.ActorControllers.Customer;


import java.io.IOException;

import DataController.Product;

import MVCModel.Controllers.IViewController;

import java.io.IOException;
import java.util.function.Consumer;

public interface IDetailsCustomerViewController extends IViewController {
	public void switchToDefault();
	public void switchToMainMenu();
	public void switchToCart() throws IOException;
	public void switchToOrders();
	public void addToCart(Product product, Consumer<Boolean> callback) throws IOException;
	public void BuyNow(Product product, Consumer<Boolean> callback) throws IOException;

	public void SetCurrentProductId(String productId);
	public String GetCurrentProductId();
}

