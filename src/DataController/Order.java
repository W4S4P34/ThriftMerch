package DataController;

import Actor.*;

import java.sql.Date;
import java.util.ArrayList;

enum ORDERSTATUS {
    PLACED,
    PROCESSED,
    DELIVERED
}

public class Order {
    private String id;
    private Date date;
    private ORDERSTATUS status;
    private ArrayList<Product> orderItem = new ArrayList<>();
    private int totalPrice;
    private Shipper shipper;

    public Order(String id, Date date, ORDERSTATUS status, ArrayList<Product> orderItem,int totalPrice,Shipper shipper){
        this.id = id;
        this.date = date;
        this.status = status;
        this.orderItem = orderItem;
        this.totalPrice = totalPrice;
        this.shipper = shipper;
    }

    //#region Getters/Setters Methods
    public String GetID(){
        return id;
    }
    public Date GetDate(){
        return date;
    }
    public ORDERSTATUS GetOrderStatus(){
        return status;
    }
    public ArrayList<Product> GetOrderItem(){
        return orderItem;
    }
    public int GetTotalPrice(){
        return totalPrice;
    }
    public Shipper GetShipper(){
        return shipper;
    }
    //#endregion
}
