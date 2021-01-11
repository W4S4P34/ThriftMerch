package MVCModel.Controllers.ActorControllers.Customer;

import java.io.IOException;

import Actor.Actor;
import DataController.Product;
import MVCModel.Controllers.IViewController;

public interface IDefaultCustomerViewController extends IViewController {
	public void setActor(Actor actor);
	public void switchToDetails(Product product);
	public void switchToMainMenu();
	public void switchToCart() throws IOException;
	public void switchToOrders();
	
	public void addToCart(Product product) throws IOException;
}
