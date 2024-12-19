package kpp.pz.hierarchy;

public class Calendar extends PrintedMaterial {
    private String calendarType;

    public Calendar(String title, String publisher, int pageCount, String calendarType) {
        super(title, publisher, pageCount);
        this.calendarType = calendarType;
    }

    public String getCalendarType() {
        return calendarType;
    }

    public int compareTo(PrintedMaterial other) {
        if (other instanceof Calendar) {
            return this.calendarType.compareTo(((Calendar) other).getCalendarType());
        }
        return super.compareTo(other);
    }

    public String toString() {
        return super.toString() + ", Calendar Type: " + calendarType;
    }
}
