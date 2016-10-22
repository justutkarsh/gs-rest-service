package domain;

/**
 * Created by utkarsh on 14-08-2016.
 */
public class TrackDTO {

    private long id;
    private String name;
    private String artist;
    private int plays;
    private int likes;
    public TrackDTO() {

    }

    public TrackDTO(long id, String name, String artist, int plays, int likes) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.plays = plays;
        this.likes = likes;

    }

    public TrackDTO(String name) {
        this.name = name;
    }

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


}
