package ProdConsPattern.entities;

public class Song {
    private String text;
    private String name;
    private String genre;


    public Song(String name, String genre, String text) {
        this.text = text;
        this.name = name;
        this.genre = genre;

    }


    @Override
    public String toString() {
        return "Song{" +

                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                "text='" + text + '\'' +
                '}';
    }

    public String getGenre() {
        return genre;
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


}
