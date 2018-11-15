package app.entities.pics;

import app.entities.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pics")
public class Picture extends BaseEntity {
    private String title;
    private String caption;
    private String path;
    private Set<Album> albums;
    public Picture() {
    }

    public Picture(String title, String caption) {
        this.title = title;
        this.caption = caption;
        this.albums = new HashSet<Album>();
    }

    @Column
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Column
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @ManyToMany(targetEntity = Album.class, mappedBy = "pictures", cascade = CascadeType.ALL)
    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
}
