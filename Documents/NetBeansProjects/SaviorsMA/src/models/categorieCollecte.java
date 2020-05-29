package models;

public class categorieCollecte {
    
    private String typeCategorie;
    private int user_id;
    private String username;

    public categorieCollecte(String typeCategorie, String username) {
        this.typeCategorie = typeCategorie;
        this.username = username;
    }

    public categorieCollecte(String typeCategorie, int user_id) {
        this.typeCategorie = typeCategorie;
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTypeCategorie() {
        return typeCategorie;
    }

    public void setTypeCategorie(String typeCategorie) {
        this.typeCategorie = typeCategorie;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return typeCategorie;
    }
    
}
