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
    private HashMap table;

    public Finder() {
        table = new HashMap();
    }

    public void buildTable(BufferedReader br, int keyCol, int valCol) throws IOException {
        // TODO: Complete the buildTable() function!
        String s = br.readLine();
        String[] split;
        while (s != null){
            split = s.split(",");

            table.put(split[keyCol], split[valCol]);

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