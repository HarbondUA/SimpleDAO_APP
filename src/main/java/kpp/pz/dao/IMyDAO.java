package kpp.pz.dao;

import kpp.pz.hierarchy.Book;
import kpp.pz.hierarchy.ScienceMagazine;
import kpp.pz.myList.MyListImpl;

import java.sql.SQLException;

public interface IMyDAO<T> {
    void add(T entity) throws SQLException;
    void update(T entity) throws SQLException;
    void deleteByTitle(String title) throws SQLException;
    Book getBookByTitle(String title) throws SQLException;
    ScienceMagazine getScienceMagazineByTitle(String title) throws SQLException;
    MyListImpl<T> getBooksByAuthor(String authorName) throws SQLException;
    MyListImpl<T> getScienceMagazinesByScientificField(String scientificField) throws SQLException;
    MyListImpl<T> getAllBooks() throws SQLException;
    MyListImpl<T> getAllScienceMagazines() throws SQLException;
}
