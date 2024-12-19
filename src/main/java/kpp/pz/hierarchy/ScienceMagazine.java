package kpp.pz.hierarchy;

public class ScienceMagazine extends Magazine {
    private String scientificField;
    private boolean peerReviewed;

    public ScienceMagazine() {}

    public ScienceMagazine(int id, String title, String publisher, int pageCount, int issueNumber, String genre, String scientificField, boolean peerReviewed) {
        super(id, title, publisher, pageCount, issueNumber, genre);
        this.scientificField = scientificField;
        this.peerReviewed = peerReviewed;
    }

    public ScienceMagazine(String title, String publisher, int pageCount, int issueNumber, String genre, String scientificField, boolean peerReviewed) {
        super(title, publisher, pageCount, issueNumber, genre);
        this.scientificField = scientificField;
        this.peerReviewed = peerReviewed;
    }

    public String getScientificField() {
        return scientificField;
    }

    public void setScientificField(String scientificField) {this.scientificField = scientificField;}

    public boolean getPeerReviewed() {
        return peerReviewed;
    }

    public void setPeerReviewed(boolean peerReviewed) {this.peerReviewed = peerReviewed;}

    public int compareTo(PrintedMaterial other) {
        if (other instanceof ScienceMagazine) {
            return this.scientificField.compareTo(((ScienceMagazine) other).getScientificField());
        }
        return super.compareTo(other);
    }

    public String toString() {
        return super.toString() + ", Scientific Field: " + scientificField + ", Peer Reviewed: " + peerReviewed;
    }
}

