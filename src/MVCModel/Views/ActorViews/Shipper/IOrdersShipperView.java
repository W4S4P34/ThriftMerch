package MVCModel.Views.ActorViews.Shipper;

import MVCModel.Controllers.ActorControllers.Shipper.IOrdersShipperViewController;
import MVCModel.Views.IView;

public interface IOrdersShipperView extends IView<IOrdersShipperViewController> {

	public void updateOrderView();

}
