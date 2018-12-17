package ProdConsPattern.Parsers;

import ProdConsPattern.Analyzer;
import ProdConsPattern.entities.Song;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

                try {
                    text = Jsoup.connect(link.attr("abs:href")).get();

                   queue.put(new Song(getSongName(text), getSongGenre(text), getSongText(text)));


                } catch (IOException e) {

                    System.out.println("error");
                    e.printStackTrace();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }




        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();

        }



    }
}

