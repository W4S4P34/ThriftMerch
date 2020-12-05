package Actor;

import DataController.DataHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Admin extends Actor {

    //#region Core Methods
    @Override
    public Actor SignIn(String id, String password) {
        return null;
    }
    @Override
    public Actor SignUp(String id,String password,String name,String phoneNumber,String address,int age,String gender){
        return null;
    }
    @Override
    public void Display() { }
    //#endregion


}
