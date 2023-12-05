package TemaTest;

public class Comentariu implements Likeable {
    private int idComentariu;
    private int nrLikes;
    static int valoare = 0;
    private String textComentariu;


    public Comentariu(int idComentariu, String textComentariu) {
        this.idComentariu += valoare;
        this.textComentariu = textComentariu;
        valoare++;
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
