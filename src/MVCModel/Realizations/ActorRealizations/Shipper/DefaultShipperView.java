package MVCModel.Realizations.ActorRealizations.Shipper;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import MVCModel.Controllers.ActorControllers.Shipper.IDefaultShipperViewController;
import MVCModel.Realizations.AbstractView;
import MVCModel.Views.ActorViews.Shipper.IDefaultShipperView;

public class DefaultShipperView extends AbstractView<IDefaultShipperViewController>
	implements IDefaultShipperView {

	private static final long serialVersionUID = 1L;
	
	/* ****************************** */
	// #region Swing Components
	
	private JLabel identity;
	
	// #endregion

	/* ****************************** */
	// #region Construct Layout Process
	
	public DefaultShipperView(IDefaultShipperViewController viewController) {
		super(viewController);
		setLayout(new BorderLayout());

		// Add identity authority
		identity = new JLabel("SHIPPER", JLabel.CENTER);
		
		// Add components to current view
		add(identity, BorderLayout.CENTER);
	}
	
	// #endregion
	
}
