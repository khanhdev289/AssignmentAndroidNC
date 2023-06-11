package khanhnqph30151.fptpoly.assignment.model;

public class Favorite {
    private int idMusic;
    private String tenMusic;

    public Favorite() {
    }

    public Favorite(String tenMusic) {
        this.tenMusic = tenMusic;
    }

    public Favorite(int idMusic, String tenMusic) {
        this.idMusic = idMusic;
        this.tenMusic = tenMusic;

    }

    public int getIdMusic() {
        return idMusic;
    }

    public void setIdMusic(int idMusic) {
        this.idMusic = idMusic;
    }

    public String getTenMusic() {
        return tenMusic;
    }

    public void setTenMusic(String tenMusic) {
        this.tenMusic = tenMusic;
    }

}
