package models;

public class DonCollecte {
    
    private int id;
    private String username;
    private int moneyDonated;
    private String dateHour;
    private String collectPending;
    
    public DonCollecte() {
    }

    public DonCollecte(int id, String username, int moneyDonated, String dateHour, String collectPending) {
        this.id = id;
        this.username = username;
        this.moneyDonated = moneyDonated;
        this.dateHour = dateHour;
        this.collectPending = collectPending;
    }

    public DonCollecte(String username, int moneyDonated, String dateHour, String collectPending) {
        this.username = username;
        this.moneyDonated = moneyDonated;
        this.dateHour = dateHour;
        this.collectPending = collectPending;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getMoneyDonated() {
        return moneyDonated;
    }

    public void setMoneyDonated(int moneyDonated) {
        this.moneyDonated = moneyDonated;
    }

    public String getDateHour() {
        return dateHour;
    }

    public void setDateHour(String dateHour) {
        this.dateHour = dateHour;
    }

    public String getCollectPending() {
        return collectPending;
    }

    public void setCollectPending(String collectPending) {
        this.collectPending = collectPending;
    }

    @Override
    public String toString() {
        return "DonCollecte{" + "id=" + id + ", username=" + username + ", moneyDonated=" + moneyDonated + ", dateHour=" + dateHour + ", collectPending=" + collectPending + '}';
    }
    
}
