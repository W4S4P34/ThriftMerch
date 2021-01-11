package MVCModel.Views.ActorViews.Customer;

import java.io.IOException;

import MVCModel.Controllers.ActorControllers.Customer.ICartCustomerViewController;
import MVCModel.Views.IView;

public interface ICartCustomerView extends IView<ICartCustomerViewController> {
	public void updateViewCart() throws IOException;
}
