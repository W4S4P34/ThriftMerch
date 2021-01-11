package MVCModel.Views.ActorViews.Shipper;

import DataController.Order;
import MVCModel.Controllers.ActorControllers.Shipper.IOrderDetailsShipperViewController;
import MVCModel.Views.IView;

public interface IOrderDetailsShipperView extends IView<IOrderDetailsShipperViewController> {
	public void updateMainOrderDetailsView(Order order);
	public void updateOrderDetailsView(Order order);
	public void updateTakenOrderDetailsView(Order order);
}
