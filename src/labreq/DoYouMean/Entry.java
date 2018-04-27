package labreq.DoYouMean;

public class Entry implements Comparable<Entry> {
    private String key;
    private int value;

    Entry(String key, int value) {
        this.key = key;
        this.value = value;
    }
    Entry( Entry entry ) {
        this.key = entry.getKey();
        this.value = entry.getValue();
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "[" + value + ", " + key + "]";
    }

    @Override
    public int compareTo(Entry other) {
        if (value > other.value)
            return 1;
        else if (value < other.value)
            return -1;
        return 0;
    }
}
