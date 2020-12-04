package MVCModels.Realizations;

import javax.swing.JComponent;
import javax.swing.JPanel;

import MVCModels.Controllers.IViewController;
import MVCModels.Views.IView;

public abstract class AbstractView<C extends IViewController> extends JPanel implements IView<C> {

	private static final long serialVersionUID = 1L;

	private C viewController;

    public AbstractView(C viewController) {
        this.viewController = viewController;
    }

    @Override
    public JComponent getView() {
        return this;
    }

    @Override
    public C getViewController() {
        return viewController;
    }
}
