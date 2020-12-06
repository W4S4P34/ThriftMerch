package Actor;


import DataController.DataHandler;
import Misc.ActorType;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Shop extends Actor {

    String license = "";


    @Override
    public ActorType GetActorType()
    {
        return ActorType.SHOP;
    }
    //#region Core Methods
    @Override
    public Actor SignIn(String id, String password) {
        return DataHandler.GetInstance().SignIn((conn)->{
            String sql = "select * from shop where id = ? and password = ?";
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1,id);
                stmt.setString(2,account.Hash(password));
                ResultSet resultSet = stmt.executeQuery();
                Actor actor = null;
                if(resultSet.next())
                {
                    this.account.ID = resultSet.getString("id");
                    this.account.password = resultSet.getString("password");
                    this.name = resultSet.getString("name");
                    this.license = resultSet.getString("license");
                    this.phoneNumber = resultSet.getString("phone_number");
                    this.address = resultSet.getString("address");
                    actor = this;
                }
                // Close resource
                stmt.close();
                conn.close();
                return actor;
            } catch (SQLException | NoSuchAlgorithmException exc) { }

            return null;
        });
    }
    @Override
    public Actor SignUp(String id,String password,String name,String phoneNumber,String address,int age,String gender){
        return null;
    }
    @Override
    public void Display() {
        System.out.println("ID: " + account.ID);
        System.out.println("Password: " + account.password);
        System.out.println("Name: " + name);
        System.out.println("Phone number: " + phoneNumber);
        System.out.println("License: " + license);
    }
    //#endregion


}
