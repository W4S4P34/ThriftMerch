package MVCModel.Controllers.ActorControllers.Shop;

import DataController.Order;
import MVCModel.Controllers.IViewController;

public interface IOrdersShopViewController extends IViewController {
	public void setPreviousView(String view);
	
	public void switchToPreviousView();
	public void switchToDefault();
	public void switchToMainMenu();
	public void switchToDetails(Order item);

}
