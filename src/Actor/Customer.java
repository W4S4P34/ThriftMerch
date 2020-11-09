package Actor;

import DataController.Post;

public class Customer extends Actor {

    public void LoadMenuGUI() {
        // Load Menu GUI
        System.out.println("\t\t\tTHIS IS CUSTOMER MENU");
    }

    //#region General Methods
    public void ShowAllPostGUI(){
        // Call DataHandler to get all post
    }
    public void Report(Post post){

    }
    //#endregion

    //#region Seller Methods
    public void CreatePostGUI(){
        // Create Post

        // Call DataHandler add post

    }
    public void ShowMyPostGUI(){
        // Call DataHandler to get my post

        // Show my post in UI
    }
    public void ModifyPostGUI(){
        // Call DataHandler to get my post

        // Modify my post
    }
    //#endregion

    //#region Buyer Methods
    public void OrderGUI(){

    }

    //#endregion
}
