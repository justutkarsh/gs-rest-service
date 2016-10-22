package dao.entitiy;

import javax.persistence.*;
import java.util.Set;

@Entity
public class PlayList {

    private long id;
    private String name;
    private int likes;
    private int plays;
    private Set<String> tags;
    private Set<Track> tracks;
    //private Set<User> likedBy

    @ElementCollection(targetClass=String.class)
    public Set<String> getTags() {
        return tags;
    }

    public PlayList setTags(Set<String> tags) {
        this.tags = tags;
        return this;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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

    @OneToMany(mappedBy = "playList", cascade = CascadeType.ALL)
    public Set<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Set<Track> tracks) {
        this.tracks = tracks;
    }

    public int getLikes() {
        return likes;
    }

    public PlayList setLikes(int likes) {
        this.likes = likes;
        return this;
    }

    public int getPlays() {
        return plays;
    }

    public PlayList setPlays(int plays) {
        this.plays = plays;
        return  this;
    }

    public  PlayList() {}

    public PlayList(String firstName) {
        this.name = firstName;
    }

    @Override
    public String toString() {
        return "PlayList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", likes=" + likes +
                ", plays=" + plays +
                '}';
    }
}

