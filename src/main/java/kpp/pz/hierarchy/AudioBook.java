package kpp.pz.hierarchy;

public class AudioBook extends Book {
    private String narrator;
    private double duration; // in hours

    public AudioBook(String title, String publisher, int pageCount, String author, String genre, String narrator, double duration) {
        super(title, publisher, pageCount, author, genre);
        this.narrator = narrator;
        this.duration = duration;
    }

    public String getNarrator() {
        return narrator;
    }

    public double getDuration() {
        return duration;
    }

    public int compareTo(PrintedMaterial other) {
        if (other instanceof AudioBook) {
            return Double.compare(this.duration, ((AudioBook) other).getDuration());
        }
        return super.compareTo(other);
    }

    public String toString() {
        return super.toString() + ", Narrator: " + narrator + ", Duration: " + duration + " hours";
    }
}

