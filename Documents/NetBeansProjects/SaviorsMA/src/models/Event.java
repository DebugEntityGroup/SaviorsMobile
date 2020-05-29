/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Jasser
 */
public class Event {
    
    private int id ; 
    private String nom ;
    private String description ; 
    private int nbrInterest;
    private String lieu ; 
    private String dateevent ;
    private String image ; 
    private int moyenne ;
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbrInterest() {
        return nbrInterest;
    }

    public void setNbrInterest(int nbrInterest) {
        this.nbrInterest = nbrInterest;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDateevent() {
        return dateevent;
    }

    public void setDateevent(String dateevent) {
        this.dateevent = dateevent;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(int moyenne) {
        this.moyenne = moyenne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Event() {
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", nbrInterest=" + nbrInterest + ", lieu=" + lieu + ", dateevent=" + dateevent + ", image=" + image + ", username="+user+" moyenne=" + moyenne + '}';
    }

    


    
}
