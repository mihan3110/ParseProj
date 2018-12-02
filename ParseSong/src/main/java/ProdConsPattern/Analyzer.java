package ProdConsPattern;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Analyzer {
    private final LinkedBlockingQueue<String> queue;
    // private final ExecutorService analizers;
    private final AtomicBoolean stop;

    public Analyzer(LinkedBlockingQueue<String> queue, AtomicBoolean stop) {
        //  this.analizers = Executors.newFixedThreadPool(4);

        this.queue = queue;

        this.stop = stop;
    }


    void analizing() {
        List<String> lst = new ArrayList<>();

        // System.out.println(queue);
        for (int str = 0; str < queue.size(); str++) {

            Map<String, Integer> occurrences = new HashMap<String, Integer>();

            for ( String word : queue.peek().split(" ") ) {
                Integer oldCount = occurrences.get(word);
                if ( oldCount == null ) {
                    oldCount = 0;
                }
                occurrences.put(word, oldCount + 1);
            }
            queue.poll();


            try (FileOutputStream fos = new FileOutputStream("C://Users/Михаил/Desktop/tets/tes2t5.txt")) {
                // перевод строки в байты
                byte[] buffer = occurrences.toString().replaceAll(",", "\n").replaceAll(" ", "").getBytes();

                fos.write(buffer, 1, buffer.length - 2);
            } catch (IOException ex) {

                System.out.println(ex.getMessage());
            }


        }
        // System.out.println(lst);
    }
}

        /*for (int i = 0; i < 1; i++) {
            final Thread analizer = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!stop.get()) {


                       // System.out.println(queue);

                        //System.out.println("____________________");
                    }
                }
            });
            this.analizers.submit(analizer);
            Thread.interrupted();
        }*/

