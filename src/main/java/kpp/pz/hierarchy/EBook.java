package kpp.pz.hierarchy;

public class EBook extends Book {
    private String fileFormat;
    private double fileSize; // in MB

    public EBook(String title, String publisher, int pageCount, String author, String genre, String fileFormat, double fileSize) {
        super(title, publisher, pageCount, author, genre);
        this.fileFormat = fileFormat;
        this.fileSize = fileSize;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public double getFileSize() {
        return fileSize;
    }

    public int compareTo(PrintedMaterial other) {
        if (other instanceof EBook) {
            return Double.compare(this.fileSize, ((EBook) other).getFileSize());
        }
        return super.compareTo(other);
    }

    public String toString() {
        return super.toString() + ", File Format: " + fileFormat + ", File Size: " + fileSize + " MB";
    }
}
