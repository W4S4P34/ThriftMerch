package MVCModel.Views.ActorViews.Customer;

import java.util.ArrayList;

import DataController.Product;
import MVCModel.Controllers.ActorControllers.Customer.IDefaultCustomerViewController;
import MVCModel.Views.IView;

public interface IDefaultCustomerView extends IView<IDefaultCustomerViewController> {
	public void updateProductView(int offset);
	public void updateSearchProductView(String products,int offset);
	public void repaintContentPanel(ArrayList<Product> productList);
	
	public void resetView();

	public int getCurrentOffset();
}
