package Actor;

import DataController.Product;
import Misc.ActorType;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.function.Consumer;

public abstract class Actor {
	/* **************************************** */
    // #region Account Inner Class
    class Account{
        String ID;
        String password;

        public Account(String ID, String password)
        {
            this.ID = ID;
            this.password = password;
        }

        public String Hash(String content) throws NoSuchAlgorithmException {
            final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
            return byteToHex(digest.digest(content.getBytes(StandardCharsets.UTF_8)));
        }
        private String byteToHex(byte[] hash)
        {
            BigInteger number = new BigInteger(1,hash);
            // Convert message digest into hex value
            StringBuilder hexString = new StringBuilder(number.toString(16));
            // Pad with leading zeros
            while (hexString.length() < 32)
            {
                hexString.insert(0, '0');
            }
            return hexString.toString();
        }
    }
    // #endregion

    /* **************************************** */
    // #region Protected Fields
    protected Account account;
    protected String name;
    protected String address;
    protected byte age;
    protected String phoneNumber;
    protected String gender;
    // #endregion

    /* **************************************** */
    // #region Private Fields

    // #endregion

    /* **************************************** */
    // #region Public Fields

    // #endregion

    /* **************************************** */

    //#region Getters/Setters methods
    public String GetName() {
        return name;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public byte GetAge() {
        return age;
    }

    public void SetAge(byte age) {
        this.age = age;
    }

    public String GetPhoneNumber() {
        return phoneNumber;
    }

    public void SetPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String GetGender() {
        return gender;
    }

    public void SetGender(String gender) {
        this.gender = gender;
    }

    public String GetID() {
        return account.ID;
    }

    public String GetPassword() {
        return account.password;
    }
    //#endregion

    /* **************************************** */
    //#region Public Methods
    public Actor() {
        account = new Account("", "");
    }
    public abstract ActorType GetActorType();
    public abstract Actor SignIn(String id, String password, Consumer<String> signInFailed);
    public abstract Actor SignUp(String id,String password,String name,String phoneNumber,String address,Consumer<String> signInFailed);
    public abstract void Display();
    // #endregion

    //#region Public virtual methods for customer
    public void AddToCard(String productId){ }
    public void RemoveItemFromCard(String productId){ }
    public HashMap<String, Product> GetMyCard() { return null; }
    //#endregion
    /* **************************************** */
    //#region Private Methods

    //#endregion
}
