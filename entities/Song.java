package ProdConsPattern.entities;

public class Song {
    private String text;
    private String name;


    public Song(String name, String text) {
        this.text = text;
        this.name = name;

    }


    @Override
    public String toString() {
        return "Song{" +
                ", name='" + name + '\'' +
                "text='" + text + '\'' +

                '}';
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
