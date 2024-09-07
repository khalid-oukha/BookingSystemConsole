package Entities;

public class Client {
    private String cin;
    private String fullName;

    public Client(String cin, String fullName) {
        this.cin = cin;
        this.fullName = fullName;
    }
    public String getCin() {
        return cin;
    }
    public void setCin(String cin) {
        this.cin = cin;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
