package khanhnqph30151.fptpoly.assignment.model;

public class User {
    private String user;
    private String pass;
    private String avatar;

    public User() {
    }

    public User(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public User(String avatar) {
        this.avatar = avatar;
    }


    public User(String user, String pass, String avatar) {
        this.user = user;
        this.pass = pass;
        this.avatar = avatar;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
