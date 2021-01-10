package Actor;

import DataController.*;
import Misc.ActorType;
import Utils.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Shop extends Actor {

    String license = "";


    @Override
    public ActorType GetActorType()
    {
        return ActorType.SHOP;
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
            String sql = "select * from shop where id = ? and password = ?";
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1,id);
                stmt.setString(2, SHA256Digest.Hash(password));
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
                if(actor == null){
                    signInFailed.accept("Username or password is not correct.");
                }
                return actor;
            } catch (SQLException exc) {
                signInFailed.accept("Exception Error: " + exc.getMessage());
            }

            return null;
        });
    }
    @Override
    public Actor SignUp(String id,String password,String name,String phoneNumber,String address,Consumer<String> signInFailed){
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

    @Override
    public boolean AddNewProduct(String name,String brand,int price,int quantity,String description,Consumer<String> consumer){
        if(name.isEmpty() || brand.isEmpty() || description.isEmpty()){
            consumer.accept("Invalid input,please fill in all information!");
            return false;
        }
        String productId = Helper.RandomCharacterNumber(10);
        int totalProduct = DataHandler.GetInstance().GetPageNumber(1);
        String imagePath = String.format("Resources/Products/%s.jpg",totalProduct+1);
        return DataHandler.GetInstance().AddNewProduct(new Product(productId,name,brand,price,quantity,imagePath,new Date(System.currentTimeMillis()),description));
    }

    @Override
    public boolean UpdateQuantity(String productId,int quantity, Consumer<String> consumer){
        return UpdateProduct(productId,"quantity",quantity,consumer);
    }
    @Override
    public boolean UpdatePrice(String productId,int price, Consumer<String> consumer){
        return UpdateProduct(productId,"price",price,consumer);
    }
    @Override
    public boolean UpdateName(String productId,String name, Consumer<String> consumer){
        return UpdateProduct(productId,"name",name,consumer);
    }
    @Override
    public boolean UpdateDescription(String productId,String description, Consumer<String> consumer){
        return UpdateProduct(productId,"name",description,consumer);
    }
    @Override
    public boolean UpdateBrand(String productId,String brand, Consumer<String> consumer){
        return UpdateProduct(productId,"brand",brand,consumer);
    }
    @Override
    public ArrayList<Order> ViewAllOrder(){
        return DataHandler.GetInstance().ViewAllOrder();
    }
    //#endregion

    //#region Private methods
    private boolean UpdateProduct(String productId,String column ,int value ,Consumer<String> consumer){
        if(productId.isEmpty()){
            consumer.accept("ProductID is empty");
            return false;
        }
        return DataHandler.GetInstance().UpdateProduct((conn)->{
            try {
                String sql = String.format("update `product` set %s = ? where id = ?",column);
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, value);
                stmt.setString(2, productId);
                if(stmt.executeUpdate() == 0){
                    System.out.println("Error: failed to update product quantity");
                }
                conn.commit();
                conn.close();
                stmt.close();
            } catch (SQLException exc) {
                System.out.println("Error: " + exc.getMessage());
            }
        });
    }
    private boolean UpdateProduct(String productId,String column ,String value ,Consumer<String> consumer){
        if(productId.isEmpty()){
            consumer.accept("ProductID is empty");
            return false;
        }
        return DataHandler.GetInstance().UpdateProduct((conn)->{
            try {
                String sql = String.format("update `product` set %s = ? where id = ?",column);
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, value);
                stmt.setString(2, productId);
                if(stmt.executeUpdate() == 0){
                    System.out.println("Error: failed to update product quantity");
                }
                conn.commit();
                conn.close();
                stmt.close();
            } catch (SQLException exc) {
                System.out.println("Error: " + exc.getMessage());
            }
        });
    }
    //#endregion
}
