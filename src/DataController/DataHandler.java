package DataController;
import Actor.*;
import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/*
    - Class controller all data of this program
 */
public class DataHandler {


    //#region Private Fields
    private static DataHandler instance = null;
    private HashMap<String,Actor> listActor = null;
    private HashMap<String,ArrayList<Post>> listPost = null;
    private ArrayList<Post> listReport = null;
    private HashMap<String,ArrayList<Order>> listOrderCustomer = null;
    private HashMap<String,ArrayList<Order>> listOrderShipper = null;
    //#endregion

    //#region Public Fields

    //#endregion

    //#region Getters and Setters
    public static DataHandler GetInstance(){
        if(instance == null)
        {
            instance = new DataHandler();
        }
        return instance;
    }
    //#endgion

    //#region Public Methods
    public Actor SignIn(String ID,String password) throws LoginException {
        Actor user = listActor.get(ID);
        if(user == null || user.GetPassword().compareTo(password) != 0)
            throw new LoginException("Your username or password may be incorrect");
        return user;
    }

    public Actor SignUp(byte actorID) throws LoginException {
        Actor user;
        if(actorID == 1)
        {
            user = new Customer();
        }
        else
        {
            user = new Shipper();
        }
        user.CreateNewActor();
        if(listActor.get(user.GetID()) != null)
            throw new LoginException("ID is already exist");
        for (Map.Entry<String,Actor> ele : listActor.entrySet()) {
            if(ele.getValue().GetPhoneNumber() == user.GetPhoneNumber())
                throw new LoginException("Phone Number is already exist");
        }
        return user;
    }
    //#endregion

    //#region Private Methods
    private DataHandler(){
        listActor = new HashMap<>();
        listPost = new HashMap<>();
    }
    //#endregion
}
