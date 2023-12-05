package TemaTest;

public class Utilizator {
    private String username;
    private String password;
    private int idUser;
    static int val_user = 0;

    public Utilizator(String username, String password) {
        this.username = username;
        this.password = password;
        this.idUser += val_user;
        val_user++;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
