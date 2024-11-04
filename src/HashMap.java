// By Tyler Hinkie in 2024
public class HashMap {
    // Instance Variables
    private double loadFactor;
    private int numPairs;
    private String[] keys;
    private String[] vals;
    private int tableSize = 19;
    private final int RADIX = 256;

    // Constructor
    public HashMap() {
        keys = new String[tableSize];
        vals = new String[tableSize];
    }

    // Helper Methods
    public void checkLoadFactor() {
        if (numPairs * 1.0 / tableSize > 0.5) resize();
    }

    public void put(String key, String value) {
        int index = hash(key);
        keys[index] = key;
        vals[index] = value;
    }

    public String get(String key) {
        int start = hash(key);
        for (int i = start; i < tableSize; i++) {
            if (key.equals(keys[i])) return vals[i];
        }
    }

    private int hash(String key) {
        int hash = 0;
        int m = key.length();
        for (int i = 0; i < m; i++) {
            hash = (hash * RADIX + key.charAt(i)) % tableSize;
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


        for (int i = 0; i < oldTableSize; i++) {
            if (oldKeys[i] != null) put(oldKeys[i], oldVals[i]);
        }
    }
}
