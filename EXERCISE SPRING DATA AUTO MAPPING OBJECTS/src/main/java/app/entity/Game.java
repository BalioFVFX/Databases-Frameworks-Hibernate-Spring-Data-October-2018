package app.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "games")
public class Game {
    private Long id;
    private String title;
    private String trailer;
    private String imageThumbnail;
    private BigDecimal size;
    private BigDecimal price;
    private String description;
    private LocalDate releaseDate;
    private List<User> users;

    public Game() {
    }

    public Game(String title, BigDecimal size, BigDecimal price, String description) {
        this.title = title;
        this.size = size;
        this.price = price;
        this.description = description;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Length(min = 3, max = 100)
    @Pattern(regexp = "[A-Z]+[\\w\\W]+", message = "Invalid title")
    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Column(name = "trailer")
    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @Pattern(regexp = "^(https:\\/\\/[\\w\\W]+)+|(http:\\/\\/[\\w\\W]+)", message = "Invalid picture url")
    @Column(name = "image_thumbnail")
    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public void setImageThumbnail(String imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    @Min(1)
    @Column(nullable = false, columnDefinition = "Decimal(19, 1)")
    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    @Min(1)
    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Length(min = 20)
    @Column(nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "release_date")
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @ManyToMany(targetEntity = User.class, mappedBy = "games", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
