package domain;

import java.util.Set;

/**
 * Created by utkarsh on 14-08-2016.
 */
public class PlayListDTO {

    private long id;
    private Set<TrackDTO> trackList;
    private Set<String> tags;
    private int plays;
    private int likes;

    public PlayListDTO() {
    }


    public PlayListDTO(long id, Set<TrackDTO> trackList, int plays, int count) {
        this.id = id;
        this.trackList = trackList;
        this.plays = plays;
    }

    public PlayListDTO(long id) {
        this.id = id;
    }

    public PlayListDTO(String id, PlayListDTO playList) {
        this.id = Long.parseLong(id);
        trackList = playList.getTrackList();
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<TrackDTO> getTrackList() {
        return trackList;
    }

    public void setTrackList(Set<TrackDTO> trackList) {
        this.trackList = trackList;
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
        return "PlayListDTO{" +
                "id=" + id +
                ", trackList=" + trackList +
                ", tags=" + tags +
                ", plays=" + plays +
                ", likes=" + likes +
                ", name='" + name + '\'' +
                '}';
    }
}
