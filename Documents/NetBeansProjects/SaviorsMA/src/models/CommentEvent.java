package models;

public class CommentEvent {

    private int id;
    private String text;
    private int inEvent;
    private String username;
    private String nomEvent;

    public String getNomEvent() {
        return nomEvent;
    }

    public void setNomEvent(String nomEvent) {
        this.nomEvent = nomEvent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getInEvent() {
        return inEvent;
    }

    public void setInEvent(int inEvent) {
        this.inEvent = inEvent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public CommentEvent() {
    }

    @Override
    public String toString() {
        return "CommentEvent{" + "id=" + id + ", text=" + text + ", inEvent=" + inEvent + ", username=" + username + '}';
    }

}
