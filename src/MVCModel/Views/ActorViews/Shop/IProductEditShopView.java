package MVCModel.Views.ActorViews.Shop;

import MVCModel.Controllers.ActorControllers.Shop.IProductEditShopViewController;
import MVCModel.Views.IView;

public interface IProductEditShopView extends IView<IProductEditShopViewController> {

	void updateEditProductView();

}
