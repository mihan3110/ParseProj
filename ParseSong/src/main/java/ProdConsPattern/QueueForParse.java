package ProdConsPattern;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ProdConsPattern.Parsers.Parser;
import ProdConsPattern.entities.Song;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class QueueForParse {
    private final LinkedBlockingQueue<Song> queue;
    private final ExecutorService parsers = Executors.newFixedThreadPool(4);


    private final ExecutorService analizers = Executors.newFixedThreadPool(4);

    QueueForParse() {

        this.queue = new LinkedBlockingQueue<>();

    }


    public void start() throws InterruptedException {
        //final Parser parser = new Parser(queue);


        List<Future> excp = new ArrayList<>();

        for (int i = 1; i <= getPagesCount(); i++) {

            Parser parser1 = new Parser(i, queue);


            Future<?> submit = parsers.submit(parser1::parse);
            //Аналзируем очередь после каждой прогонки по странице парсером
            Analyzer analyzer1 = new Analyzer(queue);
            Future<?> submit1 = analizers.submit(analyzer1::analizing);

        }
        parsers.shutdown();
analizers.shutdown();





        excp.forEach(it -> {
            try {
                it.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(excp);
        });



    }


    public int getPagesCount() {
        try {
            Document doc = Jsoup.connect("http://muzoton.ru/lastnews/").get();
            Elements pages = doc.getElementById("dle-content").getElementsByTag("a");
            return Integer.parseInt(pages.get(pages.size() - 2).text());
        } catch (IOException e) {

            e.printStackTrace();
            return 0;
        }

    }

    public static void main(String[] args) throws InterruptedException {
        QueueForParse begin = new QueueForParse();
        begin.start();


    }

}
