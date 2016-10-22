package dao.entitiy;

import javax.persistence.*;

/**
 * Created by utkarsh on 14-08-2016.
 */
@Entity(name = "track")
public class Track {

    private long id;
    private String name;
    private String artist;
    private PlayList playList;
    private int plays;
    private int likes;

    public Track() {
    }

    public Track(String name, String artist) {
        this.name = name;
        this.artist = artist;
    }

    public Track(long id, String name, String artist, int plays, int likes) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.plays = plays;
        this.likes = likes;

    }

    @ManyToOne
    @JoinColumn
    public PlayList getPlayList() {
        return playList;
    }

    public void setPlayList(PlayList playList) {
        this.playList = playList;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getPlays() {
        return plays;
    }

    public void setPlays(int plays) {
        this.plays = plays;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", plays=" + plays +
                ", likes=" + likes +
                '}';
    }
}
