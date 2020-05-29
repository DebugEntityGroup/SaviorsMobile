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
public class Rate {
    
    private int id ; 
    private int note ; 
    private int inEvent ; 
    private int idUser ; 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getInEvent() {
        return inEvent;
    }

    public void setInEvent(int inEvent) {
        this.inEvent = inEvent;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Rate() {
    }

    @Override
    public String toString() {
        return "Rate{" + "id=" + id + ", note=" + note + ", inEvent=" + inEvent + ", idUser=" + idUser + '}';
    }
    
    
    
    
}
