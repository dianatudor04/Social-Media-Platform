package TemaTest;
import java.util.ArrayList;
import java.util.List;

public class Postare implements Likeable {
    private int idPostare;
    private String Post_text;
    private int nrLikes;
    private int nrComments;
    private Utilizator post_author;
    static int number_of_posts = 0; // static variable to keep track of the number of posts.
                                    // It is incremented each time a new post is created
    private List<Comentariu> comentarii = new ArrayList<Comentariu>();

    public Postare(String Post_text, Utilizator post_author) {
        this.Post_text = Post_text;
        this.post_author = post_author;
        this.nrLikes = 0;
        this.nrComments = 0;
        number_of_posts++;
        this.idPostare = number_of_posts;
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



}
