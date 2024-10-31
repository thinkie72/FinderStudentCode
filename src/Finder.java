import java.io.BufferedReader;
import java.io.IOException;
import java.util.Locale;

/**
 * Finder
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 *
 * Completed by: Tyler Hinkie
 **/

public class Finder {

    private static final String INVALID = "INVALID KEY";
    private static final int BUCKETS = 124999981;
    private Bucket[] table;

    public Finder() {
        table = new Bucket[BUCKETS];
    }

    public void buildTable(BufferedReader br, int keyCol, int valCol) throws IOException {
        // TODO: Complete the buildTable() function!
        String s = br.readLine();
        String[] split;
        Bucket b;
        while (s != null){
            split = s.split(",");
            int index = keyHash(split[keyCol]);

            if (table[index] == null) {
                table[index] = new Bucket();
            }

            table[index].add(split[valCol]);

            s = br.readLine();
        }
        br.close();
    }

    public String query(String key){
        // TODO: Complete the query() function!
        String isNull = table[keyHash(key)].get();
        if (isNull.isEmpty()) return INVALID;
        return isNull;
    }

    private int keyHash(String key) {
        long hash = 0;
        int radix = 256;
        int m = key.length();
        for (int i = 0; i < m; i++) {
            hash = (hash * radix + key.charAt(i)) % BUCKETS;
        }
        return (int) hash;
    }
}