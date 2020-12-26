package MVCModel.Views.ActorViews.Customer;

import MVCModel.Controllers.ActorControllers.Customer.IDefaultCustomerViewController;
import MVCModel.Views.IView;

public interface IDefaultCustomerView extends IView<IDefaultCustomerViewController> {
	public void updateProductView(int offset);
}
