package ProdConsPattern;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Analyzer {
    private final LinkedBlockingQueue<String> queue;
    private final ExecutorService analizers;
    private final AtomicBoolean stop;

    public Analyzer(LinkedBlockingQueue<String> queue, AtomicBoolean stop) {
        this.analizers = Executors.newFixedThreadPool(4);

        this.queue = queue;

        this.stop = stop;
    }

    void analizing() {
        for (int i = 0; i < 1; i++) {
            final Thread analizer = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!stop.get()) {

                        System.out.println(queue);

                        //System.out.println("____________________");
                    }
                }
            });
            this.analizers.submit(analizer);

        }

    }
}
