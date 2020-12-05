package Misc;

public enum ActorType {
	SHOP(0), CUSTOMER(1), SHIPPER(2);

    private final int value;
    private ActorType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
