package ProdConsPattern.Parsers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;


public class Parser {
    private final LinkedBlockingQueue<Integer> queue;
    private final ExecutorService parsers;
    private final AtomicBoolean stop;

    public Parser(LinkedBlockingQueue<Integer> queue, AtomicBoolean stop) {
        this.parsers = Executors.newFixedThreadPool(4);

        this.queue = queue;

        this.stop = stop;
    }


    public void parsing() {
        for (int i = 0; i < 4; i++) {
            final Thread parser = new Thread(new Runnable() {
                private final Random random=    new Random();
                @Override
                public void run() {
                    while (!stop.get()) {
                        queue.add(this.random.nextInt());
                        try {
                            Thread.sleep(100);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.err.println("========================================");
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
