package TemaTest;

public class Utilizator {
    private String username;
    private String password;
    static int val_user = 0;

    public Utilizator(String username, String password) {
        this.username = username;
        this.password = password;
        val_user++;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}