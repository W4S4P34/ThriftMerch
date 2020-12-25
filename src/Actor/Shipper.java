package Actor;

import DataController.DataHandler;
import Misc.ActorType;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;

public class Shipper extends Actor{
    @Override
    public ActorType GetActorType()
    {
        return ActorType.SHIPPER;
    }
    //#region Core Methods
    @Override
    public Actor SignIn(String id, String password, Consumer<String> signInFailed) {
        //Check valid input
        if(id.equals("") || password.equals("")){
            signInFailed.accept("Invalid input! Empty input.");
            return null;
        }
        return DataHandler.GetInstance().SignIn((conn)->{
            String sql = "select * from shipper where id = ? and password = ?";
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
                    this.phoneNumber = resultSet.getString("phone_number");
                    this.address = resultSet.getString("address");
                    this.age = Integer.valueOf(resultSet.getInt("age")).byteValue();
                    this.gender = resultSet.getString("gender");
                    actor = this;
                }
                // Close resource
                stmt.close();
                conn.close();
                if(actor == null){
                    signInFailed.accept("Username or password is not correct.");
                }
                return actor;
            } catch (SQLException | NoSuchAlgorithmException exc) {
                signInFailed.accept("Exception Error: " + exc.getMessage());
            }
            return null;
        });
    }
    @Override
    public Actor SignUp(String id,String password,String name,String phoneNumber,String address,Consumer<String> signUpFailed){
        //Check valid input
        if(id.equals("") || password.equals("") || phoneNumber.equals("") || address.equals("") || name.equals("")){
            signUpFailed.accept("Invalid input! Empty input.");
            return null;
        }
        if (!phoneNumber.matches("[0-9]+")){
            signUpFailed.accept("Invalid phone number!");
            return null;
        }
        return DataHandler.GetInstance().SignUp((conn)->{
            String sql = "select id from shipper where id = ?";
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1,id);
                ResultSet resultSet = stmt.executeQuery();
                if(resultSet.next())
                    return null;
                sql = "insert into shipper values(?,?,?,?,?,?,?)";
                stmt = conn.prepareStatement(sql);
                InitData(id, account.Hash(password), name, phoneNumber, address, age, gender);
                stmt.setString(1,id);
                stmt.setString(2,account.Hash(password));
                stmt.setString(3,name);
                stmt.setString(4,phoneNumber);
                stmt.setString(5,address);
                stmt.setInt(6,age);
                stmt.setString(7,gender);
                stmt.executeUpdate();
                // Close resource
                conn.commit();
                stmt.close();
                conn.close();
                return this;
            } catch (SQLException | NoSuchAlgorithmException exc) { }
            return null;
        });
    }
    @Override
    public void Display() {
        System.out.println("ID: " + account.ID);
        System.out.println("Password: " + account.password);
        System.out.println("Name: " + name);
        System.out.println("Phone number: " + phoneNumber);
    }
    //#endregion

    //#region Private Methods
    private void InitData(String id,String password,String name,String phoneNumber,String address,int age,String gender)
    {
        this.account.ID = id;
        this.account.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.age = Integer.valueOf(age).byteValue();
        this.gender = gender;
    }
    //#endregion
}
