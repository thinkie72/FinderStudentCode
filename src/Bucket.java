import java.util.ArrayList;

// By Tyler Hinkie in 2024
public class Bucket {
    // Instance Variables

    private ArrayList<String> values;

    // Constructor

    public Bucket() {
        values = new ArrayList<>();
    }

    // Methods

    public void add(String val) {
        values.add(val);
    }

    public String get() {
        if (values.isEmpty()) return null;
        return values.get((int) (Math.random() * values.size() - 1));
    }
}
