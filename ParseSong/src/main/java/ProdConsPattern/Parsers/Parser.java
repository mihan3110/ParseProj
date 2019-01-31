package ProdConsPattern.Parsers;


import ProdConsPattern.entities.Song;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;


public class Parser {
    private final LinkedBlockingQueue<Song> queue;


    Document text;
    private int numbPage;


    public Parser(int numbPage, LinkedBlockingQueue<Song> queue) {
        this.numbPage = numbPage;

        this.queue = queue;

    }


    private String getSongName(Document text) {
        String name = text.getElementsByTag("h1").tagName("a").text().replaceAll("текст песни", "").trim();
        return name;
    }

    private String getSongText(Document text) {
        String song = text.getElementsByClass("songtext").text().replaceAll("[!^+*?/.>_<#,$%“”@&)…(\"\\]«—\\[»]", "").trim();
        return song;
    }

    private String getSongGenre(Document text) {
        String genre = text.getElementsByClass("genre").tagName("a").text().substring(0, text.getElementsByClass("genre").tagName("a").text().indexOf(",")).trim();
        return genre;
    }





    public void parse() {

        try {

            Document pag = Jsoup.connect("http://muzoton.ru/lastnews/page/" + numbPage).userAgent("Mozilla/5.0 AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.4 Safari/537.36").get();

            Elements links = pag.getElementsByClass("cell cellsong");



            for (int i = 0; i < links.size(); i++) {

                Element link = links.get(i).select("a").first();


                    text = Jsoup.connect(link.attr("abs:href")).get();
Song song = new Song(getSongName(text), getSongGenre(text), getSongText(text));
                    queue.put(song);




            }




        } catch (IOException |InterruptedException e) {
            System.out.println("error");
            e.printStackTrace();

        }



    }
}
