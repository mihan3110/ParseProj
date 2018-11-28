package ProdConsPattern;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Analyzer {
    private final LinkedBlockingQueue<Integer> queue;
    private final ExecutorService analizers;
    private final AtomicBoolean stop;

    public Analyzer(LinkedBlockingQueue<Integer> queue, AtomicBoolean stop) {
        this.analizers = Executors.newFixedThreadPool(4);

        this.queue = queue;

        this.stop = stop;
    }

    void analizing() {
        for (int i = 0; i < 4; i++) {
            final Thread analizer = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!stop.get()) {

                        System.out.println("____________________");
                    }
                }
            });
            this.analizers.submit(analizer);
        }

    }
}
