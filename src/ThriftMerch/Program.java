package ThriftMerch;
import Actor.Actor;
import DataController.DataHandler;


import java.util.Scanner;

public class Program {
    //#region Private Fields
    private static Actor user = null;

    //#endregion

    //#region Public Methods
    public static void StartProgram() {
        LoadAllData();
        LoadMenuLoginGUI();
    }
    public static void EndProgram() {
        SaveAllData();
    }
    //#endregion

    //#region Private Methods
    private static Actor SignIn() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.print("ID: ");
        String ID = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        try{
            return DataHandler.GetInstance().SignIn(ID,password);
        }
        catch (Exception exc){
            System.out.println("Error: " + exc.getMessage());
        }
        return null;
    }
    private static Actor SignUp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Customer");
        System.out.println("2. Shipper");
        System.out.print("Choice: ");
        try{
            byte choice = scanner.nextByte();
            return DataHandler.GetInstance().SignUp(choice);
        }
        catch (Exception exc)
        {
            System.out.println("Error: " + exc.getMessage());
        }
        return null;
    }
    private static void LoadAllData() {
        // Load all data from file
    }
    private static void SaveAllData(){
        // Save all data to file
    }
    private static void LoadMenuLoginGUI() {
        System.out.println("1. Sign In");
        System.out.println("2. Sign Up");
        System.out.println("3. Exit");
        int choice = MenuChoice(1,3);
        switch (choice)
        {
            case 1:
            {
                user = SignIn();
                break;
            }
            case 2:
            {
                user = SignUp();
                break;
            }
            case 3:
            {
                return;
            }
        }
        if(user != null)
            LoadMenuGUI();
        else
            LoadMenuLoginGUI();
    }
    private static void LoadMenuGUI(){
        user.LoadMenuGUI();
    }
    private static int MenuChoice(int minBound,int maxBound) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choice: ");
        int tmp = scanner.nextInt();
        if(tmp < minBound || tmp > maxBound)
            return MenuChoice(minBound,maxBound);
        return tmp;
    }
    //#endregion
}
