package ProdConsPattern.Parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {
    private final LinkedBlockingQueue<String> queue;
    private final ExecutorService parsers;
    private final AtomicBoolean stop;
    private final LinkedBlockingQueue<String> links;
    Document song;


    public Parser(LinkedBlockingQueue<String> queue, AtomicBoolean stop, LinkedBlockingQueue<String> links) {
        this.parsers = Executors.newFixedThreadPool(4);

        this.queue = queue;

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

        for (int i = 0; i < 3; i++) {
            final Thread parser = new Thread(new Runnable() {

                @Override
                public void run() {
                    while (!stop.get()) {

                        for (int j=1;j<parse(null)+1;j++){
                            Document pag=null;
                            try {
                                pag = Jsoup.connect("http://muzoton.ru/lastnews/page/"+j).get();
                                Elements urls= pag.getElementsByClass("cell cellsong");
                                queue.add(urls.html().replaceAll("<a href=\"","").replaceAll("\">.+",""));
                               // links.add(urls.html().replaceAll("<a href=\"","").replaceAll("\">.+",""));
                                //System.out.println(links);
                               /* for(String ql:links){

                                    try {
                                        song= Jsoup.connect(ql).get();
                                        queue.add(song.getElementsByClass("songtext").text());
                                    } catch (IOException e) {

                                        e.printStackTrace();

                                    }


                                }*/


                            } catch (IOException e) {

                                e.printStackTrace();

                            }

                        }

                        stop.set(true);

                    }

                    System.out.println("========================================");
                }
            });
            this.parsers.submit(parser);
        }
    }
}









/*



НОВИНКИ
В ней находим количество страниц для просмотра
в каждой странице достаю каждую песню
берем новинки
1- всего старниц
2-
*/
