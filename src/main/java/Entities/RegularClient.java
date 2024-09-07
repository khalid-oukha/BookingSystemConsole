package Entities;

public class RegularClient extends Client {
    private int loyaltyPoints;

    public RegularClient(String cin, String fullName, int loyaltyPoints) {
        super(cin, fullName);
        this.loyaltyPoints = loyaltyPoints;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }
}
