package org.wcci.albums.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @JsonIgnore
    @ManyToMany
    private List<Album> albums;
    @JsonIgnore
    @ManyToMany
    private List<Artist> artists;

    protected Tag(){}
    public Tag(String name) {
        this.name = name;

        albums = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addArtist(Artist artist) {
        if(artists == null){
            artists = new ArrayList<>();
        }
        artists.add(artist);
    }

    public void addAlbum(Album album) {
        albums.add(album);
    }

    public Long getId() {
        return id;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;

        Tag tag = (Tag) o;

        if (getId() != null ? !getId().equals(tag.getId()) : tag.getId() != null) return false;
        return getName() != null ? getName().equals(tag.getName()) : tag.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
