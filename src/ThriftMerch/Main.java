package ThriftMerch;

import Actor.*;

public class Main {
    public static void main(String[] args){
//        Program mainProgram = new Program();
//        mainProgram.StartProgram();
        Actor actor = new Customer().SignIn("shipper1","password");
        if(actor != null)
        {
            actor.Display();
        }
        Actor actor1 = new Customer().SignUp("customer10","password","Phu Quyen","0937134168","158 Xom Dat P.10 Q.11",20,"Male");
        if(actor1 == null)
        {
            System.out.println("Sign up failed");
        }
        else{
            actor1.Display();
        }
    }
}
