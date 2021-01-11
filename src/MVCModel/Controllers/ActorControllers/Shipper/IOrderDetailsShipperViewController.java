package MVCModel.Controllers.ActorControllers.Shipper;

import MVCModel.Controllers.IViewController;

public interface IOrderDetailsShipperViewController extends IViewController {
	public void setPreviousView(String view);

	public void switchToMainMenu();
	public void switchToDefault();
	public void switchToTakenOrder();
	public void switchToPreviousView();
}
