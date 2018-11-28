package ProdConsPattern.Parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {
    private final LinkedBlockingQueue<Integer> queue;
    private final ExecutorService parsers;
    private final AtomicBoolean stop;

    public Parser(LinkedBlockingQueue<Integer> queue, AtomicBoolean stop) {
        this.parsers = Executors.newFixedThreadPool(4);

        this.queue = queue;

        this.stop = stop;
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
        int pageNumb =Integer.parseInt(words.get(words.size()-1));
        return pageNumb;
    }





    public void parsing() {

        for (int i = 0; i < 4; i++) {
            final Thread parser = new Thread(new Runnable() {
                private final Random random=    new Random();
                @Override
                public void run() {
                    while (!stop.get()) {

                        for (int j=1;j<parse(null)-1;j++){
                            Document pag;
                            try {
                                pag = Jsoup.connect("http://muzoton.ru/lastnews/page/"+j).get();
                                Elements urls= new Elements();
                                urls=pag.getElementsByTag("href");
                            } catch (IOException e) {

                                e.printStackTrace();

                            }
                        }

                        try {
                            Thread.sleep(100);


                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
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
