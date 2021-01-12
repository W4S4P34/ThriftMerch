package MVCModel.Controllers.ActorControllers.Shop;

import MVCModel.Controllers.IViewController;

import java.io.File;
import java.nio.file.Path;
import java.util.function.Consumer;

public interface IAddProductShopViewController extends IViewController {
	public void setPreviousView(String view);
	
	public void switchToPreviousView();
	public void switchToDefault();
	public void switchToMainMenu();
	public void switchToOrders();

	public void AddNewProduct(String name, String brand, String price, String quantity, String description, Consumer<Boolean> callback);

	public void SetCurrentFile(File file);
	//public File GetCurrentFile();

}
