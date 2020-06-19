/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author lenovo
 */
public class CommentairePub {

    private int id;
    private String publication_id;
    private String description;

    public CommentairePub() {
    }

    public CommentairePub(int id, String publication_id, String description) {
        this.id = id;
        this.publication_id = publication_id;
        this.description = description;
    }

    public CommentairePub(String publication_id, String description) {
        this.publication_id = publication_id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPublication_id() {
        return publication_id;
    }

    public void setPublication_id(String publication_id) {
        this.publication_id = publication_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CommentairePub{" + "id=" + id + ", publication_id=" + publication_id + ", description=" + description + '}';
    }

}
