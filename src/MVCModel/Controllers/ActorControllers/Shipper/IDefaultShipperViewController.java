package MVCModel.Controllers.ActorControllers.Shipper;

import Actor.Actor;
import DataController.Order;
import MVCModel.Controllers.IViewController;

public interface IDefaultShipperViewController extends IViewController {
	public void setActor(Actor actor);
	
	public void switchToMainMenu();
	public void switchToDetails(Order order);
	public void switchToTakenOrder();
}
