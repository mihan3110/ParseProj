package ProdConsPattern;

import ProdConsPattern.entities.Song;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

public class Analyzer {
   // private final Song song;
    private final LinkedBlockingQueue<Song> queue;


    public Analyzer(LinkedBlockingQueue<Song> queue) {
        this.queue = queue;
    }

    public void analizing() {


        //String song;// = queue.take();
        for (int i = 0; i <queue.size() ; i++) {


            try {
               // System.out.println(queue.size());
                Song song = queue.take();
               // System.out.println(song.getName());
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
                System.out.println(song);
            } catch (InterruptedException e) {
                System.out.println("error");
                e.printStackTrace();
            }

        }

    }


}
























