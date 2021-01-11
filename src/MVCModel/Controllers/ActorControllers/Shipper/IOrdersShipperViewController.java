package MVCModel.Controllers.ActorControllers.Shipper;

import DataController.Order;
import MVCModel.Controllers.IViewController;

public interface IOrdersShipperViewController extends IViewController {
	public void setPreviousView(String view);

	public void switchToMainMenu();
	public void switchToDefault();
	public void switchToDetails(Order item);
	public void switchToPreviousView();
}
