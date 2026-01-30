package adminmangementsystem.com;

public class Admin {
    private String username;
    private String password;

    public Admin(String u, String p) {
        username = u;
        password = p;
    }

    public boolean login(String u, String p) {
        return username.equals(u) && password.equals(p);
    }
}

