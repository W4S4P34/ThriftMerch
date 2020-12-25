package DataController;

import java.sql.Date;

public class Product {
    private String id;
    private String name;
    private String brand;
    private int price;
    private String imagePath;
    private int quantity;
    private Date date;
    private String description;

    public Product(String id,String name,String brand,int price,int quantity,String imagePath,Date date,String description){
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.imagePath = imagePath;
        this.quantity = quantity;
        this.date = date;
        this.description = description;
    }

    //#region Getters/Setters methods
    public String GetId(){
        return id;
    }
    public String GetName(){
        return name;
    }
    public String GetBrand(){
        return brand;
    }
    public int GetPrice(){
        return price;
    }
    public String GetImagePath(){
        return imagePath;
    }
    public int GetQuantity(){
        return quantity;
    }
    public Date GetDate(){
        return date;
    }
    public String GetDescription(){
        return description;
    }
    public void SetQuantity(int quantity){
        this.quantity = quantity;
    }
    //#endregion


}
