package ProdConsPattern;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import ProdConsPattern.Parsers.Parser;

public class QueueForParse {
    private final LinkedBlockingQueue<String> queue;
    private final LinkedBlockingQueue<String> name;
    private final AtomicBoolean stop;
    private final LinkedBlockingQueue<String> links;

    QueueForParse() {

        this.stop = new AtomicBoolean(false);
        this.queue = new LinkedBlockingQueue<>();
        this.name=new LinkedBlockingQueue<>();
        this.links=new LinkedBlockingQueue<>();
    }





    public void start() {
        final Parser parser = new Parser(queue, stop, links, name);
        final Analyzer analyzer = new Analyzer(queue, name, stop);
        parser.parsing();
        analyzer.analizing();
        //final Timer timer = new Timer();
      //  timer.schedule(new Stopper(), 1000);
    }

   /* private class Stopper extends TimerTask {
        @Override
        public void run() {
            stop.set(true);
        }
    }*/

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
