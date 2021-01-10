package MVCModel.Views.ActorViews.Customer;

import DataController.Product;
import MVCModel.Controllers.ActorControllers.Customer.ICartCustomerViewController;
import MVCModel.Views.IView;

public interface ICartCustomerView extends IView<ICartCustomerViewController> {
	public void addToCart(Product product);
	public void removeFromCart(Product product);
	public void resetView();
}
