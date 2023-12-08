package TemaTest;

public class Comentariu implements Likeable {
    private int idComentariu;
    private int IdPostare;
    private int nrLikes;
    static int number_of_comments = 0;// static variable to keep track of the number of comments.
                                      // It is incremented each time a new comment is created
    private String textComentariu;
    private Utilizator comentator;


    public Comentariu(int IdPostare, String textComentariu, Utilizator comentator) {
        number_of_comments++;
        this.idComentariu = number_of_comments;
        this.IdPostare = IdPostare;
        this.textComentariu = textComentariu;
        this.comentator = comentator;
    }

    public int getIdComentariu() {
        return idComentariu;
    }

    public int getIdPostare() {
        return IdPostare;
    }

    public String getTextComentariu() {
        return textComentariu;
    }

    public Utilizator getComentator() {
        return comentator;
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
