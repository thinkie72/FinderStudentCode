import java.io.BufferedReader;
import java.io.IOException;

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
        String s = br.readLine();
        String[] split;
        while (s != null){
            split = s.split(",");

            table.add(split[keyCol].trim().toLowerCase(), split[valCol].trim());

            s = br.readLine();
        }
        br.close();
    }

    public String query(String key){
        String isNull = table.get(key.toLowerCase());
        if (isNull != null) return isNull;
        return INVALID;
    }
}