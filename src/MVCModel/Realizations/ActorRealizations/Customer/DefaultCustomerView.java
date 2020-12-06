package MVCModel.Realizations.ActorRealizations.Customer;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import MVCModel.Controllers.ActorControllers.Customer.IDefaultCustomerViewController;
import MVCModel.Realizations.AbstractView;
import MVCModel.Views.ActorViews.Customer.IDefaultCustomerView;

public class DefaultCustomerView extends AbstractView<IDefaultCustomerViewController>
	implements IDefaultCustomerView {

	private static final long serialVersionUID = 1L;
	
	/* ****************************** */
	// #region Swing Components
	
	private JLabel identity;
	
	// #endregion

	/* ****************************** */
	// #region Construct Layout Process
	
	public DefaultCustomerView(IDefaultCustomerViewController viewController) {
		super(viewController);
		setLayout(new BorderLayout());

		// Add identity authority
		identity = new JLabel("CUSTOMER", JLabel.CENTER);
		
		// Add components to current view
		add(identity, BorderLayout.CENTER);
	}
	
	// #endregion
}
