package MVCModel.Controllers.ActorControllers.Customer;

import MVCModel.Controllers.IViewController;

public interface ICartCustomerViewController extends IViewController {
	public void setPreviousView(String view);
	public void switchToPreviousView();
	public void switchToDefault();
	public void switchToMainMenu();
	public void switchToOrders();
	
	public void removeItemFromCart();
	
	public int getTotalPrice();
	public void setTotalPrice(int price);
}