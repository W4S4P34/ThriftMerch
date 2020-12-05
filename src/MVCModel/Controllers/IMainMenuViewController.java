package MVCModel.Controllers;

public interface IMainMenuViewController extends IViewController {
	public void switchToSignIn();
	public void switchToSignUp();
	public void exitProgram();
}
