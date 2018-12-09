package ProdConsPattern.Parsers;

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

    private String getSongName(Document text){
        String name="\n"+text.getElementsByTag("h1").tagName("a").text().replaceAll("текст песни", "");
        return name;
    }

    private String getSongText(Document text){
        String  song=text.getElementsByClass("songtext").text().replaceAll("[!^+*/.>_<#,\\-$%“”@&)…(\"\\]«—\\[»]", "");
        return song;
    }

    private String getSongGenre(Document text){
        String genre=text.getElementsByClass("genre").tagName("a").text().substring(0,text.getElementsByClass("genre").tagName("a").text().indexOf(","));
        return genre;
    }
    public void parse() {



        try {

            Document pag = Jsoup.connect("http://muzoton.ru/lastnews/page/" + numbPage).get();

            Elements links = pag.getElementsByClass("cell cellsong");

            for (int i = 0; i < links.size(); i++) {

                Element link = links.get(i).select("a").first();
                try {
                    text = Jsoup.connect(link.attr("abs:href")).get();



                    queue.add(new Song(getSongName(text), getSongGenre(text), getSongText(text)));

                } catch (IOException e) {

                    e.printStackTrace();

                }

            }


        } catch (IOException e) {

            e.printStackTrace();

        }
        try (FileOutputStream fos = new FileOutputStream("C://Users/Михаил/Desktop/tets/tes2t5.txt")) {
            // перевод строки в байты
            byte[] buffer = queue.toString().getBytes();

            fos.write(buffer, 1, buffer.length - 2);
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
      //  System.out.println(queue);
    }
}

///запись в файл

 /* try (FileOutputStream fos = new FileOutputStream("C://Users/Михаил/Desktop/tets/tes2t.txt")) {
            // перевод строки в байты
            byte[] buffer = links.toString().getBytes();

            fos.write(buffer, 1, buffer.length - 2);
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }*/

//parse(null) + 1; j++) {



       /* Scanner file = null;
        try {
            file = new Scanner(new File("C://Users/Михаил/Desktop/tets/tes2t.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/






        /*for (int i = 0; i < 1; i++) {
            final Thread parser = new Thread(new Runnable() {

                @Override
                public void run() {
                    while (!stop.get()) {




                    }

                    System.out.println("========================================");
                }
            });
            this.parsers.submit(parser);
            Thread.interrupted();
        }*/










/*



НОВИНКИ
В ней находим количество страниц для просмотра
в каждой странице достаю каждую песню
берем новинки
1- всего старниц
2-
*/
