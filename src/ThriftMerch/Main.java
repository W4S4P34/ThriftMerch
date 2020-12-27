package ThriftMerch;


import Actor.*;
import DataController.ORDERSTATUS;

public class Main {
    public static void main(String[] args){
//        Program mainProgram = new Program();
//        mainProgram.StartProgram();
        Actor actor = new Shipper().SignIn("shipper1","password",null);
        actor.UpdateOrder("086408003168", ORDERSTATUS.DELIVERED);
    }
}
