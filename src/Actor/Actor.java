package Actor;

import DataController.*;
import Misc.ActorType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public abstract class Actor {
	/* **************************************** */
	// #region Account Inner Class
	class Account {
		String ID;
		String password;

		public Account(String ID, String password) {
			this.ID = ID;
			this.password = password;
		}
	}
	// #endregion

	/* **************************************** */
	// #region Protected Fields
	protected Account account;
	protected String name;
	protected String address;
	protected String phoneNumber;
	protected byte age;
	protected String gender;
	// #endregion

	/* **************************************** */
	// #region Private Fields

	// #endregion

	/* **************************************** */
	// #region Public Fields

	// #endregion

	/* **************************************** */

	// #region Getters/Setters methods
	public String GetName() {
		return name;
	}
	public void SetName(String name) {
		this.name = name;
	}
	public String GetPhoneNumber() {
		return phoneNumber;
	}
	public void SetPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String GetAddress(){
		return address;
	}
	public void SetAddress(String address){
		this.address = address;
	}
	public void SetAge(byte age){
		this.age = age;
	}
	public void SetGender(String gender){
		this.gender = gender;
	}
	public String GetID() {
		return account.ID;
	}
	public String GetPassword() {
		return account.password;
	}
	// #endregion

	/* **************************************** */
	// #region Public Methods
	public Actor() {
		account = new Account("", "");
	}

	public abstract ActorType GetActorType();

	public abstract Actor SignIn(String id, String password, Consumer<String> signInFailed);

	public abstract Actor SignUp(String id, String password, String name, String phoneNumber, String address,
			Consumer<String> signInFailed);

	public abstract void Display();
	// #endregion

	//#region Public virtual methods for customer
	public void AddToCard(String productId) {
	}
	public void RemoveItemFromCard(String productId) {
	}
	public HashMap<String, Product> GetMyCard() {
		return null;
	}
	public boolean MakeOrder(){ return false; }
	public ArrayList<Order> ViewMyOrder() { return null; }
	//#endregion

	//#region Public virtual methods for shipper
	public ArrayList<Order> ViewAllOrphanedOrder(){
		return null;
	}
	public void UpdateOrder(String orderId, ORDERSTATUS orderStatus) { }
	//#endregion

	//#region Public virtual methods for shop
	public void AddNewProduct(){

	}
	public void UpdateProduct(){

	}
	//#endregion

	/* **************************************** */
	// #region Private Methods

	// #endregion
}
