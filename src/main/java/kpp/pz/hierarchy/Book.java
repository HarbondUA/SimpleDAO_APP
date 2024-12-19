package kpp.pz.hierarchy;

public class Book extends PrintedMaterial {
    private String author;
    private String genre;

    public Book() {}
    public Book(int id, String title, String publisher, int pageCount, String author, String genre) {
        super(id, title, publisher, pageCount);
        this.author = author;
        this.genre = genre;
    }
    public Book(String title, String publisher, int pageCount, String author, String genre) {
        super(title, publisher, pageCount);
        this.author = author;
        this.genre = genre;
    }

    @Override
    public String getPublisher() {
        return super.getPublisher();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) { this.author = author; }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) { this.genre = genre; }

    public int compareTo(PrintedMaterial other) {
        if (other instanceof Book) {
            return this.author.compareTo(((Book) other).getAuthor());
        }
        return super.compareTo(other);
    }

    public String toString() {
        return super.toString() + ", Author: " + author + ", Genre: " + genre;
    }
}
