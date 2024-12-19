package kpp.pz.hierarchy;

public class WallCalendar extends Calendar {
    private String mountType;

    public WallCalendar(String title, String publisher, int pageCount, String calendarType, String mountType) {
        super(title, publisher, pageCount, calendarType);
        this.mountType = mountType;
    }

    public String getMountType() {
        return mountType;
    }

    public int compareTo(PrintedMaterial other) {
        if (other instanceof WallCalendar) {
            return this.mountType.compareTo(((WallCalendar) other).getMountType());
        }
        return super.compareTo(other);
    }

    public String toString() {
        return super.toString() + ", Mount Type: " + mountType;
    }
}

