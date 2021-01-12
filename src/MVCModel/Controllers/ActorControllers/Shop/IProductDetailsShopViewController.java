package MVCModel.Controllers.ActorControllers.Shop;

import DataController.Product;
import MVCModel.Controllers.IViewController;

import java.util.function.Consumer;

public interface IProductDetailsShopViewController extends IViewController {

	void setPreviousView(String view);
	
	public void switchToEditProduct(String getId);

	public void switchToPreviousView();

	public void switchToMainMenu();

	public void switchToDefault();

	public void switchToAddProduct();

	public void switchToOrders();

	public Product GetCurrentProduct();
	public void SetCurrentProduct(Product product);
	public void RemoveProduct();
}
