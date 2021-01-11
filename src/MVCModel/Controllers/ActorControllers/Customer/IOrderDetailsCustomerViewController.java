package MVCModel.Controllers.ActorControllers.Customer;

import java.io.IOException;

import MVCModel.Controllers.IViewController;

public interface IOrderDetailsCustomerViewController extends IViewController {
	public void switchToDefault();
	public void switchToMainMenu();
	public void switchToCart() throws IOException;
	public void switchToOrders();
}
