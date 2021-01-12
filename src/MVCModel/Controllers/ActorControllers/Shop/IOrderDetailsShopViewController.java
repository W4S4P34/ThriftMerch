package MVCModel.Controllers.ActorControllers.Shop;

import MVCModel.Controllers.IViewController;

public interface IOrderDetailsShopViewController extends IViewController {
	public void setPreviousView(String view);
	
	public void switchToPreviousView();
	public void switchToDefault();
	public void switchToMainMenu();
	public void switchToAddProduct();
	public void switchToOrders();
	
}
