package MVCModel.Views.ActorViews.Shop;

import MVCModel.Controllers.ActorControllers.Shop.IOrdersShopViewController;
import MVCModel.Views.IView;

public interface IOrdersShopView extends IView<IOrdersShopViewController> {

	public void updateOrderView();

}
