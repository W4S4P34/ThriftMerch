package MVCModel.Controllers.ActorControllers.Shop;

import MVCModel.Controllers.IViewController;

public interface IProductDetailsShopViewController extends IViewController {

	void setPreviousView(String view);
	
	public void switchToEditProduct(String getId);

	public void switchToPreviousView();

	public void switchToMainMenu();

	public void switchToDefault();

	public void switchToAddProduct();

	public void switchToOrders();

}
