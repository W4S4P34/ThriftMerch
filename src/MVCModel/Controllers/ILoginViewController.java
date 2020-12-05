package MVCModel.Controllers;

public interface ILoginViewController extends IViewController {
	public void signinSuccessful();
	public void signinFailed();
	public void backToMainMenu();
}
