package ProdConsPattern;

import ProdConsPattern.entities.Song;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Analyzer {
    private final LinkedBlockingQueue<Song> queue;


    //private final ExecutorService analizers;


    //Map<String, Integer> sontex = new LinkedHashMap<String, Integer>();
    Map<String, Map<String, Integer>> sontex = new LinkedHashMap<>();


    public Analyzer(LinkedBlockingQueue<Song> queue) {
        //  this.analizers = Executors.newFixedThreadPool(4);

        this.queue = queue;

    }


    void analizing() {



    }
}

       /* try (FileOutputStream fos = new FileOutputStream("C://Users/Михаил/Desktop/tets/tes2t5.txt")) {
            // перевод строки в байты
            byte[] buffer = sontex.toString().getBytes();

            fos.write(buffer, 1, buffer.length - 2);
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }*/


     /*   // System.out.println(queue);
        for (int str = 0; str < queue.size(); str++) {
            LinkedHashMap<String, Integer> occurrences = new LinkedHashMap<String, Integer>();


            for (String word : queue.peek().toString().toLowerCase().split(" ")) {
                Integer oldCount = occurrences.get(word);
                if (oldCount == null) {
                    oldCount = 0;
                }
                if (word.length() > 3)
                    occurrences.put(word, oldCount + 1);

            }

            List<Map.Entry<String, Integer>> entries =
                    new ArrayList<Map.Entry<String, Integer>>(occurrences.entrySet());
            Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
                public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                    return a.getValue().compareTo(b.getValue());
                }
            });

            Collections.reverse(entries);

            Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
            for (Map.Entry<String, Integer> entry : entries) {
                sortedMap.put(entry.getKey(), entry.getValue());
            }
            Map<String, Integer> endMap = new LinkedHashMap<String, Integer>();

            endMap.putAll(sortedMap);


            sontex.put(endMap);
         




            queue.poll();


        }

        try (FileOutputStream fos = new FileOutputStream("C://Users/Михаил/Desktop/tets/tes2t5.txt")) {
            // перевод строки в байты
            byte[] buffer = sontex.toString().getBytes();

            fos.write(buffer, 1, buffer.length - 2);
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }*/


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

