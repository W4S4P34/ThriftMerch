package MVCModel.Views.ActorViews.Customer;

import java.awt.Component;
import java.io.IOException;

import MVCModel.Controllers.ActorControllers.Customer.ICartCustomerViewController;
import MVCModel.Views.IView;

public interface ICartCustomerView extends IView<ICartCustomerViewController> {
	public void addToCart() throws IOException;
	public void removeFromCart(Component component);
	public void resetView();
	
	public int getTotalPrice();
	public void setTotalPrice(int total);
}
