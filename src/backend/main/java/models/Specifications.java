package backend.main.java.models;

public class Specifications {

    private int id;
    private String commentText;
    
    public Specifications(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
