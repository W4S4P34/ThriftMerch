package MVCModel.Controllers.ActorControllers.Customer;

import Actor.Actor;
import MVCModel.Controllers.IViewController;

public interface IDefaultCustomerViewController extends IViewController {
	public void setActor(Actor actor);
	public void logoutAccount();
}
