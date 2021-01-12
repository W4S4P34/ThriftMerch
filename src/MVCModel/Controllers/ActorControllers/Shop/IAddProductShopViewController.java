package MVCModel.Controllers.ActorControllers.Shop;

import MVCModel.Controllers.IViewController;

public interface IAddProductShopViewController extends IViewController {
	public void setPreviousView(String view);
	
	public void switchToPreviousView();
	public void switchToDefault();
	public void switchToMainMenu();
	public void switchToOrders();

}
