package ProdConsPattern;

import ProdConsPattern.entities.Song;
import myOrmTest.db.DbConnect;
import myOrmTest.db.EntityManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

public class Analyzer {
    // private final Song song;
    private final LinkedBlockingQueue<Song> queue;


    public Analyzer(LinkedBlockingQueue<Song> queue) {
        this.queue = queue;
    }

    public void analizing() {

        EntityManager em = null;
        Connection connection = null;


try {
    DbConnect.initConnection("mysql", "root", "123456", "localhost", "3306", "myParser");

    connection = DbConnect.getConnection();

    em = new EntityManager(connection);

    for (int i = 0; i < queue.size(); i++) {



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
            song.setTop(reverseSortedMap.toString());

        em.persist(song);

    }
}

catch (InterruptedException |SQLException | IllegalAccessException e) {
    System.out.println("error");
    e.printStackTrace();

}
    }


}
























