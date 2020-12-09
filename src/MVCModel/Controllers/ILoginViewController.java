package MVCModel.Controllers;

import Actor.Actor;

public interface ILoginViewController extends IViewController {
	public void signinSuccessful(Actor actor);
	public void signinFailed();
	public void switchToMainMenu();
}
