package MVCModel.Views.ActorViews.Customer;

import DataController.Product;
import MVCModel.Controllers.ActorControllers.Customer.IDetailsCustomerViewController;
import MVCModel.Views.IView;

import java.io.IOException;

public interface IDetailsCustomerView extends IView<IDetailsCustomerViewController> {
	public void updateProductView(Product product);
	public void updateProductView(String productId);
}
