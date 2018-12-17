package ProdConsPattern;

import ProdConsPattern.entities.Song;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

public class Analyzer {
    private final Song song;
    public LinkedBlockingQueue<Song> rueue;

    public Analyzer(Song song) {
        this.song = song;
    }


    public void analizing() {


        //String song;// = queue.take();

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
    }


}
























