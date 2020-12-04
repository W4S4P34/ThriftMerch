package MVCModel.Views;

import javax.swing.JComponent;

import MVCModel.Controllers.IViewController;

public interface IView<C extends IViewController> {
	public JComponent getView();
    public C getViewController();
}
