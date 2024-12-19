package kpp.pz.hierarchy;

public class DeskCalendar extends Calendar {
    private String pageType;

    public DeskCalendar(String title, String publisher, int pageCount, String calendarType, String pageType) {
        super(title, publisher, pageCount, calendarType);
        this.pageType = pageType;
    }

    public String getPageType() {
        return pageType;
    }

    public int compareTo(PrintedMaterial other) {
        if (other instanceof DeskCalendar) {
            return this.pageType.compareTo(((DeskCalendar) other).getPageType());
        }
        return super.compareTo(other);
    }

    public String toString() {
        return super.toString() + ", Page Type: " + pageType;
    }
}

