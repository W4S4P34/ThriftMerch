package MVCModel.Views.ActorViews.Shop;

import DataController.Product;
import MVCModel.Controllers.ActorControllers.Shop.IProductDetailsShopViewController;
import MVCModel.Views.IView;

public interface IProductDetailsShopView extends IView<IProductDetailsShopViewController> {

	void updateProductDetailsView(Product product);

}
