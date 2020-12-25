package MVCModel.Controllers;

import Actor.Actor;

public interface ISignUpViewController extends IViewController {
	public void signupSuccessful(Actor actor);
	public void signupFailed(String message);
	public void getMessage(String msg);
	public void switchToMainMenu();
}
