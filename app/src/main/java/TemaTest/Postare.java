package TemaTest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Postare implements Likeable {
    private int idPostare;
    private String Post_text;
    private int nrLikes;
    private int nrComments;
    private Utilizator post_author;
    private Date post_date;
    static int val_post = 0;
    private List<Comentariu> comentarii = new ArrayList<Comentariu>();

    public Postare(String Post_text, Utilizator post_author, Date post_date) {
        this.Post_text = Post_text;
        this.post_author = post_author;
        this.post_date = post_date;
        this.nrLikes = 0;
        this.nrComments = 0;
        this.idPostare += val_post;
        val_post++;
    }

    public int getIdPostare() {
        return idPostare;
    }

    public String getPost_text() {
        return Post_text;
    }

    public int getNrLikes() {
        return nrLikes;
    }

    public int getNrComments() {
        return nrComments;
    }

    public Utilizator getPost_author() {
        return post_author;
    }

    public Date getPost_date() {
        return post_date;
    }

    public static int getVal_post() {
        return val_post;
    }

    public List<Comentariu> getComentarii() {
        return comentarii;
    }

    public void setIdPostare(int idPostare) {
        this.idPostare = idPostare;
    }

    public void setPost_text(String post_text) {
        Post_text = post_text;
    }

    public void setNrLikes(int nrLikes) {
        this.nrLikes = nrLikes;
    }

    public void setNrComments(int nrComments) {
        this.nrComments = nrComments;
    }

    public void setPost_author(Utilizator post_author) {
        this.post_author = post_author;
    }

    public void setPost_date(Date post_date) {
        this.post_date = post_date;
    }

    public void setComentarii(List<Comentariu> comentarii) {
        this.comentarii = comentarii;
    }

    public void like() {
        nrLikes++;
    }

    @Override
    public void dislike() {
        if (nrLikes > 0) {
            nrLikes--;
        }
    }


    public void addComment(Comentariu c) {
        comentarii.add(c);
        nrComments++;
    }

    public void removeComment(Comentariu c) {
        comentarii.remove(c);
        nrComments--;
    }


}