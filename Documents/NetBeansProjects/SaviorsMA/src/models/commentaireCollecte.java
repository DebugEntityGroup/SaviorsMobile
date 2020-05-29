package models;

public class commentaireCollecte {
    
    private int id;
    private String contenu;
    private String user_id;
    private String collectP;

    public commentaireCollecte() {
    }

    public commentaireCollecte(String contenu, String user_id, String collectP) {
        this.contenu = contenu;
        this.user_id = user_id;
        this.collectP = collectP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCollectP() {
        return collectP;
    }

    public void setCollectP(String collectP) {
        this.collectP = collectP;
    }

    @Override
    public String toString() {
        return "commentaireCollecte{" + "id=" + id + ", contenu=" + contenu + ", user_id=" + user_id + ", collectP=" + collectP + '}';
    }
    
}
