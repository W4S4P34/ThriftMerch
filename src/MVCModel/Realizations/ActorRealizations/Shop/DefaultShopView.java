package MVCModel.Realizations.ActorRealizations.Shop;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import MVCModel.Controllers.ActorControllers.Shop.IDefaultShopViewController;
import MVCModel.Realizations.AbstractView;
import MVCModel.Views.ActorViews.Shop.IDefaultShopView;

public class DefaultShopView extends AbstractView<IDefaultShopViewController>
	implements IDefaultShopView {

	private static final long serialVersionUID = 1L;
	
	/* ****************************** */
	// #region Swing Components
	
	private JLabel identity;
	
	// #endregion

	/* ****************************** */
	// #region Construct Layout Process
	
	public DefaultShopView(IDefaultShopViewController viewController) {
		super(viewController);
		setLayout(new BorderLayout());

		// Add identity authority
		identity = new JLabel("SHOP", JLabel.CENTER);
		
		// Add components to current view
		add(identity, BorderLayout.CENTER);
	}
	
	// #endregion
}
