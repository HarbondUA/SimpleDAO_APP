package kpp.pz.dao;

import kpp.pz.hierarchy.Book;
import kpp.pz.hierarchy.PrintedMaterial;
import kpp.pz.hierarchy.ScienceMagazine;
import kpp.pz.myList.MyListImpl;

import java.sql.SQLException;

public class CollectionLDAO implements IMyDAO<PrintedMaterial> {

    private final MyListImpl<PrintedMaterial> Collection = new MyListImpl<>();

    public CollectionLDAO() {
        Collection.add(new Book("A Monsoon Rising", "Harper Collins", 400, "Thea Guanzon", "Novel"));
        Collection.add(new Book("The Mirror", "Macmillan Publishers", 448, "Nora Roberts", "Romance"));
        Collection.add(new Book("Guide Me Home", "Hachette Book Group", 320, "Attica Locke", "Mystery & Detective"));
        Collection.add(new Book("The Hanging Garden", "Brash Books", 178, "David Wagoner", "Detective"));

        Collection.add(new ScienceMagazine("Scientific Discoveries", "Nature Publishing Group", 120, 15, "Science", "Astronomy", true));
        Collection.add(new ScienceMagazine("Physics Today", "Elsevier", 80, 8, "Physics", "Quantum Mechanics", true));
        Collection.add(new ScienceMagazine("Modern Biology", "Springer", 200, 25, "Biology", "Genetics", true));
        Collection.add(new ScienceMagazine("Mathematics Monthly", "Oxford University Press", 150, 12, "Mathematics", "Algebra", false));
        Collection.add(new ScienceMagazine("Chemistry World", "Cambridge University Press", 95, 10, "Chemistry", "Organic Chemistry", true));
    }

    @Override
    public void add(PrintedMaterial entity) throws SQLException {
        Collection.add(entity);
    }

    @Override
    public void update(PrintedMaterial entity) throws SQLException {
        for (int i = 0; i < Collection.size(); i++) {
            if (Collection.get(i).getTitle().equals(entity.getTitle())) {
                Collection.set(i, entity);
                return;
            }
        }
        throw new SQLException("Material with Title " + entity.getTitle() + " not found");
    }

    @Override
    public void deleteByTitle(String title) throws SQLException {
        PrintedMaterial toRemove = null;
        for (PrintedMaterial pm : Collection) {
            if (pm.getTitle().equalsIgnoreCase(title)) {
                toRemove = pm;
                break;
            }
        }
        if (toRemove != null) {
            Collection.remove(toRemove);
        } else {
            throw new SQLException("Material with Title " + title + " not found");
        }
    }

    @Override
    public Book getBookByTitle(String title) {
        for (PrintedMaterial book : Collection) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return (Book) book;
            }
        }
        return null;
    }

    @Override
    public ScienceMagazine getScienceMagazineByTitle(String title) {
        for (PrintedMaterial scienceMagazine : Collection) {
            if (scienceMagazine.getTitle().equalsIgnoreCase(title)) {
                return (ScienceMagazine) scienceMagazine;
            }
        }
        return null;
    }

    @Override
    public MyListImpl<PrintedMaterial> getBooksByAuthor(String authorName) throws SQLException {
        MyListImpl<PrintedMaterial> booksByAuthor = new MyListImpl<>();
        for (PrintedMaterial material : Collection) {
            if (material instanceof Book) {
                Book book = (Book) material;
                if (book.getAuthor().equalsIgnoreCase(authorName)) {
                    booksByAuthor.add(book);
                }
            }
        }
        return booksByAuthor;
    }

    @Override
    public MyListImpl<PrintedMaterial> getScienceMagazinesByScientificField(String scientificField) throws SQLException {
        MyListImpl<PrintedMaterial> magazinesByField = new MyListImpl<>();
        for (PrintedMaterial material : Collection) {
            if (material instanceof ScienceMagazine) {
                ScienceMagazine magazine = (ScienceMagazine) material;
                if (magazine.getScientificField().equalsIgnoreCase(scientificField)) {
                    magazinesByField.add(magazine);
                }
            }
        }
        return magazinesByField;
    }

    @Override
    public MyListImpl<PrintedMaterial> getAllBooks() throws SQLException {
        MyListImpl<PrintedMaterial> allBooks = new MyListImpl<>();
        for (PrintedMaterial material : Collection) {
            if (material instanceof Book) {
                allBooks.add(material);
            }
        }
        return allBooks;
    }

    @Override
    public MyListImpl<PrintedMaterial> getAllScienceMagazines() throws SQLException {
        MyListImpl<PrintedMaterial> allMagazines = new MyListImpl<>();
        for (PrintedMaterial material : Collection) {
            if (material instanceof ScienceMagazine) {
                allMagazines.add(material);
            }
        }
        return allMagazines;
    }

}
