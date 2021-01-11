package MVCModel.Views.ActorViews.Customer;

import MVCModel.Controllers.ActorControllers.Customer.IOrdersCustomerViewController;
import MVCModel.Views.IView;

public interface IOrdersCustomerView extends IView<IOrdersCustomerViewController> {
	public void updateViewOrder();
}
