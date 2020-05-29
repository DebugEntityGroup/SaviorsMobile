package models;

import java.util.Objects;

public class ProduitComment {

    private int id;
    private String ProduitPending_id;
    private String content;
    private String User_id;
    public ProduitComment() {
    }

    public ProduitComment(int id, String content, String User_id) {
        this.id = id;
        this.content = content;
        this.User_id = User_id;
    }

    
    public ProduitComment(int id, String ProduitPending_id, String content, String User_id) {
        this.id = id;
        this.ProduitPending_id = ProduitPending_id;
        this.content = content;
        this.User_id = User_id;
    }

    public ProduitComment(String ProduitPending_id, String content, String User_id) {
        this.ProduitPending_id = ProduitPending_id;
        this.content = content;
        this.User_id = User_id;
    }

    @Override
    public String toString() {
        return "ProduitComment{" + "ProduitPending_id=" + ProduitPending_id + ", content=" + content + ", User_id=" + User_id + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduitPending_id() {
        return ProduitPending_id;
    }

    public void setProduitPending_id(String ProduitPending_id) {
        this.ProduitPending_id = ProduitPending_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser_id() {
        return User_id;
    }

    public void setUser_id(String User_id) {
        this.User_id = User_id;
    }


}
