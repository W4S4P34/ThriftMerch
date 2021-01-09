package MVCModel.Controllers.ActorControllers.Customer;

import Actor.Actor;
import DataController.Product;
import MVCModel.Controllers.IViewController;

public interface IDefaultCustomerViewController extends IViewController {
	public void setActor(Actor actor);
	public void logoutAccount();
	public void switchToDetails(Product product);
	public void switchToMainMenu();
}
