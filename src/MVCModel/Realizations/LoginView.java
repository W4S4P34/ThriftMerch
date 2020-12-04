package MVCModels.Realizations;

import MVCModels.Controllers.ILoginViewController;
import MVCModels.Views.ILoginView;

public class LoginView extends AbstractView<ILoginViewController> implements ILoginView {

	private static final long serialVersionUID = 1L;
	
	/* ****************************** */
	// #region Swing Components
	
	
	// #endregion

	/* ****************************** */
	// #region Construct Layout Process
	public LoginView(ILoginViewController viewController) {
		super(viewController);
		
	}
	
	// #endregion

}
