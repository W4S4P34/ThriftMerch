package DataController;

public enum ORDERSTATUS {
    PLACED(0),
    PROCESSED(1),
    DELIVERED(2);
    private final int value;
    ORDERSTATUS(int value) {
        this.value = value;
    }
    public int GetValue(){
        return value;
    }
    public static ORDERSTATUS SetValue(int value){
        for (ORDERSTATUS item : ORDERSTATUS.values()) {
            if(item.value == value){
                return item;
            }
        }
        return null;
    }
}
