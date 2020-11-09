package Actor;

import java.util.Scanner;

public abstract class Actor {
    //#region Account Inner Class
    class Account{
        String ID;
        String password;
        public Account(String ID,String password)
        {
            this.ID = ID;
            this.password = password;
        }
        public Account HashAccount(String ID,String password)
        {
            return new Account(ID,Hash(password));
        }
        public String Hash(String content)
        {
            return content;
        }
    }
    //#endregion

    //#region Protected Fields
    protected String name;
    protected byte age;
    protected String phoneNumber;
    protected String gender;
    protected Account account;
    //#endregion

    //#region Private Fields

    //#endregion

    //#region Public Fields

    //#endregion

    //#region Getters and Setters
    public String GetName(){
        return name;
    }
    public void SetName(String name){
        this.name = name;
    }
    public byte GetAge(){
        return age;
    }
    public void SetAge(byte age){
        this.age = age;
    }
    public String GetPhoneNumber(){
        return phoneNumber;
    }
    public void SetPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public String GetGender(){
        return gender;
    }
    public void SetGender(String gender){
        this.gender = gender;
    }
    public String GetID(){
        return account.ID;
    }
    public String GetPassword(){
        return account.password;
    }
    //#endgion

    //#region Public Methods
    public Actor(){
        account = new Account("","");
    }
    public void CreateNewActor(){
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Name: ");
        name = scanner.nextLine();
        System.out.print("Age: ");
        age = Byte.parseByte(scanner.nextLine());
        System.out.print("Phone Number: ");
        phoneNumber = scanner.nextLine();
        System.out.print("Gender: ");
        gender = scanner.nextLine();
        System.out.print("ID: ");
        account.ID = scanner.nextLine();
        System.out.print("Password: ");
        account.password = scanner.nextLine();
        scanner.close();
    }
    public abstract void LoadMenuGUI();

    //#endregion

    //#region Private Methods

    //#endregion
}
