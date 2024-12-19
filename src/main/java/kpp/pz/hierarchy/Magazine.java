package kpp.pz.hierarchy;

public class Magazine extends PrintedMaterial {
    private int issueNumber;
    private String genre;

    public Magazine() {}

    public Magazine(int id, String title, String publisher, int pageCount, int issueNumber, String genre) {
        super(id, title, publisher, pageCount);
        this.issueNumber = issueNumber;
        this.genre = genre;
    }

    public Magazine(String title, String publisher, int pageCount, int issueNumber, String genre) {
        super(title, publisher, pageCount);
        this.issueNumber = issueNumber;
        this.genre = genre;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {this.issueNumber = issueNumber;}

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) { this.genre = genre; }

    public int compareTo(PrintedMaterial other) {
        if (other instanceof Magazine) {
            return Integer.compare(this.issueNumber, ((Magazine) other).getIssueNumber());
        }
        return super.compareTo(other);
    }

    public String toString() {
        return super.toString() + ", Issue Number: " + issueNumber + ", Genre: " + genre;
    }
}

