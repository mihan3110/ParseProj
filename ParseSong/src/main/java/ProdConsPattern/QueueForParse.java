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


    public void start() {
        //final Parser parser = new Parser(queue);

        //todo получить количесво строниц







        List<Future> excp = new ArrayList<>();




        for (int i = 1; i <= getPagesCount(); i++) {

            Parser parser1 = new Parser(i, queue);

            parsers.submit(parser1::parse);
           // Future<?> submit = parsers.submit(parser1::parse);

        }

        // в циклей от 0 до N
        for (int i = 1; i <=queue.size(); i++) {

          Analyzer analyzer1 = new Analyzer(queue);
           // analyzer.analizing();
            analizers.submit(analyzer1::analizing);
          //  Future<?> submit = parsers.submit(analyzer1::analizing);

        }



        excp.forEach(it->{
                try{
                    it.get();
                }
                catch (InterruptedException | ExecutionException e){
                    e.printStackTrace();
                }
           // System.out.println(excp);
        });
    }

public int getPagesCount(){
    try {
        Document doc = Jsoup.connect("http://muzoton.ru/lastnews/").get();
        Elements pages = doc.getElementById("dle-content").getElementsByTag("a");
        return Integer.parseInt(pages.get(pages.size() - 2).text());
    } catch (IOException e) {

       e.printStackTrace();
        return 0;
    }

}
    public static void main(String[] args) {
        QueueForParse begin = new QueueForParse();
        begin.start();


    }

}


///////////////////////////////////

/*
public BlockingQueue<String> queue;


public String parse(Document doc) {

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
System.out.println(pageNumb);






// name =name.substring(name.lastIndexOf(';')+1);
// String name = doc.getElementsByTag("h1").text();
//String text = doc.getElementsByClass("songtext").text();

return pages + "/";//+ text;
 */
