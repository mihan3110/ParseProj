package ProdConsPattern;

import ProdConsPattern.entities.Song;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

public class Analyzer {
    private final LinkedBlockingQueue<Song> queue;


    public Analyzer(LinkedBlockingQueue<Song> queue) {
        this.queue = queue;
    }

    void analizing() {

        //System.out.println(queue.size());
       /*
        try {
            Song song = queue.take();

        String text = song.getText().toLowerCase();
        Map<String, Integer> count = new HashMap<>();



        for (String word : text.split(" ")) {
            Integer oldCount = count.get(word);
            if (oldCount == null) {
                oldCount = 0;
            }
            if (word.length() > 3)
                count.put(word, oldCount + 1);

        }


        LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();


        count.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
        song.setText(reverseSortedMap.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
      //System.out.println(song.getName());



    }


}






























     /*   //
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


/////////////////////////////////////////////////////////////

/*


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


            sontex.putAll(endMap);





            queue.poll();


        }

        System.out.println(sontex);
 */