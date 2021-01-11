package MVCModel.Views.ActorViews.Shop;

import java.util.ArrayList;

import DataController.Product;
import MVCModel.Controllers.ActorControllers.Shop.IDefaultShopViewController;
import MVCModel.Views.IView;

public interface IDefaultShopView extends IView<IDefaultShopViewController> {

	public void updateProductView(int offset);
	public void updateSearchProductView(String products, int offset);
	
	void repaintContentPanel(ArrayList<Product> productList);

	public void UpdateCurrentView();
	public void resetView();

	public int getCurrentOffset();

}
