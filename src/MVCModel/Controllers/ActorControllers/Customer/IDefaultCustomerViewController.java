package MVCModel.Controllers.ActorControllers.Customer;

import java.io.IOException;
import java.util.function.Consumer;

import Actor.Actor;
import DataController.Product;
import MVCModel.Controllers.IViewController;

public interface IDefaultCustomerViewController extends IViewController {
	public void setActor(Actor actor);
	
	public void switchToDetails(Product product);
	public void switchToMainMenu();
	public void switchToCart() throws IOException;
	public void switchToOrders();
	public void addToCart(Product product, Consumer<Boolean> callback) throws IOException;
	public void BuyNow(Product product, Consumer<Boolean> callback) throws IOException;
}
