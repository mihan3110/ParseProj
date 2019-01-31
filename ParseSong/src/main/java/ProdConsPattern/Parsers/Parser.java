package ProdConsPattern.Parsers;


import ProdConsPattern.Analyzer;
import ProdConsPattern.entities.Song;
import myOrmTest.db.DbConnect;
import myOrmTest.db.EntityManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;


public class Parser {
    private final LinkedBlockingQueue<Song> queue;
    private final ExecutorService analizers = Executors.newFixedThreadPool(4);

    Document text;
    private int numbPage;


    public Parser(int numbPage, LinkedBlockingQueue<Song> queue) {
        this.numbPage = numbPage;

        this.queue = queue;

    }


    private String getSongName(Document text) {
        String name = text.getElementsByTag("h1").tagName("a").text().replaceAll("текст песни", "").replaceAll("'", "").trim();
        return name;
    }

    private String getSongText(Document text) {
        String song = text.getElementsByClass("songtext").text().replaceAll("[!^+*?/.>_<#,'$%“”@&)…(\"\\]«—\\[»]", "").trim();
        return song;
    }

    private String getSongGenre(Document text) {
        String genre = text.getElementsByClass("genre").tagName("a").text().substring(0, text.getElementsByClass("genre").tagName("a").text().indexOf(",")).replaceAll("'","").trim();
        return genre;
    }





    public void parse() {
        EntityManager em = null;
        Connection connection = null;


        try {

            Document pag = Jsoup.connect("http://muzoton.ru/lastnews/page/" + numbPage).userAgent("Mozilla/5.0 AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.4 Safari/537.36").get();

            Elements links = pag.getElementsByClass("cell cellsong");

            DbConnect.initConnection("mysql", "root", "123456", "localhost", "3306", "myParser");

            connection = DbConnect.getConnection();

            em = new EntityManager(connection);

            for (int i = 0; i < links.size(); i++) {

                Element link = links.get(i).select("a").first();



                    text = Jsoup.connect(link.attr("abs:href")).get();

Song song = new Song(getSongName(text), getSongGenre(text), getSongText(text));
song.setLink(link.attr("abs:href"));

                    queue.put(song);




            }




        } catch (IOException |InterruptedException | SQLException e) {
            System.out.println("error");
            e.printStackTrace();

        }


        Analyzer analyzer1 = new Analyzer(queue);
        Future<?> submit1 = analizers.submit(analyzer1::analizing);
       analizers.shutdown();

    }
}
