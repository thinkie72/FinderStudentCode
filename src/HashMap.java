// By Tyler Hinkie in 2024
public class HashMap {
    // Instance Variables
    private final int DEFAULT_TABLE_SIZE = 19;
    private int n;
    private String[] keys;
    private String[] vals;
    private int tableSize;

    // Constructor
    public HashMap() {
        tableSize = DEFAULT_TABLE_SIZE;
        keys = new String[tableSize];
        vals = new String[tableSize];
    }

    // Helper Methods

    public void add(String key, String value) {
        int index = hash(key);
        while (keys[index] != null && !keys[index].equals(key)) {
            index = (index + 1) % tableSize;
        }
        if (keys[index] == null) n++;
        keys[index] = key;
        vals[index] = value;
        if (n * 1.0 / tableSize > 0.5) resize();
    }

    public String get(String key) {
        int index = hash(key);
        while (keys[index] != null) {
            if (key.equals(keys[index])) return vals[index];
            index = (index + 1) % tableSize;
        }
        return null;
    }

    private int hash(String key) {
        int hash = 0;
        int m = key.length();
        for (int i = 0; i < m; i++) {
            hash = (hash * 256 + key.charAt(i)) % tableSize;
        }
        return hash;
    }

    private void resize() {
        int oldTableSize = tableSize;
        tableSize *= 2;
        String[] oldKeys = keys;
        String[] oldVals = vals;
        keys = new String[tableSize];
        vals = new String[tableSize];
        n = 0;

        for (int i = 0; i < oldTableSize; i++) {
            if (oldKeys[i] != null) {
                putForResize(oldKeys[i], oldVals[i]);
            }
        }
    }

    private void putForResize(String key, String value) {
        int index = hash(key);
        while (keys[index] != null) {
            index = (index + 1) % tableSize;
        }
        keys[index] = key;
        vals[index] = value;
        n++;
    }
}
