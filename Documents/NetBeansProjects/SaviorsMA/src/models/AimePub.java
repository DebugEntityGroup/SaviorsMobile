package models;

public class AimePub {
    private  int publication_id;
    private int aime_id;

    public AimePub() {
    }

    public AimePub(int publication_id, int aime_id) {
        this.publication_id = publication_id;
        this.aime_id = aime_id;
    }

    public AimePub(int publication_id) {
        this.publication_id = publication_id;
    }

    public int getPublication_id() {
        return publication_id;
    }

    public int getAime_id() {
        return aime_id;
    }

    public void setPublication_id(int publication_id) {
        this.publication_id = publication_id;
    }

    public void setAime_id(int aime_id) {
        this.aime_id = aime_id;
    }
    

    @Override
    public String toString() {
        return "AimePub{" + "publication_id=" + publication_id + ", aime_id=" + aime_id + '}';
    }
    
    
}
