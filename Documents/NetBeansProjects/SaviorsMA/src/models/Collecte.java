package models;

public class Collecte {
    
    private int id;
    private String nomCollecte;
    private int Budget;
    private int nbreAtteint;
    private String descriptionCollecte;
    private int nbreParticipantsC;
    private String image;
    private String catégorie;
    private int user_id;
    private String username;

    public Collecte() {
    }

    public Collecte(int id, String nomCollecte, int Budget, int nbreAtteint, String descriptionCollecte, int nbreParticipantsC, String image, String catégorie) {
        this.id = id;
        this.nomCollecte = nomCollecte;
        this.Budget = Budget;
        this.nbreAtteint = nbreAtteint;
        this.descriptionCollecte = descriptionCollecte;
        this.nbreParticipantsC = nbreParticipantsC;
        this.image = image;
        this.catégorie = catégorie;
    }

    public Collecte(String nomCollecte, int Budget, int nbreAtteint, String descriptionCollecte, int nbreParticipantsC, String image, String catégorie, int user_id) {
        this.nomCollecte = nomCollecte;
        this.Budget = Budget;
        this.nbreAtteint = nbreAtteint;
        this.descriptionCollecte = descriptionCollecte;
        this.nbreParticipantsC = nbreParticipantsC;
        this.image = image;
        this.catégorie = catégorie;
        this.user_id = user_id;
    }
    
    public Collecte(int id, String nomCollecte, int Budget, int nbreAtteint, String descriptionCollecte, int nbreParticipantsC, String image, String catégorie, int user_id) {
        this.id = id;
        this.nomCollecte = nomCollecte;
        this.Budget = Budget;
        this.nbreAtteint = nbreAtteint;
        this.descriptionCollecte = descriptionCollecte;
        this.nbreParticipantsC = nbreParticipantsC;
        this.image = image;
        this.catégorie = catégorie;
        this.user_id = user_id;
    }
    
    public Collecte(String nomCollecte, int Budget, int nbreAtteint, String descriptionCollecte, int nbreParticipantsC, String image, String catégorie) {
        this.nomCollecte = nomCollecte;
        this.Budget = Budget;
        this.nbreAtteint = nbreAtteint;
        this.descriptionCollecte = descriptionCollecte;
        this.nbreParticipantsC = nbreParticipantsC;
        this.image = image;
        this.catégorie = catégorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomCollecte() {
        return nomCollecte;
    }

    public void setNomCollecte(String nomCollecte) {
        this.nomCollecte = nomCollecte;
    }

    public int getBudget() {
        return Budget;
    }

    public void setBudget(int Budget) {
        this.Budget = Budget;
    }

    public int getNbreAtteint() {
        return nbreAtteint;
    }

    public void setNbreAtteint(int nbreAtteint) {
        this.nbreAtteint = nbreAtteint;
    }

    public String getDescriptionCollecte() {
        return descriptionCollecte;
    }

    public void setDescriptionCollecte(String descriptionCollecte) {
        this.descriptionCollecte = descriptionCollecte;
    }

    public int getNbreParticipantsC() {
        return nbreParticipantsC;
    }

    public void setNbreParticipantsC(int nbreParticipantsC) {
        this.nbreParticipantsC = nbreParticipantsC;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCatégorie() {
        return catégorie;
    }

    public void setCatégorie(String catégorie) {
        this.catégorie = catégorie;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Collecte{" + "id=" + id + ", nomCollecte=" + nomCollecte + ", Budget=" + Budget + ", nbreAtteint=" + nbreAtteint + ", descriptionCollecte=" + descriptionCollecte + ", nbreParticipantsC=" + nbreParticipantsC + ", image=" + image + ", cat\u00e9gorie=" + catégorie + '}';
    }
    
}
