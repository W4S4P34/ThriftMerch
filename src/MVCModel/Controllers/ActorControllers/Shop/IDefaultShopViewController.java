package MVCModel.Controllers.ActorControllers.Shop;

import Actor.Actor;
import DataController.Product;
import MVCModel.Controllers.IViewController;

public interface IDefaultShopViewController extends IViewController {
	public void setActor(Actor actor);

	public void switchToMainMenu();
	public void switchToDetails(Product getProduct);

}
