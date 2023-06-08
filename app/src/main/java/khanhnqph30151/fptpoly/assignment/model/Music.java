package khanhnqph30151.fptpoly.assignment.model;

public class Music {
    private int idMusic;
    private String tenMusic;
    private String link;

    public Music() {
    }

    public Music(int idMusic, String tenMusic, String link) {
        this.idMusic = idMusic;
        this.tenMusic = tenMusic;
        this.link = link;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
