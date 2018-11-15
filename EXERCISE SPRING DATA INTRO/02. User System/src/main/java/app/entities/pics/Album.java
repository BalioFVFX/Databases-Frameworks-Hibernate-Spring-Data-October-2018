package app.entities.pics;

import app.entities.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "albums")
public class Album extends BaseEntity {
    private String name;
    private String backgroundColor;
    private boolean isPublic;
    private Set<Picture> pictures;

    public Album() {
    }

    public Album(String name, String backgroundColor) {
        this.name = name;
        this.backgroundColor = backgroundColor;
        this.pictures = new HashSet<Picture>();
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "background_color")
    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @Column
    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    @ManyToMany(targetEntity = Picture.class)
    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }
}
