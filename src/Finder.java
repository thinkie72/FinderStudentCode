import java.io.BufferedReader;
import java.io.IOException;

/**
 * Finder
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 * <p>
 * Completed by: Tyler Hinkie
 **/

public class Finder {

    private static final String INVALID = "INVALID KEY";
    private HashMap table;

    public Finder() {
        table = new HashMap();
    }

    // Reads in the csv files using a BufferedReader and String.split()
    public void buildTable(BufferedReader br, int keyCol, int valCol) throws IOException {
        String s = br.readLine();
        String[] split;
        while (s != null) {
            split = s.split(",");
            // Adds elements from each row via the corresponding columns for keys and values
            table.add(split[keyCol], split[valCol]);
            s = br.readLine();
        }
        br.close();
    }

    // Searches for the value to a key, and returns it if it exists
    public String query(String key) {
        String isNull = table.get(key);
        if (isNull != null) return isNull;
        return INVALID;
    }
}