package Actor;


import DataController.DataHandler;
import DataController.Product;
import Misc.ActorType;
import Utils.Helper;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.HashMap;
import java.util.function.Consumer;


public class Customer extends Actor {
    //#region private fields
    private HashMap<String,Product> myShoppingCard = null;
    //#endregion
    @Override
    public ActorType GetActorType()
    {
        return ActorType.CUSTOMER;
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
            // Static getInstance method is called with hashing SHA
            String sql = "select * from customer where id = ? and password = ?";
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1,id);
                stmt.setString(2,account.Hash(password));
                ResultSet resultSet = stmt.executeQuery();
                Actor actor = null;
                if(resultSet.next())
                {
                    InitData(resultSet.getString("id"),resultSet.getString("password"),resultSet.getString("name")
                    ,resultSet.getString("phone_number"),resultSet.getString("address"));
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
        if (!Helper.IsOnlyDigit(phoneNumber)){
            signUpFailed.accept("Invalid phone number!");
            return null;
        }
        return DataHandler.GetInstance().SignUp((conn)->{
            String sql = "select * from customer where id = ? or phone_number = ?";
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1,id);
                stmt.setString(2,phoneNumber);
                ResultSet resultSet = stmt.executeQuery();
                if(resultSet.next()){
                    signUpFailed.accept("Account is already existed");
                    return null;
                }
                sql = "insert into customer values(?,?,?,?,?)";
                stmt = conn.prepareStatement(sql);
                InitData(id, account.Hash(password), name, phoneNumber, address);
                stmt.setString(1,id);
                stmt.setString(2,account.Hash(password));
                stmt.setString(3,name);
                stmt.setString(4,phoneNumber);
                stmt.setString(5,address);
                stmt.executeUpdate();
                // Close resource
                conn.commit();
                stmt.close();
                conn.close();
                return this;
            } catch (SQLException | NoSuchAlgorithmException exc) {
                try {
                    conn.rollback();
                } catch (SQLException ignored) { }
                System.out.println("Error: " + exc.getMessage());
            }
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
    @Override
    public void AddToCard(String productId){
        if(productId.equals(""))
            return;
        Product product = DataHandler.GetInstance().GetProduct(productId);
        if(product != null){
            if(!myShoppingCard.containsKey(productId)){
                myShoppingCard.put(productId,new Product(productId,product.GetName(),product.GetBrand(),
                        product.GetPrice(),0,product.GetImagePath(),product.GetDate(),product.GetDescription()));
            }
            int quantity = myShoppingCard.get(productId).GetQuantity();
            myShoppingCard.get(productId).SetQuantity(quantity+1);
        }
    }

    @Override
    public void RemoveItemFromCard(String productId) {
        if(myShoppingCard != null && !myShoppingCard.isEmpty() && myShoppingCard.containsKey(productId)){
            Product product = myShoppingCard.get(productId);
            product.SetQuantity(product.GetQuantity()-1);
            if(product.GetQuantity() == 0){
                myShoppingCard.remove(productId);
            }
        }
    }

    @Override
    public HashMap<String,Product> GetMyCard() {
        if(myShoppingCard == null || myShoppingCard.size() == 0)
            return null;
        return myShoppingCard;
    }
    //#endregion

    //#region Private Methods
    private void InitData(String id,String password,String name,String phoneNumber,String address)
    {
        this.account.ID = id;
        this.account.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        myShoppingCard = new HashMap<>();
    }
    //#endregion
}
