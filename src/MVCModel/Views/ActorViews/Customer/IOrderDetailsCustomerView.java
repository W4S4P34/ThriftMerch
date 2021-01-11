package MVCModel.Views.ActorViews.Customer;

import DataController.Order;
import MVCModel.Controllers.ActorControllers.Customer.IOrderDetailsCustomerViewController;
import MVCModel.Views.IView;

public interface IOrderDetailsCustomerView extends IView<IOrderDetailsCustomerViewController> {
	public void updateViewOrderDetails(Order order);
}
