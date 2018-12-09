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
    private Elements links;

    private List<String> name = new ArrayList<>();
    private List<String> genre = new ArrayList<>();
    private List<String> song = new ArrayList<>();


    Document text;
    private int numbPage;


    public Parser(int numbPage, LinkedBlockingQueue<Song> queue) {
        this.numbPage = numbPage;

        this.queue = queue;

    }

    public void parse() {

        Document pag = null;

        try {

            pag = Jsoup.connect("http://muzoton.ru/lastnews/page/" + numbPage).get();

            links = pag.getElementsByClass("cell cellsong");
       //     System.out.println();
//.html().replaceAll("<a href=\"", "").replaceAll("\">.+", "") + "\n"
         //   links.add(urls);

           // System.out.println(links.get(0));
            //System.out.println(links);


        } catch (IOException e) {

            e.printStackTrace();

        }

       /* try (FileOutputStream fos = new FileOutputStream("C://Users/Михаил/Desktop/tets/tes2t.txt")) {
            // перевод строки в байты
            byte[] buffer = links.toString().getBytes();

            fos.write(buffer, 1, buffer.length - 2);
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }*/

        //  }


        // public void getText() {


       for (int i = 0; i < links.size(); i++) {


            try {
                text = Jsoup.connect(links.get(i).html().replaceAll("<a href=\"", "").replaceAll("\">.+", "")).get();

                name.add("\n" + text.getElementsByTag("h1").tagName("a").text().replaceAll("текст песни", ""));
                song.add(text.getElementsByClass("songtext").text().replaceAll("[!^+*/.>_<#,\\-$%“”@&)…(\"\\]«—\\[»]", ""));
                genre.add(text.getElementsByClass("genre").tagName("a").text().substring(0,text.getElementsByClass("genre").tagName("a").text().indexOf(",")));
                //System.out.println(genre);

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

        for (int i = 0; i < links.size(); i++) {
            queue.add(new Song(name.get(i), genre.get(i), song.get(i)));
           // System.out.println(queue);

           /* try (FileOutputStream fos = new FileOutputStream("C://Users/Михаил/Desktop/tets/test.txt")) {
                // перевод строки в байты
                byte[] buffer = queue.toString().getBytes();

                fos.write(buffer, 1, buffer.length - 2);
            } catch (IOException ex) {

                System.out.println(ex.getMessage());
            }*/


        }



    }
}

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
