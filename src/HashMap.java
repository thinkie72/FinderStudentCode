// By Tyler Hinkie in 2024
public class HashMap {
    // Instance Variables
    private final static int DEFAULT_TABLE_SIZE = 19;
    // 256 caused overflow, but small primes like 31 had no issues
    private final int RADIX = 31;
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

    // Adds the key-value pair to the corresponding arrays using linear probing and hashing
    public void add(String key, String value) {
        int index = hash(key);
        // Finds the index that the key-value pair should be inserted into
        while (keys[index] != null) {
            index = (index + 1) % tableSize;
        }

        // Adds keys and values to the arrays
        keys[index] = key;
        vals[index] = value;

        // Increments the number of pairs now that insertion has occurred
        n++;

        // Resizes if load factor is greater than 1/2
        if (n * 1.0 / tableSize > 0.5) resize();
    }

    // Returns the value of a given key by reverse-engineering linear probing to find the corresponding value
    public String get(String key) {
        int index = hash(key);
        while (keys[index] != null) {
            if (key.equals(keys[index])) return vals[index];
            index = (index + 1) % tableSize;
        }
        return null;
    }

    // Uses Horner's Method to hash a code for the keys for array indexing
    private int hash(String key) {
        int hash = 0;
        int m = key.length();
        for (int i = 0; i < m; i++) {
            hash = (hash * RADIX + key.charAt(i)) % tableSize;
        }
        return hash;
    }

    // Adds old elements to the new, larger array with rehashed indexes (using add() again in the process)
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
                add(oldKeys[i], oldVals[i]);
            }
        }
    }
}
