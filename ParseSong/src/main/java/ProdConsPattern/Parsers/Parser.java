package ProdConsPattern.Parsers;

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
    private final LinkedBlockingQueue<String> queue;
    private final LinkedBlockingQueue<String> name;
    private final ExecutorService parsers;
    private final AtomicBoolean stop;
    private final LinkedBlockingQueue<String> links;
    Document text;


    public Parser(LinkedBlockingQueue<String> queue, AtomicBoolean stop, LinkedBlockingQueue<String> links, LinkedBlockingQueue<String> name) {
        this.parsers = Executors.newFixedThreadPool(4);

        this.queue = queue;
        this.name = name;
        this.stop = stop;
        this.links = links;
    }


    static int parse(Document doc) {

        try {
            doc = Jsoup.connect("http://muzoton.ru/lastnews/").get();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();

        }
        String pages = doc.getElementById("dle-content").getElementsByTag("a").text();
        Pattern pat = Pattern.compile("\\d+");
        Matcher matcher = pat.matcher(pages);
        List<String> words = new ArrayList<>();
        while (matcher.find()) {
            words.add(matcher.group());
        }
        int pageNumb = Integer.parseInt(words.get(words.size() - 1));
        return pageNumb;
    }


    public void parsing() {


        for (int j = 1; j < 2; j++) {             // parse(null) + 1; j++) {
            Document pag = null;
            try {
                pag = Jsoup.connect("http://muzoton.ru/lastnews/page/" + j).get();
                Elements urls = pag.getElementsByClass("cell cellsong");
                links.add(urls.html().replaceAll("<a href=\"", "").replaceAll("\">.+", ""));
                try (FileOutputStream fos = new FileOutputStream("C://Users/Михаил/Desktop/tets/tes2t.txt")) {
                    // перевод строки в байты
                    byte[] buffer = links.toString().replaceAll(",", "\n").replaceAll(" ", "").getBytes();

                    fos.write(buffer, 1, buffer.length - 2);
                } catch (IOException ex) {

                    System.out.println(ex.getMessage());
                }


            } catch (IOException e) {

                e.printStackTrace();

            }

        }


        Scanner file = null;
        try {
            file = new Scanner(new File("C://Users/Михаил/Desktop/tets/tes2t.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (file.hasNext()) {


            try {
                text = Jsoup.connect(file.nextLine()).get();
                name.add(text.getElementsByTag("h1").tagName("a").text().replaceAll("текст песни", ""));
                queue.add(text.getElementsByClass("songtext").text().replaceAll("[!^+*/.>_<#$%“”@&)…(\"\\]«—\\[»]", "").replaceAll(",|-", ""));


                queue.remove("");

                try (FileOutputStream fos = new FileOutputStream("C://Users/Михаил/Desktop/tets/test.txt")) {
                    // перевод строки в байты
                    byte[] buffer = queue.toString().getBytes();

                    fos.write(buffer, 1, buffer.length - 2);
                } catch (IOException ex) {

                    System.out.println(ex.getMessage());
                }
            } catch (IOException e) {

                e.printStackTrace();

            }

        }
        file.close();
    }
}

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
