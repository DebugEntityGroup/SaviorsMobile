package models;

public class Forum {
    private int id ;
    private String sujet;
    private String description;
    private String Categorie;
    private int userId;

    
        public Forum( String sujet, String description, String Categorie, int userId) {
        this.sujet = sujet;
        this.description = description;
        this.Categorie = Categorie;
        this.userId = userId;
    }
    
    public Forum(int id, String sujet, String description, String Categorie, int userId) {
        this.id = id;
        this.sujet = sujet;
        this.description = description;
        this.Categorie = Categorie;
        this.userId = userId;
    }
    

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String Categorie) {
        this.Categorie = Categorie;
    }

    @Override
    public String toString() {
        return "Forum{" + "id=" + id + ", sujet=" + sujet + ", description=" + description + ", Categorie=" + Categorie + ", userId=" + userId + '}';
    }

    public Forum() {
    }

    public Forum(String sujet, String description, String Categorie) {
        this.sujet = sujet;
        this.description = description;
        this.Categorie = Categorie;
    }


    
}
