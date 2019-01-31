package ProdConsPattern.entities;


import myOrmTest.annotations.Column;
import myOrmTest.annotations.Entity;
import myOrmTest.annotations.Id;

@Entity(name = "songs")
public class Song {

    @Id
    private Long id;
    @Column(name = "text", length = 10000)
    private String text;
    @Column(name = "name", length = 300)
    private String name;
    @Column(name = "genre", length = 100)
    private String genre;
    @Column(name = "top", length = 1000)
    private String top;


    @Column(name = "link", length = 1000)
    private String link;

    public Song() {

    }

    public Song(String text, String name, String genre, String top, String link) {
        this.text = text;
        this.name = name;
        this.genre = genre;
        this.top = top;
        this.link = link;
    }

    public Song(String name, String genre, String text) {

        this.name = name;
        this.genre = genre;
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }
}
