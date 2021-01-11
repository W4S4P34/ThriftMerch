package MVCModel.Views.ActorViews.Shop;

import DataController.Order;
import MVCModel.Controllers.ActorControllers.Shop.IOrderDetailsShopViewController;
import MVCModel.Views.IView;

public interface IOrderDetailsShopView extends IView<IOrderDetailsShopViewController> {

	void updateMainOrderDetailsView(Order order);

}
