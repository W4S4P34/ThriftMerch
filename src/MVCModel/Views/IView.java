package MVCModels.Views;

import javax.swing.JComponent;

import MVCModels.Controllers.IViewController;

public interface IView<C extends IViewController> {
	public JComponent getView();
    public C getViewController();
}
