package kpp.pz.myList;

import kpp.pz.hierarchy.PrintedMaterial;

import java.util.Comparator;

public class TitleComparator implements Comparator<PrintedMaterial> {
    @Override
    public int compare(PrintedMaterial o1, PrintedMaterial o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}

