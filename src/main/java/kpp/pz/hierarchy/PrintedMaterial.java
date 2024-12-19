package kpp.pz.hierarchy;

public class PrintedMaterial implements Comparable<PrintedMaterial>{
    private int id = 0;
    private String title;
    private String publisher;
    private int pageCount;

    public PrintedMaterial() {}
    public PrintedMaterial(int id, String title, String publisher, int pageCount) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.pageCount = pageCount;
    }
    public PrintedMaterial(String title, String publisher, int pageCount) {
        this.title = title;
        this.publisher = publisher;
        this.pageCount = pageCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) { this.publisher = publisher; }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount ) { this.pageCount = pageCount; }

    public int compareTo(PrintedMaterial other) {
        return Integer.compare(this.pageCount, other.pageCount);
    }

    public String toString() {
        return "Title: " + title + ", Publisher: " + publisher + ", Pages: " + pageCount;
    }
}
