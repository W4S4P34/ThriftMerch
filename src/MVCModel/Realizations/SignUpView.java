package MVCModels.Realizations;

import MVCModels.Controllers.ISignUpViewController;
import MVCModels.Views.ISignUpView;

public class SignUpView extends AbstractView<ISignUpViewController> implements ISignUpView{
	
	private static final long serialVersionUID = 1L;
	
	/* ****************************** */
	// #region Swing Components
	
	
	// #endregion

	/* ****************************** */
	// #region Construct Layout Process
	public SignUpView(ISignUpViewController viewController) {
		super(viewController);
		
	}
	
	// #endregion
}
