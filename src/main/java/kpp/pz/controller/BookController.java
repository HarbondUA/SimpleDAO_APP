package kpp.pz.controller;

import kpp.pz.dao.DAOFactory;
import kpp.pz.dao.IMyDAO;
import kpp.pz.dao.TypeDAO;
import kpp.pz.hierarchy.Book;
import kpp.pz.hierarchy.PrintedMaterial;
import kpp.pz.myList.MyListImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
@RequestMapping("/bookPages")
public class BookController {
    private final IMyDAO<PrintedMaterial> dao;

    public BookController() throws SQLException {
        this.dao = DAOFactory.getDAOInstance(TypeDAO.MySQL);
//        this.dao = DAOFactory.getDAOInstance(TypeDAO.MyList);
    }

    @GetMapping
    public String showAllBooks(Model model) throws SQLException {
        MyListImpl<PrintedMaterial> list = dao.getAllBooks();
        model.addAttribute("allBooks", list);
        return "bookPages/booksPage";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String showAddBookView(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "bookPages/addBookPage";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public String addBook(Model model, Book book) throws SQLException {
        dao.add(book);
        return "redirect:/bookPages";
    }

    @RequestMapping(value = {"/edit"}, method = RequestMethod.GET)
    public String showEditBookView(Model model) {
        return "bookPages/findBookToEditPage";
    }

    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
    public String editBook(@RequestParam("title") String title, Model model) throws SQLException {
        PrintedMaterial book = dao.getBookByTitle(title);
        if (book == null) {
            model.addAttribute("errorMessage", "Book not found");
            return "redirect:/bookPages";
        }
        model.addAttribute("book", book);
        return "bookPages/editBookPage";
    }

    @RequestMapping(value = {"/edit/save"}, method = RequestMethod.POST)
    public String saveEditedBook(Book book) throws SQLException {
        dao.update(book);
        return "redirect:/bookPages";
    }

    @RequestMapping(value = {"/delete"}, method = RequestMethod.GET)
    public String showDeleteBookView(Model model) {
        return "bookPages/deleteBookPage";
    }

    @RequestMapping(value = {"/delete"}, method = RequestMethod.POST)
    public String deleteBook(@RequestParam("title") String title, Model model) throws SQLException {
        PrintedMaterial book = dao.getBookByTitle(title);
        if (book == null) {
            model.addAttribute("errorMessage", "Book not found");
            return "bookPages/deleteBookPage";
        }
        dao.deleteByTitle(title);
        return "redirect:/bookPages";
    }

    @RequestMapping(value = {"/booksByAuthor"}, method = RequestMethod.GET)
    public String showGetBooksByAuthorView(Model model) {
        return "bookPages/findBooksByAuthorPage";
    }

    @RequestMapping(value = {"/booksByAuthor"}, method = RequestMethod.POST)
    public String getBooksByAuthor(@RequestParam("authorName") String authorName, Model model) throws SQLException {
        MyListImpl<PrintedMaterial> list = dao.getBooksByAuthor(authorName);
        model.addAttribute("allBooks", list);
        return "bookPages/findBooksByAuthorPage";
    }
}
